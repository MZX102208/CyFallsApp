package com.example.sriramhariharan.cyfallsapp2016;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Schedule.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Schedule#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Schedule extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static TextView currentperiod;
    private static View rootview;

    private static TextView timeleft;
    public static  ScheduleAdapter sa;
    private static ListView yourListView;
    public static ArrayList<TimSched> timesch;
    private String current;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Schedule() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Schedule.
     */
    // TODO: Rename and change types and number of parameters
    public static Schedule newInstance(String param1, String param2) {
        Schedule fragment = new Schedule();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public static ArrayList<TimSched> timesch1 = new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview =  inflater.inflate(R.layout.fragment_schedule, container, false);
        ArrayList<String> currenschedule = new ArrayList<>();
        yourListView = (ListView)rootview.findViewById(R.id.fullschedule);
        currentperiod = (TextView)rootview.findViewById(R.id.periodd);
        Spinner sc = (Spinner)rootview.findViewById(R.id.schedulespinner);
        ArrayList<String> types = new ArrayList<>();
        types.add("1st Semester");
        types.add("2nd Semester");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), R.layout.dropdowntext, types);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sc.setAdapter(dataAdapter);
        timesch = new ArrayList<>();
        DateTime dt = new DateTime();
        if(dt.getMonthOfYear() >= 8) {
            currenschedule = Values.PKG.schedulesem1;
        }
        if(dt.getMonthOfYear() >=1 && dt.getMonthOfYear() < 8){

            currenschedule = Values.PKG.schedulesem2;
        }

        sc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timesch1.clear();
                if(position == 0){
                    timesch.clear();
                    timesch.add(new TimSched(Values.PKG.schedulesem1.get(0), "7:20 – 8:13"));
                    timesch.add(new TimSched(Values.PKG.schedulesem1.get(1), "8:19 – 9:15"));
                    timesch.add(new TimSched(Values.PKG.schedulesem1.get(2), "9:21 – 10:14"));

                    if (Values.lunch1 == 3) {
                        timesch.add(new TimSched("A Lunch", "10:14 – 10:44"));
                        timesch.add(new TimSched(Values.PKG.schedulesem1.get(3), "10:50 – 11:43"));
                        timesch.add(new TimSched(Values.PKG.schedulesem1.get(4), "11:59 – 12:42"));
                    }
                    else if (Values.lunch1 == 4) {
                        timesch.add(new TimSched(Values.PKG.schedulesem1.get(3), "10:20 – 11:13"));
                        timesch.add(new TimSched("B Lunch", "11:13 – 11:43"));
                        timesch.add(new TimSched(Values.PKG.schedulesem1.get(4), "11:49 – 12:42"));
                    }
                    else if (Values.lunch1 == 5) {
                        timesch.add(new TimSched(Values.PKG.schedulesem1.get(3), "10:20 – 11:13"));
                        timesch.add(new TimSched(Values.PKG.schedulesem1.get(4), "11:19 – 12:12"));
                        timesch.add(new TimSched("C Lunch", "12:12 – 12:42"));
                    }


                    timesch.add(new TimSched(Values.PKG.schedulesem1.get(5), "12:48 – 1:41"));
                    timesch.add(new TimSched(Values.PKG.schedulesem1.get(6), "1:47 – 2:40"));
                    sa = new ScheduleAdapter(rootview.getContext(), timesch);
                    yourListView.setAdapter(sa );

                }
                if(position == 1){
                    timesch.clear();
                    timesch.add(new TimSched(Values.PKG.schedulesem2.get(0), "7:20 – 8:13"));
                    timesch.add(new TimSched(Values.PKG.schedulesem2.get(1), "8:19 – 9:15"));
                    timesch.add(new TimSched(Values.PKG.schedulesem2.get(2), "9:21 – 10:14"));

                    if (Values.lunch2 == 3) {
                        timesch.add(new TimSched("A Lunch", "10:14 – 10:44"));
                        timesch.add(new TimSched(Values.PKG.schedulesem2.get(3), "10:50 – 11:43"));
                        timesch.add(new TimSched(Values.PKG.schedulesem2.get(4), "11:59 – 12:42"));
                    }
                    else if (Values.lunch2 == 4) {
                        timesch.add(new TimSched(Values.PKG.schedulesem2.get(3), "10:20 – 11:13"));
                        timesch.add(new TimSched("B Lunch", "11:13 – 11:43"));
                        timesch.add(new TimSched(Values.PKG.schedulesem2.get(4), "11:49 – 12:42"));
                    }
                    else if (Values.lunch2 == 5) {
                        timesch.add(new TimSched(Values.PKG.schedulesem2.get(3), "10:20 – 11:13"));
                        timesch.add(new TimSched(Values.PKG.schedulesem2.get(4), "11:19 – 12:12"));
                        timesch.add(new TimSched("C Lunch", "12:12 – 12:42"));
                    }


                    timesch.add(new TimSched(Values.PKG.schedulesem2.get(5), "12:48 – 1:41"));
                    timesch.add(new TimSched(Values.PKG.schedulesem2.get(6), "1:47 – 2:40"));
                    sa = new ScheduleAdapter(rootview.getContext(), timesch);
                    yourListView.setAdapter(sa );

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        currentperiod.setText(Values.current);
        return rootview;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
