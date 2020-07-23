import java.util.*;

public class Luhnsalgorithm {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in); //creating scanner
        String input = s.next(); //user input for card number
        // System.out.println(validateCreditCardNumber(input));
        if(confirmCreditCardNumber(input))  //When the result is returned from the validate card number class its passed through this if statement to determine if valid or invalid
        {
            System.out.println("VALID");
        }
        else
        {
            System.out.println("INVALID");
        }
    }
    private static boolean confirmCreditCardNumber(String input) //passing in string input from main method and returning a boolean
    {
        int[] creditCardInt = new int[input.length()]; //Converting input from string to ints in an array

        for (int i = 0; i < input.length(); i++)
        {
            creditCardInt[i] = Integer.parseInt(input.substring(i, i+1)); // turns whole string input into an array of int
        }
        for (int i=creditCardInt.length-2; i>=0; i=i-2) //go through the array from the second last digit, getting every second digit from there
        {
            int tempValue= creditCardInt[i];
            tempValue=tempValue*2;

            if (tempValue >9 ) //if digit is greater than 9 then we divide by 10 and add 1 which is the same as taking 9 from the tot
            {
                tempValue = tempValue % 10 + 1;
            }
            creditCardInt[i]=tempValue;
        }
        int total= 0;

        for(int i=0; i<creditCardInt.length;i++) //adds each element in the int array
        {
            total+= creditCardInt[i];
        }

        if(total % 10 ==0) //divides total by 10 and if it = 0 then its valid
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}