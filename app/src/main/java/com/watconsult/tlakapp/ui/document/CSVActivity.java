package com.watconsult.tlakapp.ui.document;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.adapter.ItemArrayAdapter;

import java.io.InputStream;
import java.util.List;

public class CSVActivity extends AppCompatActivity {
    String documrntPath;
    private ListView listView;
    private ItemArrayAdapter itemArrayAdapter;
    ImageView back_btnx_txt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.csv_layout);
        listView = (ListView) findViewById(R.id.csv_list);
        itemArrayAdapter = new ItemArrayAdapter(this, R.layout.csv_item_layout);
        back_btnx_txt = (ImageView) findViewById(R.id.back_btnx_txt);
        back_btnx_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Bundle args = getIntent().getExtras();
        if (args != null) {
            documrntPath = String.valueOf(args.get("documrntPath"));
            System.out.println("documrntPath---s-------"+documrntPath);
        }

        Parcelable state = listView.onSaveInstanceState();
        listView.setAdapter(itemArrayAdapter);
        listView.onRestoreInstanceState(state);

        InputStream inputStream = getResources().openRawResource(R.raw.flightszoea);
        CSVFile csvFile = new CSVFile(inputStream);
        List scoreList = csvFile.read();

        for(Object scoreData:scoreList ) {
            itemArrayAdapter.add(scoreData);
        }

    }
}
