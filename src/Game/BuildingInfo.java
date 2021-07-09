package Game;

import Buildings.Upgrade;

import java.awt.*;

public class BuildingInfo {

    public Point coordinates;
    public int occupied;
    public int repeat;
    public long preAttackTime;
    public int[] upgradeLevel;
    public Upgrade[] upgrades;

    public BuildingInfo(Point coordinates, int occupied, int repeat, long preAttackTime, int[] upgradeLevel, Upgrade[] upgrades) {

        this.coordinates = coordinates;
        this.occupied = occupied;
        this.repeat = repeat;
        this.preAttackTime = preAttackTime;
        this.upgradeLevel = upgradeLevel;
        this.upgrades = upgrades;

    }

    public void setDefault() {
        occupied = 0;
        upgradeLevel[0] = 0;
        upgradeLevel[1] = 0;
        upgrades[0] = new Upgrade(0,0);
        upgrades[1] = new Upgrade(0,0);
    }

}