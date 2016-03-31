package tabPrayers;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.app.hanumanji.R;

import java.util.List;

import base.AppMainTabActivity;
import base.BaseFragment;


public class ShareYourLove extends BaseFragment {


	View view;
	private ImageView imageViewBack;
	private ImageView textViewShareOngmail;
	private ImageView textViewShareOnFaceBook;
	private ImageView textViewShareOnTwitter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

        view   =   inflater.inflate(R.layout.share_your_love, container, false);
		initialiseViews();
		initialiseListeners();


        return view;
	}

	private void initialiseListeners() {
		imageViewBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				((AppMainTabActivity)getActivity()).onBackPressed();
			}
		});

		textViewShareOngmail.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "hi@awesomeactually.com"));
				intent.putExtra(Intent.EXTRA_SUBJECT, "Hello Hanuman - FeedBack");
				intent.putExtra(Intent.EXTRA_TEXT, "Sent from Android");
				startActivity(intent);
			}
		});


		textViewShareOnFaceBook.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				String urlToShare = "www.jayhanuman.com";
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT, urlToShare);
			// See if official Facebook app is found
				boolean facebookAppFound = false;
				List<ResolveInfo> matches = getActivity().getPackageManager().queryIntentActivities(intent, 0);
				for (ResolveInfo info : matches) {
					if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook")) {
						intent.setPackage(info.activityInfo.packageName);
						facebookAppFound = true;
						break;  }}
				// As fallback, launch sharer.php in a browser
				if (!facebookAppFound) {
					String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
					intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
				}
				startActivity(intent);
			}
		});

		textViewShareOnTwitter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				boolean twitterAppFound = false;

				Intent intent = new Intent(Intent.ACTION_SEND);
				String urlToShare = "www.jayhanuman.com";
                // Narrow down to official Twitter app, if available:
				List<ResolveInfo> matches = getActivity().getPackageManager().queryIntentActivities(intent, 0);
				for (ResolveInfo info : matches) {
					if (info.activityInfo.packageName.toLowerCase().startsWith("com.twitter")) {
						intent.setPackage(info.activityInfo.packageName);
						twitterAppFound = true;
						break;
					}
				}

				if(!twitterAppFound){
					String tweetUrl =
							String.format("https://twitter.com/intent/tweet?text=JaiHanuman&url="+urlToShare,
									"","");
					intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl));
				}else{
					intent = new Intent(Intent.ACTION_SEND);
					intent.putExtra(Intent.EXTRA_TEXT, "Tweet;Jai Hanuman");
					intent.setType("application/twitter");
				}

				startActivity(intent);

		}
		});

	}

	private void initialiseViews() {
		imageViewBack = (ImageView)view.findViewById(R.id.imageViewBack);
		textViewShareOngmail = (ImageView)view.findViewById(R.id.textViewShareOngmail);
		textViewShareOnFaceBook = (ImageView)view.findViewById(R.id.textViewshareOnFaceBook);
		textViewShareOnTwitter = (ImageView)view.findViewById(R.id.textViewShareOnTwitter);
	}



}
