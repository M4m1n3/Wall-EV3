package ia;

import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;

/**
 * Cette classe représente les capteurs utilisés par le robot Wall-EV3.
 * Elle permet d'interagir avec les capteurs ultrasoniques, de couleur et de contact.
 */
public class Sensor {
	EV3UltrasonicSensor son;
	EV3ColorSensor couleur;
	EV3TouchSensor toucher;
	
	/**
	 * Constructeur de la classe Sensor.
	 * Initialise les capteurs ultrasoniques, de couleur et de contact.
	 */
	public Sensor() {
		this.son = new EV3UltrasonicSensor(LocalEV3.get().getPort("S1"));
		this.couleur = new EV3ColorSensor(LocalEV3.get().getPort("S4"));
		this.toucher = new EV3TouchSensor(LocalEV3.get().getPort("S3"));
	}
	
	/**
	 * Renvoie la distance mesurée par le capteur ultrasonique.
	 * @return La distance mesurée en milimètres.
	 */
	public float dist() {
		SampleProvider spdist = son.getMode("Distance");
		float[] sample = new float[spdist.sampleSize()];
		spdist.fetchSample(sample, 0);
		return sample[0];
	}
	
	/**
	 * Vérifie si le capteur de contact est activé.
	 * @return true si le capteur de contact est activé, false sinon.
	 */
	public boolean estTouche() {
		SampleProvider result = toucher.getTouchMode();
		float[] sample  = new float[1];
		result.fetchSample(sample, 0);
		return sample[0] == 1.0;
	}
	
	/**
	 * Renvoie l'identifiant de couleur détecté par le capteur de couleur.
	 * @return L'identifiant de couleur.
	 */
	public float couleur() {
		SampleProvider result = couleur.getColorIDMode();
		float[] sample  = new float[1];
		result.fetchSample(sample, 0);
		return sample[0];
	}
	
	/**
	 * Ferme les connexions avec les capteurs.
	 */
	public void close() {
		son.close();
		couleur.close();
		toucher.close();
	}
}
