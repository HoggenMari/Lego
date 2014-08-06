package Lego;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;


public class Facade {
	
	private PApplet parent;
	private int w;
	private int h;
	private int[][] matrix;
	
	private int x = 15;
	private int y = 15;
	
	private static int BRICK_LENGTH = 4;
	private static int BRICK_WIDTH = 2;
	private static double BRICK_HEIGHT = 1.5;
	private static int RATIO = 10;
	
	int matrixTest[][] = {{0,1,1,1,0},
			  			  //{0,1,0},
			  			  //{0,1,0}
			  			  //{0,1,1,1,1,1,1,0},
			  			  //{0,1,1,1,1,1,1,0},
			  			  //{0,1,1,1,1,1,1,0}
	};
	
	ArrayList<Brick> brickList = new ArrayList<Brick>();
	
	//Brick brickMatrix[][];
	
	String brickMatrix[][];

	private PImage legoBrick;
	
	public int[] getRgbSerial() {
		return rgbSerial;
	}

	private int[] rgbSerial;

	public Facade(PApplet parent, int w, int h) {
		this.parent = parent;
		this.w = w;
		this.h = h;
		this.matrix = new int[w][h];
		this.matrixTest = new int[w][h+2];
		this.rgbSerial = new int[w*h];
		
		for(int ix=0; ix<w; ix++){
			for(int iy=0; iy<h; iy++){
				matrix[ix][iy] = 1;
				matrixTest[ix][iy+1] = 1;
			}
		}
		
		for(int i=0; i<matrixTest.length; i++){
			for(int j=0; j<matrixTest[i].length; j++){
				System.out.println(matrixTest[i][j]);
			}
		}
		
		
		
		computeBricks();
		
		legoBrick = parent.loadImage("le.jpg");
	}
	
	public void map(PImage image) {
		PGraphics pg = parent.g;
		pg.pushMatrix();
		pg.background(120);
		pg.translate(x, y);
		pg.noStroke();
		/*for(int ix=0; ix<w; ix++){
			for(int iy=0; iy<h; iy++){
				
				pg.beginShape();
				pg.stroke(230,200);
				pg.texture(legoBrick);

				pg.vertex(ix*BRICK_LENGTH*RATIO, (float) (iy*BRICK_HEIGHT*RATIO), 0, 0, 0);
				pg.vertex(ix*BRICK_LENGTH*RATIO, (float) ((float) (iy*BRICK_HEIGHT*RATIO)+BRICK_HEIGHT*RATIO), 0, BRICK_LENGTH*RATIO, 0);
				pg.vertex(ix*BRICK_LENGTH*RATIO+BRICK_LENGTH*RATIO, (float) ((float) (iy*BRICK_HEIGHT*RATIO)+BRICK_HEIGHT*RATIO), 0, BRICK_LENGTH*RATIO, (float) BRICK_HEIGHT*RATIO);
				pg.vertex(ix*BRICK_LENGTH*RATIO+BRICK_LENGTH*RATIO, (float) (iy*BRICK_HEIGHT*RATIO), 0, 0, (float) BRICK_HEIGHT*RATIO);
				pg.endShape();
				pg.fill(image.get(ix, iy),100);
				pg.rect(ix*BRICK_LENGTH*RATIO,(float) (iy*BRICK_HEIGHT*RATIO),BRICK_LENGTH*RATIO,(float) (BRICK_HEIGHT*RATIO));
							}
		}*/
		
		for(Brick b : brickList){
			if(b.getClass().toString().contains("class Lego.LedBrick")){
				LedBrick led = (LedBrick) b;
				pg.beginShape();
				pg.stroke(230,200);
				pg.texture(legoBrick);
				pg.vertex(led.p1.x*BRICK_LENGTH*RATIO, (float) (led.p1.y*BRICK_HEIGHT*RATIO), 0, 0, 0);
				pg.vertex(led.p1.x*BRICK_LENGTH*RATIO, (float) ((float) (led.p1.y*BRICK_HEIGHT*RATIO)+BRICK_HEIGHT*RATIO), 0, BRICK_LENGTH*RATIO, 0);
				pg.vertex(led.p1.x*BRICK_LENGTH*RATIO+BRICK_LENGTH*RATIO, (float) ((float) (led.p1.y*BRICK_HEIGHT*RATIO)+BRICK_HEIGHT*RATIO), 0, BRICK_LENGTH*RATIO, (float) BRICK_HEIGHT*RATIO);
				pg.vertex(led.p1.x*BRICK_LENGTH*RATIO+BRICK_LENGTH*RATIO, (float) (led.p1.y*BRICK_HEIGHT*RATIO), 0, 0, (float) BRICK_HEIGHT*RATIO);
				pg.endShape();
				if(led.cType==0){
					pg.stroke(230,200);
					pg.line(led.p1.x*BRICK_LENGTH*RATIO, (float) (led.p1.y*BRICK_HEIGHT*RATIO), led.p1.x*BRICK_LENGTH*RATIO+BRICK_LENGTH*RATIO, (float) ((float) (led.p1.y*BRICK_HEIGHT*RATIO)+BRICK_HEIGHT*RATIO));
				}else{
					pg.stroke(230,200);
					pg.line(led.p1.x*BRICK_LENGTH*RATIO+BRICK_LENGTH*RATIO, (float) (led.p1.y*BRICK_HEIGHT*RATIO), led.p1.x*BRICK_LENGTH*RATIO, (float) ((float) (led.p1.y*BRICK_HEIGHT*RATIO)+BRICK_HEIGHT*RATIO));
				}
				pg.stroke(230,200);
				pg.fill(image.get((int)led.p1.x, (int)led.p1.y-1),100);
				pg.rect(led.p1.x*BRICK_LENGTH*RATIO,(float) (led.p1.y*BRICK_HEIGHT*RATIO),BRICK_LENGTH*RATIO,(float) (BRICK_HEIGHT*RATIO));
			}else if(b.getClass().toString().contains("class Lego.ConnectorBrick")){
			ConnectorBrick con = (ConnectorBrick) b;
				if(con.getType()==0){
					pg.stroke(230,200);
					pg.fill(255,255);
					pg.rect(con.p1.x*BRICK_LENGTH*RATIO,(float) (con.p1.y*BRICK_HEIGHT*RATIO),BRICK_LENGTH*RATIO,(float) (BRICK_HEIGHT*RATIO));
					pg.fill(255,255);
					pg.rect(con.p2.x*BRICK_LENGTH*RATIO,(float) (con.p2.y*BRICK_HEIGHT*RATIO),BRICK_LENGTH*RATIO,(float) (BRICK_HEIGHT*RATIO));
				}else if(con.getType()==1){
					pg.stroke(230,200);
					pg.fill(255,255);
					pg.rect(con.p1.x*BRICK_LENGTH*RATIO,(float) (con.p1.y*BRICK_HEIGHT*RATIO),BRICK_LENGTH*RATIO,(float) (BRICK_HEIGHT*RATIO));
					pg.fill(255,255);
					pg.rect(con.p2.x*BRICK_LENGTH*RATIO,(float) (con.p2.y*BRICK_HEIGHT*RATIO),BRICK_LENGTH*RATIO,(float) (BRICK_HEIGHT*RATIO));
				}else if(con.getType()==2){
					pg.stroke(230,200);
					pg.fill(255,255);
					pg.rect(con.p1.x*BRICK_LENGTH*RATIO,(float) (con.p1.y*BRICK_HEIGHT*RATIO),BRICK_LENGTH*RATIO,(float) (BRICK_HEIGHT*RATIO));
					pg.fill(255,255);
					pg.rect(con.p2.x*BRICK_LENGTH*RATIO,(float) (con.p2.y*BRICK_HEIGHT*RATIO),BRICK_LENGTH*RATIO,(float) (BRICK_HEIGHT*RATIO));
				}else if(con.getType()==3){
					pg.stroke(230,200);
					pg.fill(255,255);
					pg.rect(con.p1.x*BRICK_LENGTH*RATIO,(float) (con.p1.y*BRICK_HEIGHT*RATIO),2*BRICK_LENGTH*RATIO,(float) (BRICK_HEIGHT*RATIO));				}
			}
		}
		pg.popMatrix();
		
		
		
		writeSerial(image);
	}
	
	private void writeSerial(PImage image){
		int ctr=0;
		for(int ix=0; ix<w; ix++){
			for(int iy=h-1; iy>=0; iy--){
				if(matrix[ix][iy]==1){
					rgbSerial[ctr] = image.get(ix, iy);
					ctr++;
				}
			}
		}
	}
	
	private void computeBricks(){
		/*int start=-1;
		boolean lost = false;
		PVector lostPoint = new PVector(0,0);
		brickMatrix = new String[6][8];
		for(int ix=0; ix<matrixTest.length; ix++){
			if(ix%2==1){
				for(int iy=0; iy<matrixTest[ix].length; iy++){
					if(matrixTest[ix][iy]==1){
						if(lost){
							lost = false;
							System.out.println("Connect: "+(int)lostPoint.x+" "+(int)lostPoint.y+" mit "+ix+" "+iy);
							brickMatrix[ix][iy] = "ConnectInput "+ix+" "+iy;
							brickMatrix[ix][iy+1] = "LedBrick "+ix+" "+(iy+1);
						}else{
							brickMatrix[ix][iy] = "LedBrick "+ix+" "+(iy+1);
						}
					}else{
						if(!lost){
							System.out.println("Lost");
							lostPoint = new PVector(ix, iy-1);
							lost = true;
							brickMatrix[ix][iy] = "ConnectOutput "+ix+" "+iy;
						}else{
							brickMatrix[ix][iy] = "NormalBrick "+ix+" "+iy;
						}
					}
				}
				if(!lost){
					brickMatrix[ix][6] = "ConnectOutput "+ix+" "+6;
					lost = true;
				}
			}else{
				for(int iy=matrixTest[ix].length-1; iy>=0; iy--){
					if(matrixTest[ix][iy]==1){
						if(start==-1){
							start=matrixTest[ix][iy];
							System.out.println("Start: "+ix+" "+iy+1);
							brickMatrix[ix][iy+2] = "Input "+ix+" "+(iy+2);
							brickMatrix[ix][iy+1] = "ledBrick "+ix+" "+(iy+1);
						}else if(lost){
							lost = false;
							System.out.println("Connect: "+(int)lostPoint.x+" "+(int)lostPoint.y+" mit "+ix+" "+iy);
							brickMatrix[ix][iy+2] = "ConnectInput "+ix+" "+(iy+2);
							brickMatrix[ix][iy+1] = "LedBrick "+ix+" "+(iy+1);
						}else{
							brickMatrix[ix][iy+1] = "LedBrick "+ix+" "+(iy+1);
						}
					}else{
						if(!lost){
							System.out.println("Lost");
							lostPoint = new PVector(ix, iy-1);
							lost = true;
							brickMatrix[ix][iy+1] = "ConnectOutput "+ix+" "+(iy+1);
						}else{
							brickMatrix[ix][iy+1] = "NormalBrick "+ix+" "+(iy+1);	
						}
					}	
				}
				if(!lost){
					brickMatrix[ix][0] = "ConnectOutput "+ix+" "+0;
					lost = true;
				}
			}
		}*/
		boolean lost = false;
		PVector lostPoint = new PVector(0,0);
		for(int ix=0; ix<matrixTest.length; ix++){
			if(ix%2==0){
				for(int iy=matrixTest[ix].length-1; iy>=0; iy--){
					if(matrixTest[ix][iy]==1){
						if(lost){
							lost=false;
							brickList.add(new ConnectorBrick(lostPoint, new PVector(ix,iy+1)));
							brickList.add(new LedBrick(new PVector(ix,iy)));
						}else{
							brickList.add(new LedBrick(new PVector(ix,iy)));
						}
					}else{
						if(!lost){
							lost=true;
							lostPoint = new PVector(ix,iy);
						}
					}
				}
			}else{
				for(int iy=0; iy<matrixTest[ix].length; iy++){
					if(matrixTest[ix][iy]==1){
						if(lost){
							lost=false;
							brickList.add(new ConnectorBrick(lostPoint, new PVector(ix,iy-1)));
							brickList.add(new LedBrick(new PVector(ix,iy)));
						}else{
							brickList.add(new LedBrick(new PVector(ix,iy)));
						}
					}else{
						if(!lost){
							lost=true;
							lostPoint = new PVector(ix,iy);
						}
					}
				}
			}
			
		}
		
		for(Brick b : brickList){
			System.out.println(b.toString());
		}
		/*for(int ix=0; ix<brickMatrix.length; ix++){
			for(int iy=0; iy<brickMatrix[ix].length; iy++){
				System.out.println(brickMatrix[ix][iy]);
			}
			
		}*/
	}



}
