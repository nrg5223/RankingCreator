package com.nrg5223.Application.Data.Objects.on_hold;

import com.nrg5223.Application.Data.Objects.Rankable;

/**
 * An extension of a Rankable that represents a UFC Fighter.  Additional data
 * stored:
 *      the fighter's primary style of fighting
 */
public class UFCFighter extends Rankable {
    /** The fighter's primary style of fighting */
    private final String style;

    public UFCFighter(String name, String style) {
        super(name);
        this.style = style;
    }
}
