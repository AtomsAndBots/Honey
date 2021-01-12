package atomsandbots.android.honey.user.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import atomsandbots.android.honey.user.R;
import atomsandbots.android.honey.user.UI.MainActivity;

public class VerificationActivity extends AppCompatActivity {
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        checkEmail();
    }

    private void checkEmail(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user.isEmailVerified()){
            startActivity(new Intent(VerificationActivity.this, MainActivity.class));
            finish();
        }
    }
}