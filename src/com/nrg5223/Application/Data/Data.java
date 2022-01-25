package com.nrg5223.Application.Data;

import com.nrg5223.Application.Data.Objects.BasicRankable;
import com.nrg5223.Application.Data.Objects.Rankable;
import com.nrg5223.Application.Data.Objects.Song;
import com.nrg5223.Application.Data.Objects.Utilities.TimeConverter;
import com.nrg5223.Application.Data.Objects.on_hold.UFCFighter;

import java.io.*;
import java.util.HashSet;
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
     * Get the content of the given file in the form of a string
     *
     * @param fileName the name of a data file or a results file
     * @return the content of the file in the form of a string
     * @throws IOException if file is not found
     */
    public static String contentOf(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader((new FileReader(fileName)));
        StringBuilder str = new StringBuilder();

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
     * @param arg the main method argument, which is the text file with the item
     *            data
     * @return the set of all items
     * @throws IOException if the file is not found
     */
    public static Set<Rankable> getSetFromFile(String arg, String rankableType) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(arg));
        Set<Rankable> RankableSet = new HashSet<>();

        String currentLine = reader.readLine();
        while (!currentLine.equals("end")) {
            String[] rankableArgs = currentLine.split("[,]");
            Rankable rankable = instantiateRankable(rankableType, rankableArgs);
            RankableSet.add(rankable);

            currentLine = reader.readLine();
        }

        return RankableSet;
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
        Rankable rankable;
        if (type.equals("Song")) {
            rankable = new Song(rankableArgs[0],
                    TimeConverter.toSeconds(rankableArgs[1]),
                    Integer.parseInt(rankableArgs[2]),
                    Boolean.parseBoolean(rankableArgs[3]),
                    rankableArgs[4]);
        }
        else if (type.equals("UFCFighter")) {
            rankable = new UFCFighter(rankableArgs[0], rankableArgs[1]);
        }
        else if (type.equals("Basic")) {
            rankable = new BasicRankable(rankableArgs[0]);
        }
        else {
            throw new IllegalArgumentException("Object type must match exactly");
        }
        return rankable;
    }
}
