package Game.Player;

import Game.Main;

import java.awt.geom.Point2D;

public class Fireball extends PlayerUpgrade{

    public Fireball(int cost, int id, int level, String name, String type, boolean equipped, int attackSpeed){
        super(cost, id, level, name, type, equipped, attackSpeed);
    }

    int speed = 7;

    public void doAttack() {
        double dx = Main.player.mousePosition.x - (Main.player.positionX + 20);
        double dy = Main.player.mousePosition.y - 40 - (Main.player.positionY + 20);

        double length = Math.sqrt(dx*dx + dy*dy);

        dx /= length;
        dy /= length;

        Main.player.projectiles.add(new Projectile(new Point2D.Double(Main.player.positionX, Main.player.positionY), Main.player.projectileSprites.get(id+1),
                50, dx*speed, dy*speed, 100));
    }

}