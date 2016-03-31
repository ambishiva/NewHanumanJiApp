package tabJaap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.app.hanumanji.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import adapter.RamCountAdapter;
import base.AppConstants;
import base.BaseFragment;
import tabMala.BeadActivity;


public class Jaap extends BaseFragment implements OnClickListener {

    private Button mGotoButton;
    View view;
    private PaintView mPaintView;
    private LinearLayout mLlCanvas;
    private ImageView mResetDrawerImg;
    private ImageView mUploadImage;
    private int count=0;

    ArrayList<Bitmap> mScreenImages = new ArrayList<>();
    private TextView mMyCounterTextView;
    private GridView mGridView;

    private RamCountAdapter ramCountAdapter;
    private ImageView imageViewInformation;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
         view  = inflater.inflate(R.layout.jaap, container, false);
        mLlCanvas = (LinearLayout) view.findViewById(R.id.llCanvas);
        imageViewInformation = (ImageView)view.findViewById(R.id.imageViewInformation);
        mResetDrawerImg = (ImageView) view.findViewById(R.id.imageViewBack);
        mResetDrawerImg.setOnClickListener(this);

        mUploadImage = (ImageView) view.findViewById(R.id.uploadImg);
        mUploadImage.setOnClickListener(this);

        mMyCounterTextView = (TextView) view.findViewById(R.id.textViewMyCountNumber);

        mGridView = (GridView) view.findViewById(R.id.imagesGrid);
        ramCountAdapter = new RamCountAdapter(getActivity(),R.layout.ram_item,mScreenImages);
        mGridView.setAdapter(ramCountAdapter);
        imageViewInformation.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showInformationDialog();

            }
        });


        // mGotoButton =   (Button) view.findViewById(R.id.id_next_tab_a_button);
        //mGotoButton.setOnClickListener(listener);

        return view;
    }
    private void showInformationDialog() {

//        final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
//
//        dialog.setContentView(R.layout.bead_info);
//
//        ImageView imageViewDismiss = (ImageView) dialog.findViewById(R.id.imageViewDismiss);
//
//        imageViewDismiss.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//
//            public void onClick(View arg0) {
//
//                dialog.dismiss();
//
//            }
//
//        });
//
//        dialog.show();


        Intent beadActivity = new Intent(getActivity(), JaapActivity.class);
        startActivity(beadActivity);
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPaintView = new PaintView(getActivity(), null);
        mPaintView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mLlCanvas.addView(mPaintView, 0);
        mPaintView.requestFocus();
    }

    private void resetDraw() {
      //  mScreenImages.clear();
        mPaintView = new PaintView(getActivity(), null);
        mPaintView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mLlCanvas.addView(mPaintView, 0);
        mPaintView.requestFocus();
//        ramCountAdapter = new RamCountAdapter(getActivity(),R.layout.ram_item,mScreenImages);
//        mGridView.setAdapter(ramCountAdapter);
    }

    private void previewDraw() {
        if (mPaintView.arl.size() == 0) {
//            Toast.makeText(getActivity(), "Draw preview fail", Toast.LENGTH_LONG).show();
            return;
        }

        View view = (View) mLlCanvas.getChildAt(0);
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(
                bitmap, 200, 180, false);
        mScreenImages.add(resizedBitmap);
        mMyCounterTextView.setText(""+mScreenImages.size());
        resetDraw();
//        mPreviewImageView.setImageBitmap(resizedBitmap);
        view.setDrawingCacheEnabled(false);
       // ramCountAdapter.notifyDataSetChanged();
        ramCountAdapter = new RamCountAdapter(getActivity(),R.layout.ram_item,mScreenImages);
        mGridView.setAdapter(ramCountAdapter);

//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        byte[] byteArray = stream.toByteArray();
//        String base64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
//        Log.d("Debug", "Base64 string  : " + base64);
    }



    private OnClickListener listener =  new OnClickListener(){
        @Override
        public void onClick(View v){
            /* Go to next fragment in navigation stack*/
            mActivity.pushFragments(AppConstants.TAB_JAAP, new AppTabDSecondFragment(),true,true);
        }
    };

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imageViewBack:
                        resetDraw();
                mScreenImages.clear();
                mMyCounterTextView.setText(""+mScreenImages.size());
                break;

            case R.id.uploadImg:
                previewDraw();
                break;
        }
    }
}
