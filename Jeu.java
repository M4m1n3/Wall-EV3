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

public class Jeu {
	Actionneurs act = new Actionneurs();
	Sensor sens = new Sensor();
	Delay d = new Delay();
	
	public static void main(String[] args) {

		//refaire sans rouler mais avec travel
		act.attrapePremierPalet();
		//jusque là cava, il attrape premier, recule de 0.1sec et tourne 90deg
		
		act.alignePaletProche3(180);
		while(sens.dist()>0.33) {
			act.rouler2();
		}
		act.stop();
		act.bougerBras(600);
		while(!sens.estTouche()) {
			act.rouler2();
		}
		act.stop();
		act.bougerBras(-500);
		Delay.msDelay(100);
		act.rotate((360-act.rotation)%360, false);
		act.stop();
		while(sens.dist()>0.25) {
			act.rouler3();
		}
		act.stop();
		/*
		act.bougerBras(500);
		act.reculer();
		Delay.msDelay(700);
		act.stop();

		Delay.msDelay(5000);
		*/
		
		}
	

	}
