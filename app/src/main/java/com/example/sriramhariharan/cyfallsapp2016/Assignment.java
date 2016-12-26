package com.example.sriramhariharan.cyfallsapp2016;

/**
 * Created by SriramHariharan on 12/22/16.
 */

public class Assignment{
    String duedate;
    String name;
    String type;
    String grade;
    String totalscore;

    public Assignment(String d,String n,String t,String g,String ts){
        duedate = d;
        name = n;
        type = t;
        grade = g;
        totalscore = ts;
    }

    public String getName() {
        return name;
    }

    public String getDuedate() {
        return duedate;
    }

    public String getGrade() {
        return grade;
    }

    public String getTotalscore() {
        return totalscore;
    }

    public String getType() {
        return type;
    }

    public String toString(){
        String ret = "{"+duedate+"}"+
                "{"+name+"}"+
                "{"+type+"}"+
                "{"+grade+"}"+
                "{"+totalscore+"}";
        return ret;
    }

    @Override
    public boolean equals(Object obj) {
        Assignment assignment = (Assignment)obj;
        if(duedate.equals(assignment.duedate) && name.equals(assignment.name) && type.equals(assignment.type) && grade.equals(assignment.grade) && totalscore.equals(assignment.totalscore)){
            return true;
        }
        else{
            return false;
        }
    }
}