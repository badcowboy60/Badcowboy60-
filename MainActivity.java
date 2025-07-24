package com.manu.botpassagedudesir;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private Handler handler = new Handler();
    private Random random = new Random();

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://jeu.passagedudesir.fr/GAM348080616806");
        startBot();
    }

    private void startBot() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int dx = random.nextInt(10) - 5;
                int dy = random.nextInt(10) - 5;
                String js = "(function(){ " +
                        "var evt1=new MouseEvent('mousedown',{bubbles:true,cancelable:true,view:window,clientX:window.innerWidth/2+" + dx + ",clientY:window.innerHeight/2+" + dy + "});" +
                        "document.dispatchEvent(evt1);" +
                        "var evt2=new MouseEvent('mouseup',{bubbles:true,cancelable:true,view:window,clientX:window.innerWidth/2+" + dx + ",clientY:window.innerHeight/2+" + dy + "});" +
                        "document.dispatchEvent(evt2);" +
                        "})();";
                webView.evaluateJavascript(js, null);
                handler.postDelayed(this, 200 + random.nextInt(100));
            }
        }, 1000);
    }
}
