package modepat.modernindustry.api;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class SoundEventModernIndustry {
	
	public static ResourceLocation ambient_dark_resource_location = new ResourceLocation(ReferenceModernIndustry.MODID, "ambient_dark");
	public static ResourceLocation ambient_dark_2_resource_location = new ResourceLocation(ReferenceModernIndustry.MODID, "ambient_dark_2");
	public static ResourceLocation record_maze_resource_location = new ResourceLocation(ReferenceModernIndustry.MODID, "record_maze");
	public static ResourceLocation record_waves_resource_location = new ResourceLocation(ReferenceModernIndustry.MODID, "record_waves");
	
	public static SoundEvent AMBIENT_DARK = new SoundEvent(ambient_dark_resource_location);
	public static SoundEvent AMBIENT_DARK_2 = new SoundEvent(ambient_dark_2_resource_location);
	public static SoundEvent RECORD_MAZE = new SoundEvent(record_maze_resource_location);
	public static SoundEvent RECORD_WAVES = new SoundEvent(record_waves_resource_location);

}
