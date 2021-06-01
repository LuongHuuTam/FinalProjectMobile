package com.example.project.ui.enrollment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.adapters.EnrollmentAdapter;
import com.example.project.models.class_models.ClassResponse;
import com.example.project.models.EnrollmentResponse;
import com.example.project.sharepreference.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentFragment extends Fragment {

    private EnrollmentViewModel enrollmentViewModel;
    private EnrollmentAdapter enrollmentAdapter;
    private List<ClassResponse> classResponseList = new ArrayList();

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

        RecyclerView recyclerView = root.findViewById(R.id.rv_enrollmentList);
        AutoCompleteTextView textView= root.findViewById(R.id.e_class_selector);

        setTextView(token,container,textView);
        setRecyclerView(token,classId,recyclerView);
        return root;
    }

    private void setRecyclerView(String token, int classId, RecyclerView recyclerView){
        enrollmentViewModel.enrollments(token,classId);
        enrollmentAdapter=new EnrollmentAdapter();


        enrollmentAdapter.setEnrollmentListener(new EnrollmentAdapter.EnrollmentListener() {
            @Override
            public void onDelete(int id) {

            }
        });

        recyclerView.setAdapter(enrollmentAdapter);
        enrollmentViewModel.getEnrollmentResponseLiveData().observe(getViewLifecycleOwner(), (Observer<List<EnrollmentResponse>>) enrollmentResponseList -> {
            enrollmentAdapter.setEnrollmentResponsesList(enrollmentResponseList);
        });
    }
    private void setTextView(String token,ViewGroup container,AutoCompleteTextView textView){
//        enrollmentViewModel.getClassResponseLiveData().observe(getViewLifecycleOwner(),(Observer<List<ClassResponse>>) classResponseLists->{
//            enrollmentFragment.setClassResponseList(classResponseLists);
//        });
        List<ClassResponse> cls=classResponseList;
        ClassResponse cl=new ClassResponse(0,"All");
        cls.add(0,cl);
        ArrayAdapter arrayAdapter=new ArrayAdapter(container.getContext(),R.layout.list_item,cls);
        textView.setAdapter(arrayAdapter);
    }
}