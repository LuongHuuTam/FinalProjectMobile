package com.example.project.ui.module;

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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.project.R;
import com.example.project.models.FeedbackResponse;
import com.example.project.models.module_models.ModuleRequest;
import com.example.project.models.module_models.ModuleResponse;
import com.example.project.sharepreference.SharedPreferencesManager;
import com.example.project.ui.feedback.FeedBackViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class EditModuleFragment extends Fragment {
    ModuleViewModel moduleViewModel;
    FeedBackViewModel feedBackViewModel;
    View root;
    TextView header;
    TextInputEditText ed_module_name;
    TextInputEditText ed_start_date;
    TextInputEditText ed_end_date;
    TextInputEditText ed_admin_id;
    AutoCompleteTextView feedback_title_selector;
    TextInputEditText ed_feedback_start_date;
    TextInputEditText ed_feedback_end_date;
    TextInputLayout layout_module_name;
    TextInputLayout layout_start_date;
    TextInputLayout layout_end_date;
    TextInputLayout layout_feedback_start_date;
    TextInputLayout layout_feedback_end_date;
    Button btn_save;
    Button btn_back;
    String token;
    Calendar dateTimePicker;
    AlertDialog.Builder alertDialogBuilder;
    ModuleResponse moduleResponse;

    @SuppressLint("SetTextI18n")
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_module_edit_module, container, false);
        moduleViewModel = new ViewModelProvider(this).get(ModuleViewModel.class);
        feedBackViewModel = new ViewModelProvider(this).get(FeedBackViewModel.class);

        if (SharedPreferencesManager.getLoginResponseValue(requireContext()) != null) {
            token = SharedPreferencesManager.getLoginResponseValue(requireContext()).getToken();
        }
        header = root.findViewById(R.id.module_header_title);
        ed_module_name = root.findViewById(R.id.add_module_name);
        ed_start_date = root.findViewById(R.id.add_start_day);
        ed_end_date = root.findViewById(R.id.add_end_day);
        ed_admin_id = root.findViewById(R.id.admin_id);
        feedback_title_selector = root.findViewById(R.id.feedback_title_selector);
        ed_feedback_start_date = root.findViewById(R.id.add_feedback_start_day);
        ed_feedback_end_date = root.findViewById(R.id.add_feedback_end_day);

        layout_module_name = root.findViewById(R.id.outlinedTextModuleName);
        layout_start_date = root.findViewById(R.id.outlinedTextStartDay);
        layout_end_date = root.findViewById(R.id.outlinedTextEndDay);
        layout_feedback_start_date = root.findViewById(R.id.outlinedTextFeedBackStartDay);
        layout_feedback_end_date = root.findViewById(R.id.outlinedTextFeedBackEndDay);

        btn_save = root.findViewById(R.id.btn_save);
        dateTimePicker = Calendar.getInstance();
        alertDialogBuilder = new AlertDialog.Builder(requireContext());

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Gson gson = new Gson();
            String moduleData = bundle.getString("MODULE_DATA", "");
            Type token = new TypeToken<ModuleResponse>() {
            }.getType();
            moduleResponse = gson.fromJson(moduleData, token);
            if (moduleResponse != null) {
                header.setText("Edit Module");
                ed_module_name.setText(moduleResponse.getName());
                ed_admin_id.setText(moduleResponse.getAdminID());
                ed_start_date.setText(moduleResponse.getStartTime().substring(0, 10));
                ed_end_date.setText(moduleResponse.getEndTime().substring(0, 10));
                ed_feedback_start_date.setText(moduleResponse.getFeedbackStartTime().substring(0, 10));
                ed_feedback_end_date.setText(moduleResponse.getFeedbackEndTime().substring(0, 10));
            }
        }


        ed_module_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (ed_module_name.getText().toString().equals("")) {
                    layout_module_name.setError("Please enter class name and less than 255 characters");
                } else
                    layout_module_name.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

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

        DatePickerDialog.OnDateSetListener feedbackStartDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                dateTimePicker.set(Calendar.YEAR, year);
                dateTimePicker.set(Calendar.MONTH, month);
                dateTimePicker.set(Calendar.DAY_OF_MONTH, day);
                updateFeedBackLabelStartDay();
            }
        };

        DatePickerDialog.OnDateSetListener feedBackEndDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                dateTimePicker.set(Calendar.YEAR, year);
                dateTimePicker.set(Calendar.MONTH, month);
                dateTimePicker.set(Calendar.DAY_OF_MONTH, day);
                updateFeedBackLabelEndDay();
            }
        };

        ed_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), startDatePicker, dateTimePicker
                        .get(Calendar.YEAR), dateTimePicker.get(Calendar.MONTH),
                        dateTimePicker.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ed_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), endDatePicker, dateTimePicker
                        .get(Calendar.YEAR), dateTimePicker.get(Calendar.MONTH),
                        dateTimePicker.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ed_feedback_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), feedbackStartDatePicker, dateTimePicker
                        .get(Calendar.YEAR), dateTimePicker.get(Calendar.MONTH),
                        dateTimePicker.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ed_feedback_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), feedBackEndDatePicker, dateTimePicker
                        .get(Calendar.YEAR), dateTimePicker.get(Calendar.MONTH),
                        dateTimePicker.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //Check if User click edit

        moduleViewModel.getUpdateModuleResponseLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                alertDialogBuilder.setTitle("Success");
                alertDialogBuilder.setMessage("Update Module Successful")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Navigation.findNavController(root).navigate(R.id.nav_module);
                            }
                        });
                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(ed_module_name.getText().toString()) || TextUtils.isEmpty(ed_start_date.getText().toString()) || TextUtils.isEmpty(ed_end_date.getText().toString()) || TextUtils.isEmpty(ed_feedback_start_date.getText().toString()) || TextUtils.isEmpty(ed_end_date.getText().toString())) {
                    alertDialogBuilder.setTitle("Error");
                    alertDialogBuilder.setMessage("Please check your input")
                            .setCancelable(false)
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                } else {
                    updateModule();
                }
            }
        });
        return root;
    }

    public void updateModule() {
        ModuleRequest moduleRequest = new ModuleRequest();
        moduleRequest.setAdminID(ed_admin_id.getText().toString());
        moduleRequest.setName(ed_module_name.getText().toString());
        moduleRequest.setStartTime(ed_start_date.getText().toString());
        moduleRequest.setEndTime(ed_end_date.getText().toString());
        moduleRequest.setFeedbackTitle(feedback_title_selector.getText().toString());
        moduleRequest.setFeedbackStartTime(ed_feedback_start_date.getText().toString());
        moduleRequest.setFeedbackEndTime(ed_feedback_end_date.getText().toString());

        if (token != null) {
            moduleViewModel.updateModule(token, moduleResponse.getModuleID(), moduleRequest);
        }
    }

    private void updateLabelStartDay() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        ed_start_date.setText(sdf.format(dateTimePicker.getTime()));
    }

    private void updateLabelEndDay() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        ed_end_date.setText(sdf.format(dateTimePicker.getTime()));
    }

    private void updateFeedBackLabelStartDay() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        ed_feedback_start_date.setText(sdf.format(dateTimePicker.getTime()));
    }

    private void updateFeedBackLabelEndDay() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        ed_feedback_end_date.setText(sdf.format(dateTimePicker.getTime()));
    }
}
