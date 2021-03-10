package atomsandbots.android.honey.user.UI;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import atomsandbots.android.honey.user.R;
import atomsandbots.android.honey.user.databinding.FragmentPrivacyPolicyBinding;

public class PrivacyPolicyFragment extends Fragment {

    private FragmentPrivacyPolicyBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPrivacyPolicyBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        try {
            PackageInfo pInfo = this.getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
            String version = pInfo.versionName;
            binding.versionTextView.setText(String.format("%s%s", getString(R.string.version), version));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        binding.privacyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // link to privacy policy
                String url = "https://www.honeycollabs.com/privacy-policy";
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        binding.termsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // link to terms of use
                String url = "https://www.honeycollabs.com/terms-of-use";
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        binding.feedbackCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "honeycollaborations@gmail.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Theme of mail");
                startActivity(Intent.createChooser(intent, "Select post client"));
            }
        });


        return view;

    }

    //clean fragment
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}