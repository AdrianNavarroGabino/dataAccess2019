// Adrián Navarro Gabino

/*
 * 0.5: Ask the user for an integer and say if it is (or not) a prime
 * palindrome, with the help of two boolean functions "isPrime" and
 * "isPalindrome".
 */

import java.util.*;

public class Exercise005
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter an integer number: ");
        int number = sc.nextInt();
        
        if(isPalindrome(number) && isPrime(number))
            System.out.println("Prime palindrome");
        else
            System.out.println("NOT prime palindrome");
    }
    
    public static boolean isPrime(int n)
    {
        for(int i = 2; i <= n / 2; i++)
        {
            if(n % i == 0)
            {
                return false;
            }
        }
        
        return true;
    }
    
    public static boolean isPalindrome(int n)
    {
        String num1 = Integer.toString(n);
        String num2 = "";
        
        for(int i = num1.length() - 1; i >= 0; i--)
        {
            num2 += num1.charAt(i);
        }
        
        if(num1.equals(num2))
            return true;
        return false;
    }
}
