package br.com.bitocean.somdosbichos;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivCao,ivGato,ivLeao,ivMacaco,ivOvelha,ivVaca;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

    }

    private void initComponents() {
        ivCao = (ImageView)findViewById(R.id.ivCao);
        ivGato = (ImageView)findViewById(R.id.ivGato);
        ivLeao = (ImageView) findViewById(R.id.ivLeao);
        ivMacaco = (ImageView) findViewById(R.id.ivMacaco);
        ivVaca = (ImageView) findViewById(R.id.ivVaca);
        ivOvelha = (ImageView) findViewById(R.id.ivOvelha);
        ImageView ivs[] = {ivCao,ivGato,ivLeao,ivMacaco,ivVaca,ivOvelha};
        for(ImageView iv:ivs){
            iv.setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivCao:
                player = MediaPlayer.create(this,R.raw.cao);
                break;
            case R.id.ivGato:
                player = MediaPlayer.create(this,R.raw.gato);
                break;
            case R.id.ivLeao:
                player = MediaPlayer.create(this,R.raw.leao);
                break;
            case R.id.ivMacaco:
                player = MediaPlayer.create(this,R.raw.macaco);
                break;
            case R.id.ivOvelha:
                player = MediaPlayer.create(this,R.raw.ovelha);
                break;
            case R.id.ivVaca:
                player = MediaPlayer.create(this,R.raw.vaca);
                break;
        }

        tocarSom();

    }

    public void tocarSom(){
        if(player!=null){
            player.start();
        }
    }

    @Override
    protected void onDestroy() {
        if(player!=null){
            player.stop();
            player.release();
            player = null;
        }
        super.onDestroy();
    }
}
