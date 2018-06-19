package vocabcard.com.puzzleone;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.LinkedList;


public class MainActivity2 extends ActionBarActivity {

    InterstitialAd mInterstitialAd;
    ArrayList textViews, userTextViews;
    int count = 0, steps = 0, userSteps = 0, userCount;
    LinkedList lk, userMoves;
    int size, userSize;
    RelativeLayout relativeLayout;
    Vibrator vibrator;
    int count_touch=0;
    int min_moves = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.parseColor("#1976D2"));
        }
        vibrator = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
        vibrator.vibrate(30);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-2785868738816505/8087099674");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();

            }
        });

        requestNewInterstitial();
        initialize();

    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mInterstitialAd.loadAd(adRequest);
    }


    protected void initialize() {

        textViews = new ArrayList();
        userTextViews = new ArrayList();
        textViews.add(findViewById(R.id.textView30));
        textViews.add(findViewById(R.id.textView31));
        textViews.add(findViewById(R.id.textView32));
        textViews.add(findViewById(R.id.textView33));
        textViews.add(findViewById(R.id.textView34));
        textViews.add(findViewById(R.id.textView35));
        textViews.add(findViewById(R.id.textView36));
        textViews.add(findViewById(R.id.textView37));
        textViews.add(findViewById(R.id.textView38));

        userTextViews.add(findViewById(R.id.textView24));
        userTextViews.add(findViewById(R.id.textView25));
        userTextViews.add(findViewById(R.id.textView26));
        userTextViews.add(findViewById(R.id.textView27));
        userTextViews.add(findViewById(R.id.textView28));
        userTextViews.add(findViewById(R.id.textView29));
        userTextViews.add(findViewById(R.id.textView20));
        userTextViews.add(findViewById(R.id.textView21));
        userTextViews.add(findViewById(R.id.textView23));
        relativeLayout = ((RelativeLayout) findViewById(R.id.resultRelative));
        relativeLayout.setOnTouchListener(new OnTouchListen());
        setLinkList();
    }

    protected void setLinkList() {
        lk = Moves.getMoves();
        min_moves = lk.size();
        userMoves = Moves.getUserMoves();
        size = lk.size();
        userSize = userMoves.size();
        userCount = 0;
        setUserValue((int[]) userMoves.get(userCount));
        count = size - 1;
        setValue((int[][]) lk.get(count));
        count--;
        userCount++;
        ((TextView) findViewById(R.id.textView39)).setText("User: " + userSteps);
        ((TextView) findViewById(R.id.textView40)).setText("PuzzleOne: " + steps);
    }

    public void setValue(int at[][]) {
        int c = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (at[i][j] != 0) {
                    ((TextView) textViews.get(c)).setBackgroundColor(Color.WHITE);
                    ((TextView) textViews.get(c)).setText(at[i][j] + "");
                } else {
                    ((TextView) textViews.get(c)).setText("");
                    ((TextView) textViews.get(c)).setBackgroundColor(Color.RED);
                }
                c++;
            }

        }
    }

    public void setUserValue(int at[]) {

        for (int i = 0; i < 9; i++) {
            if (at[i] != 0) {
                ((TextView) userTextViews.get(i)).setBackgroundColor(Color.WHITE);
                ((TextView) userTextViews.get(i)).setText(at[i] + "");
            } else {
                ((TextView) userTextViews.get(i)).setText("");
                ((TextView) userTextViews.get(i)).setBackgroundColor(Color.RED);
            }

        }

    }

    protected class OnTouchListen implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent event) {

            int action = event.getActionMasked();

            switch (action) {

                case MotionEvent.ACTION_DOWN:

                    next();
                    break;

                case MotionEvent.ACTION_MOVE:
                    next();
                    break;

                case MotionEvent.ACTION_UP:
                    next();

                    break;

                case MotionEvent.ACTION_CANCEL:
                    next();
                    break;

                case MotionEvent.ACTION_OUTSIDE:
                    next();
                    break;
            }

            return false;


        }

        protected void next() {
               count_touch += 1;
            if (mInterstitialAd.isLoaded() && count_touch % 7 == 0 && count_touch > min_moves) {
                mInterstitialAd.show();
            } else {
                if (count >= 0) {
                    setValue((int[][]) lk.get(count));
                    count--;
                    steps++;

                }
                if (userCount < userSize) {
                    setUserValue((int[]) userMoves.get(userCount));
                    userCount++;
                    userSteps++;
                }
                ((TextView) findViewById(R.id.textView39)).setText("User: " + userSteps);
                ((TextView) findViewById(R.id.textView40)).setText("PuzzleOne: " + steps);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //do whatever you need for the hardware 'back' button
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }

            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        finish();
    }

}