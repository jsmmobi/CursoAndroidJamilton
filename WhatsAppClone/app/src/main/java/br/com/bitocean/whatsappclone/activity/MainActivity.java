package br.com.bitocean.whatsappclone.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.bitocean.whatsappclone.R;
import br.com.bitocean.whatsappclone.adapter.TabAdapter;
import br.com.bitocean.whatsappclone.widget.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    private SlidingTabLayout  slidingTabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slidingTabLayout = (SlidingTabLayout)findViewById(R.id.stl_tabs);
        viewPager = (ViewPager)findViewById(R.id.view_pager);

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        slidingTabLayout.setViewPager(viewPager);
    }
}
