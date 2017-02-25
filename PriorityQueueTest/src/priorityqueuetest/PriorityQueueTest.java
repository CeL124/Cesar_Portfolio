/**
 * Homework 2
 * Week three: May 30, 2016 - June 5, 2016
 *
 * @Author Cesar Lopez
 *
 * Purpose:To compare the runtime performance between one instance of the java.util.PriorityQueue<Integer>) 
 *         and one instance of the programmer's implementation of PriorityQueue.
 */
package priorityqueuetest;

import java.util.PriorityQueue;
import java.util.Random;

public class PriorityQueueTest {

    public static void main(String[] args) {
        MyPriorityQueue myQueue = new MyPriorityQueue(0);
        PriorityQueue<Integer> javaQueue = new PriorityQueue<>();

        System.out.println("Number of insertions-------Java Priority Queue(ms)----------My Priority Queue(ms)");

        for (int i = 1; i <= 10; i++) {

            int insertions = 10000 * i;

            Random randomValues = new Random();

            double startMyQueue = System.nanoTime();
            //inserting random values to myQueue
            for (int j = 0; j < insertions; j++) {
                int number = randomValues.nextInt();
                myQueue.addNumber(number);
            }
            double stopMyQueue = System.nanoTime();

            double startJavaQueue = System.nanoTime();
            //inserting random values to javaQueue
            for (int k = 0; k < insertions; k++) {
                int number = randomValues.nextInt();
                javaQueue.add(number);
            }
            double stopJavaQueue = System.nanoTime();

            //divided by 10^6 to get milliseconds. 
            double myQueueRuntime = ((stopMyQueue - startMyQueue) / 1000000);
            double javaQueueRuntime = ((stopJavaQueue - startJavaQueue) / 1000000);

            System.out.println("\t" + insertions + "\t\t\t  " + javaQueueRuntime + " \t\t\t" + myQueueRuntime);

        }

    }

}
