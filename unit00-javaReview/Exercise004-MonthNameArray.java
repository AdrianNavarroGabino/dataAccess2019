// Adrián Navarro Gabino

/*
 * 0.4: Ask the user for a number from 1 to 12 and show the name of the
 * corresponding month, using an array of text strings in which they will be
 * stored those names.
 */

import java.util.*;

public class Exercise004
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number from 1 to 12: ");
        int month = sc.nextInt();
        
        String[] months = {"Jauary", "February", "March",
                          "April", "May", "June",
                          "July", "August", "September",
                          "October", "November", "December"};
        
        System.out.println(months[month - 1]);
    }
}
