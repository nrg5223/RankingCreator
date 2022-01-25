package com.nrg5223.Application.Data;

public abstract class Line {
    private String content;

    /*
        My idea is an object to represent a line in a .txt file
        It could be abstract or an interface and extended/implemented by
        -Type (the type of data, which will be one word and the start of every
              line in a data or result file).  This would also help solve the
              problem of having to type the type of rankable in as a 3rd arg
              everytime you call the r(ank) command
        -Rankable (the line representing the rankable including name and all
                   unique characteristics/fields)
        -End (the line which is "end")
     */
    public Line(String content) {

    }
}
