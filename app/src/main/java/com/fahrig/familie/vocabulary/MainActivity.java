package com.fahrig.familie.vocabulary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.preference.PreferenceManager;

public class MainActivity extends Activity 
{
	public final long appStartDate = System.currentTimeMillis();
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
            case R.id.settings_container:
                settings();
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
    public void onActivityResult(int gameType, int value, Intent data) {
    	TextView result = (TextView)findViewById(R.id.result);
		exec(insertIntoStatisticsTable(gameType, value));
		result.setText(GameTypes.getLabel(getResources(), gameType) + ": " + value + " Punkte");
    }
    
    public void startGame(View view){
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this /* Activity context */);
        Boolean lection1 = sharedPreferences.getBoolean("switch_lection_1", false);
        Boolean lection2 = sharedPreferences.getBoolean("switch_lection_2", false);
        Boolean lection3 = sharedPreferences.getBoolean("switch_lection_3", false);
        Boolean lection4 = sharedPreferences.getBoolean("switch_lection_4", false);
        Boolean lection5 = sharedPreferences.getBoolean("switch_lection_5", false);
        Boolean lection6 = sharedPreferences.getBoolean("switch_lection_6", false);
    	int gameResult = GameTypes.getId(getResources(), "Lektion 1");
    	List<String> vocs = new ArrayList<String>();
        if (lection1) {
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 1"));
        }
        if (lection2) {
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 2"));
        }
        if (lection3) {
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 3"));
        }
        if (lection4) {
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 4"));
        }
        if (lection5) {
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 5"));
        }
        if (lection6) {
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 6"));
        }
        vocabulary = vocs.toArray(new String[vocs.size()]);

        if (GameTypes.isMathGame(gameResult)) {
            Intent intent = new Intent(this, MathGameActivity.class);
            startActivityForResult(intent, gameResult);
        } else {
            Intent intent = new Intent(this, GameActivity.class)
            .putExtra("vocabulary", vocabulary);
            startActivityForResult(intent, gameResult);
        }
    }

    public void statistics(){
        Intent intent = new Intent(this, Statistics.class)
                .putExtra("appStartDate", appStartDate);
        startActivity(intent);
    }

    public void settings(){
        Intent intent = new Intent(this, MySettingsActivity.class)
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
