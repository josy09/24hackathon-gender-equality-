package com.example.gqbulk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class bulkUI extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<String> Name=new ArrayList<>();
    private ArrayList<String> Price=new ArrayList();
    private ArrayList<String> discountPrice=new ArrayList<>();
    private ArrayList<String> ID=new ArrayList<>();
    private ArrayList<bulkItem> bindBulk=new ArrayList<>();
    private ArrayList<String> bulkUser=new ArrayList<>();

    ItemListAdapter sAdapt;
    RecyclerView bRecycleView;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulk_ui);
        getBulkAll();


    }


    public void getBulkAll()
    {
        db.collection("BulkProduct")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                bulkItem s = document.toObject(bulkItem.class);
                                s.setCategory(document.getString("Category"));
                                s.setDiscounted_Price(document.getString("Discounted_Price"));
                                s.setOriginal_Price(document.getString("Original_Price"));
                                s.setTarget(document.getString("Target"));
                                s.setProduct(document.getString("Product"));
                                s.setId((ArrayList<String>)document.get("ID"));


                                bindBulk.add(s);
                                sAdapt=new ItemListAdapter(bulkUI.this,bindBulk);
                                bRecycleView=(RecyclerView)findViewById(R.id.rescchedule);
                                bRecycleView.setAdapter(sAdapt);
                                bRecycleView.setLayoutManager(new LinearLayoutManager(bulkUI.this));
                            }
                            Log.e("testing", Integer.toString(bulkUser.size()));
                        } else {
                           // Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

}
