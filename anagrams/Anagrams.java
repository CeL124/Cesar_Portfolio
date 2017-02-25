/**
 * @author Cesar Lopez
 * Week 6: June 20-26, 2016
 */
package anagrams;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Anagrams {

    public static void main(String[] args) {
        String fileName = args[0];
        File inputFile = new File(fileName);
        String word;
        Map<String, List<String>> wordListMap = new HashMap<String, List<String>>();
        long start = System.currentTimeMillis();
        try {
            Scanner input = new Scanner(inputFile);

            while (input.hasNextLine()) {
                word = input.nextLine();
                char[] chars = word.toCharArray();
                Arrays.sort(chars);
                String sorted = new String(chars);

                if (wordListMap.get(sorted) != null) {
                    wordListMap.get(sorted).add(word);
                } else {
                    ArrayList<String> wordList = new ArrayList<>();
                    wordList.add(word);
                    wordListMap.put(sorted, wordList);
                }
            }
            for (String key : wordListMap.keySet()) {
                if (wordListMap.get(key).size() > 1) {
                    System.out.println(wordListMap.get(key));
                }
            }
            long end = System.currentTimeMillis();
            System.out.println(
                    "Some code took " + (end - start) + " mil to execute. (" + ((end - start) / 1000) + " seconds)");
            input.close();
        } catch (Exception e) {
            System.out.println("File not found");
            System.out.println(e.toString());
        }

    }

}
