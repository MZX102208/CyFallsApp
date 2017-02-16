package com.example.sriramhariharan.cyfallsapp2016;

import java.security.KeyStore;
import java.util.ArrayList;

import javax.net.ssl.SSLContext;

/**
 * Created by SriramHariharan on 12/22/16.
 */

public class Values {

    public static ClssPkg PKG;
    public static ClssPkg PKGcopy;
    public static int Courseindex;
    public static int periodnum;

    public static ArrayList<Course> courses;
    public static ArrayList<Assignment> assignments;
    public static Course assignedcourse;
    public static ArrayList<Assignment> checked;
    public static String current;
    public static ArrayList<TimSched> firstsem;
    public static ArrayList<TimSched> seconsem;

    public static ArrayList<Course> unemptycourses = new ArrayList<Course>();
    public static ArrayList<Course> hasreportcourses = new ArrayList<Course>();

    public static boolean loaded;
    public static int lunch1;
    public static int lunch2;

    public static KeyStore localTrustStore;
    public static SSLContext sslContext;


}
