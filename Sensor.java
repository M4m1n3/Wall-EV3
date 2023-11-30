package ia;

import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.MeanFilter;

import java.util.HashMap;
import java.util.Map;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;

public class Sensor {

	    private static EV3ColorSensor couleur;
	    private EV3TouchSensor toucher;
	    private static EV3UltrasonicSensor son;
		private static SampleProvider spdist;
		private static float[] sample;
	
	 public Sensor(Port port, Port port3, Port port4){    	
			
			// UltraSonic sensor
			son = new EV3UltrasonicSensor(port);
			spdist = son.getDistanceMode();
			son.enable();
			sample = new float[spdist.sampleSize()];
			spdist.fetchSample(sample, 0);
			// touch Sensor
			toucher = new EV3TouchSensor(port3);
			//colorsensor
			couleur = new EV3ColorSensor(port4);
			couleur.setFloodlight(Color.WHITE);
		
	    }
	
	public float dist() {
		spdist.fetchSample(sample, 0);
		son.close();
		return sample[0];
		
		}

	/* Valeurs limite du capteur ultrasons :
 	distance maximale detection : environ 2.50m
  	distance maximale detection palet : environ 1.20m
   	zone d'incertitude entre 0.20m et 0.33m pour le palet, affiché à 0.326 dans la zone
    	palet non détecté à moins de 0.20m
	*/
	
	
	public boolean estTouche() {
		SampleProvider result = toucher.getTouchMode();
		float[] sample  = new float[1];
		result.fetchSample(sample, 0);
		return sample[0]==1.0;
		}
	
	public float couleur() {
		SampleProvider result = couleur.getColorIDMode();
		float[] sample  = new float[1];
		result.fetchSample(sample, 0);
		return sample[0];
		}
	public void close() {
		son.close();
		couleur.close();
		toucher.close();
	}
	
}
