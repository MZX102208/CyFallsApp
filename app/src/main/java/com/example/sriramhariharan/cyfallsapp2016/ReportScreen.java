package com.example.sriramhariharan.cyfallsapp2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ReportScreen extends AppCompatActivity {


    private static View rootview;
    private TextView justText;
    private Spinner spinner;
    private static ProgressBar prog;
    private ListView yourlistview;
    private ArrayList<Course> classes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_screen);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbarreport);
        myToolbar.setTitle("Report Card");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Course c = Values.assignedcourse;
        TextView course = (TextView)findViewById(R.id.reportscreensclass);
        TextView teach = (TextView)findViewById(R.id.reportscreenteacher);
        TextView roomnum = (TextView)findViewById(R.id.reportscreenroomnum);
        course.setText(c.getName());
        teach.setText(c.getTeacher());
        roomnum.setText(c.getRoomnum());
        yourlistview = (ListView) findViewById(R.id.reportscreenlist);
        yourlistview.setAdapter(new ReportScreenAdapter(getApplicationContext(), c.report));


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
