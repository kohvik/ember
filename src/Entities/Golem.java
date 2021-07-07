package Entities;

import Game.Loader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Golem extends Entity {

    static final BufferedImage sprite = Loader.loadAsset("/golem.png");
    public static int maxHP = 250;

    public Golem(int health, int speed, int position, boolean survived) {
        super(health, speed,20, position, survived);
    }

    public void Update(Graphics2D graphics){
        Draw(graphics, sprite, maxHP);
    }

}
