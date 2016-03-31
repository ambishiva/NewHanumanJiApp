package tabPrayers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.app.hanumanji.R;

import base.BaseFragment;

public class AartiHindi extends BaseFragment {
	public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
	private LinearLayout linearLayoutAarti;
	private ScrollView scrollView;
	private float x1, x2;
	private TextView imageViewChangeAarti;
	private TextView textViewFirstDoha;
	private TextView textViewSecondDoha;
	private TextView textViewThirdDoha;
	private TextView textViewFourthDoha;
	private TextView textViewFifthDoha;
	private TextView textViewSixthDoha;
	private TextView textViewSeventhDoha;
	private TextView textViewEigthDoha;
	private TextView textViewNinthDoha;
	private TextView textViewTenthDoha;
	private TextView textViewEleventhDoha;
	private TextView textViewTwelvethDoha;

	
	public static final AartiHindi newInstance(String message)
	{
		AartiHindi f = new AartiHindi();
		Bundle bdl = new Bundle(1);
	    bdl.putString(EXTRA_MESSAGE, message);
	    f.setArguments(bdl);
	    return f;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.aarti, container, false);
		linearLayoutAarti = (LinearLayout)v.findViewById(R.id.linearLayoutAarti);
		scrollView = (ScrollView)v.findViewById(R.id.scrollViewDoha);
		textViewFirstDoha = (TextView)v.findViewById(R.id.textViewFirstDoha);
		textViewSecondDoha = (TextView)v.findViewById(R.id.textViewSecondDoha);
		textViewThirdDoha = (TextView)v.findViewById(R.id.textViewThirdDoha);
		textViewFourthDoha = (TextView)v.findViewById(R.id.textViewFourthDoha);
		textViewFifthDoha = (TextView)v.findViewById(R.id.textViewFifthDoha);
		textViewSixthDoha = (TextView)v.findViewById(R.id.textViewSixthDoha);
		textViewSeventhDoha = (TextView)v.findViewById(R.id.textViewSeventhDoha);
		textViewEigthDoha = (TextView)v.findViewById(R.id.textViewEigthDoha);
		textViewNinthDoha = (TextView)v.findViewById(R.id.textViewNinthDoha);
		textViewTenthDoha = (TextView)v.findViewById(R.id.textViewTenthDoha);
		textViewEleventhDoha = (TextView)v.findViewById(R.id.textViewEleventhDoha);
		textViewTwelvethDoha = (TextView)v.findViewById(R.id.textViewTwelvethDoha);

		imageViewChangeAarti = (TextView)v.findViewById(R.id.imageViewChangeAarti);
		linearLayoutAarti.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
						R.anim.slide_in_left_new, 0, 0, R.anim.slide_out_left_new
				).add(R.id.container, new AartiEnglish()).addToBackStack("animation").commit();

			}
		});
		linearLayoutAarti.setOnTouchListener(

				new View.OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub

						switch (event.getAction()) {

							case MotionEvent.ACTION_DOWN:
								x1 = event.getX();
								break;
							case MotionEvent.ACTION_UP:
								//scrollView.requestDisallowInterceptTouchEvent(true);
								x2 = event.getX();
								float deltaX = x2 - x1;
								if (deltaX < 0) {
//									Toast.makeText(getActivity(),
//											"Right to Left swipe",
//											Toast.LENGTH_SHORT).show();
//									getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
//											R.anim.slide_in_right_new, 0, 0, R.anim.slide_out_right_new
//									).add(R.id.container, new AartiHindi()).addToBackStack("animation").commit();
								} else if (deltaX > 0) {
//									Toast.makeText(getActivity(),
//											"Left to Right swipe",
//											Toast.LENGTH_SHORT).show();
//									getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(
//											R.anim.slide_in_left_new, 0, 0, R.anim.slide_out_left_new
//									).add(R.id.container, new AartiEnglish()).addToBackStack("animation").commit();
								}
								break;
						}

						return false;
					}
				});
		imageViewChangeAarti.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				textViewFirstDoha.setText(getActivity().getResources().getString(R.string.first_doha));
				textViewSecondDoha.setText(getActivity().getResources().getString(R.string.second_doha));
				textViewThirdDoha.setText(getActivity().getResources().getString(R.string.third_doha));
				textViewFourthDoha.setText(getActivity().getResources().getString(R.string.fourth_doha));
				textViewFifthDoha.setText(getActivity().getResources().getString(R.string.fifth_doha));
				textViewSixthDoha.setText(getActivity().getResources().getString(R.string.sixth_doha));
				textViewSeventhDoha.setText(getActivity().getResources().getString(R.string.seventh_doha));
				textViewEigthDoha.setText(getActivity().getResources().getString(R.string.eighth_doha));
				textViewNinthDoha.setText(getActivity().getResources().getString(R.string.ninth_doha));
				textViewTenthDoha.setText(getActivity().getResources().getString(R.string.tenth_doha));
				textViewEleventhDoha.setText(getActivity().getResources().getString(R.string.eleventh_doha));
				textViewTwelvethDoha.setText(getActivity().getResources().getString(R.string.twelth_doha));

			}
		});
        return v;
    }

	private void initialiseListeners() {


	}

}
