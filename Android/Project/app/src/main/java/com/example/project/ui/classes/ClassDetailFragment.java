package com.example.project.ui.classes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.adapters.class_adapters.ClassDetailAdapter;
import com.example.project.models.class_models.ClassResponse;
import com.example.project.models.class_models.ClassTraineeResponse;
import com.example.project.sharepreference.SharedPreferencesManager;

import java.util.List;

public class ClassDetailFragment extends Fragment {
    private ClassDetailAdapter classDetailAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ClassViewModel classViewModel = new ViewModelProvider(this).get(ClassViewModel.class);
        classDetailAdapter = new ClassDetailAdapter();

        String token = "";
        View root = null;
        String finalToken = token;
        Bundle bundle = this.getArguments();

        TextView tv_class_id;
        TextView tv_class_name;

        if (SharedPreferencesManager.getLoginResponseValue(requireContext()) != null) {
            token = SharedPreferencesManager.getLoginResponseValue(requireContext()).getToken();
        }

        if(bundle!=null){
            int classId = bundle.getInt("CLASS_ID");
            classViewModel.getClassTraineeList(finalToken,classId);
            classViewModel.getClassInfo(finalToken,classId);
        }
        root = inflater.inflate(R.layout.fragment_class_detail, container, false);

        tv_class_id = root.findViewById(R.id.header_detail_class_id);
        tv_class_name = root.findViewById(R.id.header_detail_class_name);
        
        RecyclerView recyclerView = root.findViewById(R.id.rv_detailList);

        recyclerView.setAdapter(classDetailAdapter);
        classViewModel.getGetClassInfoResponseLiveData().observe(getViewLifecycleOwner(), new Observer<ClassResponse>() {
            @Override
            public void onChanged(ClassResponse classResponse) {
               tv_class_id.setText(Integer.toString(classResponse.getClassID()));
               tv_class_name.setText(classResponse.getName());
            }
        });
        classViewModel.getGetClassDetailResponseLiveData().observe(getViewLifecycleOwner(), (Observer<List<ClassTraineeResponse>>) classTraineeResponses -> {
            classDetailAdapter.setClassTraineeResponseList(classTraineeResponses);
        });

        return root;
    }
}


