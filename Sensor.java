package ia;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;

public class Sensor {
	EV3UltrasonicSensor son;
	EV3ColorSensor couleur;
	EV3TouchSensor toucher;
	
	public Sensor() {
		this.son = new EV3UltrasonicSensor(LocalEV3.get().getPort("S1"));
		this.couleur = new EV3ColorSensor(LocalEV3.get().getPort("S4"));
		this.toucher = new EV3TouchSensor(LocalEV3.get().getPort("S3"));
	}
	
	public float dist() {
		SampleProvider spdist = son.getMode("Distance");
		float[] sample = new float[spdist.sampleSize()];
		spdist.fetchSample(sample, 0);
		return sample[0];}

	/* Valeurs limite du capteur ultrasons :
 	distance maximale detection : environ 2.50m
  	distance maximale detection palet : environ 1.20m
   	zone d'incertitude entre 0.20m et 0.33m pour le palet, affiché à 0.326 dans la zone
    	palet non détecté à moins de 0.20m
	*/
	
	
	public float estTouche() {
		SampleProvider result = toucher.getTouchMode();
		float[] sample  = new float[1];
		result.fetchSample(sample, 0);
		return sample[0];
		}
	
	public float couleur() {
		SampleProvider result = couleur.getColorIDMode();
		float[] sample  = new float[1];
		result.fetchSample(sample, 0);
		return sample[0];
		}

	
}
