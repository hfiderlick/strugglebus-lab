/**
 * Utility class to work around depricated 3 letter timezones
 * @author Luke Hindman
 */
import java.util.HashMap;
import java.util.Map;

public class TimeUtility {
    /** For US Timezones only, provide a mapping beween
     * the legacy short name such as PST, PDT, MST, MDT etc
     * to the cooresponding GMT offset as a string value
     * 
     * @param Timezone short name as a string
     * 
     * @return GMT offset as a string
     **/
    public static String getTimeZoneOffset(String shortName) {

        /* Create a Map data structure to contain
         *   the mapping between short name and GMT offset */
        Map<String, String> tzMap = new HashMap<String, String>();
        tzMap.put("UTC","GMT-0");
        tzMap.put("PST","GMT-8");
        tzMap.put("PDT","GMT-7");
        tzMap.put("MST","GMT-7");
        tzMap.put("MDT","GMT-6");
        tzMap.put("CST","GMT-6");
        tzMap.put("CDT","GMT-5");
        tzMap.put("EST","GMT-5");
        tzMap.put("EDT","GMT-4");
        
        /* Nomalize the shortName to all upper case, the use
         *   the map to determine the correct offset and
         *   return it to the caller. */
        shortName = shortName.toUpperCase();
        if (tzMap.containsKey(shortName)) {
            return tzMap.get(shortName);
        } else {
            System.err.println("Unable to locate name in map: " + shortName);
            System.err.println("Using UTC instead");
            return tzMap.get("UTC");
        }
    }
}