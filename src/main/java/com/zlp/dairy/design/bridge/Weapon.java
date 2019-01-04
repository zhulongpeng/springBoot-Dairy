package com.zlp.dairy.design.bridge;


public interface Weapon {

    void wield();

    void swing();

    void unWield();

    Enchantment getEnchantment();
}
