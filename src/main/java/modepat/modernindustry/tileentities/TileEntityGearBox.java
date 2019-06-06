package modepat.modernindustry.tileentities;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import modepat.modernindustry.api.ReferenceModernIndustry;
import modepat.modernindustry.blocks.BlockGearBox;
import modepat.modernindustry.blocks.BlockGearBox.EnumGearBox;
import modepat.modernindustry.container.ContainerGearBox;
import modepat.modernindustry.core.ModernUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
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

public class TileEntityGearBox extends TileEntity implements ITickable{

	private EnumGearBox enumGearBox;
	private ItemStackHandler inventory = new ItemStackHandler();
	private boolean updateNeeded, enumSearching;
	private final long INVALID_TIME = 0;
	private long lastTime = INVALID_TIME;
	private double lastAngularPosition;
	private float nextAngularPosition1;
	private double sineX;
	private double cosineX;
	
	public TileEntityGearBox() {
		super(ReferenceModernIndustry.TILE_ENTITY_TYPE_GEAR_BOX);
		this.enumGearBox = EnumGearBox.BASIC;
		this.enumSearching = true;
	}
	
	@Override
	public void tick() {
		if(enumSearching) {
			enumSearching = false;
			EnumGearBox enumGearBox = ((BlockGearBox)this.getWorld().getBlockState(this.getPos()).getBlock()).getEnumGearBox();
			this.enumGearBox = enumGearBox;
			if(this.enumGearBox.equals(EnumGearBox.BASIC)) this.inventory.setSize(1);
			if(this.enumGearBox.equals(EnumGearBox.ADVANCED)) this.inventory.setSize(2);
			if(this.enumGearBox.equals(EnumGearBox.REFINED)) this.inventory.setSize(3);
			this.updateNeeded = true;
		}
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
		compound.setInt(ReferenceModernIndustry.NBT_ENUM_ID_KEY, this.enumGearBox.getId());
		compound.setTag(ReferenceModernIndustry.NBT_INVENTORY_TAG_KEY, this.inventory.serializeNBT());
		return super.write(compound);
	}
	
	@Override
	public void read(NBTTagCompound compound) {
		super.read(compound);
		this.enumGearBox = EnumGearBox.getEnumFromID(compound.getInt(ReferenceModernIndustry.NBT_ENUM_ID_KEY));
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
	
	public String getUnlocalizedName() {
		return "block." + ReferenceModernIndustry.MODID + "gearbox_type_unknown";
	}


	public int getInventoryStackLimit() {
		return 1;
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
	
	public double getNextAngularPosition(double revsPerSecond) {
		long timeNow = System.nanoTime();
		if (lastTime == INVALID_TIME) {
			lastTime = timeNow;
			lastAngularPosition = 0.0;
		}
		final double DEGREES_PER_REV = 360.0;
		final double NANOSECONDS_PER_SECOND = 1e9;
		double nextAngularPosition = lastAngularPosition + (timeNow - lastTime) * revsPerSecond * DEGREES_PER_REV / NANOSECONDS_PER_SECOND;
		nextAngularPosition = nextAngularPosition % DEGREES_PER_REV;
		lastAngularPosition = nextAngularPosition;
		lastTime = timeNow;
		return nextAngularPosition;
	}
	
	public double getRevolvingSine(int scale) {
		if(this.sineX == 2*Math.PI) {
			this.sineX = 0;
		}
		this.sineX = this.sineX + 0.1/scale * Math.PI;
		return Math.sin(sineX);
	}
	
	public double getRevolvingSineThird(int scale, int third) {
		double newSineX;
		if(third == 1) {
			newSineX = (this.sineX + 0.1/scale * Math.PI) + 2/3*Math.PI;
		} else {
			newSineX = (this.sineX + 0.1/scale * Math.PI) + 4/3*Math.PI;
		}
		return Math.sin(newSineX)/2;
	}
	
	public double getRevolvingCosineThird(int scale, int third) {
		double newCosineX;
		if(third == 1) {
			newCosineX = (this.cosineX + 0.1/scale * Math.PI) + 2/3*Math.PI;
		} else {
			newCosineX = (this.cosineX + 0.1/scale * Math.PI) + 4/3*Math.PI;
		}
		return Math.cos(newCosineX)/2;
	}
	
	public double getRevolvingCosine(int scale) {
		if(this.cosineX == 2*Math.PI) {
			this.cosineX = 0;
		}
		this.cosineX = this.cosineX + 0.1/scale * Math.PI;
		return Math.cos(cosineX);
	}
	
	
	public float getNextAngularPosition1(float revsPerSecond1) {
		long timeNow = System.nanoTime();
		if (lastTime == INVALID_TIME) {
			lastTime = timeNow;
			lastAngularPosition = 0.0;
		}
		final double DEGREES_PER_REV = 360.0;
		final double NANOSECONDS_PER_SECOND = 1e9;
		double nextAngularPosition = lastAngularPosition + (timeNow - lastTime) * revsPerSecond1 * DEGREES_PER_REV / NANOSECONDS_PER_SECOND;
		nextAngularPosition = nextAngularPosition % DEGREES_PER_REV;
		lastAngularPosition = nextAngularPosition;
		lastTime = timeNow;
		return nextAngularPosition1;
	}


	public Container createContainer(InventoryPlayer playerInv, EntityPlayer player) {
		return new ContainerGearBox(playerInv, this);
	}


	
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing facing) {
		return facing == EnumFacing.DOWN;
	}

	
	public int[] getSlotsForFace(EnumFacing facing) {
		List<Integer> indexForSlots = new ArrayList<Integer>();
		for (int i = 0; i < this.inventory.getSlots(); i++) {
			indexForSlots.add(i);
		}
		return ModernUtil.convertIntegers(indexForSlots);
	}

	public EnumGearBox getEnumGearBox() {
		return this.enumGearBox;
	}

}
