package com.csform.android.uiapptemplate;

import com.csform.android.uiapptemplate.adapter.DrawerSocialAdapter;
import com.csform.android.uiapptemplate.util.ImageUtil;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class LeftMenusSocialActivity extends ActionBarActivity {

	private ListView mDrawerList;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle("Friends and Foes");
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mTitle = mDrawerTitle = getTitle();
		mDrawerList = (ListView) findViewById(R.id.list_view);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		View headerView = getLayoutInflater().inflate(
				R.layout.header_navigation_drawer_social, mDrawerList, false);

		ImageView iv = (ImageView) headerView.findViewById(R.id.image);
		ImageUtil.displayRoundImage(iv,
				"http://pengaja.com/uiapptemplate/newphotos/profileimages/0.jpg", null);

		mDrawerList.addHeaderView(headerView);// Add header before adapter (for
												// pre-KitKat)
		mDrawerList.setAdapter(new DrawerSocialAdapter(this));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		int color = getResources().getColor(R.color.material_grey_100);
		color = Color.argb(0xCD, Color.red(color), Color.green(color),
				Color.blue(color));
		mDrawerList.setBackgroundColor(color);
		mDrawerList.getLayoutParams().width = (int) getResources()
				.getDimension(R.dimen.drawer_width_social);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
				R.string.drawer_open, R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				getSupportActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			mDrawerLayout.openDrawer(mDrawerList);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Toast.makeText(LeftMenusSocialActivity.this,
					"You selected position: " + position, Toast.LENGTH_SHORT)
					.show();
			mDrawerLayout.closeDrawer(mDrawerList);
		}
	}

	@Override
	public void setTitle(int titleId) {
		setTitle(getString(titleId));
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
}
