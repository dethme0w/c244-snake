package ca.camosun.snake.gui;
import android.content.Context;
import android.widget.ImageView;

public class GridImage extends ImageView {
        private int row;
        private int column;
        
        public GridImage(Context theContext, int inRow, int inColumn) {
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