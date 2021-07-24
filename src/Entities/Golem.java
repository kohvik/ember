package Entities;

import Game.Loader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Golem extends Entity {

    static final BufferedImage sprite = Loader.loadAsset("/entities/golem.png");
    public static int maxHP = 250;

    public Golem(int health, int speed, int position, boolean survived) {
        super(health, speed,20, position, survived);
    }

    public void update(Graphics2D graphics){
        draw(graphics, sprite, maxHP);
    }

}
