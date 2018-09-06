package com.example.bugbusters.wifimapper;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AreaTest {
    Area area;

    @Before
    public void setUp() {
        area = new Area();
    }

    @Test
    public void getNumLocation() {
        System.out.println("Testing the getNumLocation() method and setMethod() in the area method");
        assertEquals(0, area.getNumLocation()); //testing the initial number of location which should be 0
        area.setNumLocation(4);
        assertEquals(4, area.getNumLocation());
    }

    @Test
    public void getId() {
        System.out.println("Testing the getId() method in the area class");
        area.setId("Testing ID");
        assertEquals("Testing ID", area.getId());
    }

    @Test
    public void updateArea() {
        System.out.println("Testing the updateArea method in the area class");
        area = new Area();
        area.updateArea(15);
        assertEquals(15, area.getWifiStrength());
        assertEquals(1, area.getNumLocation());
        area.updateArea(75);
        int answer = (75 + 15) / 2;
        assertEquals(2, area.getNumLocation());
        assertEquals(answer, area.getWifiStrength());
    }
}