package Entities;

import Game.Loader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Warrior extends Entity {

    static final BufferedImage sprite = Loader.loadAsset("/entities/warrior.png");
    public static int maxHP = 100;

    public Warrior(int health, int speed, int position, boolean survived) {
        super(health, speed,5, position, survived);
    }

    public void update(Graphics2D graphics){
        draw(graphics, sprite, maxHP);
    }

}
