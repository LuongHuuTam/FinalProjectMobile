package com.example.project.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.models.ClassResponse;
import com.example.project.ui.login.LoginActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {
    private List<ClassResponse> classResponseList = new ArrayList();
    private ClassDelete classDelete;
    private ClassEdit classEdit;
    @NonNull
    @NotNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class, parent, false);
        return new ClassViewHolder(view);
    }

    //set up information
    @Override
    public void onBindViewHolder(@NonNull @NotNull ClassViewHolder holder, int position) {
        holder.bind(classResponseList.get(position));
    }

    @Override
    public int getItemCount() {
        return classResponseList.size();
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder {
        public TextView classID;
        public TextView className;
        public TextView capacity;
        public TextView startDate;
        public TextView endDate;
        public ImageButton buttonAdd;
        public ImageButton buttonEdit;
        public ImageButton buttonDelete;
        AlertDialog.Builder alertDialogBuilder;

        public ClassViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            classID = itemView.findViewById(R.id.class_id);
            className = itemView.findViewById(R.id.class_name);
            capacity = itemView.findViewById(R.id.capacity);
            startDate = itemView.findViewById(R.id.startday);
            endDate = itemView.findViewById(R.id.enddate);

            buttonEdit = itemView.findViewById(R.id.btn_edit);
            buttonDelete = itemView.findViewById(R.id.btn_delete);

            alertDialogBuilder = new AlertDialog.Builder(itemView.getContext());
        }

        public void bind(ClassResponse classResponse) {

            classID.setText(Integer.toString(classResponse.getClassID()));
            className.setText(classResponse.getName());
            capacity.setText(Integer.toString(classResponse.getCapacity()));
            startDate.setText(classResponse.getStartTime().substring(0,10));
            endDate.setText(classResponse.getEndTime().substring(0,10));

            buttonEdit.setOnClickListener(view -> {
                if(classEdit!=null){
                    classEdit.onEdit(classResponse.getClassID());
                }
            });

            buttonDelete.setOnClickListener(view -> {
                if(classDelete!=null){
                    alertDialogBuilder.setTitle("Delete");
                    alertDialogBuilder.setMessage("Are you sure you want to delete this class")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    classDelete.onDelete(classResponse.getClassID());
                                    Toast.makeText(itemView.getContext(), "Delete success", Toast.LENGTH_SHORT).show();

                                }
                            })
                            .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

// show it
                    alertDialog.show();

                }
            });
        }

    }

    public void setClassResponseList(List<ClassResponse> classResponseList) {
        this.classResponseList = classResponseList;
        notifyDataSetChanged();
    }

    public List<ClassResponse> getClassResponseList() {
        return classResponseList;
    }

    public interface ClassDelete{
        void onDelete(int id);
    }

    public void setClassDelete(ClassDelete classDelete) {
        this.classDelete = classDelete;
    }

    public interface ClassEdit{
        void onEdit(int id);
    }

    public void setClassEdit(ClassEdit classEdit) {
        this.classEdit = classEdit;
    }
}
