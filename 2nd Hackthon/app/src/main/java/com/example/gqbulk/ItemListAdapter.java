package com.example.gqbulk;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

    ArrayList<bulkItem> bulkList;
    bulkUI mainActivity;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView,priceTextView,originalPriceTextView,lblProduct;
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

            countDownStart();
            }


        }


    @NonNull
    @Override
    public ItemListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.itemlist, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }
    public ItemListAdapter(bulkUI mainActivity, ArrayList<bulkItem> bulkList)
    {
        this.mainActivity=mainActivity;
        this.bulkList=bulkList;
    }
    @Override
    public void onBindViewHolder(@NonNull final ItemListAdapter.ViewHolder holder, final int position) {
        if(position==1)
        {
            holder.img.setImageResource(R.drawable.download);
            final Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    handler.postDelayed(this, 1000);
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat(
                                "yyyy-MM-dd");
                        // Please here set your event date//YYYY-MM-DD
                        Date futureDate = dateFormat.parse("2022-2-18");
                        Date currentDate = new Date();
                        if (!currentDate.after(futureDate)) {
                            long diff = futureDate.getTime()
                                    - currentDate.getTime();
                            long days = diff / (24 * 60 * 60 * 1000);
                            diff -= days * (24 * 60 * 60 * 1000);
                            long hours = diff / (60 * 60 * 1000);
                            diff -= hours * (60 * 60 * 1000);
                            long minutes = diff / (60 * 1000);
                            diff -= minutes * (60 * 1000);
                            long seconds = diff / 1000;
                            holder.txtCountDown.setText("" + String.format("%02d", days+"days")+String.format("%02d", hours)+"hours Left");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            handler.postDelayed(runnable, 1 * 1000);

        }
        else if(position==2)
        {
            holder.img.setImageResource(R.drawable.shampoo);
            final Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    handler.postDelayed(this, 1000);
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat(
                                "yyyy-MM-dd");
                        // Please here set your event date//YYYY-MM-DD
                        Date futureDate = dateFormat.parse("2022-2-19");
                        Date currentDate = new Date();
                        if (!currentDate.after(futureDate)) {
                            long diff = futureDate.getTime()
                                    - currentDate.getTime();
                            long days = diff / (24 * 60 * 60 * 1000);
                            diff -= days * (24 * 60 * 60 * 1000);
                            long hours = diff / (60 * 60 * 1000);
                            diff -= hours * (60 * 60 * 1000);
                            long minutes = diff / (60 * 1000);
                            diff -= minutes * (60 * 1000);
                            long seconds = diff / 1000;
                            holder.txtCountDown.setText("" + String.format("%02d", days+"days")+String.format("%02d", hours)+"hours Left");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            handler.postDelayed(runnable, 1 * 1000);

        }
        else {
            holder.img.setImageResource(R.drawable.bag);
            final Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    handler.postDelayed(this, 1000);
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat(
                                "yyyy-MM-dd");
                        // Please here set your event date//YYYY-MM-DD
                        Date futureDate = dateFormat.parse("2022-2-18");
                        Date currentDate = new Date();
                        if (!currentDate.after(futureDate)) {
                            long diff = futureDate.getTime()
                                    - currentDate.getTime();
                            long days = diff / (24 * 60 * 60 * 1000);
                            diff -= days * (24 * 60 * 60 * 1000);
                            long hours = diff / (60 * 60 * 1000);
                            diff -= hours * (60 * 60 * 1000);
                            long minutes = diff / (60 * 1000);
                            diff -= minutes * (60 * 1000);
                            long seconds = diff / 1000;
                            holder.txtCountDown.setText("" + String.format("%02d", days+"days")+String.format("%02d", hours)+"hours Left");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            handler.postDelayed(runnable, 1 * 1000);

        }

        holder.nameTextView.setText("Category: "+bulkList.get(position).getCategory());
        holder.originalPriceTextView.setText("Before Discount: "+ bulkList.get(position).getOriginal_Price());
        holder.lblProduct.setText(bulkList.get(position).getProduct());
        holder.priceTextView.setText("After Discount :"+bulkList.get(position).getDiscounted_Price());


        int i=0;
        for(i=0;i<bulkList.get(position).getId().size();i++)
        {
            String j=String.valueOf(Integer.parseInt(bulkList.get(position).getTarget())-i);
            holder.txtCountDown.setText("Register now to enjoy this fantastic deal!"+"\n"+j+" registration left");

        }
        holder.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("Submit","You have successfully register! We will" +" notify you once again to confirm with your purchase later");

            }
        });

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
                                ref.update("Product", "lily");
                                Log.e("success", "success");
                            }
                        }
                    }
                });
    }

    public void countDownStart() {

    }
}
