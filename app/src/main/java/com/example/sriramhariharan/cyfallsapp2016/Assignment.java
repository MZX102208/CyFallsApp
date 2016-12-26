package com.example.sriramhariharan.cyfallsapp2016;

/**
 * Created by SriramHariharan on 12/22/16.
 */

class Assignment {

    String duedate;
    String name;
    String type;
    String grade;
    String totalscore;
    String comment = "";

    public Assignment(String d, String n, String t, String g, String ts, String comment) {
        duedate = d;
        name = n;
        type = t;
        grade = g;
        totalscore = ts;
    }

    public String toString() {
        String ret = "{" + duedate + "}"
                + "{" + name + "}"
                + "{" + type + "}"
                + "{" + grade + "}"
                + "{" + totalscore + "}"
                + "{" + comment + "}";
        return ret;
    }

}