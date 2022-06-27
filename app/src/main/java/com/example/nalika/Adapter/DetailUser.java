package com.example.nalika.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nalika.Model.User;
import com.example.nalika.R;

import java.util.List;

public class DetailUser extends RecyclerView.Adapter<DetailUser.MyViewHolder> {

    private Context context;
    private List<User> list;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int post);
    }
    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }
    public DetailUser(Context context , List<User> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.alamat.setText(list.get(position).getAlamat());
        holder.telepon.setText(list.get(position).getNo_telepon());
        holder.namaorder.setText(list.get(position).getNamaorder());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,alamat,telepon,namaorder;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.nama);
            alamat = itemView.findViewById(R.id.alamat);
            telepon = itemView.findViewById(R.id.telepon);
            namaorder = itemView.findViewById(R.id.namaorder);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog!= null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}

