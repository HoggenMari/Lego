package Lego;

import java.util.ArrayList;

import processing.core.*;
import processing.serial.Serial;

public class LegoMedia {

	private PApplet parent;
	
	private ArrayList<Facade> facades;

	public LegoMedia(PApplet parent){
		this.parent = parent;
		
		facades = new ArrayList<Facade>();
	}
	
	public Facade createCornerPinSurface(int w, int h) {
		Facade s = new Facade(parent, w, h);
		facades.add(s);
		return s;
	}
	

	public void send(Serial port) {
		for(Facade f : facades){
			int rgbSerial[] = f.getRgbSerial();
			byte data[] = new byte[rgbSerial.length*4];
			String s = "";
			for(int i=0; i<3; i++){
				s +=  '#';
				s +=  Integer.toHexString(rgbSerial[i] & 0xFFFFFF);
				//s +=  Integer.toHexString((int) parent.green(parent.color(rgbSerial[i])));
				//s +=  Integer.toHexString((int) parent.blue(parent.color(rgbSerial[i])));;

				//data[i*4+2] = (byte) (rgbSerial[i] >> 8 & 0xFF);
				//data[i*4+3] = (byte) (rgbSerial[i] >> 0xFF);
			}
			s += 'n';
			port.write(s);
			//String s = "";
			/*for(byte b : data){
				s += b+" ";
			}*/
			//System.out.println(s);
		}
	}
}
