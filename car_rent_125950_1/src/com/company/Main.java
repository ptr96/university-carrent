package com.company;

import com.company.abstracts.DataBaseObject;
import com.company.enums.Fuel;
import com.company.exceptions.CustomerHasCarException;
import com.company.exceptions.CustomerHasTirException;
import com.company.exceptions.InvalidFuelExeption;
import com.company.model.Car;
import com.company.model.Customer;
import com.company.model.Tir;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static boolean running = true;
    public static void main(String[] args) throws Exception {

        Customer u1 = new Customer("Patryk", "Nowak", "Warszawa");
        Customer u2 = new Customer("Robert", "Nowak", "Białystok");
        Customer u3 = new Customer("Wioletta", "Nowak", "Poznań");

        Tir t1 = new Tir("Scania", "T40", 40, null, "Stary");

        Car c1 =new Car("mercedes", "c klasa", Fuel.gasoline,null, "na codzien");
        Car c2 =new Car("mercedes", "c klasa", Fuel.diesel,u3, "na codzien");

        u1.setCreateData(LocalDate.of(2020, 1,1));
        u2.setCreateData(LocalDate.of(2021, 3, 4));


        //DataBaseObject.getDataBase().stream().filter(n -> n.getName().startsWith("n")).forEach(n -> System.out.println(n + " "));
        //System.out.println(t1.czyPojazdMaUzytkownika());


//        Car c1 = new Car("BMW", "M5", u1);
//        Car c2 = new Car("merc", "M2", u2);
//        Tir t1 = new Tir("Reno", "Laweta", 10, u3);
//
//        // zbiór podtypów
//        System.out.println(Vehicle.getEkstensja());

        while(running){
            System.out.println("\nEnter 0 for load a rent car system." +
                    "\nEnter 1 for get all users"+
                    "\nEnter 2 for get details of any customer"+
                    "\nEnter 3 for add car"+
                    "\nEnter 4 for get all cars"+
                    "\nEnter 5 for add truck"+
                    "\nEnter 6 for get all trucks"+
                    "\nEnter 7 for search object by name"+
                    "\nEnter 8 for get all objects"+
                    "\nEnter 9 for check user has car"+
                    "\nEnter 10 for check user has tir"+
                    "\nEnter 11 for check add data"+
                    "\nEnter 12 for add customer"+
                    "\nEnter 13 for delete object from database"+
                    "\nEnter 14 for quit");
            int user_answer = scan.nextInt();
            switch(user_answer){

                case 1:
                    Customer.showCustomers();
                    break;
                case 2:
                    Customer.getDetailsOfObject();
                    break;
                case 3:
                    try
                    {
                        Car.addCar();
                    } catch (InvalidFuelExeption | CustomerHasCarException | CustomerHasTirException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    Car.wyswietlSamochody();
                    break;
                case 5:
                    Tir.addTir();
                    break;
                case 6:
                    Tir.wyswietlTiry();
                    break;
                case 7:
                    DataBaseObject.wyswietlObiektyPoNazwie(DataBaseObject.getDataBase());
                    break;
                case 8:
                    DataBaseObject.wyswietlWszystkieObiekty(DataBaseObject.getDataBase());
                    break;
                case 9:
                    System.out.println(Tir.obslugaCzyPojazdMaUzytkownika(Tir.getZbiorTirow()));
                    break;
                case 10:
                    System.out.println(Car.obslugaCzyPojazdMaUzytkownika(Car.getZbiorSamochod()));
                    break;
                case 11:
                    System.out.println("Data dodania obiektu: " + DataBaseObject.sprawdzDateDodania(DataBaseObject.getDataBase()));
                    break;
                case 12:
                    Customer.dodajCustomera();
                    break;
                case 13:
                    //DataBaseObject.usunObiekt(DataBaseObject.getDataBase());
                    break;
                case 14:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid user_answer");
            }
        }
    }
}

