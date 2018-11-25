package com.example.virajjoshi.bookmypanditji;

import android.support.v7.widget.CardView;

/**
 * Created by VIRAJ JOSHI on 18-01-2018.
 */

public class ListItem {

    private String head;
    private String desc;

    private CardView c1,c2,c3;

    public ListItem(String head, String desc, CardView c1, CardView c2, CardView c3) {
        this.head = head;
        this.desc = desc;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
    }


    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    public CardView getC1() {
        return c1;
    }

    public CardView getC2() {
        return c2;
    }

    public CardView getC3() {
        return c3;
    }
}

