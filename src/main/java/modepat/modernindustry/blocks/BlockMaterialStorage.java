package modepat.modernindustry.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import modepat.modernindustry.core.ItemCore;

public class BlockMaterialStorage extends Block {

	private EnumMaterialStorageBlock enumMaterialStorageBlock;
	
	public BlockMaterialStorage(EnumMaterialStorageBlock enumMaterialStorageBlock) {
		super(Block.Properties.create(enumMaterialStorageBlock.getMaterial(), enumMaterialStorageBlock.getMapColor()).hardnessAndResistance(enumMaterialStorageBlock.getHardness(), enumMaterialStorageBlock.getResistance()).sound(enumMaterialStorageBlock.getSoundType()));
		this.enumMaterialStorageBlock = enumMaterialStorageBlock;
	}
	
	public EnumMaterialStorageBlock getEnum() {
		return this.enumMaterialStorageBlock;
	}
	
	public enum EnumMaterialStorageBlock {
		
		ALUMINUM(0, "aluminum_ingot_block", Material.IRON, ItemCore.aluminum_ingot),
		GALLIUM(1, "gallium_ingot_block", Material.IRON, ItemCore.gallium_ingot),
		POROUS_METAL(2, "porous_metal_ingot_block", Material.IRON, MaterialColor.BLACK, 4.0f, 18.0f, SoundType.METAL, ItemCore.porous_metal_ingot);
		
		int id;
		String name;
		Material material;
		MaterialColor color;
		float hardness;
		float resistance;
		SoundType soundType;
		Item storageItem;
		
		/**
		 * Enum containing minimal Information about the storageblock.
		 * @param id			ID of the Enum.
		 * @param name			Resource-Location-Name.
		 * @param material		Material of the given storageblock.
		 * @param storageItem 	Item that is stored by means of the block.
		 */
		private EnumMaterialStorageBlock(int id, String name, Material material, Item storageItem) {
			this.id = id;
			this.name = name;
			this.material = material;
			this.color = material.getColor();
			this.hardness = 5.0f;
			this.resistance = 30.0f;
			this.soundType = SoundType.METAL;
			this.storageItem = storageItem;
		}
		
		/**
		 * 
		 * @param id			ID of the Enum.
		 * @param name			Resource-Location-Name.
		 * @param material		Material of the given storageblock.
		 * @param color			MapColor of the given storageblock.
		 * @param soundType		SoundType of the given storageblock.
		 * @param storageItem	Item that is stored by means of the block.
		 */
		private EnumMaterialStorageBlock(int id, String name, Material material, MaterialColor color, SoundType soundType, Item storageItem) {
			this.id = id;
			this.name = name;
			this.material = material;
			this.color = color;
			this.hardness = 5.0f;
			this.resistance = 30.0f;
			this.soundType = soundType;
			this.storageItem = storageItem;
		}
		
		/**
		 * 
		 * @param id			ID of the Enum.
		 * @param name			Resource-Location-Name.
		 * @param material		Material of the given storageblock.
		 * @param color			MapColor of the given storageblock.
		 * @param hardness		Hardness of the given storageblock. Defaults to 5.0f if not defined.
		 * @param resistance	Resistance of the given storageblock. Defaults to 30.0f if not defined.
		 * @param soundType		SoundType of the given storageblock.
		 * @param storageItem	Item that is stored by means of the block.
		 */
		private EnumMaterialStorageBlock(int id, String name, Material material, MaterialColor color, float hardness, float resistance, SoundType soundType, Item storageItem) {
			this.id = id;
			this.name = name;
			this.material = material;
			this.color = color;
			this.hardness = hardness;
			this.resistance = resistance;
			this.soundType = soundType;
			this.storageItem = storageItem;
		}
		
		public Material getMaterial() {
			return this.material;
		}
		
		public Item getItemStored() {
			return this.storageItem;
		}
		
		public MaterialColor getMapColor() {
			return this.color;
		}
		
		public SoundType getSoundType() {
			return this.soundType;
		}
		
		public int getID() {
			return this.id;
		}
		
		public String getName() {
			return this.name;
		}
		
		public float getHardness() {
			return this.hardness;
		}
		
		public float getResistance() {
			return this.resistance;
		}
		
		
	}

}
