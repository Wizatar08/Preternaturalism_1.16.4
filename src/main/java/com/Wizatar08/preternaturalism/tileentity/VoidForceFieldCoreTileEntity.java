package com.inf1n1T388.preternaturalism.tileentity;

import com.inf1n1T388.preternaturalism.Preternaturalism;
import com.inf1n1T388.preternaturalism.init.BlockInit;
import com.inf1n1T388.preternaturalism.init.ModTileEntityTypes;
import net.minecraft.block.*;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class VoidForceFieldCoreTileEntity extends TileEntity implements ITickableTileEntity{
    private int blocksRemoved = 0;

    public VoidForceFieldCoreTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }


    public VoidForceFieldCoreTileEntity(){
        this(ModTileEntityTypes.VOID_FORCE_FIELD_CORE.get());
    }

    private void execute(){
        for(int x = 0; x < 7; x++){
            for(int y = 0; y < 7; y++){
                for(int z = 0; z < 7; z++){
                    detectBlock(pos.add(-3 + x, -3 + y, -3 + z));
                }
            }
        }
    }

    private void detectBlock(BlockPos position){
        assert world != null;
        if(this.world.getBlockState(position) == BlockInit.VOID_BLOCK.get().getDefaultState()){
            this.world.setBlockState(position, Blocks.AIR.getDefaultState());
            this.blocksRemoved++;
        }
    }

    @Override
    public void tick() {
        if(this.blocksRemoved >= 50){
            deactivate();
        }
        if(world.isBlockPowered(pos) && this.blocksRemoved < 50){
            execute();
        }
        Preternaturalism.LOGGER.info("Blocks removed: " + this.blocksRemoved);
    }

    private void deactivate(){
        this.world.setBlockState(pos, BlockInit.DEACTIVATED_VOID_FORCE_FIELD_CORE.get().getDefaultState());
    }
}
