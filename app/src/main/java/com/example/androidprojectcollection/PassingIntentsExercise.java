package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class PassingIntentsExercise extends AppCompatActivity {
    EditText vFname, vLname, vBirth, vPhone, vEmail;
    RadioGroup vGender;
    Spinner vYear, vProg;
    Switch vWorking, vMaster, vAthlete, vScholar;
    Button btnSubmit, btnClear;
    private static final String[] programs = {"---SELECT  PROGRAM---","Computer Science","Information Technology","Nursing"," idk sa uban "};
    private static final String[] years = {   "---SELECT YEAR LVL---","1st Year","2nd Year","3rd Year","4th Year","5th Year","6th Year"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing_intents_exercise);

        vYear = findViewById(R.id.formYear);

        ArrayAdapter ad2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, years);
        ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vYear.setAdapter(ad2);

        vProg = findViewById(R.id.formProg);
        ArrayAdapter ad1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, programs);
        ad1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vProg.setAdapter(ad1);

        vFname = findViewById(R.id.formFName);
        vLname = findViewById(R.id.formLName);
        vBirth = findViewById(R.id.formBirth);
        vPhone = findViewById(R.id.formPhone);
        vEmail = findViewById(R.id.formEmail);
        vGender= findViewById(R.id.rbGender);

        vWorking = findViewById(R.id.formWorking);
        vMaster = findViewById(R.id.formMaster);
        vAthlete = findViewById(R.id.formAthlete);
        vScholar = findViewById(R.id.formScholar);

        btnClear = findViewById(R.id.formBtnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vFname.setText("");
                vLname.setText("");
                vBirth.setText("");
                vPhone.setText("");
                vEmail.setText("");

                vGender.check(0);
                vYear.setSelection(0);
                vProg.setSelection(0);

                vWorking.setChecked(false);
                vMaster.setChecked(false);
                vAthlete.setChecked(false);
                vScholar.setChecked(false);
            }
        });

        btnSubmit = findViewById(R.id.formBtnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String fname, lname, birth, phone, email, gender, year, prog, info;
                    fname = vFname.getText().toString();
                    lname = vLname.getText().toString();
                    birth = vBirth.getText().toString();
                    phone = vPhone.getText().toString();
                    email = vEmail.getText().toString();

                    try {
                        RadioButton selGender = (vGender.findViewById(vGender.getCheckedRadioButtonId()));
                        gender = selGender.getText().toString();
                    } catch (Exception e){
                        gender = "?";
                    }

                    year = vYear.getSelectedItem().toString();
                    prog = vProg.getSelectedItem().toString();

                    info = "";
                    if(vWorking.isChecked()) info+= "\t-Working\n";
                    if(vMaster.isChecked()) info+= "\t-Master\n";
                    if(vAthlete.isChecked()) info+= "\t-Athlete\n";
                    if(vScholar.isChecked()) info+= "\t-Scholar\n";

                    if(info.length()==0) info = "\tN/A\n";

                    if(fname.length()==0) fname = "?";
                    if(lname.length()==0) lname = "?";
                    if(birth.length()==0) birth = "?";
                    if(phone.length()==0) phone = "?";
                    if(email.length()==0) email = "?";

                    if(year.matches(years[0])) year = "?";
                    if(prog.matches(programs[0])) prog = "?";

                    Intent intent = new Intent(PassingIntentsExercise.this, PassingIntentsExercise2.class);
                    intent.putExtra("fname",fname);
                    intent.putExtra("lname",lname);
                    intent.putExtra("birth",birth);
                    intent.putExtra("phone",phone);
                    intent.putExtra("email",email);
                    intent.putExtra("gender",gender);
                    intent.putExtra("year",year);
                    intent.putExtra("prog",prog);
                    intent.putExtra("info",info);

                    startActivity(intent);
                } catch(Exception e){
                    Toast.makeText(PassingIntentsExercise.this,"Invalid Submission.",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}