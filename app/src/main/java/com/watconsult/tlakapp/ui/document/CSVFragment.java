package com.watconsult.tlakapp.ui.document;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.adapter.ItemArrayAdapter;

import java.io.InputStream;
import java.util.List;

public class CSVFragment extends Fragment {
    String documrntPath;
    private ListView listView;
    private ItemArrayAdapter itemArrayAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.csv_layout, container, false);
        listView = (ListView) root.findViewById(R.id.csv_list);
        itemArrayAdapter = new ItemArrayAdapter(getActivity(), R.layout.csv_item_layout);

        Bundle args = getArguments();
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

       /* try {
            CSVReader reader = new CSVReader(new FileReader("flightszoea.csv"));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                System.out.println(nextLine[0] + nextLine[1] + "etc...");
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return root;
    }
}