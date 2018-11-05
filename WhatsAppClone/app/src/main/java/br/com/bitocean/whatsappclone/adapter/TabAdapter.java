package br.com.bitocean.whatsappclone.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.com.bitocean.whatsappclone.fragments.ContatoFragment;
import br.com.bitocean.whatsappclone.fragments.ConversaFragment;

public class TabAdapter extends FragmentStatePagerAdapter {
    private String titles[] ={"CONVERSAS","CONTATOS"};

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position){
            case 0:
                frag = new ConversaFragment();
                break;
            case 1:
                frag = new ContatoFragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
