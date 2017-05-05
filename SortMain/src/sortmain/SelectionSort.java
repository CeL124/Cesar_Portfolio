package sortmain;



/**
 * @author Cesar Lopez.
 * @Date: April 8 2017
 */

public class SelectionSort implements SortInterface {

	long startTime, endTime, time;
	int count;

	public void recursiveSort(int[] list) {
		recursiveWorkSort(list, 0);
	}

	/**
	 * Recursive Selection Sort Code was obtained from the URL below
	 * 
	 * http://www.cs.kzoo.edu/cs210/Labs/Recursion/recursiveSelSort.html
	 */
	public void recursiveWorkSort(int[] array, int startIndex) {
		if (startIndex >= array.length - 1)
			return;
		int minIndex = startIndex;
		for (int index = startIndex + 1; index < array.length; index++) {
			if (array[index] < array[minIndex])
				minIndex = index;
		}
		if (startIndex != minIndex) {
			count++;
			int temp = array[startIndex];
			array[startIndex] = array[minIndex];
			array[minIndex] = temp;
		}
		recursiveWorkSort(array, startIndex + 1);
	}

	/**
	 * Iterative version of selection sort.
	 * 
	 * @param array
	 * @return
	 */
	public void iterativeSort(int[] array) {
		int minValue, minIndex, temp = 0;

		for (int i = 0; i < array.length; i++) {
			minValue = array[i];
			minIndex = i;
			for (int j = i; j < array.length; j++) {
				if (array[j] < minValue) {
					minValue = array[j];
					minIndex = j;
				}
			}
			if (minValue < array[i]) {
				count++;
				temp = array[i];
				array[i] = array[minIndex];
				array[minIndex] = temp;

			}
		}

		return;
	}

	public void resetCount() {
		count = 0;
	}

	public int getCount() {
		return count;
	}

	public void setTime() {
		time = System.nanoTime();
	}

	public long getTime() {

		return System.nanoTime() - time;
	}

	/**
	 * examine the result of each call to verify that the data has been properly
	 * sorted to verify the correctness of the algorithm.
	 * 
	 * @param list
	 * @throws UnsortedException
	 */
	public void sortCheck(int[] list) throws UnsortedException {

		for (int i = 1; i < list.length; i++) {
			if (list[i - 1] > list[i]) {
				throw new UnsortedException("Array is not Sorted");
			}

		}

	}

}