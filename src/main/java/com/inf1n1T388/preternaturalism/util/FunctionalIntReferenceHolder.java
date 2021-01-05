package com.inf1n1T388.preternaturalism.util;

import net.minecraft.util.IntReferenceHolder;

import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

public class FunctionalIntReferenceHolder extends IntReferenceHolder {
    private final IntSupplier getter;
    private final IntConsumer setter;

    public FunctionalIntReferenceHolder(final IntSupplier getter, final IntConsumer setter){
        this.getter = getter;
        this.setter = setter;
    }

    @Override
    public int get() {
        return this.getter.getAsInt();
    }

    @Override
    public void set(int p_221494_1_) {
        this.setter.accept(p_221494_1_);
    }
}
