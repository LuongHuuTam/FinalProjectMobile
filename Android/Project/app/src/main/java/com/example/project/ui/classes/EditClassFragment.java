package com.example.project.ui.classes;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.project.R;
import com.example.project.adapters.ClassAdapter;
import com.example.project.models.ClassRequest;

import com.example.project.models.ClassResponse;
import com.example.project.sharepreference.SharedPreferencesManager;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;
import java.util.Locale;

public class EditClassFragment extends Fragment {
    ClassViewModel classViewModel;
    View root;
    TextInputEditText classname;
    TextInputLayout layoutClassname;
    TextInputEditText capacity;
    TextInputLayout layoutCapacity;
    TextInputEditText startDate;
    TextInputLayout layoutStartDate;
    TextInputEditText endDate;
    TextInputLayout layoutEndDate;
    Button buttonAdd;
    String token;
    Calendar dateTimePicker;
    AlertDialog.Builder alertDialogBuilder;
    ClassResponse classResponse;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        root = inflater.inflate(R.layout.fragment_class_edit_class, container, false);
        classViewModel = new ViewModelProvider(this).get(ClassViewModel.class);
        classname = root.findViewById(R.id.edit_class_name);
        capacity = root.findViewById(R.id.edit_capacity);
        startDate = root.findViewById(R.id.edit_start_day);
        endDate = root.findViewById(R.id.edit_end_day);
        buttonAdd = root.findViewById(R.id.btn_save);
        dateTimePicker = Calendar.getInstance();
        alertDialogBuilder = new AlertDialog.Builder(requireContext());

        layoutClassname = root.findViewById(R.id.outlinedClassName);
        layoutCapacity = root.findViewById(R.id.outlinedTextCapacity);
        layoutStartDate = root.findViewById(R.id.outlinedTextStartDay);
        layoutEndDate = root.findViewById(R.id.outlinedTextEndDay);

        startDate.setEnabled(false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Gson gson = new Gson();
            String classData = bundle.getString("CLASS_DATA", "");
            Type token = new TypeToken<ClassResponse>() {
            }.getType();
            classResponse = gson.fromJson(classData, token);
            if (classResponse != null) {
                classname.setText(classResponse.getName());
                capacity.setText(Integer.toString(classResponse.getCapacity()));
                startDate.setText(classResponse.getStartTime().substring(0,10));
                endDate.setText(classResponse.getEndTime().substring(0,10));
            }
        }

        DatePickerDialog.OnDateSetListener startDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                dateTimePicker.set(Calendar.YEAR, year);
                dateTimePicker.set(Calendar.MONTH, month);
                dateTimePicker.set(Calendar.DAY_OF_MONTH, day);
                updateLabelStartDay();
            }
        };

        DatePickerDialog.OnDateSetListener endDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                dateTimePicker.set(Calendar.YEAR, year);
                dateTimePicker.set(Calendar.MONTH, month);
                dateTimePicker.set(Calendar.DAY_OF_MONTH, day);
                updateLabelEndDay();
            }
        };

//        startDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new DatePickerDialog(getContext(), startDatePicker, dateTimePicker
//                        .get(Calendar.YEAR), dateTimePicker.get(Calendar.MONTH),
//                        dateTimePicker.get(Calendar.DAY_OF_MONTH)).show();
//            }
//        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), endDatePicker, dateTimePicker
                        .get(Calendar.YEAR), dateTimePicker.get(Calendar.MONTH),
                        dateTimePicker.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


//        layoutStartDate.setError("Start day must be after current day");
//        layoutEndDate.setError("End day must be after current day and start day");

        classname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (classname.getText().toString().equals("")) {
                    layoutClassname.setError("Please enter class name and less than 255 characters");

                } else {
                    layoutClassname.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        capacity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (capacity.getText().toString().equals("") || capacity.getText().toString().equals("0")) {
                    layoutCapacity.setError("Please enter capacity in integer and larger than 0");
                } else {
                    layoutCapacity.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        if (SharedPreferencesManager.getLoginResponseValue(requireContext()) != null) {
            token = SharedPreferencesManager.getLoginResponseValue(requireContext()).getToken();
        }

        classViewModel.getUpdateClassResponseLiveData().observe(getViewLifecycleOwner(),
                (Observer<Void>) updateClassResponse -> {
                    alertDialogBuilder.setTitle("Success");
                    alertDialogBuilder.setMessage("Update class successful")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Navigation.findNavController(root).navigate(R.id.nav_class);
                                }
                            });
                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();
                    Toast.makeText(getContext(), "Update class successful !", Toast.LENGTH_LONG).show();
                });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(classname.getText().toString()) || TextUtils.isEmpty(capacity.getText().toString()) || TextUtils.isEmpty(startDate.getText().toString()) || TextUtils.isEmpty(endDate.getText().toString())) {
                    alertDialogBuilder.setTitle("Error");
                    alertDialogBuilder.setMessage("All field is required !")
                            .setCancelable(false)
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

// show it
                    alertDialog.show();
                } else {
                    updateClass();
                }
            }
        });


        return root;
    }

    public void updateClass() {
        ClassRequest classRequest = new ClassRequest();
        classRequest.setName(classname.getText().toString());
        classRequest.setCapacity(Integer.parseInt(capacity.getText().toString()));
        classRequest.setStartTime(startDate.getText().toString());
        classRequest.setEndTime(endDate.getText().toString());

        if (token != null) {
//            Do update
            classViewModel.updateClass(token, classResponse.getClassID(), classRequest);
        }

    }

    private void updateLabelStartDay() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        startDate.setText(sdf.format(dateTimePicker.getTime()));
    }

    private void updateLabelEndDay() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        endDate.setText(sdf.format(dateTimePicker.getTime()));
    }

}
