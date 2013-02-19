package ca.camosun.snake.gui;
import android.content.Context;
import android.widget.ImageButton;

public class GridButton extends ImageButton {
        private int row;
        private int column;
        
        public GridButton(Context theContext, int inRow, int inColumn) {
                super(theContext);
                
                row = inRow;
                column = inColumn;
        }
        
        public int getRow() {
                return row;
        }
        
        public int getColumn() {
                return column;
        }
}