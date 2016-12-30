package com.example.sriramhariharan.cyfallsapp2016;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GradeCalc extends AppCompatActivity {

    public static boolean removable = false;
    public static boolean adding = true;
    public static List<String> types;
    public static CalcAdapter ca;
    public static Dialog dialog;
    public static Type tr;
    public static EditText editgrade;
    public static EditText editname;
    public static EditText edittotal;
    public static boolean editable = false;
    public static LinearLayout calcheader;
    public static LinearLayout addlin;
    public static LinearLayout buttonheader;
    ArrayList<Assignment> assignments;
    public static Course c;
    public static TextView clssgrade;
    public static TextView averagename;
    public static TextView averageav;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_calc);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Grade Calculator");
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        c = Values.PKGcopy.classes.get(Values.Courseindex);
        assignments = c.assignments;

        ListView lv = (ListView) findViewById(R.id.calgradelist);
        TextView course = (TextView) findViewById(R.id.calcclass);
        course.setText(c.getName());

        averagename = (TextView) findViewById(R.id.calcaveragename);
        averageav = (TextView) findViewById(R.id.calcaverageav);

        clssgrade = (TextView) findViewById(R.id.calcgrade);
        clssgrade.setText(String.format("%.2f", c.getGrade()));

        String s = "";
        String k = "";
        for (Type t : c.getTypes()) {
            s += t.getName() + " Points - \n";
            k += String.format("%.2f", t.getOriggrade()) + "/" + String.format("%.2f", t.getWeight()) + "\n";
        }
        averagename.setText(s);
        averageav.setText(k);

        Button add = (Button) findViewById(R.id.add);
        Button remove = (Button) findViewById(R.id.remove);
        Button reset = (Button) findViewById(R.id.reset);


        types = new ArrayList<String>();
        for (Type t : c.getTypes()) {
            types.add(t.getName());
        }


        calcheader = (LinearLayout) findViewById(R.id.CALCHEADER);
        //     addlin = (LinearLayout) findViewById(R.id.addlin);
        buttonheader = (LinearLayout) findViewById(R.id.buttonheader);
        //     addlin.setVisibility(View.GONE);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removable = false;
                editable = false;
                adding = true;
                //               addlin.setVisibility(View.VISIBLE);
                //               buttonheader.setVisibility(View.GONE);
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.customdialog);
                dialog.setTitle("Add");

                // set the custom dialog components - text, image and button
                dialog.show();
                editgrade = (EditText) dialog.findViewById(R.id.addgrade);
                editname = (EditText) dialog.findViewById(R.id.addname);
                edittotal = (EditText) dialog.findViewById(R.id.addtotal);

                int num = 1;
                for (Assignment a : assignments) {
                    if (a.getName().contains("Assignment")) {
                        num++;
                    }
                }
                editname.setText("Assignment " + num);
                Spinner spinner = (Spinner) dialog.findViewById(R.id.typespinner);
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, R.layout.dropdowntext, types);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(dataAdapter);
                dataAdapter.notifyDataSetChanged();
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        for (Type t : c.getTypes()) {
                            if (t.getName().equals(types.get(position))) {
                                tr = t;
                            }
                        }
                    }


                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                Button stop = (Button) dialog.findViewById(R.id.addstopbutton);

                Button addBUTTONGO = (Button) dialog.findViewById(R.id.addbutton);
                addBUTTONGO.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.hide();
                        String type = "";
                        type = tr.getName();
                        if (tr == null) {
                            type = "none";
                        }
                        try {
                            Double.parseDouble(edittotal.getText().toString());

                        } catch (Exception e) {
                            edittotal.setText("100");

                        }

                        try {
                            Double.parseDouble(editgrade.getText().toString());
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Please enter in a value for Grade",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }
                        String comments = "";

                        assignments.add(0, new Assignment("", editname.getText().toString(), type, editgrade.getText().toString(), edittotal.getText().toString(),""));
                        ca.notifyDataSetChanged();
                        update();
                        int num = 1;
                        for (Assignment a : assignments) {
                            if (a.getName().contains("Assignment")) {
                                num++;
                            }
                        }
                        editname.setText("Assignment " + num);
                    }


                });

                stop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.hide();
                    }
                });

            }
        });


        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removable = true;
                assignments = Values.PKGcopy.classes.get(Values.Courseindex).assignments;
                LinearLayout removeline = (LinearLayout) findViewById(R.id.removeline);
                //       removeline.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), Remove.class);
                startActivityForResult(intent, RESULT_OK);


            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removable = false;
                c = Course.parse(Values.PKG.classes.get(Values.Courseindex).toString());
                Values.PKGcopy.classes.set(Values.Courseindex, c);
                assignments = Values.PKGcopy.classes.get(Values.Courseindex).assignments;
//                addlin.setVisibility(View.GONE);
                ca.notifyDataSetChanged();
                update();
                if (dialog != null) {
                    dialog.hide();
                }
            }
        });




        ca = new CalcAdapter(this, Values.PKGcopy, Values.Courseindex);
        lv.setAdapter(ca);
        lv.setPadding(0, 0, 0, 0);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.customdialog);
                dialog.setTitle("Edit");
                Log.e("HELLO", "HELLO");
                // set the custom dialog components - text, image and button
                dialog.show();
                editgrade = (EditText) dialog.findViewById(R.id.addgrade);
                editname = (EditText) dialog.findViewById(R.id.addname);
                edittotal = (EditText) dialog.findViewById(R.id.addtotal);
                Button stop = (Button) dialog.findViewById(R.id.addstopbutton);

                Button addBUTTONGO = (Button) dialog.findViewById(R.id.addbutton);
                addBUTTONGO.setText("Save");
                editname.setText(assignments.get(position).getName());
                editgrade.setText(assignments.get(position).getGrade());
                edittotal.setText(assignments.get(position).getTotalscore());
                Spinner spinner = (Spinner) dialog.findViewById(R.id.typespinner);
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, R.layout.dropdowntext, types);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(dataAdapter);
                dataAdapter.notifyDataSetChanged();

                int index = types.indexOf(assignments.get(position).getType());
                spinner.setSelection(index);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        for (Type t : c.getTypes()) {
                            if (t.getName().equals(types.get(position))) {
                                tr = t;
                            }
                        }
                    }


                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                addBUTTONGO.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.hide();
                        String type = "";
                        type = tr.getName();
                        if (tr == null) {
                            type = "none";
                        }
                        try {
                            Double.parseDouble(edittotal.getText().toString());

                        } catch (Exception e) {
                            edittotal.setText("100");

                        }

                        try {
                            Double.parseDouble(editgrade.getText().toString());
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Please enter in a value for Grade",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }
                        String commnets = "";
                        assignments.set(position, new Assignment("", editname.getText().toString(), type, editgrade.getText().toString(), edittotal.getText().toString(),commnets));
                        ca.notifyDataSetChanged();
                        update();
                        int num = 1;
                        for (Assignment a : assignments) {
                            if (a.getName().contains("Assignment")) {
                                num++;
                            }
                        }

                        editname.setText("Assignment " + num);
                    }


                });

                stop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.hide();
                    }
                });

            }
        });
        SendData sd = new SendData("entercalc");
        sd.execute();
    }


    public static void update() {
        c = Values.PKGcopy.classes.get(Values.Courseindex);
        c.calcType();
        clssgrade.setText(String.format("%.2f", c.getGrade()));
        String s = "";
        String k = "";
        for (Type t : c.getTypes()) {
            s += t.getName() + " Points - \n";
            k += String.format("%.2f", t.getGrade()) + "/" + String.format("%.2f", t.getWeight()) + "\n";
        }
        averagename.setText(s);
        averageav.setText(k);
    }

    @Override
    protected void onResume() {
        ca.notifyDataSetChanged();
        update();
        super.onResume();
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
