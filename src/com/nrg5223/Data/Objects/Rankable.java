package com.nrg5223.Data.Objects;

/**
 * An object that can be ranked using the MakeRankings program
 */
public abstract class Rankable implements Comparable {
    /** This Rankable's number of points, which is used for comparisons */
    int points = 0;
    /** This Rankable's name, which is shown to the user for comparisons */
    String name;
    /** Has this rankable been ranked already? */
    boolean isRanked;

    public Rankable(String name, Boolean isRanked) {
        this.name = name;
        this.isRanked = isRanked;
    }

    /**
     * Add a point to this Rankable's points
     */
    public void addPoint() {
        points++;
    };

    public int points() {
        return points;
    };

    public Boolean isRanked() {
        return isRanked;
    }

    /**
     * Create a string representation of this Rankable that includes all
     * additional data it contains
     *
     * Note: this is meant to be overridden by extensions of Rankable
     *
     * @return the string representation of this Rankable with modifications
     */
    public String toStringWithData() {
        return toString();
    };

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Rankable)) {
            throw new ClassCastException();
        }
        Rankable other = (Rankable)o;
        return other.points() - this.points;
    }

    @Override
    public String toString() {
        return name;
    }
}
