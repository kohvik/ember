package Entities;

import Game.Loader;
import Game.Main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Warrior extends Entity {

    private BufferedImage sprite = Loader.loadAsset("/warrior.png");

    public Warrior(int health, int speed, int position, boolean survived) {
        super(health, speed,5, position, survived);

    }

    public void Draw(Graphics2D graphics){
        if (health > 0) {
            if (position < 830) {
//                graphics.setColor(new Color(44, 95, 33));
//                graphics.fillOval(position,390,25,25);


                graphics.drawImage(sprite, position, 390, 25, 25, null);

                //drawing health bar
                graphics.setColor(Color.green);
                graphics.fillRect(position, 390 + 30, (health*25)/100, 5);
                position += speed;
            }
            else if (survived) {
                survived = false;
                Main.lives--;
                Main.livingEnemies--;
            }

        }
        else if (survived){

            Main.gold += value;
            survived = false;
            Main.livingEnemies--;

        }

    }

}
