package com.nrg5223.RankingOperation;

import com.nrg5223.Data.Objects.Rankable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The resulting ranking of items after the user votes on them
 */
public class Result {
    List<Rankable> sortedList;
    List<Rankable[]> sortedListShowingTies;

    /**
     * Construct the result of a voting of items by the user
     *
     * @param rankables the set of Rankables after they are given point scores
     */
    public Result(Set<Rankable> rankables) {
        sortedList = sortedList(rankables);
        sortedListShowingTies = sortedListShowingTies();
    }

    /**
     * If not already done, create a sorted list of the rankables based on their
     * point scores.  Return the list
     *
     * @return the sorted list
     */
    private List<Rankable> sortedList(Set<Rankable> rankables) {
        if (sortedList != null) return sortedList;

        List<Rankable> list = new ArrayList<>(rankables);
        Collections.sort(list);

        return list;
    }

    /**
     * If not already done, create a sorted list that shows where ties occurred
     * in the rankings.  Return the list
     *
     * @return a sorted list of Rankable[]
     */
    public List<Rankable[]> sortedListShowingTies() {
        if (sortedListShowingTies != null) return sortedListShowingTies;

        List<Rankable[]> sortedWithTies = new ArrayList<>();

        for (int i = 0; i < sortedList.size(); i++) {
            if (hasTieAt(i)) {
                sortedWithTies.add(getTiedItemsFrom(i));
                i += numTiedItemsAfter(i);
            }
            else {
                sortedWithTies.add(new Rankable[] {sortedList.get(i)});
            }
        }

        return sortedWithTies;
    }

    /**
     * Is there a tie between the item at index i and the following item in the
     * sorted list
     *
     * @param i the index in the sorted list of the Rankable to check
     * @return true if there is a tie; false otherwise
     */
    private boolean hasTieAt(int i) {
        return i + 1 < sortedList.size() &&
                sortedList.get(i).points() == sortedList.get(i + 1).points();
    }

    /**
     * Get the number of items following the item at index i that tie with it
     * based on point scores
     *
     * @param i the index in the sorted list of the Rankable to check
     * @return the number of following items that tie with the item at index i
     */
    private int numTiedItemsAfter(int i) {
        int numTiedItems = 0;
        while (hasTieAt(i)) {
            i++;
            numTiedItems++;
        }

        return numTiedItems;
    }

    /**
     * Get the tied items starting at index i
     *
     * @param i the index of the first item in a tied section of the sorted list
     * @return a native array of the tied items
     */
    private Rankable[] getTiedItemsFrom(int i) {
        int numTiedItems = 1 + numTiedItemsAfter(i);
        Rankable[] tiedItems = new Rankable[numTiedItems];

        int count = 0;
        while (hasTieAt(i + count)) {
            tiedItems[count] = sortedList.get(i + count);
            count++;
        }
        tiedItems[count] = sortedList.get(i + count);

        return tiedItems;
    }

    /**
     * The string representation of this Result, but with two additional comma-
     * separated values preceding the name of each item: points and whether it
     * is a tie.  This method is used specifically for writing the data to a txt
     * file.
     *
     * @return the string representing this Result with the modification
     */
    public String toStringWithData() {
        StringBuilder str = new StringBuilder();
        for (Rankable[] rankableArr : sortedListShowingTies()) {

            for (Rankable r : rankableArr) {
                str.append(r.toStringWithData()).append(",");
                str.append(r.points()).append(",");
                if (rankableArr.length > 1) {
                    str.append("true");
                }
                else {
                    str.append("false");
                }
                str.append("\n");
            }
        }
        return str.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Rankable[] rankableArr : sortedListShowingTies()) {
            int i = 0;
            for (; i + 1 < rankableArr.length; i++) {
                str.append(rankableArr[i]).append(" = ");
            }
            str.append(rankableArr[i]).append("\n");
        }
        return str.toString();
    }
}
