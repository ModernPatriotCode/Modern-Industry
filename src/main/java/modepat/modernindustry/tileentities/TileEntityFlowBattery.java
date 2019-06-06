package modepat.modernindustry.tileentities;

import javax.annotation.Nullable;

import modepat.modernindustry.api.ReferenceModernIndustry;
import modepat.modernindustry.container.ContainerFlowBattery;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityFlowBattery extends TileEntityLockable implements ITickable, IEnergyStorage, ISidedInventory, IRecipeHolder {
	
	private ItemStackHandler inventory = new ItemStackHandler(2);
	private boolean extract, receive, updateNeeded;
	private int maxEnergy, stored;
	private ITextComponent CustomName;
	
	public TileEntityFlowBattery() {
		super(ReferenceModernIndustry.TILE_ENTITY_TYPE_FLOW_BATTERY);
		this.maxEnergy = 10000;
		this.extract = true;
		this.receive = true;
	}
	
	public double getPercentageFull() {
		return (this.stored/this.maxEnergy)*100;
	}
	
	public double getFractionFull() {
		return this.stored/this.maxEnergy;
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, EnumFacing side) {
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return LazyOptional.of(() -> this.inventory).cast();
		return super.getCapability(cap, side);
	}
	
	@Override
	public void tick() {
		this.addEnergyToStored(10);	
		if(this.updateNeeded) {
			this.saveAndSync();
			this.updateNeeded = false;
		}
	}
	
	public boolean interact(EntityPlayer playerIn) {
		return this.world.getTileEntity(pos) != this ? false : playerIn.getDistanceSq(playerIn) <= 64.0D;
	}
	
	public ItemStackHandler getInventory() {
		return this.inventory;
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		return 0;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored() {
		return stored;
	}

	@Override
	public int getMaxEnergyStored() {
		return maxEnergy;
	}

	@Override
	public boolean canExtract() {
		return extract;
	}

	@Override
	public boolean canReceive() {
		return receive;
	}
	
	public void setEnergyStored(int stored) {
		this.stored = stored;
	}
	
	public boolean addEnergyToStored(int add) {
		if(this.maxEnergy > this.stored + add) {
			this.stored = this.stored + add;
			return true;
		} else {
			this.stored = this.maxEnergy;
		}
		return false;
	}

	public void saveAndSync() {
		IBlockState state = this.world.getBlockState(this.pos);
		this.world.markBlockRangeForRenderUpdate(this.pos, this.pos);
		this.world.notifyBlockUpdate(pos, state, state, 3);
		this.markDirty();
	}
	
	public void setMaxEnergyStored(int max_energy) {
		this.maxEnergy = max_energy;
	}
	
	public void setExtract(boolean can_extract) {
		this.extract = can_extract;
	}
	
	public void setReceive(boolean can_receive) {
		this.receive = can_receive;
	}

	public void setCustomName(@Nullable ITextComponent customName) {
		this.CustomName = customName;
	}
	
	@Override
	public NBTTagCompound write(NBTTagCompound compound) {
		compound.setBoolean(ReferenceModernIndustry.NBT_RECEIVE_BOOLEAN_KEY, this.receive);
		compound.setBoolean(ReferenceModernIndustry.NBT_EXTRACT_BOOLEAN_KEY, this.extract);
		compound.setInt(ReferenceModernIndustry.NBT_ENERGY_STORED_INT_KEY, this.stored);
		compound.setInt(ReferenceModernIndustry.NBT_MAX_ENERGY_INT_KEY, this.maxEnergy);
		compound.setTag(ReferenceModernIndustry.NBT_INVENTORY_TAG_KEY, this.inventory.serializeNBT());
		if (CustomName != null) {
			compound.setString("CustomName", ITextComponent.Serializer.toJson(CustomName));
		}
		return super.write(compound);
	}
	
	@Override
	public void read(NBTTagCompound compound) {
		super.read(compound);
		this.receive = compound.getBoolean(ReferenceModernIndustry.NBT_RECEIVE_BOOLEAN_KEY);
		this.extract = compound.getBoolean(ReferenceModernIndustry.NBT_EXTRACT_BOOLEAN_KEY);
		this.stored = compound.getInt(ReferenceModernIndustry.NBT_ENERGY_STORED_INT_KEY);
		this.maxEnergy = compound.getInt(ReferenceModernIndustry.NBT_MAX_ENERGY_INT_KEY);
		this.inventory.deserializeNBT(compound.getCompound(ReferenceModernIndustry.NBT_INVENTORY_TAG_KEY));
		if (compound.contains("CustomName", 8)) {
			CustomName = ITextComponent.Serializer.fromJson(compound.getString("CustomName"));
		}
		this.updateNeeded = true;
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
	}

	public String getUnlocalizedName() {
		return "block." + ReferenceModernIndustry.MODID + ".flow_battery";
	}

	@Override
	public void clear() {
		inventory.setStackInSlot(0, ItemStack.EMPTY);
		inventory.setStackInSlot(1, ItemStack.EMPTY);
	}

	@Override
	public void closeInventory(EntityPlayer arg0) {
		
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if(index >= 0 && index < 2 && inventory.getStackInSlot(index)!= ItemStack.EMPTY && count > 0) {
			return inventory.getStackInSlot(index).split(count);
		}
		return ItemStack.EMPTY;
	}

	@Override
	public int getField(int id) {
		switch (id) {
		case 0:
			return (int)(160*this.getFractionFull());
		case 1:
			return this.maxEnergy;
		case 2:
			return this.stored;
		}
		return 0;
	}

	@Override
	public int getFieldCount() {
		return 3;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public int getSizeInventory() {
		return 2;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return inventory.getStackInSlot(index);
	}

	@Override
	public boolean isEmpty() {
		for(int i = 0; i == 1; i++) {
			if(inventory.getStackInSlot(i) != ItemStack.EMPTY) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (index == 1) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		if (world.getTileEntity(pos) != this) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void openInventory(EntityPlayer player) {
		
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = inventory.getStackInSlot(index);
		inventory.setStackInSlot(index, ItemStack.EMPTY);
		return stack;
	}

	@Override
	public void setField(int id, int value) {
		
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack stackAtIndex = inventory.getStackInSlot(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(stackAtIndex) && ItemStack.areItemsEqual(stack, stackAtIndex);
		if(stack.getCount() > getInventoryStackLimit()) {
			stack.setCount(getInventoryStackLimit());
		}
		if(index == 0 && !flag) {
			markDirty();
		}
	}

	@Override
	public ITextComponent getCustomName() {
		return CustomName;
	}

	@Override
	public ITextComponent getName() {
		return (ITextComponent) (CustomName != null ? CustomName : new TextComponentTranslation("container.flow_battery"));
	}

	@Override
	public boolean hasCustomName() {
		return CustomName != null;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInv, EntityPlayer player) {
		return new ContainerFlowBattery(playerInv, this);
	}

	@Override
	public String getGuiID() {
		return ReferenceModernIndustry.MODID + ReferenceModernIndustry.GUI_FLOW_BATTERY;
	}

	@Override
	public IRecipe getRecipeUsed() {
		return null;
	}

	@Override
	public void setRecipeUsed(IRecipe recipe) {
		
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing facing) {
		return index == 1 && facing == EnumFacing.DOWN;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack stack, EnumFacing facing) {
		return isItemValidForSlot(index, stack) && facing == EnumFacing.UP;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing facing) {
		if(facing == EnumFacing.UP) {
			return new int[]{0};
		}
		if(facing == EnumFacing.DOWN) {
			return new int[]{1};
		}
		return null;
	}

}
