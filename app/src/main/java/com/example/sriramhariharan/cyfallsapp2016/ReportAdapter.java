package com.example.sriramhariharan.cyfallsapp2016;

/**
 * Created by SriramHariharan on 12/23/16.
 */

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sriramhariharan.cyfallsapp2016.ClssPkg;
import com.example.sriramhariharan.cyfallsapp2016.Course;
import com.example.sriramhariharan.cyfallsapp2016.R;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.PropertyPermission;

/**
 * Created by SriramHariharan on 3/11/16.
 */
public class ReportAdapter extends BaseAdapter {

    Context context;
    private ArrayList<Course> courses;
    private static LayoutInflater inflater = null;

    public ReportAdapter (Context contxt,ArrayList<Course> crs) {
        courses = crs;
        context = contxt;
        inflater = (LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return courses.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return courses.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView clssname;
        TextView coursenum;
        TextView finalg;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        DecimalFormat df = new DecimalFormat("#.##");
        rowView = inflater.inflate(R.layout.rowreportlayout, null);
        Course c = courses.get(position);
        holder.clssname = (TextView) rowView.findViewById(R.id.reportname);
        holder.coursenum = (TextView)rowView.findViewById(R.id.coursenum);
        String s = c.getName();
        if(s.length()>11){
            s=s.substring(0,11);
        }
        holder.clssname.setText(s);
        holder.coursenum.setText(c.getCoursenum());

        /*
        String f1 = c.getFinal1();
        String f2 = c.getFinal2();
        if(f1.equals(" ")){
            f1 = "__";
        }
        if(f2.equals(" ")){
            f2 = "__";
        }
        holder.finalg.setText(f1+" | "+f2);


*/




   //     holder.clssname.setText(c.getName());
   //         Log.e("Michael_Grade",courses.get(position).getName());
     //       holder.grade.setText(String.format("%.2f", courses.get(position).getGrade()) + "");


        return rowView;
    }


}
