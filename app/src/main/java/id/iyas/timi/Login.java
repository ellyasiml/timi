package id.iyas.timi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText login_username, login_password;
    Button login_login, login_signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_username = (EditText) findViewById(R.id.login_username);
        login_password = (EditText) findViewById(R.id.login_password);
        login_login = (Button) findViewById(R.id.login_login);
        login_signin = (Button) findViewById(R.id.login_signin);
        DB = new DBHelper(this);

        login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = login_username.getText().toString();
                String pass = login_password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Login.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass == true){
                        Toast.makeText(Login.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("USERNAME", user);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "Invalid credentials, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        login_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
    }
}