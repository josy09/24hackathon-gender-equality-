package com.example.gqbulk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Login extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
          btn=(Button)findViewById(R.id.Lbutton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText LUsername = (EditText)findViewById(R.id.LUsernameET);

             /*   Toast toast= Toast. makeText(getApplicationContext(),LUsername.getText(),Toast. LENGTH_SHORT);
                toast. show();*/

                db.collection("Users")
                        .whereEqualTo("LoginID", LUsername.getText().toString())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d("GDQUID", "DocumentSnapshot successfully updated!"+document.getString("Password"));
                                        String name=document.getString("Password");
                                        Toast toast = Toast.makeText(getApplicationContext(),name,Toast.LENGTH_SHORT);
                                        toast.show();
                                        Intent myIntent = new Intent(getBaseContext(), bulkUI.class);
                                        startActivity(myIntent);
                                }

                                } else {
                                    Log.d("error", "Error getting documents: ", task.getException());
                                }
                            }
                        });


            }
        });
    }
}
