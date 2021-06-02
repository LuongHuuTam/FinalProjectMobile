package com.example.project.ui.module;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.adapters.module_adapters.ModuleAdapter;
import com.example.project.models.module_models.ModuleResponse;
import com.example.project.sharepreference.SharedPreferencesManager;
import com.google.gson.Gson;

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
        ImageButton buttonAddModule = root.findViewById(R.id.m_btn_add);
        buttonAddModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_module_to_module_add_module);
            }
        });

        if (SharedPreferencesManager.getLoginResponseValue(requireContext()) != null) {
            token = SharedPreferencesManager.getLoginResponseValue(requireContext()).getToken();
        }

        moduleViewModel.modules(token);
        moduleAdapter=new ModuleAdapter();
        String finalToken = token;
        moduleAdapter.setModuleDelete(new ModuleAdapter.ModuleDelete() {
            @Override
            public void onDelete(int moduleId) {
                if(finalToken !=null){
                    moduleViewModel.deleteModule(finalToken,moduleId);
                }
                Toast.makeText(root.getContext(), "Delete success", Toast.LENGTH_SHORT).show();
            }
        });
        moduleAdapter.setModuleEdit(new ModuleAdapter.ModuleEdit() {
            @Override
            public void onEdit(int moduleId) {
                moduleViewModel.getGetModuleInfoResponseLiveData().observe(getViewLifecycleOwner(), new Observer<ModuleResponse>() {
                    @Override
                    public void onChanged(ModuleResponse moduleResponse) {
                        Gson gson = new Gson();
                        String moduleResponseData = gson.toJson(moduleResponse);
                        Bundle bundle = new Bundle();
                        bundle.putString("MODULE_DATA", moduleResponseData);
                        Navigation.findNavController(root).navigate(R.id.action_nav_module_to_module_update_module, bundle);
                    }
                });
                if(finalToken != null){
                    moduleViewModel.getModuleInfo(finalToken,moduleId);
                }
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
