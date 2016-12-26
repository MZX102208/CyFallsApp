package com.example.sriramhariharan.cyfallsapp2016;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SriramHariharan on 3/30/16.
 */
public class ScheduleAdapter extends BaseAdapter{
    Context context;
    private ArrayList<TimSched> times;
    private static LayoutInflater inflater = null;

    public ScheduleAdapter (Context contxt,ArrayList<TimSched> tim) {
        times = tim;
        context = contxt;

        inflater = (LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return times.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return  times.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    public class Holder
    {
        TextView periodname;
        TextView periodtime;
        TextView periodroom;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.schedulerowlayout, null);
        holder.periodname = (TextView) rowView.findViewById(R.id.theperiod);
        holder.periodtime = (TextView)rowView.findViewById(R.id.thetime);
        holder.periodroom = (TextView)rowView.findViewById(R.id.roomnumber);
        holder.periodname.setText(times.get(position).getPeriod());
        holder.periodtime.setText(times.get(position).getTime());
        holder.periodroom.setText(times.get(position).getRoomnumb());

        return rowView;
    }
}
