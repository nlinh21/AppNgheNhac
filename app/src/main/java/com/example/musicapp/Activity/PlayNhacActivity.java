package com.example.musicapp.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.musicapp.Adapter.ViewPagerPlaylist;
import com.example.musicapp.Fragment.Fragment_DiaNhac;
import com.example.musicapp.Fragment.Fragment_Play_DS_BaiHat;
import com.example.musicapp.Model.Baihat;
import com.example.musicapp.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {

    Toolbar toolbarplaynhac;
    TextView txtTimesong, txtTotalTimesong;
    SeekBar sktime;
    ImageButton imgplay,imgrepeat, imgnext, imgprevious, imgrandom;
    ViewPager viewPagerplaynhac;
    public static ArrayList<Baihat> mangbaihat = new ArrayList<>();
    public static ViewPagerPlaylist adapternhac;
    Fragment_DiaNhac fragment_diaNhac;
    Fragment_Play_DS_BaiHat fragment_play_ds_baiHat;
    MediaPlayer mediaPlayer;
    int postion = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        init();
        eventClick();

    }

    private void eventClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapternhac.getItem(1) != null) {
                    if (mangbaihat.size() > 0) {
                        fragment_diaNhac.PlayNhac(mangbaihat.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                    }
                    else {
                        handler.postDelayed(this, 300);

                    }
                }
            }
        }, 500);
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.play_button_arrowhead);
                } else {
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.pause);
                }
            }
        });
        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeat == false) {
                    if (checkrandom == true) {
                        checkrandom = false;
                        imgrepeat.setImageResource(R.drawable.repeatcolor);
                        imgrandom.setImageResource(R.drawable.random);
                    }
                    imgrepeat.setImageResource(R.drawable.repeatcolor);
                    repeat = false;
                } else {
                    imgrepeat.setImageResource(R.drawable.repeat);
                    repeat = false;
                }
            }
        });
        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkrandom == false) {
                    if (repeat == true) {
                        repeat = false;
                        imgrandom.setImageResource(R.drawable.shuffleed);
                        imgrepeat.setImageResource(R.drawable.repeat);
                    }
                    imgrandom.setImageResource(R.drawable.shuffleed);
                    checkrandom = true;
                } else {
                    imgrandom.setImageResource(R.drawable.shuffle);
                    checkrandom = false;
                }
            }
        });
        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mangbaihat.size() > 0) {
                    if(mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer= null;
                    }
                    if(postion < (mangbaihat.size())) {
                        imgplay.setImageResource(R.drawable.pause);
                        postion++;
                        if (repeat == true) {
                            if(postion == 0) {
                                postion =  mangbaihat.size();
                            }
                            postion -=1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == postion) {
                                postion = index - 1;
                            }
                            postion = index;
                        }
                        if (postion > (mangbaihat.size()-1)) {
                            postion = 0;
                        }
                        new PlayMp3().execute(mangbaihat.get(postion).getLinkbaihat());
                        fragment_diaNhac.PlayNhac(mangbaihat.get(postion).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(postion).getTenbaihat());
                        UpdateTime();
                    }
                }
                imgprevious.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgprevious.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);
            }
        });
        imgprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mangbaihat.size() > 0) {
                    if(mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer= null;
                    }
                    if(postion < (mangbaihat.size())) {
                        imgplay.setImageResource(R.drawable.pause);
                        postion--;

                        if (postion < 0){
                            postion = mangbaihat.size() -1;
                        }
                        if (repeat == true) {
                            postion += 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == postion) {
                                postion = index - 1;
                            }
                            postion = index;
                        }
//                        if (postion > (mangbaihat.size()-1)) {
//                            postion = 0;
//                        }
                        new PlayMp3().execute(mangbaihat.get(postion).getLinkbaihat());
                        fragment_diaNhac.PlayNhac(mangbaihat.get(postion).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(postion).getTenbaihat());
                        UpdateTime();
                    }
                }
                imgprevious.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgprevious.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);
            }
        });
    }

    private void UpdateTime() {
        final Handler handler =  new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(next == true) {
                    if(postion < (mangbaihat.size())) {
                        imgplay.setImageResource(R.drawable.pause);
                        postion++;
                        if (repeat == true) {
                            if(postion == 0) {
                                postion =  mangbaihat.size();
                            }
                            postion -=1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == postion) {
                                postion = index - 1;
                            }
                            postion = index;
                        }
                        if (postion > (mangbaihat.size()-1)) {
                            postion = 0;
                        }
                        new PlayMp3().execute(mangbaihat.get(postion).getLinkbaihat());
                        fragment_diaNhac.PlayNhac(mangbaihat.get(postion).getHinhbaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(postion).getTenbaihat());
                    }

                    imgprevious.setClickable(false);
                    imgnext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgprevious.setClickable(true);
                            imgnext.setClickable(true);
                        }
                    }, 5000);
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 100);
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent != null) {
            if (intent.hasExtra("ca khuc")) {
                Baihat baihat = intent.getParcelableExtra("ca khuc");
                mangbaihat.add(baihat);

            }
            if (intent.hasExtra("cac bai hat")) {
                ArrayList<Baihat> baihatArrayList = intent.getParcelableArrayListExtra("cac bai hat");
                mangbaihat = baihatArrayList;
            }
        }
    }

    private void init() {
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        txtTimesong = findViewById(R.id.textviewtimesong);
        txtTotalTimesong = findViewById(R.id.textviewtotaltimesong);
        sktime = findViewById(R.id.seekbarsong);
        imgplay = findViewById(R.id.imagebuttonplay);
        imgrepeat = findViewById(R.id.imagebuttonrepeat);
        imgnext = findViewById(R.id.imagebuttonnext);
        imgprevious = findViewById(R.id.imagebuttonprevious);
        imgrandom = findViewById(R.id.imagebuttonshuffle);
        viewPagerplaynhac = findViewById(R.id.viewpagerplaynhac);
        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                mediaPlayer.stop();
                mangbaihat.clear();
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.WHITE);
        fragment_diaNhac = new Fragment_DiaNhac();
        fragment_play_ds_baiHat = new Fragment_Play_DS_BaiHat();
        adapternhac = new ViewPagerPlaylist(getSupportFragmentManager());
        adapternhac.AddFragment(fragment_diaNhac);
        adapternhac.AddFragment(fragment_play_ds_baiHat);
        viewPagerplaynhac.setAdapter(adapternhac);
        fragment_diaNhac = (Fragment_DiaNhac) adapternhac.getItem(0);
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenbaihat());
            new PlayMp3().execute(mangbaihat.get(0).getLinkbaihat());
            imgplay.setImageResource(R.drawable.pause);
    }
    class PlayMp3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
            mediaPlayer =  new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e ) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }

        private void TimeSong() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
            txtTotalTimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
            sktime.setMax(mediaPlayer.getDuration());
        }

    }
}