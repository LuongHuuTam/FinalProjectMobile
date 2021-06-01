package com.example.project.ui.home;

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
import com.example.project.adapters.HomeTraineeAdapter;
import com.example.project.models.DoFeedbackResponse;
import com.example.project.sharepreference.SharedPreferencesManager;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private HomeTraineeAdapter homeAdapterTrainee;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root;
        String token = "";
        String role="";
        String username="";


        if (SharedPreferencesManager.getLoginResponseValue(requireContext()) != null) {
            token = SharedPreferencesManager.getLoginResponseValue(requireContext()).getToken();
            role = SharedPreferencesManager.getLoginResponseValue(requireContext()).getRole();
            username = SharedPreferencesManager.getLoginResponseValue(requireContext()).getUsername();
        }

        if(role.equals("Trainee")){
            root=inflater.inflate(R.layout.fragment_home_trainee,container,false);
            homeAdapterTrainee=new HomeTraineeAdapter();
            homeViewModel.feedbacks(token,username);


            homeAdapterTrainee.setDoFeedbackListener(new HomeTraineeAdapter.DoFeedbackListener() {
                @Override
                public void onDelete(int id) {

                }
            });
            RecyclerView recyclerView = root.findViewById(R.id.rv_doFeedback);
            recyclerView.setAdapter(homeAdapterTrainee);


            homeViewModel.getDoFeedbackResponseLiveData().observe(getViewLifecycleOwner(), (Observer<List<DoFeedbackResponse>>) feedbackResponseList -> {
                homeAdapterTrainee.setDoFeedbackResponseList(feedbackResponseList);
            });
        }else {
            root=inflater.inflate(R.layout.fragment_home,container,false);
        }

        return root;
    }
}