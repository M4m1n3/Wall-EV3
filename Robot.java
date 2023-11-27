package ia;

import java.util.ArrayList;
import java.util.Collections;

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

	/*public void alignePaletProche() {
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
	}*/

	public void alignePaletProche() {
		act.chassis.setAngularSpeed(300);
		float min = 999;
		float dN = sens.dist();
		float dNmoins1;
		ArrayList<Float> mesures = new ArrayList<Float>();
		int indexMin=0;
		act.rotate(360,true);
		while(act.chassis.isMoving()) {
			dNmoins1=dN;
			dN=sens.dist();
			if (dNmoins1-dN>0.1)
				if(dN < min) {
					min=dN;
					//System.out.println(min);
					indexMin=mesures.size();
				}
			mesures.add(dN);
		}
		act.chassis.setAngularSpeed(1000);
		int rota = (int) (((float) indexMin/(mesures.size()-1))*360);
		//System.out.println(rota);
		act.rotate(rota, false);
		d.msDelay(10000);
	}
	public ArrayList<Float> getMesures() {
		act.chassis.setAngularSpeed(300);
		ArrayList<Float> mesures = new ArrayList<Float>();
		float dN = sens.dist();
		act.rotate(360,true);
		while(act.chassis.isMoving()) {
			mesures.add(dN);
			dN = sens.dist();
		}
		return mesures;
	}
	//fct qui si diff<10cm remplacer mesure par 999  
    public int getMin(ArrayList<Float> mesures) {
        for (int i = 0; i < mesures.size() - 1; i++) {
            if (Math.abs(mesures.get(i) - mesures.get(i + 1)) < 0.1) {
                mesures.set(i,999);
            }
        }
        return indexOf(Collections.min(mesures))/(mesures.size()*360);
    }

	    public void alignePaletProche3(){
        ArrayList<Float> mesures = getMesures();
        int rota = getMin(mesures);
        act.rotate(rota,false);
    }


	public boolean detectionMur() {
		return (sens.dist()<20);
	}

	public void eviteObstacle() {
		if(detectionMur())act.rotate(90, false);
	}

	public static void main(String[] args) {
		Robot bot = new Robot();
		bot.alignePaletProche3();
		//bot.act.rotate(360, false);

	}
}
