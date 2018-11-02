package Modulo_9;

import java.util.ArrayList;

public class Statistics {

   public   ArrayList<String> x;
   public   ArrayList<Integer> y;
   public   String type;

    public Statistics(ArrayList<String> x, ArrayList<Integer> y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public ArrayList<String> getX() {
        return x;
    }

    public void setX(ArrayList<String> x) {
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

    }
}