package com.company.abstracts;

import java.util.ArrayList;
import java.util.List;

public abstract class Person extends DataBaseObject{
    private String surname, adres;


    public Person(String name, String surname, String adres) {
        super(name);
        this.surname = surname;
        this.adres = adres;

        DataBaseObject.addObject(this);
    }

    public abstract void info();

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String toString() {
        return getName() + " " + surname + " " + adres;
    }
}
