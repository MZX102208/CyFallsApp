package com.example.sriramhariharan.cyfallsapp2016;

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

/**
 * Created by SriramHariharan on 3/11/16.
 */
public class CourseAdapter extends BaseAdapter {

    Context context;
    private ArrayList<Course> courses;
    private static LayoutInflater inflater = null;

    public CourseAdapter (Context contxt,ArrayList<Course> crs) {
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
        TextView teacher;
        TextView grade;
        TextView gradeim;
        LinearLayout color;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        DecimalFormat df = new DecimalFormat("#.##");
        rowView = inflater.inflate(R.layout.rowlayout, null);
        Course c = courses.get(position);
        holder.clssname=(TextView) rowView.findViewById(R.id.period);
        holder.teacher=(TextView) rowView.findViewById(R.id.teachedr);
        holder.grade = (TextView) rowView.findViewById(R.id.grade);
        holder.color = (LinearLayout)rowView.findViewById(R.id.colorcoding);
        holder.clssname.setText(courses.get(position).getName());
        holder.teacher.setText(courses.get(position).getTeacher());

        if(java.lang.Double.isNaN(courses.get(position).getGrade()))
            holder.grade.setText("-\t");
        else {
            Log.e("Michael_Grade",courses.get(position).getName());
            holder.grade.setText(String.format("%.2f", courses.get(position).getGrade()) + "");
        }


        if(Math.round(courses.get(position).getGrade()) >= 90 )
            holder.color.setBackgroundColor(Color.parseColor("#66BB6A"));
        else if(Math.round(courses.get(position).getGrade()) >= 80 )
            holder.color.setBackgroundColor(Color.parseColor("#FFEB3B"));
        else if(Math.round(courses.get(position).getGrade()) >= 70 )
            holder.color.setBackgroundColor(Color.parseColor("#E65100"));
        else if(java.lang.Double.isNaN(courses.get(position).getGrade())){
            holder.color.setBackgroundColor(Color.parseColor("#2196F3"));
        }
        else
            holder.color.setBackgroundColor(Color.parseColor("#BF360C"));


        return rowView;
    }


}
