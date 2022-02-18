package com.company.model;

import com.company.abstracts.DataBaseObject;
import com.company.abstracts.Vehicle;
import com.company.exceptions.TirNotFoundException;
import com.company.implementations.IPrintable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tir extends Vehicle implements IPrintable {
    private int ladownosc;


    private Customer customer;

    private static List<Tir> zbiorTirow = new ArrayList<>();

    public Tir(String mark, String model, int ladownosc, Customer customer,String name) {
        super(mark, model, name);
        this.ladownosc = ladownosc;
        this.customer = customer;

        zbiorTirow.add(this);
    }

    public static List<Tir> getZbiorTirow() {
        return zbiorTirow;
    }

    public static void wyswietlTiry() {
        System.out.println("Wyświetl tiry: \n");
        for(Tir element : zbiorTirow) {
            System.out.println(element);
        }
    }

    public static void addTir() throws Exception {
        Scanner scan = new Scanner(System.in);

        String mark, model;
        int ladowonosc;
        Customer customer = null;
        int userId;

        System.out.println("\nEnter User id: ");
        userId = scan.nextInt();

        System.out.println("\nEnter Mark: ");
        mark = scan.next();

        System.out.println("\nEnter model of car: ");
        model = scan.next();

        System.out.println("\nLadownosc: ");
        ladowonosc = scan.nextInt();

        for(Customer element : Customer.getZbiorUsrow()) {
            if(element.getID() == userId) {
                customer = element;
            }
        }

        if(customer.getCar() != null) {
            System.out.println("Nasz użytkownik o id: " + customer.getID() + " | Posiada już samochód: " + customer.getCar());
        } else if(customer.getTir() != null) {
            System.out.println("Nasz użytkownik o id: " + customer.getID() + " | Posiada już tira: " + customer.getTir());
        } else {
            Tir t = new Tir(mark, model, ladowonosc, customer,"");
            customer.setTir(t);
            DataBaseObject.addObject(t);
            System.out.println(t);
        }

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String toString() {
        if (getCustomer() != null) {
            return "Id: " + getID() + " | User: " + customer.getName() + " " + customer.getSurname() + " | Mark: " + getMark() + " | Model: " + getModel();
        } else {
            return "Id: " + getID() + " | User: " + null + " | Mark: " + getMark() + " | Model: " + getModel();
        }
    }

    @Override
    public void wyswietlCos() {
        System.out.println("Jesteśmy w klasie Tir");
    }

    public static boolean obslugaCzyPojazdMaUzytkownika(List<Tir> list) throws TirNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id tira: ");
        int idTira = scanner.nextInt();

        List<Integer> listaDostepnychId = new ArrayList<>();
        for(Tir element : list) {
            listaDostepnychId.add(element.getID());
        }

        if(!listaDostepnychId.contains(idTira)) {
            throw new TirNotFoundException("Nie ma takiego tira z takim id");
        }

        for(Tir element : list) {
            if (element.getID() == idTira) {
                return element.czyPojazdMaUzytkownika();
            }
        }

        return false;
    }

    @Override
    public boolean czyPojazdMaUzytkownika() {
        return getCustomer() != null;
    }
}
