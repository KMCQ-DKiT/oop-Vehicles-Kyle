package org.example;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BookingManager {
    private final ArrayList<Booking> bookingList;
    public PassengerStore passengerStore;
    public VehicleManager vehicleManager;

    // Constructor
    public BookingManager(String filename, VehicleManager vehicleManager, PassengerStore passengerStore) {
        this.bookingList = new ArrayList<>();
        this.vehicleManager = vehicleManager;
        this.passengerStore = passengerStore;
        loadBookingDataFromFile(filename);
    }
    public Booking findByID(int id)
    {
    for(Booking v: bookingList )
    {
        if(v.getBookingId()==(id))
        {
            return v;
        }

    }
        return null;
}

    public void displayAllBookings() {
        for (Booking b : bookingList)
            System.out.println(b.toString());
    }
    public void editBooking(int bookingID, int newPassenger, int newVehicle, int newYear, int newMonth, int newDay, int newHour, int newMinute, int newSecond,
                            double newStartLat, double newStartLon, double newEndLat, double newEndLon, double newCost)
    {
        Booking b = findByID(bookingID);
        b.setPassengerId(newPassenger);
        b.setVehicleId(newVehicle);
        b.setBookingDateTime(LocalDateTime.of(newYear,newMonth,newDay,newHour,newSecond));
        b.setStartLocation(newStartLat,newStartLon);
        b.setEndLocation(newEndLat,newEndLon);
        b.setCost(newCost);


    }

    public void makeBooking(int passengerId, int vehicleId,
                            int year, int month, int day, int hour, int minute, int second,
                            double startLatitude, double startLongitude,
                            double endLatitude, double endLongitude, double cost) {
        //list all vehicle id and passenger id
        //ask user for vehicle id and passenger id
        //ask user for rest of data
        //check that data exists
        //call booking manager to make the booking
        boolean found = false;

        Booking i = new Booking(passengerId, vehicleId,
                year, month, day, hour, minute, second,
                startLatitude, startLongitude,
                endLatitude, endLongitude, cost);

        for (Booking b : bookingList) {
            if (b.equals(i)) {
                found = true;
                break;
            }


        }
        if (found == false) {
            bookingList.add(i);
        } else {
            System.out.println("Booking already exists");
        }

    }

    private void loadBookingDataFromFile(String filename) {

        try {
            Scanner sc = new Scanner(new File(filename));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int bookingId = sc.nextInt();
                int passengerId = sc.nextInt();
                int vehicleId = sc.nextInt();
                int year = sc.nextInt();   // last service date
                int month = sc.nextInt();
                int day = sc.nextInt();
                int hour = sc.nextInt();
                int minute = sc.nextInt();
                int second = sc.nextInt();
                double startLatitude = sc.nextDouble();
                double startLongitude = sc.nextDouble();
                double endLatitude = sc.nextDouble();
                double endLongitude = sc.nextDouble();
                double cost = sc.nextDouble();

                bookingList.add(new Booking(bookingId, passengerId, vehicleId,
                        year, month, day, hour, minute, second,
                        startLatitude, startLongitude, endLatitude, endLongitude,
                        cost));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }


    //TODO implement functionality as per specification

    public void displayBooking() {

        final String MENU_ITEMS =
                "\n*** Vehicle MENU ***\n"
                        + "1. Display all bookings\n"
                        + "2. Create a Booking\n"
                        + "3. Edit a Booking\n"
                        + "4. Exit Menu\n"
                        + "Enter Option [1,4]";

        final int DISPLAY = 1;
        final int CREATE = 2;
        final int EDIT = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case DISPLAY:
                        System.out.println("Display ALL Passengers");
                        displayAllBookings();
                        break;
                    case CREATE:
                        System.out.println("Create A Booking");
                        System.out.println("********");
                        System.out.println("Enter PassengerID: ");
                        int passenegerID = keyboard.nextInt();
                        System.out.println("Enter VehicleID: ");
                        int VehicleID = keyboard.nextInt();
                        System.out.println("Enter Year of Booking");
                        int year = keyboard.nextInt();
                        System.out.println("Enter Month of Booking");
                        int month = keyboard.nextInt();
                        System.out.println("Enter Day of Booking");
                        int day = keyboard.nextInt();
                        System.out.println("Enter Hour of Booking");
                        int hour = keyboard.nextInt();
                        System.out.println("Enter Minute of Booking");
                        int minute = keyboard.nextInt();
                        System.out.println("Enter Second of Booking");
                        int second = keyboard.nextInt();
                        System.out.println("Enter Start Latitude");
                        double startLatitude = keyboard.nextDouble();
                        System.out.println("Enter Start Longitude");
                        double startLongitude = keyboard.nextDouble();
                        System.out.println("Enter End Latitude");
                        double endLatitude = keyboard.nextDouble();
                        System.out.println("Enter End Longitude");
                        double endLongitude = keyboard.nextDouble();
                        System.out.println("Enter Cost ");
                        double cost = keyboard.nextDouble();
                        makeBooking(passenegerID, VehicleID, year, month, day, hour, minute, second, startLatitude, startLongitude, endLatitude, endLongitude, cost);
                        String email = passengerStore.getEmail(passenegerID);
                        System.out.println("TO: " + email);
                        String name = passengerStore.getPassengerName(passenegerID);
                        System.out.println("Dear " + name);
                        System.out.println("");
                        System.out.println("This is a confirmation email on the car is as follows");
                        System.out.println("Car Ordered = ");
                        Vehicle v = vehicleManager.findVehicleByID(VehicleID);
                        System.out.println(v);
                        System.out.println("Passenger in Vehicle");
                        Passenger p = passengerStore.findPassengerByID(passenegerID);
                        System.out.println(p);
                        break;
                    case EDIT:
                        System.out.println("Edit Booking");
                        System.out.println("Enter Booking ID");
                        int bookingID = keyboard.nextInt();
                        System.out.println("Enter New Passenger");
                        int newPassenger = keyboard.nextInt();
                        System.out.println("Enter New VehicleID");
                        int newVehicle = keyboard.nextInt();
                        System.out.println("Enter New Year");
                        int newYear = keyboard.nextInt();
                        System.out.println("Enter New Month");
                        int newMonth = keyboard.nextInt();
                        System.out.println("Enter New Day");
                        int newDay = keyboard.nextInt();
                        System.out.println("Enter New Hour");
                        int newHour = keyboard.nextInt();
                        System.out.println("Enter New Minute");
                        int newMinute = keyboard.nextInt();
                        System.out.println("Enter new Second");
                        int newSecond = keyboard.nextInt();
                        System.out.println("Enter New Star Latitude");
                        double newStartLat = keyboard.nextDouble();
                        System.out.println("Enter New Start Longitude");
                        double newStartLon = keyboard.nextDouble();
                        System.out.println("Enter new End Latitude");
                        double newEndLat = keyboard.nextDouble();
                        System.out.println("Enter New End Longitude");
                        double newEndLon = keyboard.nextDouble();
                        System.out.println("Enter New Cost");
                        double newCost = keyboard.nextDouble();


                        editBooking(bookingID,newPassenger,newVehicle,newYear,newMonth,newDay,newHour,newMinute,
                                    newSecond,newStartLat,newStartLon,newEndLat,newEndLon,newCost);
                        break;
                    case EXIT:
                        System.out.println();
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                        }





            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");

            }

        }while (option != EXIT) ;
    }

}