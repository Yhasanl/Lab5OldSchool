package com.example.lab5oldschool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView lvNotes;
    ArrayList<String> notesList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvNotes = findViewById(R.id.lvNotes);
        notesList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        lvNotes.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    private void loadNotes() {
        notesList.clear();
        SharedPreferences sharedPreferences = getSharedPreferences("MyNotes", MODE_PRIVATE);
        Map<String, ?> allNotes = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : allNotes.entrySet()) {
            notesList.add(entry.getKey());
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            Intent intent = new Intent(this, AddNoteActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.menu_delete) {
            Intent intent = new Intent(this, DeleteNoteActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}