import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class TestActionneurs {
	public static void main(String[] args) {
		// Créez une instance de la classe Actionneurs
		Actionneurs actionneurs = new Actionneurs();

		// Testez les méthodes de la classe Actionneurs
		System.out.println("Avancer...");
		actionneurs.rouler();

		// Attendez quelques secondes pour voir les moteurs en action
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Arrêter...");
		actionneurs.arreter();

		// Attendez à nouveau


		//    System.out.println("Reculer...");
		//      actionneurs.reculer();

		// Attendez à nouveau
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Rotation horaire de 90 degrés...");
		actionneurs.tourner(true,1);
		System.out.println("Avancer...");
		actionneurs.rouler();
		System.out.println("Rotation antihoraire de -360 degrés...");
		actionneurs.tourner(true,-1);

		// Attendez à nouveau
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try { 
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 

		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Mouvement du bras de -1100 degrés...");
		actionneurs.bougerBras(1100);
		actionneurs.bougerBras(-1100);

		// Attendez à nouveau
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Arrêtez tous les moteurs à la fin
		actionneurs.arreter();
	}
}
