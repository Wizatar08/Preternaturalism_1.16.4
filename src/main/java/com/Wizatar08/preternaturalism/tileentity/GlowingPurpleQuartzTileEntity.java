package com.inf1n1T388.preternaturalism.tileentity;

import com.inf1n1T388.preternaturalism.init.ModTileEntityTypes;
import com.inf1n1T388.preternaturalism.particles.QuartzParticle;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.World;

public class GlowingPurpleQuartzTileEntity extends TileEntity implements ITickableTileEntity {
    public int x, y, z, tick;
    boolean initialized = false;
    public GlowingPurpleQuartzTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public GlowingPurpleQuartzTileEntity() {
        this(ModTileEntityTypes.GLOWING_PURPLE_QUARTZ.get());
    }

    @Override
    public void tick(){
        //Preternaturalism.LOGGER.info("TICK");
        if(!initialized){
            init();
        }
        tick -= 1;
        if(tick <= 0){
            execute();
            tick = (int) Math.round(Math.random() * 600) + 400;
        }

    }

    private void init(){
        initialized = true;
        x = this.pos.getX();
        y = this.pos.getY();
        z = this.pos.getZ();
        tick = (int) Math.round(Math.random() * 600) + 400;
    }

    private void execute(){
        for(int i = 0; i < 5; i++){
            World world = getWorld();
            double moveX = (Math.random() * 0.2/3) - 0.1/3;
            double moveY = (Math.random() * 0.2/3) - 0.1/3;
            double moveZ = (Math.random() * 0.2/3) - 0.1/3;
            //world.addParticle(new QuartzParticle.ColouredParticleData(1.0f, 0.0f, 1.0f, 100.0f), false, x + 0.5, y + 0.5, z + 0.5, moveX, moveY, moveZ);
            }

    }
}
