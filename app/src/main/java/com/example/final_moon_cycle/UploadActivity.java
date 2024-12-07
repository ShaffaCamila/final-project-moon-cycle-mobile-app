package com.example.final_moon_cycle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UploadActivity extends AppCompatActivity {

    EditText uploadQuestion;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        // Inisialisasi komponen UI
        uploadQuestion = findViewById(R.id.uploadQuestion);
        saveButton = findViewById(R.id.saveButton);

        // Tombol untuk menyimpan data
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    public void saveData() {
        // Mengambil teks pertanyaan dari EditText
        String question = uploadQuestion.getText().toString().trim();

        // Validasi input
        if (question.isEmpty()) {
            Toast.makeText(this, "Pertanyaan tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Membuat referensi ke Firebase Database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReference("Questions");

        // Membuat ID unik untuk setiap pertanyaan
        String questionId = databaseReference.push().getKey();

        // Membuat objek DataClass2 untuk menyimpan data
        DataClass2 dataClass2 = new DataClass2(question);

        // Menyimpan data ke Firebase Database
        databaseReference.child(questionId).setValue(dataClass2)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(UploadActivity.this, "Pertanyaan berhasil disimpan!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(UploadActivity.this, "Gagal menyimpan pertanyaan!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
