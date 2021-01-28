package com.inf1n1T388.preternaturalism.container;

import com.inf1n1T388.preternaturalism.init.BlockInit;
import com.inf1n1T388.preternaturalism.init.ModContainerTypes;
import com.inf1n1T388.preternaturalism.tileentity.ItemChamberTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import javax.annotation.Nullable;
import java.util.Objects;

public class ItemChamberContainer extends Container {
    public final ItemChamberTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    public ItemChamberContainer(final int windowId, final PlayerInventory playerInv, final ItemChamberTileEntity tileEntity){
        super(ModContainerTypes.ITEM_CHAMBER.get(), windowId);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

        this.addSlot(new Slot(tileEntity, 0, 81, 36));

        // Main inventory
        int startX = 8;
        int startY = 84;
        int slotSizePlus2 = 18;
        for(int row = 0; row < 3; row++){
            for(int column = 0; column < 9; column++){
                this.addSlot(new Slot(playerInv, 9 + (row * 9) + column, startX + (column * slotSizePlus2), startY + (row * slotSizePlus2)));
            }
        }

        // Hotbar
        int hotbarY = 142;
        for(int column = 0; column < 9; column++){
            this.addSlot(new Slot(playerInv, column, startX + (column * slotSizePlus2), hotbarY));
        }
    }

    public ItemChamberContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data){
        this(windowId, playerInv, getTileEntity(playerInv, data));
    }

    private static ItemChamberTileEntity getTileEntity(PlayerInventory playerInv, final PacketBuffer data){
        Objects.requireNonNull(playerInv, "playerInv cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        final TileEntity tileAtPos = playerInv.player.world.getTileEntity(data.readBlockPos());
        if(tileAtPos instanceof ItemChamberTileEntity){
            return (ItemChamberTileEntity) tileAtPos;
        }
        throw new IllegalStateException("Tile entity is not correct " + tileAtPos);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, BlockInit.ITEM_CHAMBER.get());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if(slot != null && slot.getHasStack()){
            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();
            if(index < 1){
                if(!this.mergeItemStack(itemStack1, 1, this.inventorySlots.size(), true)){
                    return ItemStack.EMPTY;
                }
            } else if(!this.mergeItemStack(itemStack1, 0, 1, false)){
                return ItemStack.EMPTY;
            }
            if(itemStack1.isEmpty()){
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemStack;
    }
}
