package org.myatf;

    /*(Variable-Length Argument List) Write an application that calculates the product of a series
of integers that are passed to method product using a variable-length argument list. Test your method
with several calls, each with a different number of arguments.
Calculeaza produsul numerelor introduse, folosind variable-length argument list*/


import java.util.ArrayList;
import java.util.Scanner;

public class HomeworkOlga {

    public static void main(String[] args) {
        int num; //input number
        ArrayList<Integer> list = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        System.out.println("Application will calculate the product of input numbers!\ntype \"0\" to stop and receive the result");
        System.out.print("Enter a number: ");
        // loop will terminate when user enter 0
        while ((num = input.nextInt()) != 0) {
            System.out.print("Enter a number: ");
            list.add(num);
        }
        // Create array with list size
        Integer[] arr = new Integer[list.size()];
        // Convert ArrayList to Array
        arr = list.toArray(arr);
        // Apply method product to Array and return result
        System.out.println("Product is: " + product(arr));
        input.close();
    }

    public static int product(Integer... numbers) {
        if (numbers == null || numbers.length == 0) {
            // Handle the case when no numbers are provided
            return 0; // You can choose a different default value if needed
        }

        int result = 1; // Initialize the result to 1

        for (Integer num : numbers) {
            if (num != null) {
                result *= num;
            }
        }

        return result;
    }
}

