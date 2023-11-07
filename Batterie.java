package test;
import lejos.hardware.Battery;

public class Batterie {

	public static void main(String[] args) {
		int batteryLevel = Battery.getVoltageMilliVolt();
		System.out.println("Niveau de la batterie : " + batteryLevel + " mV");
	}
}
