package com.example.ability;

import java.util.ArrayList;

import java.util.List;


import android.content.Context;

import android.graphics.Bitmap;

import android.graphics.BitmapFactory;

import android.graphics.Canvas;

import android.graphics.Color;

import android.view.MotionEvent;
import android.view.SurfaceHolder;

import android.view.SurfaceView;

public class DodgingView extends SurfaceView {

	private SurfaceHolder holder;

	private DodgingLoopThread gameLoopThread;

	private List<DodgingSprite> sprites = new ArrayList<DodgingSprite>();
	
	private static boolean bc = false;

	public DodgingView(Context context) {

		super(context);

		gameLoopThread = new DodgingLoopThread(this);

		holder = getHolder();

		holder.addCallback(new SurfaceHolder.Callback() {

		
			public void surfaceDestroyed(SurfaceHolder holder) {

				boolean retry = true;

				gameLoopThread.setRunning(false);

				while (retry) {

					try {

						gameLoopThread.join();

						retry = false;

					} catch (InterruptedException e) {

					}

				}

			}


			public void surfaceCreated(SurfaceHolder holder) {

				createSprites();

				gameLoopThread.setRunning(true);

				gameLoopThread.start();

			}

	
			public void surfaceChanged(SurfaceHolder holder, int format,

			int width, int height) {

			}

		});

	}

	private void createSprites() {
		
		sprites.add(createSprite(R.drawable.q1, true));
		for (int i = 0; i <4; ++i)
		{
			sprites.add(createSprite(R.drawable.q1, false));
		}
	}

	private DodgingSprite createSprite(int resouce, boolean gamer) {

		Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);

		return new DodgingSprite(this, bmp, gamer);

	}


	
	
	@Override
	protected void onDraw(Canvas canvas) {
		//int distGamer = (int) Math.sqrt(Math.exp(Math.abs(x-x_gamer)) + Math.exp(Math.abs(y-y_gamer)));
		//if(this.radi < distGamer)
		List<DodgingPosition> positions = new ArrayList<DodgingPosition>();
		DodgingPosition pgamer = null;
		if(bc)
		{
			canvas.drawColor(Color.WHITE);
		} else {
			canvas.drawColor(Color.BLACK);
		}
		
		for (DodgingSprite sprite : sprites) {
			
			DodgingPosition aux = sprite.onDraw(canvas);
			if(aux.gamer)
			{
				pgamer = aux;
			} else {
				positions.add(aux);
			}
		}
		
		if(pgamer != null)
		{
			for( DodgingPosition position : positions) 
			{
				if(pgamer.distMin(position))
				{
					bc = true;
					break;
				} else {
					bc = false;
				}
			}
		}
		

	}
	
	@Override
    public boolean onTouchEvent(MotionEvent event) {

    	sprites.get(0).moveGamer(event);
    	return true;

    }
	


}
