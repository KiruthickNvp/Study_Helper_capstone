package com.kiruthicknvp.studyhelper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText name;
    EditText pass;
    EditText repass;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitializeUI();

    }
    private void AlertBoxMsg(String msg,View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setCancelable(true);
        builder.setTitle("Alert");
        builder.setMessage(msg);
        builder.show();
    }
    private void CallMainPage(){
        Intent intent = new Intent(this, Main_Page_Activity.class);
        startActivity(intent);
    }
    private void MakeToast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

    private void InitializeUI() {
        setContentView(R.layout.activity_sign_up);
        DBHelper db = new DBHelper(this);
        name = findViewById(R.id.Name);
        pass = findViewById(R.id.Pass);
        repass = findViewById(R.id.ReEnterPass);
        signup = findViewById(R.id.changepass);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usrName = name.getText().toString();
                String UsrPass = pass.getText().toString();
                String RePass = repass.getText().toString();
                if(UsrPass.equals(RePass))
                {
                    if(db.InsertTable(usrName,UsrPass))
                    {

                        MakeToast("Account Created Successfully");
                        CallMainPage();
                    }
                    else
                    {
                        AlertBoxMsg("Sorry!,Account not created, maybe you can try a another uniqie username",v);
                    }
                }
                else{
                    AlertBoxMsg("PassWord Does Not Matching",v);
                }

            }
        });
    }
}