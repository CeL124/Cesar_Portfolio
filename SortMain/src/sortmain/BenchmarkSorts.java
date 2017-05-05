package sortmain;



/**
 * @author Cesar Lopez
 * Date: April 8 2017 
 * 
 * Purpose: code to perform the benchmarking of Selection Sort algorithm and measuring the actual run time and 
 * identify critical operation counts.
 *  
 */
import java.util.*;

public class BenchmarkSorts {
	private int[] sizesOfSets;
	private int numberOfTests = 50;//It should produce 50 data sets for each value of n
	private ArrayList<Statistics> stats = new ArrayList<Statistics>();

	public BenchmarkSorts(int[] sizesOfSets) {
		this.sizesOfSets = sizesOfSets;
	}

	/**
	 * method to do everything!
	 */
	public void runSorts() {
		SelectionSort sort = new SelectionSort();
		Statistics data;
		// arrays to store the time value of the sorts for each set
		long[] recursiveTimeArray = new long[numberOfTests];
		long[] iterativeTimeArray = new long[numberOfTests];

		// arrays to store critical operation count of the sorts for each set.
		int[] recursiveCriticalCount = new int[numberOfTests];
		int[] iterativeCriticalCount = new int[numberOfTests];

		for (int i = 0; i < sizesOfSets.length; i++) {
			for (int j = 0; j < numberOfTests; j++) {

				int[] listA = randomGenerator(sizesOfSets[i]);
				int[] listB = new int[sizesOfSets[i]];
				copyArray(listA, listB);

				// Recursive selection sort timer
				sort.resetCount();
				sort.setTime();
				try {
					sort.recursiveSort(listA);
					recursiveTimeArray[j] = sort.getTime();
					recursiveCriticalCount[j] = sort.getCount();
					sort.sortCheck(listA);
				} catch (UnsortedException e) {
					System.out.println(e); //If the array is not sorted, an exception should be thrown.
				}

				// Iterative Selection Sort
				sort.resetCount();
				sort.setTime();
				try {
					sort.iterativeSort(listB);
					iterativeTimeArray[j] = sort.getTime();
					iterativeCriticalCount[j] = sort.getCount();
					sort.sortCheck(listB);
				} catch (UnsortedException e) {
					System.out.println(e); //If the array is not sorted, an exception should be thrown.
				}

			} // end of nest FOR loop
			data = new Statistics(sizesOfSets[i], iterativeCriticalCount, iterativeTimeArray, recursiveCriticalCount,
					recursiveTimeArray);
			stats.add(data);

		} // End of size FOR loop

	}

	/**
	 * Just to copy arrays so both sorts use the same data.
	 * 
	 * @param from
	 * @param to
	 */
	public void copyArray(int from[], int to[]) {
		for (int i = 0; i < from.length; i++) {
			to[i] = from[i];
		}
	}

	/**
	 * randomly generate data to pass to the sorting methods.
	 * 
	 * @param size
	 * @return
	 */
	public int[] randomGenerator(int size) {
		int array[] = new int[size];
		Random random = new Random();
		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt(10000);
		}
		return array;
	}

	public void displayReport() {

		System.out.println("\n******|*************************************************************************|*************************************************************************|");
		System.out.println("******|                                ITERATIVE                                |                               RECURSIVE                                 |");
		System.out.println(" DATA |                                                                         |                                                                         |");
		System.out.println(" SET  |*************************************************************************|*************************************************************************|");
		System.out.println(" SIZE |   Average    | Coeff of Var |     Average Excecution    | Coeff of Var  |    Average   | Coeff of Var |      Average Excecution     | Coeff of Var|");
		System.out.println("  N   |   Op Count   |   of Count   |            Time           |    of Time    |    Op Count  |   of Count   |            Time             |   of Time   |");
		System.out.println(
				"------|--------------|--------------|---------------------------|---------------|--------------|--------------|-----------------------------|-------------|");
		for (int i = 0; i < stats.size(); i++) {
			stats.get(i).show();
		}

	}

}

/**
 * 
 * Purpose: code to determine their efficiency based on the number of times that
 * the critical operation is executed and actual time measurements. In addition,
 * you should examine the result of each call to verify that the data has been
 * properly sorted to verify the correctness of the algorithm.
 *
 */
class Statistics {
	private double averageCount;
	private double countCoeffVariance;
	private double averageTime;
	private double timeCoeffVariance;
	private double recAverageCount;
	private double recCountCoeffVariance;
	private double recAverageTime;
	private double recTimeCoeffVariance;
	private int size;

	Statistics(int size, int[] count, long[] time, int[] recCount, long[] recTime) {
		this.size = size;
		averageTime = 0;
		timeCoeffVariance = 0;
		double timeSummation = 0;
		double timeSumOfSquare = 0;
		double timePlaceHolder;
		double timeVariance = 0;
		double timeStandardDiv = 0;

		recAverageTime = 0;
		recTimeCoeffVariance = 0;
		double recTimeSummation = 0;
		double recTimeSumOfSquare = 0;
		double recTimePlaceHolder;
		double recTimeVariance = 0;
		double recTimeStandardDiv = 0;

		averageCount = 0;
		countCoeffVariance = 0;
		double countVariance = 0;
		double countSummation = 0;
		double countSumOfSquare = 0;
		double countStandardDiv = 0;
		double countPlaceHolder;

		recAverageCount = 0;
		recCountCoeffVariance = 0;
		double recCountVariance = 0;
		double recCountSummation = 0;
		double recCountSumOfSquare = 0;
		double recCountStandardDiv = 0;
		double recCountPlaceHolder;

		int arraySize = count.length;
		int timeArraySize = time.length;

		int recArraySize = recCount.length;
		int recTimeArraySize = recTime.length;

		for (int i = 0; i < arraySize; i++) {
			countSummation += count[i];
			timeSummation += time[i];

			recCountSummation += recCount[i];
			recTimeSummation += recTime[i];
		}
		check(arraySize, recArraySize, timeArraySize, recTimeArraySize);
		averageCount = countSummation / arraySize;
		averageTime = timeSummation / timeArraySize;

		recAverageCount = recCountSummation / recArraySize;
		recAverageTime = recTimeSummation / recTimeArraySize;

		for (int i = 0; i < arraySize; i++) {
			countPlaceHolder = count[i] - averageCount;
			countSumOfSquare += (countPlaceHolder * countPlaceHolder);

			timePlaceHolder = time[i] - averageTime;
			timeSumOfSquare += (timePlaceHolder * timePlaceHolder);

			recCountPlaceHolder = recCount[i] - recAverageCount;
			recCountSumOfSquare += (recCountPlaceHolder * recCountPlaceHolder);

			recTimePlaceHolder = recTime[i] - recAverageTime;
			recTimeSumOfSquare += (recTimePlaceHolder * recTimePlaceHolder);
		} // End for loop
		check(arraySize, recArraySize, timeArraySize, recTimeArraySize);

		// Iterative variance
		countVariance = countSumOfSquare / arraySize;
		timeVariance = timeSumOfSquare / timeArraySize;

		// Iterative Standard deviation
		countStandardDiv = Math.sqrt(countVariance);
		timeStandardDiv = Math.sqrt(timeVariance);

		//calculate Iterative coefficient of variances 
		countCoeffVariance = countStandardDiv / averageCount;
		timeCoeffVariance = timeStandardDiv / averageTime;

		// Recursive variance
		recCountVariance = recCountSumOfSquare / recArraySize;
		recTimeVariance = recTimeSumOfSquare / recTimeArraySize;
		
		// Recursive Standard deviation
		recCountStandardDiv = Math.sqrt(recCountVariance);
		recTimeStandardDiv = Math.sqrt(recTimeVariance);
		
		//calculate Recursive coefficient of variances
		recCountCoeffVariance = recCountStandardDiv / recAverageCount;
		recTimeCoeffVariance = recTimeStandardDiv / recAverageTime;

	}

	public void show() {

		System.out.format("%5d | %12.4f |%14.4f|%14.4f nano seconds|%15.4f|%14.4f|%14.4f|%16.4f nano seconds|%13.4f|%n",
				size, averageCount, countCoeffVariance, averageTime, timeCoeffVariance, recAverageCount,
				recCountCoeffVariance, recAverageTime, recTimeCoeffVariance);
	}

	/**
	 * catches if denominator is zero
	 * 
	 * @param num1
	 * @param num2
	 * @param num3
	 * @param num4
	 */
	void check(int num1, int num2, long num3, long num4) {
		if (num1 == 0 || num2 == 0 || num3 == 0 || num4 == 0) {
			throw new IllegalArgumentException("cannot devide by zero");
		}
	}

}// END Statistics Class
