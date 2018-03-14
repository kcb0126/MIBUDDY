package com.kcb0126.developer.mibuddy.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kcb0126.developer.mibuddy.MainActivity;
import com.kcb0126.developer.mibuddy.R;
import com.kcb0126.developer.mibuddy.adapters.GroupListAdapter;
import com.kcb0126.developer.mibuddy.managers.ApiManager;
import com.kcb0126.developer.mibuddy.models.GroupModel;
import com.kcb0126.developer.mibuddy.utils.OnFragmentInteractionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link CommunityGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommunityGroupFragment extends Fragment implements View.OnClickListener, TextWatcher {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_COMMUNITY = "community";

    // TODO: Rename and change types of parameters
    private String mCommunity;

    private MainActivity parentActivity;

    private CommunityFragment parentFragment;

    private OnFragmentInteractionListener mListener;

    private EditText edtGroupName;

    private ListView lvwGroups;

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
        parentActivity = (MainActivity)getActivity();
        parentFragment = (CommunityFragment) getParentFragment();

        // Set title
        TextView tvwTitle = view.findViewById(R.id.tvwTitle);
        String title = mCommunity + " Community";
        tvwTitle.setText(title);

        // configure back button
        View btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        // configure form of group name
        edtGroupName = (EditText) view.findViewById(R.id.edtGroupName);
        edtGroupName.addTextChangedListener(this);

        // configure createNewGroup button
        Button btnCreate = (Button)view.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);

        // configure listview for groups
        lvwGroups = (ListView)view.findViewById(R.id.lvwGroups);
        ApiManager.instance().groupList(parentActivity, mCommunity, "", new ApiManager.GroupListCallBack() {
            @Override
            public void success(ArrayList<GroupModel> groups) {
                GroupListAdapter adapter = new GroupListAdapter(parentActivity, groups);
                lvwGroups.setAdapter(adapter);
            }
        });

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

            case R.id.btnCreate:
                String groupName = edtGroupName.getText().toString();
                ApiManager.instance().createGroup(parentActivity, groupName, mCommunity, new Runnable() {
                    @Override
                    public void run() {
                        ApiManager.instance().groupList(parentActivity, mCommunity, edtGroupName.getText().toString(), new ApiManager.GroupListCallBack() {
                            @Override
                            public void success(ArrayList<GroupModel> groups) {
                                GroupListAdapter adapter = (GroupListAdapter) lvwGroups.getAdapter();
                                adapter.setModel(groups);
                                adapter.notifyDataSetChanged();
                                lvwGroups.setAdapter(adapter);
                            }
                        });
                    }
                });
                break;

            default:

                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        ApiManager.instance().groupList(parentActivity, mCommunity, s.toString(), new ApiManager.GroupListCallBack() {
            @Override
            public void success(ArrayList<GroupModel> groups) {
                GroupListAdapter adapter = (GroupListAdapter) lvwGroups.getAdapter();
                adapter.setModel(groups);
                adapter.notifyDataSetChanged();
                lvwGroups.setAdapter(adapter);
            }
        });

    }
}
