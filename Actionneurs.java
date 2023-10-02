import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Actionneurs {
	EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.A);
	EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.B);
	EV3LargeRegulatedMotor bras = new EV3LargeRegulatedMotor(MotorPort.D);
	private double angle;
	public void rouler() {
		rightMotor.startSynchronization();
		rightMotor.synchronizeWith(new EV3LargeRegulatedMotor[] {leftMotor});
		rightMotor.forward();
		leftMotor.forward();
		rightMotor.endSynchronization();
	}
	public void arreter() {
		rightMotor.startSynchronization();
		rightMotor.synchronizeWith(new EV3LargeRegulatedMotor[] {leftMotor});
		rightMotor.stop();
		leftMotor.stop();
		rightMotor.endSynchronization();
	}
	public void reculer() {
		rightMotor.startSynchronization();
		rightMotor.synchronizeWith(new EV3LargeRegulatedMotor[] {leftMotor});
		rightMotor.backward();
		leftMotor.backward();
		rightMotor.endSynchronization();
	}
	public void rotateClockwise(int angle)
    {
		rightMotor.setSpeed(angle);
		leftMotor.setSpeed(0);
    }
    
    /*public void rotateCounterClockwise(int angle)
    {    
    	rightMotor.rotate(angle);
    }*/
	public void addAngle(int deg,boolean dir) {

		if(dir==true) {
			angle= angle +deg;
		}else {
			if(angle==0) {
				angle=360-deg;
			}else if(angle<deg) {
				angle=360-(deg-angle);
			}
			else {
				angle= angle -deg;
			}
		}
		angle%=360;
	}
	
	public void tourner(boolean dir,double nbQuartT) {
		leftMotor.endSynchronization();

		if(dir==true) {
			rightMotor.rotate((int)(-190*nbQuartT),true);
			leftMotor.rotate((int)(190*nbQuartT),true);
			addAngle((int)(90*nbQuartT),true);
		}else if(dir==false) {
			leftMotor.rotate((int)(-190*nbQuartT),true);
			rightMotor.rotate((int)(190*nbQuartT),true);
			addAngle((int)(90*nbQuartT),false);
		}
		leftMotor.startSynchronization();
	}
    public void bougerBras(int angle) {
    	bras.rotate(angle);
    }
    public void accelerer(int vitesse) {
    	rightMotor.setAcceleration(vitesse);
    	leftMotor.setAcceleration(vitesse);
    }

    
}
