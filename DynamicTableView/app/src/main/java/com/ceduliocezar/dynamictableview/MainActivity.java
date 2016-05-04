package com.ceduliocezar.dynamictableview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private List<Item> modelItems;
    private List<PresentationItem> presentationItems;

    private int numberOfColumns = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createFakeModelItems();
        convertPresentationItems();


        listView = (ListView) findViewById(R.id.recycler);
        listView.setAdapter(new PresentationAdapter(this, presentationItems));

    }

    private void convertPresentationItems() {
        presentationItems = new ArrayList<>();

        for (Item item : modelItems) {
            if(item.hasImage()){
                presentationItems.add(convertImageItem(item));
            }else{
                presentationItems.addAll(convertTableIntoItems(item));
            }
        }
    }

    private List<PresentationItem> convertTableIntoItems(Item item) {
        List<PresentationItem> items =  new ArrayList<>();

        presentationHeaderItem(item, items);
        presentationLineItems(item, items);

        return items;
    }

    private void presentationLineItems(Item item, List<PresentationItem> items) {
        List<List<String>> lines = item.lines;

        for (int i = 0; i < lines.size(); i++) {
            presentationLineItem(item, items);
        }
    }

    private void presentationLineItem(Item item, List<PresentationItem> items) {
        PresentationItem presentationLineItem = new PresentationItem();
        presentationLineItem.type = PresentationItem.Type.TABLE_LINE;
        presentationLineItem.lineItems = item.columns;
        items.add(presentationLineItem);
    }

    private void presentationHeaderItem(Item item, List<PresentationItem> items) {
        PresentationItem headerItem = new PresentationItem();
        headerItem.lineItems =  item.columns;
        headerItem.type = PresentationItem.Type.TABLE_HEADER;
        items.add(headerItem);
    }

    private PresentationItem convertImageItem(Item item) {

        PresentationItem presentationItem =  new PresentationItem();
        presentationItem.type = PresentationItem.Type.IMAGE;
        presentationItem.imageURL = "/fake/img";

        return presentationItem;
    }

    private void createFakeModelItems() {
        modelItems = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Item item = new Item();

            if (i % 2 == 0) {
                item.imageUrl = "";
            } else {
                mockTable(item, i);
            }

            modelItems.add(item);
        }
    }

    private void mockTable(Item item, int index) {

        item.columns = new ArrayList<>();

        for (int column = 0; column < numberOfColumns; column++) {
            item.columns.add("Label " + column);
        }

        item.lines = new ArrayList<>();
        for (int lineIndex = 0; lineIndex < index; lineIndex++) {
            List<String> line = new ArrayList<>();

            for (int column = 0; column < numberOfColumns; column++) {
                line.add("Cell " + lineIndex + "" + column);
            }
            item.lines.add(line);
        }
    }

    private class PresentationAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private List<PresentationItem> items;

        public PresentationAdapter(Context context, List<PresentationItem> items) {
            this.inflater = LayoutInflater.from(context);
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (isImageCell(position)) {
                return createImageView(position);
            } else {
                return createTableLine(position);
            }
        }

        private View createImageView(int position) {
            return inflater.inflate(R.layout.image_layout, null);
        }

        private View createTableLine(int position) {
            PresentationItem item = items.get(position);

            LinearLayout tableLineContainer = (LinearLayout) inflater.inflate(R.layout.table_line, null);

            if(item.type == PresentationItem.Type.TABLE_HEADER){
                createTableHeader(item, tableLineContainer);
            }else{
                createTableLine(item, tableLineContainer);
            }

            return tableLineContainer;
        }

        private void createTableLine(PresentationItem item, LinearLayout tableLineContainer) {
            List<String> lineItems = item.lineItems;

            for (int columnIndex = 0; columnIndex < lineItems.size(); columnIndex++) {
                TextView label = (TextView) inflater.inflate(R.layout.cell, null);
                label.setText(lineItems.get(columnIndex));

                label.setLayoutParams(
                        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.MATCH_PARENT, 1f));

                tableLineContainer.addView(label);
            }
        }

        private void createTableHeader(PresentationItem item, LinearLayout tableLineContainer) {

            List<String> columns = item.lineItems;

            for (int columnIndex = 0; columnIndex < columns.size(); columnIndex++) {
                TextView label = (TextView) inflater.inflate(R.layout.cell_header, null);
                label.setText(columns.get(columnIndex));

                label.setLayoutParams(
                        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.MATCH_PARENT, 1f));

                tableLineContainer.addView(label);
            }
        }

        private boolean isImageCell(int position) {
            return items.get(position).imageURL != null;
        }
    }

}
