package Entities;

import java.awt.*;

public abstract class Entity {

    public int health;
    public int speed;
    public int position;
    public boolean survived;

    public Entity(int health, int speed, int position, boolean survived) {

    this.health = health;
    this.speed = speed;
    this.position = position;
    this.survived = survived;

    }

    public abstract void Draw(Graphics2D graphics);

}
