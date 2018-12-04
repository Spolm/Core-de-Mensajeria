package Classes.M09_Statistics;

import java.util.ArrayList;

public class Statistics {

    public   ArrayList x;
    public   ArrayList<Integer> y;
    public   String type;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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