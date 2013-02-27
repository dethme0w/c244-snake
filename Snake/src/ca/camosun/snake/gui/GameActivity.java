package ca.camosun.snake.gui;

import ca.camosun.snake.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridLayout;

public class GameActivity extends Activity implements OnClickListener{

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
               
                setContentView(R.layout.activity_game);
                
                GridLayout grid = (GridLayout) findViewById(R.layout.activity_game);

                int rowCount = 10;
                int columnCount = 10;
                int cellSize = 16;
                int dpi = 20;
 
                addGrid(grid, rowCount, columnCount, cellSize, dpi, this);
                
        }
        

        private void addGrid(GridLayout grid, int rowCount, int columnCount,
                        int cellSize, int dpi, OnClickListener listen) {
        	
                int rowPixels = rowCount * (cellSize * dpi);
                int columnPixels = columnCount * (cellSize * dpi);
            	grid.setRowCount(rowCount);
            	grid.setColumnCount(columnCount);
            	
                for (int row = 0; row < rowCount; row++) {
                        makeGridColumn(row, columnCount, grid, cellSize, listen);
                }
                
                
        }

        private void makeGridColumn(int row, int columnSize, GridLayout grid,
                        int buttonSize, OnClickListener listen) {
                for (int column = 0; column < columnSize; column++) {
                       
                	    GridImage aCell = new GridImage(this,row,column);
                	    
                	    aCell.setImageResource(R.drawable.boardbackground);
                	    
                	    grid.addView(aCell);
                }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.activity_main, menu);

                return true;
        }


		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}       

}