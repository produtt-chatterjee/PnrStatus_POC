package com.example.trainpnrsubmit.ui.login;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.renderscript.RenderScript;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.example.trainpnrsubmit.R;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class PnrActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final WebView myWebView = new WebView(this.getApplicationContext());
        setContentView(myWebView);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setDomStorageEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.setClickable(true);
        myWebView.setWebChromeClient(new WebChromeClient());
        myWebView.getSettings().setSaveFormData(true);
        myWebView.getSettings().setSupportZoom(true);
        myWebView.setVisibility(View.GONE);
        myWebView.loadUrl("http://www.indianrail.gov.in/enquiry/PNR/PnrEnquiry.html?locale=en");
        Bundle bundle = getIntent().getExtras();
        final String pnrNoText = bundle.getString("pnrNo");

        myWebView.setWebViewClient(new WebViewClient() {

            String a = "";

            /*@Override
            public void onLoadResource(WebView view, String url) {
                if (url.contains("captchaDraw")) {
                    DownloadManager mdDownloadManager = (DownloadManager) getApplicationContext()
                            .getSystemService(Context.DOWNLOAD_SERVICE);
                    DownloadManager.Request request = new DownloadManager.Request(
                            Uri.parse(url));
                    String endPart = getFileName(url);
                    File destinationFile = new File(
                            Environment.getExternalStorageDirectory().listFiles()[8],
                            endPart);

                    request.setDescription("Downloading ...");
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.setDestinationUri(Uri.fromFile(destinationFile));
                    mdDownloadManager.enqueue(request);
                    try {
                        Thread.sleep(3000);
                    }catch (InterruptedException e){

                    }
                    String base = Environment.getExternalStorageDirectory().listFiles()[8].getAbsolutePath().toString();
                    String imagePath = "file://"+ base + "/" + endPart;
                    String html = "<html><head></head><body><img src=\""+ imagePath + "\"></body></html>";
                    view.loadDataWithBaseURL("", html, "text/html","utf-8", "");
                }
            }*/

            public String getFileName(String url) {
                String filenameWithoutExtension = "";
                filenameWithoutExtension = String.valueOf(System.currentTimeMillis()
                        + ".jpg");
                return filenameWithoutExtension;
            }

            /*@Override
            public void onLoadResource(WebView view, String url) {
                if (url.contains("captchaDraw")) {
                    try {

                        System.out.println("target:" + url);
                        final URL aURL = new URL(url);
                        final String myurl = url;
                        System.out.println("target:" + myurl);


                        Thread thread = new Thread(new Runnable() {

                            @Override
                            public void run() {
                                try  {
                                    URLConnection conn = aURL.openConnection();
                                    conn.connect();
                                    InputStream is = conn.getInputStream();
                                    String captchaContent = convertToString(is);
                                    System.out.println("CaptchaContent : " + captchaContent);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        thread.start();
                    }catch (IOException e){

                    }
                }
            }*/

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String pnrInput = pnrNoText;
                view.loadUrl("javascript:{" +
                        "window.scrollTo(100,700);" +
                        "}");
                view.loadUrl("javascript:{" +
                        "var x = document.getElementById('inputPnrNo').value = '" + pnrInput + "';" +
                        "}");

                view.loadUrl("javascript:{" +
                        "var element = document.getElementById('modal1');" +
                        "if (typeof(element) != 'undefined' && element != null){var y = document.getElementById('modal1').click();}" +
                        "else { var capelement = document.getElementById('submitPnrNo');" +
                        "if (typeof(capelement) != 'undefined' && capelement != null){var y = document.getElementById('submitPnrNo').click();} }" +
                        "}");

                myWebView.setVisibility(View.VISIBLE);

                /*
                Picture picture = view.capturePicture();
                int width = picture.getWidth();
                int height = picture.getHeight();
                Bitmap bitmap = null;
                if (width > 0 && height > 0) {
                     bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmap);
                    picture.draw(canvas);
                }
                FileOutputStream fos = null;
                try {
                    String v = Environment.getExternalStorageDirectory().listFiles()[8].getAbsolutePath().toString() + "/myImg.jpg";
                    fos = new FileOutputStream( v);
                    if ( fos != null ) {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos );
                        fos.close();
                    }
                }
                catch( Exception e ) {
                    System.out.println("-----error--"+e);
                }

                try {
                    Thread.sleep(3000);
                }catch (InterruptedException e){

                }

                String base = Environment.getExternalStorageDirectory().listFiles()[8].getAbsolutePath().toString();
                String imagePath = "file://"+ base + "/" + "myImg.jpg";
                String html = "<html><head></head><body><img src=\""+ imagePath + "\"></body></html>";
                view.loadDataWithBaseURL("", html, "text/html","utf-8", "");

                */

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void imageFileToByte(final String name) throws IOException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL("http://www.indianrail.gov.in/enquiry/captchaDraw.png?1588785187018");
                    InputStream is = url.openStream();
                    OutputStream os = new FileOutputStream(name);

                    byte[] b = new byte[2048];
                    int length;

                    while ((length = is.read(b)) != -1) {
                        os.write(b, 0, length);
                    }

                    String s = new String(b, StandardCharsets.UTF_8);
                    System.out.println("Output : " + s);

                    is.close();
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String convertToString(InputStream inputStream) throws IOException {
        StringBuffer string = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        byte[] arr = buffer.toByteArray();
        String encodedStr = Base64.encodeToString(arr, Base64.DEFAULT);

        System.out.println("Original String: " + encodedStr);

        //decoding byte array into base64
        byte[] decoded = Base64.decode(encodedStr, Base64.DEFAULT);
        String ans = new String(arr, StandardCharsets.UTF_8);
        System.out.println("Base 64 Decoded  String : " + new String(arr));
        return new String(decoded);
    }
}
