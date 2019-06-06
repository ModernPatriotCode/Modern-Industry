package modepat.modernindustry.items;

import modepat.modernindustry.api.ReferenceModernIndustry;
import modepat.modernindustry.core.objects.BlockCore;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMonkeyWrench extends Item {

	public static Properties monkey_wrench_properties = new Properties().maxStackSize(1).rarity(EnumRarity.UNCOMMON).setNoRepair().group(ReferenceModernIndustry.MODERNINDUSTRYGROUP);
	
	public ItemMonkeyWrench() {
		super (monkey_wrench_properties);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemUseContext context) {
		if (Minecraft.getInstance().player.isSneaking() == true) {
			if (Minecraft.getInstance().world.isRemote == true) {
				ResourceLocation blockClicked = context.getWorld().getBlockState(new BlockPos(context.getPos())).getBlock().getRegistryName();
				if (blockClicked != null) {
					if (blockClicked == BlockCore.flow_battery.getRegistryName()) {
						context.getWorld().destroyBlock(context.getPos(), true);
					}
				}
			}
		}
		return super.onItemUse(context);
	}

}
