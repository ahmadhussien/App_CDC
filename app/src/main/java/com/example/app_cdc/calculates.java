package com.example.app_cdc;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;


public class calculates extends AppCompatActivity {
     RadioGroup radioGroup_BBD,radioGroup_weight,radioGroup_height,radioGroup_formula,radioGroup_DoseUnit;
    RadioButton BBD_u1,BBD_u2,BBD_u3,height_u1,height_u2,height_u3,weight_u1,weight_u2,formula_u1,formula_u2,DoseUnit_u1,DoseUnit_u2,DoseUnit_u3;
     Button result,reset;
    TextView TVresult;
    EditText ET_BBD,ET_weight,ET_height;
    double DDU=0;
    double resDDU=0;
    static double finalResDDU;
    static String finalunit;
    double resHeight,resBBD,resWeight;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculates);
        TVresult = (TextView) findViewById(R.id.Result);
        result = (Button)findViewById(R.id.b_Result);
        reset = (Button)findViewById(R.id.b_Reset);
        radioGroup_BBD=(RadioGroup)findViewById(R.id.radioGroup_BBD);
        radioGroup_weight=(RadioGroup)findViewById(R.id.radioGroup_Weight);
        radioGroup_height=(RadioGroup)findViewById(R.id.radioGroup_Height);
        radioGroup_formula=(RadioGroup)findViewById(R.id.radioGroup_Formula);
        radioGroup_DoseUnit=(RadioGroup)findViewById(R.id.radioGroup_Dose);

        BBD_u1=(RadioButton)findViewById(R.id.BBD_U1);
        BBD_u2=(RadioButton)findViewById(R.id.BBD_U2);
        BBD_u3=(RadioButton)findViewById(R.id.BBD_U3);
        height_u1=(RadioButton)findViewById(R.id.Height_U1);
        height_u2=(RadioButton)findViewById(R.id.Height_U2);
        height_u3=(RadioButton)findViewById(R.id.Height_U3);
        weight_u1=(RadioButton)findViewById(R.id.Weight_U1);
        weight_u2=(RadioButton)findViewById(R.id.Weight_U2);
        formula_u1=(RadioButton)findViewById(R.id.Formula_U1);
        formula_u2=(RadioButton)findViewById(R.id.Formula_U2);
        DoseUnit_u1=(RadioButton)findViewById(R.id.Dose_U1);
        DoseUnit_u2=(RadioButton)findViewById(R.id.Dose_U2);
        DoseUnit_u3=(RadioButton)findViewById(R.id.Dose_U3);

        ET_BBD=(EditText) findViewById(R.id.ET_BBD);
        ET_weight=(EditText) findViewById(R.id.ET_Weight);
        ET_height=(EditText) findViewById(R.id.ET_Height);
        result();





    }
    public static double convertBBD(double inBBD,RadioGroup radioGroup_BBD, RadioButton BBD_u1,RadioButton BBD_u2,RadioButton BBD_u3){
        int selectedId = radioGroup_BBD.getCheckedRadioButtonId();


            if (selectedId==BBD_u1.getId())
                inBBD=inBBD/1000000;
            else if (selectedId==BBD_u2.getId())
                    inBBD=inBBD/1000000000;
            else if (selectedId==BBD_u3.getId())
                    inBBD=inBBD/1000;
            else
                inBBD=inBBD;



        return  inBBD;
    }
    public static double convertWeight(double inWeight,RadioGroup radioGroup_weight,RadioButton weight_u1, RadioButton weight_u2){
        int selectedId = radioGroup_weight.getCheckedRadioButtonId();


            if(selectedId==weight_u1.getId())
                    inWeight=inWeight;
            else if(selectedId==weight_u2.getId())
                    inWeight=inWeight*0.45359237 ;
            else
                inWeight=inWeight;

        return  inWeight;
    }

    public static double convertHeight(double inHeight,RadioGroup radioGroup_height,RadioButton height_u1, RadioButton height_u2,RadioButton height_u3){
        int selectedId = radioGroup_height.getCheckedRadioButtonId();

        if (selectedId==height_u1.getId())
                    inHeight=inHeight;
        else if (selectedId==height_u2.getId())
                    inHeight=inHeight*100;
        else if (selectedId==height_u3.getId())
                    inHeight=inHeight*2.54;
        else
            inHeight=inHeight;
        return  inHeight;
    }
    public static String selectFormula(RadioGroup radioGroup_formula,RadioButton formula_u1,RadioButton formula_u2){
        int selectedId = radioGroup_formula.getCheckedRadioButtonId();
        String inFormula = null;


           if (selectedId==formula_u1.getId())
               inFormula="Dubois";
           else if (selectedId==formula_u2.getId())
                    inFormula="Mosteller";
           else
               inFormula=" ";
        return inFormula ;
    }
    public static double convertDDU(double inDDU,RadioGroup radioGroup_DoseUnit,RadioButton DoseUnit_u1,RadioButton DoseUnit_u2,RadioButton DoseUnit_u3){
        int selectedId = radioGroup_DoseUnit.getCheckedRadioButtonId();
         if (selectedId==DoseUnit_u1.getId()){
                    inDDU=inDDU*1000;
        finalunit= "g";
         }

        else if (selectedId==DoseUnit_u2.getId()){
                    inDDU=inDDU*1000000;
             finalunit="mg";

        }

         else if (selectedId==DoseUnit_u3.getId()){
                    inDDU=inDDU*1000000000;
             finalunit= "mcg";
         }
         else{
             inDDU=inDDU;
             finalunit= "mcg";
         }
        return  inDDU;
    }




    public void backc(View v) {
        // Do something in response to button click
        Intent i = new Intent(calculates.this, MainActivity.class);
        startActivity(i);
    }
    public void registration(View v) {
        // Do something in response to button click
        Intent i = new Intent(calculates.this, RegDose.class);
        startActivity(i);
    }
    public void reset(View v) {
        ET_BBD.setText("");
        ET_height.setText("");
        ET_weight.setText("");
        TVresult.setText("");
        radioGroup_BBD.clearCheck();
        radioGroup_DoseUnit.clearCheck();
        radioGroup_formula.clearCheck();
        radioGroup_height.clearCheck();
        radioGroup_weight.clearCheck();



    }
    public void result(){
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ET_BBD.getText().toString().trim().length() == 0){
                    ET_BBD.setError("Your message");
                    double BBD=0;
                     resBBD=convertBBD(BBD,radioGroup_BBD,  BBD_u1, BBD_u2, BBD_u3);
                }
                else {
                    double BBD= Double.parseDouble(ET_BBD.getText().toString());
                     resBBD=convertBBD(BBD,radioGroup_BBD,  BBD_u1, BBD_u2, BBD_u3);

                }
                if(ET_weight.getText().toString().trim().length() == 0){
                    ET_weight.setError("Your message");
                    double weight= 0;
                     resWeight=convertWeight(weight,radioGroup_weight,  weight_u1, weight_u2);
                }
                else {

                    double weight= Double.parseDouble(ET_weight.getText().toString());
                     resWeight=convertWeight(weight,radioGroup_weight,  weight_u1, weight_u2);

                }
                if(ET_height.getText().toString().trim().length() == 0){
                    ET_height.setError("Your message");
                    double height=0;
                     resHeight=convertHeight(height,radioGroup_height,height_u1,height_u2,height_u3);

                }
                else {

                    double height=Double.parseDouble(ET_height.getText().toString());
                     resHeight=convertHeight(height,radioGroup_height,height_u1,height_u2,height_u3);


                }

                String Formula=selectFormula(radioGroup_formula,formula_u1,formula_u2);
                if(Formula=="Dubois"){
                    //Dose = BSA Based Dose * 0.007184 * Height(cm)^0.725 * Weight(kg)^0.425.
                    DDU=resBBD*0.007184*(Math.pow(resHeight,0.725))*(Math.pow(resWeight,0.425));

                }
                else if (Formula=="Mosteller"){
                    //Dose = BSA Based Dose x square root [(Height (cm) x Weight (kg)) / 3600]
                    DDU=resBBD*(Math.sqrt((resHeight*resWeight/3600)));

                }
                else
                    DDU=0;


                resDDU =convertDDU(DDU,radioGroup_DoseUnit,DoseUnit_u1,DoseUnit_u2,DoseUnit_u3);
                BigDecimal b   =   new   BigDecimal(resDDU);
                finalResDDU   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                TVresult.setText(Double.toString(finalResDDU));








            }
        });
    }


}


