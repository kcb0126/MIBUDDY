package com.kcb0126.developer.mibuddy.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kcb0126.developer.mibuddy.R;

/**
 * Created by developer on 3/5/2018.
 */

public class HomeGridAdapter extends BaseAdapter {

    private String[] mTitles = {"Food", "Transport", "Shopping"
                                    , "Booking", "Icons", "Tips"
                                    , "Emergency", "Attractions", "Culture"};

    private int[] mIcons = {R.drawable.ic_home_food, R.drawable.ic_home_transport, R.drawable.ic_home_shopping
                            , R.drawable.ic_home_booking, R.drawable.ic_home_icons, R.drawable.ic_home_tips
                            , R.drawable.ic_home_emergency, R.drawable.ic_home_attractions, R.drawable.ic_home_culture};

    private Context mContext;

    public HomeGridAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mTitles.length;
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

        View cellView;
        ViewHolder holder;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            cellView = inflater.inflate(R.layout.item_homegrid, parent, false);

            holder = new ViewHolder();
            holder.iconImageView = cellView.findViewById(R.id.item_image);
            holder.iconImageView.setImageResource(mIcons[position]);

            holder.titleTextView = cellView.findViewById(R.id.item_title);
            holder.titleTextView.setText(mTitles[position]);

            cellView.setBackgroundResource(R.drawable.home_grid_item_bg);

            convertView = cellView;
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        convertView.setTag(holder);
        return convertView;
    }

    class ViewHolder {
        ImageView iconImageView;
        TextView titleTextView;
    }
}
