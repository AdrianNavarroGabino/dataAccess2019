// Adrián Navarro Gabino

/*
 * 1.2.3: Create a program that shows the contents of the "annotations.txt"
 * file paginated: after every 23 lines a pause will be made until the user
 * presses Enter.
 */

import java.io.*;
import java.util.*;

public class Exercise123
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        
        if (! (new File("annotations.txt")).exists() ) {
            System.out.println("annotations.txt not found");
            return;
        }

        try {
            BufferedReader file = new BufferedReader(
                new FileReader(new File("annotations.txt")));

            String line = file.readLine();
            int count = 0;

            while (line != null) {
                System.out.println(line);

                line = file.readLine();
                count++;
                if(count % 23 == 0)
                {
                    sc.nextLine();
                }
            }
            
            file.close();
        }
        catch (IOException errorDeFichero) {
            System.out.println(
                "There were problems: " +
                errorDeFichero.getMessage() );
        }
    }
}
