package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class VehicleManager {
    public final ArrayList<Vehicle> vehicleList;  // for Car and Van objects

    public VehicleManager(String fileName) {
        this.vehicleList = new ArrayList<>();
        loadVehiclesFromFile(fileName);
    }

    public void displayAllVehicles() {
        for (Vehicle v : vehicleList)
            System.out.println(v.toString());
    }

    public void loadVehiclesFromFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String type = sc.next();  // vehicle type
                String make = sc.next();
                String model = sc.next();
                double milesPerKwH = sc.nextDouble();
                String registration = sc.next();
                double costPerMile = sc.nextDouble();
                int year = sc.nextInt();   // last service date
                int month = sc.nextInt();
                int day = sc.nextInt();
                int mileage = sc.nextInt();
                double latitude = sc.nextDouble();  // Depot GPS location
                double longitude = sc.nextDouble();
                double loadSpace = .0;
                int noOfSeats = 0;

                if (type.equalsIgnoreCase("van") || type.equalsIgnoreCase("truck"))
                {
                    loadSpace=sc.nextDouble();
                }
                else if (type.equalsIgnoreCase("car") || type.equalsIgnoreCase("4x4"))
                {
                    noOfSeats= sc.nextInt();
                }

                if (type.equalsIgnoreCase("Van") ||
                        type.equalsIgnoreCase("Truck")) {

                    // construct a Van object and add it to the passenger list
                    vehicleList.add(new Van(id, type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude,
                            loadSpace));
                }
                else if (type.equalsIgnoreCase("car")||type.equalsIgnoreCase("4x4"))
                {
                    vehicleList.add(new Car(id, type, make, model, milesPerKwH,
                            registration, costPerMile,
                            year, month, day,
                            mileage, latitude, longitude,
                            noOfSeats));
                }

            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }


    //TODO add more functionality as per spec.
    public Vehicle findVehicleByRegistrationNumber(String registration) {
        for (Vehicle v : vehicleList)
        {
            if (v.getRegistration().equalsIgnoreCase(registration)) {
                return v;
            }
        }

        return null;
    }
    public Vehicle findVehicleByID(int id) {
        for (Vehicle v : vehicleList)
        {
            if (v.getId()==(id)) {
                return v;
            }
        }

        return null;
    }
    public Vehicle findVehicleCost(int id) {
        for (Vehicle v : vehicleList)
        {
            if (v.getId()==(id)) {
                v.getCostPerMile();
                return v;
            }
        }

        return null;
    }
    public void DisplayCarVanOrTruck(String type,ArrayList<Vehicle> list) {

        for(Vehicle v: vehicleList )
        {
            if(v.getType().equalsIgnoreCase(type))
            {
                list.add(v);
            }
        }

    }

    public void displayVehicleMenu() {
        final String MENU_ITEMS = "\n*** Vehicle MENU ***\n"
                + "1. Search By Registration\n"
                + "2. Display all Vehicles\n"
                + "3. Display Car,4x4,Van or Truck\n"
                + "4. Exit Menu\n"
                + "Enter Option [1,5]";
        final int SEARCH = 1;
        final int SHOW_ALL = 2;
        final int DISPLAY_ORDER = 3;
        final int EXIT = 4;
        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case SEARCH:
                        System.out.println("Search By Registration");
                        System.out.print("Enter Registration:");
                        String registration = keyboard.nextLine();
                        System.out.println(findVehicleByRegistrationNumber(registration));

                        break;
                    case SHOW_ALL:
                        System.out.println("Display All Vehicles");
                        displayAllVehicles();
                        break;
                    case DISPLAY_ORDER:
                        VehicleRegComparator regComparator = new VehicleRegComparator();
                        ArrayList<Vehicle> vehicleList = new ArrayList<>();
                        System.out.print("Enter Type Of Vehicle, Car Van Or Truck, 4x4");
                        String type = keyboard.nextLine();
                        if (!(type.equalsIgnoreCase("car") || type.equalsIgnoreCase("4x4") || type.equalsIgnoreCase("truck") || type.equalsIgnoreCase("van"))) {
                            System.out.println("That Type " + type + "isnt Registered");
                        } else {
                            DisplayCarVanOrTruck(type, vehicleList);
                            Collections.sort(vehicleList, regComparator);
                            System.out.println("List of " + type + "'s sorted by registration");
                            System.out.println("----------------");
                            for (Vehicle v : vehicleList) {
                                System.out.println(v.toString());
                            }
                        }

                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        }
        while (option != EXIT);
    }
}



