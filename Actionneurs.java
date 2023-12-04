package ia;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;
/**
 * Cette classe représente les moteurs et les mouvements du robot.
 * Elle permet au robot d'avancer, de reculer, de tourner, de s'arrêter, de bouger les bras et d'accélérer.
 * La classe contient des méthodes pour contrôler les mouvements du robot et récupérer son pilote.
 */

public class Actionneurs {
	EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
	EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);
	EV3LargeRegulatedMotor bras = new EV3LargeRegulatedMotor(MotorPort.D);
	Wheel roueGauche = WheeledChassis.modelWheel(leftMotor, 43.2).offset(-7.5);
	Wheel roueDroite = WheeledChassis.modelWheel(rightMotor, 43.2).offset(7.5);
	Chassis chassis = new WheeledChassis(new Wheel[] { roueGauche, roueDroite }, WheeledChassis.TYPE_DIFFERENTIAL);
	MovePilot pilot = new MovePilot(chassis);
	//l'attribut rotation va permettre de cumuler toutes les rotations pour connaitre l'angle du robot
	public int rotation = 0;
	//public int rotationBras = 0;
	//static int brasOuverts=1100;
	//static int brasFermes=-1100;

	/*
	 * Fonction qui permet de récupérer le pilote du robot
	 */
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

	/*
	 * Fonction qui permet d'arreter l'avancée du robot
	 * 
	 */
	public void stop() {
		pilot.stop();
	}
	/*Peut etre a modifier
	 * Fonction qui permet de faire reculer le robot
	 */
	public void reculer() {
		rightMotor.startSynchronization();
		rightMotor.synchronizeWith(new EV3LargeRegulatedMotor[] { leftMotor });
		rightMotor.backward();
		leftMotor.backward();
		rightMotor.endSynchronization();
	}

	/*
	 * Fonction qui permet de faire tourner le robot à un angle donné
	 * 
	 * @param angle: angle de rotation du robot
	 * 
	 * @param retourImmediat: true si le robot doit s'arrêter dès que l'angle est
	 * atteint, false sinon
	 */
	public void rotate(int angle, boolean retourImmediat) {
		// 360 degres = 2110
		pilot.rotate(angle * 2210 / 360, retourImmediat);
		rotation=(rotation+angle)%360;
	}

	/*
	 * Fonction qui permet d'ouvrir ou fermer les bras du robot
	 * 
	 * @param angle: brasOuverts ou brasFermes cf constantes
	 */
	public void bougerBras(int angle) {
		bras.rotate(angle);
		//rotationBras+=angle;
	}
	/*public void fermerBras(){
		bras.rotate(brasFermes-rotationBras);
		rotationBras=0;
	}
	public void ouvrirBras(){
		bras.rotate(brasOuverts-rotationBras);
		rotationBras=1100;
	}*/
	/*
	 * à modifier via mesures
	 * Fonction qui permet de faire tourner les roues à une vitesse donnée
	 * 
	 * @param vitesse: vitesse de rotation des roues
	 * 
	 */
	public void accelerer(int vitesse) {
		pilot.setLinearAcceleration(vitesse);
		pilot.setAngularAcceleration(vitesse);
		pilot.setAngularSpeed(vitesse);
	}

}
