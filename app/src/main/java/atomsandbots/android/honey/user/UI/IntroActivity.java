package atomsandbots.android.honey.user.UI;

import android.content.Intent;
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
                getResources().getColor(R.color.primary_dark)));


        addSlide(AppIntroFragment.newInstance("Second",
                "A description that will be shown on the bottom",
                R.mipmap.ic_launcher,
                getResources().getColor(R.color.primary_dark)));

        addSlide(AppIntroFragment.newInstance("Three",
                "A description that will be shown on the bottom",
                R.mipmap.ic_launcher,
                getResources().getColor(R.color.primary_dark)));

        addSlide(AppIntroFragment.newInstance("Fourth",
                "A description that will be shown on the bottom",
                R.mipmap.ic_launcher,
                getResources().getColor(R.color.primary_dark)));
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

     /*TODO this needs to activated once the user is authenticated.
     *  Also, i have a suggestion regarding payments, what do you think about a different way of payment (Paypal etc.)?.
     *  I can pay you for this on-going gig separately and only one fee applies for both of us,
     *  right now Fiverr taking a transaction fee from you and me. Email me at stanleymanoah@gmail.com if you'd rather do that. */

    private void MoveNext() {
        Intent intent = new Intent(IntroActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}