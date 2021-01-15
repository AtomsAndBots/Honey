package atomsandbots.android.honey.user.Registration;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import atomsandbots.android.honey.user.UI.MainActivity;
import atomsandbots.android.honey.user.databinding.ActivityVerificationBinding;


public class VerificationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        atomsandbots.android.honey.user.databinding.ActivityVerificationBinding binding = ActivityVerificationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        checkEmail();

        binding.goToInboxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("\"message/rfc822\"");
                Intent mailer = Intent.createChooser(intent, null);
                startActivity(mailer);
            }
        });

    }

    private void checkEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        if (user.isEmailVerified()) {
            startActivity(new Intent(VerificationActivity.this, MainActivity.class));
            finish();
        }
    }

}
