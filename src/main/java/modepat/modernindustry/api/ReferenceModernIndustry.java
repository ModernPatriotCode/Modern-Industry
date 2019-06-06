package modepat.modernindustry.api;

import modepat.modernindustry.ModernIndustryItemGroup;
import modepat.modernindustry.ModernIndustryMain;
import modepat.modernindustry.tileentities.TileEntityFlowBattery;
import modepat.modernindustry.tileentities.TileEntityGearBox;
import modepat.modernindustry.tileentities.TileEntityTeslaCoil;
import modepat.modernindustry.tileentities.TileEntityTeslaCoilFocus;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;

public class ReferenceModernIndustry {

	public static final String MODID = "modernindustry";
	public static final String MODNAME = "Modern Industry";
	public static final String MODVERSION = "0.1";
	public static final String VERSION = "1.13.2";
	
	public static final ItemGroup MODERNINDUSTRYGROUP = new ModernIndustryItemGroup();
	public static ModernIndustryMain INSTANCE;
	
	public static TileEntityType<TileEntityFlowBattery> TILE_ENTITY_TYPE_FLOW_BATTERY;
	public static TileEntityType<TileEntityGearBox> TILE_ENTITY_TYPE_GEAR_BOX;
	public static TileEntityType<TileEntityTeslaCoil> TILE_ENTITY_TYPE_TESLA_COIL;
	public static TileEntityType<TileEntityTeslaCoilFocus> TILE_ENTITY_TYPE_TESLA_COIL_FOCUS;
	
	public static String NBT_RECEIVE_BOOLEAN_KEY = "can_receive";
	public static String NBT_EXTRACT_BOOLEAN_KEY = "can_extract";
	public static String NBT_MAX_ENERGY_INT_KEY = "max_energy";
	public static String NBT_ENERGY_STORED_INT_KEY = "en_stored";
	public static String NBT_ENUM_ID_KEY = "enum_id";
	public static String NBT_INVENTORY_TAG_KEY = "inventory";
	
	public static final String GUI_FLOW_BATTERY = ":flow_battery_gui";
	public static final int GUI_FLOW_BATTERY_INDEX = 0;

}
