package com.kcb0126.developer.mibuddy.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.kcb0126.developer.mibuddy.MainActivity;
import com.kcb0126.developer.mibuddy.R;
import com.kcb0126.developer.mibuddy.utils.OnFragmentInteractionListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link CommunityMainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommunityMainFragment extends Fragment implements OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private MainActivity parentActivity;

    private CommunityFragment parentFragment;

    public CommunityMainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CommunityMainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CommunityMainFragment newInstance(String param1, String param2) {
        CommunityMainFragment fragment = new CommunityMainFragment();
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
        View view = inflater.inflate(R.layout.fragment_community_main, container, false);

        // Remember Main Activity and parent
        parentActivity = (MainActivity)getActivity();
        parentFragment = (CommunityFragment)getParentFragment();

        // Configure back button
        View btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        // Configure community buttons
        View btnTravel = view.findViewById(R.id.btnTravel);
        btnTravel.setOnClickListener(this);

        View btnCountries = view.findViewById(R.id.btnCountries);
        btnCountries.setOnClickListener(this);

        View btnNightLife = view.findViewById(R.id.btnNightLife);
        btnNightLife.setOnClickListener(this);

        View btnDating = view.findViewById(R.id.btnDating);
        btnDating.setOnClickListener(this);

        View btnStudies = view.findViewById(R.id.btnStudies);
        btnStudies.setOnClickListener(this);

        View btnBusiness = view.findViewById(R.id.btnBusiness);
        btnBusiness.setOnClickListener(this);

        View btnDining = view.findViewById(R.id.btnDining);
        btnDining.setOnClickListener(this);

        View btnHousing = view.findViewById(R.id.btnHousing);
        btnHousing.setOnClickListener(this);

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
            case R.id.btnBack:
                parentActivity.gotoHome();
                break;

            case R.id.btnTravel:
                parentFragment.showGroupFragment("Travel");
                break;

            case R.id.btnCountries:
                parentFragment.showGroupFragment("Countries");
                break;

            case R.id.btnNightLife:
                parentFragment.showGroupFragment("Night Life");
                break;

            case R.id.btnDating:
                parentFragment.showGroupFragment("Dating");
                break;

            case R.id.btnStudies:
                parentFragment.showGroupFragment("Studies");
                break;

            case R.id.btnBusiness:
                parentFragment.showGroupFragment("Business");
                break;

            case R.id.btnDining:
                parentFragment.showGroupFragment("Dining");
                break;

            case R.id.btnHousing:
                parentFragment.showGroupFragment("Housing");
                break;

            default:

                break;
        }
    }
}
