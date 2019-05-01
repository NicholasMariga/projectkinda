package com.example.slimguy.projectkinda.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.slimguy.projectkinda.OwnData.OurData;
import com.example.slimguy.projectkinda.R;

public class ListAdapter extends RecyclerView.Adapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindview(position);

    }

    @Override
    public int getItemCount() {
        return OurData.title.length;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mItemText;
        private ImageView mItemImage;

        public ListViewHolder (View itemView){
            super(itemView);
            mItemText = (TextView)itemView.findViewById(R.id.itemText);
            mItemImage = (ImageView) itemView.findViewById(R.id.itemImage);
            itemView.setOnClickListener(this);
        }

        public void bindview (int position){
            mItemText.setText(OurData.title[position]);
            mItemImage.setImageResource(OurData.picturepath[position]);
        }
        public void onClick(View view){

        }
    }
}
