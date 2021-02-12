package com.sibi.firebaseabackgounddatamessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    TextView title,message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title=findViewById(R.id.title);
        message=findViewById(R.id.message);


        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(
                instanceIdResult -> {
                    String newToken = instanceIdResult.getToken();
                    Log.d(">>","Token"+newToken);
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i(">>", "onFailure : " + e.toString());
            }
        });


        if(getIntent().getExtras() != null){
            for(String key: getIntent().getExtras().keySet()){
                if(key.equals("title")){
                    title.setText(getIntent().getStringExtra(key));
                }
                if(key.equals("message")){
                    message.setText(getIntent().getStringExtra(key));
                }
            }
        }
    }


}