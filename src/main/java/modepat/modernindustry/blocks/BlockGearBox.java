package modepat.modernindustry.blocks;

import java.util.stream.IntStream;

import modepat.modernindustry.core.ModernUtil;
import modepat.modernindustry.tileentities.TileEntityGearBox;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Particles;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BlockGearBox extends Block {

	private EnumGearBox enumGearBox;
	
	public BlockGearBox(EnumGearBox enumGearBox) {
		super(Block.Properties.create(enumGearBox.getMaterial(), enumGearBox.getMaterial().getColor()).hardnessAndResistance(enumGearBox.getHardness(), enumGearBox.getResistance()).sound(enumGearBox.getSoundType()));
		this.enumGearBox = enumGearBox;
	}
	
	public EnumGearBox getEnumGearBox() {
		return enumGearBox;
	}
	
	@Override
	public void onReplaced(IBlockState state, World worldIn, BlockPos pos, IBlockState newState, boolean isMoving) {
		TileEntityGearBox tileEntityGearBox = (TileEntityGearBox) worldIn.getTileEntity(pos);
		if (!worldIn.isRemote) {
			for(int i = 0; i < tileEntityGearBox.getInventory().getSlots(); i++) {
				if (!tileEntityGearBox.getStackInSlot(i).isEmpty()) {
					EntityItem item = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), tileEntityGearBox.getStackInSlot(i));
					worldIn.spawnEntity(item);
				}
			}
		}
		
		if (hasTileEntity(state)) {
			if (state.getBlock() != newState.getBlock()) {
				worldIn.removeTileEntity(pos);
			}
		}
	}
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(IBlockState state, IBlockReader world) {
		return new TileEntityGearBox();
	}
	
	@Override
	public IBlockState updatePostPlacement(IBlockState stateIn, EnumFacing facing, IBlockState facingState,
			IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		TileEntity tileEntity = worldIn.getTileEntity(currentPos);
		if(tileEntity instanceof TileEntityGearBox) {
			TileEntityGearBox tileEntityGearBox = (TileEntityGearBox) tileEntity;
			tileEntityGearBox.saveAndSync();
		}
			
		return stateIn;
	}
	
	@Override
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntityGearBox tileEntityGearBox = (TileEntityGearBox) worldIn.getTileEntity(pos);
		if(!player.inventory.getCurrentItem().isEmpty()) {
			if(!tileEntityGearBox.isFull()) {
				for(int i = 0; i < tileEntityGearBox.getInventory().getSlots(); i++) {
					if(tileEntityGearBox.getInventory().getStackInSlot(i) == ItemStack.EMPTY) {
						tileEntityGearBox.getInventory().setStackInSlot(i, new ItemStack(player.inventory.getCurrentItem().getItem(), 1));
						player.inventory.getCurrentItem().setCount(player.inventory.getCurrentItem().getCount()-1);
						tileEntityGearBox.saveAndSync();
						return true;
					}
				}
			}
		}
				
		if(!tileEntityGearBox.isEmpty()) {
			for (int i = 0; i < tileEntityGearBox.getInventory().getSlots(); i++) {
				if(!tileEntityGearBox.getInventory().getStackInSlot(tileEntityGearBox.getInventory().getSlots()-1-i).isEmpty()) {
					if(!worldIn.isRemote) {
						EntityItem item = new EntityItem(worldIn, pos.getX(), pos.getY()+1, pos.getZ(), tileEntityGearBox.getStackInSlot(tileEntityGearBox.getInventory().getSlots()-1-i));
						worldIn.spawnEntity(item);	
					}
					tileEntityGearBox.getInventory().setStackInSlot(tileEntityGearBox.getInventory().getSlots()-1-i, ItemStack.EMPTY);
					tileEntityGearBox.saveAndSync();
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		if(this.getEnumGearBox().equals(EnumGearBox.BASIC)) {
			worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS, 6.0f, 1.2f, false);
			IntStream.range(0, 8).forEach(i -> {
				double[] offsetAndVelocity = ModernUtil.getRandomizedOffsetAndScaledVelocityForBlock(pos.getX(), pos.getY(), pos.getZ(), 2);
				worldIn.spawnParticle(Particles.SMOKE, offsetAndVelocity[0], offsetAndVelocity[1], offsetAndVelocity[2], offsetAndVelocity[3], offsetAndVelocity[4], offsetAndVelocity[5]);	
			});
		}
		if(this.getEnumGearBox().equals(EnumGearBox.ADVANCED)) {
			worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS, 7.0f, 0.8f, false);
			IntStream.range(0, 8).forEach(i -> {
				double[] offsetAndVelocity = ModernUtil.getRandomizedOffsetAndScaledVelocityForBlock(pos.getX(), pos.getY(), pos.getZ(), 2);
				worldIn.spawnParticle(Particles.PORTAL, offsetAndVelocity[0], offsetAndVelocity[1], offsetAndVelocity[2], offsetAndVelocity[3], offsetAndVelocity[4], offsetAndVelocity[5]);	
			});
		}
		if(this.getEnumGearBox().equals(EnumGearBox.REFINED)) {
			worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS, 8.0f, 0.3f, false);
			IntStream.range(0, 8).forEach(i -> {
				double[] offsetAndVelocity = ModernUtil.getRandomizedOffsetAndScaledVelocityForBlock(pos.getX(), pos.getY(), pos.getZ(), 2);
				worldIn.spawnParticle(Particles.CLOUD, offsetAndVelocity[0], offsetAndVelocity[1], offsetAndVelocity[2], offsetAndVelocity[3], offsetAndVelocity[4], offsetAndVelocity[5]);	
			});
		}
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}
	
	@Override
	public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		if (this.getEnumGearBox().equals(EnumGearBox.BASIC)) return VoxelShapes.combine(VoxelShapes.create(0.11, 0, 0.11, 0.89, 0.7, 0.89), VoxelShapes.create(0.05, 0, 0.05, 0.95, 0.15, 0.95), IBooleanFunction.NOT_SAME);
		if (this.getEnumGearBox().equals(EnumGearBox.ADVANCED)) return VoxelShapes.combine(VoxelShapes.create(0.05, 0, 0.05, 0.95, 0.2, 0.95), VoxelShapes.create(0.07, 0, 0.07, 0.93, 0.8, 0.93), IBooleanFunction.NOT_SAME);
		if (this.getEnumGearBox().equals(EnumGearBox.REFINED)) return VoxelShapes.create(0.015, 0, 0.015, 0.985, 0.85, 0.985);
		return Block.makeCuboidShape(0, 0, 0, 1, 1, 1);
	}

	@Override
	public VoxelShape getCollisionShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		if (this.getEnumGearBox().equals(EnumGearBox.BASIC)) return VoxelShapes.combine(VoxelShapes.create(0.11, 0, 0.11, 0.89, 0.7, 0.89), VoxelShapes.create(0.05, 0, 0.05, 0.95, 0.15, 0.95), IBooleanFunction.NOT_SAME);
		if (this.getEnumGearBox().equals(EnumGearBox.ADVANCED)) return VoxelShapes.combine(VoxelShapes.create(0.05, 0, 0.05, 0.95, 0.2, 0.95), VoxelShapes.create(0.07, 0, 0.07, 0.93, 0.8, 0.93), IBooleanFunction.NOT_SAME);
		if (this.getEnumGearBox().equals(EnumGearBox.REFINED)) return VoxelShapes.create(0.015, 0, 0.015, 0.985, 0.85, 0.985);
		return Block.makeCuboidShape(0, 0, 0, 1, 1, 1);
	}

	public enum EnumGearBox {
		
		BASIC(0, "basic_gearbox", Material.WOOD, 1, 5.0f, 10.0f, SoundType.WOOD, EnumRarity.COMMON, ToolType.AXE),
		ADVANCED(1, "advanced_gearbox", Material.IRON, 1, 8.0f, 30.0f, SoundType.METAL, EnumRarity.UNCOMMON, ToolType.PICKAXE),
		REFINED(2, "refined_gearbox", Material.IRON, 1, 12.0f, 200.0f, SoundType.METAL, EnumRarity.RARE, ToolType.PICKAXE);
		
		private int id;
		private String name; 
		private Material material; 
		private int inventorySize; 
		private float hardness; 
		private float resistance; 
		private SoundType soundType; 
		private EnumRarity rarity;
		private ToolType toolType;
		
		EnumGearBox(int id, String name, Material material, int inventorySize, float hardness, float resistance, SoundType soundType, EnumRarity rarity, ToolType toolType) {
			this.id = id;
			this.name = name;
			this.material = material;
			this.inventorySize = inventorySize;
			this.hardness = hardness;
			this.resistance = resistance;
			this.soundType = soundType;
			this.rarity = rarity;
			this.toolType = toolType;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public Material getMaterial() {
			return material;
		}
		
		public int getInventorySize() {
			return inventorySize;
		}

		public float getHardness() {
			return hardness;
		}

		public float getResistance() {
			return resistance;
		}

		public SoundType getSoundType() {
			return soundType;
		}
		
		public EnumRarity getRarity() {
			return rarity;
		}

		public ToolType getToolType() {
			return toolType;
		}
		
		public static EnumGearBox getEnumFromID(int id) {
			switch(id) {
			case 0: return EnumGearBox.BASIC;
			case 1: return EnumGearBox.ADVANCED;
			case 2: return EnumGearBox.REFINED;
			}
			return EnumGearBox.BASIC;
		}

	}

}
