








package com.raj.jsoup;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class parse_Adapter extends RecyclerView.Adapter<parse_Adapter.viewHolder>{
    private ArrayList<parseItem> parseItems;
    private Context context;

    public parse_Adapter(Context context,ArrayList<parseItem> parseItems ) {
        this.parseItems = parseItems;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public parse_Adapter.viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull parse_Adapter.viewHolder holder, int position) {
        parseItem parseItem =parseItems.get(position);

        Picasso.get().load(parseItem.getImageurl()).into(holder.img);
        holder.title_image.setText(parseItem.getTitle());
        holder.txt_language.setText(parseItem.getLanguage());

    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView title_image,txt_language;
        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            title_image=itemView.findViewById(R.id.title_image);
            txt_language=itemView.findViewById(R.id.txt_language);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            parseItem parseItem=parseItems.get(position);
            Intent intent=new Intent(context,DetialActivity.class);
            intent.putExtra("title",parseItem.getTitle());
            intent.putExtra("imageurl",parseItem.getImageurl());
            intent.putExtra("language",parseItem.getLanguage());
            intent.putExtra("detialurl",parseItem.getDetailUrl());
            context.startActivity(intent);

        }
    }
}

//public class parse_Adapter extends RecyclerView.Adapter<parse_Adapter.viewHolder> {
//    private ArrayList<parseItem> parseItems;
//    private Context context;
//
//
//
//    public parse_Adapter(Context context,ArrayList<parseItem> parseItems) {
//        this.parseItems = parseItems;
//        this.context = context;
//    }
//
//
//
//    @Override
//    public parse_Adapter.viewHolder onCreateViewHolder(  ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item,parent,false);
//
//
//        return new viewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(  parse_Adapter.viewHolder holder, int position) {
//        parseItem parseItem =parseItems.get(position);
//        holder.title_image.setText(parseItem.getTitle());
//        Picasso.get().load(parseItem.getImageurl()).into(holder.img);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return parseItems.size();
//    }
//
//    public class viewHolder extends RecyclerView.ViewHolder {
//        ImageView img;
//        TextView title_image;
//        public viewHolder(View itemView) {
//            super(itemView);
//            img=itemView.findViewById(R.id.img);
//            title_image=itemView.findViewById(R.id.title_image);
//
//        }
//    }
//}
