package com.example.project.ui.contact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.project.R;
import com.example.project.ui.enrollment.EnrollmentViewModel;

public class ContactFragment extends Fragment {
    private ContactViewModel contactViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contactViewModel =
                new ViewModelProvider(this).get(ContactViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contact, container, false);
        return root;
    }
}
