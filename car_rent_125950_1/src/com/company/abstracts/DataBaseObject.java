package com.company.abstracts;

import com.company.exceptions.ObjectNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public abstract class DataBaseObject {
    private int ID;
    private String name;
    private static int IDnumerator=1;
    private LocalDate createData;//data dodania obiektu do bazy
    //String addDataString = String.format("Current Date/Time : %tc", addDate );

    //nie spełnia zasady jednej odpowiedzialnosci
    public static List<DataBaseObject> dataBase = new ArrayList<>();

    public DataBaseObject( String name) {
        this.ID = IDnumerator++;
        this.name = name;
        this.createData = LocalDate.now();
    }

//    public static void usunObiekt(List<DataBaseObject> list) throws ObjectNotFoundException {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Podaj id obiektu: ");
//        int idObiektu = scanner.nextInt();
//
//        List<Integer> listaDostepnychId = new ArrayList<>();
//        for (DataBaseObject dbo : list) {
//            listaDostepnychId.add(dbo.getID());
//        }
//
//        if (listaDostepnychId.contains(idObiektu)) {
//            for (DataBaseObject element : list) {
//                if (element.getID() == idObiektu) {
//                    list.removeIf(element);
//                } else if (!listaDostepnychId.contains(idObiektu)){
//                    throw new ObjectNotFoundException("Nie ma takiego obiektu o takim id");
//                }
//
//            }
//        }
//
//  }
//
//    public void usunObiekt(int ID)
//    {
//    this.element.removeIf(element -> element.()=ID);
//    }

    public static void wyswietlObiektyPoNazwie(List<DataBaseObject> list) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Podaj nazwe: ");
        String nazwa = scan.nextLine();

        for(DataBaseObject element : list) {
            if(nazwa.equalsIgnoreCase(element.getName())) {
                System.out.println(element);
            }
        }
    }

    public static LocalDate sprawdzDateDodania(List<DataBaseObject> list) throws ObjectNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj id obiektu: ");
        int idObiektu = scanner.nextInt();

        List<Integer> listaDostepnychId = new ArrayList<>();
        for(DataBaseObject dbo : list) {
            listaDostepnychId.add(dbo.getID());
        }

        if(!listaDostepnychId.contains(idObiektu)) {
            throw new ObjectNotFoundException("Nie ma takiego obiektu o takim id");
        }

        for(DataBaseObject element : list) {
            if(element.getID() == idObiektu) {
                return element.getCreateData();
            }
        }

        return null;
    }

    public static void wyswietlWszystkieObiekty(List<DataBaseObject> list) {
        System.out.println("\nWszystkie obiekty: ");
        for(DataBaseObject element : list) {
            System.out.println(element);
        }
    }


    public static List<DataBaseObject> getDataBase() {
        return dataBase;
    }

    public static void addObject(DataBaseObject DBO) {
        dataBase.add(DBO);
    }

    public int getID() {
        return ID;
    }
    
    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreateData() {
        return createData;
    }

    public void setCreateData(LocalDate createData) {
        this.createData = createData;
    }
}
