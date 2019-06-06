package modepat.modernindustry.core;

import modepat.modernindustry.api.ReferenceModernIndustry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

public class ItemBlockCore {
	
	
	//Declaration
	
		//Tile Entities
		public static Item flow_battery_item;
		public static Item basic_gearbox_item, advanced_gearbox_item, refined_gearbox_item;
		
		//Ores
		public static Item aluminum_ore_item;
		public static Item gallium_ore_item;
		
		//Frames
		
		
		//Lamps
	
	
		//Material Storage
		public static Item gallium_ingot_block_item;
		public static Item aluminum_ingot_block_item;
		public static Item porous_metal_ingot_block_item;
		
		//Various
		public static Item destabilized_glass_block_item;
		public static Item stabilized_glass_block_item;
		
		public static Item registerItemBlock(Item reg_item, String resource_name, Block reg_block) {
			return reg_item = new ItemBlock(reg_block, new Item.Properties()).setRegistryName(new ResourceLocation(ReferenceModernIndustry.MODID, resource_name));
		}
		
		public static Item registerItemBlock(Item reg_item, String resource_name, Block reg_block, ItemGroup reg_group) {
			return reg_item = new ItemBlock(reg_block, new Item.Properties().group(reg_group)).setRegistryName(new ResourceLocation(ReferenceModernIndustry.MODID, resource_name));
		}
		
		public static Item registerItemBlock(Item reg_item, String resource_name, Block reg_block, ItemGroup reg_group, int maxStackSize) {
			return reg_item = new ItemBlock(reg_block, new Item.Properties().group(reg_group).maxStackSize(maxStackSize)).setRegistryName(new ResourceLocation(ReferenceModernIndustry.MODID, resource_name));
		}

	
}
