package com.hemant.pixabay;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private Context context;
    private ArrayList<ExampleItem> exampleItems;
    private Onclicklistner onclicklistner;

    public interface Onclicklistner{
        void onclicklist(int position);
    }

    public void setOnItemClickListner(Onclicklistner listner){
        onclicklistner = listner;
    }

    public ExampleAdapter(Context context, ArrayList<ExampleItem> exampleItems) {
        this.context = context;
        this.exampleItems = exampleItems;

    }



    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(context).inflate(R.layout.example_item,viewGroup,false);
       return new ExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentitem = exampleItems.get(position);
        String imageurl =currentitem.getmImageurl();
        String textviewcreator  = currentitem.getmCreator();
        int textves = currentitem.getmLikes();

        holder.textviewcreator.setText(textviewcreator);
       // holder.textviewlikes.setText(textves);
        holder.textviewlikes.setText("Likes: " + textves);

        Picasso.with(context).load(imageurl).fit().centerCrop().into(holder.imageview);



    }

    @Override
    public int getItemCount() {
        return exampleItems.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageview;
        public TextView textviewcreator,textviewlikes;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            imageview = itemView.findViewById(R.id.imageview);
            textviewcreator = itemView.findViewById(R.id.textview_creator);
            textviewlikes =itemView.findViewById(R.id.tvlikes);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onclicklistner !=null){
                        int position = getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            onclicklistner.onclicklist(position);
                        }
                    }
                }
            });
        }
    }
}
