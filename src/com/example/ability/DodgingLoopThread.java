package com.example.ability;

import android.graphics.Canvas;

public class DodgingLoopThread extends Thread {

	static final long FPS = 10;

	private DodgingView view;

	private boolean running = false;

	public DodgingLoopThread(DodgingView view) {

		this.view = view;

	}

	public void setRunning(boolean run) {

		running = run;

	}

	@Override
	public void run() {

		long ticksPS = 1000 / FPS;

		long startTime;

		long sleepTime;

		while (running) {

			Canvas c = null;

			startTime = System.currentTimeMillis();

			try {

				c = view.getHolder().lockCanvas();

				synchronized (view.getHolder()) {

					view.onDraw(c);

				}

			} finally {

				if (c != null) {

					view.getHolder().unlockCanvasAndPost(c);

				}

			}

			sleepTime = ticksPS - (System.currentTimeMillis() - startTime);

			try {

				if (sleepTime > 0)

					sleep(sleepTime);

				else

					sleep(10);

			} catch (Exception e) {
			}

		}

	}

}