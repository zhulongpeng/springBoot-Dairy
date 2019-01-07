package com.zlp.dairy.design.bridge.test;

import com.zlp.dairy.design.bridge.FlyingEnchantment;
import com.zlp.dairy.design.bridge.Hammer;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class HammerTest extends WeaponTest {

    @Test
    public void testHammer(){
        final Hammer hammer = spy(new Hammer(mock(FlyingEnchantment.class)));
        testBasicWeaponActions(hammer);
    }
}
