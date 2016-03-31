package com.example.shiva.hanumanji;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.app.hanumanji.R;

import tabPrayers.Help;

public class VerticalPagingActivity extends ActionBarActivity {

    private SpinnerAdapter mSpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vertical_page_main);

        mSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.fragment_list,
                android.R.layout.simple_spinner_dropdown_item);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getSupportActionBar().setListNavigationCallbacks(mSpinnerAdapter,
                new ActionBar.OnNavigationListener() {

                    String[] strings = getResources().getStringArray(R.array.fragment_list);

                    @Override
                    public boolean onNavigationItemSelected(int i, long l) {
                        Fragment f = null;
                        switch (strings[i]) {
                            case "Horizontal":
                                f = new HorizontalPagingFragment();
                                break;
                            case "Vertical":
                            default:
                                f = new Help();
                                break;
                        }

                        FragmentTransaction ft = getSupportFragmentManager()
                                .beginTransaction();

                        ft.replace(R.id.container, f, strings[i]);
                        ft.commit();
                        return true;
                    }
                }
        );

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new Help())
                    .commit();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
