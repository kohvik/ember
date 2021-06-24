package Game;

import java.awt.*;

public class BuildingInfo {

    public Point coordinates;
    public int occupied;
    public int repeat;
    public long preAttackTime;
    public int[] upgrades;

    public BuildingInfo(Point coordinates, int occupied, int repeat, long preAttackTime, int[] upgrades) {

        this.coordinates = coordinates;
        this.occupied = occupied;
        this.repeat = repeat;
        this.preAttackTime = preAttackTime;
        this.upgrades = upgrades;

    }

}