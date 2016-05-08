package com.example.santhosh.BR_Android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleItemView extends Activity {
	String address;
	String phone;
	String city;
	String state;
	String zipcode;
	String store;
	String storeLogo;
	String latitude;
	String longitude;
	ImageLoader imageLoader = new ImageLoader(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_item_view);

		Intent i = getIntent();
		address = i.getStringExtra("address");
		phone = i.getStringExtra("phone");
		city = i.getStringExtra("city");
		state = i.getStringExtra("state");
		zipcode = i.getStringExtra("zipcode");
		store = i.getStringExtra("storeID");
		latitude = i.getStringExtra("latitude");
		longitude = i.getStringExtra("longitude");
		storeLogo = i.getStringExtra("storeLogoURL");

		TextView txtaddress = (TextView) findViewById(R.id.address);
		TextView txtphone = (TextView) findViewById(R.id.phone);
		TextView txtcity = (TextView) findViewById(R.id.city);
		TextView txtstoreID = (TextView) findViewById(R.id.store);
		TextView locationDetails = (TextView) findViewById(R.id.locationDetails);

		ImageView imgflag = (ImageView) findViewById(R.id.store_logo);

		txtaddress.setText(address);
		txtphone.setText(phone);
		txtcity.setText(city + ", " + state +" - "+ zipcode);
		txtstoreID.setText(store);
		locationDetails.setText("Lat:" + latitude + " Long:" + longitude);

		imageLoader.DisplayImage(storeLogo, imgflag);
	}
}