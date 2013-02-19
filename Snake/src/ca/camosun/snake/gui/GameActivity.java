package ca.camosun.snake.gui;

import ca.camosun.snake.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class GameActivity extends Activity implements OnClickListener{

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_game);
                GridLayout grid = (GridLayout) findViewById(R.layout.activity_game);

                int rowCount = 20;
                int columnCount = 20;
                int buttonSize = 10;
                int dpi = 20;

                addButtonGrid(grid, rowCount, columnCount, buttonSize, dpi, this);
        }
        

        private void addButtonGrid(GridLayout grid, int rowCount, int columnCount,
                        int buttonSize, int dpi, OnClickListener listen) {
                int rowPixels = rowCount * (buttonSize * dpi);
                int columnPixels = columnCount * (buttonSize * dpi);

                grid.setLayoutParams(new GridLayout.MarginLayoutParams(0, 0));
        
                grid.setLayoutParams(new RelativeLayout.LayoutParams(
                                new ViewGroup.LayoutParams(rowPixels, columnPixels)));
                grid.setColumnCount(columnCount);
                grid.setRowCount(rowCount);

                for (int row = 0; row < rowCount; row++) {
                        makeButtonColumn(row, columnCount, grid, buttonSize, listen);
                }
        }

        private void makeButtonColumn(int row, int columnSize, GridLayout grid,
                        int buttonSize, OnClickListener listen) {
                for (int column = 0; column < columnSize; column++) {
                        GridButton aButton = new GridButton(this, row, column);
                        GridLayout.LayoutParams params = new GridLayout.LayoutParams(
                                        GridLayout.spec(row), GridLayout.spec(column));
                        aButton.setLayoutParams(params);
                        aButton.setAdjustViewBounds(true);
                        aButton.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        aButton.setMaxWidth(buttonSize);
                        aButton.setMaxHeight(buttonSize);
//                      aButton.setBackgroundResource(R.drawable.state_empty);
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