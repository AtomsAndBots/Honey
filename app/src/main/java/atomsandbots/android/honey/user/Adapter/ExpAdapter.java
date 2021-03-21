package atomsandbots.android.honey.user.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import atomsandbots.android.honey.user.Model.ExpModel;
import atomsandbots.android.honey.user.Model.ProductModel;
import atomsandbots.android.honey.user.R;

import static androidx.recyclerview.widget.RecyclerView.*;

// Adapter for Explore Fragment

public class ExpAdapter extends RecyclerView.Adapter<ExpAdapter.ViewHolder> {
    private List<ExpModel> expModels;

    public ExpAdapter(List<ExpModel> expModels) {
        this.expModels = expModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exp_item_layout, parent, false);
        return new ExpAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.expTittle.setText(expModels.get(position).getTittle());
        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.itemView.getContext());
        layoutManager.setOrientation(HORIZONTAL);
        holder.expRV.setLayoutManager(layoutManager);
        holder.expRV.setHasFixedSize(true);
        holder.expRV.setItemViewCacheSize(20);
        holder.expRV.setDrawingCacheEnabled(true);
        HomeAdapter homeAdapter = new HomeAdapter(expModels.get(position).getProductModels(), holder.itemView.getContext(), true, true,false, true);
        holder.expRV.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return expModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView expTittle;
        private RecyclerView expRV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            expRV = itemView.findViewById(R.id.expRV);
            expTittle = itemView.findViewById(R.id.expTittle);
        }
    }
}
