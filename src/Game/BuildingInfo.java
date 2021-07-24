package Game;

import Buildings.BuildingUpgrade;

import java.awt.*;

public class BuildingInfo {

    public Point coordinates;
    public int occupied;
    public int repeat;
    public long preAttackTime;
    public int[] upgradeLevel;
    public BuildingUpgrade[] upgrades;

    public BuildingInfo(Point coordinates, int occupied, int repeat, long preAttackTime, int[] upgradeLevel, BuildingUpgrade[] upgrades) {

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
        upgrades[0] = new BuildingUpgrade(0,0);
        upgrades[1] = new BuildingUpgrade(0,0);
    }

}