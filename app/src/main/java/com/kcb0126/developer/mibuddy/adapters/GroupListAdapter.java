package com.kcb0126.developer.mibuddy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kcb0126.developer.mibuddy.R;
import com.kcb0126.developer.mibuddy.models.GroupModel;

import java.util.ArrayList;

/**
 * Created by developer on 3/15/2018.
 */

public class GroupListAdapter extends BaseAdapter {

    // list of groups to show
    ArrayList<GroupModel> mGroups = new ArrayList<>();

    // context
    Context mContext;

    // constructor
    public GroupListAdapter(Context context, ArrayList<GroupModel> groups) {
        mContext = context;
        for(GroupModel group : groups) {
            mGroups.add(group.copy());
        }
    }

    // change model
    public void setModel(ArrayList<GroupModel> groups) {
        mGroups.clear();
        for(GroupModel group : groups) {
            mGroups.add(group.copy());
        }
    }

    @Override
    public int getCount() {
        return mGroups.size();
    }

    @Override
    public Object getItem(int position) {
        return mGroups.get(position);
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
            rowView = inflater.inflate(R.layout.item_grouplist, parent, false);

            holder = new ViewHolder();
            holder.tvwGroup = rowView.findViewById(R.id.tvwGroup);
            holder.tvwLeader = rowView.findViewById(R.id.tvwLeader);
            holder.tvwMembers = rowView.findViewById(R.id.tvwMembers);

            GroupModel group = mGroups.get(position);

            holder.tvwGroup.setText(group.getName());
            holder.tvwLeader.setText(group.getLeader());
            holder.tvwMembers.setText(String.valueOf(group.getMembers()));

            convertView = rowView;
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        convertView.setTag(holder);

        return convertView;
    }

    class ViewHolder {
        TextView tvwGroup;
        TextView tvwLeader;
        TextView tvwMembers;

    }
}
