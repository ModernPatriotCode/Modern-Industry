package modepat.modernindustry.items.tiers;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public class TierCuttingTool implements IItemTier {

	@Override
	public float getAttackDamage() {
		return 9.0f;
	}

	@Override
	public float getEfficiency() {
		return 0;
	}

	@Override
	public int getEnchantability() {
		return 0;
	}

	@Override
	public int getHarvestLevel() {
		return 0;
	}

	@Override
	public int getMaxUses() {
		return 16;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return null;
	}

}
