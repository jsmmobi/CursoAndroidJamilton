package br.com.bitocean.intagrancloneparse.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import java.util.HashMap;

import br.com.bitocean.intagrancloneparse.R;
import br.com.bitocean.intagrancloneparse.fragment.HomeFragment;
import br.com.bitocean.intagrancloneparse.fragment.UsuarioFragment;

public class TabsAdapter extends FragmentStatePagerAdapter {
    private String[] abas = {"HOME","USUÁRIOS"};
    private int[] icones = {R.drawable.ic_action_home,R.drawable.ic_group};
    private Context context;
    private HashMap<Integer,Fragment> mapaFragment = new HashMap<>();

    private int tamanhoIcone;
    public TabsAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        double scala = this.context.getResources().getDisplayMetrics().density;
        tamanhoIcone = (int) (scala*36);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new HomeFragment();
                mapaFragment.put(position,fragment);
                break;
            case 1:
                fragment = new UsuarioFragment();
                mapaFragment.put(position,fragment);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return abas.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
       // return abas[position];
        Drawable drawable = ContextCompat.getDrawable(context,icones[position]);
        drawable.setBounds(0,0,tamanhoIcone,tamanhoIcone);
        ImageSpan span = new ImageSpan(drawable);
        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(span,0,spannableString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //spannableString.setSpan(abas[position],spannableString.length()+1,abas[position].length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        return spannableString;

        }


        public Fragment getFragment(int position){
            return mapaFragment.get(position);
        }

}
