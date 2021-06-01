package com.example.project.ui.module;

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
import com.example.project.adapters.ModuleAdapter;
import com.example.project.models.ModuleResponse;
import com.example.project.sharepreference.SharedPreferencesManager;

import java.util.List;

public class ModuleFragment extends Fragment {
    private ModuleViewModel moduleViewModel;
    private ModuleAdapter moduleAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        moduleViewModel =
                new ViewModelProvider(this).get(ModuleViewModel.class);
        View root = inflater.inflate(R.layout.fragment_module, container, false);
        String token = "";

        if (SharedPreferencesManager.getLoginResponseValue(requireContext()) != null) {
            token = SharedPreferencesManager.getLoginResponseValue(requireContext()).getToken();
        }

        moduleViewModel.modules(token);
        moduleAdapter=new ModuleAdapter();
        moduleAdapter.setModuleListener(new ModuleAdapter.ModuleListener() {
            @Override
            public void onDelete(int id) {

            }
        });

        RecyclerView recyclerView = root.findViewById(R.id.rv_module);
        recyclerView.setAdapter(moduleAdapter);


        moduleViewModel.getModuleResponseLiveData().observe(getViewLifecycleOwner(), (Observer<List<ModuleResponse>>) moduleResponseList -> {
            moduleAdapter.setModuleResponseList(moduleResponseList);
        });

        return root;
    }
}
