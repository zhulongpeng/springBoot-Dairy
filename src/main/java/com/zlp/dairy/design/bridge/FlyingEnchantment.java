package com.zlp.dairy.design.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlyingEnchantment implements Enchantment {

    private static final Logger logger = LoggerFactory.getLogger(FlyingEnchantment.class);

    @Override
    public void onActivate() {
        logger.info("The item begins to glow faintly.");
    }

    @Override
    public void apply() {
        logger.info("The item flies and strikes the enemies finally returning to owner's hand.");
    }

    @Override
    public void onDeactivate() {
        logger.info("The item's glow fades.");
    }
}
