package Entities.M09_Statistics;

import Entities.Entity;
import java.util.ArrayList;

public class PieChart extends Entity {

    public   ArrayList<Integer> values;
    public   ArrayList labels;
    public   String type;

    public PieChart(ArrayList labels, ArrayList<Integer> values, String type) {
        this.labels = labels;
        this.values = values;
        this.type = type;
    }

    public ArrayList<Integer> getValues() {
        return values;
    }

    public void setValues(ArrayList<Integer> values) {
        this.values = values;
    }

    public ArrayList getLabels() {
        return labels;
    }

    public void setLabels(ArrayList labels) {
        this.labels = labels;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PieChart() {

    }
}
