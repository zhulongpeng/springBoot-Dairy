package com.zlp.dairy.design.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static  final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        Sword enchantedSword = new Sword(new SoulEatingEnchantment());
        enchantedSword.wield();
        enchantedSword.swing();
        enchantedSword.unWield();

        logger.info("The valkyrie receives an enchanted hammer");
        Hammer hammer = new Hammer(new FlyingEnchantment());
        hammer.wield();
        hammer.unWield();
        hammer.swing();
    }


}
