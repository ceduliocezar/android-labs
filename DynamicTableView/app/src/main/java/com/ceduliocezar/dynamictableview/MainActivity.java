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

    private List<Item> items;

    private int numberOfColumns = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createFakeItems();

        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(new TableAdpter(this, items));

    }

    private void createFakeItems() {
        items = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Item item = new Item();

            if (i % 2 == 0) {
                item.imageUrl = "";
            } else {
                mockTable(item, i);
            }

            items.add(item);
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

    private class TableAdpter extends BaseAdapter {

        private LayoutInflater inflater;
        private List<Item> items;

        public TableAdpter(Context context, List<Item> items) {
            this.inflater = LayoutInflater.from(context);
            this.items = items;
        }

        @Override
        public int getCount() {
            return 100;
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
                return createTableView(position);
            }
        }

        private View createImageView(int position) {
            return inflater.inflate(R.layout.image_cell, null);
        }

        private View createTableView(int position) {
            Item item = items.get(position);

            LinearLayout tableContainer = (LinearLayout) inflater.inflate(R.layout.table_layout, null);

            createTableHeader(item, tableContainer);
            createTableLines(item, tableContainer);

            return tableContainer;
        }

        private void createTableLines(Item item, LinearLayout tableContainer) {
            List<List<String>> lines = item.lines;

            for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
                createTableLine(tableContainer, lines.get(lineIndex));
            }
        }

        private void createTableLine(LinearLayout tableContainer, List<String> line) {
            LinearLayout lineTableLayout;
            lineTableLayout = (LinearLayout) inflater.inflate(R.layout.table_line, null);

            for (int columnIndex = 0; columnIndex < line.size(); columnIndex++) {
                createTableLineCell(line.get(columnIndex), lineTableLayout);
            }

            tableContainer.addView(lineTableLayout);
        }

        private void createTableLineCell(String text, LinearLayout lineTableLayout) {
            TextView label = (TextView) inflater.inflate(R.layout.cell, null);
            label.setText(text);

            label.setLayoutParams(
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.MATCH_PARENT, 1f));

            lineTableLayout.addView(label);
        }

        private void createTableHeader(Item item, LinearLayout tableContainer) {
            LinearLayout headerLayout = (LinearLayout) inflater.inflate(R.layout.table_line, null);
            List<String> columns = item.columns;

            for (int columnIndex = 0; columnIndex < columns.size(); columnIndex++) {
                createTableHeaderCell(columns.get(columnIndex), headerLayout);
            }

            tableContainer.addView(headerLayout);
        }

        private void createTableHeaderCell(String text, LinearLayout headerLayout) {
            TextView label = (TextView) inflater.inflate(R.layout.cell_header, null);
            label.setText(text);

            label.setLayoutParams(
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.MATCH_PARENT, 1f));

            headerLayout.addView(label);
        }

        private boolean isImageCell(int position) {
            return items.get(position).hasImage();
        }
    }
}
