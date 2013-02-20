package ca.camosun.snake;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private int numRows = 80;
    private int numCols = 48;
    private int gridSize = 10;
    private int rowCounter = 0;
    private int colCounter = 0;
    private int cellCounter = 0;
    
    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return numRows * numCols;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        
        int bgColor = Color.GRAY;
        
        if (convertView == null) {  // if it's not recycled, initialize some attributes
        	
        	imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(gridSize, gridSize));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(0, 0, 0, 0);
            imageView.setId(cellCounter);
            
            imageView.setOnClickListener(new OnClickListener() {
    	    	@Override
    	        public void onClick(View view) {
    	    		System.out.println("Grid clicked:" + view.getId());
    	        }
    	    });
            
            if(colCounter == numCols) {
            	colCounter = 0;
            	rowCounter++;
            }
            
    		if(colCounter % 2 == 1) {
    			bgColor = Color.DKGRAY;
    		}
    		
    		if(rowCounter % 2 == 1) {
    			bgColor = Color.GRAY;
    			if(colCounter % 2 == 0) {
    				bgColor = Color.DKGRAY;
    			}
    		}
    		
    		colCounter++;
    		cellCounter++;
            
        } else {
            imageView = (ImageView) convertView;
        }
		
        // imageView.setImageResource(mThumbIds[position]);
        imageView.setBackgroundColor(bgColor);
        
        return imageView;
    }

}
