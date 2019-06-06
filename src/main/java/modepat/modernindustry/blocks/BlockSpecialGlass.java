package modepat.modernindustry.blocks;

import modepat.modernindustry.core.ModernUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSpecialGlass extends BlockGlass {

	private EnumSpecialGlass enumSpecialGlass;
	
	public BlockSpecialGlass(EnumSpecialGlass enumSpecialGlass) {
		super(Block.Properties.create(Material.GLASS, MaterialColor.AIR).hardnessAndResistance(enumSpecialGlass.getHardness(), enumSpecialGlass.getResistance()).sound(SoundType.GLASS));
		this.enumSpecialGlass = enumSpecialGlass;
	}
	
	public EnumSpecialGlass getEnum() {
		return this.enumSpecialGlass;
	}
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		super.onEntityWalk(worldIn, pos, entityIn);
		if(this.enumSpecialGlass.dangerous) {
			if (!worldIn.isRemote) {
				worldIn.destroyBlock(pos, false);
			}
			java.util.List<EntityLivingBase> entityList = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(new BlockPos(pos.getX()-8,pos.getY()-8,pos.getZ()-8), new BlockPos(pos.getX()+8,pos.getY()+8,pos.getZ()+8)));
			if(entityList.size() != 0) {
				for (int i = 0; i < entityList.size(); i++) {
					entityList.get(i).attackEntityFrom(DamageSource.MAGIC, Math.round(Math.random()*5));
					entityList.get(i).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 40));
					double[] velocity = ModernUtil.getScaledVelocityForBlock(pos.getX(), pos.getY(), pos.getZ(), entityList.get(i).getPosition().getX(), entityList.get(i).getPosition().getY(), entityList.get(i).getPosition().getZ(), 1);
					entityList.get(i).addVelocity(velocity[0], velocity[1], velocity[2]);
					if (entityList.get(i) instanceof EntityPlayer) {
						worldIn.playSound((EntityPlayer)entityList.get(i), pos, SoundEvents.AMBIENT_CAVE, SoundCategory.BLOCKS, 10, 1);
					}
				}
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(IBlockState state, World worldIn, BlockPos pos, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if(this.enumSpecialGlass.dangerous) {
			if (!worldIn.isRemote) {
				worldIn.destroyBlock(pos, false);
			}
			java.util.List<EntityLivingBase> entityList = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(new BlockPos(pos.getX()-8,pos.getY()-8,pos.getZ()-8), new BlockPos(pos.getX()+8,pos.getY()+8,pos.getZ()+8)));
			if(entityList.size() != 0) {
				for (int i = 0; i < entityList.size(); i++) {
					entityList.get(i).attackEntityFrom(DamageSource.MAGIC, Math.round(Math.random()*5));
					entityList.get(i).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 40));
					double[] velocity = ModernUtil.getScaledVelocityForBlock(pos.getX(), pos.getY(), pos.getZ(), entityList.get(i).getPosition().getX(), entityList.get(i).getPosition().getY(), entityList.get(i).getPosition().getZ(), 1);
					entityList.get(i).addVelocity(velocity[0], velocity[1], velocity[2]);
				}
			}
			worldIn.playSound(player, pos, SoundEvents.AMBIENT_CAVE, SoundCategory.BLOCKS, 10, 1);
			return true;
		}
		return false;
	}
	
	public enum EnumSpecialGlass {
		
		UNSTABLE(0, "destabilized_glass_block", true),
		STABLE(1, "stabilized_glass_block");
		
		int id;
		String name;
		boolean dangerous;
		
		private EnumSpecialGlass(int id, String name, boolean dangerous) {
			this.id = id;
			this.name = name;
			this.dangerous = dangerous;
		}
		
		private EnumSpecialGlass(int id, String name) {
			this.id = id;
			this.name = name;
			this.dangerous = false;
		}
		
		public int getID() {
			return this.id;
		}
		
		public boolean getIsDangerous() {
			return this.dangerous;
		}
		
		public String getName() {
			return this.name;
		}
		
		public float getHardness() {
			if (this.dangerous) return 0.15f;
			return 0.5f;
		}
		
		public float getResistance() {
			if (this.dangerous) return 30;
			return 60;
		}
		
	}

}
