package com.Wizatar08.preternaturalism.tileentity;

import com.Wizatar08.preternaturalism.init.BlockInit;
import com.Wizatar08.preternaturalism.init.ModTileEntityTypes;
import com.Wizatar08.preternaturalism.objects.blocks.PulsingLamp;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class PulsingLampTileEntity extends TileEntity implements ITickableTileEntity {
    private static final RedstoneParticleData PARTICLE_1 = new RedstoneParticleData(0F, 0.2F, 0.8F, 1.0F);
    private static final RedstoneParticleData PARTICLE_2 = new RedstoneParticleData(0F, 0.4F, 0.6F, 1.0F);
    private static final RedstoneParticleData PARTICLE_3 = new RedstoneParticleData(0F, 0.6F, 0.45F, 1.0F);
    private static final RedstoneParticleData PARTICLE_4 = new RedstoneParticleData(0F, 0.75F, 0.3F, 1.0F);
    private static final RedstoneParticleData PARTICLE_5 = new RedstoneParticleData(0F, 0.5F, 0.15F, 1.0F);
    private static final RedstoneParticleData PARTICLE_6 = new RedstoneParticleData(0F, 0.25F, 0F, 1.0F);
    private static final RedstoneParticleData PARTICLE_7 = new RedstoneParticleData(0F, 0F, 0F, 1.0F);

    private int ticks;

    public PulsingLampTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        ticks = 0;
    }

    public PulsingLampTileEntity() {
        this(ModTileEntityTypes.PULSING_LAMP.get());
    }

    @Override
    public void tick() {
        assert world != null;
        switch (this.getBlockState().get(PulsingLamp.STAGE)) {
            case 0:
                for (int i = 0; i < 16; i++)
                    world.addParticle(PARTICLE_1, pos.getX() + 0.5f - 1 + (world.getRandom().nextFloat() * 2), pos.getY() + 0.5f - 1 + (world.getRandom().nextFloat() * 2), pos.getZ() + 0.5f - 1 + (world.getRandom().nextFloat() * 2), 1f, 1f, 1f);
                break;
            case 1:
                for (int i = 0; i < 32; i++)
                    world.addParticle(PARTICLE_1, pos.getX() + 0.5f - 1.5 + (world.getRandom().nextFloat() * 3), pos.getY() + 0.5f - 1.5 + (world.getRandom().nextFloat() * 3), pos.getZ() + 0.5f - 1.5 + (world.getRandom().nextFloat() * 3), 1.3f, 1.3f, 1.3f);
                break;
            case 2:
                for (int i = 0; i < 48; i++)
                    world.addParticle(PARTICLE_1, pos.getX() + 0.5f - 2 + (world.getRandom().nextFloat() * 4), pos.getY() + 0.5f - 0.75 + (world.getRandom().nextFloat() * 4), pos.getZ() + 0.5f - 0.75 + (world.getRandom().nextFloat() * 4), 1.3f, 1.3f, 1.3f);
                for (int i = 0; i < 24; i++)
                    world.addParticle(PARTICLE_2, pos.getX() + 0.5f - 1 + (world.getRandom().nextFloat() * 2), pos.getY() + 0.5f - 1 + (world.getRandom().nextFloat() * 2), pos.getZ() + 0.5f - 1 + (world.getRandom().nextFloat() * 2), 1f, 1f, 1f);
                break;
            case 3:
                for (int i = 0; i < 64; i++)
                    world.addParticle(PARTICLE_2, pos.getX() + 0.5f - 2 + (world.getRandom().nextFloat() * 4), pos.getY() + 0.5f - 2 + (world.getRandom().nextFloat() * 4), pos.getZ() + 0.5f - 2 + (world.getRandom().nextFloat() * 4), 1.3f, 1.3f, 1.3f);
                for (int i = 0; i < 32; i++)
                    world.addParticle(PARTICLE_3, pos.getX() + 0.5f - 1 + (world.getRandom().nextFloat() * 2), pos.getY() + 0.5f - 1 + (world.getRandom().nextFloat() * 2), pos.getZ() + 0.5f - 1 + (world.getRandom().nextFloat() * 2), 1f, 1f, 1f);
                break;
            case 4:
                for (int i = 0; i < 80; i++)
                    world.addParticle(PARTICLE_3, pos.getX() + 0.5f - 3 + (world.getRandom().nextFloat() * 6), pos.getY() + 0.5f - 3 + (world.getRandom().nextFloat() * 6), pos.getZ() + 0.5f - 3 + (world.getRandom().nextFloat() * 6), 1.5f, 1.5f, 1.5f);
                for (int i = 0; i < 48; i++)
                    world.addParticle(PARTICLE_4, pos.getX() + 0.5f - 1.5 + (world.getRandom().nextFloat() * 3), pos.getY() + 0.5f - 1.5 + (world.getRandom().nextFloat() * 3), pos.getZ() + 0.5f - 1.5 + (world.getRandom().nextFloat() * 3), 1f, 1f, 1f);
                break;
            case 5:
                for (int i = 0; i < 96; i++)
                    world.addParticle(PARTICLE_4, pos.getX() + 0.5f - 3 + (world.getRandom().nextFloat() * 6), pos.getY() + 0.5f - 3 + (world.getRandom().nextFloat() * 6), pos.getZ() + 0.5f - 3 + (world.getRandom().nextFloat() * 6), 1.5f, 1.5f, 1.5f);
                for (int i = 0; i < 64; i++)
                    world.addParticle(PARTICLE_5, pos.getX() + 0.5f - 1.5 + (world.getRandom().nextFloat() * 3), pos.getY() + 0.5f - 1.5 + (world.getRandom().nextFloat() * 3), pos.getZ() + 0.5f - 1.5 + (world.getRandom().nextFloat() * 3), 1f, 1f, 1f);
                break;
            case 6:
                for (int i = 0; i < 128; i++)
                    world.addParticle(PARTICLE_4, pos.getX() + 0.5f - 3 + (world.getRandom().nextFloat() * 6), pos.getY() + 0.5f - 3 + (world.getRandom().nextFloat() * 6), pos.getZ() + 0.5f - 3 + (world.getRandom().nextFloat() * 6), 1.5f, 1.5f, 1.5f);
                for (int i = 0; i < 72; i++)
                    world.addParticle(PARTICLE_5, pos.getX() + 0.5f - 1.5 + (world.getRandom().nextFloat() * 3), pos.getY() + 0.5f - 1.5 + (world.getRandom().nextFloat() * 3), pos.getZ() + 0.5f - 1.5 + (world.getRandom().nextFloat() * 3), 1f, 1f, 1f);
                for (int i = 0; i < 32; i++)
                    world.addParticle(PARTICLE_6, pos.getX() + 0.5f - 1.5 + (world.getRandom().nextFloat() * 3), pos.getY() + 0.5f - 1.5 + (world.getRandom().nextFloat() * 3), pos.getZ() + 0.5f - 1.5 + (world.getRandom().nextFloat() * 3), 1f, 1f, 1f);
                break;
            case 7:
                for (int i = 0; i < 128; i++)
                    world.addParticle(PARTICLE_5, pos.getX() + 0.5f - 3 + (world.getRandom().nextFloat() * 6), pos.getY() + 0.5f - 3 + (world.getRandom().nextFloat() * 6), pos.getZ() + 0.5f - 3 + (world.getRandom().nextFloat() * 6), 1f, 1f, 1f);
                for (int i = 0; i < 72; i++)
                    world.addParticle(PARTICLE_6, pos.getX() + 0.5f - 2 + (world.getRandom().nextFloat() * 4), pos.getY() + 0.5f - 2 + (world.getRandom().nextFloat() * 4), pos.getZ() + 0.5f - 2 + (world.getRandom().nextFloat() * 4), 1.5f, 1.5f, 1.5f);
                for (int i = 0; i < 48; i++)
                    world.addParticle(PARTICLE_7, pos.getX() + 0.5f - 1.5 + (world.getRandom().nextFloat() * 3), pos.getY() + 0.5f - 1.5 + (world.getRandom().nextFloat() * 3), pos.getZ() + 0.5f - 1.5 + (world.getRandom().nextFloat() * 3), 0.6f, 0.6f, 0.6f);
                break;
            case 8:
                for (int i = 0; i < 144; i++)
                    world.addParticle(PARTICLE_6, pos.getX() + 0.5f - 3 + (world.getRandom().nextFloat() * 6), pos.getY() + 0.5f - 3 + (world.getRandom().nextFloat() * 6), pos.getZ() + 0.5f - 3 + (world.getRandom().nextFloat() * 6), 1.5f, 1.5f, 1.5f);
                for (int i = 0; i < 72; i++)
                    world.addParticle(PARTICLE_7, pos.getX() + 0.5f - 2 + (world.getRandom().nextFloat() * 4), pos.getY() + 0.5f - 2 + (world.getRandom().nextFloat() * 4), pos.getZ() + 0.5f - 2 + (world.getRandom().nextFloat() * 4), 2.2f, 2.2f, 2.2f);
                break;
            case 9:
                for (int i = 0; i < 160; i++)
                    world.addParticle(PARTICLE_7, pos.getX() + 0.5f - 1.5 + (world.getRandom().nextFloat() * 3), pos.getY() + 0.5f - 1.5 + (world.getRandom().nextFloat() * 3), pos.getZ() + 0.5f - 1.5 + (world.getRandom().nextFloat() * 3), 2.5f, 2.5f, 2.5f);
                break;
            case 10:
                for (int i = 0; i < 184; i++)
                    world.addParticle(PARTICLE_7, pos.getX() + 0.5f - 0.75 + (world.getRandom().nextFloat() * 1.5), pos.getY() + 0.5f - 0.75 + (world.getRandom().nextFloat() * 1.5), pos.getZ() + 0.5f - 0.75 + (world.getRandom().nextFloat() * 1.5), 3.5f, 3.5f, 3.5f);
                break;
        }

        if (this.getBlockState().get(PulsingLamp.IGNITED)) {
            ticks++;
            if (ticks >= 30) {
                if (this.getBlockState().get(PulsingLamp.STAGE) < 10) {
                    world.setBlockState(pos, this.getBlockState().with(PulsingLamp.STAGE, this.getBlockState().get(PulsingLamp.STAGE) + 1));
                    ticks = 0;
                } else {
                    if (world.getBlockState(pos.up()).getBlock() == BlockInit.VOID_BLOCK.get()) {
                        world.setBlockState(pos, BlockInit.ABYSS_OF_BINDING.get().getDefaultState());
                        world.setBlockState(pos.up(), Blocks.AIR.getDefaultState());
                    } else {
                        world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 4f, Explosion.Mode.BREAK);
                        world.setBlockState(pos, BlockInit.PULSING_LAMP.get().getDefaultState());
                    }
                }
            }
        } else {
            ticks = 0;
        }
    }
}
