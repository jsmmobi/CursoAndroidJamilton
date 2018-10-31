package br.com.bitocean.mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private ToggleButton playPause;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playPause = (ToggleButton)findViewById(R.id.playPause);
        playPause.setTextOff("Pausado");
        playPause.setTextOn("Tocando...");
        playPause.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked && !mediaPlayer.isPlaying()){
                    tocarMusica();
                }else{
                    stop();
                }
            }
        });

        mediaPlayer = MediaPlayer.create(this,R.raw.musica);

    }

    private void tocarMusica(){
        if(mediaPlayer!=null){
            mediaPlayer.start();
        }
    }

    private void stop(){
        if(mediaPlayer!=null && mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        if(mediaPlayer!=null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();

            mediaPlayer = null;
        }
        super.onDestroy();
    }
}
