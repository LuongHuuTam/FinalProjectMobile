package com.example.project.ui.enrollment;

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
import com.example.project.adapters.EnrollmentAdapter;
import com.example.project.models.EnrollmentResponse;
import com.example.project.sharepreference.SharedPreferencesManager;

import java.util.List;

public class EnrollmentFragment extends Fragment {

    private EnrollmentViewModel enrollmentViewModel;
    private EnrollmentAdapter enrollmentAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        enrollmentViewModel =
                new ViewModelProvider(this).get(EnrollmentViewModel.class);


        View root = inflater.inflate(R.layout.fragment_enrollment, container, false);
        String token = "";
        int classId=0;
        if (SharedPreferencesManager.getLoginResponseValue(requireContext()) != null) {
            token = SharedPreferencesManager.getLoginResponseValue(requireContext()).getToken();
        }
        enrollmentViewModel.enrollments(token,classId);

        enrollmentAdapter=new EnrollmentAdapter();


        enrollmentAdapter.setEnrollmentListener(new EnrollmentAdapter.EnrollmentListener() {
            @Override
            public void onDelete(int id) {

            }
        });
        RecyclerView recyclerView = root.findViewById(R.id.rv_enrollmentList);
        recyclerView.setAdapter(enrollmentAdapter);

//        AutoCompleteTextView classSelector = root.findViewById((R.id.e_class_selector));
//        List<ClassResponse> options = enrollmentViewModel.getClassResponseLiveData().getValue();
//
//        ArrayAdapter arrayAdapter = new ArrayAdapter(container.getContext(),R.layout.list_item,options);
//        classSelector.setAdapter(arrayAdapter);

        enrollmentViewModel.getEnrollmentResponseLiveData().observe(getViewLifecycleOwner(), (Observer<List<EnrollmentResponse>>) enrollmentResponseList -> {
            enrollmentAdapter.setEnrollmentResponsesList(enrollmentResponseList);
        });
        return root;
    }
}