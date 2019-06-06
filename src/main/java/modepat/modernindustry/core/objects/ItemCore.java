package modepat.modernindustry.core.objects;

import modepat.modernindustry.api.ReferenceModernIndustry;
import net.minecraft.item.Item;
//import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;

public class ItemCore {
	
	//Declaration
	
		//Ingots
		public static Item gallium_ingot;
		public static Item aluminum_ingot;
		public static Item porous_metal_ingot;
	
		//Crystals
		public static Item destabilized_glass_shard;
		public static Item stabilized_glass_shard;
		public static Item gallium_crystal;
		public static Item alumina_crystal;
		public static Item hematite_crystal;
	
		//Powders
		public static Item thermite_powder;
		public static Item aluminum_oxide_powder;
		public static Item iron_oxide_powder;
	
		//Parts
		public static Item coil;
		public static Item magnet;
		public static Item conducting_bits;
	
		//Tools
		public static Item monkey_wrench;
		public static Item cutting_tool;
		public static Item conducting_focus;
	
		//Various
		public static Item shrapnel;
		public static Item crystal_seed;
	
		
	public static Item registerItem(Item reg_item, ItemGroup reg_group, String resource_name) {
		return reg_item = new Item(new Item.Properties().group(reg_group)).setRegistryName(new ResourceLocation(ReferenceModernIndustry.MODID, resource_name));
	}
	
	public static Item registerItem(Item reg_item, Item.Properties properties, String resource_name) {
		return reg_item = new Item(properties).setRegistryName(new ResourceLocation(ReferenceModernIndustry.MODID, resource_name));
	}
	
}
