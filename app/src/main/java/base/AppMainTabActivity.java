package base;



import java.util.HashMap;
import java.util.Stack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.app.hanumanji.R;

import tabJaap.Jaap;

import tabMala.Mala;
import tabMore.More;
import tabPrayers.PrayersFragment;
import tabReminders.AddReminder;



public class AppMainTabActivity extends FragmentActivity {


    SharedPreferences sharedPrefrences;
    SharedPreferences.Editor editor;
    /* Your Tab host */
    private TabHost mTabHost;

    /* A HashMap of stacks, where we use tab identifier as keys..*/
    private HashMap<String, Stack<Fragment>> mStacks;

    /*Save current tabs identifier in this..*/
    private String mCurrentTab;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.app_main_tab_fragment_layout);

        /*  
         *  Navigation stacks for each tab gets created.. 
         *  tab identifier is used as key to get respective stack for each tab
         */
        sharedPrefrences = getSharedPreferences("AartiTut", Context.MODE_PRIVATE);
        editor = sharedPrefrences.edit();
        editor.putBoolean("AartiFlagTut", false);
        editor.commit();
        mStacks  =  new HashMap<String, Stack<Fragment>>();
        mStacks.put(AppConstants.TAB_PRAYERS, new Stack<Fragment>());
        mStacks.put(AppConstants.TAB_MALA, new Stack<Fragment>());
        mStacks.put(AppConstants.TAB_REMINDER, new Stack<Fragment>());
        mStacks.put(AppConstants.TAB_JAAP, new Stack<Fragment>());
        mStacks.put(AppConstants.TAB_MORE, new Stack<Fragment>());


        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setOnTabChangedListener(listener);
        mTabHost.setup();

        initializeTabs();
    }


    private View createTabView(final int id,String tabText) {

        View view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        ImageView imageView =   (ImageView) view.findViewById(R.id.img_tabtxt);
        TextView textViewTab = (TextView)view.findViewById(R.id.textViewTab);
        XmlResourceParser xrp = getResources().getXml(R.color.tab_text_indicator);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(), xrp);
            textViewTab.setTextColor(csl);
        } catch (Exception e) {  }

      //  textViewTab.setBackgroundResource(R.color.tab_text_indicator);

        textViewTab.setText(tabText);


        imageView.setImageDrawable(getResources().getDrawable(id));
        return view;
    }

    public void initializeTabs(){
        /* Setup your tab icons and content views.. Nothing special in this..*/
        TabHost.TabSpec spec    =   mTabHost.newTabSpec(AppConstants.TAB_PRAYERS);
        mTabHost.setCurrentTab(1);

        spec.setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {
                return findViewById(R.id.realtabcontent);
            }
        });
        spec.setIndicator(createTabView(R.drawable.tab_prayer,"Prayers"));
        mTabHost.addTab(spec);


        spec = mTabHost.newTabSpec(AppConstants.TAB_MALA);
        spec.setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {
                return findViewById(R.id.realtabcontent);
            }
        });
        spec.setIndicator(createTabView(R.drawable.tab_mala,"Mala"));
        mTabHost.addTab(spec);

        spec = mTabHost.newTabSpec(AppConstants.TAB_REMINDER);
        spec.setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {
                return findViewById(R.id.realtabcontent);
            }
        });
        spec.setIndicator(createTabView(R.drawable.tab_reminders,"Reminders"));
        mTabHost.addTab(spec);


        spec = mTabHost.newTabSpec(AppConstants.TAB_JAAP);
        spec.setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {
                return findViewById(R.id.realtabcontent);
            }
        });
        spec.setIndicator(createTabView(R.drawable.tab_jaap,"Jaap"));
        mTabHost.addTab(spec);


        spec = mTabHost.newTabSpec(AppConstants.TAB_MORE);
        spec.setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String tag) {
                return findViewById(R.id.realtabcontent);
            }
        });
        spec.setIndicator(createTabView(R.drawable.tab_more,"More"));
        mTabHost.addTab(spec);


    }


    /*Comes here when user switch tab, or we do programmatically*/
    TabHost.OnTabChangeListener listener =   new TabHost.OnTabChangeListener() {
      public void onTabChanged(String tabId) {
        /*Set current tab..*/
        mCurrentTab  = tabId;

        if(mStacks.get(tabId).size() == 0){
          /*
           *    First time this tab is selected. So add first fragment of that tab.
           *    Dont need animation, so that argument is false.
           *    We are adding a new fragment which is not present in stack. So add to stack is true.
           */
          if(tabId.equals(AppConstants.TAB_PRAYERS)){
            pushFragments(tabId, new PrayersFragment(), false,true);
          }else if(tabId.equals(AppConstants.TAB_MALA)){
            pushFragments(tabId, new Mala(), false,true);
          }else if(tabId.equals(AppConstants.TAB_REMINDER)){
              SharedPreferences sharedPreferences = getSharedPreferences("AlarmTutorial",MODE_PRIVATE);
              SharedPreferences.Editor editor = sharedPreferences.edit();
              editor.putBoolean("Flag",true);
              editor.commit();
              pushFragments(tabId, new AddReminder(), false, true);
          }else if(tabId.equals(AppConstants.TAB_JAAP)){
              pushFragments(tabId, new Jaap(), false,true);
          }else if(tabId.equals(AppConstants.TAB_MORE)){
              pushFragments(tabId, new More(), false,true);
          }
        }else {
          /*
           *    We are switching tabs, and target tab is already has atleast one fragment. 
           *    No need of animation, no need of stack pushing. Just show the target fragment
           */
            SharedPreferences sharedPreferences = getSharedPreferences("AlarmTutorial",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("Flag",true);
            editor.commit();
          pushFragments(tabId, mStacks.get(tabId).lastElement(), false,false);
        }
      }
    };


    /* Might be useful if we want to switch tab programmatically, from inside any of the fragment.*/
    public void setCurrentTab(int val){
          mTabHost.setCurrentTab(val);
    }


    /* 
     *      To add fragment to a tab. 
     *  tag             ->  Tab identifier
     *  fragment        ->  Fragment to show, in tab identified by tag
     *  shouldAnimate   ->  should animate transaction. false when we switch tabs, or adding first fragment to a tab
     *                      true when when we are pushing more fragment into navigation stack. 
     *  shouldAdd       ->  Should add to fragment navigation stack (mStacks.get(tag)). false when we are switching tabs (except for the first time)
     *                      true in all other cases.
     */
    public void pushFragments(String tag, Fragment fragment,boolean shouldAnimate, boolean shouldAdd){
      if(shouldAdd)

      mStacks.get(tag).push(fragment);
      FragmentManager   manager  = getSupportFragmentManager();
      FragmentTransaction ft =  manager.beginTransaction();
      if(shouldAnimate)
      ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
      ft.replace(R.id.realtabcontent, fragment);
      ft.commit();

    }

    public void pushFragmentsClickedReminders(String tag, Fragment fragment,boolean shouldAnimate, boolean shouldAdd,String title,String parentId){
        if(shouldAdd)

        mStacks.get(tag).push(fragment);
        Bundle bundle = new Bundle();
        bundle.putString("Title",title);
        bundle.putString("ParentId",parentId);
        fragment.setArguments(bundle);
        FragmentManager   manager  = getSupportFragmentManager();
        FragmentTransaction ft =  manager.beginTransaction();
        if(shouldAnimate)
        ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        ft.replace(R.id.realtabcontent, fragment);

        ft.commit();

    }


    public void popFragments(){
      /*    
       *    Select the second last fragment in current tab's stack.. 
       *    which will be shown after the fragment transaction given below 
       */
      Fragment fragment =  mStacks.get(mCurrentTab).elementAt(mStacks.get(mCurrentTab).size() - 2);

      /*pop current fragment from stack.. */
      mStacks.get(mCurrentTab).pop();

      /* We have the target fragment in hand.. Just show it.. Show a standard navigation animation*/
      FragmentManager manager =   getSupportFragmentManager();
      FragmentTransaction ft  =   manager.beginTransaction();
      ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
      ft.replace(R.id.realtabcontent, fragment);
      ft.commit();
    }   


    @Override
    public void onBackPressed() {
       	if(((BaseFragment)mStacks.get(mCurrentTab).lastElement()).onBackPressed() == false){
       		/*
       		 * top fragment in current tab doesn't handles back press, we can do our thing, which is
       		 * 
       		 * if current tab has only one fragment in stack, ie first fragment is showing for this tab.
       		 *        finish the activity
       		 * else
       		 *        pop to previous fragment in stack for the same tab
       		 * 
       		 */
       		if(mStacks.get(mCurrentTab).size() == 1){
       			super.onBackPressed();  // or call finish..
       		}else{
       			popFragments();
       		}
       	}else{
       		//do nothing.. fragment already handled back button press.
       	}
    }


    /*
     *   Imagine if you wanted to get an image selected using ImagePicker intent to the fragment. Ofcourse I could have created a public function
     *  in that fragment, and called it from the activity. But couldn't resist myself.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(mStacks.get(mCurrentTab).size() == 0){
            return;
        }

        /*Now current fragment on screen gets onActivityResult callback..*/
        mStacks.get(mCurrentTab).lastElement().onActivityResult(requestCode, resultCode, data);
    }
}
