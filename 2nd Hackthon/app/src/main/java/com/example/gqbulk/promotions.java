package com.example.gqbulk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class promotions extends AppCompatActivity {

    //reuse bulkitem class and its adapater
    private ArrayList<bulkItem> bulkPromotion=new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    promotionItemAdapter sAdapt;
    RecyclerView bRecycleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotions);
        getALL();
    }
    public void getALL()
    {
        db.collection("Promotions")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                bulkItem ss = document.toObject(bulkItem.class);
                                ss.setCategory(document.getString("Category"));
                                ss.setDiscounted_Price(document.getString("Discounted_Price"));
                                ss.setOriginal_Price(document.getString("Original_Price"));
                                ss.setProduct(document.getString("Product"));

                                bulkPromotion.add(ss);
                                sAdapt=new promotionItemAdapter(promotions.this,bulkPromotion);
                                bRecycleView=(RecyclerView)findViewById(R.id.promotionRecycle);
                                bRecycleView.setAdapter(sAdapt);
                                bRecycleView.setLayoutManager(new LinearLayoutManager(promotions.this));
                            }

                        } else {
                            // Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
