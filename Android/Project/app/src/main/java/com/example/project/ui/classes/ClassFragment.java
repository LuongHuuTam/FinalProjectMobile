package com.example.project.ui.classes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.adapters.ClassAdapter;
import com.example.project.models.ClassResponse;
import com.example.project.models.LoginResponse;
import com.example.project.sharepreference.SharedPreferencesManager;
import com.example.project.ui.MainActivity;
import com.example.project.ui.login.LoginActivity;

import java.util.List;

public class ClassFragment extends Fragment {

    private ClassViewModel classViewModel;
    private ClassAdapter classAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        classViewModel = new ViewModelProvider(this).get(ClassViewModel.class);
        View root = inflater.inflate(R.layout.fragment_class, container, false);

        String token = "";
        if (SharedPreferencesManager.getLoginResponseValue(requireContext()) != null) {
            token = SharedPreferencesManager.getLoginResponseValue(requireContext()).getToken();
        }
        classViewModel.classes(token);
        classAdapter = new ClassAdapter();
        classAdapter.setClassListener(new ClassAdapter.ClassListener() {
            @Override
            public void onDelete(int id) {
//                classViewModel.deleteClass(id);
            }
        });
        RecyclerView recyclerView = root.findViewById(R.id.rv_classList);
        recyclerView.setAdapter(classAdapter);
        classViewModel.getClassResponseLiveData().observe(getViewLifecycleOwner(), (Observer<List<ClassResponse>>) classResponseList -> {
            classAdapter.setClassResponseList(classResponseList);
        });
        return root;
    }
}