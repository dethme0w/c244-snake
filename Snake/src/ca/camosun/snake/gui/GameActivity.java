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

                int rowCount = 20;
                int columnCount = 20;
                int buttonSize = 5;
                int dpi = 20;
 
                addButtonGrid(grid, rowCount, columnCount, buttonSize, dpi, this);
                
        }
        

        private void addButtonGrid(GridLayout grid, int rowCount, int columnCount,
                        int buttonSize, int dpi, OnClickListener listen) {
        	
                int rowPixels = rowCount * (buttonSize * dpi);
                int columnPixels = columnCount * (buttonSize * dpi);
            	grid.setRowCount(rowCount);
            	grid.setColumnCount(columnCount);
            	
                for (int row = 0; row < rowCount; row++) {
                        makeButtonColumn(row, columnCount, grid, buttonSize, listen);
                }
                
                
        }

        private void makeButtonColumn(int row, int columnSize, GridLayout grid,
                        int buttonSize, OnClickListener listen) {
                for (int column = 0; column < columnSize; column++) {
                        GridButton aButton = new GridButton(this, row, column);
                        aButton.setAdjustViewBounds(true);                                     
                        aButton.setMinimumHeight(1);
                        aButton.setMinimumWidth(1);
                        aButton.setMaxWidth(buttonSize);
                        aButton.setMaxHeight(buttonSize);
                        aButton.setOnClickListener(listen);
                        grid.addView(aButton, row);
                }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.activity_main, menu);

                return true;
        }


        @Override
        public void onClick(View theButton) {
                GridButton aButton = (GridButton) theButton;
                System.out.println("clicked row " + aButton.getRow() + " column " + aButton.getColumn());
                
        }

}