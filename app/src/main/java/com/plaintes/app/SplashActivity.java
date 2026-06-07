package com.plaintes.app;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DURATION_MS = 2600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.logo);
        View glow = findViewById(R.id.logo_glow);
        TextView title = findViewById(R.id.title);
        TextView subtitle = findViewById(R.id.subtitle);
        View divider = findViewById(R.id.divider);
        TextView tagline = findViewById(R.id.tagline);
        LinearLayout dots = findViewById(R.id.dots);
        View dot1 = findViewById(R.id.dot1);
        View dot2 = findViewById(R.id.dot2);
        View dot3 = findViewById(R.id.dot3);

        // Logo entrance: scale + fade
        logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.logo_intro));

        // Glow pulse
        ObjectAnimator glowPulse = ObjectAnimator.ofFloat(glow, "alpha", 0.4f, 0.95f);
        glowPulse.setDuration(1400);
        glowPulse.setRepeatCount(ValueAnimator.INFINITE);
        glowPulse.setRepeatMode(ValueAnimator.REVERSE);
        glowPulse.start();

        ObjectAnimator glowScale = ObjectAnimator.ofFloat(glow, "scaleX", 0.9f, 1.08f);
        glowScale.setDuration(1400);
        glowScale.setRepeatCount(ValueAnimator.INFINITE);
        glowScale.setRepeatMode(ValueAnimator.REVERSE);
        glowScale.start();

        ObjectAnimator glowScaleY = ObjectAnimator.ofFloat(glow, "scaleY", 0.9f, 1.08f);
        glowScaleY.setDuration(1400);
        glowScaleY.setRepeatCount(ValueAnimator.INFINITE);
        glowScaleY.setRepeatMode(ValueAnimator.REVERSE);
        glowScaleY.start();

        // Title slide-up + fade
        Animation titleAnim = AnimationUtils.loadAnimation(this, R.anim.title_intro);
        title.startAnimation(titleAnim);
        title.setAlpha(1f);

        Animation subtitleAnim = AnimationUtils.loadAnimation(this, R.anim.title_intro);
        subtitleAnim.setStartOffset(550);
        subtitle.startAnimation(subtitleAnim);
        subtitle.setAlpha(1f);

        // Divider grow
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            divider.setAlpha(1f);
            divider.setScaleX(0f);
            divider.animate().scaleX(1f).setDuration(500).start();
        }, 900);

        // Tagline
        Animation taglineAnim = AnimationUtils.loadAnimation(this, R.anim.tagline_intro);
        tagline.startAnimation(taglineAnim);
        tagline.setAlpha(1f);

        // Loading dots: fade in container, then bounce each with offset
        Animation dotsAnim = AnimationUtils.loadAnimation(this, R.anim.dots_intro);
        dots.startAnimation(dotsAnim);
        dots.setAlpha(1f);

        startBounce(dot1, 0);
        startBounce(dot2, 150);
        startBounce(dot3, 300);

        // Navigate to MainActivity
        new Handler(Looper.getMainLooper()).postDelayed(this::goToMain, SPLASH_DURATION_MS);
    }

    private void startBounce(View view, long delay) {
        view.postDelayed(() -> {
            Animation bounce = AnimationUtils.loadAnimation(this, R.anim.dot_bounce);
            view.startAnimation(bounce);
        }, 1500 + delay);
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
