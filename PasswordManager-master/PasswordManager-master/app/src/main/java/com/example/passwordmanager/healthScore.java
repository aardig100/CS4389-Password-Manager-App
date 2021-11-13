package com.example.passwordmanager;

public class healthScore {


    public String calculateHealth(String password){

        // overall score of password
        int score = 0;


        if( password.length() < 10 ) {
            return "0/5"; // bad password, return 0
        }
        else {
            score += 1;
        }

        // if password contains one digit, add 1 to total score
        if( password.matches("(?=.*[0-9]).*") ) {
            score += 1;
        }

        // if password contains one lower case letter, add 1 to total score
        if( password.matches("(?=.*[a-z]).*") ) {
            score += 1;
        }

        // if password contains one upper case letter, add 1 to total score
        if( password.matches("(?=.*[A-Z]).*") ) {
            score += 1;
        }

        // if password contains one special character, add 1 to total score
        if( password.matches("(?=.*[~!@#$%^&*()_-]).*") ) {
            score += 1;
        }

        // return score out of 5
        return (String.valueOf(score) + "/5");
    }
}
