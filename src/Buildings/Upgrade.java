package Buildings;

import java.io.FileWriter;
import java.util.Scanner;

public class Upgrade {

    public int cost;
    public int id;

    public Upgrade(int cost, int id) {

        this.cost = cost;
        this.id = id;

    }

    public void saveUpgrade(FileWriter writer) {
        try {
            writer.write(cost + " ");
            writer.write(id + " ");
        } catch (Exception e) {System.out.println("could not save upgrade.");}
    }

    public void loadUpgrade(Scanner scanner) {
        try {
            cost = scanner.nextInt();
            id = scanner.nextInt();

        } catch (Exception e) {System.out.println("could not load upgrade.");}
    }

}
