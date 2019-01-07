package com.zlp.dairy.design.bridge.test;

import com.zlp.dairy.design.bridge.FlyingEnchantment;
import com.zlp.dairy.design.bridge.Sword;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class SwordTest extends WeaponTest{

    @Test
    public void testSword(){
        final Sword sword = spy(new Sword(mock(FlyingEnchantment.class)));
        testBasicWeaponActions(sword);
    }
}
