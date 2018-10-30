package br.com.bitocean.seeckbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private SeekBar seekBarA, seekBarB;
    private TextView tvA, tvB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    private void initComponents() {
        seekBarA = (SeekBar)findViewById(R.id.seekBarA);
        seekBarB = (SeekBar)findViewById(R.id.seekBarB);

        seekBarB.setOnSeekBarChangeListener(this);
        seekBarA.setOnSeekBarChangeListener(this);

        tvA = (TextView)findViewById(R.id.tvA);
        tvB = (TextView)findViewById(R.id.tvB);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.seekBarA:
                tvA.setText(String.valueOf(progress));
                break;
            case R.id.seekBarB:
                tvB.setText(String.valueOf(progress));
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()){
            case R.id.seekBarA:
                break;
            case R.id.seekBarB:
                break;
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()){
            case R.id.seekBarA:
                break;
            case R.id.seekBarB:
                break;
        }
    }
}
