package modepat.modernindustry.core;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;


public class BlockCore {
	
	
	//Declaration
	
		//Tile Entities
		public static Block flow_battery;
		public static Block basic_gearbox, advanced_gearbox, refined_gearbox;
		
		//Ores
		public static Block aluminum_ore;
		public static Block gallium_ore;
		
		//Frames
		
		
		//Lamps
		
	
		//Material Storage
		public static Block gallium_ingot_block;
		public static Block aluminum_ingot_block;
		public static Block porous_metal_ingot_block;
	
		//Various
		public static Block destabilized_glass_block;
		public static Block stabilized_glass_block;
		
		
	public static Block registerBlock(Block reg_block, Material materialIn, String resource_name) {
		return reg_block = new Block(Block.Properties.create(materialIn)).setRegistryName(resource_name);
	}
	

}
