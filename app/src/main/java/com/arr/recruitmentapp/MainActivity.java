package com.arr.recruitmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

    //https://github.com/shhekarchowdary/Recruitment-App
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView basicSalary,totalSalary,biWeeklySalary;
    EditText qualification,experience,langKnown;
    Button calculateSalary;

    HashMap<String,Double>programBonus = new HashMap<>();

    final int qualificationId = R.id.etxtQualification;
    final int calculateSalaryId = R.id.btnSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        programBonus.put("C#",200.0);
        programBonus.put("Java",190.0);
        programBonus.put("Python",180.0);
        programBonus.put("HTML",120.0);
        programBonus.put("Android",220.0);
        programBonus.put("Swift",230.0);

        qualification = findViewById(R.id.etxtQualification);
        experience = findViewById(R.id.etxtExperience);
        langKnown = findViewById(R.id.etxtLanguages);
        calculateSalary = findViewById(R.id.btnSalary);
        basicSalary = findViewById(R.id.txtBasicSalary);
        totalSalary = findViewById(R.id.txtTotalSalary);
        biWeeklySalary = findViewById(R.id.txtbiSalary);

        qualification.setOnClickListener(this);

        calculateSalary.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case qualificationId:
                if(!qualification.getText().toString().isEmpty()){
                    String qual = qualification.getText().toString();
                    if(qual.equalsIgnoreCase("PHD")){
                        basicSalary.setText("6500");
                    }else if(qual.equalsIgnoreCase("Masters")){
                        basicSalary.setText("6000");
                    }else if(qual.equalsIgnoreCase("Bachelor")){
                        basicSalary.setText("5300");
                    }else if(qual.equalsIgnoreCase("Diploma")){
                        basicSalary.setText("5000");
                    }else if(qual.equalsIgnoreCase("Secondary school")){
                        basicSalary.setText("4500");
                    }else{
                        basicSalary.setText("Please Enter Correct Qualification");
                    }
                }else basicSalary.setText("Please Enter Qualification");
            case calculateSalaryId:
                if(!qualification.getText().toString().isEmpty()){
                    boolean q;
                    String qual = qualification.getText().toString();
                    if(qual.equalsIgnoreCase("PHD")){
                        basicSalary.setText("6500");
                        q = true;
                    }else if(qual.equalsIgnoreCase("Masters")){
                        basicSalary.setText("6000");
                        q = true;
                    }else if(qual.equalsIgnoreCase("Bachelor")){
                        basicSalary.setText("5300");
                        q = true;
                    }else if(qual.equalsIgnoreCase("Diploma")){
                        basicSalary.setText("5000");
                        q = true;
                    }else if(qual.equalsIgnoreCase("Secondary school")){
                        basicSalary.setText("4500");
                        q = true;
                    }else{
                        basicSalary.setText("Please Enter Correct Qualification");
                        basicSalary.setTextColor(Color.parseColor("#FF0000"));
                        q = false;
                    }

                    if(!experience.getText().toString().isEmpty() && q){
                        if(!langKnown.getText().toString().isEmpty()){

                            double basicSal = Double.parseDouble(basicSalary.getText().toString());
                            int exp = Integer.parseInt(experience.getText().toString());
                            double expBonus = basicSal*0.02;
                            if(expBonus > 600){
                                expBonus = 600;
                            }


                            String[] languages = langKnown.getText().toString().split(",");
                            double langBonus = 0;
                            for(String lang: languages){
                                String removal = "";
                                for(String la: programBonus.keySet()){
                                    if(lang.equalsIgnoreCase(la)){
                                        langBonus += programBonus.get(la);
                                        removal = la;
                                    }
                                }
                                if(!removal.isEmpty()){
                                    programBonus.remove(removal);
                                }
                            }

                            double toSalary = (basicSal + expBonus + langBonus)*12.0;
                            totalSalary.setText(String.format("%.2f",toSalary));

                            double biSalary = (toSalary/365)*14;
                            biWeeklySalary.setText(String.format("%.2f",biSalary));

                            programBonus.put("C#",200.0);
                            programBonus.put("Java",190.0);
                            programBonus.put("Python",180.0);
                            programBonus.put("HTML",120.0);
                            programBonus.put("Android",220.0);
                            programBonus.put("Swift",230.0);
                        }else{
                            Toast.makeText(this,"Enter Languages Known",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(this,"Enter Experience",Toast.LENGTH_LONG).show();
                    }
                }else basicSalary.setText("Please Enter Qualification");


            default:

        }
    }
}