package com.inf1n1T388.preternaturalism.container;

import com.inf1n1T388.preternaturalism.init.BlockInit;
import com.inf1n1T388.preternaturalism.init.ModContainerTypes;
import com.inf1n1T388.preternaturalism.tileentity.IronContainerTileEntity;
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

public class IronContainerContainer extends Container {
    public final IronContainerTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    public IronContainerContainer(final int windowId, final PlayerInventory playerInventory, final IronContainerTileEntity tileEntity) {
        super(ModContainerTypes.IRON_CONTAINER.get(), windowId);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

        // MAIN INVENTORY
        int startX = 8;
        int startY = 18;
        int slotSizePlus2 = 18;
        for(int row = 0; row < 4; row++){
            for(int column = 0; column < 9; column++){
                this.addSlot(new Slot(tileEntity, row * 9 + column, startX + (column * slotSizePlus2), startY + (row * slotSizePlus2)));
            }
        }

        // MAIN PLAYER INVENTORY
        int startPlayerInvY = startY * 5 + 12; // 102
        for(int row2 = 0; row2 < 3; row2++){
            for(int column = 0; column < 9; column++){
                this.addSlot(new Slot(playerInventory, 9 + (row2 * 9) + column, startX + (column * slotSizePlus2), startPlayerInvY + (row2 * slotSizePlus2)));
            }
        }

        // HOTBAR Y
        int hotBarY = startPlayerInvY + 58;
        for(int column = 0; column < 9; column++){
            this.addSlot(new Slot(playerInventory, column, startX + (column * slotSizePlus2), hotBarY));
        }
    }

    private static IronContainerTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data){
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null!!!!!!!!!!!");
        Objects.requireNonNull(data, "data cannot be null!!!!!!!!!!!!!!!!!!!");
        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
        if(tileAtPos instanceof IronContainerTileEntity){
            return (IronContainerTileEntity) tileAtPos;
        }
        throw new IllegalStateException("Tile entity is not correct: " + tileAtPos);
    }

    public IronContainerContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data){
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, BlockInit.IRON_CONTAINER.get());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if(slot != null && slot.getHasStack()){
            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();
            if(index < 36){
                if(this.mergeItemStack(itemStack1, 36, this.inventorySlots.size(), true)){
                    return ItemStack.EMPTY;
                }
            } else if(!this.mergeItemStack((itemStack1), 0 ,36, false)){
                return ItemStack.EMPTY;
            }

            if(itemStack.isEmpty()){
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemStack;
    }
}
