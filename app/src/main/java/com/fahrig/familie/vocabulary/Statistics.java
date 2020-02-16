package com.fahrig.familie.vocabulary;

import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;
import android.database.*;
import android.database.sqlite.*;

import com.fahrig.familie.vocabulary.R;

public class Statistics extends Activity 
{
	//public final int LATIN5_LECTION2_RESULT = 0;
	//public final int LATIN5_LECTION1_RESULT = 1;
	//public final int LATIN5_LECTION3_RESULT = 3;
	//public final int LATIN5_LECTION4_RESULT = 4;
	//public final int ADDITION_RESULT = 2;
	public final String STATISTICS_TABLE = "StatisticsTable";
	public final String TIMESTAMP = "Timestamp";
	public final String GAME_TYPE = "GameType";
	public final String POINTS = "Points";
	//public String[] vocabulary;
	public SQLiteDatabase database;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);
		database = openOrCreateDatabase("Voc.db", Context.MODE_PRIVATE, null);
        loadStatistics();
        Bundle bundle = getIntent().getExtras();
        bundle.getLong("appStartDate");
    }
    
    @Override 
	public void onResume(){
		super.onResume();
    }
    
    @Override 
	public void onWindowFocusChanged(boolean hasFocus){
		super.onWindowFocusChanged(hasFocus);
    }
    
    public void loadStatistics(){
    	StringBuffer tsbuffer = new StringBuffer(TIMESTAMP);
    	StringBuffer gtbuffer = new StringBuffer(GAME_TYPE);
    	StringBuffer ptbuffer = new StringBuffer(POINTS);
		try {
	    	Cursor cursor = database.query(
				STATISTICS_TABLE, //tableName,
				new String[] {TIMESTAMP, GAME_TYPE, POINTS}, //columnsSet, 
				null,          // columns for where part, z.B: "column3 =?";
				null, //  args for where part, z.B: {"apple"};
				null, //groupBy, 
				null, //having, 
				TIMESTAMP + " DESC", //orderBy, // z.B: "Ingredient ASC";
				"100"); //limit); // z.B: "10";
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			tsbuffer.append("\n" + cursor.getString(0));
			gtbuffer.append("\n" + cursor.getInt(1));
			ptbuffer.append("\n" + cursor.getInt(2));
			cursor.moveToNext();
		}
		} catch  (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
        TextView timestamp = (TextView)findViewById(R.id.timestamp); 
        TextView gameType = (TextView)findViewById(R.id.gametype); 
        TextView points = (TextView)findViewById(R.id.points);
        timestamp.setText(tsbuffer.toString());
        gameType.setText(gtbuffer.toString());
        points.setText(ptbuffer.toString());
		
    }
}
