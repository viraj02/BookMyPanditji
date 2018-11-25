package com.example.virajjoshi.bookmypanditji;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by VIRAJ JOSHI on 16-02-2018.
 */

public class ArtistList extends ArrayAdapter<Artist> {

    private Activity context;
    private List<Artist> artistList;

    public ArtistList(Activity context, List<Artist> artistList){
        super(context,R.layout.list_layout,artistList);
        this.context=context;
        this.artistList=artistList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.list_layout,null,true);

        // list_layout

        TextView textViewOrderID1=(TextView) listViewItem.findViewById(R.id.textViewOrderID);
        TextView textViewName1=(TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewAddress1=(TextView) listViewItem.findViewById(R.id.textViewAddress);
        TextView textViewDate1=(TextView) listViewItem.findViewById(R.id.textViewDate);
        TextView textViewTime1=(TextView) listViewItem.findViewById(R.id.textViewTime);
        TextView textViewEmail1=(TextView) listViewItem.findViewById(R.id.textViewEmail);

        TextView textViewMobile1=(TextView) listViewItem.findViewById(R.id.textViewMobile);
        TextView textViewTypeOfPooja1=(TextView) listViewItem.findViewById(R.id.textViewPooja);
        TextView textViewTotalAmount1=(TextView) listViewItem.findViewById(R.id.textViewAmount);

        Artist artist=artistList.get(position);


        textViewOrderID1.setText(artist.getOrderID());
        textViewName1.setText(artist.getName());
        textViewAddress1.setText(artist.getAddress());
        textViewDate1.setText(artist.getDate());
        textViewTime1.setText(artist.getTime());
        textViewEmail1.setText(artist.getEmail());

        textViewMobile1.setText(artist.getMobile());
        textViewTypeOfPooja1.setText(artist.getTypeOfPooja());
        textViewTotalAmount1.setText(artist.getTotalAmount());

        return listViewItem;

    }
}

