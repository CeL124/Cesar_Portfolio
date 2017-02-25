/**
 * Project 1
 * Week two: May 23-29, 2016
 *
 * @Author Cesar Lopez
 *
 * Purpose:This program finds all prime numbers less than a given number
 *         by implementing the Eratosthenes sieves algorithm.
 */
package primesieve;

import java.util.LinkedList;

public class PrimeSieve {

    public static void main(String[] args) {
        LinkedList<Integer> allNumbers = new LinkedList<>();
        LinkedList<Integer> allPrimes = new LinkedList<>();
        int prime;
        int primeMultiple;
        int count = 0;

        if (args.length != 1) {
            System.out.println("Incorrect amount of arguments entered\n\n");
            System.exit(1);
        } else {

            try {

                int maxNumber = Integer.parseInt(args[0]);

                //put numbers 2 to max number given into allNumber collection.
                for (int i = 2; i <= maxNumber; i++) {
                    allNumbers.add(i);
                }

                while (allNumbers.size() > 0) {

                    //remove the first element of AllNumbers and put it in allPrime.
                    prime = allNumbers.remove(0);
                    allPrimes.add(prime);

                    for (int j = 1; j*prime <= maxNumber; j++) {

                        if (allNumbers.contains(prime * j)) {

                            //remove all multiples of prime from allNumbers
                            primeMultiple = allNumbers.indexOf(prime * j);
                            allNumbers.remove(primeMultiple);

                        }
                    }

                }
                System.out.println("\t\t\t\t\t--------All Prime Numbers Less Than " + maxNumber + "----------\n");

                //Print all numbers from allPrimes
                for (int k = 0; k < allPrimes.size(); k++) {
                    count++;
                    if (count % 10 == 0) {
                        System.out.printf(" %5d\n", allPrimes.get(k));
                    } else {
                        System.out.printf(" %5d", allPrimes.get(k));
                    }

                }
                System.out.println("\n");
                
            } catch (NumberFormatException e) {
                System.err.println("Argument must be integer value!!!\n\n");
                System.exit(1);

            }
        }
    }

}
