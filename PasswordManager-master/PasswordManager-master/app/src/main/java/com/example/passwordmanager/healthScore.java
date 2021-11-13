package com.example.passwordmanager;

public class healthScore {
    public static void main(String[] args) {

        //test password
        String password = "Ferr@rizzz";

        System.out.print(calculateHealth(password));

    }

    public static String calculateHealth(String password) {
        // overall score of password
        int score = 0;


        if( password.length() < 8 ) {
            return "0"; // bad password, return 0
        }
        else if ( password.length() >= 10 ) {
            score += 1;
        }
        else {
            score += 1;
        }

        // if password contains one digit, add 1 to total score
        if( password.matches("(?=.[0-9]).") ) {
            score += 1;
        }

        // if password contains one lower case letter, add 1 to total score
        if( password.matches("(?=.[a-z]).") ) {
            score += 1;
        }

        // if password contains one upper case letter, add 1 to total score
        if( password.matches("(?=.[A-Z]).") ) {
            score += 1;
        }

        // if password contains one special character, add 1 to total score
        if( password.matches("(?=.[~!@#$%^&()_-]).*") ) {
            score += 1;
        }

        // return score out of 5
        return (String.valueOf(score) + "/5");
    }
}
