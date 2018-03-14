package com.kcb0126.developer.mibuddy.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kcb0126.developer.mibuddy.R;
import com.kcb0126.developer.mibuddy.utils.OnFragmentInteractionListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link CommunityGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommunityGroupFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_COMMUNITY = "community";

    // TODO: Rename and change types of parameters
    private String mCommunity;

    private CommunityFragment parentFragment;

    private OnFragmentInteractionListener mListener;

    public CommunityGroupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param community Parameter 1.
     * @return A new instance of fragment CommunityGroupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CommunityGroupFragment newInstance(String community) {
        CommunityGroupFragment fragment = new CommunityGroupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_COMMUNITY, community);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCommunity = getArguments().getString(ARG_COMMUNITY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_community_group, container, false);

        // remember parent
        parentFragment = (CommunityFragment) getParentFragment();

        // Set title
        TextView tvwTitle = view.findViewById(R.id.tvwTitle);
        String title = mCommunity + " Community";
        tvwTitle.setText(title);

        // configure back button
        View btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

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
                parentFragment.showMainFragment();
                break;

            default:

                break;
        }
    }
}
