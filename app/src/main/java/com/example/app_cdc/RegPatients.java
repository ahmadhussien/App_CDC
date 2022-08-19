package com.example.app_cdc;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegPatients extends AppCompatActivity {
    EditText ID_Patients,First_Name,Last_Name,gender,level;
    Button submit;
    MyDataBase myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_patients);
        myDb=new MyDataBase(this);
        ID_Patients=(EditText) findViewById(R.id.ET_idp);
        First_Name=(EditText) findViewById(R.id.ET_fName);
        Last_Name=(EditText) findViewById(R.id.ED_lNAme);
        gender=(EditText) findViewById(R.id.ET_sex);
        level=(EditText)findViewById(R.id.ET_level);
        submit=(Button) findViewById(R.id.b_submit);
        submit();

    }
    public void backr(View v) {
        // Do something in response to button click
        Intent i = new Intent(RegPatients.this, MainActivity.class);
        startActivity(i);
    }
    public  void  submit(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ID_Patients.getText().toString().trim().length() == 0 && First_Name.getText().toString().trim().length() == 0 && Last_Name.getText().toString().trim().length() == 0 && gender.getText().toString().trim().length() == 0  && level.getText().toString().trim().length() == 0){
                    ID_Patients.setError("Your message");
                    First_Name.setError("Your message");
                    Last_Name.setError("Your message");
                    gender.setError("Your message");
                    level.setError("Your message");
                    Toast.makeText(RegPatients.this,"Data not is Inserted ",Toast.LENGTH_LONG).show();
                    Intent  b= new Intent(RegPatients.this, MainActivity.class);
                    startActivity(b);
                    myDb.close();


                }
                else {
                    Cursor res = myDb.search2(ID_Patients.getText().toString());
                    if(res!=null){
                        Toast.makeText(RegPatients.this,"ID is Exist ",Toast.LENGTH_LONG).show();

                    }


                    else {


                        myDb.insertData(Integer.parseInt(ID_Patients.getText().toString()), First_Name.getText().toString(), Last_Name.getText().toString(), gender.getText().toString(), level.getText().toString());
                        Toast.makeText(RegPatients.this, "Data is Inserted ", Toast.LENGTH_LONG).show();
                        myDb.close();
                    }


                }

            }
        });
    }
}