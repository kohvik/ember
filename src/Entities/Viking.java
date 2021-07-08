package Entities;

import Game.Loader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Viking extends Entity {

    static final BufferedImage sprite = Loader.loadAsset("/entities/viking.png");
    public static int maxHP = 60;

    public Viking(int health, int speed, int position, boolean survived) {
        super(health, speed, 10, position, survived);
    }

    public void Update(Graphics2D graphics){
        Draw(graphics, sprite, maxHP);
    }
}
