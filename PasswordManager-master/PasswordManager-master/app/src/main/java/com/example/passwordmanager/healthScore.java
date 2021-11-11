package com.example.passwordmanager;

public class healthScore {
    public static void main(String[] args) {

        //test password strings
        String[] passwords = {
                "password",
                "helloworld1s",
                "helloworld01",
                "Helloworld01",
                "Hellow*rld01",
                "abc",
                "randompassword",
                "00000000000000",
                "JerrySmith01",
                "toyotacorolla2012",
                "Ferr@riCarz",
                "Ferr@riCarzSport01"
        };

        System.out.print( "Average Password Health: " + calculateHealth(passwords) );

    }

    private static double calculateHealth(String[] passwords){

        // overall score of password
        double score = 0;

        for (int i = 0; i < passwords.length; i++) { // iterate through each password

            if( passwords[i].length() < 8 ) {
                continue; // move to next iteration
            }
            else if ( passwords[i].length() >= 10 ) {
                score += 2;
            }
            else {
                score += 1;
            }

            // if password contains one digit, add 2 to total score
            if( passwords[i].matches("(?=.*[0-9]).*") ) {
                score += 2;
            }

            // if password contains one lower case letter, add 2 to total score
            if( passwords[i].matches("(?=.*[a-z]).*") ) {
                score += 2;
            }

            // if password contains one upper case letter, add 2 to total score
            if( passwords[i].matches("(?=.*[A-Z]).*") ) {
                score += 2;
            }

            // if password contains one special character, add 2 to total score
            if( passwords[i].matches("(?=.*[~!@#$%^&*()_-]).*") ) {
                score += 2;
            }


        } // end for

        // return average score out of 100
        return (score / passwords.length) * 10;
    }
}
