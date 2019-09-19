// Adrián Navarro Gabino

/*
 * 1.1.7. Create a program that asks the user how many days a day
 * month, the number of the first day (1 for Monday, 7 for Sunday) and the name
 * of the month (for example, "March") and create a text file called
 * "calendarMarch.txt" (or the corresponding month name), with the
 * calendar of that month.
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Exercise117
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of days of the month: ");
        int numberOfDays = sc.nextInt();
        System.out.print("Enter the first day of the week (1 to 7): ");
        int firstDayOfTheWeek = sc.nextInt() - 1;
        System.out.print("Enter the name of the month: ");
        sc.nextLine();
        String month = sc.nextLine();

        PrintWriter pw = null;

        try
        {
            pw = new PrintWriter("calendar" + month + ".txt");
            pw.println(month);
            pw.println("mon tue wed thu fri sat sun");

            for(int i = 0; i < numberOfDays; i++)
            {
                if(i == 0)
                {
                    for(int j = 0; j < 2 + firstDayOfTheWeek * 4; j++)
                        pw.print(" ");
                }
                else if((i + firstDayOfTheWeek) % 7 == 0 && i != 0)
                {
                    pw.println();
                    pw.print(" ");
                    if(i + 1 <= 9)
                        pw.print(" ");
                }
                else
                {
                    pw.print("  ");
                    if(i + 1 <= 9)
                        pw.print(" ");
                }
                pw.print(i + 1);
            }

            System.out.println("File created");
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(pw != null)
                pw.close();
        }
    }
}
