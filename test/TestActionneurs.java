import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class TestActionneurs {
	public static void main(String[] args) {
	Actionneurs actionneurs = new Actionneurs();
	Sensor capteurs = new Sensor();
	Delay d = new Delay();
//refaire sans rouler mais avec travel
		while(capteurs.dist()>0.33) {
			actionneurs.rouler2();
		}
		actionneurs.stop();
		
		actionneurs.bougerBras(600);
		while(!capteurs.estTouche()) {
			actionneurs.rouler2();
		}
		actionneurs.stop();
		actionneurs.bougerBras(-500);
		Delay.msDelay(100);
		
		//aller au but
		while(capteurs.dist()>0.25) {
			actionneurs.rouler3();
		}
		actionneurs.stop();
		actionneurs.bougerBras(600);
		actionneurs.stop();
		
    	actionneurs.pilot.travel(-500,false);
		
		actionneurs.stop();
		actionneurs.bougerBras(-600);
		actionneurs.stop();
		

	//	actionneurs.bougerBras(500);
		//actionneurs.reculer();
		//Delay.msDelay(700);
		//actionneurs.stop();
		
		//d.msDelay(5000);
		
		}
	}
