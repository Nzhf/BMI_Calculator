package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    public Button button;
    EditText weight, height;
    TextView resulttext;
    String calculation, BMIcat, range, risk;
    private static final String FILE_NAME1 = "weight.txt";
    private static final String FILE_NAME2 = "height.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        resulttext = findViewById(R.id.result);

        button = (Button) findViewById(R.id.aboutPage);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AboutPage.class);
                startActivity(intent);
            }
        });
    }

    public void calculateBMI(View view) {
        String S1 = weight.getText().toString();
        String S2 = height.getText().toString();

        float weightValue = Float.parseFloat(S1);
        float heightValue = Float.parseFloat(S2) / 100;

        FileInputStream fis1 = null;

        try {
            fis1 = openFileInput(FILE_NAME1);
            InputStreamReader isr = new InputStreamReader(fis1);
            BufferedReader br = new BufferedReader(isr);
            String txt;

            while((txt = br.readLine()) != null){
                weight.setText(txt);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis1 != null){
                try {
                    fis1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        FileInputStream fis2 = null;

        try {
            fis2 = openFileInput(FILE_NAME2);
            InputStreamReader isr = new InputStreamReader(fis2);
            BufferedReader br = new BufferedReader(isr);
            String txt;

            while((txt = br.readLine()) != null){
                height.setText(txt);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis2 != null){
                try {
                    fis2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String txt1 = weight.getText().toString();
        String txt2 = height.getText().toString();
        FileOutputStream fos1 = null;
        try {
            fos1 = openFileOutput(FILE_NAME1, MODE_PRIVATE);
            fos1.write(txt1.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos1!=null){
                try {
                    fos1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        FileOutputStream fos2 = null;
        try {
            fos2 = openFileOutput(FILE_NAME2, MODE_PRIVATE);
            fos2.write(txt2.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos2!=null){
                try {
                    fos2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        float bmi = weightValue / (heightValue * heightValue);

        if(bmi < 18.4) {
            BMIcat = "Underweight";
            range = "18.4 and below";
            risk = "malnutrition risk";
        }else if(bmi >= 18.5 && bmi <= 24.9) {
            BMIcat = "Normal weight";
            range = "18.5 - 24.9";
            risk = "low health risk";
        }else if(bmi >= 25 && bmi<= 29.9 ) {
            BMIcat = "Overweight";
            range = "25 - 29.9";
            risk = "enhanced health risk";
        }else if(bmi >= 30 && bmi <= 34.9) {
            BMIcat = "Moderately obese";
            range = "30 - 34.9";
            risk = "medium health risk";
        }else if(bmi >= 35 && bmi <= 39.9) {
            BMIcat = "Severely obese";
            range = "35 - 39.9";
            risk = "high health risk";
        }else {
            BMIcat = "Very severely obese";
            range = "40 and above";
            risk = "very high health risk";
        }

        calculation = "\n" + "\tYour BMI: " + bmi + "\n" + "\tYou are in a category of: \n\t" + BMIcat + "\n" + "Your range of BMI(kg/m²): " + "\t\t\t"+ range + "\n" + "\t\tYou have a " + risk;

        resulttext.setText(calculation);


    }

}