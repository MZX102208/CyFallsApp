package com.example.sriramhariharan.cyfallsapp2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AssignmentScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_screen);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Assignments");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView lv = (ListView)findViewById(R.id.assignlist);
        ArrayList<Assignment> assignments = Values.PKG.classes.get(Values.Courseindex).assignments;
        lv.setAdapter(new AssignmentAdapter(this, assignments));
        lv.setPadding(0, 0, 0, 0);

        for(Assignment a : assignments){
            Log.e("COMMENTS",a.getComment()+"_");
        }

        Course c = Values.assignedcourse;

        View headerview = getLayoutInflater().inflate(R.layout.assignheader,null);
        TextView course = (TextView)headerview.findViewById(R.id.headerclass);
        course.setText(c.getName());
        TextView teach =(TextView)headerview.findViewById(R.id.headerteach);
        teach.setText(c.getTeacher());
        TextView room = (TextView)headerview.findViewById(R.id.roomnumber);
        room.setText("Room Number:"+ " "+ c.getRoomnum());
        TextView email = (TextView)headerview.findViewById(R.id.email);
        email.setText(c.getEmail());
        TextView averagename = (TextView)headerview.findViewById(R.id.averagename);
        TextView averageav = (TextView)headerview.findViewById(R.id.averageav);


        TextView clssgrade = (TextView)headerview.findViewById(R.id.headergade);
        clssgrade.setText(String.format("%.2f",c.getGrade()));
        String s = "";
        String k = "";
        for(Type t:c.getTypes()){
            s+=t.getName()+" Points - \n";
            k+=String.format("%.2f",t.getOriggrade())+"/"+String.format("%.2f",t.getWeight())+"\n";
        }
        averagename.setText(s);
        averageav.setText(k);
        lv.addHeaderView(headerview);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.assignmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case R.id.gradecalc:
                Intent intent = new Intent(getApplicationContext(), GradeCalc.class);
                startActivity(intent);
                return true;
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
