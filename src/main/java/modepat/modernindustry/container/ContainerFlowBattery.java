package modepat.modernindustry.container;

import modepat.modernindustry.tileentities.TileEntityFlowBattery;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerFlowBattery extends Container {
	
	private final TileEntityFlowBattery tileEntityFlowBattery;
	
	public ContainerFlowBattery(InventoryPlayer player, TileEntityFlowBattery tileEntityFlowBattery) {
		
		this.tileEntityFlowBattery = tileEntityFlowBattery;
		ItemStackHandler inventory = tileEntityFlowBattery.getInventory();
		
		// Flux Battery Inventory
		
		this.addSlot(new SlotItemHandler(inventory, 0, 85, 42) {
			@Override
			public void onSlotChanged() {
				tileEntityFlowBattery.saveAndSync();
			}
		});
		
		this.addSlot(new SlotItemHandler(inventory, 1, 146, 42){
			@Override
			public void onSlotChanged() {
				tileEntityFlowBattery.saveAndSync();
			}
		});
		
		//	Player Inventory
		
		for(int i = 0; i < 9; i++) {
			this.addSlot(new Slot(player, i, i*18+8, 146));
		}
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 3; j++) {
				this.addSlot(new Slot(player, i + j * 9 + 9, i * 18 + 8, j * 18 + 88));
			}
		}
		
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.tileEntityFlowBattery.interact(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack previous = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(index);
		
		if(slot!=null && slot.getHasStack()) {
			ItemStack current = slot.getStack();
			previous = current.copy();
			
			if(index < 2) {
				if (!this.mergeItemStack(current, 2, 29, true))
					return ItemStack.EMPTY;
			} else {
				if(!this.mergeItemStack(current, 0, 1, false))
					return ItemStack.EMPTY;
			}
			
			if(current.getCount() == 0) {
				
				slot.putStack(ItemStack.EMPTY);
				
			} else {
				
				slot.onSlotChanged();
				
			}
			
			if(current.getCount() == previous.getCount()) {
				return ItemStack.EMPTY;
			} 
			
			slot.onTake(playerIn, current);
			
		}
		return previous;
	}
	

}
