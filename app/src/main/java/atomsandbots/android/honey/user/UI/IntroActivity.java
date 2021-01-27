package atomsandbots.android.honey.user.UI;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroFragment;

import atomsandbots.android.honey.user.R;
import atomsandbots.android.honey.user.Registration.RegisterActivity;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.newInstance("1st intro",
                "A description that will be shown on the bottom",
                R.mipmap.ic_launcher,
                R.color.primary_light));

        addSlide(AppIntroFragment.newInstance("Second",
                "A description that will be shown on the bottom",
                R.mipmap.ic_launcher,
                R.color.colorPrimaryDark));

        addSlide(AppIntroFragment.newInstance("Three",
                "A description that will be shown on the bottom",
                R.mipmap.ic_launcher,
                R.color.light_blue_200));

        addSlide(AppIntroFragment.newInstance("Fourth",
                "A description that will be shown on the bottom",
                R.mipmap.ic_launcher,
                R.color.colorPrimaryDark));
    }

    @Override
    protected void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        MoveNext();
    }

    @Override
    protected void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        MoveNext();
    }

    private void MoveNext(){
        Intent intent = new Intent(IntroActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}