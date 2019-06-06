package modepat.modernindustry.tileentities.ter;

import org.lwjgl.opengl.GL11;
import modepat.modernindustry.tileentities.TileEntityGearBox;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RendererGearBox extends TileEntityRenderer<TileEntityGearBox> {
	
	@Override
	public void render(TileEntityGearBox tileEntityIn, double x, double y, double z, float partialTicks,
			int destroyStage) {
		World worldIn = tileEntityIn.getWorld();
		BlockPos posB = tileEntityIn.getPos();
		TileEntityGearBox bgd = (TileEntityGearBox)worldIn.getTileEntity(posB);
		if(!tileEntityIn.isEmpty()) {
			switch(tileEntityIn.getEnumGearBox()) {
			case BASIC:
				ItemStack itemstack0 = bgd.getInventory().getStackInSlot(0);
				if(itemstack0!=null){
					double angularPositionInDegrees = bgd.getNextAngularPosition(0.07);
					EntityItem entItem = new EntityItem(bgd.getWorld(), x, y, z, bgd.getStackInSlot(0));
					GL11.glPushMatrix();
					GL11.glDepthMask(true); 
					entItem.hoverStart = 0.0F;
					GL11.glColor4f(0F, 0F, 0F, 0.35F);
					GL11.glTranslatef((float)x + 0.5F, (float)y + 0.7F , (float)z + 0.5F);
					GlStateManager.scaled(1.2, 1.2, 1.2);
					GlStateManager.rotatef((float)angularPositionInDegrees, 0, 1, 0);
					Minecraft.getInstance().getRenderManager().renderEntity(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
					GlStateManager.popMatrix();
				}
				break;
			case ADVANCED:
				ItemStack itemStack1 = bgd.getInventory().getStackInSlot(0);
				ItemStack itemStack2 = bgd.getInventory().getStackInSlot(1);
				if(!itemStack1.isEmpty() && itemStack2.isEmpty()){
					double angularPositionInDegrees = bgd.getNextAngularPosition(0.07);
					EntityItem entItem = new EntityItem(bgd.getWorld(), x, y, z, bgd.getStackInSlot(0));
					GL11.glPushMatrix();
					GL11.glDepthMask(true); 
					entItem.hoverStart = 0.0F;
					GL11.glColor4f(0F, 0F, 0F, 0.35F);
					GL11.glTranslatef((float)x + 0.5F, (float)y + 0.7F, (float)z + 0.5F);
					GlStateManager.scaled(1.2, 1.2, 1.2);
					GlStateManager.rotatef((float)angularPositionInDegrees, 0, 1, 0);
					Minecraft.getInstance().getRenderManager().renderEntity(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
					GlStateManager.popMatrix();
				}

				if(itemStack1.isEmpty() && !itemStack2.isEmpty()){
					double angularPositionInDegrees = bgd.getNextAngularPosition(0.07);
					EntityItem entItem = new EntityItem(bgd.getWorld(), x, y, z, bgd.getStackInSlot(1));
					GL11.glPushMatrix();
					GL11.glDepthMask(true); 
					entItem.hoverStart = 0.0F;
					GL11.glColor4f(0F, 0F, 0F, 0.35F);
					GL11.glTranslatef((float)x + 0.5F, (float)y + 0.7F , (float)z + 0.5F);
					GlStateManager.scaled(1.2, 1.2, 1.2);
					GlStateManager.rotatef((float)angularPositionInDegrees, 0, 1, 0);
					Minecraft.getInstance().getRenderManager().renderEntity(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
					GlStateManager.popMatrix();
				}
				
				if(!itemStack1.isEmpty() && !itemStack2.isEmpty()) {
					double angularPositionInDegrees = bgd.getNextAngularPosition(0.07);
					double revolvingSine = bgd.getRevolvingSine(40)*0.5;
					double revolvingCosine = bgd.getRevolvingCosine(40)*0.5;
					EntityItem entItem = new EntityItem(bgd.getWorld(), x, y, z, bgd.getStackInSlot(0));
					GL11.glPushMatrix();
					GL11.glDepthMask(true); 
					entItem.hoverStart = 0.0F;
					GL11.glColor4f(0F, 0F, 0F, 0.35F);
					GL11.glTranslatef((float)x + 0.5F + (float)revolvingSine, (float)y + 0.7F , (float)z + 0.5F + (float)revolvingCosine);
					GlStateManager.scaled(1.2, 1.2, 1.2);
					GlStateManager.rotatef((float)angularPositionInDegrees, 0, 1, 0);
					Minecraft.getInstance().getRenderManager().renderEntity(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
					GlStateManager.popMatrix();
					
					EntityItem entItem2 = new EntityItem(bgd.getWorld(), x, y, z, bgd.getStackInSlot(1));
					GL11.glPushMatrix();
					GL11.glDepthMask(true); 
					entItem2.hoverStart = 0.0F;
					GL11.glColor4f(0F, 0F, 0F, 0.35F);
					GL11.glTranslatef((float)x + 0.5F - (float)revolvingSine, (float)y + 0.7F , (float)z + 0.5F - (float)revolvingCosine);
					GlStateManager.scaled(1.2, 1.2, 1.2);
					GlStateManager.rotatef((float)angularPositionInDegrees, 0, 1, 0);
					Minecraft.getInstance().getRenderManager().renderEntity(entItem2, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
					GlStateManager.popMatrix();
				}
				
				break;
			case REFINED: 
				
				ItemStack itemStackA = bgd.getInventory().getStackInSlot(0);
				ItemStack itemStackB = bgd.getInventory().getStackInSlot(1);
				ItemStack itemStackC = bgd.getInventory().getStackInSlot(2);
				
				if(!itemStackA.isEmpty() && itemStackB.isEmpty() && itemStackC.isEmpty()){
					double angularPositionInDegrees = bgd.getNextAngularPosition(0.07);
					EntityItem entItem = new EntityItem(bgd.getWorld(), x, y, z, bgd.getStackInSlot(0));
					GL11.glPushMatrix();
					GL11.glDepthMask(true); 
					entItem.hoverStart = 0.0F;
					GL11.glColor4f(0F, 0F, 0F, 0.35F);
					GL11.glTranslatef((float)x + 0.5F, (float)y + 0.7F , (float)z + 0.5F);
					GlStateManager.scaled(1.2, 1.2, 1.2);
					GlStateManager.rotatef((float)angularPositionInDegrees, 0, 1, 0);
					Minecraft.getInstance().getRenderManager().renderEntity(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
					GlStateManager.popMatrix();
				}
				
				if(itemStackA.isEmpty() && !itemStackB.isEmpty() && itemStackC.isEmpty()){
					double angularPositionInDegrees = bgd.getNextAngularPosition(0.07);
					EntityItem entItem = new EntityItem(bgd.getWorld(), x, y, z, bgd.getStackInSlot(1));
					GL11.glPushMatrix();
					GL11.glDepthMask(true); 
					entItem.hoverStart = 0.0F;
					GL11.glColor4f(0F, 0F, 0F, 0.35F);
					GL11.glTranslatef((float)x + 0.5F, (float)y + 0.7F , (float)z + 0.5F);
					GlStateManager.scaled(1.2, 1.2, 1.2);
					GlStateManager.rotatef((float)angularPositionInDegrees, 0, 1, 0);
					Minecraft.getInstance().getRenderManager().renderEntity(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
					GlStateManager.popMatrix();
				}
				
				if(itemStackA.isEmpty() && itemStackB.isEmpty() && !itemStackC.isEmpty()){
					double angularPositionInDegrees = bgd.getNextAngularPosition(0.07);
					EntityItem entItem = new EntityItem(bgd.getWorld(), x, y, z, bgd.getStackInSlot(2));
					GL11.glPushMatrix();
					GL11.glDepthMask(true); 
					entItem.hoverStart = 0.0F;
					GL11.glColor4f(0F, 0F, 0F, 0.35F);
					GL11.glTranslatef((float)x + 0.5F, (float)y + 0.7F , (float)z + 0.5F);
					GlStateManager.scaled(1.2, 1.2, 1.2);
					GlStateManager.rotatef((float)angularPositionInDegrees, 0, 1, 0);
					Minecraft.getInstance().getRenderManager().renderEntity(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
					GlStateManager.popMatrix();
				}
				
				if(!itemStackA.isEmpty() && !itemStackB.isEmpty() && itemStackC.isEmpty()) {
					double angularPositionInDegrees = bgd.getNextAngularPosition(0.07);
					double revolvingSine = bgd.getRevolvingSine(40)*0.5;
					double revolvingCosine = bgd.getRevolvingCosine(40)*0.5;
					
					EntityItem entItem = new EntityItem(bgd.getWorld(), x, y, z, bgd.getStackInSlot(0));
					GL11.glPushMatrix();
					GL11.glDepthMask(true); 
					entItem.hoverStart = 0.0F;
					GL11.glColor4f(0F, 0F, 0F, 0.35F);
					GL11.glTranslatef((float)x + 0.5F + (float)revolvingSine, (float)y + 0.7F , (float)z + 0.5F + (float)revolvingCosine);
					GlStateManager.scaled(1.2, 1.2, 1.2);
					GlStateManager.rotatef((float)angularPositionInDegrees, 0, 1, 0);
					Minecraft.getInstance().getRenderManager().renderEntity(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
					GlStateManager.popMatrix();
					
					EntityItem entItem2 = new EntityItem(bgd.getWorld(), x, y, z, bgd.getStackInSlot(1));
					GL11.glPushMatrix();
					GL11.glDepthMask(true); 
					entItem2.hoverStart = 0.0F;
					GL11.glColor4f(0F, 0F, 0F, 0.35F);
					GL11.glTranslatef((float)x + 0.5F - (float)revolvingSine, (float)y + 0.7F , (float)z + 0.5F - (float)revolvingCosine);
					GlStateManager.scaled(1.2, 1.2, 1.2);
					GlStateManager.rotatef((float)angularPositionInDegrees, 0, 1, 0);
					Minecraft.getInstance().getRenderManager().renderEntity(entItem2, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
					GlStateManager.popMatrix();
				}
				
				if(!itemStackA.isEmpty() && itemStackB.isEmpty() && !itemStackC.isEmpty()) {
					double angularPositionInDegrees = bgd.getNextAngularPosition(0.07);
					double revolvingSine = bgd.getRevolvingSine(40)*0.5;
					double revolvingCosine = bgd.getRevolvingCosine(40)*0.5;
					EntityItem entItem = new EntityItem(bgd.getWorld(), x, y, z, bgd.getStackInSlot(0));
					GL11.glPushMatrix();
					GL11.glDepthMask(true); 
					entItem.hoverStart = 0.0F;
					GL11.glColor4f(0F, 0F, 0F, 0.35F);
					GL11.glTranslatef((float)x + 0.5F + (float)revolvingSine, (float)y + 0.7F , (float)z + 0.5F + (float)revolvingCosine);
					GlStateManager.scaled(1.2, 1.2, 1.2);
					GlStateManager.rotatef((float)angularPositionInDegrees, 0, 1, 0);
					Minecraft.getInstance().getRenderManager().renderEntity(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
					GlStateManager.popMatrix();
					
					EntityItem entItem2 = new EntityItem(bgd.getWorld(), x, y, z, bgd.getStackInSlot(2));
					GL11.glPushMatrix();
					GL11.glDepthMask(true); 
					entItem2.hoverStart = 0.0F;
					GL11.glColor4f(0F, 0F, 0F, 0.35F);
					GL11.glTranslatef((float)x + 0.5F - (float)revolvingSine, (float)y + 0.7F , (float)z + 0.5F - (float)revolvingCosine);
					GlStateManager.scaled(1.2, 1.2, 1.2);
					GlStateManager.rotatef((float)angularPositionInDegrees, 0, 1, 0);
					Minecraft.getInstance().getRenderManager().renderEntity(entItem2, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
					GlStateManager.popMatrix();
				}
				
				if(itemStackA.isEmpty() && !itemStackB.isEmpty() && !itemStackC.isEmpty()) {
					double angularPositionInDegrees = bgd.getNextAngularPosition(0.07);
					double revolvingSine = bgd.getRevolvingSine(40)*0.5;
					double revolvingCosine = bgd.getRevolvingCosine(40)*0.5;
					
					EntityItem entItem = new EntityItem(bgd.getWorld(), x, y, z, bgd.getStackInSlot(1));
					GL11.glPushMatrix();
					GL11.glDepthMask(true); 
					entItem.hoverStart = 0.0F;
					GL11.glColor4f(0F, 0F, 0F, 0.35F);
					GL11.glTranslatef((float)x + 0.5F + (float)revolvingSine, (float)y + 0.7F , (float)z + 0.5F + (float)revolvingCosine);
					GlStateManager.scaled(1.2, 1.2, 1.2);
					GlStateManager.rotatef((float)angularPositionInDegrees, 0, 1, 0);
					Minecraft.getInstance().getRenderManager().renderEntity(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
					GlStateManager.popMatrix();
					
					EntityItem entItem2 = new EntityItem(bgd.getWorld(), x, y, z, bgd.getStackInSlot(2));
					GL11.glPushMatrix();
					GL11.glDepthMask(true); 
					entItem2.hoverStart = 0.0F;
					GL11.glColor4f(0F, 0F, 0F, 0.35F);
					GL11.glTranslatef((float)x + 0.5F - (float)revolvingSine, (float)y + 0.7F , (float)z + 0.5F - (float)revolvingCosine);
					GlStateManager.scaled(1.2, 1.2, 1.2);
					GlStateManager.rotatef((float)angularPositionInDegrees, 0, 1, 0);
					Minecraft.getInstance().getRenderManager().renderEntity(entItem2, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
					GlStateManager.popMatrix();
				}
				
				if(!itemStackA.isEmpty() && !itemStackB.isEmpty() && !itemStackC.isEmpty()) {
					double angularPositionInDegrees = bgd.getNextAngularPosition(0.07);
					EntityItem entItem = new EntityItem(bgd.getWorld(), x, y, z, bgd.getStackInSlot(0));
					GL11.glPushMatrix();
					GL11.glDepthMask(true); 
					entItem.hoverStart = 0.0F;
					GL11.glColor4f(0F, 0F, 0F, 0.35F);
					GL11.glTranslatef((float)x + 0.5F + (float)bgd.getRevolvingSine(40), (float)y + 0.7F , (float)z + 0.5F + (float)bgd.getRevolvingCosine(40));
					GlStateManager.scaled(1.2, 1.2, 1.2);
					GlStateManager.rotatef((float)angularPositionInDegrees, 0, 1, 0);
					Minecraft.getInstance().getRenderManager().renderEntity(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
					GlStateManager.popMatrix();
					
					EntityItem entItem2 = new EntityItem(bgd.getWorld(), x, y, z, bgd.getStackInSlot(1));
					GL11.glPushMatrix();
					GL11.glDepthMask(true); 
					entItem2.hoverStart = 0.0F;
					GL11.glColor4f(0F, 0F, 0F, 0.35F);
					GL11.glTranslatef((float)x + 0.5F + (float)bgd.getRevolvingSineThird(40, 1), (float)y + 0.7F , (float)z + 0.5F + (float)bgd.getRevolvingCosineThird(40, 1));
					GlStateManager.scaled(1.2, 1.2, 1.2);
					GlStateManager.rotatef((float)angularPositionInDegrees, 0, 1, 0);
					Minecraft.getInstance().getRenderManager().renderEntity(entItem2, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
					GlStateManager.popMatrix();
					
					EntityItem entItem3 = new EntityItem(bgd.getWorld(), x, y, z, bgd.getStackInSlot(2));
					GL11.glPushMatrix();
					GL11.glDepthMask(true); 
					entItem3.hoverStart = 0.0F;
					GL11.glColor4f(0F, 0F, 0F, 0.35F);
					GL11.glTranslatef((float)x + 0.5F + (float)bgd.getRevolvingSineThird(40, 2), (float)y + 0.7F , (float)z + 0.5F + (float)bgd.getRevolvingCosineThird(40, 2));
					GlStateManager.scaled(1.2, 1.2, 1.2);
					GlStateManager.rotatef((float)angularPositionInDegrees, 0, 1, 0);
					Minecraft.getInstance().getRenderManager().renderEntity(entItem3, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F, false);
					GlStateManager.popMatrix();
					
				}
			}
		}
	}
//float floating = bgd.getNextAngularPosition1(0.5F)-180F;
//GlStateManager.translatef((floating)/180, 0.0f, (floating)/180);
}
