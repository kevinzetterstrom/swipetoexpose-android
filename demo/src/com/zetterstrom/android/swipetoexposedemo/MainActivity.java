package com.zetterstrom.android.swipetoexposedemo;

import java.util.ArrayList;

import com.zetterstrom.android.swipetoexpose.SwipeExposeListView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private SwipeExposeListView mListView;
    private SwipeExposeAdapter mAdapter;

    private OnItemClickListener mClickHandler = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
            Toast.makeText(getApplicationContext(), "Clicked the list item",
                    Toast.LENGTH_SHORT).show();

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new SwipeExposeAdapter(this);
        mListView = (SwipeExposeListView) findViewById(R.id.swipetoexposelistview);
        mListView.setHiddenLayout(R.id.hidden_view);
        mListView.setFrontLayout(R.id.front_layout);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(mClickHandler);

    }

    private class SwipeExposeAdapter extends BaseAdapter {

        private ArrayList<String> mItems = new ArrayList<String>();
        private Context mContext;

        public SwipeExposeAdapter(Context context) {
            mContext = context;
            for (int i = 0; i < 50; i++) {
                mItems.add("View #" + String.valueOf(i));
            }
        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            //if (v == null) {
                LayoutInflater vi = (LayoutInflater) mContext
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.list_item_expose, null);
            //}

            final String item = mItems.get(position);
            TextView text = (TextView) v.findViewById(R.id.front_text);
            text.setText("Front View " + item);
            TextView hiddenView = (TextView) v.findViewById(R.id.hidden_view);
            hiddenView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, item + " hidden view clicked",
                            Toast.LENGTH_SHORT).show();

                }
            });

            return v;
        }

    }
}
