package com.kcb0126.developer.mibuddy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kcb0126.developer.mibuddy.R;

import java.util.ArrayList;

/**
 * Created by developer on 3/6/2018.
 */

public class QuestionListAdapter extends BaseAdapter {

    // Constructor
    public QuestionListAdapter(Context context, ArrayList<String> questions) {
        mContext = context;
        mQuestions = questions;
    }

    Context mContext;

    ArrayList<String> mQuestions;

    @Override
    public int getCount() {
        return mQuestions.size();
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
            rowView = inflater.inflate(R.layout.item_questionlist, parent, false);

            holder = new ViewHolder();
            holder.questionTextView = rowView.findViewById(R.id.tvwQuestion);

            holder.questionTextView.setText(mQuestions.get(position));

            convertView = rowView;
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        convertView.setTag(holder);;
        return convertView;
    }

    class ViewHolder {
        TextView questionTextView;
    }
}
