package sortmain;

/**
 * @author Cesar Lopez
 * @Date: April 8 2017
 */

public interface SortInterface {

	void recursiveSort(int[] list) throws UnsortedException;

	void iterativeSort(int[] list) throws UnsortedException;

	int getCount();

	long getTime();
}
