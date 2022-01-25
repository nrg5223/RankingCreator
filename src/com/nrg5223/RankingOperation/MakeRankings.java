package com.nrg5223.RankingOperation;

import com.nrg5223.Data.Data;
import com.nrg5223.Data.Objects.*;

import java.io.*;
import java.util.Set;

/**
 * A system used to create a ranking of items based on user opinion in the form
 * of votes between every combination of two items from the set
 */
public class MakeRankings {

    /**
     * Create a sorted list to show the rankings based on user opinion of a set
     * of items
     *
     * @param args the name of the text file containing the data on the items to
     *             rank
     * @throws IOException if the file is not found
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: java MakeRankings input-file Type");
        }
        else {
            printBeginMessage();

            String fileWithRankables = args[0];
            Set<Rankable> Rankables = Data.getSetFromFile(fileWithRankables);
            String rankableType = Data.getRankableTypeIn(fileWithRankables);

            CombinationSet combinationSet = new CombinationSet(Rankables.toArray());
            combinationSet.getUserVotesForAll();

            Result result = new Result(Rankables);

            BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
            writer.write(
                    rankableType + "\n" +
                    result.toStringWithData() +
                    "end");
            writer.close();

            printEndMessage();
        }
    }

    /**
     * Print a message to notify the user to begin voting
     */
    private static void printBeginMessage() {
        System.out.println("Begin voting: ");
    }

    /**
     * Print a message to notify the user that the rankings have been made
     */
    private static void printEndMessage() {
        System.out.println("=======================");
        System.out.println("Items have been ranked.");
        System.out.println();
    }
}
