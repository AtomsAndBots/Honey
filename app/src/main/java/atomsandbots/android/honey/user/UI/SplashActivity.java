package atomsandbots.android.honey.user.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import atomsandbots.android.honey.user.Registration.RegisterActivity;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("IntroScreen", MODE_PRIVATE);
        boolean isSeen = preferences.getBoolean("isSeen", false);
        if (isSeen){
            Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
            startActivity(intent);
        } else {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isSeen", true);
            editor.apply();
            Intent intent = new Intent(SplashActivity.this, IntroActivity.class);
            startActivity(intent);
        }

        // close splash activity
        finish();
    }
}
