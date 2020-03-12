package com.watconsult.tlakapp.ui.document;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.watconsult.tlakapp.R;

public class PDFActivity extends AppCompatActivity {
    WebView webView;
    String documrntPath;
    ProgressDialog pDialog;
    ImageView back_btnx_txt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_layout);
        Bundle args = getIntent().getExtras();
        if (args != null) {
            documrntPath = String.valueOf(args.get("documrntPath"));
            System.out.println("documrntPath---s-------"+documrntPath);
        }
        back_btnx_txt = (ImageView) findViewById(R.id.back_btnx_txt);
        back_btnx_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
      /*  webView = (WebView)root.findViewById(R.id.pdf);
        String myPdfUrl = "http://account.tlakapp.com/tlak/images/uploads/traveldocuments/flight-tickets-vobg.pdf";
        String url = "http://docs.google.com/gview?embedded=true&url=" + myPdfUrl;
        // Log.i(TAG, "Opening PDF: " + url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(myPdfUrl);*/



        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.MyAlertDialogStyle);
        progressDialog.setMessage("Loading Data...");
        progressDialog.setCancelable(false);
        WebView web_view = (WebView) findViewById(R.id.pdf);
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
    }
}
