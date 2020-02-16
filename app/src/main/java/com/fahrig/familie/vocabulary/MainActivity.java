package com.fahrig.familie.vocabulary;

import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;
import android.database.*;
import android.database.sqlite.*;

import com.fahrig.familie.vocabulary.R;

public class MainActivity extends Activity 
{
	public final long appStartDate = System.currentTimeMillis();
	public final int LATIN5_LECTION2_RESULT = 0;
	public final int LATIN5_LECTION1_RESULT = 1;
	public final int LATIN5_LECTION3_RESULT = 3;
	public final int LATIN5_LECTION4_RESULT = 4;
	public final int ADDITION_RESULT = 2;
	public final String STATISTICS_TABLE = "StatisticsTable";
	public final String TIMESTAMP = "Timestamp";
	public final String GAME_TYPE = "GameType";
	public final String POINTS = "Points";
	public String[] vocabulary;
	public SQLiteDatabase database;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		database = openOrCreateDatabase("Voc.db", Context.MODE_PRIVATE, null);
        exec(createStatisticsTable());
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
    // Handle item selection
        //Toast.makeText(this, item.getTitle (), Toast.LENGTH_SHORT).show();
	    switch (item.getItemId()) {
	        case R.id.statistics:
	            statistics();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
		}
    }
    
    @Override 
	public void onResume(){
		super.onResume();
    }
    
    @Override 
	public void onWindowFocusChanged(boolean hasFocus){
		super.onWindowFocusChanged(hasFocus);
    }
    
    @Override
    public void onActivityResult(int id, int value, Intent data) {
    	TextView result = (TextView)findViewById(R.id.result);
		exec(insertIntoStatisticsTable(id, value));
    	switch (id) {
	    	case LATIN5_LECTION2_RESULT:
				result.setText(getResources().getString(R.string.latin5_lection2) + ": " + (value - RESULT_FIRST_USER) + " Punkte");
			case LATIN5_LECTION1_RESULT:
				result.setText(getResources().getString(R.string.latin5_lection1) + ": " + (value - RESULT_FIRST_USER) + " Punkte");
			case LATIN5_LECTION3_RESULT:
				result.setText(getResources().getString(R.string.latin5_lection3) + ": " + (value - RESULT_FIRST_USER) + " Punkte");
			case LATIN5_LECTION4_RESULT:
				result.setText(getResources().getString(R.string.latin5_lection4) + ": " + (value - RESULT_FIRST_USER) + " Punkte");
			case ADDITION_RESULT:
				result.setText(getResources().getString(R.string.addition) + ": " + (value - RESULT_FIRST_USER) + " Punkte");
			default:
	   }
    }
    
    public void startGame(View view){
    	int gameResult = 0;
    	if  (((Button)view).getText().equals(getResources().getString(R.string.latin5_lection2))) {
	    	vocabulary = getResources().getStringArray(R.array.latin5_lection2);
			gameResult = LATIN5_LECTION2_RESULT;
    	} else if  (((Button)view).getText().equals(getResources().getString(R.string.latin5_lection1))) {
	    	vocabulary = getResources().getStringArray(R.array.latin5_lection1);
			gameResult = LATIN5_LECTION3_RESULT;
    	} else if  (((Button)view).getText().equals(getResources().getString(R.string.latin5_lection3))) {
	    	vocabulary = getResources().getStringArray(R.array.latin5_lection3);
			gameResult = LATIN5_LECTION4_RESULT;
    	} else if  (((Button)view).getText().equals(getResources().getString(R.string.latin5_lection4))) {
	    	vocabulary = getResources().getStringArray(R.array.latin5_lection4);
			gameResult = LATIN5_LECTION1_RESULT;
    	} else if  (((Button)view).getText().equals(getResources().getString(R.string.addition))) {
			gameResult = ADDITION_RESULT;
    	}
    	if (gameResult == LATIN5_LECTION1_RESULT || gameResult == LATIN5_LECTION2_RESULT || gameResult == LATIN5_LECTION3_RESULT || gameResult == LATIN5_LECTION4_RESULT){
	    	Intent intent = new Intent(this, GameActivity.class)
		    .putExtra("vocabulary", vocabulary);
	        startActivityForResult(intent, gameResult); 
    	} else {
	    	Intent intent = new Intent(this, MathGameActivity.class);
	        startActivityForResult(intent, gameResult);
		}
    }
    
    public void statistics(){
    	Intent intent = new Intent(this, Statistics.class)
	    .putExtra("appStartDate", appStartDate);
        startActivity(intent); 
    }
    
    public String createStatisticsTable(){
    	StringBuffer buffer = new StringBuffer("CREATE TABLE IF NOT EXISTS ");
        buffer.append(STATISTICS_TABLE + " (");
        buffer.append(TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP, ");
        buffer.append(GAME_TYPE + " INTEGER, ");
        buffer.append(POINTS + " INTEGER);");
        return buffer.toString();
    }
    
    public String insertIntoStatisticsTable(int gameType, int points){
    	StringBuffer buffer = new StringBuffer("INSERT INTO ");
        buffer.append(STATISTICS_TABLE + " (");
        buffer.append(GAME_TYPE + ", ");
        buffer.append(POINTS + ") VALUES(");
        buffer.append(gameType + ", ");
        buffer.append(points + ");");
        return buffer.toString();
    }
    
    public void exec(String statement) {
		//Toast.makeText(this, statement, Toast.LENGTH_SHORT).show();
    	try {
	    	database.execSQL(statement);
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
		}
    }
}
