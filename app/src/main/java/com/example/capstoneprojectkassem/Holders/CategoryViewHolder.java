package com.example.capstoneprojectkassem.Holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
;

import com.example.capstoneprojectkassem.Interface.ItemClickListener;
import com.example.capstoneprojectkassem.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView category_name;
    public ImageView category_image;
    public ItemClickListener itemclickListener;
    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        category_image =itemView.findViewById(R.id.category_image);
        category_name = itemView.findViewById(R.id.category_name);
        itemView.setOnClickListener(this);
    }

    public void setItemclickListener(ItemClickListener itemclickListener) {
        this.itemclickListener = itemclickListener;
    }

    @Override
    public void onClick(View v) {
        itemclickListener.onClick(v,getAdapterPosition(),false);
    }
}
