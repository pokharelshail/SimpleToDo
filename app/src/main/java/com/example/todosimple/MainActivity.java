package com.example.todosimple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import org.apache.commons.io.FileUtils;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> items_myapp;

    Button Add_Button;
    EditText Edit_Text;
    RecyclerView Rec_View;
    Items_Adapter items_myappadapterAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Add_Button = findViewById(R.id.Add_Button);
        Edit_Text = findViewById(R.id.Edit_Text);
        Rec_View = findViewById(R.id.Rec_View);

        loaditems();

        Items_Adapter.Onlongclicklistener onLongclicklistener = new Items_Adapter.Onlongclicklistener(){

            @Override
            public void onItemLongClicked(int position) {
                items_myapp.remove(position);
                items_myappadapterAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(), "Removed!", Toast.LENGTH_SHORT).show();
                saveItems();
            }
        };

        items_myappadapterAdapter = new Items_Adapter(items_myapp, onLongclicklistener);
        Rec_View.setAdapter(items_myappadapterAdapter);
        Rec_View.setLayoutManager(new LinearLayoutManager(this));

        Add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String todoItem = Edit_Text.getText().toString();
                items_myapp.add(todoItem);
                Edit_Text.setText("");
                Toast.makeText(getApplicationContext(), "Added!", Toast.LENGTH_SHORT).show();
                saveItems();
            }
        });
    }

    private File getDataFile(){
        return new File(getFilesDir(), "data.txt");
    }

    private void loaditems() {
        try {
            items_myapp = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e) {
            Log.e("MainActivity", "Error reading", e);
            items_myapp = new ArrayList<>();
        }
    }

    private void saveItems(){
        try {
            FileUtils.writeLines(getDataFile(), items_myapp);
        } catch (IOException e) {
            Log.e("MainActivity", "Error writing", e);
        }


    }
}