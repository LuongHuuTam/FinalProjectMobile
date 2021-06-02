package com.example.project.ui.assignment;

import android.os.Bundle;
import android.util.Log;
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
import com.example.project.adapters.AssignmentAdapter;
import com.example.project.models.Assignment;
import com.example.project.models.AssignmentRequest;
import com.example.project.models.ModuleResponse;
import com.example.project.sharepreference.SharedPreferencesManager;
import com.google.gson.Gson;

import java.util.List;

public class AssignmentFragment extends Fragment {

    private AssignmentViewModel assignmentViewModel;
    private AssignmentAdapter assignmentAdapter;
    //get token
    String token = "";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        assignmentViewModel = new ViewModelProvider(this).get(AssignmentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_assignment, container, false);
        ImageButton btnAdd = root.findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.addAssignmentFragment);
            }
        });

        if (SharedPreferencesManager.getLoginResponseValue(requireContext()) != null) {
            token = SharedPreferencesManager.getLoginResponseValue(requireContext()).getToken();
        }

        //put token to Header
        assignmentViewModel.assignments(token);
        assignmentAdapter = new AssignmentAdapter();

        //
        assignmentAdapter.setAssignmentDelete(new AssignmentAdapter.AssignmentDelete() {
            @Override
            public void onDelete(int moduleID, int classID, String trainerID) {
                if(token !=null){
                    AssignmentRequest assignmentRequest = new AssignmentRequest();
                    assignmentRequest.setModuleID(moduleID);
                    assignmentRequest.setClassId(classID);
                    assignmentRequest.setTrainerID(trainerID);
                    assignmentViewModel.deleteAssignment(token, assignmentRequest);
                }
                Toast.makeText(root.getContext(), "Delete success", Toast.LENGTH_SHORT).show();
            }
        });


        RecyclerView recyclerView = root.findViewById(R.id.rv_assignment_list);
        recyclerView.setAdapter(assignmentAdapter);

        assignmentViewModel.getAssignmentLiveData().observe(getViewLifecycleOwner(),
                (Observer<List<Assignment>>) assignmentList -> {
            assignmentAdapter.setAssignmentList(assignmentList);
            Log.i( "ASSIGNMENT DATA LIST", "123"+assignmentList.toString()+"123");

        });


        return root;
    }
}