package modepat.modernindustry.blocks;

import modepat.modernindustry.tileentities.TileEntityTeslaCoilFocus;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class BlockTeslaCoilFocus extends Block {

	public BlockTeslaCoilFocus() {
		super(Block.Properties.create(Material.IRON).hardnessAndResistance(3.0f, 30.0f));
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
		return new TileEntityTeslaCoilFocus();
	}
	
	@Override
	public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		return VoxelShapes.combine(VoxelShapes.combine(VoxelShapes.combine(VoxelShapes.combine(VoxelShapes.create(7.1/16, 11/16, 7.1/16, 8.9/16, 12.75/16, 8.9/16), VoxelShapes.create(7.6/16, 6/16, 7.6/16, 8.4/16, 14.5/16, 8.4/16), IBooleanFunction.OR), VoxelShapes.combine(VoxelShapes.create(6.9/16, 10/16, 6.9/16, 9.1/16, 10.5/16, 9.1/16), VoxelShapes.create(7.4/16, 6/16, 7.4/16, 8.6/16, 9/16, 8.6/16), IBooleanFunction.OR), IBooleanFunction.OR), VoxelShapes.combine(VoxelShapes.combine(VoxelShapes.create(6.7/16, 6/16, 6.7/16, 9.3/16, 6.5/16, 9.3/16), VoxelShapes.create(4.4/16, 1.25/16, 4.4/16, 11.6/16, 1.75/16, 11.6/16), IBooleanFunction.OR), VoxelShapes.combine(VoxelShapes.create(5.3/16, 3/16, 5.3/16, 10.7/16, 4/16, 10.7/16), VoxelShapes.create(6.3/16, 1/16, 6.3/16, 9.7/16, 6/16, 9.7/16), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), VoxelShapes.create(4.5/16, 0, 4.5/16, 11.5/16, 1/16, 11.5/16), IBooleanFunction.OR);
	}
	
	@Override
	public VoxelShape getCollisionShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		return VoxelShapes.combine(VoxelShapes.combine(VoxelShapes.combine(VoxelShapes.combine(VoxelShapes.create(7.1/16, 11/16, 7.1/16, 8.9/16, 12.75/16, 8.9/16), VoxelShapes.create(7.6/16, 6/16, 7.6/16, 8.4/16, 14.5/16, 8.4/16), IBooleanFunction.OR), VoxelShapes.combine(VoxelShapes.create(6.9/16, 10/16, 6.9/16, 9.1/16, 10.5/16, 9.1/16), VoxelShapes.create(7.4/16, 6/16, 7.4/16, 8.6/16, 9/16, 8.6/16), IBooleanFunction.OR), IBooleanFunction.OR), VoxelShapes.combine(VoxelShapes.combine(VoxelShapes.create(6.7/16, 6/16, 6.7/16, 9.3/16, 6.5/16, 9.3/16), VoxelShapes.create(4.4/16, 1.25/16, 4.4/16, 11.6/16, 1.75/16, 11.6/16), IBooleanFunction.OR), VoxelShapes.combine(VoxelShapes.create(5.3/16, 3/16, 5.3/16, 10.7/16, 4/16, 10.7/16), VoxelShapes.create(6.3/16, 1/16, 6.3/16, 9.7/16, 6/16, 9.7/16), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), VoxelShapes.create(4.5/16, 0, 4.5/16, 11.5/16, 1/16, 11.5/16), IBooleanFunction.OR);
	}

}
