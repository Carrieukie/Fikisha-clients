package com.example.fikisha;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fikisha.Models.User;
import com.example.fikisha.SharedPrefs.Login;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {

    private static final int PICTURE_RESULT = 42;
    TextInputEditText name,idNumber;
    CircleImageView circleImageView;
    FirebaseStorage mStorage;
    StorageReference mStorageRef;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_edit_settings);
        name = findViewById(R.id.Name);
        idNumber = findViewById(R.id.IdNumber);
        progressBar = findViewById(R.id.progressBar_Imageupload);
        mStorage = FirebaseStorage.getInstance();
        mStorageRef = mStorage.getReference().child("deals_pictures");
        circleImageView = findViewById(R.id.circleImageViewAccount);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(intent.createChooser(intent, "Insert Picture"), PICTURE_RESULT);
            }
        });

        setupinFo();
    }

    private void setupinFo() {
        Login login = new Login();
        String mname = login.get(getApplicationContext(),"name");
        String mId = login.get(getApplicationContext(),"id");
        String mImage = login.get(getApplicationContext(),"img");

        if (mname != "Not Found"){
            name.setText(mname);
        }

        if (mId != "Not Found"){
            idNumber.setText(mId);
        }

        if (mImage != "Not Found"){
            showImage(mImage);
        }
    }


    public void SaveAccount(View view) {
        progressBar.setVisibility(View.VISIBLE);

        final Map<String,String> user = new HashMap<>();
        user.put("name",name.getText().toString());
        user.put("nationalId",idNumber.getText().toString());

        final User muser = new User();
        muser.setName(name.getText().toString());
        muser.setId(idNumber.getText().toString());

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();

                new Login().saveObject(getApplicationContext(),muser);
                progressBar.setVisibility(View.INVISIBLE);
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICTURE_RESULT && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            final StorageReference ref = mStorageRef.child(imageUri.getLastPathSegment());
            progressBar.setVisibility(View.VISIBLE);

            final Task uploadTask = ref.putFile(imageUri);

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    String message = e.toString();
                    Toast.makeText(getApplicationContext(), "Error: " + message, Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }

                            return ref.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                String downloadImageUrl = task.getResult().toString();
                                new Login().setString(getApplicationContext(),downloadImageUrl);
                                progressBar.setVisibility(View.INVISIBLE);
                                showImage(downloadImageUrl);
                            }
                        }
                    });

                }
            });
        }

    }

    private void showImage(String downloadImageUrl) {
            if (downloadImageUrl != null && downloadImageUrl.isEmpty() == false) {

                progressBar.setVisibility(View.VISIBLE);

                int width = Resources.getSystem().getDisplayMetrics().widthPixels;
                Picasso.get()
                        .load(downloadImageUrl)
                        .resize(width, width*2/3)
                        .centerCrop()
                        .into(circleImageView);

                progressBar.setVisibility(View.INVISIBLE);

            }
    }

}

