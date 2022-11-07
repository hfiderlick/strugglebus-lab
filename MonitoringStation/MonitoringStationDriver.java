import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

public class MonitoringStationDriver {
    public static void main(String[] args) {
        /* Get and print input from user */

        System.out.println("---------------------");
        System.out.println("|   Station Setup   |");
        System.out.println("---------------------");
        Scanner kbd = new Scanner(System.in);
        String userStationID = MonitoringStation.getStationID(kbd);
        String userDescription = MonitoringStation.getDescription(kbd);
        File userDataFile = MonitoringStation.getFile(kbd);

        /* Create new Monitoring Station Object */

        MonitoringStation userStation = new MonitoringStation(userStationID, userDescription);

        /* Print number of samples returned */
        System.out.println("Successfully loaded " + userStation.loadSampleData(userDataFile) + " files");

        /* List samples returned */
        System.out.println("\n");
        System.out.println("------------------------");
        System.out.println("| Station Observations |");
        System.out.println("------------------------");
        System.out.println(userStation.displayObservations());

        /* List formatted Station summary */
        System.out.println("\n");
        System.out.println("---------------------");
        System.out.println("|  Station Summary  |");
        System.out.println("---------------------");
        System.out.println(userStation.toString());
        System.out.println(userStation.loadSampleData(userDataFile));
    }
}
