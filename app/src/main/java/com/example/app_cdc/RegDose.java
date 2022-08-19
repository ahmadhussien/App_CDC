package com.example.app_cdc;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;





        import androidx.appcompat.app.AppCompatActivity;

public class RegDose extends AppCompatActivity {
    EditText ID_Patients,Name_of_Dose,DDUnit,date;
    Button submit;
    MyDataBase myDb;
    TextView Unit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regdose);
        myDb=new MyDataBase(this);
        ID_Patients=(EditText) findViewById(R.id.ET_idp);
        Name_of_Dose=(EditText) findViewById(R.id.ET_name_of_Dos);
        DDUnit=(EditText) findViewById(R.id.ET_Dose_Unit);
        date=(EditText) findViewById(R.id.ET_Date);
        Unit=(TextView)findViewById(R.id.TV_Unit);
        DDUnit.setText(Double.toString(calculates.finalResDDU));
        Unit.setText(calculates.finalunit);
        submit=(Button) findViewById(R.id.b_submit);
        submit();
    }


    public void backr(View v) {
        // Do something in response to button click
        Intent i = new Intent(RegDose.this, calculates.class);
        startActivity(i);
    }
    public  void  submit(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ID_Patients.getText().toString().trim().length() == 0 && Name_of_Dose.getText().toString().trim().length() == 0  && date.getText().toString().trim().length() == 0){
                    ID_Patients.setError("Your message");
                    Name_of_Dose.setError("Your message");
                    DDUnit.setError("Your message");
                    date.setError("Your message");
                    Toast.makeText(RegDose.this,"Data not is Inserted ",Toast.LENGTH_LONG).show();
                    Intent  b= new Intent(RegDose.this, calculates.class);
                    startActivity(b);


                }
                else {
                    Cursor res = myDb.search2(ID_Patients.getText().toString());
                    if (res == null) {
                        Toast.makeText(RegDose.this, "ID is Exist ", Toast.LENGTH_LONG).show();

                    } else {


                        myDb.insertData2(Integer.parseInt(ID_Patients.getText().toString()), Name_of_Dose.getText().toString(), DDUnit.getText().toString(), Unit.getText().toString(), date.getText().toString());
                        Toast.makeText(RegDose.this, "Data is Inserted ", Toast.LENGTH_LONG).show();


                    }

                }
            }


        });
    }
}