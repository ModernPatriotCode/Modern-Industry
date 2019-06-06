package modepat.modernindustry.blocks;

import modepat.modernindustry.tileentities.TileEntityTeslaCoil;
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

public class BlockTeslaCoil extends Block {

	
	public BlockTeslaCoil() {
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
		return new TileEntityTeslaCoil();
	}
	
	@Override
	public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		return VoxelShapes.combine(VoxelShapes.combine(VoxelShapes.create(0.37, 0.0625, 0.37, 0.63, 0.1875, 0.63), VoxelShapes.create(0.11, 0.1875, 0.11, 0.89, 0.25, 0.89), IBooleanFunction.OR), VoxelShapes.combine(VoxelShapes.combine(VoxelShapes.create(0.06, 0, 0.06, 0.94, 0.0625, 0.94), VoxelShapes.create(0.25, 0.875, 0.25, 0.75, 1, 0.75), IBooleanFunction.OR), VoxelShapes.create(0.31, 0.25, 0.31, 0.69, 0.875, 0.69), IBooleanFunction.OR), IBooleanFunction.OR);
	}
	
	@Override
	public VoxelShape getCollisionShape(IBlockState state, IBlockReader worldIn, BlockPos pos) {
		return VoxelShapes.combine(VoxelShapes.combine(VoxelShapes.create(0.37, 0.0625, 0.37, 0.63, 0.1875, 0.63), VoxelShapes.create(0.11, 0.1875, 0.11, 0.89, 0.25, 0.89), IBooleanFunction.OR), VoxelShapes.combine(VoxelShapes.combine(VoxelShapes.create(0.06, 0, 0.06, 0.94, 0.0625, 0.94), VoxelShapes.create(0.25, 0.875, 0.25, 0.75, 1, 0.75), IBooleanFunction.OR), VoxelShapes.create(0.31, 0.25, 0.31, 0.69, 0.875, 0.69), IBooleanFunction.OR), IBooleanFunction.OR);
	}

}
