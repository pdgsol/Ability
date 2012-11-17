package com.example.ability;

import java.util.Random;

import android.graphics.Bitmap;

import android.graphics.Canvas;

import android.graphics.Rect;
import android.view.MotionEvent;

public class DodgingSprite {

	// direction = 0 up, 1 left, 2 down, 3 right,

	// animation = 3 back, 1 left, 0 front, 2 right

	int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };

	private static final int BMP_ROWS = 4;

	private static final int BMP_COLUMNS = 3;

	private static final int MAX_SPEED = 40;
	
	private DodgingView gameView;

	private Bitmap bmp;

	private int x = 0;

	private int y = 0;

	private int xSpeed;

	private int ySpeed;

	private int currentFrame = 0;

	private int width;

	private int height;
	
	private Integer radi;
	
	private boolean gamer;
	
	private boolean touch;

	public DodgingSprite(DodgingView gameView, Bitmap bmp, boolean bGamer) {

		this.gamer = bGamer; 
		
		this.touch = false;
		
		this.width = bmp.getWidth(); // BMP_COLUMNS;

		this.height = bmp.getHeight(); // BMP_ROWS;
		
		//this.radi = (int) Math.sqrt((this.width*this.width) + (this.height*this.height));

		this.gameView = gameView;

		this.bmp = bmp;

		Random rnd = new Random();

		x = rnd.nextInt(gameView.getWidth() - width);

		y = rnd.nextInt(gameView.getHeight() - height);

		xSpeed = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;

		ySpeed = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;
		
	}
	
	public int getRadi()
	{
		return this.radi;
	}

	private void update() {
		

		if (x >= gameView.getWidth() - width - xSpeed || x + xSpeed <= 0) {

			xSpeed = -xSpeed;

		}
		
		x = x + xSpeed;

		if (y >= gameView.getHeight() - height - ySpeed || y + ySpeed <= 0) {

			ySpeed = -ySpeed;

		}

		y = y + ySpeed;
		
		

		//currentFrame = ++currentFrame % BMP_COLUMNS;

	}

	private void updateGamer() {
		
		if(touch)
		{
			x-=width/2;
			y-=height/2;
			touch = false;
		}
		if (x >= gameView.getWidth() - width) {
			
			x = gameView.getWidth() - width;
		}
		
		if (x < 0 ) {
			
			x = 0;
		}
		
		if (y >= gameView.getHeight() - height) {
			y = gameView.getHeight() - height;
		}
		
		if (y < 0) {
			y = 0;
		}
		

	}
	public DodgingPosition onDraw(Canvas canvas) {
		
		
		if(gamer) {
			updateGamer();
		} else {
			update();
		
			
		}
		//Position position = new Position(x+(width/2),y+(height/2),gamer);
		DodgingPosition position = new DodgingPosition(x,y,width,height,gamer);
		//
		//int srcX = currentFrame * width;

		//int srcY = 1 * height;

        // Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);

        //Rect dst = new Rect(x, y, x + width, y + height);

        canvas.drawBitmap(bmp, x, y, null);
       
        
        return position; 
	}

	/*private int getAnimationRow() {

		double dirDouble = (Math.atan2(xSpeed, ySpeed) / (Math.PI / 2) + 2);

		int direction = (int) Math.round(dirDouble) % BMP_ROWS;

		return DIRECTION_TO_ANIMATION_MAP[direction];

	}*/
	
    public void moveGamer(MotionEvent event) {

        x=(int)event.getX();
        y=(int)event.getY();
        touch = true;
          
       
    }

}