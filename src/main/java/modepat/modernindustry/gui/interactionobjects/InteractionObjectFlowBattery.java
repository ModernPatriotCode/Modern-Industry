package modepat.modernindustry.gui.interactionobjects;

import modepat.modernindustry.api.ReferenceModernIndustry;
import modepat.modernindustry.container.ContainerFlowBattery;
import modepat.modernindustry.tileentities.TileEntityFlowBattery;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IInteractionObject;

public class InteractionObjectFlowBattery implements IInteractionObject {

	private final TileEntityFlowBattery tileEntityFlowBattery;
	
	public InteractionObjectFlowBattery(TileEntityFlowBattery tileEntityFlowBattery) {
		this.tileEntityFlowBattery = tileEntityFlowBattery;
	}
	
	@Override
	public ITextComponent getCustomName() {
		return null;
	}

	@Override
	public ITextComponent getName() {
		return null;
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInv, EntityPlayer entityPlayer) {
		return new ContainerFlowBattery(playerInv, this.tileEntityFlowBattery);
	}

	@Override
	public String getGuiID() {
		return ReferenceModernIndustry.MODID + ReferenceModernIndustry.GUI_FLOW_BATTERY;
	}

}
