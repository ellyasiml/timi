package id.iyas.timi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText register_name, register_username, register_password;
    Button register_sign, register_login;
    RadioGroup register_gender;
    RadioButton register_genderradio;
    CheckBox register_tnc;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_name = (EditText) findViewById(R.id.register_name);
        register_username = (EditText) findViewById(R.id.register_username);
        register_password = (EditText) findViewById(R.id.register_password);
        register_sign = (Button) findViewById(R.id.register_sign);
        register_login = (Button) findViewById(R.id.register_login);
        register_gender = (RadioGroup) findViewById(R.id.register_gender);
        register_tnc = (CheckBox) findViewById(R.id.register_tnc);
        DB = new DBHelper(this);

        register_sign.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view){

                int radioId = register_gender.getCheckedRadioButtonId();
                register_genderradio = findViewById(radioId);

                String user = register_username.getText().toString();
                String name = register_name.getText().toString();
                String pass = register_password.getText().toString();
                String gender = register_genderradio.getText().toString();

                if(user.equals("")||pass.equals("")||name.equals("")||gender.equals(""))
                    Toast.makeText(Register.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                else{
                    if(register_tnc.isChecked()){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, name, pass, gender);
                            if(insert == true){
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("USERNAME", user);
                                Toast.makeText(Register.this, "Registered succesfully", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            }else{
                                Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Register.this, "User already exists, please login!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Register.this, "Please accept our T&C before registering", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        register_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }
}