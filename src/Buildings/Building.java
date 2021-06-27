package Buildings;

import java.awt.*;

public abstract class Building {

    public int cost;
    public int id;
    public String name;
    public Upgrade attackSpeed;
    public Upgrade attackDamage;

    public Building(int cost, int id, String name, Upgrade attackSpeed, Upgrade attackDamage) {

        this.cost = cost;
        this.id = id;
        this.name = name;
        this.attackSpeed = attackSpeed;
        this.attackDamage = attackDamage;

    }

    public abstract void Draw(Graphics2D graphics, int x, int y, boolean shopDraw);
    public abstract void doAttack(Graphics2D graphics, int x, int y);

}
