package modepat.modernindustry.core;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.energy.EnergyStorage;

public class EnergyStorageCore extends EnergyStorage{

	public EnergyStorageCore(int capacity)
    {
        super(capacity, capacity, capacity, 0);
    }

    public EnergyStorageCore(int capacity, int maxTransfer)
    {
        super(capacity, maxTransfer, maxTransfer, 0);
    }

    public EnergyStorageCore(int capacity, int maxReceive, int maxExtract)
    {
        super(capacity, maxReceive, maxExtract, 0);
    }

    public EnergyStorageCore(int capacity, int maxReceive, int maxExtract, int energy)
    {
        super(capacity, maxReceive, maxExtract, energy);
    }
    
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return super.receiveEnergy(maxReceive, simulate);
    }
    
    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
       return super.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored() {
        return super.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return super.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return super.canExtract();
    }

    @Override
    public boolean canReceive() {
        return super.canReceive();
    }
    
    public void readFromNBT(NBTTagCompound compound) {
    	this.energy = compound.getInt("Energy");
    	this.energy = compound.getInt("Capacity");
    	this.energy = compound.getInt("MaxReceive");
    	this.energy = compound.getInt("MaxExtract");
    }
    
    public void writeToNBT(NBTTagCompound compound) {
    	compound.setInt("Energy", this.energy);
    	compound.setInt("Capacity", this.capacity);
    	compound.setInt("MaxReceive", this.maxReceive);
    	compound.setInt("MaxExtract", this.maxExtract);
    }
    
    
}
