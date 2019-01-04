package com.zlp.dairy.design.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hammer implements Weapon{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final Enchantment enchantment;

    public Hammer(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    @Override
    public void wield() {
        logger.info("wield");
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
