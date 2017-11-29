package com.example.lina.hala;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_FROM_CAMERA = 100;
    private static final int  PICK_FROM_GALLARY = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void openCamera(View view) {
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            try {
                startActivityForResult(intent, PICK_FROM_CAMERA);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        }}



    public void openGallary(View view) {




        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_FROM_GALLARY);

    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;
        switch (requestCode) {
            case PICK_FROM_CAMERA:
                Bitmap photo = null;
                Bundle extras = data.getExtras();
                if (extras != null) {
                    photo = extras.getParcelable("data");
                }
                Intent i = new Intent(this, EditPhotoActivity.class);
                i.putExtra("image", photo);
                startActivity(i);

            case PICK_FROM_GALLARY:

                Bitmap selectedphoto   = null;
                Uri uri = data.getData();
                String [] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String filePath = cursor.getString(columnIndex);
                selectedphoto = BitmapFactory.decodeFile(filePath);
                cursor.close();
                Intent intent = new Intent(this,EditPhotoActivity.class);
                //intent.putExtra("data", uri);
                intent.setData( uri );
                startActivity(intent);


                break;
        }
    }}
