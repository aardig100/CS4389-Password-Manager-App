package com.example.passwordmanager;

import java.security.SecureRandom;
import java.util.ArrayList;

public class RandomGen {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ArrayList<String> charGroup = new ArrayList<>();
        charGroup.add(0, "abcdefghijklmnopqrstuvxyz");
        charGroup.add(1, "ABCDEFGHIJKLMNOPQRSTUVXYZ");
        charGroup.add(2, "0123456789");
        charGroup.add(3, "$&@#!?");

        int passwordLength = 24; //If this value is already set somewhere else in
        //the app, pull it/call it from that somewhere else
        StringBuilder passwdStr = new StringBuilder();


        int[] checkPlace = new int[passwordLength];

        for (int i = 0; i < passwordLength; i++) {

            //Pick a random character group
            String randGroup = charGroup.get(randomNum(charGroup.size()));
            System.out.println("randGroup: " + randGroup);

            //Pick a random character from the random character group
            char randChar = randGroup.charAt(randomNum(randGroup.length()));
            System.out.println("randChar: " + randChar);

            passwdStr.append(randChar);
        }

        // passwdStr is the randomized password. Use that variable for the app


    }

    public static int randomNum(int upperBound) {

        //SecureRandom uses a Crytpographically Secured P-RNG
        SecureRandom secureRandom = new SecureRandom();
        int randomInt = secureRandom.nextInt(upperBound);

        return randomInt;

    }

}
