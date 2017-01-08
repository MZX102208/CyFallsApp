/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sriramhariharan.cyfallsapp2016;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author User1
 */
public class ClssPkg implements Serializable{

    String username;
    String password;

    String lunch;
    int[] abs = new int[8];
    String absString = "";
    String name;
    ArrayList<Course> classes = new ArrayList<Course>();
    ArrayList<String> schedulesem1 = new ArrayList<String>();
    ArrayList<String> schedulesem2 = new ArrayList<String>();
    //public static final String IP = "99.8.234.29";
    public static final String IP = "173.227.86.24";

    public ClssPkg(String n) {
        name = n;
    }

    public Course get(String n) {
        for (Course c : classes) {
            if ((c.coursenum + " " + c.name).equals(n)) {
                return c;
            }
        }
        return null;
    }

    public int[] getAbs() {
        return abs;
    }

    public String getName() {
        return name;
    }

    public String getLunch() {
        return lunch;
    }

    public ArrayList<Course> getClasses() {
        return classes;
    }

    public String getAbsString() {
        return absString;
    }

    public void addAssign(String n, Assignment a) {
        for (Course c : classes) {
            if (c.name.equals(n)) {
                c.addAssign(a);
            }
        }
    }

    public void addType(String n, Type a) {
        for (Course c : classes) {
            if (c.name.equals(n)) {
                c.addType(a);
            }
        }
    }

    public void addAbsences(String a) {
        absString = a;
        String[] arr = a.split(" ");
        for (int i = 0; i < arr.length; i++) {
            String[] tmp = arr[i].split("-");
            if(tmp.length>=2){
                abs[i] = Integer.parseInt(tmp[1]);
            }
        }
        Log.e("Testing123", Arrays.toString(abs));
    }

    public String toString() {
        String ret = "{" + name + "}";
        ret += classes.size();
        for (Course c : classes) {
            ret += c.toString();
        }
        for (int i = 0; i < abs.length; i++) {
            ret += "{" + abs[i] + "}";
        }
        ret += schedulesem1.size();
        for(String s : schedulesem1){
            ret+="{"+s+"}";
        }
        ret += "{"+schedulesem2.size();
        for(String s : schedulesem2){
            ret+="{"+s+"}";
        }
        return ret;
    }

    public static ClssPkg parse(String str) {
        Scanner s = new Scanner(str);
        s.useDelimiter("\\}\\{|\\}|\\{");
        ClssPkg clss = new ClssPkg(s.next());
        int iter1 = s.nextInt();
        for (int i = 0; i < iter1; i++) {
            Course c = new Course(s.next(), s.next(), s.next(), s.next(), s.next(), s.nextInt(), s.next(), s.next(), s.next(), s.next(), s.next(), s.next(), s.next(), s.next(), s.next(), s.next());
            int iter2 = s.nextInt();
            for (int j = 0; j < iter2; j++) {
                Assignment a = new Assignment(s.next(), s.next(), s.next(), s.next(), s.next(), s.next());
                c.addAssign(a);
            }
            iter2 = s.nextInt();
            for (int j = 0; j < iter2; j++) {
                Type a = new Type(s.next(), s.nextDouble(), s.nextDouble(), s.nextDouble());
                c.addType(a);
            }
            clss.classes.add(c);
        }
        for (int i = 0; i < clss.abs.length; i++) {
            String k = s.next();
       //     Log.e("UNIQUE",i+ " " +clss.abs.length+" "+k);
            clss.abs[i] = Integer.parseInt(k);

        }
        iter1 = s.nextInt();
        for(int i=0;i<iter1;i++){
            clss.schedulesem1.add(s.next());
        }
        iter1 = s.nextInt();
        for(int i=0;i<iter1;i++){
            clss.schedulesem2.add(s.next());
        }
        Log.e("FIRST SEM",clss.schedulesem1.toString());
        Log.e("SECOND SEM",clss.schedulesem2.toString());

        return clss;

    }
    public static ClssPkg getFromServer(String user, String pass, int semnum){
        String s = "";
        try {
            Log.e("TEST","STARTING");
            Socket clientSocket = new Socket(IP, 6789);
            Log.e("Test","Successfull");
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outToServer.writeBytes(user+" "+pass+" "+semnum+'\n');
            Log.e("TEST","wroteBytes");
            String rec = inFromServer.readLine();
            Log.e("TEST","readLine");
            s = rec;
            clientSocket.close();
        } catch (IOException ex) {

        }
        ClssPkg cp = parse(s);
        cp.username = user;
        cp.password = pass;
        return cp;
    }

    public void sendActivityData(String d){
        try {
            Socket clientSocket = new Socket(IP, 6789);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outToServer.writeBytes(username+" "+password+" "+d+'\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}