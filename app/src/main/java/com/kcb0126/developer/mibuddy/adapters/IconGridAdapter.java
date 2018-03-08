package com.kcb0126.developer.mibuddy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.kcb0126.developer.mibuddy.R;

/**
 * Created by developer on 3/9/2018.
 */

public class IconGridAdapter extends BaseAdapter {

    private int[] mIcons = {R.drawable.ic_bike, R.drawable.ic_arrow_up, R.drawable.ic_arrow_down, R.drawable.ic_arrow_back
    , R.drawable.ic_car_fore, R.drawable.ic_tap, R.drawable.ic_plate_fork_and_knife, R.drawable.ic_beer
    , R.drawable.ic_car, R.drawable.ic_man_sitting_on_the_toilet, R.drawable.ic_doctor_suitcase_with_a_cross, R.drawable.ic_coffee_cup_of_hot_drink_black_silhouette
    , R.drawable.ic_bus_side_view, R.drawable.ic_right_arrow, R.drawable.ic_triptych_paper, R.drawable.ic_person_lying_on_bed_inside_a_home
    , R.drawable.ic_train, R.drawable.ic_clock, R.drawable.ic_policeman_head, R.drawable.ic_wifi_signal
    , R.drawable.ic_sea_ship, R.drawable.ic_oil, R.drawable.ic_man_silhouette_running_on_treadmill_machine, R.drawable.ic_suitcase_with_wheels
    , R.drawable.ic_plane, R.drawable.ic_camera, R.drawable.ic_money_paper_of_dollars, R.drawable.ic_question_mark};

    private Context mContext;

    public IconGridAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mIcons.length;
    }

    @Override
    public Object getItem(int position) {
        return mIcons[position];
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
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            cellView = inflater.inflate(R.layout.item_icongrid, parent, false);

            holder = new ViewHolder();
            holder.imgIcon = (ImageView) cellView.findViewById(R.id.imgIcon);
            holder.imgIcon.setImageResource(mIcons[position]);

            convertView = cellView;
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        convertView.setTag(holder);
        return convertView;
    }

    class ViewHolder {
        ImageView imgIcon;
    }
}
