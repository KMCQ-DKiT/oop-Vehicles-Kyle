package org.example;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PassengerStore {

    private final ArrayList<Passenger> passengerList;

    public PassengerStore(String fileName) {
        this.passengerList = new ArrayList<>();
        loadPassengerDataFromFile(fileName);
    }

    public List<Passenger> getAllPassengers() {
        return this.passengerList;
    }

    public void displayAllPassengers() {
        for (Passenger p : this.passengerList) {
            System.out.println(p.toString());
        }
    }

    /**
     * Read Passenger records from a text file and create and add Passenger
     * objects to the PassengerStore.
     */
    private void loadPassengerDataFromFile(String filename) {

        try {
            Scanner sc = new Scanner(new File(filename));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String name = sc.next();
                String email = sc.next();
                String phone = sc.next();
                double latitude = sc.nextDouble();
                double longitude = sc.nextDouble();

                // construct a Passenger object and add it to the passenger list
                passengerList.add(new Passenger(id, name, email, phone, latitude, longitude));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    // TODO - see functional spec for details of code to add
    public void addPassenger(String name, String email, String phone, double latitude, double longitude) {
        boolean found = false;
        Passenger i = new Passenger(name, email, phone, latitude, longitude);
        for (Passenger p : passengerList) {
            if (p.equals(i)) {
                found = true;
                break;
            }


        }
        if (found == false) {
            passengerList.add(i);
        }
    }


    public void displayPassengerMenu() {
        final String MENU_ITEMS = "\n*** PASSENGER MENU ***\n"
                + "1. Show all Passengers\n"
                + "2. Find Passenger by Name\n"
                + "3. Add Passenger\n"
                + "4. Edit Passenger\n"
                + "5. Delete Passenger\n"
                + "6. Exit\n"
                + "Enter Option [1,6]";
        final int SHOW_ALL = 1;
        final int FIND_BY_NAME = 2;
        final int ADD = 3;
        final int EDIT = 4;
        final int DELETE = 5;
        final int EXIT = 6;


        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case SHOW_ALL:
                        System.out.println("Display ALL Passengers");
                        displayAllPassengers();
                        break;
                    case FIND_BY_NAME:
                        System.out.println("Find Passenger by Name");
                        System.out.println("Enter passenger name: ");
                        String name = keyboard.nextLine();
                        System.out.println(findPassengerByName(name));
                        break;
                    case ADD:
                        System.out.println("Enter Name Of Passenger: ");
                        name = keyboard.nextLine();
                        System.out.println("Enter Email");
                        String email = keyboard.nextLine();
                        System.out.println("Enter Phone Number");
                        String phone = keyboard.nextLine();
                        System.out.println("Enter Latitude");
                        double latitude = keyboard.nextDouble();
                        System.out.println("Enter Longitude");
                        double longitude = keyboard.nextDouble();
                        addPassenger(name, email, phone, latitude, longitude);
                        break;
                    case EDIT:
                        System.out.println("1. Edit Name & Email");
                        System.out.println("2. Edit Phone Number");
                        System.out.println("3. Enter Longitude & Latitude");
                        int menu = 0;
                        final int EDIT_NAME = 1;
                        final int EDIT_PHONE = 2;
                        final int EDIT_LAT = 3;
                        String usersEditInput = keyboard.nextLine();
                        menu = Integer.parseInt(usersEditInput);
                        switch (menu){
                            case EDIT_NAME:
                                System.out.println("Enter ID of Passenger");
                                int id = keyboard.nextInt();
                                String whoKnowsWhyThisFixesIt = keyboard.nextLine();
                                System.out.println();
                                System.out.println("Enter New Name");
                                String newName = keyboard.nextLine();
                                System.out.println("Enter New Email");
                                String newEmail = keyboard.nextLine();
                                editNameAndEmail(id,newName,newEmail);
                                break;
                            case EDIT_PHONE:
                                System.out.println("Enter ID of Passenger");
                                int idPhone = keyboard.nextInt();
                                String whoKnowsWhyThisFixesItPhone = keyboard.nextLine();
                                System.out.println("Enter New Phone-Number");
                                String newPhone = keyboard.nextLine();
                                editPhone(idPhone,newPhone);
                                break;
                            case EDIT_LAT:
                                System.out.println("Enter ID of Passenger");
                                int idLat = keyboard.nextInt();
                                String whoKnowsWhyThisFixesItLat = keyboard.nextLine();
                                System.out.println("Enter New Latitude");
                                double newLat = keyboard.nextDouble();
                                System.out.println("Enter New Longitude");
                                double newLon = keyboard.nextDouble();
                                editLatAndLon(idLat,newLat,newLon);
                                break;
                        }
                        break;
                    case DELETE:
                        System.out.println("Enter ID of Passenger You Wish To Delete");
                        int id = keyboard.nextInt();
                        deletePassenger(id);
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

    private Passenger findPassengerByName(String name) {
        for(Passenger v: passengerList )
        {
            if(v.getName().equalsIgnoreCase(name))
            {

                return v;
            }

        }
        return null;
    }
    private Passenger editLatAndLon(int id, double newLat, double newLon) {
        for(Passenger v: passengerList )
        {
            if(v.getId()==(id))
            {
                v.setLocation(newLat,newLon);
                System.out.println("Edited Location");
                return v;
            }

        }
        return null;
    }
    private Passenger editPhone(int id, String newPhone) {
        for(Passenger v: passengerList )
        {
            if(v.getId()==(id))
            {
                v.setPhone(newPhone);
                System.out.println("Edited Phone");
                return v;
            }

        }
        return null;
    }

    private Passenger editNameAndEmail(int id, String newName,String newEmail) {
        for(Passenger v: passengerList )
        {
            if(v.getId()==(id))
            {

                v.setName(newName);
                v.setEmail(newEmail);
                System.out.println("Edited Name and Email");
                return v;
            }

        }
        return null;
    }
    public Passenger findPassengerByID(int id) {
        for(Passenger v: passengerList )
        {
            if(v.getId()==(id))
            {
                return v;
            }

        }
        return null;
    }
    public String getPassengerName(int id) {
        for(Passenger v: passengerList )
        {
            if(v.getId()==(id))
            {
                return v.getName();
            }

        }
        return null;
    }

    public String getEmail(int id) {
        for(Passenger v: passengerList )
        {
            if(v.getId()==(id))
            {
                return v.getEmail();
            }

        }
        return null;
    }
    private Passenger deletePassenger(int id)
    {
     for(Passenger v: passengerList )
    {
        if(v.getId()==(id))
        {
            passengerList.remove(v);
            return v;
        }

    }
        return null;
    }

} // end class
