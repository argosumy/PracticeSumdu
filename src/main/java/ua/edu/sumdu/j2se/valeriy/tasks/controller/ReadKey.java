package ua.edu.sumdu.j2se.valeriy.tasks.controller;

import java.util.Scanner;

public interface ReadKey {
    public static  int readKey() {
        int num;
        while (true) {
            Scanner in = new Scanner(System.in);
            num = in.nextInt();
            break;
        }
        return num;
    }
}
