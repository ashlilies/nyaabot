package com.meowbie.nyaabot.utils;

import java.awt.Color;
import java.util.Random;

public class ColorUtil {
    public static Color getRandomColor() {
        Random rand = new Random();

        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        return new Color(r, g, b);
    }
}
