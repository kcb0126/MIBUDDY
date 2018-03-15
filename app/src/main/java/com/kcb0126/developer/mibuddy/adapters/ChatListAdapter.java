package com.kcb0126.developer.mibuddy.adapters;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kcb0126.developer.mibuddy.R;
import com.kcb0126.developer.mibuddy.managers.ApiManager;
import com.kcb0126.developer.mibuddy.models.ChatModel;
import com.kcb0126.developer.mibuddy.models.MessageModel;
import com.kcb0126.developer.mibuddy.utils.ConfirmDialog;

import java.util.ArrayList;

/**
 * Created by developer on 3/15/2018.
 */

public class ChatListAdapter extends BaseAdapter implements View.OnClickListener {

    // list of chats to show
    private ArrayList<ChatModel> mChats = new ArrayList<>();

    // context
    private Context mContext;

    // Is leader of this chat
    private boolean mIsLeader;

    // contructor
    public ChatListAdapter(Context context, ArrayList<ChatModel> chats, boolean isLeader) {
        mContext = context;
        for(ChatModel chat : chats) {
            mChats.add(chat.copy());
        }
        mIsLeader = isLeader;
    }

    // update
    public boolean update(ArrayList<ChatModel> chats) {
        if(chats.size() == mChats.size()) {
            if(chats.get(chats.size() - 1).getMessages().size() == mChats.get(mChats.size() - 1).getMessages().size()) {
                return false;
            }
        }
        mChats.clear();
        for(ChatModel chat : chats) {
            mChats.add(chat.copy());
        }
        this.notifyDataSetChanged();
        return true;
    }

    @Override
    public int getCount() {
        return mChats.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        ViewHolder holder;

        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null) {
            assert inflater != null;
            rowView = inflater.inflate(R.layout.item_chat, parent, false);

            holder = new ViewHolder();
            holder.lvwMessages = (ListView) rowView.findViewById(R.id.lvwMessages);
            holder.lytMessages = (LinearLayout)rowView.findViewById(R.id.lytMessages);
            holder.lytUser = (LinearLayout) rowView.findViewById(R.id.lytUser);
            holder.tvwUsername = (TextView) rowView.findViewById(R.id.tvwUsername);


            convertView = rowView;
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        ChatModel chat = mChats.get(position);
        holder.lytMessages.removeAllViews();
        for(MessageModel message : chat.getMessages()) {
            assert inflater != null;
            View msgView = inflater.inflate(R.layout.item_message, holder.lytMessages, false);
            Button msgButton = msgView.findViewById(R.id.btnMessage);
            msgButton.setId(message.getId());
            msgButton.setText(message.getMessage());
            holder.lytMessages.addView(msgView);
            RelativeLayout rtlContainer = msgView.findViewById(R.id.rtlContainer);
            if(chat.getIsYou()) {
                rtlContainer.setGravity(Gravity.END);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    msgButton.setBackgroundColor(mContext.getColor(R.color.colorMessageFromYou));
                } else {
                    msgButton.setBackgroundColor(mContext.getResources().getColor(R.color.colorMessageFromYou));
                }
            } else {
                rtlContainer.setGravity(Gravity.START);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    msgButton.setBackgroundColor(mContext.getColor(R.color.colorMessageFromOther));
                } else {
                    msgButton.setBackgroundColor(mContext.getResources().getColor(R.color.colorMessageFromOther));
                }
            }
            msgButton.setId(message.getId());
            if(mIsLeader) {
                msgButton.setOnClickListener(this);
            }
        }

        holder.tvwUsername.setText(chat.getUsername());

        if(chat.getIsYou()) {
            holder.lytUser.setGravity(Gravity.END);
        } else {
            holder.lytUser.setGravity(Gravity.START);
        }

        convertView.setTag(holder);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        // only msg buttons use this.

        String message = ((Button)v).getText().toString();

        final int messageId = v.getId();

        ConfirmDialog.showConfirmDialog(mContext, "Pin a message", "Are you sure you want to pin \"" + message + "\"?", new Runnable() {
            @Override
            public void run() {
                ApiManager.instance().pinMessage(mContext, messageId, new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext, "Success", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    class ViewHolder {
        ListView lvwMessages;
        LinearLayout lytMessages;
        LinearLayout lytUser;
        TextView tvwUsername;
    }
}
