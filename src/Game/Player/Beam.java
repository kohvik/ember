package Game.Player;

import Game.Main;

import java.awt.*;

public class Beam {

    int x1;
    int y1;
    int x2;
    int y2;

    double lifeSpan;
    double maxLifeSpan;
    int damage;

    Color color;

    int thickness;

    public Beam(int x1, int y1, int x2, int y2, double lifeSpan, int damage, int thickness, Color color) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.lifeSpan = lifeSpan;
        this.color = color;
        this.damage = damage;
        this.thickness = thickness;
        maxLifeSpan = lifeSpan;

    }

    public void update(Graphics2D graphics) {

        if (lifeSpan > 0) {
            if (lifeSpan == maxLifeSpan) {
                onCollision();
            }
            graphics.setStroke(new BasicStroke((float) (thickness * (lifeSpan/10))));
            graphics.setColor(color);
            graphics.drawLine(x1, y1, x2, y2);
            lifeSpan--;
        }
        else {
            Main.player.beams.remove(this);
        }

    }

    public void onCollision() {
        Main.player.getClosestEntity().health -= damage;
    }

}
