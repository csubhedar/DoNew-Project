package com.example.a500039856.haar_project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by This Pc on 22-03-2017.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    ArrayList<item> feedItems;
    Context context;

    public ItemAdapter(Context context,ArrayList<item> feedItems)
    {
        this.context=context;
        this.feedItems=feedItems;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Title,Price;
        ImageView imageView;
        public MyViewHolder(View itemView) {
         super(itemView);
            itemView.setOnClickListener((View.OnClickListener) this);
            Title= (TextView) itemView.findViewById(R.id.ItemName);
            Price= (TextView) itemView.findViewById(R.id.ItemPrice);
            imageView= (ImageView)itemView.findViewById(R.id.Image);

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "position = " + getPosition(), Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(v.getContext(),ItemDetail.class);
            context.startActivity(intent);
        }
    }

    @Override
    public ItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.new_item,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemAdapter.MyViewHolder holder, int position) {
        item current=feedItems.get(position);
        holder.Title.setText(current.getTitle());
        holder.Price.setText(current.getPrice());
        Picasso.with(context).load(current.getImglink()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }


}
