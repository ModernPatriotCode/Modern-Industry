package modepat.modernindustry.tileentities;

import javax.annotation.Nullable;

import modepat.modernindustry.api.ReferenceModernIndustry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityTeslaCoilFocus extends TileEntity implements ITickable {

	private boolean updateNeeded;
	private ItemStackHandler inventory = new ItemStackHandler(3);
	
	public TileEntityTeslaCoilFocus() {
		super(ReferenceModernIndustry.TILE_ENTITY_TYPE_TESLA_COIL_FOCUS);
		
	}

	@Override
	public void tick() {
		if(this.updateNeeded) {
			this.saveAndSync();
			this.updateNeeded = false;
		}
	}
	
	public int getStackCount() {
		int count = 0;
		for (int i = 0; i < this.inventory.getSlots(); i++) {
			if(!this.inventory.getStackInSlot(i).isEmpty()) {
				count++;
			}
		}
		return count;
	}
	
	@Override
	public NBTTagCompound write(NBTTagCompound compound) {
		compound.setTag(ReferenceModernIndustry.NBT_INVENTORY_TAG_KEY, this.inventory.serializeNBT());
		return super.write(compound);
	}
	
	@Override
	public void read(NBTTagCompound compound) {
		super.read(compound);
		this.inventory.deserializeNBT(compound.getCompound(ReferenceModernIndustry.NBT_INVENTORY_TAG_KEY));
		this.updateNeeded = true;
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, EnumFacing side) {
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return LazyOptional.of(() -> this.inventory).cast();
		return super.getCapability(cap, side);
	}
	
	public ItemStackHandler getInventory() {
		return this.inventory;
	}
	
	public boolean interact(EntityPlayer playerIn) {
		return this.world.getTileEntity(pos) != this ? false : playerIn.getDistanceSq(playerIn) <= 64.0D;
	}

	public void saveAndSync() {
		IBlockState state = this.world.getBlockState(this.pos);
		this.world.markBlockRangeForRenderUpdate(this.pos, this.pos);
		this.world.notifyBlockUpdate(pos, state, state, 3);
		this.markDirty();
	}

	public void clear() {
		for (int i = 0; i < this.inventory.getSlots(); i++) {
			this.inventory.setStackInSlot(i, ItemStack.EMPTY);
		}
	}

	public ItemStack decrStackSize(int index, int count) {
		if(index >= 0 && index < this.inventory.getSlots() && inventory.getStackInSlot(index)!= ItemStack.EMPTY && count > 0) {
			return inventory.getStackInSlot(index).split(count);
		}
		return ItemStack.EMPTY;
	}
	
	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.getPos(), 0, this.getUpdateTag());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		this.read(packet.getNbtCompound());
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		return this.write(new NBTTagCompound());
	}
	
	@Override
	public void handleUpdateTag(NBTTagCompound nbt) {
		this.read(nbt);
		this.saveAndSync();
	}
	
	public int getSizeInventory() {
		return this.inventory.getSlots();
	}


	public ItemStack getStackInSlot(int index) {
		return this.inventory.getStackInSlot(index);
	}


	public boolean isEmpty() {
		for (int i = 0; i < this.inventory.getSlots(); i++ ) {
			if(!this.inventory.getStackInSlot(i).isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isFull() {
		for (int i = 0; i < this.inventory.getSlots(); i++ ) {
			if(inventory.getStackInSlot(i).isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public boolean isUsableByPlayer(EntityPlayer arg0) {
		if (world.getTileEntity(pos) != this) {
			return false;
		} else {
			return true;
		}
	}

	
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = inventory.getStackInSlot(index);
		inventory.setStackInSlot(index, ItemStack.EMPTY);
		return stack;
	}



	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack stackAtIndex = inventory.getStackInSlot(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(stackAtIndex) && ItemStack.areItemsEqual(stack, stackAtIndex);
		if(stack.getCount() > getInventoryStackLimit()) {
			stack.setCount(getInventoryStackLimit());
		}
		if(index < this.inventory.getSlots() && !flag) {
			markDirty();
		}
	}
	
	public int getInventoryStackLimit() {
		return 64;
	}

}
