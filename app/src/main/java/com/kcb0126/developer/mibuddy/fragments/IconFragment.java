package com.kcb0126.developer.mibuddy.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.kcb0126.developer.mibuddy.MainActivity;
import com.kcb0126.developer.mibuddy.R;
import com.kcb0126.developer.mibuddy.adapters.IconGridAdapter;
import com.kcb0126.developer.mibuddy.utils.OnFragmentInteractionListener;
import com.kcb0126.developer.mibuddy.utils.TouchImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link IconFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IconFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private MainActivity parentActivity;

    private GridView mIconGridView;

    public IconFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IconFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IconFragment newInstance(String param1, String param2) {
        IconFragment fragment = new IconFragment();
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
        View view = inflater.inflate(R.layout.fragment_icon, container, false);

        // Remember Main Activity
        parentActivity = (MainActivity)getActivity();

        // Configure gridview of icons
        mIconGridView = (GridView)view.findViewById(R.id.grdIcons);
        IconGridAdapter adapter = new IconGridAdapter(getActivity().getApplicationContext());
        mIconGridView.setAdapter(adapter);
        mIconGridView.setOnItemClickListener(this);

        // Configure back button
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View contentView = getLayoutInflater().inflate(R.layout.view_icon_detail, null);
        ((ImageView)contentView.findViewById(R.id.imgIcon)).setImageResource((int)parent.getItemAtPosition(position));
        dialog.setContentView(contentView);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btnBack:
            parentActivity.gotoHome();
            break;
        default:
            break;
        }
    }
}
