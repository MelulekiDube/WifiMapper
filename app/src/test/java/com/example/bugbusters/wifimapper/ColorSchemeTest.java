package com.example.bugbusters.wifimapper;

import android.graphics.Color;
import android.util.Pair;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ColorScheme.class)
public class ColorSchemeTest {
    private final int TRANSPARENCY = 100;
    private final int RED1 = Color.argb(TRANSPARENCY, 255, 0, 0);
    private final int ORANGE1 = Color.argb(TRANSPARENCY, 255, 127, 0);
    private final int YELLOW1 = Color.argb(TRANSPARENCY, 255, 255, 0);
    private final int GREEN_LIGHT1 = Color.argb(TRANSPARENCY, 127, 255, 0);
    private final int GREEN1 = Color.argb(TRANSPARENCY, 0, 255, 0);

    @Before
    public void setUp() {
        System.out.println("Testing the ColorScheme class methods");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void evaluateColor() {
        System.out.println("Testing the aevluating ");
        List<Pair<Integer, Integer>> inputStrength_expectedColor = new ArrayList<>();
        assertEquals(RED1, ColorScheme.evaluateColor(15));
        assertEquals(ORANGE1, ColorScheme.evaluateColor(40));
        assertEquals(YELLOW1, ColorScheme.evaluateColor(56));
        assertEquals(GREEN_LIGHT1, ColorScheme.evaluateColor(73));
        assertEquals(GREEN1, ColorScheme.evaluateColor(87));
    }
}