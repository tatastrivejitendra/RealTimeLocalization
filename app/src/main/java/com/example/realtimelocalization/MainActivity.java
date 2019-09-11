package com.example.realtimelocalization;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button changelang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadLocale();

        changelang = findViewById(R.id.button3);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        changelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showchangelanguagedialog();

            }
        });
    }

    private void showchangelanguagedialog() {
        final String[] listitems = {"English", "हिंदी", "मराठी"};
        final AlertDialog.Builder mbuilder = new AlertDialog.Builder(MainActivity.this);
        mbuilder.setTitle("Change Language");
        mbuilder.setSingleChoiceItems(listitems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0) {
                    setLocale("En");
                    recreate();

                } else if (i == 1) {
                    setLocale("Hi");
                    recreate();

                } else if (i == 2) {
                    setLocale("Mr");
                    recreate();
                }

                dialog.dismiss();
            }
        });

        AlertDialog mdialog = mbuilder.create();
        mdialog.show();


    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("settings", MODE_PRIVATE).edit();
        editor.putString("My Lang", lang);
        editor.apply();


    }

    public void loadLocale() {
        SharedPreferences sharedPreferences = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        String language = sharedPreferences.getString("My_Lang","");
        setLocale(language);


    }
}


