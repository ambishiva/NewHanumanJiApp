package tabPrayers;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.app.hanumanji.R;

import java.util.ArrayList;
import java.util.List;

import base.BaseFragment;

public class MainAarti extends BaseFragment {

	MyPageAdapter pageAdapter;
    View view;
    SharedPreferences sharedPrefrences;
    boolean aartiTutFlag;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view   =   inflater.inflate(R.layout.activity_page_view, container, false);


        sharedPrefrences = getActivity().getSharedPreferences("AartiTut", Context.MODE_PRIVATE);
        editor = sharedPrefrences.edit();
        aartiTutFlag = sharedPrefrences.getBoolean("AartiFlagTut",false);
//        if(!aartiTutFlag){
//
//            showOverLay();
//        }

        List<Fragment> fragments = getFragments();

        pageAdapter = new MyPageAdapter(getActivity().getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager)view.findViewById(R.id.viewpager);
        pager.setAdapter(pageAdapter);
        pager.invalidate();

        return view;
    }

    private void showOverLay() {

        final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);

        dialog.setContentView(R.layout.aarti_tut);

        RelativeLayout layout = (RelativeLayout) dialog.findViewById(R.id.transparent);

        layout.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View arg0) {

                dialog.dismiss();
                editor.putBoolean("AartiFlagTut", true);
                editor.commit();


            }

        });

        dialog.show();
    }


    private List<Fragment> getFragments(){


    	List<Fragment> fList = new ArrayList<Fragment>();
    	fList.add(AartiEnglish.newInstance("Fragment 1"));
    	//fList.add(AartiHindi.newInstance("Fragment 2"));
    	return fList;

    }

    private class MyPageAdapter extends FragmentStatePagerAdapter {


    	private List<Fragment> fragments;
        public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }
        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }
     
        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }
}
