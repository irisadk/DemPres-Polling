import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
* Reads in csv files of the form lastname, fullname, percentage
* and inputs data into an ArrayHeap, favoring recent entries.
* @author Iris Kim
*/
public class DriverHW06 {
    
    /** The position in the filename where the year number begins. */
    public static final int YEARSTARTINDEX = 8;
    /** The position in the filename where the day number ends. */
    public static final int YEARENDINDEX = 16;

    /**
    * Main method. Reads in a list of filenames, stores the data in a heap,
    * prints out the updated heap after each file is read.
    * @param args - array of filenames
    */
    public static void main(String[] args) {
        checkArgs(args.length);
        String[] fileArgs = new String[args.length];
        String[] nameArgs = new String[args.length];
        int topN = -1;
        int numFileArgs = 0;
        int numNameArgs = 0;

        //parses input from command line
        for (int i = 0; i < args.length;) {
            if (args[i].equals("-n")) {
                try {
                    topN = Integer.parseInt(args[i + 1]);
                    i += 2;
                } catch (NumberFormatException e) {
                    System.out.println("Must have integer after '-n'.");
                    System.exit(1);
                }
            } else if (args[i].equals("-r")) {
                i++;
                while (!isCSV(args[i]) && args[i].charAt(0) != '-') {
                    nameArgs[numNameArgs] = args[i];
                    numNameArgs++;
                    if (i + 1 == args.length) {
                        System.out.println("Must include file name.");
                        System.exit(1);
                    }
                    i++;
                }
            } else if (args[i].charAt(0) == '-') {
                System.out.println("bad flag");
                System.exit(1);
            } else {
                fileArgs[numFileArgs] = args[i];
                numFileArgs++;
                i++;
            }
        }

        checkArgs(numFileArgs);

        //constructs and populates ArrayHeap
        ArrayHeap pollData = new ArrayHeap();
        for (int i = 0; i < numFileArgs; i++) {
            readFile(pollData, fileArgs[i]);
            System.out.println(pollData);
            if ((i + 1) < numFileArgs) {
                System.out.println();
            }
        }
        removeCandidates(pollData, nameArgs, numNameArgs);
        if (topN != -1) {
            System.out.println();
            printTopN(pollData.peekTopN(topN), topN);
        }
    }

    /** 
     * Prints a heading and then the top n candidates.
     * @param list - the list to print from
     * @param n - the number of candidates to print
     */
    public static void printTopN(ArrayList list, int n) {
        System.out.println("Top " + list.size() + " Candidates:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    /** 
     * Quits program and prints an error message if the
     * number is not positive.
     * @param i - the number to check
     */
    public static void checkArgs(int i) {
        if (i <= 0) {
            System.out.println("Not enough arguments.");
            System.exit(1);
        }
    }

    /** 
     * Reads in a file and adds all data to an ArrayHeap.
     * Quits if the file cannot be found.
     * @param heap - the heap to which data is added
     * @param filename - the name of the file to be read
     */
    public static void readFile(ArrayHeap heap, String filename) {
        try {
            Scanner reader = new Scanner(new File(filename));
            int date = getDate(filename);
            if (date == -1) {
                System.out.println("File name must begin with \"dempres\".");
                System.exit(1);
            }
            String[] tokens;
            Candidate temp;
            String line;
            reader.nextLine();
            while (reader.hasNextLine()) {
                line = reader.nextLine();
                tokens = line.split(",");
                float pct = Float.parseFloat(tokens[2]);
                temp = new Candidate(tokens[0], tokens[1], pct, date);
                heap.insert(pct, temp);
            }
        } catch (FileNotFoundException e) {
            System.out.println("At least one of those files does not exist.");
            System.exit(1);
        }
    }

    /** 
     * Removes the specified candidates from the heap.
     * @param heap - the heap to be operated upon
     * @param names - an array of names to be removed; may have empty space
     * @param numNames - the number of names in the array
     */
    public static void removeCandidates(ArrayHeap heap,
                                        String[] names, int numNames) {
        for (int i = 0; i < numNames; i++) {
            heap.remove(0, new Candidate(names[i]));
        }
    }

    /** 
     * Uses substring to determine if an argument is a csv file.
     * @param s - the name of the argument
     * @return true if it is a csv, false if not
     */
    public static boolean isCSV(String s) {
        return s.endsWith(".csv");
    }
    
    /** 
     * Parses the date from the name of the file.
     * Supports filenames beginning with paths.
     * Uses constants YEARSTARTINDEX and YEARENDINDEX.
     * @param filename - the name to be parsed
     * @return the date in the form YYYYMMDD
     */
    public static int getDate(String filename) {
        int start = filename.indexOf("dempres");
        if (start == -1) {
            return -1;
        }
        return Integer.parseInt(
            filename.substring(start + YEARSTARTINDEX, start + YEARENDINDEX));
    }
}