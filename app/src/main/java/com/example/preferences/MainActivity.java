package com.example.preferences;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class MainActivity<privete> extends AppCompatActivity {

    EditText name, lastName, age;
    Button btn_save,btn_load;

    SharedPreferences pref ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        pref = getPreferences(MODE_PRIVATE);





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

                   SharedPreferences.Editor editor = pref.edit();

                   editor.putString("n",first_name);
                   editor.putString("l",last_name);
                   editor.putInt("a",Integer.valueOf(ageStr));

                   editor.apply();
                    Toast.makeText(MainActivity.this, "data saved", Toast.LENGTH_SHORT).show();


                }


            }
        });

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstname = pref.getString("n","not found");
                String lastname = pref.getString("l","not found");
                Integer age = pref.getInt("a",-1);

                new AlertDialog.Builder(MainActivity.this).setTitle("shedpref value")
                        .setMessage("first name : "+firstname +"\n"
                              +  "last name : "+lastname +"\n"
                               + "age : " +(age == -1 ?"not found":age )) // اگر سن 1- شد یعنی پیدا نشده وگرنه همون سن رو بزار
                        .show();


            }
        });
    }

    //pref screen
   private void laodAppPref(){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String username =pref.getString("username","");
        String userbio =pref.getString("userbio","");
        boolean viewImages = pref.getBoolean("viewimages",false);
        boolean notificatoin = pref.getBoolean("notif",true);



                        new AlertDialog.Builder(this).setTitle("user info")
                    .setMessage("user name : " + username + "\n" +
                            "" +
                            "your bio : " + userbio + "\n" +
                            "show images : " + (viewImages == true ?"yes":"No" ) + "\n" +
                            "show notification : " + (notificatoin ?"yes":"No" ) )
                    .show();




   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        menu.add("sharePrefrences").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                startActivity( new Intent(MainActivity.this,sharePrefrences.class));
                return false;
            }
        });
        menu.add("dilog").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")

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

return false ;
            }
        });

        menu.add("Setting").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                startActivity(new Intent(MainActivity.this,SettingActivity.class));
                return false;
            }
        });

        menu.add("load app pref").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                laodAppPref();
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
