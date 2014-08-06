package Lego;

import processing.core.PGraphics;
import processing.core.PVector;

public class ConnectorBrick implements Brick {
	
	PVector p1;
	PVector p2;
	private byte type;

	public ConnectorBrick(PVector p1, PVector p2){
		this.p1 = p1;
		this.p2 = p2;
		if(p1.x==p2.x){
			System.out.println("go1");
			if(Math.abs(p1.y-p2.y)<=2){
				type = 0;
				System.out.println("type0");
			}
			else{
				type = 1;
				System.out.println("type1");
			}
		}else{
			System.out.println("go2");
			if(p1.y!=p2.y){
				type = 2;
				System.out.println("type2");
			}
			else{
				type = 3;
				System.out.println("type3");
			}
		}
	}
	
	public String toString(){
		String s = "CONNECTORBRICK "+p1.x+" "+p1.y+" to "+p2.x+" "+p2.y+" TYPE:"+type;
		return s;	
	}
	
	public byte getType(){
		return type;
	}

	@Override
	public void draw(PGraphics pg) {
		// TODO Auto-generated method stub
		
	}
	

}
