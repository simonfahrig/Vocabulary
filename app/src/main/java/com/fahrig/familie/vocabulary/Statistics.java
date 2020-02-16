package com.fahrig.familie.vocabulary;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Statistics extends Activity 
{
	public final String STATISTICS_TABLE = "StatisticsTable";
	public final String TIMESTAMP = "Timestamp";
	public final String GAME_TYPE = "GameType";
	public final String POINTS = "Points";
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
    	StringBuffer timestampBuffer = new StringBuffer(TIMESTAMP);
    	StringBuffer gametypeBuffer = new StringBuffer(GAME_TYPE);
    	StringBuffer pointsBuffer = new StringBuffer(POINTS);
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
			timestampBuffer.append("\n" + cursor.getString(0));
			gametypeBuffer.append("\n" + GameTypes.getLabel(getResources(), cursor.getInt(1)));
			pointsBuffer.append("\n" + cursor.getInt(2));
			cursor.moveToNext();
		}
		} catch  (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
        TextView timestamp = (TextView)findViewById(R.id.timestamp); 
        TextView gameType = (TextView)findViewById(R.id.gametype); 
        TextView points = (TextView)findViewById(R.id.points);
        timestamp.setText(timestampBuffer.toString());
        gameType.setText(gametypeBuffer.toString());
        points.setText(pointsBuffer.toString());
		
    }
}
