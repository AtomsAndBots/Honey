package atomsandbots.android.honey.user.Registration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import atomsandbots.android.honey.user.R;
import atomsandbots.android.honey.user.UI.MainActivity;
import atomsandbots.android.honey.user.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        atomsandbots.android.honey.user.databinding.ActivityRegisterBinding binding
                = ActivityRegisterBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();
        setContentView(v);

        mAuth = FirebaseAuth.getInstance();


        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoLoginPage();
            }
        });

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoSignUpPage();
            }
        });
    }

    private void GotoSignUpPage() {
        startActivity(new Intent(RegisterActivity.this, SignUpActivity.class));
    }

    private void GotoLoginPage() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("LoginDetails", MODE_PRIVATE);
        boolean isLogin = preferences.getBoolean("isLogin", false);
        boolean isAdmin = preferences.getBoolean("isAdmin", false);
        if (isLogin) {
            if (isAdmin) {
                Toast.makeText(this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
            } else {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    if (user.isEmailVerified()) {
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        finish();
                    } else {
                        FirebaseAuth.getInstance().signOut();
                    }
                }

            }
        }

    }


}