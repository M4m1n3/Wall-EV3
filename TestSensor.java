package ia;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.*;
import lejos.utility.Delay;
public class TestSensor {

	public static void main(String[] args) {
		Sensor robot = new Sensor();
		Delay d = new Delay();
		float s1,s3;
		char s2;
		for (int i = 0; i<30; i++) {
			//s1 = robot.dist();
			//s2 = robot.estTouche();
			s3 = robot.couleur();
			//System.out.print(s1);
			//System.out.print(" ");
			//System.out.print(s2);
			//System.out.print(" ");
			System.out.print(s3);
			System.out.println();
			d.msDelay(1000);
		}
		d.msDelay(5000);
	}
}
