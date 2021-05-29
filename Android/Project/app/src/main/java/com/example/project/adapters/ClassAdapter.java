package com.example.project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.models.ClassResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {
    private List<ClassResponse> classResponseList = new ArrayList();
    private ClassListener classListener;
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
        public ImageButton buttonEdit;
        public ImageButton buttonDelete;

        public ClassViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            classID = itemView.findViewById(R.id.class_id);
            className = itemView.findViewById(R.id.class_name);
            capacity = itemView.findViewById(R.id.capacity);
            startDate = itemView.findViewById(R.id.startday);
            endDate = itemView.findViewById(R.id.enddate);

            buttonEdit = itemView.findViewById(R.id.btn_edit);
            buttonDelete = itemView.findViewById(R.id.btn_delete);
        }

        public void bind(ClassResponse classResponse) {
            classID.setText(Integer.toString(classResponse.getClassID()));
            className.setText(classResponse.getName());
            capacity.setText(Integer.toString(classResponse.getCapacity()));
            startDate.setText(classResponse.getStartTime());
            endDate.setText(classResponse.getEndTime());

            buttonEdit.setOnClickListener(view -> {

            });
            buttonDelete.setOnClickListener(view -> {
                if(classListener!=null){
                    classListener.onDelete(classResponse.getClassID());
                }
            });
        }

    }

    public void setClassResponseList(List<ClassResponse> classResponseList) {
        this.classResponseList = classResponseList;

        notifyDataSetChanged();
    }
    public interface ClassListener{
        void onDelete(int id);
    }

    public void setClassListener(ClassListener classListener) {
        this.classListener = classListener;
    }
}
