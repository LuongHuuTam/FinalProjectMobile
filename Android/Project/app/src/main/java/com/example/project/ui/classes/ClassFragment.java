package com.example.project.ui.classes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.adapters.class_adapters.ClassAdapter;
import com.example.project.adapters.class_adapters.ClassAdapterTrainerTrainee;
import com.example.project.models.class_models.ClassResponse;
import com.example.project.sharepreference.SharedPreferencesManager;
import com.google.gson.Gson;

import java.util.List;

public class ClassFragment extends Fragment {
    private ClassAdapter classAdapter;
    private ClassAdapterTrainerTrainee classAdapterTrainerTrainee;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ClassViewModel classViewModel = new ViewModelProvider(this).get(ClassViewModel.class);
        String token = "";
        String role = "";
        String userName ="";
        View root;
        String finalToken = token;


        if (SharedPreferencesManager.getLoginResponseValue(requireContext()) != null) {
            token = SharedPreferencesManager.getLoginResponseValue(requireContext()).getToken();
            role = SharedPreferencesManager.getLoginResponseValue(requireContext()).getRole();
            userName = SharedPreferencesManager.getLoginResponseValue(requireContext()).getUsername();
        }

        //put token to @Header
        classViewModel.classes(finalToken);
        classViewModel.trainerTraineeClass(token,role,userName);
        classAdapter = new ClassAdapter();
        classAdapterTrainerTrainee = new ClassAdapterTrainerTrainee();

        if (role.equals("Admin")) {
            root = inflater.inflate(R.layout.fragment_class, container, false);

            ImageButton buttonAdd = root.findViewById(R.id.btn_add);
            buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).navigate(R.id.action_nav_class_to_class_addClass);
                }
            });

        } else {
            root = inflater.inflate(R.layout.fragment_class_trainer_trainee, container, false);
        }


        //
        classAdapter.setClassDelete(new ClassAdapter.ClassDelete() {
            @Override
            public void onDelete(int id) {
                classViewModel.deleteClass(finalToken,id);
            }
        });


        classAdapter.setClassEdit(new ClassAdapter.ClassEdit() {
            @Override
            public void onEdit(int id) {
                classViewModel.getGetClassInfoResponseLiveData().observe(getViewLifecycleOwner(), new Observer<ClassResponse>() {
                    @Override
                    public void onChanged(ClassResponse classResponse) {
                        Gson gson = new Gson();
                        String classResponseData = gson.toJson(classResponse);
                        Bundle bundle = new Bundle();
                        bundle.putString("CLASS_DATA", classResponseData);
                        Navigation.findNavController(root).navigate(R.id.action_nav_class_to_class_editClass, bundle);
                    }
                });
                classViewModel.getClassInfo(finalToken,id);
            }
        });

        classAdapterTrainerTrainee.setClassDetail(new ClassAdapterTrainerTrainee.ClassDetail() {
            @Override
            public void onDetail(int id) {
                Bundle bundle = new Bundle();
                bundle.putInt("CLASS_ID", id);
                Navigation.findNavController(root).navigate(R.id.action_nav_class_to_class_detailClass,bundle);
            }
        });

        RecyclerView recyclerView = root.findViewById(R.id.rv_classList);

        if (role.equals("Admin")) {
            recyclerView.setAdapter(classAdapter);
            classViewModel.getClassResponseLiveData().observe(getViewLifecycleOwner(), (Observer<List<ClassResponse>>) classResponseList -> {
                classAdapter.setClassResponseList(classResponseList);
            });
        } else {
            recyclerView.setAdapter(classAdapterTrainerTrainee);
            classViewModel.getTrainerClassResponseLiveData().observe(getViewLifecycleOwner(), (Observer<List<ClassResponse>>) trainerClassResponseList -> {
                classAdapterTrainerTrainee.setTrainerClassResponseList(trainerClassResponseList);
            });
        }
        return root;
    }

}