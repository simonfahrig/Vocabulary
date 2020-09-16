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
        Boolean lection1_1 = sharedPreferences.getBoolean("switch_lection_1_1", false);
        Boolean lection1_2 = sharedPreferences.getBoolean("switch_lection_1_2", false);
        Boolean lection1_3 = sharedPreferences.getBoolean("switch_lection_1_3", false);
        Boolean lection2_1 = sharedPreferences.getBoolean("switch_lection_2_1", false);
        Boolean lection2_2 = sharedPreferences.getBoolean("switch_lection_2_2", false);
        Boolean lection2_3 = sharedPreferences.getBoolean("switch_lection_2_3", false);
        Boolean lection3_1 = sharedPreferences.getBoolean("switch_lection_3_1", false);
        Boolean lection3_2 = sharedPreferences.getBoolean("switch_lection_3_2", false);
        Boolean lection3_3 = sharedPreferences.getBoolean("switch_lection_3_3", false);
        Boolean lection4_1 = sharedPreferences.getBoolean("switch_lection_4_1", false);
        Boolean lection4_2 = sharedPreferences.getBoolean("switch_lection_4_2", false);
        Boolean lection4_3 = sharedPreferences.getBoolean("switch_lection_4_3", false);
        Boolean lection5_1 = sharedPreferences.getBoolean("switch_lection_5_1", false);
        Boolean lection5_2 = sharedPreferences.getBoolean("switch_lection_5_2", false);
        Boolean lection5_3 = sharedPreferences.getBoolean("switch_lection_5_3", false);
        Boolean lection6_1 = sharedPreferences.getBoolean("switch_lection_6_1", false);
        Boolean lection6_2 = sharedPreferences.getBoolean("switch_lection_6_2", false);
        Boolean lection6_3 = sharedPreferences.getBoolean("switch_lection_6_3", false);
        Boolean lection7_1 = sharedPreferences.getBoolean("switch_lection_7_1", false);
        Boolean lection7_2 = sharedPreferences.getBoolean("switch_lection_7_2", false);
        Boolean lection7_3 = sharedPreferences.getBoolean("switch_lection_7_3", false);
        Boolean lection8_1 = sharedPreferences.getBoolean("switch_lection_8_1", false);
        Boolean lection8_2 = sharedPreferences.getBoolean("switch_lection_8_2", false);
        Boolean lection8_3 = sharedPreferences.getBoolean("switch_lection_8_3", false);
    	int gameResult = GameTypes.getId(getResources(), "Lektion 1");
    	List<String> vocs = new ArrayList<String>();
        if (lection1_1){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 1.1"));
        }
        if (lection1_2){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 1.2"));
        }
        if (lection1_3){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 1.3"));
        }
        if (lection2_1){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 2.1"));
        }
        if (lection2_2){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 2.2"));
        }
        if (lection2_3){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 2.3"));
        }
        if (lection3_1){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 3.1"));
        }
        if (lection3_2){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 3.2"));
        }
        if (lection3_3){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 3.3"));
        }
        if (lection4_1){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 4.1"));
        }
        if (lection4_2){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 4.2"));
        }
        if (lection4_3){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 4.3"));
        }
        if (lection5_1){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 5.1"));
        }
        if (lection5_2){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 5.2"));
        }
        if (lection5_3){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 5.3"));
        }
        if (lection6_1){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 6.1"));
        }
        if (lection6_2){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 6.2"));
        }
        if (lection6_3){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 6.3"));
        }
        if (lection7_1){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 7.1"));
        }
        if (lection7_2){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 7.2"));
        }
        if (lection7_3){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 7.3"));
        }
        if (lection8_1){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 8.1"));
        }
        if (lection8_2){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 8.2"));
        }
        if (lection8_3){
            Collections.addAll(vocs, GameTypes.getList(getResources(), "Lektion 8.3"));
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
