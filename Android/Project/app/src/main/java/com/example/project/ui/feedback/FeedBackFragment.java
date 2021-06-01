package com.example.project.ui.feedback;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.adapters.FeedbackAdapter;
import com.example.project.models.FeedbackResponse;
import com.example.project.sharepreference.SharedPreferencesManager;

import java.util.List;

public class FeedBackFragment extends Fragment {
    private FeedBackViewModel feedBackViewModel;
    private FeedbackAdapter feedbackAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        feedBackViewModel =
                new ViewModelProvider(this).get(FeedBackViewModel.class);
        View root = inflater.inflate(R.layout.fragment_feedback, container, false);

        String token = "";

        if (SharedPreferencesManager.getLoginResponseValue(requireContext()) != null) {
            token = SharedPreferencesManager.getLoginResponseValue(requireContext()).getToken();
        }
        feedBackViewModel.feedbacks(token);
        feedbackAdapter=new FeedbackAdapter();


        feedbackAdapter.setFeedbackListener(new FeedbackAdapter.FeedbackListener() {
            @Override
            public void onDelete(int id) {

            }
        });
        RecyclerView recyclerView = root.findViewById(R.id.rv_feedbackList);
        recyclerView.setAdapter(feedbackAdapter);


        feedBackViewModel.getFeedbackResponseLiveData().observe(getViewLifecycleOwner(), (Observer<List<FeedbackResponse>>) feedbackResponseList -> {
            feedbackAdapter.setFeedbackResponseList(feedbackResponseList);
        });

        return root;
    }
}
