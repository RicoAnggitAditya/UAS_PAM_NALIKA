package com.example.nalika;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.nalika.Adapter.DetailUser;
import com.example.nalika.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TampilanOrder extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton btnAdd;
    private FirebaseFirestore db =  FirebaseFirestore.getInstance();

    private List<User> list = new ArrayList<>();
    private DetailUser userAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilan_order);

        recyclerView = findViewById(R.id.recycler_view);
        btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),Home.class);
                startActivity(intent1);
            }
        });


        progressDialog = new ProgressDialog(TampilanOrder.this);
        progressDialog.setTitle("Loading...");
        progressDialog.setTitle("Mengambil data");
        userAdapter = new DetailUser(getApplicationContext(),list);


        userAdapter.setDialog(new DetailUser.Dialog() {
            @Override
            public void onClick(int post) {
                final CharSequence[] dialogItem = {"Edit", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(TampilanOrder.this);

                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            /* bagian untuk edit pindah ke halaman detail order*/
                            case 0:
                                Intent intent = new Intent(getApplicationContext(), DetailOrder.class);
                                intent.putExtra("id", list.get(post).getId());
                                intent.putExtra("name", list.get(post).getName());
                                intent.putExtra("alamat", list.get(post).getAlamat());
                                intent.putExtra("no_telepon", list.get(post).getNo_telepon());
                                intent.putExtra("namaorder", list.get(post).getNamaorder());
                                startActivity(intent);
                                break;
                            case 1:
                                /*bagian delete*/
                                deleteData(list.get(post).getId());
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });

        /*memnggil data adapter dari detail user*/
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(userAdapter);


    }

    /*Firebase Delate*/
    private void deleteData(String id){
        progressDialog.show();
        db.collection("users").document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Data Gagal di untuk Hapuss!!!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(TampilanOrder.this, " Selamat Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                        getData();
                    }
                });

    }

    /*untuk menampilkan data dihalaman pertama tampilan order*/
    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }

    /*untuk menampilkan seluruh data*/
    private void getData(){
        progressDialog.show();

        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifiyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        if (task.isSuccessful()){

                            for (QueryDocumentSnapshot document : task.getResult()){

                                /*memanggil nama, alamat, telpon, nama order di kelas USer*/
                                User user = new User(document.getString("name"), document.getString("alamat"), document.getString("no_telepon"),
                                        document.getString("namaorder"));
                                user.setId(document.getId());
                                list.add(user);
                            }
                            userAdapter.notifyDataSetChanged();
                        }else {
                            Toast.makeText(getApplicationContext(),"Data Gagal di Ambil!!!!!....",Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

}