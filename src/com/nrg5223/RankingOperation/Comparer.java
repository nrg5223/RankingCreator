package com.nrg5223.RankingOperation;

import com.nrg5223.Application.Data.Objects.Rankable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * A class used for showing song combinations to the user for them to compare
 * and give input on
 */
public class Comparer {
    Object[] objects;
    Set<Object[]> combinationSet = new HashSet<>();

    /**
     * Constructor for a Comparer object
     *
     * @param objects a native array of objects
     */
    public Comparer(Object[] objects) {
        this.objects = objects;
    }

    /**
     * Fill the combination set with all combinations of 2 objects in the
     * objects set.  A combination is modeled by an array of size 2
     */
    public void fillCombinationSet() {
        for (int i = 0; i < objects.length; i++) {
            for (int j = i + 1; j < objects.length; j++) {
                combinationSet.add(new Object[] {objects[i], objects[j]});
            }
        }
    }

    /**
     * Show all songs to the user, and record their votes by adding points to
     * the winning songs
     */
    public void getUserVotes() {
        Scanner scanner = new Scanner(System.in);

        for (Object[] combo : combinationSet) {
            System.out.println(Arrays.toString(combo));

            String vote = scanner.nextLine();
            while (!isValid(vote)) {
                System.out.println("Enter '0' or '1'");
                vote = scanner.nextLine();
            }

            int index = Integer.parseInt(vote);
            Rankable winner = (Rankable)combo[index];
            winner.addPoint();
        }
    }

    /**
     * Is the given vote valid?
     *
     * @param vote a string representing a vote
     * @return true if vote is "0" or "1"; false otherwise
     */
    public boolean isValid(String vote) {
        return vote != null && (vote.equals("0") || vote.equals("1"));
    }
}
