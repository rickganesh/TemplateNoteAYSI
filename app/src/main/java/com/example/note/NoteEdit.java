package com.example.note;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class NoteEdit extends Activity{
	

	public static String curDate = "";

    private EditText mTitleText;
    private EditText mBodyText;
    private TextView mDateText;





      
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        

        setContentView(R.layout.note_edit);
        setTitle(R.string.app_name);

        mTitleText = (EditText) findViewById(R.id.title);
        mBodyText = (EditText) findViewById(R.id.body);
        mDateText = (TextView) findViewById(R.id.notelist_date);

        long msTime = System.currentTimeMillis();  
        Date curDateTime = new Date(msTime);
 	
        SimpleDateFormat formatter = new SimpleDateFormat("M'/'d'/'y");
        curDate = formatter.format(curDateTime);        
        
        mDateText.setText(""+curDate);


		SharedPreferences prefs = getSharedPreferences("information", MODE_PRIVATE);
		String title = prefs.getString("name", "");
		String body = prefs.getString("title", "");
		mTitleText.setText(title);
		mBodyText.setText(body);
    
    }
	
	  public static class LineEditText extends EditText{
			// we need this constructor for LayoutInflater
			public LineEditText(Context context, AttributeSet attrs) {
				super(context, attrs);
					mRect = new Rect();
			        mPaint = new Paint();
			        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
			        mPaint.setColor(Color.BLUE);
			}

			private Rect mRect;
		    private Paint mPaint;	    
		    
		    @Override
		    protected void onDraw(Canvas canvas) {
		  
		        int height = getHeight();
		        int line_height = getLineHeight();

		        int count = height / line_height;

		        if (getLineCount() > count)
		            count = getLineCount();

		        Rect r = mRect;
		        Paint paint = mPaint;
		        int baseline = getLineBounds(0, r);

		        for (int i = 0; i < count; i++) {

		            canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);
		            baseline += getLineHeight();

		        super.onDraw(canvas);
		    }

		}
	  }
	  

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.noteedit_menu, menu);
			return true;		
		}
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
		    switch (item.getItemId()) {
		    	case R.id.menu_save:
	    		saveState();

		    default:
		    	return super.onOptionsItemSelected(item);
		    }
		}
	    
	    private void saveState() {
	        String title = mTitleText.getText().toString();
	        String body = mBodyText.getText().toString();
			SharedPreferences.Editor editor = getSharedPreferences("information", MODE_PRIVATE).edit();
			editor.putString("name", title);
			editor.putString("title", body);
			editor.commit();
			Toast.makeText(this, "Saved!",
					Toast.LENGTH_LONG).show();

	    }
	    
	  



}
