package com.example.spraywall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    Bitmap bitmap;
    ImageView problem;
    Button toLibrary, addProblem;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    DbBitmapUtility image_converter = new DbBitmapUtility();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        problem = findViewById(R.id.problem_image);
        toLibrary = findViewById(R.id.to_library);
        toLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProblemLibraryActivity.class);
                startActivity(intent);
            }
        });
        addProblem = findViewById(R.id.add_problem);
        addProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // insert data into Local Database
                Bitmap icon = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                        R.mipmap.wayup);

                boolean insert_status = databaseHelper.insertData(image_converter.getBytes(icon));


                // Alert user about the status of the added problem
                if(insert_status){
                    Toast.makeText(getApplicationContext(),"Problem successfully added.", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Failed to add the problem.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    // Helper function for managing redirects
    public static void redirectActivity(Activity activity, Class aClass) {
        // Initialize intent
        Intent intent = new Intent(activity, aClass);
        // Set Flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Start the activity
        activity.startActivity(intent);
    }

    // Helper class for converting image to bytes and back to Bitmap
    public static class DbBitmapUtility {

        // convert from bitmap to byte array
        public byte[] getBytes(Bitmap bitmap) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
            return stream.toByteArray();
        }

        // convert from byte array to bitmap
        public Bitmap getImage(byte[] image) {
            return BitmapFactory.decodeByteArray(image, 0, image.length);
        }
    }

}