// Adrián Navarro Gabino

/*
 * 1.1.6. Create a program that asks the user how many days a day
 * month, the number of the first day (1 for Monday, 7 for Sunday) and the name
 * of the month (for example, "March") and create a text file called
 * "agendaMarch.txt" (or the corresponding month name), which will contain a
 * skeleton agenda for that month.
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Exercise116
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

        String[] weeks = {"Monday ", "Tuesday ", "Wednesday ",
                          "Thursday ", "Friday ", "Saturday ", "Sunday "};

        PrintWriter pw = null;

        try
        {
            pw = new PrintWriter("agenda" + month + ".txt");
            pw.println(month);
            pw.println(
                "-----------------------------------------------------------");

            for(int i = 0; i < numberOfDays; i++)
            {
                pw.print(weeks[(i + firstDayOfTheWeek) % 7]);
                pw.println((i + 1) + ":");
                pw.println(
                    "-----------------------------------------------------------");
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
