package ca.camosun.snake.gui;

import ca.camosun.snake.R;
import ca.camosun.snake.SingleScore;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class HighScoresActivity extends Activity {

	private TableLayout scoreTable;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_high_scores);
		
		scoreTable = (TableLayout) findViewById(R.id.tableLayout);

		displayScores();
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
