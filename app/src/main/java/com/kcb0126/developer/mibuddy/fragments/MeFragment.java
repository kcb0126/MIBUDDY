package com.kcb0126.developer.mibuddy.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.kcb0126.developer.mibuddy.MainActivity;
import com.kcb0126.developer.mibuddy.R;
import com.kcb0126.developer.mibuddy.models.UserModel;
import com.kcb0126.developer.mibuddy.utils.OnFragmentInteractionListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link MeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MainActivity parentActivity;

    private TextView tvwUsername;
    private EditText edtUsername;
    private EditText edtGender;
    private EditText edtAge;
    private EditText edtNationality;
    private EditText edtLanguage;
    private EditText edtOccupation;
    private EditText edtAreas;
    private EditText edtHerefor;
    private EditText edtAboutme;

    private OnFragmentInteractionListener mListener;

    public MeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeFragment newInstance(String param1, String param2) {
        MeFragment fragment = new MeFragment();
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
        View view = inflater.inflate(R.layout.fragment_me, container, false);

        // Remember main activity
        parentActivity = (MainActivity)getActivity();

        // configure forms
        UserModel user = UserModel.instance();

        tvwUsername = (TextView)view.findViewById(R.id.tvwUsername);
        tvwUsername.setText(user.getUsername());

        edtUsername = (EditText)view.findViewById(R.id.edtUsername);
        edtUsername.setText(user.getUsername());

        edtGender = (EditText)view.findViewById(R.id.edtGender);
        edtGender.setText(user.getGender());

        edtAge = (EditText)view.findViewById(R.id.edtAge);
        edtAge.setText(String.valueOf(user.getAge()));

        edtNationality = (EditText)view.findViewById(R.id.edtNationality);
        edtNationality.setText(user.getNationality());

        edtLanguage = (EditText)view.findViewById(R.id.edtLanguage);
        edtLanguage.setText(user.getLanguage());

        edtOccupation = (EditText)view.findViewById(R.id.edtOccupation);
        edtOccupation.setText(user.getOccupation());

        edtAreas = (EditText)view.findViewById(R.id.edtAreas);
        edtAreas.setText(user.getAreas());

        edtHerefor = (EditText)view.findViewById(R.id.edtHerefor);
        edtHerefor.setText(user.getHerefor());

        edtAboutme = (EditText)view.findViewById(R.id.edtAboutme);
        edtAboutme.setText(user.getAboutme());

        return view;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:

                break;
        }
    }
}
