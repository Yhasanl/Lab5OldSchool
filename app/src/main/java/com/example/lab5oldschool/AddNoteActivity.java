package com.example.lab5oldschool;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    EditText etNoteTitle, etNoteContent;
    Button btnSaveNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        etNoteTitle = findViewById(R.id.etNoteTitle);
        etNoteContent = findViewById(R.id.etNoteContent);
        btnSaveNote = findViewById(R.id.btnSaveNote);

        btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etNoteTitle.getText().toString().trim();
                String content = etNoteContent.getText().toString().trim();

                if (title.isEmpty() || content.isEmpty()) {
                    Toast.makeText(AddNoteActivity.this, getString(R.string.error_empty), Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sharedPreferences = getSharedPreferences("MyNotes", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString(title, content);
                    editor.apply();

                    Toast.makeText(AddNoteActivity.this, "Note saved!", Toast.LENGTH_SHORT).show();

                    finish();
                }
            }
        });
    }
}