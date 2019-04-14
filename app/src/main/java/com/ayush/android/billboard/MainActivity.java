package com.ayush.android.billboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    FloatingActionButton fabOCR;
    TextView textView;
    TableLayout tableLayout;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        fabOCR = (FloatingActionButton) findViewById(R.id.fabOCR);

        fabOCR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ocrIntent = new Intent(MainActivity.this, OcrActivity.class);
                startActivity(ocrIntent);
            }
        });
    }

}