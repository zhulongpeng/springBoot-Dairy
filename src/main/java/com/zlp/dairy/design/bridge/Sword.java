package com.zlp.dairy.design.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sword implements Weapon {

    private static final Logger logger = LoggerFactory.getLogger(Sword.class);

    private final Enchantment enchantment;

    public Sword(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    @Override
    public void wield() {
        enchantment.onActivate();
    }

    @Override
    public void swing() {
        enchantment.apply();
    }

    @Override
    public void unWield() {
        enchantment.onDeactivate();
    }

    @Override
    public Enchantment getEnchantment() {
        return enchantment;
    }
}
