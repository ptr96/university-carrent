package com.company.abstracts;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle extends DataBaseObject{
    private String mark, model;

    public Vehicle(String mark, String model, String name) {
        super(name);
        this.mark = mark;
        this.model = model;

        DataBaseObject.addObject(this);
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String toString() {
        return mark + " " + model;
    }
}
