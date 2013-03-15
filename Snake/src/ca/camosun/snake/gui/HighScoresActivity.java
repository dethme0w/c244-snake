package ca.camosun.snake.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import ca.camosun.snake.R;
import ca.camosun.snake.SingleScore;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class HighScoresActivity extends Activity {

	private TableLayout scoreTable;
	private Context context;
	private String score_filename;
	private File scoreFile;
	FileOutputStream outputStream;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_high_scores);
	    
	    score_filename = getString(R.string.score_filename);
		
		scoreTable = (TableLayout) findViewById(R.id.tableLayout);
		
		context = getApplicationContext();
		
		try {
			
			saveScores();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		displayScores();
	}
	
	public void saveScores() throws IOException{
		scoreFile = context.getFileStreamPath(score_filename);
		
		if(!scoreFile.exists()){
			new File(context.getFilesDir(), score_filename);
		}
		
		outputStream = openFileOutput(score_filename, Context.MODE_PRIVATE);
			
		for (SingleScore aScore : MainActivity.highScores) {
			
			String scoreString = aScore.getName() + ":" + aScore.getScore() + "\r\n";
			
			outputStream.write(scoreString.getBytes());
			
		}
		outputStream.close();
		
	}
	
	private void displayScores() {		
		TableRow titleRow = new TableRow(this);
        TextView title = new TextView(this);

		if (MainActivity.highScores.size() == 0) {
			title.setText("There are no scores yet!");
			titleRow.addView(title);
			titleRow.setGravity(Gravity.CENTER_HORIZONTAL);
	        scoreTable.addView(titleRow);
			return;
		}
		
		
        title.setText("Your scores:");
        titleRow.addView(title);
        titleRow.setGravity(Gravity.CENTER_HORIZONTAL);
        scoreTable.addView(titleRow);

		for (SingleScore aScore : MainActivity.highScores) {
	        TableRow row = new TableRow(this);

	        TextView name = new TextView(this);
	        TextView score = new TextView(this);
	        
	        name.setText(aScore.getName());
	        score.setText(String.valueOf(aScore.getScore()));

	        score.setGravity(Gravity.RIGHT);
	        
	        row.addView(name);
	        row.addView(score);

	        scoreTable.addView(row);
		}
	}

}
