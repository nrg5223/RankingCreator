package com.nrg5223.Application.Data.Objects;

/**
 * An object that can be ranked using the MakeRankings program
 */
public abstract class Rankable implements Comparable {
    /** This Rankable's number of points, which is used for comparisons */
    int points = 0;
    /** This Rankable's name, which is shown to the user for comparisons */
    String name;

    public Rankable(String name) {
        this.name = name;
    }

    /**
     * Add a point to this Rankable's points
     */
    public void addPoint() {
        points++;
    };

    /**
     * Get this Rankable's number of points
     *
     * @return points
     */
    public int points() {
        return points;
    };

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
