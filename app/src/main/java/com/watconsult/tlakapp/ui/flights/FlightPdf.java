package com.watconsult.tlakapp.ui.flights;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.watconsult.tlakapp.R;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FlightPdf extends AppCompatActivity {
    WebView webView;
    String documrntPath;
    ProgressDialog pDialog;
    ImageView back_btnx_txt;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_layout);
        Bundle args = getIntent().getExtras();
        if (args != null) {
            documrntPath = String.valueOf(args.get("ticket"));
            System.out.println("documrntPath---s-ddd------" + documrntPath);
        }
        back_btnx_txt = (ImageView) findViewById(R.id.back_btnx_txt);
        back_btnx_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mProgressDialog = new ProgressDialog(this, R.style.MyAlertDialogStyle);
        mProgressDialog.setMessage("Loading Data...");
        mProgressDialog.setCancelable(false);

//        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);


// execute this when the downloader must be fired
        final DownloadTask downloadTask = new DownloadTask(FlightPdf.this);
        downloadTask.execute("https://account.tlakapp.com/tlak/images/uploads/flight/passes/hchxp_59.pdf");

        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                downloadTask.cancel(true);
            }
        });


        WebView web_view = (WebView) findViewById(R.id.pdf);
        web_view.requestFocus();
        web_view.getSettings().setJavaScriptEnabled(true);
        String myPdfUrl = "https://account.tlakapp.com/tlak/images/uploads/flight/passes/hchxp_59.pdf";
        // String myPdfUrl = "gymnasium-wandlitz.de/vplan/vplan.pdf";
        String url = "https://docs.google.com/viewer?embedded = true&url = " + myPdfUrl;
        web_view.loadUrl("https://docs.google.com/viewer?embedded%20=%20true&url=" + myPdfUrl);
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
                    mProgressDialog.show();
                }
                if (progress == 100) {
                    mProgressDialog.dismiss();
                }
            }
        });
    }

    private class DownloadTask extends AsyncTask<String, Integer, String> {

        private Context context;
        private PowerManager.WakeLock mWakeLock;

        public DownloadTask(Context context) {
            this.context = context;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // take CPU lock to prevent CPU from going off if the user
            // presses the power button during download
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    getClass().getName());
            mWakeLock.acquire();
            mProgressDialog.show();
        }
        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // if we get here, length is known, now set indeterminate to false
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgress(progress[0]);
        }
        @Override
        protected void onPostExecute(String result) {
            mWakeLock.release();
            mProgressDialog.dismiss();
            if (result != null)
                Toast.makeText(context,"Download error: "+result, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(context,"File downloaded", Toast.LENGTH_SHORT).show();
        }
        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(sUrl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                // download the file
                input = connection.getInputStream();
                output = new FileOutputStream("/sdcard/file_name.extension");

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }
            return null;
        }
    }
}
