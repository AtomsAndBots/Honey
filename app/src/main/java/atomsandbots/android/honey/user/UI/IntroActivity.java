package atomsandbots.android.honey.user.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import atomsandbots.android.honey.user.Adapter.IntroViewPagerAdapter;
import atomsandbots.android.honey.user.Model.ScreenItem;
import atomsandbots.android.honey.user.R;
import atomsandbots.android.honey.user.databinding.ActivityIntroBinding;

public class IntroActivity extends AppCompatActivity {


    IntroViewPagerAdapter introViewPagerAdapter;
    int position = 0;
    Animation btnAnim;

    private ActivityIntroBinding binding;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        View v = binding.getRoot();

        // make the activity on full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        // check if the user has already opened welcome screen before launching
        if (restorePrefData()) {
            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainActivity);
            finish();
        }

        setContentView(v);

        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);


        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Welcome To Honey Co.",
                "Welcome to Honey Co. the all-in-one Giveaway and Promotional platform." +
                        " Honey Co. offers the simplest and easiest way to enter Giveaways or prize competitions. " +
                        "We will always find the best Giveaways and deals for you and if it's not a deal, it's a Giveaway.Literally! "
                , R.drawable.hello_image));
        mList.add(new ScreenItem("You Win, We Deliver!",
                "You Win, We deliver!" +
                        " Don't forget keep your notifications on to stay up to date with our Giveaways and Events.",
                R.drawable.delivery_image));
        mList.add(new ScreenItem("Honey Explore",
                " Also check our in App Explore Page to discover new trends and 'Rate or Hate' upcoming Giveaways. " +
                        "Thank you for choosing us.",
                R.drawable.notification_image));

        //setup viewpager
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        binding.screenViewpager.setAdapter(introViewPagerAdapter);

        //setup tabLayout with viewpager
        binding.tabIndicator.setupWithViewPager(binding.screenViewpager);

        //Next button to get next item on screen pager
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                position = binding.screenViewpager.getCurrentItem();
                if (position < mList.size()) {
                    position++;
                    binding.screenViewpager.setCurrentItem(position);
                }

                if (position == mList.size() - 1) { // when we reach to the last screen
                    IntroActivity.this.onLastScreen();
                }

            }
        });

        // tabLayout add change listener
        binding.tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size() - 1) {
                    onLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        // Get Started button click listener
        binding.btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v12) {
                //open main activity
                Intent mainActivity = new Intent(IntroActivity.this.getApplicationContext(), MainActivity.class);
                IntroActivity.this.startActivity(mainActivity);
                // also we need to save a boolean value to storage so next time when the user run the app
                // we could know that he is already checked the intro screen activity
                // we are using shared preferences to that process
                IntroActivity.this.savePrefsData();
                //finish IntroActivity
                IntroActivity.this.finish();

            }
        });

        // skip button click listener
        binding.tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v13) {
                binding.screenViewpager.setCurrentItem(mList.size());
            }
        });


    }

    private boolean restorePrefData() {
        //check to see is user has opened app before
        SharedPreferences pref = getApplicationContext().getSharedPreferences(firebaseUser.getUid() + "myPrefs", MODE_PRIVATE);
        return pref.getBoolean("isIntroOpened", false);
    }

    private void savePrefsData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences(firebaseUser.getUid() + "myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.apply();
    }


    // show the 'get started' Button and hide the indicator and the next button
    private void onLastScreen() {

        binding.btnNext.setVisibility(View.INVISIBLE);
        binding.btnGetStarted.setVisibility(View.VISIBLE);
        binding.tvSkip.setVisibility(View.INVISIBLE);
        binding.tabIndicator.setVisibility(View.INVISIBLE);
        // setup animation
        binding.btnGetStarted.setAnimation(btnAnim);

    }

}