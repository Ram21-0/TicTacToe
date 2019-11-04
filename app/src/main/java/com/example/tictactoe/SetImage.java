package com.example.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class SetImage extends AppCompatActivity {

    ImageView image1, image2;
    EditText name1, name2;
    private final int GALLERY_REQUEST_CODE = 1;
    int called = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_img_activity);

        image1 = findViewById(R.id.player1);
        image2 = findViewById(R.id.player2);
        name1 = findViewById(R.id.name1);
        name2 = findViewById(R.id.name2);

        Picasso.with(this).load(PlayerInfo.img1).centerCrop().resize(350, 350).into(image1);
        Picasso.with(this).load(PlayerInfo.img2).centerCrop().resize(350, 350).into(image2);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                called = 1;
                pickImage();
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                called = 2;
                pickImage();
            }
        });

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerInfo.name1 = name1.getText().toString().trim();
                if (PlayerInfo.name1.isEmpty()) PlayerInfo.name1 = "INSTA";
                PlayerInfo.name2 = name2.getText().toString().trim();
                if (PlayerInfo.name2.isEmpty()) PlayerInfo.name2 = "YOUTUBE";
                startActivity(new Intent(SetImage.this, MainActivity.class));
            }
        });
        findViewById(R.id.restore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerInfo.setToDefault();
                startActivity(new Intent(SetImage.this, MainActivity.class));
            }
        });
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == GALLERY_REQUEST_CODE) {
            Uri imgFromGallery = data.getData();
            if (called == 1) PlayerInfo.img1 = imgFromGallery;
            if (called == 2) PlayerInfo.img2 = imgFromGallery;
            Picasso.with(this).load(imgFromGallery).centerCrop().resize(350, 350).into(called == 1 ? image1 : image2);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
