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
        
        // Overriding the hashcode() function 
        @Override
        public int hashCode() 
        { 
            String val = (GenerateASCIIString(origin) + GenerateASCIIString(destination)) + "" + (GenerateASCIIString(date) + GenerateASCIIString(time));
            return Integer.parseInt(val);
        } 
        
         @Override
        public boolean equals(Object o) 
        { 
            if (this == o) { 
                return true; 
            } 
            if (o == null) { 
                return false; 
            } 
            if (this.getClass() != o.getClass()) { 
                return false; 
            } 
            TravelData other = (TravelData)o; 
            if (!this.origin.equals(other.origin)
                || !this.destination.equals(other.destination)
                || !this.date.equals(other.date)
                || !this.time.equals(other.time)) { 
                return false; 
            } 
            return true; 
        } 
        
        int GenerateASCIIString(String value){
            int hashCode = 0;
            for(int i = 0; i <value.length(); i++){
                int c = value.charAt(i);
                hashCode += c;
            }   
            return hashCode;
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
            File file =  new File("C:\\Users\\1\\Documents\\NetBeansProjects\\DS_PracticalsSet\\src\\FlightData\\FlightData.txt");
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
            
            sc.close();
            sc = new Scanner(System.in);
            
            String srcCity;
            String dstCity;
            String date;
            String time;
            String difference;
            
            System.out.println("Enter the source city");
            srcCity = sc.nextLine();
            
            System.out.println("Enter the destination city");
            dstCity = sc.nextLine();
            
            System.out.println("Enter the date(DD/MM/YY)");
            date = sc.nextLine();
            
            System.out.println("Enter the time(HH:MM:SS)");
            time = sc.nextLine();
            
            TravelData f1Data = new TravelData(srcCity, dstCity, date, time);
         
            if(flightStoreData.containsKey(f1Data)){
                FlightData f1TData = flightStoreData.get(f1Data);
                System.out.println("Flight destination data : \nFlight name:" + f1TData.flightName + " : Flight Duration : " + f1TData.flightDuration + " : Total Seats : " + f1TData.seatCountTotal + " : First Class - Available Seats : " + f1TData.flightSeatFirst.availableTickets + " : First Class - Fare : " + f1TData.flightSeatFirst.classFare + " : Economy - Available Seats : " + f1TData.flightSeatsEconomy.availableTickets + " : Economy - Fare: " + f1TData.flightSeatsEconomy.classFare);    
            }else
                System.out.println("No available flights found");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FlightDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}