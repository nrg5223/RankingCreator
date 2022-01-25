package com.nrg5223.Data.Objects;

import com.nrg5223.Data.Objects.Utilities.TimeConverter;

/**
 * An extension of Rankable that represents a song.  It currently stores this
 * extra data:
 *      length of the song in seconds
 *      the release year of the song
 *      whether the song is a feature or not
 */
public class Song extends Rankable {

    /** Extra data for fun (not being used currently) */
    final private int lengthInSeconds;
    final private int releaseYear;
    final private boolean isFeature;
    final private String project;

    /**
     * Constructor for a song
     *
     * @param name name of the song
     * @param length length of the song in seconds
     * @param year release year of the song
     * @param isFeature whether the song is a feature
     */
    public Song(String name, int length, int year, boolean isFeature, String project) {
        super(name);
        this.lengthInSeconds = length;
        this.releaseYear = year;
        this.isFeature = isFeature;
        this.project = project;
    }

    @Override
    public String toStringWithData() {
        StringBuilder str = new StringBuilder();
        str.append(name).append(",");
        str.append(TimeConverter.toFormatted(lengthInSeconds)).append(",");
        str.append(releaseYear).append(",");
        str.append(isFeature).append(",");
        str.append(project);
        return str.toString();
    }
}
