package com.zlp.dairy.design.bridge.test;

import com.zlp.dairy.design.bridge.Enchantment;
import com.zlp.dairy.design.bridge.Weapon;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public abstract class WeaponTest {

    protected final void testBasicWeaponActions(final Weapon weapon){
        assertNotNull(weapon);
        Enchantment enchantment = weapon.getEnchantment();
        assertNotNull(enchantment);
        assertNotNull(weapon.getEnchantment());

        weapon.swing();
        verify(enchantment).apply();
        verifyNoMoreInteractions(enchantment);

        weapon.wield();
        verify(enchantment).onActivate();
        verifyNoMoreInteractions(enchantment);

        weapon.unWield();
        verify(enchantment).onDeactivate();
        verifyNoMoreInteractions(enchantment);
    }
}
