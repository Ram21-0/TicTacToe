package com.example.tictactoe;

import android.net.Uri;

public class PlayerInfo {

    public static String name1 = "INSTA", name2 = "YOUTUBE";
    public static Uri img1 = Uri.parse("android.resource://com.example.tictactoe/drawable/insta"),
                      img2 = Uri.parse("android.resource://com.example.tictactoe/drawable/youtube");

    public static void setToDefault() {
        name1 = "INSTA";
        name2 = "YOUTUBE";
        img1 = Uri.parse("android.resource://com.example.tictactoe/drawable/insta");
        img2 = Uri.parse("android.resource://com.example.tictactoe/drawable/youtube");
    }
}