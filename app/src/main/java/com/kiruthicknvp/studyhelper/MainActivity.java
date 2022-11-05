package com.kiruthicknvp.studyhelper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHelper db = new DBHelper(this);
    private EditText usrname,usrpass;
    private Button loginBtn,forgotpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usrname = findViewById(R.id.usrname);
        usrpass = findViewById(R.id.usrpass);
        forgotpass = findViewById(R.id.forgotpass);
        loginBtn = findViewById(R.id.loginBTN);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = usrname.getText().toString();
                String Pass = usrpass.getText().toString();
                if(db.GetData(Name,Pass))
                {
                    MakeToast("Logged in Successfully");
                    CallMainPage();
                }
                else{
                    AlertBoxMsg("Sorry!, wrong credentials or user may not exsits",v);
                }
            }
        });
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallForgotPassPage();
            }
        });
    }
    private void CallMainPage(){
        Intent intent = new Intent(this, Main_Page_Activity.class);
        startActivity(intent);
    }
    private void CallForgotPassPage(){
        Intent intent = new Intent(this,ForgotPassword.class);
        startActivity(intent);
    }

    private void MakeToast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
    private void AlertBoxMsg(String msg,View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setCancelable(true);
        builder.setTitle("Alert");
        builder.setMessage(msg);
        builder.show();
    }



    public void signup(View view) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}