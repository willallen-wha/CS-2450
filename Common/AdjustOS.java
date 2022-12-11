package Common;

/**
 * Common static class for detecting the OS of the computer running the code.
 * Necessary to be truly-cross platform since MacOS and Windows utilize different Filepaths
 */
public class AdjustOS {
    
    // Constant variable to hold a parsed OS family
    public static String OS = null;

    // Expected and support OS families
    public static final String WIN = "Windows";
    public static final String MAC = "Macintosh";
    public static final String OTH = "Other";

    // Variables to be adjusted
    public static String LOGOPATH = "/GUI/Graphics/Logo.png";
    public static String BACKGROUNDPATH = "/GUI/Graphics/Background.png";
    public static String USERSPATH = "/Auth/Users.txt";
    public static int FRAMEWIDTH = 580;

    /**
     * Static function to detect OS of the computer running the program. Should be called as soon as possible to 
     * ensure proper file traversal for auth and logo usage.
     */
    public static void detectOS() {
        // Make sure not already set
        if(isOSSet()) return;
        // Request OS details from computer and clean resulting string
        String temp = System.getProperty("os.name").toLowerCase().trim();
        // Check for recognized OS
        if(temp.contains("windows")) {
            OS = WIN;
            // LOGOPATH = LOGOPATH.replaceAll("/", "\\\\");
            // USERSPATH = USERSPATH.replaceAll("/", "\\\\");
            // BACKGROUNDPATH = BACKGROUNDPATH.replaceAll("/", "\\\\");
            FRAMEWIDTH = 500;
        }
        else if(temp.contains("mac")) {
            OS = MAC;
            // Defaults to assuming MacOS or other UNIX-like family for simplicity
            // No variables need adjusted
        }
        else {
            OS = OTH;
            System.out.println("Error detecting operating system: program will likely not run.");
        }
    }

    /**
     * Detect if the OS has already been set during this run of the program
     * 
     * @return true if detectOS() has already been called, false otherwise.
     * @see detectOS()
     */
    public static boolean isOSSet() {
        return OS != null;
    }

}
