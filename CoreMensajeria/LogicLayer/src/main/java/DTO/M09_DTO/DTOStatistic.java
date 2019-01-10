package DTO.M09_DTO;

import DTO.DTO;

import java.util.ArrayList;

public class DTOStatistic extends DTO {

    private ArrayList x;
    private   ArrayList<Integer> y;

    public DTOStatistic() {
        x = new ArrayList();
        y = new ArrayList<Integer>();
    }

    public DTOStatistic(ArrayList x, ArrayList<Integer> y) {
        this.x = x;
        this.y = y;
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
}
