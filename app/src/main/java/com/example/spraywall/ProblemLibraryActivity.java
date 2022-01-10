package com.example.spraywall;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProblemLibraryActivity extends AppCompatActivity {

    Button toMain;
    ListView problemTable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_library_activity);

        toMain = findViewById(R.id.to_Main);
        toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.redirectActivity(ProblemLibraryActivity.this, MainActivity.class);
            }
        });
    }
}
