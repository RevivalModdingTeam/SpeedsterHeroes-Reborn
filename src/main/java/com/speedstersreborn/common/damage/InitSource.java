package com.speedstersreborn.common.damage;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class InitSource extends DamageSource {

    private String message;
    private boolean blockable;

    public InitSource(String name, boolean blockable) {
        super(name);
        this.message = "damagesource.speedster." + name;
        this.blockable = blockable;
    }

    public InitSource(String name) {
        this(name, false);
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase entity) {
        return new TextComponentTranslation(message, entity.getName());
    }

    @Override
    public boolean isUnblockable() {
        return !blockable;
    }
}
