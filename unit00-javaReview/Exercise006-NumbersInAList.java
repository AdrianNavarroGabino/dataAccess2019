// Adrián Navarro Gabino

/*
 * 0.6: Create a program that will have two phases: a first phase of request for
 * data and a second phase of search results. In the first phase, the
 * user will enter positive integers, which will be saved in a
 * ArrayList (this phase will end when you enter a negative number, which does
 * not it will be saved). Then, the user will enter positive integers of
 * one by one, which will be answered if the corresponding number is or is not
 * in the list of data that had been initially entered. When
 * enter a negative number again, the search phase will end and
 * the data contained in the ArrayList will be sorted.
 */

import java.util.*;

public class Exercise006
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        
        int number;
        
        do
        {
            System.out.print("Enter a possitive number: ");
            number = sc.nextInt();
            
            if(number > 0)
                numbers.add(number);
        }while(number > 0);
        
        do
        {
            System.out.print("Enter a possitive number to search: ");
            number = sc.nextInt();
            
            if(number > 0 && numbers.contains(number))
                System.out.println("It's in the list");
            else if(number > 0 && !numbers.contains(number))
                System.out.println("It's NOT in the list");
        }while(number > 0);
        
        Collections.sort(numbers);
        for(int num: numbers)
        {
            System.out.println(num);
        }
    }
}
