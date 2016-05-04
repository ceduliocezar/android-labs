package com.ceduliocezar.dynamictableview;

import java.util.List;

/**
 * Created by ceduliocezar on 04/05/16.
 */
public class PresentationItem {

    String imageURL;
    List<String> lineItems;
    Type type;

    public enum Type{
        IMAGE,
        TABLE_HEADER,
        TABLE_LINE,
    }
}
