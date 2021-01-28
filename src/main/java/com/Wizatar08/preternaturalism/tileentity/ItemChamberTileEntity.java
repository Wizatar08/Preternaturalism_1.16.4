package com.inf1n1T388.preternaturalism.tileentity;

import com.inf1n1T388.preternaturalism.Preternaturalism;
import com.inf1n1T388.preternaturalism.container.ItemChamberContainer;
import com.inf1n1T388.preternaturalism.init.ModTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.ComposterBlock;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IClearable;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;

public class ItemChamberTileEntity extends LockableLootTileEntity implements IClearable, INamedContainerProvider {
    protected NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

    public ItemChamberTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public ItemChamberTileEntity(){
        this(ModTileEntityTypes.ITEM_CHAMBER.get());
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.items);

    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        ItemStackHelper.saveAllItems(compound, this.items);
        return compound;
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    public void markDirty() {
        super.markDirty();
        this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.preternaturalism.item_chamber");
    }

    @Override
    public ITextComponent getDisplayName() {
        return this.getName();
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new ItemChamberContainer(id, player, this);
    }

    @Override
    public int getSizeInventory() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack stack : this.items){
            if(!stack.isEmpty()){
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.items.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.items, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.items, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemStack = this.items.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemStack) && ItemStack.areItemStackTagsEqual(stack, itemStack);
        this.items.set(index, stack);
        if(stack.getCount() > this.getInventoryStackLimit()){
            stack.setCount(this.getInventoryStackLimit());
        }

        if(!flag){
            this.markDirty();
        }
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        if(this.world.getTileEntity(pos) != this){
            return false;
        } else {
            return player.getDistanceSq((double) this.pos.getX() + 0.5d, (double) this.pos.getY() + 0.5d, (double) this.pos.getZ() + 0.5d) <= 64.0d;
        }
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return !stack.isDamaged();
    }

    @Override
    public void clear() {
        super.clear();
        this.items.clear();
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbt = new CompoundNBT();
        this.write(nbt);

        return new SUpdateTileEntityPacket(this.getPos(), 1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.read(this.getBlockState(), pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        this.read(state, tag);
    }
}
