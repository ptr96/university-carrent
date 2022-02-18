package com.company.model;

import com.company.abstracts.DataBaseObject;
import com.company.abstracts.Person;
import com.company.implementations.IPrintable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer extends Person {
    private Car car;
    private Tir tir;

    // zbiór wszystkich obiektów typu USER
    private static List<Customer> zbiorUsrow = new ArrayList<>();

    public Customer(String name, String surname, String adres) {
        super(name, surname, adres);
        zbiorUsrow.add(this);
    }

    public static void dodajCustomera() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Podaj imie: ");
            String name = scanner.next();

            System.out.println("Podaj nazwisko: ");
            String surname = scanner.next();

            System.out.println("Podaj adres: ");
            String adres = scanner.next();

            Customer toAdd = new Customer(name,surname,adres);
            DataBaseObject.addObject(toAdd);
            System.out.println("Pomyślnie dodano: " + toAdd);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void getDetailsOfObject() {
        Scanner scan = new Scanner(System.in);

        Customer newCustomer = null;

        System.out.println("Enter id: ");
        int customerIdInput = scan.nextInt();

        for (Customer element : zbiorUsrow) {
            if (element.getID() == customerIdInput) {
                newCustomer = element;
            }
        }
        newCustomer.info();
    }

    @Override
    public void info() {
        System.out.println("\nInformacje o kliencie: ");
        System.out.println("Id: " + getID());
        System.out.println("Imie: " + getName());
        System.out.println("Nazwisko: " + getSurname());
        System.out.println("Adres: " + getAdres());
        if (getCar() != null) {
            System.out.println("Samochód: " + getCar());
        } else {
            System.out.printf("Klient nie posiada samochodu.");
        }
        System.out.println();
    }

    public static void showCustomers() {
        System.out.printf("List of customers: \n");
        for (Customer element : zbiorUsrow) {
            System.out.println(element);
        }
    }

    public Tir getTir() {
        return tir;
    }

    public void setTir(Tir tir) {
        this.tir = tir;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public static List<Customer> getZbiorUsrow() {
        return zbiorUsrow;
    }



    public String toString() {
        return "Id: " + getID() + " | Name: " + getName() + " | Adres: " + getAdres();
    }

}
