package com.example.luongoc.n3_13_androidfitness.Main3CoBung;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.luongoc.n3_13_androidfitness.Main2.Main2CoBung;
import com.example.luongoc.n3_13_androidfitness.PrefManage;
import com.example.luongoc.n3_13_androidfitness.R;

import java.util.Calendar;
import java.util.Locale;

public class Main2CoBungBicycle extends AppCompatActivity {
    private ImageView imgBackCobung;
    private TextView tvDate;
    private DatePickerDialog.OnDateSetListener mDateSetListenner;

    //Timer
    private static final long START_TIME_IN_MILLIS= 90000;
    private CountDownTimer mCountDownTimer;
    private boolean mTimeRunning;
    private long mTimeLeftOnMillis= START_TIME_IN_MILLIS;
    private Button btnReset, btnStartPause;
    private TextView tvTimer;


    //Slider
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private PrefManage prefManager;

    //Music
    private MediaPlayer player;
    private Button btnMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_co_bung_bicycle);
        //Music
        player= MediaPlayer.create(this, R.raw.vietnamvanhungchuyendi);

        //Slider
        //Kiểm tra khởi chạy đầu tiên
        prefManager = new PrefManage(this);
        setContentView(R.layout.activity_main2_co_bung_bicycle);


        /////
        imgBackCobung=findViewById(R.id.imgBackCobung);
        imgBackCobung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Main2CoBungBicycle.this, Main2CoBung.class);
                startActivity(intent);
            }
        });


        //Gắn id cho textViewDate - lịch
        tvDate= findViewById(R.id.tvDate);
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c= Calendar.getInstance();
                int year= c.get(Calendar.YEAR);
                int month= c.get(Calendar.MONTH);
                int day= c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog= new DatePickerDialog(
                        Main2CoBungBicycle.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListenner,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListenner= new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int day, int month) {
                month= month+1;
                Log.d("tag", "onDateSet: dd/mm/yyy:" +day+"/"+ month+"/"+year);
                String date= day+"/" +month+"/"+ year;
                tvDate.setText(date);
            }
        };

        //------------------------------Chức năng Timer--------------------------------//
        //Gắn id chức năng Timer
        btnReset=findViewById(R.id.btnReset);
        btnMusic=findViewById(R.id.btnMusic);
        btnStartPause= findViewById(R.id.btnStartPause);
        tvTimer=findViewById(R.id.tvTimer);

        btnStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimeRunning){
                    pauseTime();
                }else {
                    startTime();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTime();
            }
        });
        updateCountDownText();


        //-----------------------------Slider-------------------------
        viewPager = findViewById(R.id.view_pager);
        dotsLayout =findViewById(R.id.layoutDots);

        // layouts of all welcome sliders
        // add few more layouts if you want
        //Bố trí các thanh trượt và thêm thanh trượt muốn
        layouts = new int[]{
                R.layout.activity_main2_co_bung_bicycle_slider1,
                R.layout.activity_main2_co_bung_bicycle_slider2,
                R.layout.activity_main2_co_bung_bicycle_slider3};

        // adding bottom dots
        //Thêm dấu chấm dưới
        addBottomDots(0);

        // making notification bar transparent
        //Thanh thông báo minh bạch
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
    }


    //--------------------------Các Hàm Slider-------------------------------
    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(Main2CoBungBicycle.this,Main2CoBungBicycle.class));
        finish();
    }

    //ViewPager thay đổi người nghe
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter(){

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }




    //-------------------------Timer------------------
    private void startTime(){
        mCountDownTimer= new CountDownTimer(mTimeLeftOnMillis, 1000) {
            @Override
            public void onTick(long l) {
                mTimeLeftOnMillis=l;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimeRunning=false;
                btnStartPause.setText("Start");
            }
        }.start();
        mTimeRunning=true;
        btnStartPause.setText("Pause");
    }

    private void pauseTime(){
        mCountDownTimer.cancel();
        mTimeRunning=false;
        btnStartPause.setText("Start");
    }

    private void resetTime(){
        mTimeLeftOnMillis=START_TIME_IN_MILLIS;
        updateCountDownText();
    }
    private void updateCountDownText(){
        int minutes= (int) (mTimeLeftOnMillis/1000)/60;
        int seconds= (int) (mTimeLeftOnMillis/1000)%60;

        String timeOnLeftFormated= String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        tvTimer.setText(timeOnLeftFormated);
    }


    //Music chaỵ ngầm
    public void play(View view){
        if(player==null){
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlayer();
                }
            });
        }
        player.start();

    }
    public void stop(View view){
        stopPlayer();
    }

    private void stopPlayer(){
        if(player!=null){
            player.release();
            player=null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}
