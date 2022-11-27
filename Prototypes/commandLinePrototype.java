package Prototypes;

// Use BigDecimal class for accuracy
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;

public class commandLinePrototype {
    
    // Constant variable declarations
    private static final BigDecimal nuggetWeightPounds = new BigDecimal(16.5).multiply(new BigDecimal(0.0022046226));

    public static void main(String args[]) {
        
        // Establish scanner and repetition variable
        Scanner sc = new Scanner(System.in);
        boolean complete = false;

        // Enter recurring loop
        while(!complete) {

            // Get current weight
            System.out.println("Please enter your current weight in pounds:");
            double weight = 0;
            // Try/catch for input validation
            try{
                weight = Double.valueOf(sc.nextLine().trim());
            } catch(Exception e) {
                System.out.println("Error readig input. Please use only numbers and do not include parenthesis.");
                continue;
            }

            // Get the desired percentage
            System.out.println("What percentage would you like to be? (Enter 1 for 1%):");
            double percent = 0;
            // Try/catch for input validation
            try{
                percent = Double.valueOf(sc.nextLine().trim());
            } catch(Exception e) {
                System.out.println("Error readig input. Please use only numbers and do not include the percent symbol.");
                continue;
            }

            // How many pounds of that thing are needed
            BigDecimal pounds = amountForPercent(weight, percent);
            // Factor in the weight of a chicken nugget
            BigDecimal number = pounds.divide(nuggetWeightPounds, new MathContext(5));

            // Output results
            System.out.println("You would need to consume " + number + " chicken nuggets.");

            // Go again?
            System.out.print("Would you like to go again? (y/n): ");
            // Do a little input cleaning
            char repeat = sc.nextLine().trim().toLowerCase().charAt(0);
            if(repeat == 'y') continue;
            else if (repeat == 'n') complete = true;
            else System.out.println("Error reading input: restarting.");
        }
        sc.close();
    }

    // Returns (as a float) the weight required for the new weight to be some percentage larger than the original weight.
    //
    // Arguments -
    // orig, a float: the original weight of the person
    // percent, a float: the percentage increase
    //
    // Example -
    // amountForPercentage(99, 1) will return 1 since a 99 pound person who ate a pound of chicken nuggets would be 100 pounds, one percentage (a single pound) of which is chicken nuggets.
    private static BigDecimal amountForPercent(double orig, double percent) {
        //New weight needed for consumption
        BigDecimal weight;

        //The general formula is orginal weight times percent over 100 minus percent. Source: trust me I did the math
        weight = (new BigDecimal(percent * orig)).divide(new BigDecimal(100 - percent), new MathContext(20));
        
        //Return it
        return weight;
    }

}
