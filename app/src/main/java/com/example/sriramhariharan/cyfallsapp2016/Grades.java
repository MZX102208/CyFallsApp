package com.example.sriramhariharan.cyfallsapp2016;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Grades.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Grades#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Grades extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static ListView yourListView;
    private static View rootview;
    private static TextView emptyText;
    public static ProgressTask pt;
    public static UserGradesTask ug;
    private View mProgressView;
    private Button refr;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String username;
    String password;


    private OnFragmentInteractionListener mListener;

    public Grades() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static Grades newInstance(String param1, String param2) {
        Grades fragment = new Grades();
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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_grades, container, false);
        mProgressView = rootview.findViewById(R.id.grades_login);
        yourListView = (ListView) rootview.findViewById(R.id.listview);
        emptyText = (TextView) rootview.findViewById(R.id.noAssignTxt);
        mProgressView = rootview.findViewById(R.id.grades_login);
        SharedPreferences sharedPref = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        username = sharedPref.getString("Username", "");
        password = sharedPref.getString("Password", "");

        Values.unemptycourses.clear();
        for(Course c : Values.PKG.classes){
            if(c.assignments.size()>0)Values.unemptycourses.add(c);
        }

        if(Values.unemptycourses.size()>0)emptyText.setVisibility(View.INVISIBLE);
        else emptyText.setVisibility(View.VISIBLE);

        yourListView.setAdapter(new CourseAdapter(rootview.getContext(), Values.unemptycourses));
        yourListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Values.assignments = Values.unemptycourses.get(position).getAssignments();
                Values.Courseindex = position;
                for(Course c :Values.PKG.classes){
                    if(c.getName().equals(Values.unemptycourses.get(position).getName())){
                        Values.periodnum = Values.PKG.classes.indexOf(c);
                    }
                }
                  /*      if(java.lang.Double.isNaN(Values.unemptycourses.get(position).getAverage())){
                            Values.assignments = null;
                        } */


                if (Values.assignments != null && Values.assignments.size() != 0) {
                    Log.e("ASSIGNTEST",Values.assignments.toString());
                    Values.assignedcourse = Values.unemptycourses.get(position);
                    Intent intent = new Intent(getActivity(), AssignmentScreen.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "There are no assignments", Toast.LENGTH_SHORT).show();
                }

            }

        });
        SendData sd = new SendData("entergrades");
        sd.execute();
        return rootview;
    }
    private void showProg(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            yourListView.setVisibility(show ? View.GONE : View.VISIBLE);
            yourListView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    yourListView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            yourListView.setVisibility(show ? View.GONE : View.VISIBLE);
        }

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

    public boolean isInFront() {
        return isInFront;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (pt != null && !pt.isCancelled()) {
            pt.cancel(true);
        }
        if(ug != null && !ug.isCancelled()){
            ug.cancel(true);
        }

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.gradesmenu, menu);
    }
    public class ProgressTask extends AsyncTask<Void, Void, Void> {
        public ProgressTask() {

        }

        protected Void doInBackground(Void... params) {
            while (Values.loaded == false && isInFront == false) {

            }
            return null;
        }

        protected void onPostExecute(Void aVoid) {
            showProg(false);
            yourListView.setAdapter(new CourseAdapter(rootview.getContext(), Values.unemptycourses));
        }
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

    public void logOut() {
        SharedPreferences settings = getActivity().getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(getContext(), Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        getActivity().finish();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.refresh) {
            //Do whatever you want to do
            showProg(true);
            ug = new UserGradesTask(username, password);
            emptyText.setVisibility(View.INVISIBLE);
            ug.execute();
             pt = new ProgressTask();
            pt.execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public class UserGradesTask extends AsyncTask<Void, Void, Void> {
        private final String muser;
        private final String mPassword;

        public UserGradesTask(String user, String password) {
            muser = user;
            mPassword = password;
        }

        protected Void doInBackground(Void... params) {

            try {
                ClssPkg p = ClssPkg.getFromServer(muser,mPassword, 2);
                Log.e("______THEERRROR",p.toString());
                if(!(p.toString().equals("Wrong login"))){
                    Values.PKG = p;
                    Values.unemptycourses = p.classes;
                    Values.PKGcopy = ClssPkg.parse(p.toString());



                    Log.e("HELLO",p.toString());
               //     return "success";
                }
                else{
             //       return "fail";
                }

            } catch (Exception e) {
                Log.e("THE ERROR",e.toString());
        //        return "Exception";
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            Values.loaded = true;
            Values.unemptycourses.clear();
            for(Course c : Values.PKG.classes){
                if(c.assignments.size()>0)Values.unemptycourses.add(c);
            }

            if(Values.unemptycourses.size()>0)emptyText.setVisibility(View.INVISIBLE);
            else emptyText.setVisibility(View.VISIBLE);

        }
    }
}
