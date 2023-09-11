import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class FirstApp {

    public static void main(String[] args) {
        // Obtenez une instance de la brique EV3
        Brick ev3 = BrickFinder.getDefault();

        // Initialisez les moteurs des roues
        EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
        EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);
        EV3LargeRegulatedMotor bras = new EV3LargeRegulatedMotor(MotorPort.D);

        // Faites tourner les roues vers l'avant pendant 2 secondes
        bras.forward();
        leftMotor.forward();
        rightMotor.forward();

        try {
            Thread.sleep(5000); // Pause de 2 secondes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Arrêtez les moteurs
        bras.stop();
        bras.setSpeed(8000);
        rightMotor.setAcceleration(5000);
        rightMotor.stop();
        leftMotor.stop();

        // Libérez les ressources
        bras.close();
        leftMotor.close();
        rightMotor.close();
    }
}
