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
/*
 * Cette classe représente les différentes méthodes ainsi que le programme principal du robot utilisé pour la compétition.
 */
public class Robot {  
	Sensor sens = new Sensor();
	Actionneurs act = new Actionneurs();
	Delay d = new Delay();

	/**
	 * Démarre le thread de détection de mur.
	 */
    public void startWallDetectionThread() {
        	        Thread wallDetectionThread = new Thread(new Runnable() {
        	            @Override
        	            public void run() {
        	                while (true) {
        	                    if (detectionMur()) {
        	                        eviteObstacle();
        	                    }
        	                    try {
        	                        Thread.sleep(100); 
								} catch (InterruptedException e) {
        	                        e.printStackTrace();
        	                    }
        	                }
        	            }
        	        });
        	        wallDetectionThread.start();
        	    }

	/**
	 * Récupère les mesures à partir d'un angle donné.
	 * 
	 * @param angle l'angle de rotation du robot
	 * @return une liste des mesures obtenues à chaque étape de rotation
	 */
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

	/**
	 * Récupère l'angle de rotation qui correspond à la distance minimale. Retourne 0 si la distance minimale est supérieure à 1.25. Remplace toutes les valeurs inatteignables par 999.
	 * 
	 * @param mesures la liste des mesures obtenues à chaque étape de rotation
	 * @param angle l'angle de rotation du robot
	 * @return l'angle de rotation qui correspond à la distance minimale
	 */
	public int getMin(ArrayList<Float> mesures, int angle) {
		for (int i = 0; i < mesures.size() - 1; i++) {
			if ((Math.abs(mesures.get(i) - mesures.get(i + 1)) < 0.1)||mesures.get(i)<0.3) {
				mesures.set(i,mesures.get(i)+999);
			}
		}
		if (Collections.min(mesures)>1.25) return 0 ;
		return (int)(((float)(mesures.indexOf(Collections.min(mesures)))/mesures.size())*angle);
	}

	/**
	 * Aligne le robot avec le palet le plus proche en utilisant un angle spécifié.
	 * 
	 * @param angle L'angle spécifié pour effectuer la mesure.
	 * @return L'angle de rotation nécessaire pour aligner le robot avec le palet le plus proche.
	 */
	public int alignePaletProche(int angle){
		ArrayList<Float> mesures = getMesures(angle);
		int rota = getMin(mesures,angle);
		act.chassis.setAngularSpeed(400);
		act.rotate((-(angle-rota)),false);
		return rota;
	}

	/**
	 * Vérifie s'il y a un mur détecté à proximité.
	 * 
	 * @return true si un mur est détecté à proximité, sinon false.
	 */
	public boolean detectionMur() {
		return (sens.dist()<0.15);
	}

	/**
	 * Fait tourner le robot de 90 degrés si un obstacle est détecté.
	 */
	public void eviteObstacle() {
		if(detectionMur())act.rotate(90, false);
	}

	/**
	 * Vérifie si le bouton ENTER est pressé.
	 * @return true si le bouton ENTER est pressé, sinon false.
	 */
	public boolean boutonPresse() {
		return Button.ENTER.isDown();
	}

	/**
	 * Programme principal du robot.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Robot robot = new Robot();
		Delay d = new Delay();
		boolean paletDetecte = false;
		boolean obstacle=false;
		robot.act.chassis.setAngularSpeed(400);
		while(true){
			if(Button.ENTER.isDown()) {
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


	while (!paletDetecte) {
		int min = robot.alignePaletProche(360);
		if (min!=0) {
			while (robot.sens.dist() > 0.33) {
				robot.act.rouler2();
			}

			robot.act.stop();
			robot.act.bougerBras(500);

			while (!robot.sens.estTouche()&&!obstacle) {
				robot.act.rouler2();
				if (robot.detectionMur()) {
					obstacle=true;
					robot.act.bougerBras(-500);
				}
				robot.eviteObstacle();
			}
			robot.act.stop();
			if(!obstacle) {
				robot.act.bougerBras(-500);
				Delay.msDelay(100);
				paletDetecte=true ;
				}
			obstacle=false;
		}
		
	else {
			robot.act.rotate(-90, false);
			robot.act.move(60, true);
			while (robot.act.chassis.isMoving()) robot.eviteObstacle();
		}
		
	}
	
		robot.act.rotate((360 - robot.act.rotation) % 360, false);
		robot.act.stop();

		while (robot.sens.dist() > 0.25) {
			robot.act.rouler3();
		}
		robot.act.stop();
		robot.act.bougerBras(500);
		robot.act.stop();
		robot.act.pilot.travel(-400, false);
		robot.act.stop();
		paletDetecte=false;
		robot.act.bougerBras(-500);
		robot.act.stop();
		robot.act.rotate(90, false);
		robot.act.stop();

	}
		}

	}
}
