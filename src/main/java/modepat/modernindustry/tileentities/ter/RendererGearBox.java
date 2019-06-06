package modepat.modernindustry.tileentities.ter;

import modepat.modernindustry.tileentities.TileEntityGearBox;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.item.EntityItem;

public class RendererGearBox extends TileEntityRenderer<TileEntityGearBox> {
	
	@Override
	public void render(TileEntityGearBox tileEntityIn, double x, double y, double z, float partialTicks,
			int destroyStage) {
		
		if(!tileEntityIn.isEmpty()) {
			
			switch(tileEntityIn.getStackCount()) {
				
			case 1:
				
				this.renderItem(tileEntityIn, 0, x, y, z, 0, 0);
				break;
			
			case 2:
				
				double revolvingSine = tileEntityIn.getRevolvingSine(40)/2;
				double revolvingCosine = tileEntityIn.getRevolvingCosine(40)/2;
				this.renderItem(tileEntityIn, 0, x, y, z, revolvingSine, revolvingCosine);
				this.renderItem(tileEntityIn, 1, x, y, z, -revolvingSine, -revolvingCosine);
				break;
			
			case 3:
				
				double revolvingSineIn = tileEntityIn.getRevolvingSine(40)/2;
				double revolvingCosineIn = tileEntityIn.getRevolvingCosine(40)/2;
				double revolvingSineThirdOne = tileEntityIn.getRevolvingSineThird(40, 1);
				double revolvingCosineThirdOne = tileEntityIn.getRevolvingCosineThird(40, 2);
				double revolvingSineThirdTwo = tileEntityIn.getRevolvingSineThird(40, 1);
				double revolvingCosineThirdTwo = tileEntityIn.getRevolvingCosineThird(40, 2);
				
				this.renderItem(tileEntityIn, 0, x, y, z, revolvingSineIn, revolvingCosineIn);
				this.renderItem(tileEntityIn, 1, x, y, z, revolvingSineThirdOne, revolvingCosineThirdOne);
				this.renderItem(tileEntityIn, 2, x, y, z, revolvingSineThirdTwo, revolvingCosineThirdTwo);
				break;
				
			}
		}
	}
	
	private void renderItem(TileEntityGearBox tileEntityIn, int index, double x, double y, double z, double offsetX, double offsetZ) {
		
		double angularPositionInDegreesA = tileEntityIn.getNextAngularPosition(0.07);
		EntityItem entItemA = new EntityItem(tileEntityIn.getWorld(), x, y, z, tileEntityIn.getStackInSlot(index));
		GlStateManager.pushMatrix();
		GlStateManager.depthMask(true); 
		entItemA.hoverStart = 0.0F;
		GlStateManager.color4f(0F, 0F, 0F, 0.35F);
		GlStateManager.translatef((float)x + 0.5F + (float)offsetX, (float)y + 0.7F , (float)z + 0.5F + (float)offsetZ);
		GlStateManager.scaled(1.2, 1.2, 1.2);
		GlStateManager.rotatef((float)angularPositionInDegreesA, 0, 1, 0);
		Minecraft.getInstance().getRenderManager().renderEntity(entItemA, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
		GlStateManager.popMatrix();
		
	}
	
//float floating = bgd.getNextAngularPosition1(0.5F)-180F;
//GlStateManager.translatef((floating)/180, 0.0f, (floating)/180);
	
}
