package com.kcb0126.developer.mibuddy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kcb0126.developer.mibuddy.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by developer on 3/16/2018.
 */

public class PinnedListAdapter extends BaseAdapter {

    // context
    private Context mContext;

    // pinned messages
    private ArrayList<String> mPinnedMessages = new ArrayList<>();

    // constructor
    public PinnedListAdapter(Context context, ArrayList<String> pinnedMessages) {
        mContext = context;
        mPinnedMessages.addAll(pinnedMessages);
    }

    // update
    public boolean update(ArrayList<String> pinnedMessages) {
        mPinnedMessages.clear();
        mPinnedMessages.addAll(pinnedMessages);
        return true;
    }

    @Override
    public int getCount() {
        return mPinnedMessages.size();
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

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            rowView = inflater.inflate(R.layout.item_pinned, parent, false);

            holder = new ViewHolder();
            holder.tvwPinned = (TextView) rowView.findViewById(R.id.tvwPinned);

            convertView = rowView;
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvwPinned.setText(mPinnedMessages.get(position));

        convertView.setTag(holder);

        return convertView;
    }

    class ViewHolder {
        TextView tvwPinned;
    }
}
