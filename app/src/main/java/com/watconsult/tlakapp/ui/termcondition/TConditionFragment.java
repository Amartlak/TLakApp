package com.watconsult.tlakapp.ui.termcondition;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.watconsult.tlakapp.R;

public class TConditionFragment extends Fragment {
RelativeLayout relativeLayout;
    private TConditionViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(TConditionViewModel.class);
        View root = inflater.inflate(R.layout.termcon_fragment, container, false);
       /* relativeLayout = (RelativeLayout) root.findViewById(R.id.click);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              *//*  FlightDetail_Fragment a2Fragment = new FlightDetail_Fragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.docDetailList, a2Fragment).commit();*//*
            }
        });*/
    /* final TextView textView = root.findViewById(R.id.text_tools);
        toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}