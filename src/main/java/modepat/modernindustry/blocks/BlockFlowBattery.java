package modepat.modernindustry.blocks;


import modepat.modernindustry.gui.interactionobjects.InteractionObjectFlowBattery;
import modepat.modernindustry.tileentities.TileEntityFlowBattery;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeBlock;
import net.minecraftforge.fml.network.NetworkHooks;

public class BlockFlowBattery extends BlockDirectional implements IForgeBlock {

	public static final DirectionProperty FACING = BlockHorizontal.HORIZONTAL_FACING;
	public static Block.Properties properties = Block.Properties.create(Material.IRON, MaterialColor.GRAY).hardnessAndResistance(6.0f, 30.0f).sound(SoundType.METAL);

	public BlockFlowBattery() {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, EnumFacing.NORTH));
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, IBlockState> builder) {
		builder.add(FACING);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(IBlockState state, IBlockReader world) {
		return new TileEntityFlowBattery();
	}
	
	public IBlockState getStateForPlacement(BlockItemUseContext context) {
	      return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		worldIn.playSound(pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, SoundEvents.BLOCK_WATER_AMBIENT, SoundCategory.BLOCKS, 9.0f, 0.8f, false);
	}
	
	@Override
	public void onBlockAdded(IBlockState state, World worldIn, BlockPos pos, IBlockState oldState) {
	}
	
	@Override
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote && player instanceof EntityPlayerMP) {
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			NetworkHooks.openGui((EntityPlayerMP) player, new InteractionObjectFlowBattery((TileEntityFlowBattery)tileEntity), (buffer) -> {
					buffer.writeBlockPos(pos);
			});
		}
		return true;
	}
	
	@Override
	public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState,
			IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		TileEntity tileEntity = worldIn.getTileEntity(currentPos);
		if(tileEntity instanceof TileEntityFlowBattery) {
			TileEntityFlowBattery tileEntityFlowBattery = (TileEntityFlowBattery) tileEntity;
			tileEntityFlowBattery.saveAndSync();
		}
			
		return stateIn;
	}
	
	@Override
	public int getHarvestLevel(IBlockState state) {
		return 2;
	}
	
	@Override
	public ToolType getHarvestTool(IBlockState state) {
		return ToolType.PICKAXE;
	}
	
}
