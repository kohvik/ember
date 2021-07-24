package Game.Player;

import Game.Main;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Projectile {

    Point2D.Double position;
    BufferedImage sprite;
    double lifeSpan;
    double velX;
    double velY;
    int damage;

    public Projectile(Point2D.Double position, BufferedImage sprite, double lifeSpan, double velX, double velY, int damage) {
        this.position = position;
        this.sprite = sprite;
        this.lifeSpan = lifeSpan;
        this.velX = velX;
        this.velY = velY;

        this.damage = damage;
    }

    public void update(Graphics2D graphics) {
        if (lifeSpan > 0) {
            position.x += velX;
            position.y += velY;

            graphics.drawImage(sprite, (int)position.x, (int)position.y, sprite.getWidth(), sprite.getHeight(), null);

            lifeSpan--;
            checkCollision();
        }
        else {
            Main.player.projectiles.remove(this);
        }


    }

    public void checkCollision() {
        for (int a = 0; a < Main.entities.length; a++) {
            for (int b = 0; b < Main.entities[a].length; b++) {
                if (Main.entities[a][b].position - 20 < position.x) {
                    if (Main.entities[a][b].position + 20 > position.x) {
                        if (position.y > 390 && position.y < 430) {
                            if (Main.entities[a][b].survived) {
                                onCollision(a, b);
                            }
                        }
                    }
                }
            }
        }

    }

    public void onCollision(int a, int b) {
        Main.entities[a][b].health -= damage;
        Main.player.projectiles.remove(this);
    }
}
