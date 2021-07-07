package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private EditText name, age;
    private RadioButton radioMale, radioFemale;
    private RadioGroup radioGroupGender;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.edit1);
        age = findViewById(R.id.edit2);
        radioFemale = findViewById(R.id.radioFemale);
        radioMale = findViewById(R.id.radioMale);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        prefs = getSharedPreferences("MySharedPref", MODE_PRIVATE);
    }

    public void saveContent(View view) {
        if (name.getText().toString().equals("") || age.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "Enter the details", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences.Editor myEdit = prefs.edit();
            myEdit.putString("name", name.getText().toString());
            myEdit.putInt("age", Integer.parseInt(age.getText().toString()));
            myEdit.putBoolean("gender_female", radioFemale.isChecked());
            myEdit.putBoolean("gender_male", radioMale.isChecked());
            myEdit.apply();
        }
    }

    public void showContent(View view) {

        String s1 = prefs.getString("name", "");
        int a = prefs.getInt("age", 0);
        boolean f = prefs.getBoolean("gender_female", false);
        boolean m = prefs.getBoolean("gender_male", false);
        name.setText(s1);
        age.setText(String.valueOf(a));
        radioGroupGender.clearCheck();
        radioFemale.setChecked(f);
        radioMale.setChecked(m);
    }

    public void clearContent(View view) {
        name.setText("");
        age.setText("");
        radioFemale.setChecked(false);
        radioMale.setChecked(false);
    }
}