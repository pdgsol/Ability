package com.example.ability;

public class DodgingPosition {

	public boolean gamer;
	public Integer x;
	public Integer y;
	private Integer width;
	private Integer height;
	
	public DodgingPosition(Integer x, Integer y, Integer width, Integer height,  boolean gamer)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gamer = gamer;
	}
	
	public boolean distMin(DodgingPosition p)
	{
		/*boolean intersec;
		Integer x2,y2,a1,a2,b1,b2;
		
		x2 = (x + this.width);
		y2 = (y + this.height);
		
		a1 = p.x;
		a2 = (p.x + p.width);
		
		b1 = p.y;
		b2 = (p.y + p.height);
		*/
		
		boolean bx_1 = ((p.x + p.width) > x) && ((p.x + p.width) < (x + this.width));
		boolean bx_2 = (p.x < (x + this.width)) && (p.x > x);
		
		boolean by_1 = ((p.y + p.height) > y) && ((p.y + p.height) < (y + this.height));
		boolean by_2 = (p.y < (y + this.height)) && (p.y > y);
		
		return (bx_1 || bx_2) && (by_1 || by_2);
		
		
		/*double a = Math.abs(this.x-p.x);
		double b = Math.abs(this.y-p.y);
		Integer dist = (int) Math.floor(Math.sqrt((a*a)+(b*b)));
		
		if((dist) > (distMin*0.7))
		{
			intersec = false;
		} else {
			intersec = true;
		}*/
	
	}
}
