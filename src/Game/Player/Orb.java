package Game.Player;

import Game.Main;

import java.awt.geom.Point2D;

public class Orb extends PlayerUpgrade{

    public Orb(int cost, int id, int level, String name, String type, boolean equipped, int attackSpeed){
        super(cost, id, level, name, type, equipped, attackSpeed);
    }

    int speed = 3;

    public void doAttack() {
        double dx = Main.player.mousePosition.x - (Main.player.positionX);
        double dy = Main.player.mousePosition.y - 40 - (Main.player.positionY);

        double length = Math.sqrt(dx*dx + dy*dy);

        dx /= length;
        dy /= length;

        double angle = (dx < 0? Math.atan(dy/dx) + Math.PI:Math.atan(dy/dx));

        double angle1 = angle + 0.1;
        double angle2 = angle - 0.1;
        double angle3 = angle + 0.2;

        double dx1 = Math.cos(angle1);
        double dy1 = Math.sin(angle1);
        double dx2 = Math.cos(angle2);
        double dy2 = Math.sin(angle2);
        double dx3 = Math.cos(angle3);
        double dy3 = Math.sin(angle3);

        Main.player.projectiles.add(new Projectile(new Point2D.Double(Main.player.positionX + 15, Main.player.positionY + 10), Main.player.projectileSprites.get(id+1),
                40, dx*speed, dy*speed, 20));
        Main.player.projectiles.add(new Projectile(new Point2D.Double(Main.player.positionX + 15, Main.player.positionY + 10), Main.player.projectileSprites.get(id+1),
                40, dx1*speed, dy1*speed, 20));
        Main.player.projectiles.add(new Projectile(new Point2D.Double(Main.player.positionX + 15, Main.player.positionY + 10), Main.player.projectileSprites.get(id+1),
                40, dx2*speed, dy2*speed, 20));
        Main.player.projectiles.add(new Projectile(new Point2D.Double(Main.player.positionX + 15, Main.player.positionY + 10), Main.player.projectileSprites.get(id+1),
                40, dx3*speed, dy3*speed, 20));

    }

}