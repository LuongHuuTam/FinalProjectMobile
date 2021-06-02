package com.example.project.ui.assignment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.project.R;
import com.example.project.adapters.class_adapters.ClassDetailAdapter;
import com.example.project.models.Assignment;
import com.example.project.models.AssignmentRequest;
import com.example.project.models.TrainerResponse;
import com.example.project.models.class_models.ClassResponse;
import com.example.project.sharepreference.SharedPreferencesManager;
import com.example.project.ui.User.TrainerViewModel;
import com.example.project.ui.classes.ClassViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddAssignmentFragment extends Fragment {
    AssignmentViewModel assignmentViewModel;
    ClassViewModel classViewModel;
    TrainerViewModel trainerViewModel;
    AutoCompleteTextView actxtModuleName;
    AutoCompleteTextView actxtClassName;
    AutoCompleteTextView actxtTrainerID;

    TextInputLayout layoutModuleName;
    TextInputLayout layoutClassName;
    TextInputLayout layoutTrainerID;

    View root;
    String token;
    Button btnSave, btnBack;

    List<Assignment> assignmentList = new ArrayList<>();
    List<ClassResponse> classList = new ArrayList<>();
    List<TrainerResponse> trainerList = new ArrayList<>();
    HashMap<Integer, String> hmModule = new HashMap<Integer, String>();
    HashMap<Integer, String> hmClass = new HashMap<Integer, String>();
    HashMap<String, String> hmTrainer = new HashMap<String, String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         root = inflater.inflate(R.layout.fragment_add_assignment, container, false);
         assignmentViewModel = new ViewModelProvider(this).get(AssignmentViewModel.class);
         classViewModel = new ViewModelProvider(this).get(ClassViewModel.class);
         trainerViewModel = new ViewModelProvider(this).get(TrainerViewModel.class);

        if(SharedPreferencesManager.getLoginResponseValue(requireContext())!=null){
            token = SharedPreferencesManager.getLoginResponseValue(requireContext()).getToken();
        }

        actxtModuleName = root.findViewById(R.id.module_name_selector);
        actxtClassName = root.findViewById(R.id.class_name_selector);
        actxtTrainerID = root.findViewById(R.id.trainer_id_selector);

        layoutModuleName = root.findViewById(R.id.menu_module_name);
        layoutClassName = root.findViewById(R.id.menu_class_name);
        layoutTrainerID = root.findViewById(R.id.menu_trainer_id);

        btnSave = root.findViewById(R.id.btn_save);
        btnBack = root.findViewById(R.id.btn_back);

        assignmentViewModel.assignments(token);
        assignmentViewModel.getAssignmentLiveData().observe(getViewLifecycleOwner(),
                (Observer<List<Assignment>>) assignmentList ->{
            this.assignmentList = assignmentList;
            Log.i( "ASSIGNMENT DATA LIST", "123"+assignmentList.toString()+"123");
            for(int i = 0; i<this.assignmentList.size();i++){
                hmModule.put(this.assignmentList.get(i).getModuleID(), this.assignmentList.get(i).getModuleName());
            }
            String[] modules = hmModule.values().toArray(new String[0]);
            ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), R.layout.list_item, modules);
            actxtModuleName.setText(arrayAdapter.getItem(0).toString(),false);
            actxtModuleName.setAdapter(arrayAdapter);
        });

        classViewModel.classes(token);
        classViewModel.getClassResponseLiveData().observe(getViewLifecycleOwner(), (Observer<List<ClassResponse>>) classResponseList ->{
            classList = classResponseList;
            for(int i=0; i<classList.size();i++){
                hmClass.put(classList.get(i).getClassID(), classList.get(i).getName());
            }
            //String array contains all class name
            String[] classes = hmClass.values().toArray(new String[0]);
            ArrayAdapter arrayAdapter2 = new ArrayAdapter(getContext(), R.layout.list_item, classes);
            actxtClassName.setText(arrayAdapter2.getItem(0).toString(),false);
            actxtClassName.setAdapter(arrayAdapter2);
        });

        trainerViewModel.trainer(token);
        trainerViewModel.getTrainerResponseLiveData().observe(getViewLifecycleOwner(),
                (Observer<List<TrainerResponse>>) trainerResponseList->{
            trainerList = trainerResponseList;
            for(int i=0; i<trainerList.size();i++){
                hmTrainer.put(trainerList.get(i).getUserName(), trainerList.get(i).getName());
            }
            String[] trainers = hmTrainer.values().toArray(new String[0]);
            ArrayAdapter arrayAdapter3 = new ArrayAdapter(getContext(), R.layout.list_item, trainers );
            actxtTrainerID.setText(arrayAdapter3.getItem(0).toString(), false);
            actxtTrainerID.setAdapter(arrayAdapter3);

        });



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAssignment();
            }
        });
        return root;
    }
    private boolean isExisted(int moduleID, int classID, String trainerID)
    {
        for(int i=0;i<this.assignmentList.size();i++){
            Assignment assignment = assignmentList.get(i);
            if(moduleID == assignment.getModuleID() && classID == assignment.getClassID() && trainerID.equals(assignment.getTrainerID())){
                return true;
            }
        }
        return false;
    }
    public void addAssignment(){
        AssignmentRequest assignmentRequest = new AssignmentRequest();
        Integer moduleID = null, classID = null;
        String trainerID = null;
        moduleID = Integer.parseInt(getKeyFromValue(hmModule, actxtModuleName.getText().toString()).toString());
        classID = Integer.parseInt(getKeyFromValue(hmClass, actxtClassName.getText().toString()).toString());
        trainerID = getKeyFromValue(hmTrainer, actxtTrainerID.getText().toString()).toString();
        assignmentRequest.setModuleID(moduleID);
        assignmentRequest.setClassId(classID);
        assignmentRequest.setTrainerID(trainerID);

        if(token!=null){
            assignmentViewModel.addAssignment(token, assignmentRequest);
        }
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