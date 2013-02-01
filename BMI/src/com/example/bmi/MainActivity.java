package com.example.bmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Button calcbutton;
	private EditText fieldheight;
	private EditText fieldweight;
	private TextView view_result;
	private TextView view_suggest;
	
	protected static final int MENU_ABOUT = Menu.FIRST;
	protected static final int MENU_Quit = Menu.FIRST+1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViews();
		setListeners();
	}

	private void findViews() {
		calcbutton = (Button) findViewById(R.id.submit);
		fieldheight = (EditText) findViewById(R.id.height);
		fieldweight = (EditText) findViewById(R.id.weight);

		view_result = (TextView) findViewById(R.id.result);
		view_suggest = (TextView) findViewById(R.id.suggest);
	}

	private void setListeners() {
		calcbutton.setOnClickListener(calcBMI);
	}

	private Button.OnClickListener calcBMI = new OnClickListener() {
		public void onClick(View v) {
			DecimalFormat nf = new DecimalFormat("0.00");

			double height = Double
					.parseDouble(fieldheight.getText().toString()) / 100;
			double weight = Double
					.parseDouble(fieldweight.getText().toString());
			double BMI = weight / (height * height);

			view_result.setText(getText(R.string.bmi_result) + nf.format(BMI));

			// Give health advice
			if (BMI > 25) {
				view_suggest.setText(R.string.advice_heavy);
			} else if (BMI < 20) {
				view_suggest.setText(R.string.advice_light);
			} else {
				view_suggest.setText(R.string.advice_average);
			}
		}

	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_main, menu);
//		return true;
		super.onCreateOptionsMenu(menu);
		menu.add(0, MENU_ABOUT, 0, R.string.menu_about);
		menu.add(0, MENU_Quit, 0, R.string.menu_quit);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
	   super.onOptionsItemSelected(item);
	   switch(item.getItemId()){
	        case MENU_ABOUT:
	            openOptionsDialog();
	            break;
	       case MENU_Quit:
	    	   finish();
	    	    break;
		}
		return true;
	}	          
	
	private void openOptionsDialog() {
		/* // this is Dialog
		AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
		dialog.setTitle(R.string.about_title);
		dialog.setMessage(R.string.about_msg);
		
		dialog.setPositiveButton(R.string.dialog_OK,
                new DialogInterface.OnClickListener(){
                    public void onClick(
                            DialogInterface dialoginterface, int i){
                            }
                    });
		
		dialog.show();

		// this is Toast
		Toast popup = Toast.makeText(MainActivity.this,
				R.string.toast_text, Toast.LENGTH_SHORT);
		popup.show();
		*/
		new AlertDialog.Builder(MainActivity.this)
			.setTitle(R.string.about_title)
			.setMessage(R.string.about_msg)
			.setPositiveButton(R.string.dialog_OK, 
					new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
				}
			})
			.setNegativeButton(R.string.homepage_label, 
					new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Uri uri = Uri.parse(getString(R.string.uri_string));
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(intent);
				}
			})
			.show();
	}

}
