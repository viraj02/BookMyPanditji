package com.example.virajjoshi.bookmypanditji;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by VIRAJ JOSHI on 19-02-2018.
 */

public class MyAdapterP extends RecyclerView.Adapter<MyAdapterP.ViewHolder>  {

    private Context context;
    private List<Upload> uploads;

    public MyAdapterP(Context context, List<Upload> uploads) {
        this.uploads = uploads;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_images, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Upload upload = uploads.get(position);

        holder.textViewName.setText(upload.getName());
        holder.textViewDate1.setText(upload.getDate());
        holder.textViewAddress1.setText(upload.getAddress());

        holder.textViewEmail1.setText(upload.getEmail());
        holder.textViewMobile1.setText(upload.getMobile());
        holder.textViewExp1.setText(upload.getExp());


        Glide.with(context).load(upload.getUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public ImageView imageView;

        public TextView textViewDate1;
        public TextView textViewAddress1;

        public TextView textViewEmail1;
        public TextView textViewMobile1;
        public TextView textViewExp1;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

            textViewDate1 = (TextView) itemView.findViewById(R.id.DateOfBirth);
            textViewAddress1 = (TextView) itemView.findViewById(R.id.textViewAddress);

            textViewEmail1 = (TextView) itemView.findViewById(R.id.textViewEmail);
            textViewMobile1 = (TextView) itemView.findViewById(R.id.textViewMobile);
            textViewExp1 = (TextView) itemView.findViewById(R.id.textViewExp);

        }
    }

}
