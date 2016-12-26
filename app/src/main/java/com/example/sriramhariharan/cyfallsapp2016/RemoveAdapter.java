package com.example.sriramhariharan.cyfallsapp2016;

/**
 * Created by SriramHariharan on 12/22/16.
 */


import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.text.Html;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.util.ArrayList;

public class RemoveAdapter extends BaseAdapter
{
    Context context;
    private ClssPkg courses;
    int index;
    private static LayoutInflater inflater = null;


    public RemoveAdapter (Context contxt,ClssPkg courses, int index) {
        this.courses = courses;
        context = contxt;
        this.index = index;
        Values.checked = new ArrayList<Assignment>();




        inflater = (LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return courses.classes.get(index).assignments.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return  courses.classes.get(index).assignments.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    public class Holder
    {
        TextView assignname;
        TextView assigncategory;
        TextView assigngrade;
        TextView assigntotalgrade;
        LinearLayout assigncolor;
        CheckBox checkbox;
    }



    public View getView(final int position, View convertView, ViewGroup parent)
    {
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.removelayout, null);
        Assignment a = courses.classes.get(index).assignments.get(position);
        holder.assignname = (TextView) rowView.findViewById(R.id.assignname);
        holder.assigncategory = (TextView)rowView.findViewById(R.id.assigncategory);
        holder.assigngrade = (TextView)rowView.findViewById(R.id.assigngrade);
        holder.assigntotalgrade = (TextView)rowView.findViewById(R.id.totalgrade);
        holder.assigncolor = (LinearLayout)rowView.findViewById(R.id.assigncolor);
        holder.checkbox = (CheckBox)rowView.findViewById(R.id.checkboxx);
        String s = a.getName();
        if(s.length()>26){
            s=s.substring(0,26);
            s+="...";
        }

        holder.assignname.setText(s);
        holder.assigncategory.setText(a.getType());
        try {
            if (java.lang.Double.isNaN(Double.parseDouble(a.getGrade()))) {
                holder.assigngrade.setText("-\t");
                holder.assigncolor.setBackgroundColor(Color.parseColor("#2196F3"));
            }
            else

                holder.assigngrade.setText(String.format("%.2f", Double.parseDouble(a.getGrade())));
            holder.assigntotalgrade.setText("/"+a.getTotalscore());

            double colag = Double.parseDouble(a.getGrade());
            double cold= Double.parseDouble(a.getTotalscore());
            double colav = (colag/cold)*100;
            Log.e("TEST",colav+"");

            if(colav >= 90 )
                holder.assigncolor.setBackgroundColor(Color.parseColor("#66BB6A"));
            else if(colav >= 80 )
                holder.assigncolor.setBackgroundColor(Color.parseColor("#FFEB3B"));
            else if(colav >= 70 )
                holder.assigncolor.setBackgroundColor(Color.parseColor("#E65100"));

            else
                holder.assigncolor.setBackgroundColor(Color.parseColor("#BF360C"));


        }
        catch (Exception e){
            Log.e("Error",e.toString());
            holder.assigngrade.setText("X");
            holder.assigncolor.setBackgroundColor(Color.parseColor("#2196F3"));

        }
        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    Values.checked.add(Values.PKGcopy.classes.get(Values.Courseindex).assignments.get(position));
                }
                else{
                    Values.checked.remove(Values.PKGcopy.classes.get(Values.Courseindex).assignments.get(position));
                }
            }
        });

        return rowView;
    }

}
