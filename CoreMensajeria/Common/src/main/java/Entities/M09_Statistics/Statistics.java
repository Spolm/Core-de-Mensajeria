package Entities.M09_Statistics;

import Entities.Entity;
import java.util.ArrayList;

public class Statistics extends Entity {

    private   ArrayList x;
    private   ArrayList<Integer> y;
    private   String type;


    public Statistics(ArrayList x, ArrayList<Integer> y) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public Statistics(ArrayList x, ArrayList<Integer> y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public ArrayList getX() {
        return x;
    }

    public void setX(ArrayList x) {
        this.x = x;
    }

    public ArrayList<Integer> getY() {
        return y;
    }

    public void setY(ArrayList<Integer> y) {
        this.y = y;
    }

    public Statistics() {
        x = new ArrayList();
        y = new ArrayList<Integer>();
    }

    public void addX(String x) {
        this.x.add(x);
    }

    public void addY(Integer y) {
        this.y.add(y);
    }
}