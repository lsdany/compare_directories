package com.ld;

import com.ld.files.FileReader;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Preparing to compare directories");
        FileReader fileReader = new FileReader();

        if(args.length != 2 ){
            System.out.println("Enter directory origin: ");
            try(Scanner scann = new Scanner(System.in)){
                String origin = scann.next();
                System.out.println("Enter directory destination: ");
                String destination = scann.next();
                fileReader.read(origin, destination);
            }
        }else{
            System.out.printf("Comparing directories: %s ---->  %s \n", args[0] , args[1]);
            fileReader.read(args[0] , args[1]);

        }
    }

}
