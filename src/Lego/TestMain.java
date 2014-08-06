package Lego;

import processing.core.*;
import processing.serial.Serial;


public class TestMain extends PApplet {
	
	private LegoMedia lM;
	
	private Facade f1;
	
	private PGraphics offscreen;

	private Serial port;

	private int coleur;

	public void setup() {
		
		size(400, 300, P3D);
		
		frameRate(50);
		
		String portName = Serial.list()[6];
		for(String s : Serial.list()){
		System.out.println(s);
		}
		port = new Serial(this, portName, 115200);

		
		lM = new LegoMedia(this);
		
		f1 = lM.createCornerPinSurface(2, 3);
		
		offscreen = createGraphics(2, 3);
		

	}
	
	public void draw() {
		
		background(120);
		offscreen.beginDraw();
		//offscreen.background(255,255);
		offscreen.noStroke();
		//for(int i=0; i<5; i++){
		//offscreen.fill(random(0,255),random(0,255),random(0,255));
		//offscreen.rect(random(0,1), random(0,3),1,1);
		//}
		offscreen.fill(0,255-coleur,coleur,255);
		offscreen.rect(0,0,1,1);
		offscreen.fill(0,coleur,255-coleur,255);
		offscreen.rect(0,1,1,1);
		offscreen.fill(0,255-coleur,coleur,255);
		offscreen.rect(0,2,1,1);
		//offscreen.background(10,10,10);
		
		if(coleur<255){
			coleur++;
		}else{
			coleur = 0;
		}
		offscreen.endDraw();
		f1.map(offscreen);
		
		lM.send(port);
		
	}

	//Initiate as Application
	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "TestMain" });
	}
	
}
