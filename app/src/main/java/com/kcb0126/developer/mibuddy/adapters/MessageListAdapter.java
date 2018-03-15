package com.kcb0126.developer.mibuddy.adapters;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.kcb0126.developer.mibuddy.R;
import com.kcb0126.developer.mibuddy.managers.ApiManager;
import com.kcb0126.developer.mibuddy.models.MessageModel;

import java.util.ArrayList;

/**
 * Created by developer on 3/15/2018.
 */

public class MessageListAdapter extends BaseAdapter {

    // list of messages to show
    private ArrayList<MessageModel> mMessages = new ArrayList<>();

    // context
    private Context mContext;

    // your message or other's?
    private boolean mIsYou;

    // constructor
    public MessageListAdapter(Context context, ArrayList<MessageModel> messages, boolean isYou) {
        mContext = context;
        for(MessageModel message : messages) {
            mMessages.add(message.copy());
        }
        mIsYou = isYou;
    }

    public ArrayList<MessageModel> getModel() {
        return mMessages;
    }

    @Override
    public int getCount() {
        return mMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return mMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        ViewHolder holder;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            rowView = inflater.inflate(R.layout.item_message, parent, false);

            holder = new ViewHolder();
            holder.rtlContainer = (RelativeLayout) rowView.findViewById(R.id.rtlContainer);
            holder.btnMessage = (Button) rowView.findViewById(R.id.btnMessage);

            holder.btnMessage.setText(mMessages.get(position).getMessage());

            if(mIsYou) {
                holder.rtlContainer.setGravity(Gravity.END);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    holder.btnMessage.setBackgroundColor(mContext.getColor(R.color.colorMessageFromYou));
                } else {
                    holder.btnMessage.setBackgroundColor(mContext.getResources().getColor(R.color.colorMessageFromYou));
                }
            }

            convertView = rowView;
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        convertView.setTag(holder);

        return convertView;
    }

    class ViewHolder {
        RelativeLayout rtlContainer;
        Button btnMessage;
    }
}
