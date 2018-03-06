package com.kcb0126.developer.mibuddy.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kcb0126.developer.mibuddy.MainActivity;
import com.kcb0126.developer.mibuddy.R;
import com.kcb0126.developer.mibuddy.adapters.AnswerListAdapter;
import com.kcb0126.developer.mibuddy.adapters.QuestionListAdapter;
import com.kcb0126.developer.mibuddy.utils.ChineseAnswer;
import com.kcb0126.developer.mibuddy.utils.ChineseQuestion;
import com.kcb0126.developer.mibuddy.utils.EnglishAnswer;
import com.kcb0126.developer.mibuddy.utils.EnglishQuestion;
import com.kcb0126.developer.mibuddy.utils.OnFragmentInteractionListener;
import com.kcb0126.developer.mibuddy.utils.QuestionxAnswer;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link TalkNowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TalkNowFragment extends Fragment implements OnClickListener, OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    // parent activity or main activity
    private MainActivity parentActivity;

    // talk-now controls
    private ListView lvwQuestion;
    private ListView lvwAnswers;
    private TextView tvwQuestion;

    // questions and answers controls
    private EnglishQuestion englishQuestions;
    private EnglishAnswer englishAnswer;
    private ChineseQuestion chineseQuestion;
    private ChineseAnswer chineseAnswer;
    private QuestionxAnswer questionxAnswer;

    // lists of questions and answers
    private ArrayList<String> questions;
    private ArrayList<String> questionIDs;
    private ArrayList<String> answers;
    private ArrayList<String> answerIDs;

    public TalkNowFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TalkNowFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TalkNowFragment newInstance(String param1, String param2) {
        TalkNowFragment fragment = new TalkNowFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_talk_now, container, false);

        // Remember main activity
        parentActivity = (MainActivity)getActivity();

        // Configure back button
        View btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        // initializing lists of questions and answers
        questions = new ArrayList<String>();
        questionIDs = new ArrayList<String>();
        answers = new ArrayList<String>();
        answerIDs = new ArrayList<String>();

        // creating controls for questions and answers
        englishQuestions = new EnglishQuestion();
        englishAnswer = new EnglishAnswer();
        chineseQuestion = new ChineseQuestion();
        chineseAnswer = new ChineseAnswer();
        questionxAnswer = new QuestionxAnswer();

        // initializing above controls
        if(englishQuestions.readAllQuestions(getActivity().getApplicationContext())) {
            do {
                questions.add(englishQuestions.getQuestionText());
                questionIDs.add(englishQuestions.getDBID());
            } while (englishQuestions.moveCursor());
        } else {
            questions.add("no leyo las preguntas en ingles");
        }

        // Configure listview for questions
        lvwQuestion = (ListView)view.findViewById(R.id.lvwQuestions);
        QuestionListAdapter questionListAdapter = new QuestionListAdapter(getActivity().getApplicationContext(), questions);
        lvwQuestion.setAdapter(questionListAdapter);
        lvwQuestion.setOnItemClickListener(this);

        // Configure other widgets
        lvwAnswers = (ListView)view.findViewById(R.id.lvwAnswers);
        tvwQuestion = (TextView)view.findViewById(R.id.tvwQuestion);


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
                parentActivity.gotoHome();
                break;

            default:
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lvwQuestions:
                answers.clear();
                answerIDs.clear();

                if(chineseQuestion.readQuestionID(getActivity().getApplicationContext(), questionIDs.get(position))) {
                    tvwQuestion.setText(chineseQuestion.getQuestionText());

                    if(questionxAnswer.readQuestionID(getActivity().getApplicationContext(), questionIDs.get(position))) {
                        do {
                            answerIDs.add(questionxAnswer.getAID());
                            if(chineseAnswer.readAnswerID(getActivity().getApplicationContext(), questionxAnswer.getAID())) {
                                answers.add(chineseAnswer.getAnswerText());
                            } else {
                                answers.add("no esta leyendo bien de answers");
                            }
                        } while (questionxAnswer.moveCursor());
                    } else {
                        answers.add("no esta leyendo bien de questionsXanswers");
                    }
                } else {
                    tvwQuestion.setText("no esta leyendo bien de la base");
                }

                AnswerListAdapter answerListAdapter = new AnswerListAdapter(getActivity().getApplicationContext(), answers, answerIDs, englishAnswer);
                lvwAnswers.setAdapter(answerListAdapter);
                lvwAnswers.setOnItemClickListener(this);

                break;
            default:

                break;
        }
    }
}
