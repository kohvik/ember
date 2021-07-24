package Game.Player;

import Entities.Entity;
import Game.Loader;
import Game.Main;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {

    public double positionX;
    public double positionY;

    int speed;

    long currentTime;
    long[] lastTime;
    long[] deltaTime;

    public boolean[] attacking;

    Point mousePosition;

    BufferedImage sprite;
    ArrayList<BufferedImage> projectileSprites = new ArrayList<BufferedImage>();
    final ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    ArrayList<Beam> beams = new ArrayList<Beam>();

    PlayerUpgrade[][] upgrades = new PlayerUpgrade[2][2];

    int[] currentUpgrade = {0,0};

    public boolean isLeft, isUp, isRight, isDown;

    public Player() {

        sprite = Loader.loadAsset("/player/player.png");
        projectileSprites.add(Loader.loadAsset("/player/attack projectile.png"));
        projectileSprites.add(Loader.loadAsset("/player/ability projectile.png"));
        projectileSprites.add(Loader.loadAsset("/player/shotgun projectile.png"));
        projectileSprites.add(Loader.loadAsset("/player/fireball projectile.png"));
        projectileSprites.add(Loader.loadAsset("/player/orb projectile.png"));
        projectileSprites.add(Loader.loadAsset("/player/lightning.png"));

        speed = 3;
        positionX = Main.buildingInfo[3][3].coordinates.x + 30;
        positionY = Main.buildingInfo[3][3].coordinates.y + 30;

        deltaTime = new long[projectileSprites.size()];
        lastTime = new long[projectileSprites.size()];
        attacking = new boolean[2];

        for (int i = 0; i < projectileSprites.size(); i++) {
            deltaTime[i] = 0;
            lastTime[i] = 0;

        }

        upgrades[0][0] = new Shotgun(500, 1, 0, "shotgun", "attack", false, 600);
        upgrades[0][1] = new Orb(2500, 3, 0, "orb", "attack", false, 300);
        upgrades[1][0] = new Fireball(400, 2, 0, "fireball", "ability", false, 2000);
        upgrades[1][1] = new Lightning(4000, 4, 0, "lightning", "ability", false, 500);
    }

    public void update(Graphics2D graphics) {
        graphics.drawImage(sprite, (int) positionX, (int) positionY, 40, 40, null);
        doMovement();
        currentTime = System.currentTimeMillis();


        if (attacking[0]) {
            doAttack();
        }
        if (attacking[1]) {
            doAbility();
        }

        for (int i = 0; i < projectiles.size(); i++) {
                projectiles.get(i).update(graphics);
        }

        for (int i = 0; i < beams.size(); i++) {
            beams.get(i).update(graphics);
        }
        for (int i = 0; i < projectileSprites.size(); i++) {
            deltaTime[i] = currentTime - lastTime[i];
        }
    }

    public void doAttack() {

        if (currentUpgrade[0] > 0) {
            for (int a = 0; a < upgrades[0].length && upgrades[0][a] != null; a++) {
                if (deltaTime[upgrades[0][a].id + 1] > upgrades[0][a].attackSpeed && upgrades[0][a].id == currentUpgrade[0]) {
                    lastTime[upgrades[0][a].id + 1] = currentTime;
                    upgrades[0][a].doAttack();
                }
            }
        }

        else if (deltaTime[0] > 300) {
            lastTime[0] = currentTime;
            double dx = mousePosition.x - (positionX + 20);
            double dy = mousePosition.y - 40 - (positionY + 20);

            double length = Math.sqrt(dx * dx + dy * dy);

            dx /= length;
            dy /= length;

            projectiles.add(new Projectile(new Point2D.Double(positionX + 15, positionY + 10), projectileSprites.get(0), 50, dx * speed, dy * speed, 30));

        }
    }

    public void doAbility() {
        if (currentUpgrade[1] > 0) {
            for (int a = 0; a < upgrades[0].length && upgrades[1][a] != null; a++) {
                if (deltaTime[upgrades[1][a].id + 1] > upgrades[1][a].attackSpeed && upgrades[1][a].id == currentUpgrade[1]) {
                    lastTime[upgrades[1][a].id + 1] = currentTime;
                    upgrades[1][a].doAttack();
                }
            }
        }
        else if (deltaTime[1] > 5000) {
            lastTime[1] = currentTime;
            double dx = mousePosition.x - (positionX + 20);
            double dy = mousePosition.y - 20 - (positionY + 20);

            double length = Math.sqrt(dx * dx + dy * dy);

            dx /= length;
            dy /= length;

            projectiles.add(new Projectile(new Point2D.Double(positionX, positionY), projectileSprites.get(1), 50, dx * speed, dy * speed, 80));
        }
    }

    public void doMovement() {
        if (isUp) {
            if (isLeft || isRight) {
                positionY -= speed / 1.41421356237;
            } else {
                positionY -= speed;
            }
        }
        if (isLeft) {
            if (isUp || isDown) {
                positionX -= speed / 1.41421356237;
            } else {
                positionX -= speed;
            }
        }
        if (isDown) {
            if (isLeft || isRight) {
                positionY += speed / 1.41421356237;
            } else {
                positionY += speed;
            }
        }
        if (isRight) {
            if (isUp || isDown) {
                positionX += speed / 1.41421356237;
            } else {
                positionX += speed;
            }
        }
    }

    public void getMousePosition(Point mousePosition) {
        this.mousePosition = mousePosition;
    }

    public Entity getClosestEntity() {

        int closestDistance = 1000;
        Entity closestEntity = null;

        for (int a = 0; a < Main.entities.length; a++) {
            for (int b = 0; b < Main.entities[a].length; b++) {
                if (closestDistance > (int) Math.abs(Main.entities[a][b].position - positionX) && Main.entities[a][b].survived && Main.entities[a][b].position > 0) {
                    closestEntity = Main.entities[a][b];
                    closestDistance = (int) Math.abs(Main.entities[a][b].position - positionX);
                }
            }
        }

        return closestEntity;

    }
}

