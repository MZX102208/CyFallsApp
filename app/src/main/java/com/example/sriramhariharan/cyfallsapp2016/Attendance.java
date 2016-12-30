package com.example.sriramhariharan.cyfallsapp2016;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by User1 on 4/20/2016.
 */
public class Attendance extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static View rootview;
    private static ListView yourListView;
    private static View vieww;
    private static TextView justText;
    private static TextView tardy;
    private View mProgressView;
    private static boolean attendload = false;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Attendance() {
        // Required empty public constructor
    }

    private boolean isInFront;

    @Override
    public void onResume() {
        super.onResume();
        isInFront = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isInFront = false;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootview = inflater.inflate(R.layout.fragment_attendance, container, false);
        yourListView = (ListView)rootview.findViewById(R.id.listView3);
        tardy = (TextView)rootview.findViewById(R.id.textView8);
        vieww = (View)rootview.findViewById(R.id.view);
        justText = (TextView)rootview.findViewById(R.id.textView12);
        setRetainInstance(true);
        ClssPkg pkg = Values.PKG;
        /*
        tardy.setText("Tardies - "+pkg.getAbs()[7]);
        */
        tardy.setText("Tardies - "+4);
        String[] StringArray = new String[7];

        StringArray[0]="1st Period - "+2;
        StringArray[1]="2nd Period - "+2;
        StringArray[2]="3rd Period - "+2;
        StringArray[3]="4th Period - "+1;
        StringArray[4]="5th Period - "+1;
        StringArray[5]="6th Period - "+0;
        StringArray[6]="7th Period - "+0;


        /*
        StringArray[0]="1st Period - "+pkg.getAbs()[0];
        StringArray[1]="2nd Period - "+pkg.getAbs()[1];
        StringArray[2]="3rd Period - "+pkg.getAbs()[2];
        StringArray[3]="4th Period - "+pkg.getAbs()[3];
        StringArray[4]="5th Period - "+pkg.getAbs()[4];
        StringArray[5]="6th Period - "+pkg.getAbs()[5];
        StringArray[6]="7th Period - "+pkg.getAbs()[6];

        */
        ArrayAdapter adapter = new ArrayAdapter<String>(rootview.getContext(),R.layout.simplelayoutleft,R.id.textView3,StringArray);
        yourListView.setAdapter(adapter);
        SendData sd = new SendData("enterattend");
        sd.execute();
        return rootview;
    }


    @Override
    public void onStart() {
        super.onStart();
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
