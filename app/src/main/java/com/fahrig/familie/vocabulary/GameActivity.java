package com.fahrig.familie.vocabulary;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends Activity 
{
	public List<Vocable> vocabulary;
	public Random random;
	public final int TIME_LIMIT = 60;
	public int timeLeft = TIME_LIMIT;
	public final int BLOCKED_TIME_LIMIT = 3;
	public int blockedTimeLeft = BLOCKED_TIME_LIMIT;
	public boolean blocked = false;
	public int points = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        random = new Random(System.currentTimeMillis());
        getVocabulary("vocabulary");
        addPoint(0);
        if(savedInstanceState != null){
        	if (savedInstanceState.getBoolean("blocked")) {
		        setBlockedTime(savedInstanceState.getInt("blockedTimeLeft"));
	        }
        	setCountDown(savedInstanceState.getInt("timeLeft"));
        } else {
	        setCountDown(TIME_LIMIT);
	        provideQuestion();
        }
    }

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
  // Save UI state changes to the savedInstanceState.
  // This bundle will be passed to onCreate if the process is
  // killed and restarted.
		savedInstanceState.putString("question", (String)((TextView)findViewById(R.id.question)).getText());
		savedInstanceState.putString("answer1", (String)((Button)findViewById(R.id.answer1)).getText());
		savedInstanceState.putString("answer2", (String)((Button)findViewById(R.id.answer2)).getText());
		savedInstanceState.putString("answer3", (String)((Button)findViewById(R.id.answer3)).getText());
		savedInstanceState.putInt("timeLeft", timeLeft);
		savedInstanceState.putInt("blockedTimeLeft", blockedTimeLeft);
		savedInstanceState.putInt("points", points);
		savedInstanceState.putBoolean("blocked", blocked);
  //savedInstanceState.putDouble("myDouble", 1.9);
  //savedInstanceState.putString("MyString", "Welcome back to Android");
  // etc.
}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		((TextView)findViewById(R.id.question)).setText(savedInstanceState.getString("question"));
		((Button)findViewById(R.id.answer1)).setText(savedInstanceState.getString("answer1"));
		((Button)findViewById(R.id.answer2)).setText(savedInstanceState.getString("answer2"));
		((Button)findViewById(R.id.answer3)).setText(savedInstanceState.getString("answer3"));
  // Restore UI state from the savedInstanceState.
  // This bundle has also been passed to onCreate.
		blocked = savedInstanceState.getBoolean("blocked");
  //double myDouble = savedInstanceState.getDouble("myDouble");
		points = savedInstanceState.getInt("points");
  //String myString = savedInstanceState.getString("MyString");
}
    
    @Override 
	public void onResume(){
		super.onResume();
    }
    
    @Override 
	public void onWindowFocusChanged(boolean hasFocus){
		super.onWindowFocusChanged(hasFocus);
    }
    
    public void getVocabulary(String vocArrayName){
    	Bundle bundle = getIntent().getExtras();
	    String[] rawVocabulary = new String[0];
        if (bundle != null) {
		    rawVocabulary = bundle.getStringArray(vocArrayName);
		}
		vocabulary = new ArrayList<Vocable>();
		for (int i = 0; i < rawVocabulary.length; i++) {
			vocabulary.add(new Vocable(rawVocabulary[i].split(";")));
		}
    }
    
    public void provideQuestion() {
    	List<Vocable> proposals = getProposals();
	    TextView question = (TextView)findViewById(R.id.question);
		Button answer1 = (Button)findViewById(R.id.answer1);
		Button answer2 = (Button)findViewById(R.id.answer2);
		Button answer3 = (Button)findViewById(R.id.answer3);
		answer1.setTextColor(getResources().getColor(R.color.black));
		answer2.setTextColor(getResources().getColor(R.color.black));
		answer3.setTextColor(getResources().getColor(R.color.black));
	    if (random.nextBoolean()) {
			question.setText(proposals.get(random.nextInt(3)).german);
			answer1.setText(proposals.get(0).english);
			answer2.setText(proposals.get(1).english);
			answer3.setText(proposals.get(2).english);
		} else {
			question.setText(proposals.get(random.nextInt(3)).english);
			answer1.setText(proposals.get(0).german);
			answer2.setText(proposals.get(1).german);
			answer3.setText(proposals.get(2).german);
		}
    }
    
    public void setCountDown(final int seconds){
	    final ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
	    //progress.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
		new CountDownTimer(1000 * seconds, 1000) {
			public void onTick(long millisUntilFinished) {
				progress.setProgress((int)((millisUntilFinished - 1000)/ (10 * TIME_LIMIT)));
				timeLeft = (int)(millisUntilFinished/1000);
			}
			public void onFinish() {
				progress.setProgress(0);
				finish();
			}
		}.start();
    }
    
    public void onAnswer(View view){
    	if (!blocked) {
	    	final Button answerView = (Button)view;
	    	String answer = (String)answerView.getText();
			TextView questionView = (TextView)findViewById(R.id.question);
			String question = (String)questionView.getText();
			if (vocabulary.contains(new Vocable(question, answer)) || vocabulary.contains(new Vocable(answer, question))){
				addPoint(1);
				provideQuestion();
			} else {
				answerView.setTextColor(getResources().getColor(R.color.red));
				setBlockedTime(BLOCKED_TIME_LIMIT);
			}
		}
    }
    
    public void setBlockedTime(final int seconds){
    	blocked = true;
	    new CountDownTimer(1000 * seconds, 1000) {
			public void onTick(long millisUntilFinished) {
				blockedTimeLeft = (int)(millisUntilFinished/1000);
			}
			public void onFinish() {
				blocked = false;
				((Button)findViewById(R.id.answer1)).setTextColor(getResources().getColor(R.color.black));
				((Button)findViewById(R.id.answer2)).setTextColor(getResources().getColor(R.color.black));
				((Button)findViewById(R.id.answer3)).setTextColor(getResources().getColor(R.color.black));
			}
		}.start();
    }
    
    public void addPoint(int increment) {
    	points += increment;;
    	setResult(points);
		TextView results = (TextView)findViewById(R.id.points);
		results.setText("" + points + " " + getResources().getString(R.string.points));
    }
    
    public List<Vocable> getProposals() {
    	List<Integer> propIndices = new ArrayList<Integer>();
	    while(propIndices.size() < 3){
		    propIndices.add(nextIntExcluding(vocabulary.size(), propIndices));
		}
		List<Vocable> proposals = new ArrayList<Vocable>();
		for (int i = 0; i < propIndices.size(); i++) {
			proposals.add(vocabulary.get(propIndices.get(i)));
		}
		return proposals;
    }
    
    public int nextIntExcluding(int bound, List<Integer> excludes) {
    	int randomInt;
    	do {
		    randomInt = random.nextInt(bound);
	    } while(excludes.contains(randomInt));
		return randomInt;
	}
}
