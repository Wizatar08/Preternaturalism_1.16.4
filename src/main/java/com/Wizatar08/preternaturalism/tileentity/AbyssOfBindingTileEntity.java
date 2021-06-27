package com.Wizatar08.preternaturalism.tileentity;

import com.Wizatar08.preternaturalism.init.ModTileEntityTypes;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class AbyssOfBindingTileEntity extends TileEntity implements ITickableTileEntity {
    private int yDisplacement;
    private static final RedstoneParticleData PARTICLE = new RedstoneParticleData(0.15F, 1.0F, 0.15F, 1.0F);

    public AbyssOfBindingTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        yDisplacement = 0;
    }

    public AbyssOfBindingTileEntity() {
        this(ModTileEntityTypes.ABYSS_OF_BINDING.get());
    }


    @Override
    public void tick() {
        assert world != null;
        for (float j = 0; j < 64; j++) {
            world.addParticle(PARTICLE, true, this.pos.getX() + 0.5, this.pos.getY() + 1 + ((j - 32) / 12), this.pos.getZ() + 0.5, 0.5, 1024, 0.5);
        }
        for (int i = 0; i < 8; i++) {
            world.addParticle(PARTICLE, true, this.pos.getX() + (Math.random() * 4) - 2, this.pos.getY() + (Math.random() * 4) - 2, this.pos.getZ() + (Math.random() * 4) - 2, 3, 3, 3);
        }
    }
}
