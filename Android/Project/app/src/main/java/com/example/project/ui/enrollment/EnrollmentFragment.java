package com.example.project.ui.enrollment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnrollmentFragment extends Fragment {

    private EnrollmentViewModel enrollmentViewModel;
    private EnrollmentAdapter enrollmentAdapter;
    private List<ClassResponse> classResponseList = new ArrayList();
    HashMap<Integer, String> hmClass = new HashMap<Integer, String>();
    String token = "";
    int classId=0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        enrollmentViewModel =
                new ViewModelProvider(this).get(EnrollmentViewModel.class);


        View root = inflater.inflate(R.layout.fragment_enrollment, container, false);
        if (SharedPreferencesManager.getLoginResponseValue(requireContext()) != null) {
            token = SharedPreferencesManager.getLoginResponseValue(requireContext()).getToken();
        }

        RecyclerView recyclerView = root.findViewById(R.id.rv_enrollmentList);
        AutoCompleteTextView textView= root.findViewById(R.id.e_class_selector);

        setTextView(token,container,textView);
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setTextView(token,container,textView);
                classId =Integer.parseInt(getKeyFromValue(hmClass, textView.getText().toString()).toString());
                Log.i("CLASS ID", ""+classId);
                setRecyclerView(token,classId,recyclerView);
            }
        });
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
        enrollmentViewModel.getEnrollmentResponseLiveData().observe(getViewLifecycleOwner(),(Observer<List<EnrollmentResponse>>) enrollmentResponseList->{
            hmClass.put(0,"All");
            for(int i= 0; i<enrollmentResponseList.size();i++){
                hmClass.put(enrollmentResponseList.get(i).getClassId(), enrollmentResponseList.get(i).getClassName());
            }
            String[] classes = hmClass.values().toArray(new String[0]);
            ArrayAdapter arrayAdapter2 = new ArrayAdapter(getContext(), R.layout.list_item, classes);
            textView.setAdapter(arrayAdapter2);
        });
    }
    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }
}