package com.example.sriramhariharan.cyfallsapp2016;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by SriramHariharan on 12/22/16.
 */

public class Course {
    ArrayList<Assignment> assignments = new ArrayList<Assignment>();
    ArrayList<Type> types = new ArrayList<Type>();
    String name;
    String teacher;
    double grade;
    String email;
    String roomnum;
    String coursenum;
    int periodnum;
    ArrayList<String> report;

    String mp1;
    String mp2;
    String mp3;
    String final1;
    String sem1;

    String mp4;
    String mp5;
    String mp6;
    String final2;
    String sem2;

    public Course(String cour, String t, String e, String r, String cn, int p, String m1, String m2, String m3, String f1, String s1, String m4, String m5, String m6, String f2, String s2){
        teacher = t;
        email = e;
        roomnum = r;
        coursenum = cn;
        periodnum = p;

        mp1 = m1;
        mp2 = m2;
        mp3 = m3;
        final1 = f1;
        sem1 = s1;

        mp4 = m4;
        mp5 = m5;
        mp6 = m6;
        final2 = f2;
        sem2 = s2;

        name = cour;
        report = new ArrayList<String>();
        report.add(mp1);
        report.add(mp2);
        report.add(mp3);
        report.add(final1);
        report.add(sem1);
        report.add(mp4);
        report.add(mp5);
        report.add(mp6);
        report.add(final2);
        report.add(sem2);
    }

    public void addAssign(Assignment a){
        assignments.add(a);
        calcType();
    }

    public String getRoomnum() {
        return roomnum;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Type> getTypes() {
        return types;
    }

    public double getGrade() {
        return grade;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public int getPeriodnum() {
        return periodnum;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void remAssign(int i){
        assignments.remove(i);
    }

    public String getCoursenum() {
        return coursenum;
    }

    public String getFinal1() {
        return final1;
    }

    public String getFinal2() {
        return final2;
    }

    public String getMp1() {
        return mp1;
    }

    public String getMp2() {
        return mp2;
    }

    public String getMp3() {
        return mp3;
    }

    public String getMp4() {
        return mp4;
    }

    public String getMp5() {
        return mp5;
    }

    public String getMp6() {
        return mp6;
    }

    public String getSem1() {
        return sem1;
    }

    public String getSem2() {
        return sem2;
    }

    public void modAssign(int i, double g){
        assignments.get(i).grade = ""+g;
    }

    public void addType(Type t){
        types.add(t);
        calcType();
    }

    public void calcType(){
        double totalgr = 0.0;
        double weightsum = 0.0;
        for(Type t : types){
            double d = 0.0;
            int pd = 0;
            boolean temp = false;
            for(Assignment a : assignments){
                if(a.type.equals(t.name) && !a.grade.equals("X")){
                    temp = true;
                    d+=Double.parseDouble(a.grade);
                    pd+=Double.parseDouble(a.totalscore);
                }
            }
            t.grade = d / pd * t.weight;
            if(temp) {
                totalgr += t.grade;
                weightsum += t.weight;
            }
        }
        grade = totalgr/weightsum*100;
    }

    public String toString(){
        String ret = "{"+name+"}"+
                "{"+teacher+"}"+
                "{"+email+"}"+
                "{"+roomnum+"}"+
                "{"+coursenum+"}"+
                "{"+periodnum+"}"+
                "{"+mp1+"}"+
                "{"+mp2+"}"+
                "{"+mp3+"}"+
                "{"+final1+"}"+
                "{"+sem1+"}"+
                "{"+mp4+"}"+
                "{"+mp5+"}"+
                "{"+mp6+"}"+
                "{"+final2+"}"+
                "{"+sem2+"}";
        ret += assignments.size();
        for(Assignment a : assignments){
            ret+=a.toString();
        }
        ret += "{"+types.size();
        for(Type a : types){
            ret+=a.toString();
        }
        return ret;
    }


    public static Course parse(String str) {
        Scanner s = new Scanner(str);
        s.useDelimiter("\\}\\{|\\}|\\{");
        Course c = new Course(s.next(), s.next(), s.next(), s.next(), s.next(), s.nextInt(), s.next(), s.next(), s.next(), s.next(), s.next(), s.next(), s.next(), s.next(), s.next(), s.next());
        int iter2 = s.nextInt();
        for (int j = 0; j < iter2; j++) {
            Assignment a = new Assignment(s.next(), s.next(), s.next(), s.next(), s.next(),s.next());
            c.addAssign(a);
        }
        iter2 = s.nextInt();
        for (int j = 0; j < iter2; j++) {
            Type a = new Type(s.next(), s.nextDouble(), s.nextDouble(), s.nextDouble());
            c.addType(a);
        }
        return c;
    }


}