package com.example.sinukov.acatalog.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sinukov.acatalog.R;
import com.example.sinukov.acatalog.fragment.MainFragment;
import com.example.sinukov.acatalog.helper.PreferenceHelper;

public class MainActivity extends BaseFragmentActivity {

    private PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_fragmen_container);

        PreferenceHelper.getInstance().init(getApplicationContext());
        preferenceHelper = PreferenceHelper.getInstance();

        if (savedInstanceState == null) {
            if (currentFragment == null)
                startFragment(new MainFragment(), false);
            else
                startFragment(currentFragment, false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem splashItem = menu.findItem(R.id.action_splash);
        splashItem.setChecked(preferenceHelper.getBoolean(PreferenceHelper.SPLASH_IS_INVISIBLE));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_splash) {
            item.setChecked(!item.isChecked());
            preferenceHelper.putBoolean(PreferenceHelper.SPLASH_IS_INVISIBLE, item.isChecked());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}