package com.example.capstoneprojectkassem.Holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstoneprojectkassem.Interface.ItemClickListener;
import com.example.capstoneprojectkassem.R;

public class RankingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView txt_name,txt_score;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public RankingViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_name = itemView.findViewById(R.id.txt_name);
        txt_score = itemView.findViewById(R.id.txt_score);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(itemView,getAdapterPosition(),false);

    }
}
