import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;
import java.util.ArrayList;
import java.util.Collections;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.chassis.Chassis;
import lejos.utility.Delay;
import lejos.hardware.Button;

public class TestActionneurs {
	public static void main(String[] args) {
	Actionneurs act = new Actionneurs();
	Sensor sens = new Sensor();
	Delay d = new Delay();
	
		act.attrapePremierPalet();
		//jusque là cava, il attrape premier, recule de 0.1sec et tourne 90deg
	/*	
		actionneurs.alignePaletProche3(180);
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
		actionneurs.rotate((360-actionneurs.rotation)%360, false);
		actionneurs.stop();
		while(capteurs.dist()>0.25) {
			actionneurs.rouler3();
		}
		actionneurs.stop();
		*/
	//	actionneurs.bougerBras(500);
		//actionneurs.reculer();
		//Delay.msDelay(700);
		//actionneurs.stop();
		
		//d.msDelay(5000);
		
		}
	

	}
