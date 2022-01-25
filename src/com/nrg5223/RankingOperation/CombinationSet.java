package com.nrg5223.RankingOperation;

import com.nrg5223.Data.Objects.Rankable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * A class used for showing song combinations to the user for them to compare
 * and give input on
 */
public class CombinationSet {
    private Object[] items;
    private Set<Object[]> completeCombinationSet = new HashSet<>();

    /**
     * Constructor for a Comparer object
     *
     * @param items a native array of objects
     */
    public CombinationSet(Object[] items) {
        this.items = items;
        fillCombinationSet();
    }

    /**
     * Fill the combination set with all combinations of 2 objects in the
     * objects set.  A combination is modeled by an array of size 2
     */
    private void fillCombinationSet() {
        for (int i = 0; i < items.length; i++) {
            for (int j = i + 1; j < items.length; j++) {
                completeCombinationSet.add(new Object[] {items[i], items[j]});
            }
        }
    }

    /**
     * Add and vote on new items
     *
     * @param newItems an array of new rankables
     */
    public void add(Object[] newItems) {
        Set<Object[]> newCombinationSet = new HashSet<>();
        for (Object newItem : newItems) {
            for (Object item : items) {
                newCombinationSet.add(new Object[] {item, newItem});
            }
        }
        getUserVotesFor(newCombinationSet);

        int previousMaxIndex = items.length;
        for (Object newItem : newItems) {
            items[previousMaxIndex++] = newItem;
        }
    }

    /**
     * Show all songs to the user, and record their votes by adding points to
     * the winning songs
     * @param setOfCombinations a set of the combinations to use for voting
     */
    private void getUserVotesFor(Set<Object[]> setOfCombinations) {
        Scanner scanner = new Scanner(System.in);

        for (Object[] combo : setOfCombinations) {
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
     * Get the user's votes for all possible combinations of Rankables
     */
    public void getUserVotesForAll() {
        getUserVotesFor(completeCombinationSet);
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
