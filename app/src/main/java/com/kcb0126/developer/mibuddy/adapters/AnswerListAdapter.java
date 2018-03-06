package com.kcb0126.developer.mibuddy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kcb0126.developer.mibuddy.R;
import com.kcb0126.developer.mibuddy.utils.EnglishAnswer;

import java.util.ArrayList;

import javax.crypto.AEADBadTagException;

/**
 * Created by developer on 3/6/2018.
 */

public class AnswerListAdapter extends BaseAdapter {

    // Constructor
    public AnswerListAdapter(Context context, ArrayList<String> answers, ArrayList<String> answerIDs, EnglishAnswer englishAnswer) {
        mContext = context;
        mAnswers = answers;
        mAnswerIDs = answerIDs;
        mEnglishAnswer = englishAnswer;
    }

    private Context mContext;

    private ArrayList<String> mAnswers;
    private ArrayList<String> mAnswerIDs;
    private EnglishAnswer mEnglishAnswer;

    @Override
    public int getCount() {
        return mAnswers.size();
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
            LayoutInflater inflater =(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            rowView = inflater.inflate(R.layout.item_answerlist, parent, false);

            holder = new ViewHolder();
            holder.englishTextView = rowView.findViewById(R.id.tvwEnglish);
            holder.chineseTextView = rowView.findViewById(R.id.tvwChinese);

            holder.chineseTextView.setText(mAnswers.get(position));

            if(mEnglishAnswer.readAnswer(mContext, mAnswerIDs.get(position))) {
                holder.englishTextView.setText(mEnglishAnswer.getAnswerText());
            } else {
                holder.englishTextView.setText("no leyo bien de englishanswers");
            }

            convertView = rowView;
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        convertView.setTag(holder);
        return convertView;
    }

    class ViewHolder {
        TextView englishTextView;
        TextView chineseTextView;
    }
}
