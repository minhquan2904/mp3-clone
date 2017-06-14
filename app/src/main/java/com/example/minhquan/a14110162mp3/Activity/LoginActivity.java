package com.example.minhquan.a14110162mp3.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.minhquan.a14110162mp3.Activity.Fragment.FragmentOnline;
import com.example.minhquan.a14110162mp3.Database.Webservices;
import com.example.minhquan.a14110162mp3.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {
    Button btn_login;
    EditText edt_login_email;
    EditText edt_login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = (Button) findViewById(R.id.btn_login);
        edt_login_email = (EditText) findViewById(R.id.edt_login_email);
        edt_login_password = (EditText) findViewById(R.id.edt_login_password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "user/checkLogin/"+ edt_login_email.getText().toString()+"/"+edt_login_password.getText().toString();

                Webservices webservices = new Webservices(url);
                webservices.execute();

                String JSON = null;
                try {
                    JSON = webservices.get();
                    JSONObject object = new JSONObject(JSON);
                    Log.d("JSON",object.getString("success"));
                    if(JSON != null)
                    {
                        String result = object.getString("success");

                        if(result.equals("true"))
                        {
                            Intent myIntent = new Intent(LoginActivity.this, OnlineMusicActivity.class);
                            myIntent.putExtra("name",edt_login_email.getText().toString());
                            startActivity(myIntent);
                        }
                        else
                        {

                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }
}
