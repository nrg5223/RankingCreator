package com.nrg5223.Application.Data.Objects.on_hold;

import com.nrg5223.Application.Data.Objects.Song;

import java.util.Arrays;
import java.util.Set;

/**
 * A tier level for loosely grouping songs by opinion
 */
public class Tier {
    private Set<Song> songs;

    /**
     * Constructor for Tier
     *
     * @param songs a list of songs to be added to the tier
     */
    public Tier(Song ... songs) {
        this.songs.addAll(Arrays.asList(songs));
    }

    public Set<Song> songs() {
        return songs;
    }
}
