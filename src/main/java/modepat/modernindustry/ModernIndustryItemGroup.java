package modepat.modernindustry;

import modepat.modernindustry.core.objects.BlockCore;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModernIndustryItemGroup extends ItemGroup {

	public ModernIndustryItemGroup() {
		super("modernindustry");
		
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(BlockCore.flow_battery.asItem());
	}

}
