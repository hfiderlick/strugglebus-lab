import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

/* Creates a class that can be used to instantiate
 * new objects of type Monitoring Station
 * @author Halle Fiderlick
 */

public class MonitoringStation {
    
    /* Instance variables */
    private String stationID;
    private String description;
    private ArrayList<FlowSample> observations;

    /* Constructor */
    public MonitoringStation(String stationID, String description)
    {
        this.stationID = stationID;
        this.description = description;

        /* Instantiate arrayList  */
        this.observations = new ArrayList<FlowSample>();

    }

    /* Methods */

    /**
     * Prompt the user to enter the Station ID and use the provided 
     *    Scanner object to read user input as a String and return
     *    it to the user.
     * @param kbd Scanner object bound to System.in
     * @return String containing Station ID
     */

    public static String getStationID(Scanner kbd) 
    {
        System.out.print("Please enter the station id: ");
        String stationID = kbd.nextLine();
        return stationID;
    }

    /**
     * Prompt the user to enter the Station description, and use the 
     * provided Scanner object to read user input as a String and return
     * it to the user.
     * @param kbd Scanner object bound to System.in
     * @return String containing Station description
     */

    public static String getDescription(Scanner kbd) 
    {
        System.out.print("Please enter the station description: ");
        String description = kbd.nextLine();
        return description;
    }

    /* Prompt user to enter the name of a TSV data file, then return the 
     *  name of the file to the user. If the file does not exist, print an
     *  error message and prompt the user again to enter a valid file name.
     * 
     * @param name of TSV data file
     * @return File 
    */
    public static File getFile(Scanner kbd) 
    {
        boolean validFile = false;
        File userFile;

        do
        {
            System.out.print("Please enter the filename: ");
            String filename = kbd.nextLine();
            userFile = new File("./"+ filename);

            if(userFile.exists() && userFile.isFile())
            {
            validFile = true;  
            }
            else
            {
            validFile = false;
            System.out.println("Error: File does not exist.");
            }  
        }while(validFile == false);

        return userFile;
    }

    /**
     * Read tab deliminated water flow data from specified TSV database file
     *    where each line in the file represents exactly one flow sample. 
     *    For each line, create a new FlowSample object and add it to the
     *    observations ArrayList. As each sample is added, increment a counter
     *    to track the total number of observations. When all flow samples
     *    have been processed, return the sample count.
     * 
     *    If a FileNotFoundException occurs when opening the database File
     *    with a Scanner object, display the following error in the console
     *    and return a sampleCount of zero.
     *
     * @param database File object containing TSV formatted water sample data
     * @return Number of samples processed
     */
    public int loadSampleData(File userFile)
    {
        int counter = 0;
    
        try {
			Scanner fileScan = new Scanner(userFile);

			while (fileScan.hasNextLine()) {
                
				String line = fileScan.nextLine();
                FlowSample userFlowSample = new FlowSample(line);
                System.out.println(userFlowSample);
                // observations.add(newFlowSample);
                counter ++;
			}
            
			
			fileScan.close();
		} catch (FileNotFoundException e) {

			System.out.println("File \"" + userFile + "\" could not be opened.");
			System.exit(1);
            return 0;
		} 
        // System.out.println(observations.toString());

        return counter;
    
    }

    /**
     * Display all of the FlowSample objects in the observations ArrayList 
     *     in the console, by calling toString() on each sample object 
     *     and displaying the resulting String value using println(). Count
     *     the number of items displayed and return this value to the caller.
     * 
     * @return Number of samples displayed
     */
    public int displayObservations()
    {
        int counter = 0;
        System.out.println(observations.toString());
        return counter;
    }

    /**
     * Return a String representation of the current MonitoringStation object
     *     using the following template:
     * 
     *     StationID: <stationID>
     *     Description: <description>
     *     Number of Observations: <number of observations>
     * 
     * @return String value representing this MonitoringStation object
     */
    public String toString()
    {
        String output = "";

        output += "Station ID: " + this.stationID + "\n";
        output += "Description: " + this.description + "\n";
        // output += "Number of observations: " + this.displayObservations() + "\n";

        return output;
    }

}