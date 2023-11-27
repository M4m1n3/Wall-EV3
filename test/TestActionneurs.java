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
		
		//aller au but
		while(capteurs.couleur()!=6.0) {
			//float s3 = capteurs.couleur();
			//System.out.println(s3);
				actionneurs.rouler2();
				Delay.msDelay(100);
			}
		actionneurs.stop();
		actionneurs.bougerBras(500);
		actionneurs.reculer();
		Delay.msDelay(700);
		actionneurs.stop();
		
		//d.msDelay(5000);
		
		}
	}
