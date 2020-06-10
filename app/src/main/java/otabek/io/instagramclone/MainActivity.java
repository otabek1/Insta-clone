package otabek.io.instagramclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "Otabek";
    EditText userNameEditText;
    EditText passwordEditText;

    public void showUserList(){
        Intent intent = new Intent(getApplicationContext(),UserListActivity.class);
        startActivity(intent);
    }


    public void signUp(View view) {
        ParseUser user = new ParseUser();

        if (passwordEditText.getText().toString().isEmpty() || userNameEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Username and password are required", Toast.LENGTH_SHORT).show();
        } else {
            user.setUsername(userNameEditText.getText().toString());
            user.setPassword(passwordEditText.getText().toString());
        }
        if (view.getId() == R.id.signUpButton) {
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.i(TAG, "done: Successfully signed up");
                        showUserList();
                    } else {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else {
            if (view.getId() == R.id.logInButton) {

                ParseUser.logInInBackground(userNameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            showUserList();
                            Log.i(TAG, "done: Signed in ok");
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Insta");


        ImageView logoImageView = findViewById(R.id.logoimageView);
        ConstraintLayout mainConstraintLayout = findViewById(R.id.mainConstraintLayout);
        userNameEditText = findViewById(R.id.userNameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);


        logoImageView.setOnClickListener(this);
        mainConstraintLayout.setOnClickListener(this);

        if (ParseUser.getCurrentUser() !=null){
            showUserList();
        }


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.logoimageView || v.getId() == R.id.mainConstraintLayout){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
    }
}
