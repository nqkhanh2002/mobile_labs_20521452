package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnFadeInXml, btnFadeInCode, btnFadeOutXml, btnFadeOutCode, btnBlinkXml,
            btnBlinkCode, btnZoomInXml, btnZoomInCode, btnZoomOutXml, btnZoomOutCode, btnRotateXml,
            btnRotateCode, btnMoveXml, btnMoveCode, btnSlideUpXml, btnSlideUpCode, btnBounceXml,
            btnBounceCode, btnCombineXml, btnCombineCode;
    private ImageView ivUitLogo;
    private Animation.AnimationListener animationListener;


    private void findViewsByIds() {
        ivUitLogo = (ImageView) findViewById(R.id.iv_uit_logo);
        btnFadeInXml = (Button) findViewById(R.id.btn_fade_in_xml);
        btnFadeInCode = (Button) findViewById(R.id.btn_fade_in_code);
        btnFadeOutXml = (Button) findViewById(R.id.btn_fade_out_xml);
        btnFadeOutCode = (Button) findViewById(R.id.btn_fade_out_code);
        btnBlinkXml = (Button) findViewById(R.id.btn_blink_xml);
        btnBlinkCode = (Button) findViewById(R.id.btn_blink_code);
        btnZoomInXml = (Button) findViewById(R.id.btn_zoom_in_xml);
        btnZoomInCode = (Button) findViewById(R.id.btn_zoom_in_code);
        btnZoomOutXml = (Button) findViewById(R.id.btn_zoom_out_xml);
        btnZoomOutCode = (Button) findViewById(R.id.btn_zoom_out_code);
        btnRotateXml = (Button) findViewById(R.id.btn_rotate_xml);
        btnRotateCode = (Button) findViewById(R.id.btn_rotate_code);
        btnMoveXml = (Button) findViewById(R.id.btn_move_xml);
        btnMoveCode = (Button) findViewById(R.id.btn_move_code);
        btnSlideUpXml = (Button) findViewById(R.id.btn_slide_up_xml);
        btnSlideUpCode = (Button) findViewById(R.id.btn_slide_up_code);
        btnBounceXml = (Button) findViewById(R.id.btn_bounce_xml);
        btnBounceCode = (Button) findViewById(R.id.btn_bounce_code);
        btnCombineXml = (Button) findViewById(R.id.btn_combine_xml);
        btnCombineCode = (Button) findViewById(R.id.btn_combine_code);
    }
    //

    private void initVariables() {
        animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getApplicationContext(), "Animation Stopped",
                        Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        };

    }
    private void handleClickAnimationXml(Button btn, int animID)
    {
        // load the animation
        final Animation anim = AnimationUtils.loadAnimation(MainActivity.this, animID);
        // set animation listener
        anim.setAnimationListener(animationListener);
        // handle onclickListener to start the animation
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(anim);
            }
        });
    }

    private void handleClickAnimationCode(Button btn, final Animation animation) {

        // Handle onclickListenner to start animation
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(animation);
            }
        });

    }
    private Animation initFadeInAnimation() {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(3000);
        anim.setFillAfter(true);
        anim.setAnimationListener(animationListener);
        return anim;
    }
    // init code for fade out
    private Animation initFadeOutAnimation() {
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(3000);
        anim.setFillAfter(true);
        anim.setAnimationListener(animationListener);
        return anim;
    }
    // init code for blink animation
    private Animation initBlinkAnimation() {
        Animation blinkAnimation = new AlphaAnimation(1.0f, 0.0f);
        blinkAnimation.setDuration(500); // Thời gian 1 lần nhấp nháy (500ms)
        blinkAnimation.setRepeatMode(Animation.REVERSE); // Chế độ lặp lại đảo ngược (nhấp nháy lần thứ 2 sẽ ẩn View)
        blinkAnimation.setRepeatCount(Animation.INFINITE); // Số lần lặp vô hạn

        return blinkAnimation;
    }
    // init code for zoom in animation
    private Animation initZoomInAnimation() {
        Animation zoomInAnimation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        zoomInAnimation.setDuration(3000);
        zoomInAnimation.setFillAfter(true);
        zoomInAnimation.setAnimationListener(animationListener);
        return zoomInAnimation;
    }
    // init code for zoom out animation
    private Animation initZoomOutAnimation() {
        Animation zoomOutAnimation = new ScaleAnimation(2.0f, 1.0f, 2.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        zoomOutAnimation.setDuration(3000);
        zoomOutAnimation.setFillAfter(true);
        zoomOutAnimation.setAnimationListener(animationListener);
        return zoomOutAnimation;
    }
    // init code for rotate animation
    private Animation initRotateAnimation() {
        Animation rotateAnimation = new RotateAnimation(0.0f, 360.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rotateAnimation.setDuration(3000);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setAnimationListener(animationListener);
        return rotateAnimation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewsByIds();
        initVariables();

        // Handle Click Animations XML
        handleClickAnimationXml(btnFadeInXml, R.anim.anim_fade_in);
        handleClickAnimationXml(btnFadeOutXml, R.anim.anim_fade_out);
        handleClickAnimationXml(btnBlinkXml, R.anim.anim_blink);
        handleClickAnimationXml(btnZoomInXml, R.anim.anim_zoom_in);
        handleClickAnimationXml(btnZoomOutXml, R.anim.anim_zoom_out);
        handleClickAnimationXml(btnRotateXml, R.anim.anim_rotate);
        handleClickAnimationXml(btnMoveXml, R.anim.anim_move);
        handleClickAnimationXml(btnSlideUpXml, R.anim.anim_slide_up);
        handleClickAnimationXml(btnBounceXml, R.anim.anim_bounce);
        handleClickAnimationXml(btnCombineXml, R.anim.anim_combine);

        // Handle Click Animations Code
        handleClickAnimationCode(btnFadeInCode, initFadeInAnimation());
        handleClickAnimationCode(btnFadeOutCode, initFadeOutAnimation());
        handleClickAnimationCode(btnBlinkCode, initBlinkAnimation());
        handleClickAnimationCode(btnZoomInCode, initZoomInAnimation());
        handleClickAnimationCode(btnZoomOutCode, initZoomOutAnimation());
        handleClickAnimationCode(btnRotateCode, initRotateAnimation());
    }

}