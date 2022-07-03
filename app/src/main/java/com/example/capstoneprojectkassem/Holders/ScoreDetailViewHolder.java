package com.example.capstoneprojectkassem.Holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstoneprojectkassem.R;

public class ScoreDetailViewHolder extends RecyclerView.ViewHolder  {
    public TextView txt_name,txt_score;
    public ScoreDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_name=itemView.findViewById(R.id.txt_name);
        txt_score = itemView.findViewById(R.id.txt_score);

    }
}
