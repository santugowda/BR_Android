package com.example.santhosh.BR_Android;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewAdapter extends BaseAdapter {

	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;
	ImageLoader imageLoader;
	HashMap<String, String> hashMap = new HashMap<String, String>();

	public ListViewAdapter(Context context,
			ArrayList<HashMap<String, String>> arraylist) {
		this.context = context;
		data = arraylist;
		imageLoader = new ImageLoader(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		TextView listPhone;
		TextView listAddress;
		ImageView flag;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.listview_item, parent, false);
		hashMap = data.get(position);

		listPhone = (TextView) itemView.findViewById(R.id.address);
		listAddress = (TextView) itemView.findViewById(R.id.phone);
		flag = (ImageView) itemView.findViewById(R.id.store_logo);

		listPhone.setText(hashMap.get(MainActivity.ADDRESS));
		listAddress.setText(hashMap.get(MainActivity.PHONE));

		imageLoader.DisplayImage(hashMap.get(MainActivity.STORE_LOGO), flag);
		itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				hashMap = data.get(position);
				Intent intent = new Intent(context, SingleItemView.class);
				intent.putExtra("address", hashMap.get(MainActivity.ADDRESS));
				intent.putExtra("phone", hashMap.get(MainActivity.PHONE));
				intent.putExtra("city", hashMap.get(MainActivity.CITY));
				intent.putExtra("state",hashMap.get(MainActivity.STATE));
				intent.putExtra("zipcode",hashMap.get(MainActivity.ZIPCODE));
				intent.putExtra("latitude",hashMap.get(MainActivity.LATITUDE));
				intent.putExtra("longitude", hashMap.get(MainActivity.LONGITUDE));
				intent.putExtra("storeID",hashMap.get(MainActivity.STOREID));
				intent.putExtra("storeLogoURL", hashMap.get(MainActivity.STORE_LOGO));
				context.startActivity(intent);

			}
		});
		return itemView;
	}
}
