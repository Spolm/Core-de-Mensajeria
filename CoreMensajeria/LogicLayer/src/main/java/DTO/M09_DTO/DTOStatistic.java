package DTO.M09_DTO;

import DTO.DTO;

import java.util.ArrayList;

public class DTOStatistic extends DTO {

    private ArrayList x;
    private   ArrayList<Integer> y;
    private   String type;

    public DTOStatistic() {
        x = new ArrayList();
        y = new ArrayList<Integer>();
    }

    public DTOStatistic(ArrayList x, ArrayList<Integer> y) {
        this.x = x;
        this.y = y;
    }

    public DTOStatistic(ArrayList x, ArrayList<Integer> y, String type) {
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
}
