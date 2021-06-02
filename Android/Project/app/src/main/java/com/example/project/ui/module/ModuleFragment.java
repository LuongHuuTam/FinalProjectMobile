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
import com.example.project.adapters.ModuleAdapter;
import com.example.project.adapters.ModuleAdapterTraineeTrainer;
import com.example.project.models.ModuleResponse;
import com.example.project.sharepreference.SharedPreferencesManager;
import com.google.gson.Gson;

import java.util.List;

public class ModuleFragment extends Fragment {
    private ModuleViewModel moduleViewModel;
    private ModuleAdapter moduleAdapter;
    private ModuleAdapterTraineeTrainer moduleAdapterTraineeTrainer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        moduleViewModel =
                new ViewModelProvider(this).get(ModuleViewModel.class);
        View root = inflater.inflate(R.layout.fragment_module, container, false);
        String token = "";
        String role="";
        String username="";


        if (SharedPreferencesManager.getLoginResponseValue(requireContext()) != null) {
            token = SharedPreferencesManager.getLoginResponseValue(requireContext()).getToken();
            role=SharedPreferencesManager.getLoginResponseValue(requireContext()).getRole();
            username=SharedPreferencesManager.getLoginResponseValue(requireContext()).getUsername();
        }
        ImageButton buttonAddModule = root.findViewById(R.id.m_btn_add);
        RecyclerView recyclerView = root.findViewById(R.id.rv_module);
        moduleAdapter=new ModuleAdapter();
        moduleAdapterTraineeTrainer=new ModuleAdapterTraineeTrainer();
        moduleViewModel.modules(token);
        moduleViewModel.getModuleTraineeTrainer(token,role,username);

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
                        Navigation.findNavController(root).navigate(R.id.action_nav_module_to_module_add_module, bundle);
                    }
                });
                if(finalToken != null){
                    moduleViewModel.getModuleInfo(finalToken,moduleId);

                }
            }
        });

        if (role.equals("Admin")){
            buttonAddModule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.action_nav_module_to_module_add_module);
                }
            });
            recyclerView.setAdapter(moduleAdapter);
            moduleViewModel.getModuleResponseLiveData().observe(getViewLifecycleOwner(), (Observer<List<ModuleResponse>>) moduleResponseList -> {
                moduleAdapter.setModuleResponseList(moduleResponseList);
            });
        }
        else {
            buttonAddModule.setVisibility(View.GONE);
            recyclerView.setAdapter(moduleAdapterTraineeTrainer);
            moduleViewModel.getModuleTraineeTrainerResponseLiveData().observe(getViewLifecycleOwner(), (Observer<List<ModuleResponse>>) moduleResponseList -> {
                moduleAdapterTraineeTrainer.setModuleResponseList(moduleResponseList);
            });
        }

        return root;

    }

}
