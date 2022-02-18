package com.company.model;

import com.company.abstracts.DataBaseObject;
import com.company.abstracts.Vehicle;
import com.company.enums.Fuel;
import com.company.exceptions.*;
import com.company.implementations.IPrintable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Car extends Vehicle implements IPrintable {

    private Customer customer;
    private Fuel fuel;

    private static List<Car> zbiorSamochod = new ArrayList<>();

    public Car(String mark, String model, Fuel fuel, Customer customer,String name) {
        super(mark, model, name);
        this.customer = customer;

        this.fuel = fuel;

        zbiorSamochod.add(this);
    }

    /*
    public static void usunSamochodZListy() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter id of car to remove");
        int inputId = scan.nextInt();

        for(Car element : zbiorSamochod) {
            if(element.getID() == inputId) {
                System.out.println("Usunięty element: " + element);

                zbiorSamochod.remove(element);

                element.getCustomer().setCar(null);
                element.setMark(null);
                element.setModel(null);
                element.setCustomer(null);


            }
        }

    }
    */


    public static void wyswietlSamochody() {
        System.out.println("Wyświetl samochody: \n");
        for(Car element : zbiorSamochod) {
            System.out.println(element);
        }
    }

    public static void addCar() throws Exception, CustomerHasCarException, CustomerHasTirException, InvalidFuelExeption {
        Scanner scan = new Scanner(System.in);

        String mark, model, name;
        Customer customer = null;
        Fuel fuel = null;
        int userId;

        System.out.println("\nEnter User id: ");
        userId = scan.nextInt();

        System.out.println("\nEnter Mark: ");
        mark = scan.next();

        System.out.println("\nEnter model of car: ");
        model = scan.next();

        System.out.println("\nEnter name of car: ");
        name = scan.next();

        System.out.println("\nEnter fuel type: 1 - gasoline, 2- diesel");
        int value = scan.nextInt();

        switch (value) {
            case 1:
                fuel = Fuel.gasoline;
                break;
            case 2:
                fuel = Fuel.diesel;
                break;
            default:
                throw new InvalidFuelExeption("Nieprawidłowe paliwo");
                //w tym miejscu powinienem wyrzucić wyjatek - w tym momencie program jest kontynuowany pomimo tego, ze byl zly wybor;
                //wyjatek zakonczy dzialanie tej funkcji - invalidfuelexeption
        }



        for(Customer element : Customer.getZbiorUsrow()) {
            if(element.getID() == userId) {
                customer = element;
            }
        }

        if(customer.getCar() != null) {
            //w tym miejscu wyjatek
            throw new CustomerHasCarException("Nasz użytkownik o id: " + customer.getID() + " | Posiada już samochód: " + customer.getCar()+" samochód nie został dodany do bazy danych");
        } else if(customer.getTir() != null) {
            //w tym miejscu wyjatek jak wyzej
            throw new CustomerHasTirException("Nasz użytkownik o id: " + customer.getID() + " | Posiada już tira: " + customer.getCar()+ "pojazd nie został dodany do bazy danych");
        } else {
            Car c;
            if(fuel == Fuel.gasoline) {
                c = new Car(mark, model, Fuel.gasoline, customer, name);
            } else {
                c = new Car(mark, model, Fuel.diesel, customer,name);
            }
            customer.setCar(c);
            DataBaseObject.addObject(c);
            System.out.println(c);
        }
    }

    public Customer getCustomer() {
        return customer;
    }


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public static List<Car> getZbiorSamochod() {
        return zbiorSamochod;
    }


    public String toString() {
        if(getCustomer() != null) {
            return "Id: " + getID() + " | User: " + customer.getName() + " " + customer.getSurname() + " | Mark: " + getMark() + " | Model: " + getModel() + " | Fuel " + fuel;
        } else {
            return "Id: " + getID() + " | User: " + null + " | Mark: " + getMark() + " | Model: " + getModel() + " | Fuel " + fuel;
        }
    }

    @Override
    public void wyswietlCos() {
        System.out.println("Jesteśmy w klasie samochód");
    }

    public static boolean obslugaCzyPojazdMaUzytkownika(List<Car> list) throws CarNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id cara: ");
        int idCara = scanner.nextInt();

        List<Integer> listaDostepnychId = new ArrayList<>();
        for(Car element : list) {
            listaDostepnychId.add(element.getID());
        }

        if(!listaDostepnychId.contains(idCara)) {
            throw new CarNotFoundException("Nie ma takiego cara z takim id");
        }

        for(Car element : list) {
            if (element.getID() == idCara) {
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
