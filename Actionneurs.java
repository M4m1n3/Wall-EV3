import java.util.ArrayList; 
import java.util.Collections;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class Actionneurs {
    EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
    EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);
    EV3LargeRegulatedMotor bras = new EV3LargeRegulatedMotor(MotorPort.D);
    // objets Wheel pour les roues gauche et droite
    Wheel roueGauche = WheeledChassis.modelWheel(leftMotor, 43.2).offset(-7.5);
    Wheel roueDroite = WheeledChassis.modelWheel(rightMotor, 43.2).offset(7.5);
    Chassis chassis = new WheeledChassis(new Wheel[]{roueGauche, roueDroite}, WheeledChassis.TYPE_DIFFERENTIAL);
    MovePilot pilot = new MovePilot(chassis);
    public int rotation = 0;
	public int rotationBras = 0;
    public MovePilot getPilot() {
        return this.pilot;
    }

	public void rouler1() {
		rightMotor.startSynchronization();
		rightMotor.synchronizeWith(new EV3LargeRegulatedMotor[] {leftMotor});
		rightMotor.setSpeed(200);
		leftMotor.setSpeed(200);
		rightMotor.forward();
		leftMotor.forward();
		rightMotor.endSynchronization();
	}
	
	public void rouler2() {
		rightMotor.startSynchronization();
		rightMotor.synchronizeWith(new EV3LargeRegulatedMotor[] {leftMotor});
		rightMotor.setSpeed(400);
		leftMotor.setSpeed(400);
		rightMotor.forward();
		leftMotor.forward();
		rightMotor.endSynchronization();
	}
	
	public void rouler3() {
		
		rightMotor.startSynchronization();
		rightMotor.synchronizeWith(new EV3LargeRegulatedMotor[] {leftMotor});
		rightMotor.setSpeed(800);
		leftMotor.setSpeed(800);
		rightMotor.forward();
		leftMotor.forward();
		rightMotor.endSynchronization();
	}
	
	
	public void move(int distance, boolean immediateReturn) {
		// 1cm = 8
		pilot.travel(distance*8, immediateReturn);
	}
	public void stop() {
		pilot.stop();
	}
	public void reculer() {
		rightMotor.startSynchronization();
		rightMotor.synchronizeWith(new EV3LargeRegulatedMotor[] {leftMotor});
		rightMotor.setSpeed(400);
		leftMotor.setSpeed(400);
		rightMotor.backward();
		leftMotor.backward();
		rightMotor.endSynchronization();
	}
	public void rotate(int angle, boolean retourImmediat) {
		// 360 degres = 2110
		pilot.rotate(angle * 2110 / 360, retourImmediat);
		rotation=(rotation+angle)%360;
	}
    public void bougerBras(int angle) {
    	bras.rotate(angle);
    }
	public void setNiveauVitesse(String v) {
		if (v == "rapide") {
			pilot.setLinearAcceleration(300);
			pilot.setAngularAcceleration(2000);
			pilot.setAngularSpeed(1000);
		}
		else if (v == "moyen") {
			pilot.setLinearAcceleration(150);
			pilot.setAngularAcceleration(1000);
		}
		else if (v == "lent"){
			pilot.setLinearAcceleration(75);
			pilot.setAngularAcceleration(150);
		}
		else if (v == "opti"){
            pilot.setLinearAcceleration(115);
            pilot.setAngularAcceleration(400);
            pilot.setAngularSpeed(275);
            
        }
	}
	
    public void accelerer(int vitesse) {
    	rightMotor.setAcceleration(vitesse);
    	leftMotor.setAcceleration(vitesse);
		pilot.setLinearAcceleration(vitesse);
		pilot.setAngularAcceleration(vitesse);
		pilot.setAngularSpeed(vitesse);
    }
    

    
 
}
