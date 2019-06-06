package modepat.modernindustry.items;

import modepat.modernindustry.api.ReferenceModernIndustry;
import modepat.modernindustry.items.tiers.TierCuttingTool;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemAxe;

public class ItemCuttingTool extends ItemAxe{

	public static IItemTier tier_cutting_tool = new TierCuttingTool();
	public static Properties cutting_tool_properties = new Properties().maxStackSize(1).setNoRepair().group(ReferenceModernIndustry.MODERNINDUSTRYGROUP);
	
	public ItemCuttingTool() {
		super(tier_cutting_tool, 0, 6.0f, cutting_tool_properties);
	}

}
