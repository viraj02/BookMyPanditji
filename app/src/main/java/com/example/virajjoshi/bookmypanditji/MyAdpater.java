package com.example.virajjoshi.bookmypanditji;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by VIRAJ JOSHI on 30-12-2017.
 */

public class MyAdpater extends RecyclerView.Adapter<MyAdpater.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;



    public MyAdpater(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item , parent ,false);
        return new ViewHolder(v);

    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final ListItem listItem = listItems.get(position);

    }

    @Override
    public int getItemCount() {
        return listItems.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHead;
        public TextView textViewDesc;
        public LinearLayout linearLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDesc);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);

        }
    }
}