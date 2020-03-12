package com.watconsult.tlakapp.ui.document;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.watconsult.tlakapp.R;

public class PdfFragment extends Fragment {
WebView webView;
String documrntPath;
    ProgressDialog pDialog;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.pdf_layout, container, false);

        Bundle args = getArguments();
        if (args != null) {
            documrntPath = String.valueOf(args.get("documrntPath"));
             System.out.println("documrntPath---s-------"+documrntPath);
        }
      /*  webView = (WebView)root.findViewById(R.id.pdf);
        String myPdfUrl = "http://account.tlakapp.com/tlak/images/uploads/traveldocuments/flight-tickets-vobg.pdf";
        String url = "http://docs.google.com/gview?embedded=true&url=" + myPdfUrl;
        // Log.i(TAG, "Opening PDF: " + url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(myPdfUrl);*/



        final ProgressDialog progressDialog = new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);
        progressDialog.setMessage("Loading Data...");
        progressDialog.setCancelable(false);
        WebView web_view = root.findViewById(R.id.pdf);
        web_view.requestFocus();
        web_view.getSettings().setJavaScriptEnabled(true);
        String myPdfUrl = "http://account.tlakapp.com/tlak/images/uploads/traveldocuments/flight-tickets-vobg.pdf";
       // String myPdfUrl = "gymnasium-wandlitz.de/vplan/vplan.pdf";
        String url = "https://docs.google.com/viewer?embedded = true&url = "+myPdfUrl;
        web_view.loadUrl("https://docs.google.com/viewer?embedded%20=%20true&url="+documrntPath);
        web_view.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        web_view.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100) {
                    progressDialog.show();
                }
                if (progress == 100) {
                    progressDialog.dismiss();
                }
            }
        });

        return root;
    }
}