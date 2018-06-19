package vocabcard.com.puzzleone;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.LinkedList;
import java.util.Random;



public class MainActivity extends ActionBarActivity {
    InterstitialAd mInterstitialAd;
    int playState[] = new int[9];
    int r;
    Random randomValue, randomPlace, randomColor;
    Model model;
    Vibrator vibrator;
    private TextView textView22;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button12;
    private RelativeLayout relativeLayoutVal;
    private LinkedList userMoves, moves, buttons;
    boolean condition;
    int play[][];
    int count = 0;
    static int cost = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vibrator = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
        randomValue = new Random();
        randomPlace = new Random();
        randomColor = new Random();
        relativeLayoutVal = (RelativeLayout) findViewById(R.id.relativeLayout);
        textView22 = (TextView) findViewById(R.id.textView22);
        textView22.setText(count + " / " + cost);
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
        initializeButton();
        userMoves = new LinkedList();

        do {
            initial();
            condition = isSolvable();
        } while (!condition);

        int storeUser[] = new int[9];
        for (int y = 0; y < 9; y++)
            storeUser[y] = playState[y];
        userMoves.add(storeUser);
        new Solve().execute();


    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mInterstitialAd.loadAd(adRequest);
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


    protected void initializeButton() {

        model = new Model();
        buttons = new LinkedList();

        button1 = (Button) findViewById(R.id.button);
        button1.setTag("0");
        buttons.add(button1);

        button2 = (Button) findViewById(R.id.button2);
        button2.setTag("0");
        buttons.add(button2);

        button3 = (Button) findViewById(R.id.button3);
        button3.setTag("0");
        buttons.add(button3);

        button4 = (Button) findViewById(R.id.button4);
        button4.setTag("0");
        buttons.add(button4);

        button5 = (Button) findViewById(R.id.button5);
        button5.setTag("0");
        buttons.add(button5);

        button6 = (Button) findViewById(R.id.button6);
        button6.setTag("0");
        buttons.add(button6);

        button7 = (Button) findViewById(R.id.button7);
        button7.setTag("0");
        buttons.add(button7);

        button8 = (Button) findViewById(R.id.button8);
        button8.setTag("0");
        buttons.add(button8);


        button9 = (Button) findViewById(R.id.button9);
        button9.setTag("0");
        buttons.add(button9);

        button12 = (Button) findViewById(R.id.button12);
        button12.setEnabled(false);
        button12.setVisibility(View.INVISIBLE);

        initial();
    }


    public void initial() {
        playState[8] = -1;
        playState[0] = -1;
        playState[1] = -1;
        playState[2] = -1;
        playState[3] = -1;
        playState[4] = -1;
        playState[5] = -1;
        playState[6] = -1;
        playState[7] = -1;
        r = 0;
        do {
            setValues();
        } while (!check(-1));
        for (int i = 0; i < 9; i++) {

            if (playState[i] != 0)
                ((Button) buttons.get(i)).setText("" + playState[i]);
            else
                ((Button) buttons.get(i)).setText("");
            ((Button) buttons.get(i)).setTag("" + playState[i]);
            setColor();
        }
    }

    public void setValues() {

        int num = randomValue.nextInt(9);
        if (check(num)) {

            playState[randomPlace.nextInt(9)] = num;
        }


    }

    public boolean check(int y) {
        for (int i = 0; i < 9; i++) {
            if (playState[i] == y) {
                return false;
            }
        }
        return true;
    }

    public boolean isSolvable() {

        int t = 0;
        int[] check = new int[8];
        int j = 0;
        for (int i = 0; i < 9; i++) {
            if (playState[i] != 0) {
                check[j] = playState[i];
                j++;
            }
        }
        for (int q = 0; q < 8; q++) {
            int w = check[q];
            for (int e = q + 1; e < 8; e++) {
                if (check[e] < w) {
                    t++;
                }
            }
        }
        if (t != 0 && (t % 2) == 0) {
            return true;
        }
        return false;
    }

    public void change(int a[]) {
        play = new int[3][3];
        int q = 0, w = 0;
        for (int i = 0; i < 9; i++) {
            play[q][w] = a[i];
            w++;
            if (i == 2 || i == 5 || i == 8) {
                q++;
                w = 0;
            }
        }
    }

    public View onClickButton1(View v) {

        int i;
        for (i = 0; i < 9; i++) {

            if (playState[i] == 0) {

                break;
            }
        }
        if (i == 1 || i == 3) {
            button1.setText("");
            button1.setTag("0");
            r = playState[0];
            playState[0] = 0;
            playState[i] = r;
            replace(i);
        }
        return v;
    }

    public View onClickButton2(View v) {

        int i;
        for (i = 0; i < 9; i++) {
            if (playState[i] == 0) {
                break;
            }
        }
        if (i == 0 || i == 2 || i == 4) {
            button2.setText("");
            button2.setTag("0");
            r = playState[1];
            playState[1] = 0;
            playState[i] = r;
            replace(i);
        }
        return v;
    }

    public View onClickButton3(View v) {

        int i;
        for (i = 0; i < 9; i++) {

            if (playState[i] == 0) {
                break;
            }

        }
        if (i == 1 || i == 5) {
            button3.setText("");
            button3.setTag("0");
            r = playState[2];
            playState[2] = 0;
            playState[i] = r;
            replace(i);
        }
        return v;
    }

    public View onClickButton4(View v) {

        int i;
        for (i = 0; i < 9; i++) {
            if (playState[i] == 0) {
                break;
            }

        }
        if (i == 0 || i == 4 || i == 6) {
            button4.setText("");
            button4.setTag("0");
            r = playState[3];
            playState[3] = 0;
            playState[i] = r;
            replace(i);
        }
        return v;
    }

    public View onClickButton5(View v) {

        int i;
        for (i = 0; i < 9; i++) {
            if (playState[i] == 0) {
                break;
            }

        }
        if (i == 1 || i == 3 || i == 5 || i == 7) {
            button5.setText("");
            button5.setTag("0");
            r = playState[4];
            playState[4] = 0;
            playState[i] = r;
            replace(i);
        }
        return v;
    }

    public View onClickButton6(View v) {

        int i;
        for (i = 0; i < 9; i++) {
            if (playState[i] == 0) {
                break;
            }

        }
        if (i == 2 || i == 4 || i == 8) {
            button6.setText("");
            button6.setTag("0");
            r = playState[5];
            playState[5] = 0;
            playState[i] = r;
            replace(i);
        }
        return v;
    }

    public View onClickButton7(View v) {

        int i;
        for (i = 0; i < 9; i++) {
            if (playState[i] == 0) {
                break;
            }

        }
        if (i == 3 || i == 7) {
            button7.setText("");
            button7.setTag("0");
            r = playState[6];
            playState[6] = 0;
            playState[i] = r;
            replace(i);
        }
        return v;
    }

    public View onClickButton8(View v) {

        int i;
        for (i = 0; i < 9; i++) {
            if (playState[i] == 0) {
                break;
            }

        }
        if (i == 4 || i == 6 || i == 8) {
            button8.setText("");
            button8.setTag("0");
            r = playState[7];
            playState[7] = 0;
            playState[i] = r;
            replace(i);
        }
        return v;
    }

    public View onClickButton9(View v) {
        int i;

        for (i = 0; i < 9; i++) {
            if (playState[i] == 0) {
                break;
            }

        }
        if (i == 5 || i == 7) {
            button9.setText("");
            button9.setTag("0");
            r = playState[8];
            playState[8] = 0;
            playState[i] = r;
            replace(i);
        }
        return v;
    }
    public View onClickButton12(View v){
        Moves.setUserMoves(userMoves);
        Moves.setMoves(moves);
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        this.finish();
        return v;
    }

    public void replace(int d) {

        count++;
        textView22.setText(count + " / " + cost);
        if (count >= cost && cost != 0) {
            button12.setEnabled(true);
            button12.setVisibility(View.VISIBLE);
        }
        int storeUser[] = new int[9];
        for (int y = 0; y < 9; y++)
            storeUser[y] = playState[y];
        userMoves.add(storeUser);
        ((Button) buttons.get(d)).setText("" + playState[d]);
        ((Button) buttons.get(d)).setTag("" + playState[d]);
        setColor();
        if (mInterstitialAd.isLoaded() && count % 7 ==0 && count > cost) {
            mInterstitialAd.show();
        }
    }

    public void setColor() {

        int colorValue = randomColor.nextInt(9);
        relativeLayoutVal.setBackgroundColor(Color.parseColor(model.background(colorValue)));
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.parseColor(model.text(colorValue)));
        }
        button1.setTextColor(Color.parseColor(model.text(colorValue)));
        button2.setTextColor(Color.parseColor(model.text(colorValue)));
        button3.setTextColor(Color.parseColor(model.text(colorValue)));
        button4.setTextColor(Color.parseColor(model.text(colorValue)));
        button5.setTextColor(Color.parseColor(model.text(colorValue)));
        button6.setTextColor(Color.parseColor(model.text(colorValue)));
        button7.setTextColor(Color.parseColor(model.text(colorValue)));
        button8.setTextColor(Color.parseColor(model.text(colorValue)));
        button9.setTextColor(Color.parseColor(model.text(colorValue)));
        button12.setTextColor(Color.parseColor(model.text(colorValue)));
        vibrator.vibrate(30);
        match();

    }

    public void match() {
        int[] finalState = model.getFinalState();
        int c = 0;
        for (int i = 0; i < 9; i++) {
            if (playState[i] == finalState[i]) {
                c++;
            }
        }
        if (c == 9) {
            (Toast.makeText(this, "Completed", Toast.LENGTH_SHORT)).show();
        }
    }

   public void display() {

       textView22.setText(count + " / " + cost);

       if (count >= cost && cost != 0) {
           button12.setEnabled(true);
           button12.setVisibility(View.VISIBLE);

       }
   }

    public class Solve extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPostExecute(Void v) {
            display();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            change(playState);
            if (condition) {
                ManhattanDistance md = new ManhattanDistance();
                md.manhattanDistances(play);
                State st = new State();
                st.setEvaluationFunction(md.getDis());
                st.setManhattanDistance(md.getDis());
                st.setState(play);
                st.setCost(0);
                Solver s = new Solver(st);
                moves = s.getMoves();
            }
            return null;
        }
    }

}
