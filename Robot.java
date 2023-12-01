import java.util.ArrayList;
import java.util.Collections;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.chassis.Chassis;
import lejos.utility.Delay;
import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;

public class Robot {  
	Sensor sens = new Sensor();
	Actionneurs act = new Actionneurs();
	Delay d = new Delay();

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
	public ArrayList<Float> getMesures(int angle) {
		act.chassis.setAngularSpeed(300);
		ArrayList<Float> mesures = new ArrayList<Float>();
		float dN = sens.dist();
		act.rotate(angle,true);
		while(act.chassis.isMoving()) {
			mesures.add(dN);
			dN = sens.dist();
		}
		return mesures;
	}
	//fct qui si diff<10cm remplacer mesure par 999  
	public int getMin(ArrayList<Float> mesures, int angle) {
		for (int i = 0; i < mesures.size() - 1; i++) {
			if (Math.abs(mesures.get(i) - mesures.get(i + 1)) < 0.1) {
				mesures.set(i,mesures.get(i)+999);
			}
		}
		if (Collections.min(mesures)>1.25) return 0 ;
		return (int)(((float)(mesures.indexOf(Collections.min(mesures)))/mesures.size())*angle);
	}

	public int alignePaletProche3(int angle){
		ArrayList<Float> mesures = getMesures(angle);
		int rota = getMin(mesures,angle);
		act.chassis.setAngularSpeed(1000);
		act.rotate((-(angle-rota)),false);
		return rota;
	}

	public boolean detectionMur() {
		return (sens.dist()<20);
	}

	public void eviteObstacle() {
		if(detectionMur())act.rotate(90, false);
	}
	public boolean boutonPresse() {
		return Button.ENTER.isDown();
	}

	public static void main(String[] args) {
		Robot robot = new Robot();
		Delay d = new Delay();
		boolean paletDetecte = false;
		while (robot.sens.dist() > 0.33) {
			robot.act.rouler2();
		}

		robot.act.stop();
		robot.act.bougerBras(600);

		while (!robot.sens.estTouche()) {
			robot.act.rouler2();
		}

		robot.act.stop();
		robot.act.bougerBras(-600);
		robot.act.stop();
		robot.act.rotate(100, false);
		robot.act.stop();
		robot.act.move(20, false);
		robot.act.stop();
		robot.act.rotate(-90, false);

		//aller au but
		while (robot.sens.dist() > 0.25) {
			robot.act.rouler3();
		}
    
		robot.act.stop();
		robot.act.bougerBras(600);
		robot.act.stop();
		robot.act.pilot.travel(-300, false);
		robot.act.stop();
		robot.act.bougerBras(-500);
		robot.act.stop();
		robot.act.rotate(90, false);
		robot.act.stop();

		while(!robot.boutonPresse()){

				
	while (!paletDetecte) {
		int min = robot.alignePaletProche3(180);
		if (min!=0) {
			while (robot.sens.dist() > 0.33) {
				robot.act.rouler2();
			}

			robot.act.stop();
			robot.act.bougerBras(500);

			while (!robot.sens.estTouche()) {
				robot.act.rouler2();
			}
			robot.act.stop();
			robot.act.bougerBras(-500);
			Delay.msDelay(100);
			paletDetecte=true ;	
		}
		
	else {
			robot.act.rotate(-90, false);
			robot.act.move(60, false);
		}
		
	}
	
		robot.act.rotate((360 - robot.act.rotation) % 360, false);
		robot.act.stop();

		while (robot.sens.dist() > 0.25) {
			robot.act.rouler3();
		}
		robot.act.stop();
		robot.act.bougerBras(600);
		robot.act.stop();
		robot.act.pilot.travel(-600, false);
		robot.act.stop();
		robot.act.bougerBras(-600);
		robot.act.stop();
		robot.act.rotate(90, false);
		robot.act.stop();

	}
		robot.act.stop();

	}
}
