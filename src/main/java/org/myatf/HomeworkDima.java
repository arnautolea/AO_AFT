package org.myatf;

import java.util.Scanner;

public class HomeworkDima {
    public static void main(String[] args) {
        int n;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number between 3 and 100: ");
        n = input.nextInt();
        if (n > 2 && n < 101) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(Math.abs(i - j));
                }
                System.out.println();
            }
        } else
            System.out.println("Wrong input");
    }
}
