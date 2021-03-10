package atomsandbots.android.honey.user.UI;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import atomsandbots.android.honey.user.Adapter.HomeAdapter;
import atomsandbots.android.honey.user.Extras.GridSpacingItemDecoration;
import atomsandbots.android.honey.user.Model.ProductModel;
import atomsandbots.android.honey.user.R;

//Used to be 'rate or hate' fragment
public class ExploreFragment extends Fragment {
    private RecyclerView exploreRecyclerView;

    private List<ProductModel> productModelList;
    private ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_explore, container, false);


        // check internet connection
        ConnectivityManager conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null) {
            Toast.makeText(getContext(), "Check internet Connection", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Please Wait");
            progressDialog.show();

            exploreRecyclerView = root.findViewById(R.id.explore_recyclerView);
            productModelList = new ArrayList<>();

            // create spacing between recyclerview item
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            exploreRecyclerView.setLayoutManager(gridLayoutManager);
            int spanCount = 2; // 3 columns
            int spacing = 15; // 50px
            boolean includeEdge = false;
            exploreRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, false));
            exploreRecyclerView.setHasFixedSize(true);
            exploreRecyclerView.setItemViewCacheSize(20);
            exploreRecyclerView.setDrawingCacheEnabled(true);

            //Get firebase products instance
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Products");
            reference.orderByChild("ProductName").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    productModelList.clear();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        ProductModel product = snapshot1.getValue(ProductModel.class);
                        productModelList.add(product);
                    }
                    HomeAdapter homeAdapter = new HomeAdapter(productModelList, getContext(), true, false, true);
                    exploreRecyclerView.setAdapter(homeAdapter);
                    progressDialog.dismiss();
                    homeAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

        return root;
    }

}