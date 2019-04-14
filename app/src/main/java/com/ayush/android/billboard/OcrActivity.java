package com.ayush.android.billboard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class OcrActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST=1888;

    Bitmap photo;
    TextView textView;
    Bundle extras;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ocr);

        textView = (TextView) findViewById(R.id.txtOCR);
        DBHandler db = new DBHandler(this);

        extras = getIntent().getExtras();
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);

        db.addExpense(new Expenses(1, "Restaurant", 500));
        db.addExpense(new Expenses(2, "Sports", 1000));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAMERA_REQUEST) {
            photo = (Bitmap) data.getExtras().get("data");
        }
        imageOcr();
    }

    protected void imageOcr() {
        TextRecognizer txtRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!txtRecognizer.isOperational()) {
            textView.setText("TextRecogniizer not working");
        }
        else {
            Frame frame = new Frame.Builder().setBitmap(photo).build();
            SparseArray items = txtRecognizer.detect(frame);
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < items.size(); i++)
            {
                TextBlock item = (TextBlock)items.valueAt(i);
                strBuilder.append(item.getValue());
                strBuilder.append("/");
            }
            textView.setText(strBuilder.toString());
        }
    }
}
