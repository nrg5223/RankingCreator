package com.nrg5223.Data;

import com.nrg5223.Data.Objects.BasicRankable;
import com.nrg5223.Data.Objects.Rankable;
import com.nrg5223.Data.Objects.Song;
import com.nrg5223.Data.Objects.Utilities.TimeConverter;
import com.nrg5223.Data.Objects.on_hold.UFCFighter;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * A class containing methods related to accessing or creating data on this
 * system
 */
public class Data {

    /** The path for the data file directory */
    public final static String DATA_DIR =
            "C:\\Users\\gross\\BackupRankingProject\\data";

    /** The path for the results file directory */
    public final static String RESULTS_DIR =
            "C:\\Users\\gross\\BackupRankingProject\\data\\Results";

    /**
     * Print the names of the files in the given directory excluding the suffix
     *
     * @param directoryName a name of a directory containing data-related files
     */
    public static void showFilesIn(String directoryName) {
        File directory = new File(directoryName);
        File[] files = directory.listFiles();
        assert files != null;
        for (File f : files) {
            if (f.isFile()) {
                String fileName = f.getName();
                fileName = f.getName().substring(0, fileName.length() - 4);
                System.out.println(fileName);
            }
        }
    }

    /**
     * Does the data directory contain a file with the given file name
     *
     * @param fileName the name of the file to find
     * @return true if the file named fileName exists; false otherwise
     */
    public static boolean dirContainsFile(String directoryPath, String fileName) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        assert files != null;
        for (File f : files) {
            String fName = f.getName();
            fName = fName.substring(0, fName.length() - 4);
            if (fName.equals(fileName)) return true;
        }
        return false;
    }

    /**
     * Create the full data file name by adding the directory prefix and the
     * file type suffix to the file name
     *
     * @param fileName: the name of the file excluding prefixes and suffixes
     * @return the full data file name
     */
    public static String createDataFileName(String fileName) {
        return "data/" + fileName + ".txt";
    }

    /**
     * Create the full results file name by adding the directory prefix and the
     * file type suffix to the file name
     *
     * @param fileName: the name of the file excluding prefixes and suffixes
     * @return the full results file name
     */
    public static String createResultsFileName(String fileName) {
        return "data/Results/" + fileName + ".txt";
    }

    /**
     * Get the content of the given file in the form of a string.  This excludes
     * the first and last lines, which are the rankable type and "end",
     * respectively
     *
     * @param fileName the name of a data file or a results file
     * @return the content of the file in the form of a string
     * @throws IOException if file is not found
     */
    public static String contentOf(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader((new FileReader(fileName)));
        StringBuilder str = new StringBuilder();

        reader.readLine(); // skip the first line, which is the rankable type
        String currentLine = reader.readLine();
        while (!currentLine.equals("end")) {
            str.append(currentLine).append("\n");
            currentLine = reader.readLine();
        }
        return str.toString();
    }

    /**
     * Read in items from the text file with the item data, and store them in a
     * set
     *
     * @param fileName the main method argument, which is the text file with the item
     *            data
     * @return the set of all items
     * @throws IOException if the file is not found
     */
    public static Set<Rankable> getSetFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        Set<Rankable> RankableSet = new HashSet<>();

        String rankableType = reader.readLine();
        String currentLine = reader.readLine();
        while (!currentLine.equals("end")) {
            String[] rankableArgs = currentLine.split("[,]");
            if (!itemIsRanked(rankableArgs.length - 1, rankableArgs)) {
                Rankable rankable = instantiateRankable(rankableType, rankableArgs);
                RankableSet.add(rankable);
            }
            currentLine = reader.readLine();
        }

        return RankableSet;
    }

    /**
     * Is the next rankable item unranked?
     *
     * @param i the index of rankableArgs containing the boolean unRanked
     * @param rankableArgs the arguments parsed from the data file representing
     *                     data about that line's rankable item
     * @return true if unranked; false otherwise
     */
    private static boolean itemIsRanked(int i, String[] rankableArgs) {
        return Boolean.parseBoolean(rankableArgs[i]);
    }

    /**
     * Take the data from the next line, and instantiate a new extension of
     * Rankable depending on the type of data
     *
     * @param type the type of extension of Rankable
     * @param rankableArgs the arguments needed for the extension's constructor
     * @return the instantiated extension of Rankable
     */
    private static Rankable instantiateRankable(String type, String[] rankableArgs) {
        Rankable rankable = switch (type) {
            case "Song" -> new Song(
                    rankableArgs[0],
                    TimeConverter.toSeconds(rankableArgs[1]),
                    Integer.parseInt(rankableArgs[2]),
                    Boolean.parseBoolean(rankableArgs[3]),
                    rankableArgs[4],
                    Boolean.parseBoolean(rankableArgs[5]));
            case "UFCFighter" -> new UFCFighter(
                    rankableArgs[0],
                    rankableArgs[1],
                    Boolean.parseBoolean(rankableArgs[2]));
            case "Basic" -> new BasicRankable(
                    rankableArgs[0],
                    Boolean.parseBoolean(rankableArgs[1]));
            default -> throw new IllegalArgumentException("Object type must match exactly");
        };
        return rankable;
    }

    /**
     * Get a string representing the type of rankable stored in the file
     *
     * @param fileName the name of a file with a list of rankables and their
     *                 related data
     * @return the string representing the type of rankable stored in the file
     * @throws IOException if the file is not found
     */
    public static String getRankableTypeIn(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader((new FileReader(fileName)));

        return reader.readLine();
    }

    /**
     * Create a new file, take user input for entries, and write the entries to
     * the new file.
     *
     * @param in the scanner that gets the user input
     * @param newFileName the name of the new file to write to
     * @throws IOException if file is not found
     */
    public static void createAndWriteNewFile(Scanner in, String newFileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(Data.createDataFileName(newFileName)));
        System.out.print("Enter Rankable Type: ");
        String rankableType = in.nextLine().split(" ")[0];
        writer.write(rankableType + "\n");

        printInstructions();
        String line = in.nextLine();
        while (!line.equals("end")) {
            writer.write(line + ",false" + "\n"); // isRanked
            System.out.println();
            line = in.nextLine();
        }
        writer.write(line);
        writer.close();

        String message = newFileName + " has been created.";
        System.out.println(message);
    }

    // TODO: this code is reused in createAndWriteNewFile and addDataTo, but it
    //    does not work when implemented as a method
    /**
     * Get input from the user.  The during this operation, the user enters data
     * line by line, via System.in, to be stored in a data file
     *
     * @param in the scanner which reads the user input
     * @param writer the writer which write the user's input to a file
     * @throws IOException if the file is not found
     */
    private static void getUserInput(Scanner in, BufferedWriter writer) throws IOException {
        printInstructions();
        String line = in.nextLine();
        while (!line.equals("end")) {
            writer.write(line + ",false" + "\n"); // isRanked
            System.out.println();
            line = in.nextLine();
        }
        writer.write(line);
    }

    /**
     * Get new input data from the user and add it to the given file
     *
     * @param in the scanner that reads input from the user
     * @param fileName the name of the file to add to (excluding pre/suffixes)
     * @throws IOException if the file is not found
     */
    public static void addDataTo(Scanner in, String fileName) throws IOException {
        String fullFileName = Data.createDataFileName(fileName);
        String existingContent = getRankableTypeIn(fullFileName) + "\n" +
                                 contentOf(fullFileName);
        deleteDataFile(fullFileName);

        BufferedWriter writer = new BufferedWriter(new FileWriter(fullFileName));
        writer.write(existingContent);

        printInstructions();
        String line = in.nextLine();
        while (!line.equals("end")) {
            writer.write(line + ",false" + "\n"); // isRanked
            System.out.println();
            line = in.nextLine();
        }
        writer.write(line);
        writer.close();

        String message = fileName + " has been updated.";
        System.out.println(message);
    }

    public static void rewriteFileAfterVoting(String fullFileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fullFileName));

        StringBuilder newContent = new StringBuilder();
        String rankableType = reader.readLine();
        newContent.append(rankableType);

        String currentLine = reader.readLine();
        while (!currentLine.equals("end")) {
            String updatedLine = updateIsRankedOnLine(currentLine);
            newContent.append(updatedLine).append("\n");

            currentLine = reader.readLine();
        }
        newContent.append("end");

        deleteDataFile(fullFileName);

        BufferedWriter writer = new BufferedWriter(new FileWriter(fullFileName));
        writer.write(newContent.toString());
        writer.close();
    }

    /**
     * Change the last argument on the line to "true" whether it is false or
     * true. The purpose of this method is to rewrite data files to reflect
     * when they have been used to make rankings; hence isRanked becomes true
     *
     * @param currentLine a line in a data file
     * @return the line with the last argument updated to "true"
     */
    private static String updateIsRankedOnLine(String currentLine) {
        String[] rankableArgs = currentLine.split("[,]");
        int lastIndex = rankableArgs.length - 1;
        rankableArgs[lastIndex] = "true";
        StringBuilder updatedRankableArgs = new StringBuilder();
        for (int i = 0; i < lastIndex; i++) {
            updatedRankableArgs.append(rankableArgs[i]).append(",");
        }
        updatedRankableArgs.append(rankableArgs[lastIndex]);
        return updatedRankableArgs.toString();
    }

    /**
     * Delete the file from the system
     *
     * @param fileName the name of the file to be deleted,
     *                 excluding pre/suffixes
     */
    public static void deleteDataFile(String fileName) {
        // TODO: if (!isBackupFile(fileName)) { ... something like this

        fileName = cutPreSuffixesOffFile(fileName);

        File directory = new File(Data.DATA_DIR);
        File[] files = directory.listFiles();
        assert files != null;
        for (File f : files) {
            String fName = f.getName();
            fName = fName.substring(0, fName.length() - 4);
            if (fName.equals(fileName)) {
                f.delete();
                break;
            }
        }
    }

    /**
     * Get the given filename excluding pre/suffixes
     *
     * @param fileName the name of a file including the pre/suffixes
     * @return the updated filename
     */
    private static String cutPreSuffixesOffFile(String fileName) {
        int prefixLength = 0;
        if (fileName.startsWith("data/")) {
            prefixLength = "data/".length();
        }
        if (fileName.startsWith("results/")) {
            prefixLength = "results/".length();
        }
        int suffixLength = ".txt".length();
        int fileNameLength = fileName.length();

        return fileName.substring(prefixLength, fileNameLength - suffixLength);
    }

    /**
     * Print instructions for how to enter data to a data file
     */
    private static void printInstructions() {
        System.out.println("Enter data line by line.  Separate fields by comma.");
        System.out.println("Example: ");
        System.out.println("High For This,4:07,2012,false,Trilogy");
        System.out.println("Enter 'end' to finish.");
        System.out.println();
    }
}
