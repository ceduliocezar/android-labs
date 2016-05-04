package com.ceduliocezar.dynamictableview;

import java.util.List;

/**
 * Created by ceduliocezar on 04/05/16.
 */
public class Item {

    String name;
    String imageUrl;
    List<String> columns;

    List<List<String>> lines;

    boolean hasImage(){
        return imageUrl != null;
    }


}