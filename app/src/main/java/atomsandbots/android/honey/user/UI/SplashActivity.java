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
        Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}
