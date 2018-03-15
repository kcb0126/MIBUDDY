package com.kcb0126.developer.mibuddy.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.kcb0126.developer.mibuddy.MainActivity;
import com.kcb0126.developer.mibuddy.R;
import com.kcb0126.developer.mibuddy.adapters.ChatListAdapter;
import com.kcb0126.developer.mibuddy.adapters.PinnedListAdapter;
import com.kcb0126.developer.mibuddy.managers.ApiManager;
import com.kcb0126.developer.mibuddy.models.ChatModel;
import com.kcb0126.developer.mibuddy.utils.OnFragmentInteractionListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link CommunityChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommunityChatFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ISLEADER = "isLeader";
    private static final String ARG_GROUPID = "groupId";

    // TODO: Rename and change types of parameters
    private boolean mIsLeader;
    private int mGroupId;

    private OnFragmentInteractionListener mListener;

    private MainActivity parentActivity;

    private CommunityFragment parentFragment;

    private ListView lvwChats;

    private ListView lvwPinnedMessages;

    private EditText edtMessage;

    Timer chatTimer;

    public CommunityChatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param isLeader Parameter 1.
     * @param groupId Parameter 2.
     * @return A new instance of fragment CommunityChatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CommunityChatFragment newInstance(boolean isLeader, int groupId) {
        CommunityChatFragment fragment = new CommunityChatFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_ISLEADER, isLeader);
        args.putInt(ARG_GROUPID, groupId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mIsLeader = getArguments().getBoolean(ARG_ISLEADER);
            mGroupId = getArguments().getInt(ARG_GROUPID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_community_chat, container, false);

        // remember parent
        parentActivity = (MainActivity)getActivity();
        parentFragment = (CommunityFragment) getParentFragment();

        // configure back button
        View btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        // configure pinned list
        lvwPinnedMessages = (ListView)view.findViewById(R.id.lvwPinnedMessages);
        PinnedListAdapter pinnedListAdapter = new PinnedListAdapter(parentActivity, new ArrayList<String>());
        lvwPinnedMessages.setAdapter(pinnedListAdapter);

        // configure chat listview
        lvwChats = (ListView)view.findViewById(R.id.lvwChats);
        ChatListAdapter adapter = new ChatListAdapter(parentActivity, new ArrayList<ChatModel>(), mIsLeader);
        lvwChats.setAdapter(adapter);
        chatTimer = new Timer();
        chatTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                final TimerTask task = this;

                ApiManager.instance().messageList(parentActivity, mGroupId, new ApiManager.MessageListCallBack() {
                    @Override
                    public void success(ArrayList<ChatModel> chats, ArrayList<String> pinned) {
                        ChatListAdapter adapter = (ChatListAdapter) lvwChats.getAdapter();
                        if(adapter.update(chats)) {
                            adapter.notifyDataSetChanged();
                            lvwChats.setSelection(adapter.getCount());
                        }

                        PinnedListAdapter pinnedListAdapter = (PinnedListAdapter) lvwPinnedMessages.getAdapter();
                        if(pinnedListAdapter.update(pinned)) {
                            pinnedListAdapter.notifyDataSetChanged();
                        }
                    }
                });

//                chatTimer.schedule(this, 5000);
            }
        }, 0, 5000);

        // configure input form for message
        edtMessage = (EditText)view.findViewById(R.id.edtMessage);

        // configure send button
        Button btnSend = (Button)view.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);

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
                parentActivity.gotoCommunity();
                break;

            case R.id.btnSend:
                String message = edtMessage.getText().toString();
                edtMessage.setText("");
                ApiManager.instance().sendMessage(parentActivity, mGroupId, message, new Runnable() {
                    @Override
                    public void run() {
                        // show message instant
                    }
                });
                break;
            default:

                break;
        }
    }
}
