package com.example.sriramhariharan.cyfallsapp2016;

/**
 * Created by SriramHariharan on 12/22/16.
 */

public class Type implements Comparable {
    String name;
    double weight;
    double grade;
    double origgrade;

    public Type(String n, double w, double o) {
        name = n;
        weight = w;
        origgrade = o;
    }

    public Type(String n, double w, double g, double o) {
        name = n;
        weight = w;
        grade = g;
        origgrade = o;
    }

    public String getName() {
        return name;
    }

    public double getOriggrade() {
        return origgrade;
    }

    public double getWeight() {
        return weight;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public int compareTo(Object o) {
        Type t = (Type) o;
        if ((int) (t.weight - weight) != 0) return (int) (t.weight - weight);
        else return t.name.compareTo(name);
    }

    public String toString() {
        String ret = "{" + name + "}" +
                "{" + weight + "}" +
                "{" + grade + "}" +
                "{" + origgrade + "}";
        return ret;
    }
}