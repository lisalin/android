package com.example.bmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
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
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
