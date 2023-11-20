package ia;

import java.util.ArrayList;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.chassis.Chassis;
import lejos.utility.Delay;

public class Robot {  
	Sensor sens = new Sensor();
	Actionneurs act = new Actionneurs();
	Delay d = new Delay();
	
	public void alignePaletProche() {
		float min=999;
		float dN=sens.dist();
		float dNmoins1;
		int rotaTotale=0,rotaDepuisMin=0;
		while (rotaTotale<360){
			act.rotate(5,false);
			dNmoins1=dN;
			dN = sens.dist();
			if (Math.abs(dNmoins1-dN)>0.04)
				if(dN < min) {
					min=dN;
					rotaDepuisMin=0;
				}
			rotaTotale+=5;
			rotaDepuisMin+=5;
			d.msDelay(5);
		}
			act.rotate(360-rotaDepuisMin,false);
	}
	
	public void alignePaletProcheU() {
		act.chassis.setAngularSpeed(250);
		float min = 999;
		float dN = sens.dist();
		float dNmoins1;
		ArrayList<Float> mesures = new ArrayList<Float>();
		int indexMin=0;
		act.rotate(360,true);
		while(act.chassis.isMoving()) {
			dNmoins1=dN;
			dN=sens.dist();
			if (Math.abs(dNmoins1-dN)>0.04)
				if(dN < min) {
					min=dN;
					indexMin=mesures.size();
				}
			mesures.add(dN);
		}
		System.out.println(min);
		d.msDelay(1000);
		act.chassis.setAngularSpeed(1000);
		int rota = ((indexMin/(mesures.size()-1))*360);
		act.rotate(rota, false);
		//act.rotate(360, false);
	}
	
	
	public boolean detectionMur() {
        return (sens.dist()<20);
    }
	
	public void eviteObstacle() {
		if(detectionMur())act.rotate(90, false);
	}
	
	public static void main(String[] args) {
		Robot bot = new Robot();
		bot.alignePaletProcheU();
		//bot.act.rotate(360, false);

	}
}
