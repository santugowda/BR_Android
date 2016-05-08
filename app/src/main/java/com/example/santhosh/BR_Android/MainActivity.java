package com.example.santhosh.BR_Android;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
	JSONObject jsonobject;
	JSONArray jsonarray;
	ListView listview;
	ListViewAdapter adapter;
	ProgressDialog mProgressDialog;
	ArrayList<HashMap<String, String>> arraylist;

	private static String url = "http://sandbox.bottlerocketapps.com/BR_Android_CodingExam_2015_Server/stores.json";

	static String ADDRESS = "address";
	static String PHONE = "phone";
	static String CITY = "city";
	static String STATE = "state";
	static String ZIPCODE = "zipcode";
	static String STOREID = "storeID";
	static String LATITUDE = "latitude";
	static String LONGITUDE = "longitude";
	static String STORE_LOGO = "storeLogoURL";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_main);

		if(!isConnected(MainActivity.this)){
			buildDialog(MainActivity.this).show();
		}else {
			new DownloadJSON().execute();
		}
	}

	public boolean isConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netinfo = cm.getActiveNetworkInfo();

		if (netinfo != null && netinfo.isConnectedOrConnecting()) {
			android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

			if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
			else return false;
		} else
			return false;
	}
	
	private class DownloadJSON extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(MainActivity.this);
			mProgressDialog.setTitle("Android JSON Parse Tutorial");
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			arraylist = new ArrayList<HashMap<String, String>>();
			jsonobject = ServiceHandler.getJSONfromURL(url);

			try {
				jsonarray = jsonobject.getJSONArray("stores");

				for (int i = 0; i < jsonarray.length(); i++) {
					HashMap<String, String> map = new HashMap<String, String>();
					jsonobject = jsonarray.getJSONObject(i);
					map.put("address", jsonobject.getString("address"));
					map.put("phone", jsonobject.getString("phone"));
					map.put("city", jsonobject.getString("city"));
					map.put("state", jsonobject.getString("state"));
					map.put("zipcode", jsonobject.getString("zipcode"));
					map.put("storeID", jsonobject.getString("storeID"));
					map.put("latitude", jsonobject.getString("latitude"));
					map.put("longitude", jsonobject.getString("longitude"));
					map.put("storeLogoURL", jsonobject.getString("storeLogoURL"));
					arraylist.add(map);
				}
			} catch (JSONException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void args) {
			listview = (ListView) findViewById(R.id.listview);
			adapter = new ListViewAdapter(MainActivity.this, arraylist);
			listview.setAdapter(adapter);
			mProgressDialog.dismiss();
		}
	}

	public AlertDialog.Builder buildDialog(Context c) {
		AlertDialog.Builder builder = new AlertDialog.Builder(c);
		builder.setTitle("No Internet connection.");
		builder.setMessage("Please check your internet connection");

		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				MainActivity.this.finish();
			}
		});
		return builder;
	}
}