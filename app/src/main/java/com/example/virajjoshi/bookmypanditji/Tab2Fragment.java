package com.example.virajjoshi.bookmypanditji;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by VIRAJ JOSHI on 21-02-2018.
 */

public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab2Fragment";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment,container,false);

        return view;
    }
}
