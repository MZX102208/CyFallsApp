package com.example.sriramhariharan.cyfallsapp2016;

/**
 * Created by SriramHariharan on 12/23/16.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
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
public class ReportScreenAdapter extends BaseAdapter {

    Context context;
    private ArrayList<String> report;
    private static LayoutInflater inflater = null;

    public ReportScreenAdapter (Context contxt,ArrayList<String> crs) {
        report = crs;
        context = contxt;
        inflater = (LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return report.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return report.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView grade;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        DecimalFormat df = new DecimalFormat("#.##");
        rowView = inflater.inflate(R.layout.reportscreenrowlayout, null);
        holder.grade = (TextView)rowView.findViewById(R.id.mp1);

        String s = "";
        if(position == 0){
            s="1st six weeks: ";
        }
        if(position == 1){
            s="2nd six weeks: ";
        }
        if(position == 2){
            s="3rd six weeks: ";
        }
        if(position == 3){
            s="Exam 1: ";
            holder.grade.setTypeface(null, Typeface.BOLD_ITALIC);
        }
        if(position == 4){
            s="Semester 1: ";
            holder.grade.setTypeface(null, Typeface.BOLD_ITALIC);
        }
        if(position == 5){
            s="4th six weeks: ";
        }
        if(position == 6){
            s="5th six weeks: ";
        }
        if(position == 7){
            s="6th six weeks: ";
        }
        if(position == 8){
            s="Exam 2: ";
            holder.grade.setTypeface(null, Typeface.BOLD);

        }
        if(position == 9){
            s="Semester 2: ";
            holder.grade.setTypeface(null, Typeface.BOLD_ITALIC);
        }
        String l = report.get(position);
        if(l.equals(" ")){
            l = "-";
        }
        holder.grade.setText(s+l);

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
