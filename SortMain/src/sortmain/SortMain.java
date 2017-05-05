package sortmain;

/*
 * @author Cesar Lopez
 * Date April 8, 2017
 * 
 * Driver Class
 */
public class SortMain {

	public static void main(String[] args) throws UnsortedException {

		// create 10 different sizes of data sets.
		int[] sizesOfSets = { 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000 };

		BenchmarkSorts benchmark = new BenchmarkSorts(sizesOfSets);
		benchmark.runSorts();
		benchmark.displayReport();

	}// End of Main

}

