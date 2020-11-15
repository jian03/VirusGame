package kr.hs.emirim.s2019s19.virusgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    TextView count;
    TextView startcountdown;
    private int btncount = 0, btncount2 = 0;
    private int media_pos;
    private static MediaPlayer mp;
    ImageButton btnpause, btnsoundon, btnclose, btngo, btnrefresh, btnhome;
    TimerThread thread;
    MyThread thread2;
    LinearLayout q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, q15, q16, q17, q18, q19, q20, notanswer, rightanswer;
    EditText ae1, ae2, ae3, ae4, ae5, ae6, ae7, ae8, ae9, ae10, ae11, ae12, ae13, ae14, ae15, ae16, ae17, ae18, ae19, ae20;
    Button ab1, ab2, ab3, ab4, ab5, ab6, ab7, ab8, ab9, ab10, ab11, ab12, ab13, ab14, ab15, ab16, ab17, ab18, ab19, ab20, overbtnhome, overbtnrefresh;
    RelativeLayout lineargameover;
    boolean loopFlag = true;
    boolean isFirst = true;
    boolean isRun = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final LinearLayout linear = (LinearLayout)inflater.inflate(R.layout.activity_countdown, null);
        final LinearLayout.LayoutParams paramlinear = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        final FrameLayout.LayoutParams paramframe = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        );
        linear.setBackgroundColor(Color.parseColor("#99000000")); // 배경 불투명도 설정
        addContentView(linear, paramlinear);
        final View view = (View)getLayoutInflater().inflate(R.layout.activity_pause, null);
        final FrameLayout frame = view.findViewById(R.id.frame);
        frame.setBackgroundColor(Color.parseColor("#99000000"));

        count = findViewById(R.id.timer);
        btnpause = findViewById(R.id.btn_pause);
        btnclose = view.findViewById(R.id.btn_close);
        btngo = view.findViewById(R.id.btn_go);
        btnhome = view.findViewById(R.id.btn_home);
        btnrefresh = view.findViewById(R.id.btn_refresh);
        btnsoundon = findViewById(R.id.btn_soundon);
        startcountdown = (TextView) findViewById(R.id.count3);

        addContentView(frame, paramframe);
        frame.setVisibility(View.INVISIBLE);
        btnpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
                frame.setVisibility(View.VISIBLE);
                isRun = false;
                isFirst = false;
            }
        });
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                frame.setVisibility(View.GONE);
                isRun = true;
                isFirst = true;
            }
        });
        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                frame.setVisibility(View.GONE);
                isRun = true;
                isFirst = true;
            }
        });
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
            }
        });

        btnsoundon.setOnClickListener(btnListener);
        frame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        overbtnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        overbtnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
            }
        });


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //상단바 숨기기

        mp = MediaPlayer.create(this, R.raw.backmusic);
        mp.setLooping(true);

        Thread mThread = new Thread(); // 321
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                thread = new TimerThread();
                thread.start();
            }

        });

        Thread mThread2 = new Thread(); // 100
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                linear.setVisibility(View.GONE);
                thread2 = new MyThread();
                thread2.start();
                mp.start();
                q1 = findViewById(R.id.q1);
                q1.setVisibility(View.VISIBLE);
            }
        }, 4000); // 4초 뒤에 카운트다운 시작

        ab1.setOnClickListener(new View.OnClickListener() { //문제 1
            @Override
            public void onClick(View v) { //문제 1
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae1.getWindowToken(), 0); // 키보드 내리기
                if(ae1.getText().toString().equals("OBJECT")) {
                    rightanswer.setVisibility(View.VISIBLE);
                } else {
                    notanswer.setVisibility(View.VISIBLE);
                }
                q1.setVisibility(View.INVISIBLE);
                q2.setVisibility(View.VISIBLE);
                ab1.setVisibility(View.INVISIBLE);
                ab2.setVisibility(View.VISIBLE);
            }
        });

        ab2.setOnClickListener(new View.OnClickListener() { //문제 2
            @Override
            public void onClick(View v) {  //문제 2
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae2.getWindowToken(), 0); // 키보드 내리기
                if(ae2.getText().toString().equals("EARTH")) {
                    rightanswer.setVisibility(View.VISIBLE);
                } else {
                    notanswer.setVisibility(View.VISIBLE);
                }
                q2.setVisibility(View.INVISIBLE);
                q3.setVisibility(View.VISIBLE);
                ab2.setVisibility(View.INVISIBLE);
                ab3.setVisibility(View.VISIBLE);
            }
        });

        ab3.setOnClickListener(new View.OnClickListener() { //문제 3
            @Override
            public void onClick(View v) {  //문제 3
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae3.getWindowToken(), 0); // 키보드 내리기
                if(ae3.getText().toString().equals("OCEAN")) {
                    rightanswer.setVisibility(View.VISIBLE);
                } else {
                    notanswer.setVisibility(View.VISIBLE);
                }
                q3.setVisibility(View.INVISIBLE);
                q4.setVisibility(View.VISIBLE);
                ab3.setVisibility(View.INVISIBLE);
                ab4.setVisibility(View.VISIBLE);
            }
        });

        ab4.setOnClickListener(new View.OnClickListener() { //문제 4
            @Override
            public void onClick(View v) {  //문제 4
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae4.getWindowToken(), 0); // 키보드 내리기
                if(ae4.getText().toString().equals("CENTER")) {
                    rightanswer.setVisibility(View.VISIBLE);
                } else {
                    notanswer.setVisibility(View.VISIBLE);
                }
                q4.setVisibility(View.INVISIBLE);
                q5.setVisibility(View.VISIBLE);
                ab4.setVisibility(View.INVISIBLE);
                ab5.setVisibility(View.VISIBLE);
            }
        });

        ab5.setOnClickListener(new View.OnClickListener() { //문제 5
            @Override
            public void onClick(View v) {  //문제 5
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae5.getWindowToken(), 0); // 키보드 내리기
                if(ae5.getText().toString().equals("HUNTER")) {
                    rightanswer.setVisibility(View.VISIBLE);
                } else {
                    notanswer.setVisibility(View.VISIBLE);
                }
                q5.setVisibility(View.INVISIBLE);
                q6.setVisibility(View.VISIBLE);
                ab5.setVisibility(View.INVISIBLE);
                ab6.setVisibility(View.VISIBLE);
            }
        });

        ab6.setOnClickListener(new View.OnClickListener() { //문제 6
            @Override
            public void onClick(View v) {  //문제 6
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae6.getWindowToken(), 0); // 키보드 내리기
                if(ae6.getText().toString().equals("SHEEP")) {
                    rightanswer.setVisibility(View.VISIBLE);
                } else {
                    notanswer.setVisibility(View.VISIBLE);
                }
                q6.setVisibility(View.INVISIBLE);
                q7.setVisibility(View.VISIBLE);
                ab6.setVisibility(View.INVISIBLE);
                ab7.setVisibility(View.VISIBLE);
            }
        });

        ab7.setOnClickListener(new View.OnClickListener() { //문제 7
            @Override
            public void onClick(View v) {  //문제 7
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae7.getWindowToken(), 0); // 키보드 내리기
                if(ae7.getText().toString().equals("PIECE")) {
                    rightanswer.setVisibility(View.VISIBLE);
                } else {
                    notanswer.setVisibility(View.VISIBLE);
                }
                q7.setVisibility(View.INVISIBLE);
                q8.setVisibility(View.VISIBLE);
                ab7.setVisibility(View.INVISIBLE);
                ab8.setVisibility(View.VISIBLE);
            }
        });

        ab8.setOnClickListener(new View.OnClickListener() { //문제 8
            @Override
            public void onClick(View v) {  //문제 8
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae8.getWindowToken(), 0); // 키보드 내리기
                if(ae8.getText().toString().equals("FRUIT")) {
                    rightanswer.setVisibility(View.VISIBLE);
                } else {
                    notanswer.setVisibility(View.VISIBLE);
                }
                q8.setVisibility(View.INVISIBLE);
                q9.setVisibility(View.VISIBLE);
                ab8.setVisibility(View.INVISIBLE);
                ab9.setVisibility(View.VISIBLE);
            }
        });

        ab9.setOnClickListener(new View.OnClickListener() { //문제 9
            @Override
            public void onClick(View v) {  //문제 9
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae9.getWindowToken(), 0); // 키보드 내리기
                if(ae9.getText().toString().equals("VOICE")) {
                    rightanswer.setVisibility(View.VISIBLE);
                } else {
                    notanswer.setVisibility(View.VISIBLE);
                }
                q9.setVisibility(View.INVISIBLE);
                q10.setVisibility(View.VISIBLE);
                ab9.setVisibility(View.INVISIBLE);
                ab10.setVisibility(View.VISIBLE);
            }
        });

        ab10.setOnClickListener(new View.OnClickListener() { //문제 10
            @Override
            public void onClick(View v) {  //문제 10
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae10.getWindowToken(), 0); // 키보드 내리기
                if(ae10.getText().toString().equals("CODE")) {
                    rightanswer.setVisibility(View.VISIBLE);
                } else {
                    notanswer.setVisibility(View.VISIBLE);
                }
                q10.setVisibility(View.INVISIBLE);
                q11.setVisibility(View.VISIBLE);
                ab10.setVisibility(View.INVISIBLE);
                ab11.setVisibility(View.VISIBLE);
            }
        });

        ab11.setOnClickListener(new View.OnClickListener() { //문제 11
            @Override
            public void onClick(View v) {  //문제 11
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae11.getWindowToken(), 0); // 키보드 내리기
                if(ae11.getText().toString().equals("MARS")) {
                    rightanswer.setVisibility(View.VISIBLE);
                } else {
                    notanswer.setVisibility(View.VISIBLE);
                }
                q11.setVisibility(View.INVISIBLE);
                q12.setVisibility(View.VISIBLE);
                ab11.setVisibility(View.INVISIBLE);
                ab12.setVisibility(View.VISIBLE);
            }
        });

        ab12.setOnClickListener(new View.OnClickListener() { //문제 12
            @Override
            public void onClick(View v) {  //문제 12
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae12.getWindowToken(), 0); // 키보드 내리기
                if(ae12.getText().toString().equals("FRENCH")) {
                    rightanswer.setVisibility(View.VISIBLE);
                } else {
                    notanswer.setVisibility(View.VISIBLE);
                }
                q12.setVisibility(View.INVISIBLE);
                q13.setVisibility(View.VISIBLE);
                ab12.setVisibility(View.INVISIBLE);
                ab13.setVisibility(View.VISIBLE);
            }
        });

        ab13.setOnClickListener(new View.OnClickListener() { //문제 13
            @Override
            public void onClick(View v) {  //문제 13
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae13.getWindowToken(), 0); // 키보드 내리기
                if(ae13.getText().toString().equals("MOUSE")) {
                    rightanswer.setVisibility(View.VISIBLE);
                } else {
                    notanswer.setVisibility(View.VISIBLE);
                }
                q13.setVisibility(View.INVISIBLE);
                q14.setVisibility(View.VISIBLE);
                ab13.setVisibility(View.INVISIBLE);
                ab14.setVisibility(View.VISIBLE);
            }
        });

        ab14.setOnClickListener(new View.OnClickListener() { //문제 14
            @Override
            public void onClick(View v) {  //문제 14
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae14.getWindowToken(), 0); // 키보드 내리기
                if(ae14.getText().toString().equals("PLANE")) {
                    rightanswer.setVisibility(View.VISIBLE);
                } else {
                    notanswer.setVisibility(View.VISIBLE);
                }
                q14.setVisibility(View.INVISIBLE);
                q15.setVisibility(View.VISIBLE);
                ab14.setVisibility(View.INVISIBLE);
                ab15.setVisibility(View.VISIBLE);
            }
        });

        ab15.setOnClickListener(new View.OnClickListener() { //문제 15
            @Override
            public void onClick(View v) {  //문제 15
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae15.getWindowToken(), 0); // 키보드 내리기
                if(ae15.getText().toString().equals("WEATHER")) {
                    rightanswer.setVisibility(View.VISIBLE);
                } else {
                    notanswer.setVisibility(View.VISIBLE);
                }
                q15.setVisibility(View.INVISIBLE);
                q16.setVisibility(View.VISIBLE);
                ab15.setVisibility(View.INVISIBLE);
                ab16.setVisibility(View.VISIBLE);
            }
        });

        ab16.setOnClickListener(new View.OnClickListener() { //문제 16
            @Override
            public void onClick(View v) {  //문제 16
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae16.getWindowToken(), 0); // 키보드 내리기
                if(ae16.getText().toString().equals("PHONE")) {
                    rightanswer.setVisibility(View.VISIBLE);
                } else {
                    notanswer.setVisibility(View.VISIBLE);
                }
                q16.setVisibility(View.INVISIBLE);
                q17.setVisibility(View.VISIBLE);
                ab16.setVisibility(View.INVISIBLE);
                ab17.setVisibility(View.VISIBLE);
            }
        });

        ab17.setOnClickListener(new View.OnClickListener() { //문제 17
            @Override
            public void onClick(View v) {  //문제 17
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae17.getWindowToken(), 0); // 키보드 내리기
                if(ae17.getText().toString().equals("PUZZLE")) {
                    rightanswer.setVisibility(View.VISIBLE);
                } else {
                    notanswer.setVisibility(View.VISIBLE);
                }
                q17.setVisibility(View.INVISIBLE);
                q18.setVisibility(View.VISIBLE);
                ab17.setVisibility(View.INVISIBLE);
                ab18.setVisibility(View.VISIBLE);
            }
        });

        ab18.setOnClickListener(new View.OnClickListener() { //문제 18
            @Override
            public void onClick(View v) {  //문제 18
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae18.getWindowToken(), 0); // 키보드 내리기
                if(ae18.getText().toString().equals("CAMERA")) {
                    rightanswer.setVisibility(View.VISIBLE);
                } else {
                    notanswer.setVisibility(View.VISIBLE);
                }
                q18.setVisibility(View.INVISIBLE);
                q19.setVisibility(View.VISIBLE);
                ab18.setVisibility(View.INVISIBLE);
                ab19.setVisibility(View.VISIBLE);
            }
        });

        ab19.setOnClickListener(new View.OnClickListener() { //문제 19
            @Override
            public void onClick(View v) {  //문제 19
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae19.getWindowToken(), 0); // 키보드 내리기
                if(ae19.getText().toString().equals("PROGRAM")) {
                    rightanswer.setVisibility(View.VISIBLE);
                } else {
                    notanswer.setVisibility(View.VISIBLE);
                }
                q19.setVisibility(View.INVISIBLE);
                q20.setVisibility(View.VISIBLE);
                ab19.setVisibility(View.INVISIBLE);
                ab20.setVisibility(View.VISIBLE);
            }
        });

        ab20.setOnClickListener(new View.OnClickListener() { //문제 20
            @Override
            public void onClick(View v) {  //문제 20
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae20.getWindowToken(), 0); // 키보드 내리기
                if(ae20.getText().toString().equals("PENCIL")) {
                    rightanswer.setVisibility(View.VISIBLE);
                } else {
                    notanswer.setVisibility(View.VISIBLE);
                }
                //게임 클리어 들어갈 곳
            }
        });

    }


    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_soundon:
                    if(btncount2 % 2 == 0) { // 음소거
                        btnsoundon.setImageResource(R.drawable.soundoff);
                        mp.pause();
                        media_pos = mp.getCurrentPosition();
                    } else if(btncount2 % 2 == 1) { // 재생
                        btnsoundon.setImageResource(R.drawable.soundon);
                        mp.seekTo(media_pos);
                        mp.start();
                    }
                    btncount2++;
                    break;
            }
        }
    };

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what == 1){
                count.setText("Time : "+String.valueOf(msg.arg1));
            }else if (msg.what == 2){
                count.setText(String.valueOf(msg.obj));
            }
        }
    };
    Handler autoCountHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what == 1){
                startcountdown.setText(String.valueOf(msg.arg1));
            }else if (msg.what == 2){
                startcountdown.setText(String.valueOf(msg.obj));
            }
        }
    };

    class TimerThread extends Thread {
        public void run() {
            try {
                int startcount = 4;
                while (loopFlag) { // true
                    if (isRun) {
                        startcount--;
                        Message message = new Message();
                        message.what = 1;
                        message.arg1 = startcount;
                        autoCountHandler.sendMessage(message);
                        if (startcount == 0) {
                            message = new Message();
                            message.what = 2;
                            message.obj = "Start!";
                            autoCountHandler.sendMessage(message);

                            //theread 종료되게 설정
                            loopFlag = false;
                        }
                        Thread.sleep(1000);
                    }
                }
            } catch (InterruptedException ex) {
            }
        }
    }

    class MyThread extends Thread {
        public void run() {
            try{
                int countDown=101;
                loopFlag = true;
                while (loopFlag){ // true
                    if (isRun) {
                        countDown--;
                        Message message = new Message();
                        message.what = 1;
                        message.arg1 = countDown;
                        handler.sendMessage(message);
                        if (countDown == 0) {
                            lineargameover.setVisibility(View.VISIBLE);
                            //theread 종료되게 설정
                            loopFlag = false;
                            mp.stop();
                        }
                    }
                    Thread.sleep(1000);

                }
            } catch (Exception e){
            }
        }
    }

    public void onBackPressed() { //뒤로 가기 버튼 불가능
    }
}
