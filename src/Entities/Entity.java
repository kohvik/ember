package Entities;

import java.awt.*;

public abstract class Entity {

    public int health;
    public int speed;
    public int value;
    public int position;
    public boolean survived;

    public Entity(int health, int speed, int value, int position, boolean survived) {

    this.health = health;
    this.speed = speed;
    this.value = value;
    this.position = position;
    this.survived = survived;

    }

    public abstract void Draw(Graphics2D graphics);

}
