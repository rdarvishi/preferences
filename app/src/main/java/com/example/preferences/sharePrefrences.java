package com.example.preferences;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sharePrefrences extends AppCompatActivity {

    EditText name, lastName, age;
    Button btn_save,btn_load;

    SharedPreferences shdpref ;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        shdpref = getSharedPreferences("pref1",MODE_PRIVATE);

    }

    private void init() {
        name = findViewById(R.id.Input_firstName);
        lastName = findViewById(R.id.Input_lastName);
        age = findViewById(R.id.Input_age);
        btn_save = findViewById(R.id.save);
        btn_load = findViewById(R.id.load);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first_name = name.getText().toString();
                String last_name = lastName.getText().toString();
                String ageStr = age.getText().toString();

                if (!first_name.isEmpty() && !last_name.isEmpty() && !ageStr.isEmpty()){

                    SharedPreferences.Editor editor = shdpref.edit();

                    editor.putString("n",first_name);
                    editor.putString("l",last_name);
                    editor.putInt("a",Integer.valueOf(ageStr));

                    editor.apply();
                    Toast.makeText(getApplicationContext(), "data saved", Toast.LENGTH_SHORT).show();


                }


            }
        });

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstname = shdpref.getString("n","not found");
                String lastname = shdpref.getString("l","not found");
                Integer age = shdpref.getInt("a",-1);

                Toast.makeText(sharePrefrences.this, firstname + lastname + age, Toast.LENGTH_SHORT).show();



                new AlertDialog.Builder(sharePrefrences.this)
                        .setTitle("shardpref")
                        .setMessage("first name : "+firstname +"\n"
                        +  "last name : "+lastname +"\n"
                        + "age : " +(age == -1 ?"not found":age )) // اگر سن 1- شد یعنی پیدا نشده وگرنه همون سن رو بزار

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

//                new AlertDialog.Builder(sharePrefrences.this).setTitle("pref value")
//                        .setMessage("first name : "+firstname +"\n"
//                                +  "last name : "+lastname +"\n"
//                                + "age : " +age ) // اگر سن 1- شد یعنی پیدا نشده وگرنه همون سن رو بزار
//                        .show();


            }
        });
    }

    }
