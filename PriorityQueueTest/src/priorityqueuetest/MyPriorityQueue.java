/**
 * This class is the programmer's own implementation of the PriorityQueue
 * without using using predefined collections in the java library
 */
package priorityqueuetest;

public class MyPriorityQueue {

    int queueArray[];
    int queueSize;
    int count;

    public MyPriorityQueue(int arraySize) {

        this.queueSize = queueSize;
        queueArray = new int[queueSize];
        count = 0;

    }

    //inserting an integer to the array
    public void addNumber(int number) {

        while (count < queueSize) {
            queueArray[count] = Integer.MIN_VALUE;
            sizeIncreaser(count, number);
            count+=1;

        }
    }

    //Increasing the amount of intergers in the array. 
    void sizeIncreaser(int i, int number) {
        queueArray[i] = number;
        if (i >= 0 && queueArray[i / 2] < queueArray[i]) {

            int temp = queueArray[i / 2];
            queueArray[i / 2] = queueArray[i];
            queueArray[i] = temp;
            i = i / 2;
        }

    }

    // This method sorts the integers in the array in priority. 
    void heapSort(int i) {
        int right = 2 * i + 2;
        int left = 2 * i + 1;
        int largest = i;

        if (right < queueSize && queueArray[right] > queueArray[largest]) {
            largest = right;
        } else if (left < queueSize && queueArray[left] > queueArray[largest]) {
            largest = left;
        } else if (largest != i) {
            int temp = queueArray[i];
            queueArray[i] = queueArray[largest];
            queueArray[largest] = temp;

        }
    }

    //checking if a given integer is already contained in the queue.
    int checkForNumber(int number) {
        int index = 0;
        int notPresent = -1;
        while (index < count) {
            if (queueArray[index] == number) {
                index++;
                return index;
            }

        }
        return notPresent;
    }

    // remove an integer from array
    boolean remove(int number) {
        int subtitute = checkForNumber(number);
        while (subtitute != -1) {
            queueArray[subtitute] = queueArray[count - 1];
            count -= 1;
            heapSort(number);//rearrange to array set after number is removed from array.
            return true;
        }
        return false;
    }

}
