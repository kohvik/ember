package Entities;

import Game.Main;

import java.awt.*;

public class Warrior extends Entity {

    public Warrior(int health, int speed, int position, boolean survived) {
        super(health, speed, position, survived);

    }

    public void Draw(Graphics2D graphics){
        if (position < 830) {
            graphics.setColor(new Color(44, 95, 33));
            graphics.fillOval(position,390,25,25);
            position += speed;
            }
        else if (survived) {
            survived = false;
            Main.lives--;
        }

    }

}
