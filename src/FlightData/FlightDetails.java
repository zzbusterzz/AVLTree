/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlightData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Student
 */
public class FlightDetails {

    private class TravelData {

        public String origin;
        public String destination;
        public String date;
        public String time;

        public TravelData(String origin, String destination, String date, String time) {
            this.origin = origin;
            this.destination = destination;
            this.date = date;
            this.time = time;
        }
    }

    private class FlightData {
        String flightName;
        FlightClass flightSeatsEconomy;
        FlightClass flightSeatFirst;
        String flightDuration;
        int seatCountTotal;

        public FlightData(String flightName, FlightClass flightSeatsEconomy, FlightClass flightSeatFirst, String flightDuration, int seatCountTotal) {
            this.flightName = flightName;
            this.flightSeatsEconomy = flightSeatsEconomy;
            this.flightSeatFirst = flightSeatFirst;
            this.flightDuration = flightDuration;
            this.seatCountTotal = seatCountTotal;
        }
    }

    private class FlightClass {
        int availableTickets;
        int classFare;

        public FlightClass(int availableTickets, int classFare) {
            this.availableTickets = availableTickets;
            this.classFare = classFare;
        }
    }

    private HashMap<TravelData, FlightData> flightStoreData;
    
    public static void main(String[] args) {
       FlightDetails list = new FlightDetails();
       list.BeginOperations();
    }
    
    
    void BeginOperations(){ 
       flightStoreData = new HashMap<TravelData, FlightData>();
       LoadFlightData();
    }
    
    
    void LoadFlightData(){
         try {
            File file =  new File("D:\\AVLTree_\\src\\FlightData\\FlightData.txt");
            Scanner sc = new Scanner(file);
            
            while (sc.hasNextLine()) {
                String[] flightDetails = sc.nextLine().split(",");
                //0-Flight Name
                //1-Origin
                //2-Destination
                //3-Date
                //4-Time
                //5-Total Seat
                //6-Available eco
                //7-Available first
                //8-Total time
                //9-Cost
                
                 TravelData f1Data = new TravelData(flightDetails[1], flightDetails[2], flightDetails[3], flightDetails[4]);
                 
                 FlightClass economy = new FlightClass(Integer.parseInt(flightDetails[6]), Integer.parseInt(flightDetails[9]));
                 FlightClass firstClass = new FlightClass(Integer.parseInt(flightDetails[7]), Integer.parseInt(flightDetails[9]));
                 FlightData f1TData = new FlightData(flightDetails[0], economy, firstClass, flightDetails[8], Integer.parseInt(flightDetails[5]));
            
                 if(!flightStoreData.containsKey(f1Data)){
                     flightStoreData.put(f1Data, f1TData);
                 }
            }
            //System.out.println(sc.nextLine());
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FlightDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}