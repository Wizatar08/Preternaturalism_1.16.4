package com.Wizatar08.preternaturalism.tileentity;

import com.Wizatar08.preternaturalism.init.BlockInit;
import com.Wizatar08.preternaturalism.init.ModTileEntityTypes;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tags.BlockTags;

public class VoidTileEntity extends TileEntity implements ITickableTileEntity {
    private int seconds, updateBlockTime, tick;
    private boolean initialized = false;
    private BlockPos pos;

    public VoidTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public VoidTileEntity(){
        this(ModTileEntityTypes.VOID_BLOCK.get());
    }



    @Override
    public void tick() {
        if(!initialized){
            init();
        }

        tick++;
        if(tick >= updateBlockTime){
            execute();
        }
    }

    private void init(){
        tick = 0;
        seconds = 20;
        updateBlockTime = (int) ((120 + (Math.round(Math.random() * 180))) * seconds);
        initialized = true;
    }

    private void execute(){
        pos = this.getPos();
        int i = 0;
        run();

    }

    private BlockPos getTarget(){
        BlockPos target = null;
        int rand = (int) (Math.floor(Math.random() * 6));
        if(rand == 0){
            target = pos.add(1,0,0);
        } else if(rand == 1){
            target = pos.add(0,1,0);
        } else if(rand == 2){
            target = pos.add(0,0,1);
        } else if(rand == 3){
            target = pos.add(-1,0,0);
        } else if(rand == 4){
            target = pos.add(0,-1,0);
        } else if(rand == 5){
            target = pos.add(0,0,-1);
        }
        return target;
    }

    private void run(){
        BlockPos blockPos = getTarget();
        if((isValidBlock(blockPos)) && (!isAtHeightLimit(blockPos, false)) && (!isAtHeightLimit(blockPos, true))){
            world.setBlockState(blockPos, BlockInit.VOID_BLOCK.get().getDefaultState());
            tick = 0;
            updateBlockTime = (int) ((120 + (Math.round(Math.random() * 180))) * seconds);
        } else {
            blockPos = getTarget();
            if((isValidBlock(blockPos)) && (!isAtHeightLimit(blockPos, false)) && (!isAtHeightLimit(blockPos, true))){
                world.setBlockState(blockPos, BlockInit.VOID_BLOCK.get().getDefaultState());
                tick = 0;
                updateBlockTime = (int) ((30 + (Math.round(Math.random() * 60)))* seconds);
            } else {
                blockPos = getTarget();
                if((isValidBlock(blockPos)) && (!isAtHeightLimit(blockPos, false)) && (!isAtHeightLimit(blockPos, true))){
                    world.setBlockState(blockPos, BlockInit.VOID_BLOCK.get().getDefaultState());
                    tick = 0;
                    updateBlockTime = (int) ((120 + (Math.round(Math.random() * 180))) * seconds);
                } else {
                    blockPos = getTarget();
                    if((isValidBlock(blockPos)) && (!isAtHeightLimit(blockPos, false)) && (!isAtHeightLimit(blockPos, true))){
                        world.setBlockState(blockPos, BlockInit.VOID_BLOCK.get().getDefaultState());
                        tick = 0;
                        updateBlockTime = (int) ((120 + (Math.round(Math.random() * 180))) * seconds);
                    } else {
                        blockPos = getTarget();
                        if((isValidBlock(blockPos)) && (!isAtHeightLimit(blockPos, false)) && (!isAtHeightLimit(blockPos, true))){
                            world.setBlockState(blockPos, BlockInit.VOID_BLOCK.get().getDefaultState());
                            tick = 0;
                            updateBlockTime = (int) ((120 + (Math.round(Math.random() * 180))) * seconds);
                        } else {
                            blockPos = getTarget();
                            if((isValidBlock(blockPos)) && (!isAtHeightLimit(blockPos, false)) && (!isAtHeightLimit(blockPos, true))){
                                world.setBlockState(blockPos, BlockInit.VOID_BLOCK.get().getDefaultState());
                                tick = 0;
                                updateBlockTime = (int) ((120 + (Math.round(Math.random() * 180))) * seconds);
                            } else {
                                blockPos = getTarget();
                                if((isValidBlock(blockPos)) && (!isAtHeightLimit(blockPos, false)) && (!isAtHeightLimit(blockPos, true))){
                                    world.setBlockState(blockPos, BlockInit.VOID_BLOCK.get().getDefaultState());
                                    tick = 0;
                                    updateBlockTime = (int) ((120 + (Math.round(Math.random() * 180))) * seconds);
                                } else {
                                    blockPos = getTarget();
                                    if((isValidBlock(blockPos)) && (!isAtHeightLimit(blockPos, false)) && (!isAtHeightLimit(blockPos, true))){
                                        world.setBlockState(blockPos, BlockInit.VOID_BLOCK.get().getDefaultState());
                                        tick = 0;
                                        updateBlockTime = (int) ((120 + (Math.round(Math.random() * 180))) * seconds);
                                    } else {
                                        blockPos = getTarget();
                                        if((isValidBlock(blockPos)) && (!isAtHeightLimit(blockPos, false)) && (!isAtHeightLimit(blockPos, true))){
                                            world.setBlockState(blockPos, BlockInit.VOID_BLOCK.get().getDefaultState());
                                            tick = 0;
                                            updateBlockTime = (int) ((120 + (Math.round(Math.random() * 180))) * seconds);
                                        } else {
                                            blockPos = getTarget();
                                            if((isValidBlock(blockPos)) && (!isAtHeightLimit(blockPos, false)) && (!isAtHeightLimit(blockPos, true))){
                                                world.setBlockState(blockPos, BlockInit.VOID_BLOCK.get().getDefaultState());
                                                tick = 0;
                                                updateBlockTime = (int) ((120 + (Math.round(Math.random() * 180))) * seconds);
                                            } else {
                                                blockPos = getTarget();
                                                if((isValidBlock(blockPos)) && (!isAtHeightLimit(blockPos, false)) && (!isAtHeightLimit(blockPos, true))){
                                                    world.setBlockState(blockPos, BlockInit.VOID_BLOCK.get().getDefaultState());
                                                    tick = 0;
                                                    updateBlockTime = (int) (((120 + (Math.round(Math.random() * 180))) * seconds));
                                                } else {
                                                    blockPos = getTarget();

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isValidBlock(BlockPos position){
        if(this.world.getBlockState(position) == Blocks.AIR.getDefaultState() ||
            this.world.getBlockState(position) == BlockTags.BEEHIVES.getAllElements() ||
            this.world.getBlockState(position) == BlockTags.BEDS.getAllElements() ||
            this.world.getBlockState(position) == BlockTags.BUTTONS.getAllElements() ||
            this.world.getBlockState(position) == BlockTags.CARPETS.getAllElements() ||
            this.world.getBlockState(position) == BlockTags.CORAL_PLANTS.getAllElements() ||
            this.world.getBlockState(position) == BlockTags.CORALS.getAllElements() ||
            this.world.getBlockState(position) == BlockTags.CROPS.getAllElements() ||
            this.world.getBlockState(position) == BlockTags.DOORS.getAllElements() ||
            this.world.getBlockState(position) == BlockTags.PORTALS.getAllElements() ||
            this.world.getBlockState(position) == BlockTags.FENCES.getAllElements() ||
            this.world.getBlockState(position) == BlockTags.FLOWER_POTS.getAllElements() ||
            this.world.getBlockState(position) == BlockTags.FLOWERS.getAllElements() ||
            this.world.getBlockState(position) == BlockTags.LEAVES.getAllElements() ||
            this.world.getBlockState(position) == BlockTags.RAILS.getAllElements() ||
            this.world.getBlockState(position) == BlockTags.SAPLINGS.getAllElements() ||
            this.world.getBlockState(position) == BlockTags.SIGNS.getAllElements() ||
            this.world.getBlockState(position) == BlockTags.TRAPDOORS.getAllElements() ||
            this.world.getBlockState(position) == BlockTags.UNDERWATER_BONEMEALS.getAllElements()){
            return true;
        }
        return false;
    }

    private boolean isAtHeightLimit(BlockPos position, boolean flip){
        int i = 1;
        if(flip == true){
            i = -1;
        }
        if((this.world.getBlockState(position.add(0,i * -1,0)) == BlockInit.VOID_BLOCK.get().getDefaultState()) &&
            this.world.getBlockState(position.add(0, i * -2, 0)) == BlockInit.VOID_BLOCK.get().getDefaultState() &&
            this.world.getBlockState(position.add(0, i * -3, 0)) == BlockInit.VOID_BLOCK.get().getDefaultState() &&
            this.world.getBlockState(position.add(0, i * -4, 0)) == BlockInit.VOID_BLOCK.get().getDefaultState() &&
            this.world.getBlockState(position.add(0, i * -5, 0)) == BlockInit.VOID_BLOCK.get().getDefaultState() &&
            this.world.getBlockState(position.add(0, i * -6, 0)) == BlockInit.VOID_BLOCK.get().getDefaultState() &&
            this.world.getBlockState(position.add(0, i * -7, 0)) == BlockInit.VOID_BLOCK.get().getDefaultState() &&
            this.world.getBlockState(position.add(0, i * -8, 0)) == BlockInit.VOID_BLOCK.get().getDefaultState() &&
            this.world.getBlockState(position.add(0, i * -9, 0)) == BlockInit.VOID_BLOCK.get().getDefaultState()){
            return true;
        }
        return false;
    }
}
