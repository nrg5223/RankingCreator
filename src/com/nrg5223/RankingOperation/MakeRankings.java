package com.nrg5223.RankingOperation;

import com.nrg5223.Application.Data.Data;
import com.nrg5223.Application.Data.Objects.*;

import java.io.*;
import java.util.Set;

/**
 * A system used to create a ranking of items based on user opinion in the form
 * of votes between every combination of two items from the set
 */
public class MakeRankings {
    /*
    Requirements:
    - 3+ categorized sets String songName (hardcoded)
    - Write the songs to a file in the computer
        - (research)
    - PTUI - have not thought of functionality yet (workflow, etc)
    - GUI ?
        - see ranked (sorted) list of songs
        - choose a song to move up in rankings (move up x spaces)
        - see the 3+ groupings and move songs within those too
        - be able to compare songs on the GUI
            - (more thinking)
    - don't know if this requirements list is complete
    */

    /**
     * Create a sorted list to show the rankings based on user opinion of a set
     * of items
     *
     * @param args the name of the text file containing the data on the items to
     *             rank
     * @throws IOException if the file is not found
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.err.println("Usage: java MakeRankings input-file Type");
        }
        else {
            printBeginMessage();

            Set<Rankable> Rankables = Data.getSetFromFile(args[0], args[2]);

            Comparer comparer = new Comparer(Rankables.toArray());
            comparer.fillCombinationSet();
            comparer.getUserVotes();

            Result result = new Result(Rankables);

            BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
            writer.write(result.toStringWithData() + "end");
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
