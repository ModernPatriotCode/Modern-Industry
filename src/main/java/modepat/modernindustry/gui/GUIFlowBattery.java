package modepat.modernindustry.gui;

import modepat.modernindustry.api.ReferenceModernIndustry;
import modepat.modernindustry.container.ContainerFlowBattery;
import modepat.modernindustry.tileentities.TileEntityFlowBattery;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIFlowBattery extends GuiContainer {

	private static final ResourceLocation GUI_TEX = new ResourceLocation(ReferenceModernIndustry.MODID + ":textures/gui/container/flow_battery_gui.png");
	@SuppressWarnings("unused")
	private TileEntityFlowBattery tileEntityFlowBattery;
	
	public GUIFlowBattery(InventoryPlayer playerInv, TileEntityFlowBattery tileEntityFlowBattery) {
		super(new ContainerFlowBattery(playerInv, tileEntityFlowBattery));
		this.tileEntityFlowBattery = tileEntityFlowBattery;
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		super.render(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		String name;
		if (tileEntityFlowBattery.hasCustomName()) {
			name = tileEntityFlowBattery.getCustomName().toString();
		} else {
			name = I18n.format(this.tileEntityFlowBattery.getUnlocalizedName());
		}
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GUI_TEX);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, 176, 170);
		this.fontRenderer.drawStringWithShadow(name, this.guiLeft + 6, this.guiTop + 64, 16777215);
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GUI_TEX);
		this.drawTexturedModalRect(this.guiLeft+8, this.guiTop+9, 0, 170, 50, 22);
	}

}
