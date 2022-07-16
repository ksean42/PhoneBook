package com.myapp.phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File directory = new File("src/resources/small_directory.txt");
        File findFile = new File("src/resources/small_find.txt");

        List<String> find = readFind(findFile);
        List<Phone> dir = readDirectory(directory);
        List<Phone> dirForQuicksort = new ArrayList<>(dir);

        runLinearSearch(dir, find);
        runBubbleSortAndJumpSearch(dir, find);
        runQuickSortAndBinarySearch(dirForQuicksort, find);
        runHashTable(directory, find);

    }
    public static void runLinearSearch(List<Phone> dir, List<String> find) {
        long linStartTime = System.currentTimeMillis();

        System.out.println("Start searching (linear search)...");
        int result = Search.linearSearch(dir, find);
        long linEndTime = System.currentTimeMillis();
        Duration linSearch = Duration.ofMillis(linEndTime - linStartTime);

        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n",
                result, find.size(), linSearch.toMinutesPart(), linSearch.toSecondsPart(), linSearch.toMillisPart());

    }

    public static void runBubbleSortAndJumpSearch(List<Phone> dir, List<String> find) {
        long totalStartTime = System.currentTimeMillis();
        System.out.println("\nStart searching (bubble sort + jump search)...");
        long bubbleStartTime = System.currentTimeMillis();
        Sort.bubbleSort(dir, totalStartTime);
        if(Sort.tooLong)
            Collections.sort(dir);

        long bubbleEndTime = System.currentTimeMillis();
        Duration bubbleTime = Duration.ofMillis(bubbleEndTime - bubbleStartTime);

        int result = Search.jumpingSearch(dir, find);

        long jumpEndTime = System.currentTimeMillis();
        Duration jumpTime = Duration.ofMillis(jumpEndTime - bubbleEndTime);

        Duration total = Duration.ofMillis(jumpEndTime - totalStartTime);

        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n",
                result, find.size(), total.toMinutesPart(), total.toSecondsPart(), total.toMillisPart());

        System.out.printf("Sorting time: %d min. %d sec. %d ms. %s\n", bubbleTime.toMinutesPart(),
                bubbleTime.toSecondsPart(), bubbleTime.toMillisPart(), Sort.tooLong  ? "- STOPPED, too long" : "");

        System.out.printf("Searching time: %d min. %d sec. %d ms.\n", jumpTime.toMinutesPart(),
                jumpTime.toSecondsPart(), jumpTime.toMillisPart());
    }

    public static void runQuickSortAndBinarySearch(List<Phone> dir, List<String> find) {
        long totalStartTime = System.currentTimeMillis();
        System.out.println("\nStart searching (quick sort + binary search)...");
        long quickStartTime = System.currentTimeMillis();
        Sort.tooLong = false;
        Sort.quickSort(dir, 0 , dir.size() - 1, quickStartTime);
        if(Sort.tooLong)
            Collections.sort(dir);
        long quickEndTime = System.currentTimeMillis();
        Duration quickTime = Duration.ofMillis(quickEndTime -quickStartTime);

        int result = Search.binarySearch(dir, find);

        long binaryEndTime = System.currentTimeMillis();
        Duration binaryTime = Duration.ofMillis(binaryEndTime - quickEndTime);

        Duration total = Duration.ofMillis(binaryEndTime - totalStartTime);

        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n",
                result, find.size(), total.toMinutesPart(), total.toSecondsPart(), total.toMillisPart());

        System.out.printf("Sorting time: %d min. %d sec. %d ms. %s\n", quickTime.toMinutesPart(),
                quickTime.toSecondsPart(), quickTime.toMillisPart(), Sort.tooLong  ? "- STOPPED, too long" : "");

        System.out.printf("Searching time: %d min. %d sec. %d ms.%s\n", binaryTime.toMinutesPart(),
                binaryTime.toSecondsPart(), binaryTime.toMillisPart(), Search.tooLong  ? "- STOPPED, too long" : "");
    }

    public static void runHashTable(File directory, List<String> find) throws FileNotFoundException {
        System.out.println("\nStart searching (hash table)...");
        Scanner sc = new Scanner(directory);
        Hashtable<String, String> dir = new Hashtable<>();
        String[] input;
        long totalStart = System.currentTimeMillis();
        while (sc.hasNext()) {
            input = sc.nextLine().split(" ", 2);
            dir.put(input[1], input[0]);
        }
        long creatingEnd = System.currentTimeMillis();
        Duration creatingTime = Duration.ofMillis(creatingEnd - totalStart);

        long searchingStart = System.currentTimeMillis();
        int result = Search.findInHashTable(dir, find);

        long searchingEnd = System.currentTimeMillis();
        Duration searchingTime = Duration.ofMillis(searchingEnd - searchingStart);

        long totalEnd = System.currentTimeMillis();
        Duration total = Duration.ofMillis(totalEnd - totalStart);

        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n",
                result, find.size(), total.toMinutesPart(), total.toSecondsPart(), total.toMillisPart());

        System.out.printf("Creating time: %d min. %d sec. %d ms.\n", creatingTime.toMinutesPart(),
                creatingTime.toSecondsPart(), creatingTime.toMillisPart());

        System.out.printf("Searching time: %d min. %d sec. %d ms.\n", searchingTime.toMinutesPart(),
                searchingTime.toSecondsPart(), searchingTime.toMillisPart());
    }

    public static List<Phone> readDirectory(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        List<Phone> dir = new ArrayList<>();
        while(sc.hasNext()) {
            dir.add(new Phone(sc.nextLine()));
        }
        return dir;
    }

    public static List<String> readFind(File file) throws FileNotFoundException{
        Scanner sc = new Scanner(file);
        List<String> find = new ArrayList<>();
        while(sc.hasNext()) {
            find.add(sc.nextLine());
        }
        return find;
    }
}
