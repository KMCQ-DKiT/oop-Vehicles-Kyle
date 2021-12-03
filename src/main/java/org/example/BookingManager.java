package org.example;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class BookingManager
{
    private final ArrayList<Booking> bookingList;
    private PassengerStore passengerStore;
    private VehicleManager vehicleManager;
    // Constructor
    public BookingManager(String filename) {
        this.bookingList = new ArrayList<>();
        loadBookingDataFromFile(filename);
    }

    public void displayAllBookings() {
        for (Booking b : bookingList)
            System.out.println(b.toString());
    }

    public void makeBooking(int passengerId, int vehicleId,
                            int year, int month, int day, int hour, int minute, int second,
                            double startLatitude, double startLongitude,
                            double endLatitude, double endLongitude,double cost)
    {
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

        for(Booking b: bookingList)
        {
            if(b.equals(i))
            {
                found = true;
                break;
            }


        }
        if(found == false)
        {
            bookingList.add(i);
        }
        else
        {
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


}
