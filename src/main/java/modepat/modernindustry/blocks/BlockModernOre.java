package modepat.modernindustry.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;

public class BlockModernOre extends BlockOre {
	
	private EnumModernOre modernOreEnum;
	
	public BlockModernOre(EnumModernOre modernOreEnum) {
		super(Block.Properties.create(Material.ROCK, MaterialColor.GRAY).hardnessAndResistance(modernOreEnum.getHardness(), modernOreEnum.getResistance()).sound(SoundType.STONE));
		this.modernOreEnum = modernOreEnum;
	}
	
	@Override
	public int getHarvestLevel(IBlockState state) {
		return modernOreEnum.getHarvestLevel();
	}
	
	@Override
	public ToolType getHarvestTool(IBlockState state) {
		return ToolType.PICKAXE;
	}
	
	public EnumModernOre getEnum() {
		return this.modernOreEnum;
	}
	
	
	
	public enum EnumModernOre {
		
		ALUMINUM(0, "aluminum_ore", 2, 1),
		GALLIUM(1, "gallium_ore", 1, 1);
		
		int id;
		String name;
		int harvestLevel;
		int rarity;
		float hardness;
		float resistance;
		Item customDrop;
		int customItemDropAmount;
		
		private EnumModernOre(int id, String name, int harvestLevel, int rarity, float hardness, float resistance, Item customDrop, int customItemDropAmount) {
			this.id = id;
			this.name = name;
			this.harvestLevel = harvestLevel;
			this.rarity = rarity;
			this.hardness = hardness;
			this.resistance = resistance;
			this.customDrop = customDrop;
			this.customItemDropAmount = customItemDropAmount;
		}
		
		private EnumModernOre(int id, String name, int harvestLevel, int rarity, float hardness, float resistance) {
			this.id = id;
			this.name = name;
			this.harvestLevel = harvestLevel;
			this.rarity = rarity;
			this.hardness = hardness;
			this.resistance = resistance;
		}
		
		private EnumModernOre(int id, String name, int harvestLevel, int rarity) {
			this.id = id;
			this.name = name;
			this.harvestLevel = harvestLevel;
			this.rarity = rarity;
			this.hardness = 3.0f;
			this.resistance = 15.0f;
		}
		
		@Nullable
		public Item getCustomDrop () {
			return this.customDrop;
		}
		
		public int getCustomDropAmount() {
			return this.customItemDropAmount;
		}
		
		public boolean hasCustomDrop() {
			return this.customDrop!=null;
		}
		
		public int getID() {
			return this.id;
		}
		
		public int getHarvestLevel() {
			return this.harvestLevel;
		}
		
		public String getName() {
			return this.name;
		}
		
		public int getRarity() {
			return this.rarity;
		}
		
		public float getHardness() {
			return this.hardness;
		}
		
		public float getResistance() {
			return this.resistance;
		}
		
	}

}
