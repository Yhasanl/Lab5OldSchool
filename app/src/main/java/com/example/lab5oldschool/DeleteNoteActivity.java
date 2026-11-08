package com.example.lab5oldschool;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Map;

public class DeleteNoteActivity extends AppCompatActivity {

    ListView lvDeleteNotes;
    ArrayList<String> notesList;
    ArrayAdapter<String> adapter;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        lvDeleteNotes = findViewById(R.id.lvDeleteNotes);
        notesList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        lvDeleteNotes.setAdapter(adapter);

        sharedPreferences = getSharedPreferences("MyNotes", MODE_PRIVATE);
        loadNotes();

        lvDeleteNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String noteTitle = notesList.get(position);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(noteTitle);
                editor.apply();

                Toast.makeText(DeleteNoteActivity.this, "Note deleted!", Toast.LENGTH_SHORT).show();
                loadNotes();
            }
        });
    }

    private void loadNotes() {
        notesList.clear();
        Map<String, ?> allNotes = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allNotes.entrySet()) {
            notesList.add(entry.getKey());
        }
        adapter.notifyDataSetChanged();
    }
}