package com.example.gqbulk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class promotionItemAdapter extends RecyclerView.Adapter<promotionItemAdapter.ViewHolder> {

    ArrayList<bulkItem> bulkList;
    promotions mainActivity;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView,priceTextView,originalPriceTextView,lblProduct,txtTimer;
        private final TextView txtCountDown;

        private ImageView ic;
        ImageView img;
        Button register;
        public ViewHolder(View itemView) {

            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.imageView3);
            nameTextView=(TextView)itemView.findViewById(R.id.lblProdName);
            originalPriceTextView=(TextView)itemView.findViewById(R.id.lblOPrice);
            priceTextView=(TextView)itemView.findViewById(R.id.lblDiscountPrice);
            txtCountDown=(TextView)itemView.findViewById(R.id.CountDown);
            lblProduct=(TextView)itemView.findViewById(R.id.txtProduct);
            register=(Button)itemView.findViewById(R.id.btnRegister);
            txtTimer=(TextView)itemView.findViewById(R.id.txtTimer);
            register.setVisibility(View.GONE);
            txtCountDown.setVisibility(View.GONE);
        }


    }


    @NonNull
    @Override
    public promotionItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.itemlist, parent, false);

        // Return a new holder instance
        promotionItemAdapter.ViewHolder viewHolder = new promotionItemAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if(position==1)
        {
            holder.img.setImageResource(R.drawable.download);


        }
        else if(position==2)
        {
            holder.img.setImageResource(R.drawable.shampoo);


        }
        else {
            holder.img.setImageResource(R.drawable.bag);

        }

        holder.nameTextView.setText("Category: "+bulkList.get(position).getCategory());
        holder.originalPriceTextView.setText("Before Discount: "+ bulkList.get(position).getOriginal_Price());
        holder.lblProduct.setText(bulkList.get(position).getProduct());
        holder.priceTextView.setText("After Discount :"+bulkList.get(position).getDiscounted_Price());




    }

    public promotionItemAdapter(promotions mainActivity, ArrayList<bulkItem> bulkList)
    {
        this.mainActivity=mainActivity;
        this.bulkList=bulkList;
    }
    @Override
    public int getItemCount() {
        return bulkList.size();
    }
    public void register(String prod)
    {
        db.collection("BulkProduct")
                .whereEqualTo("Product", prod)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DocumentReference ref = db.collection("BulkProduct").document(document.getId());
                                ref.update("Target", "19");
                                Log.e("success", "success");
                            }
                        }
                    }
                });
    }

}
