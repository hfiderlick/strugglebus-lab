import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

/* Implementation of FlowSampleEntry for Lab 06
 * @author Halle Fiderlick
 */

public class FlowSample {
    
    /* Instance variables */
    private String agency;
    private String siteNumber;
    private String qualCode;
    private double flowRate;
    private Date timestamp;
    private String dataLine;
    private String timeZone;
    private String userTimestamp;
    private String userTimeZone;

    /* Constructors */
    public FlowSample(String agency, String siteNumber, String timeZone, String qualCode, String timestamp, double flowRate)
    {
        this.agency = agency; 
        this.siteNumber = siteNumber;
        this.qualCode = qualCode;
        this.flowRate = flowRate;

    /* Create date formatter and parse  */
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        myFormatter.setTimeZone(TimeZone.getTimeZone(TimeUtility.getTimeZoneOffset(timeZone)));

        try
        {
            this.timestamp = myFormatter.parse(timestamp);
        }
        catch (ParseException e)
        {
            System.out.println("Error: Unable to parse timestamp: " + timestamp);
        
            /* Initialize timestamp with the epoch, January 1, 1970, 00:00:000 GMT */
            this.timestamp = new Date(0);
        }
    }

    public FlowSample(String line) 
    {
        this.dataLine = line;
    }

    /* Accessor methods */

    /* Return the current Agency for the sample
     * @return String containing Agency
     */
    public String getAgency()
    {
        Scanner userScanner = new Scanner(dataLine);

        while(userScanner.hasNextLine()) {
            String line = userScanner.nextLine();

            Scanner userLineScanner = new Scanner (line);
            userLineScanner.useDelimiter("\t");

            for(int i = 0; i < 1; i++)
            {
                this.agency = (userLineScanner.next());
            }
            userLineScanner.close();
        }
         userScanner.close();
         
        return this.agency;
    }
    
    /* Return the current Site Number for the sample
     * @return String containing Site Number
     */
    public String getSiteNumber()
    {
        Scanner userScanner = new Scanner(dataLine);

        while(userScanner.hasNextLine()) {
            String line = userScanner.nextLine();

            Scanner userLineScanner = new Scanner (line);
            userLineScanner.useDelimiter("\t");

            for(int i = 0; i < 2; i++)
            {
                this.siteNumber = (userLineScanner.next());
            }
            userLineScanner.close();
        }
         userScanner.close();

        return this.siteNumber;
    }
    
    /* Return the Flow Rate of this sample in cubic feet / second
     * @return double containing Flow Rate
     */
    public double getFlowRate()
    {
        Scanner userScanner = new Scanner(dataLine);

        String userFlowRate = "";

        while(userScanner.hasNextLine()) {
            String line = userScanner.nextLine();

            Scanner userLineScanner = new Scanner (line);
            userLineScanner.useDelimiter("\t");

            for(int i = 0; i < 5; i++)
            {
                userFlowRate = (userLineScanner.next());
            }
            userLineScanner.close();
        }
         userScanner.close();
        this.flowRate = Double.parseDouble(userFlowRate);
         return this.flowRate;
    }

    /* Return the Qualification Code for the sample
     * "P" means provisional value, subject to change
     * "A" means approved value, ready for publication
     * @return String containing the Qualification Code
     */
    public String getQualCode()
    {
        Scanner userScanner = new Scanner(dataLine);

        while(userScanner.hasNextLine()) {
            String line = userScanner.nextLine();

            Scanner userLineScanner = new Scanner (line);
            userLineScanner.useDelimiter("\t");

            for(int i = 0; i < 6; i++)
            {
                this.qualCode = (userLineScanner.next());
            }
            userLineScanner.close();
        }
         userScanner.close();
         
        return this.qualCode;
    }

    /**
     * Return a String representation of the timestamp with the following
     *     custom format: "EEE dd MMM yyyy hh:mm:ss a z"
     * 
     *     The timezone value should remain unchanged
     * 
     *     Example: Thu 30 Dec 2021 02:30:00 PM MST
     *
     * @return Timestamp with custom format
     */
    public String getTimestamp()
    {
        /* Isolate Timestamp data from the String */

        Scanner userScanner = new Scanner(dataLine);

        String userTimeStamp = "";

        while(userScanner.hasNextLine()) {
            String line = userScanner.nextLine();

            Scanner userLineScanner = new Scanner (line);
            userLineScanner.useDelimiter("\t");

            for(int i = 0; i < 2; i++)
            {
                userTimestamp = (userLineScanner.next());
                userTimeZone = (userLineScanner.next());
            }
            userLineScanner.close();
         }
         userScanner.close();

        this.userTimestamp = userTimestamp + " " + userTimeZone;

        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        myFormatter.setTimeZone(TimeZone.getTimeZone(TimeUtility.getTimeZoneOffset(userTimeZone)));

        try
        {
            this.timestamp = myFormatter.parse(userTimestamp);
        }
        catch (ParseException e)
        {
            System.out.println("Error: Unable to parse timestamp: " + userTimestamp);
        
            /* Initialize timestamp with the epoch, January 1, 1970, 00:00:000 GMT */
            this.timestamp = new Date(0);
        }

        /* Format the date and output as a string*/

            SimpleDateFormat customFormatter = new SimpleDateFormat("EEE dd MMM yyyy hh:mm:ss a z");
            return customFormatter.format(this.timestamp);
    }

    /**
     * Return a String representation of the timestamp with the following
     *     custom format: "EEE dd MMM yyyy hh:mm:ss a z"
     * 
     *     The timezone value should adjusted to UTC
     * 
     *     Example: Thu 30 Dec 2021 09:30:00 PM UTC
     *
     * @return UTC Timestamp with custom format
     */
    public String getUTCTimestamp()
    {
        SimpleDateFormat customFormatter = new SimpleDateFormat("EEE dd MMM yyyy hh:mm:ss a z");
        TimeZone tz = TimeZone.getTimeZone("UTC");
        customFormatter.setTimeZone(tz);
        return customFormatter.format(this.timestamp);
    }
        
/* ToString */

    /**
     * Return a String representation of the current FlowSample object
     *    using the following template:
     *
     *    ### <agency> - <siteNumber> ###
     *    Timestamp: <UTCTimeStamp>
     *    Flow Rate: <flowRate>
     *
     * @return String value representing this FlowSample object
     */
    public String toString()
    {
        String output = "";

        output += "### " + this.agency + " - " + this.siteNumber + " ###\n";
        output += "Timestamp: " + this.getUTCTimestamp() + "\n";
        output += "Flow Rate: " + this.getFlowRate() + "\n";

        return output;
    }

}