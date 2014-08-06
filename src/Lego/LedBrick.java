package Lego;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PVector;

public class LedBrick implements Brick{
	public PVector p1;
	byte cType;
	
	public LedBrick(PVector p1){
		this.p1 = p1;
		if(p1.y%2==0){
			cType = 0;	
		}else{
			cType = 1;
		}
	}
	
	public String toString(){
		String s = "LEDBRICK "+p1.x+" "+p1.y;
		return s;	
	}

	@Override
	public void draw(PGraphics pg) {

	}
}
