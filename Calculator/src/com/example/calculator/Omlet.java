package com.example.calculator;


import android.content.Context;
import android.content.Intent;

public class Omlet {
	public static void post(String s, Context context) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.setPackage("mobisocial.omlet");
		intent.putExtra(Intent.EXTRA_TEXT, s);
		intent.setType("text/plain");
		context.startActivity(intent);
	}
	
	public static void postAndGo(String s, Context context) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.setPackage("mobisocial.omlet");
		intent.putExtra(Intent.EXTRA_TEXT, s);
		intent.setType("text/plain");
		context.startActivity(intent);
	}
}
