package com.example.inspiron.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewAnimationUtils;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private AppBarLayout appBar;
    private EditText searchEditText;
    private Toolbar searchToolBar;
    private AppBarLayout searchAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appBar = (AppBarLayout) findViewById(R.id.appBar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        searchAppBar = (AppBarLayout) findViewById(R.id.searchAppBar);
        setSupportActionBar(toolbar);

        searchEditText = (EditText) findViewById(R.id.searchEditText);
        searchToolBar = (Toolbar) findViewById(R.id.searchToolBar);

        searchToolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_48dp);
        searchToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchToolBar.setVisibility(View.GONE);
                toolbar.setVisibility(View.VISIBLE);
                tabLayout.setVisibility(View.VISIBLE);
                Utils.hideKeyBoard(searchEditText);
            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new CustomFragmentPagerAdapter(getSupportFragmentManager(),
                MainActivity.this));

        // Give the TabLayout the ViewPager
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        tabLayout.getTabAt(1).select();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_search:
                //toolbar.setVisibility(View.GONE);
                //tabLayout.setVisibility(View.GONE);
                //searchToolBar.setVisibility(View.VISIBLE);
                enterReveal();
                searchEditText.requestFocus();
                Utils.showKeyBoard(searchEditText);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // if the searchToolBar is visible, hide it
        // otherwise, do parent onBackPressed method
        if (searchToolBar.getVisibility() == View.VISIBLE) {
            exitReveal();
        } else {
            super.onBackPressed();
        }
    }

    private void enterReveal() {
        // previously invisible view

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(appBar, "translationY", -tabLayout.getHeight()),
                ObjectAnimator.ofFloat(viewPager, "translationY", -tabLayout.getHeight()),
                ObjectAnimator.ofFloat(appBar, "alpha", 0)
        );
        set.setDuration(300).addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                appBar.setVisibility(View.GONE);
                searchEditText.requestFocus();
                Utils.showKeyBoard(searchEditText);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();

        // get the center for the clipping circle
      //  int cx = toolbar.getMeasuredWidth() / 2;
        int cx = searchToolBar.getWidth() - (int) (getResources().getDimension(R.dimen.dp48) * (0.5f + 2));
      //  int cy = toolbar.getMeasuredHeight() / 2;
        int cy = (searchToolBar.getTop() + searchToolBar.getBottom()) / 2;

        // get the final radius for the clipping circle
      //  int finalRadius = Math.max(toolbar.getWidth(), toolbar.getHeight()) / 2;
        int dx = Math.max(cx, searchToolBar.getWidth() - cx);
        int dy = Math.max(cy, searchToolBar.getHeight() - cy);
        float finalRadius = (float) Math.hypot(dx, dy);

        // create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(searchToolBar, cx, cy, 0, finalRadius);

        // make the view visible and start the animation
        searchAppBar.setVisibility(View.VISIBLE);
        anim.setDuration(300);
        anim.start();
    }

    private void exitReveal() {

        // get the center for the clipping circle
        int cx = searchToolBar.getWidth() - (int) (getResources().getDimension(R.dimen.dp48) * (0.5f + 2));
        int cy = (searchToolBar.getTop() + searchToolBar.getBottom()) / 2;

        // get the initial radius for the clipping circle
        int dx = Math.max(cx, searchToolBar.getWidth() - cx);
        int dy = Math.max(cy, searchToolBar.getHeight() - cy);
        float finalRadius = (float) Math.hypot(dx, dy);

        // create the animation (the final radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(searchAppBar, cx, cy, finalRadius, 0);

        anim.setDuration(300);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                searchAppBar.setVisibility(View.GONE);
                Utils.hideKeyBoard(searchEditText);
            }
        });

        // start the animation
        anim.start();

        appBar.setVisibility(View.VISIBLE);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(appBar, "translationY", 0),
                ObjectAnimator.ofFloat(appBar, "alpha", 1),
                ObjectAnimator.ofFloat(viewPager, "translationY", 0)
        );
        set.setDuration(300).start();
    }
}

