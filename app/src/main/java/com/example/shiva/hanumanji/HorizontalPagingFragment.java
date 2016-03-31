package com.example.shiva.hanumanji;



import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.hanumanji.R;

import java.util.ArrayList;
import java.util.List;

import viewpager.PagerAdapter;
import viewpager.ViewPager;

/**
 * Created by rharter on 11/11/13.
 */
public class HorizontalPagingFragment extends Fragment {

    ViewPager mPager;
    HorizontalPagerAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_horizontal, container, false);

        mPager = (ViewPager) v.findViewById(R.id.pager);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new HorizontalPagerAdapter(getActivity());
        mPager.setAdapter(mAdapter);
    }

    public static class HorizontalPagerAdapter extends PagerAdapter {

        static final List<PageInfo> PAGES = new ArrayList<PageInfo>();
        static {
            PAGES.add(new PageInfo(Color.BLUE, "Nothing Unusual"));
            PAGES.add(new PageInfo(Color.RED, "Use a normal PagerAdapter, but subclass mine"));
            PAGES.add(new PageInfo(Color.GREEN, "Nothin Unusual"));
            PAGES.add(new PageInfo(Color.MAGENTA, "Nothin Unusual"));
        }

        Context mContext;
        LayoutInflater mLayoutInflater;

        public HorizontalPagerAdapter(Context context) {
            super();
            mContext = context;
            mLayoutInflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return PAGES.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = mLayoutInflater.inflate(R.layout.page, container, false);

            final PageInfo info = PAGES.get(position);

            View page = v.findViewById(R.id.container);
            page.setBackgroundColor(info.background);

            TextView content = (TextView) v.findViewById(R.id.text);
            content.setText(info.text);

            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        private static class PageInfo {
            int background;
            String text;

            PageInfo(int background, String text) {
                this.background = background;
                this.text = text;
            }
        }
    }

}
