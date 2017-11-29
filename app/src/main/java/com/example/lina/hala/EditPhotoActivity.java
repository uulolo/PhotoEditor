package com.example.lina.hala;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.net.URI;

public class EditPhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_photo);


        camera();
         gallary();
    }
    protected void camera(){

        Bitmap bitmap  = getIntent().getExtras().getParcelable("image");
        ImageView imge = (ImageView) findViewById(R.id.view_photo);



        imge.setImageBitmap(bitmap);
    }
    protected void gallary() {

        ImageView imageview = (ImageView)findViewById(R.id.view_photo);
       // URI imageUri = getIntent().getData();

        Bitmap selectedphoto  =(Bitmap)this.getIntent().getParcelableExtra("data");
        imageview.setImageBitmap(selectedphoto);
    }

}
