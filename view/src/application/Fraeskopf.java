package application;

public class Fraeskopf implements Runnable {
	public double fahrGeschwindigkeit;
	public int schnittGeschwindigkeit;
	public boolean fraesenStatus = false;
	//
	public static double positionX;
	public static double positionY;
	
	public Fraeskopf() {
		positionX = 0;
		positionY = 0;
	}
	
	public double _getFahrGeschwindigkeit() {
		return fahrGeschwindigkeit;
	}
	
	public void _setFahrGeschwindigkeit(double v) {
		fahrGeschwindigkeit = v;
	}
	
	public int _getSchnittGeschwindigkeit() {
		return schnittGeschwindigkeit;
	}
	
	public void _setSchnittGeschwindigkeit(boolean kuehlmittelStatus) {
		if (kuehlmittelStatus == true) {
			schnittGeschwindigkeit = 3;
		}
		else {
			schnittGeschwindigkeit = 2;
		}
	}
	
	public String _getFraesenStatus() {
		if (fraesenStatus == false) {
			return "Die Fr�se ist aus";
		}
		else {
			return "Die Fr�se ist an";
		}
	}
	
	public void startFraese() {
		if (fraesenStatus == false) {
			fraesenStatus = true;
		}
		else {
			ErrorHandling.fraeskopfRunning();
		}
	}
	
	public void stoppFraese() {
		if (fraesenStatus == true) {
			fraesenStatus = false;
		}
		else {
			ErrorHandling.fraeskopfStopped();
		}
	}
	
	public static void _setPositionX(double x) {
		if(x > 0 && x < 1400) {
			positionX = x;
		}
		else {
			ErrorHandling.OutOfRangeX(x);
		}
	}
	
	public static void _setPositionY(double y) {
		if(y > 0 && y < 1050) {
			positionY = y;
		}
		else {
			ErrorHandling.OutOfRangeY(y);
		}
	}
	
	public void fraeskopfPositionieren(double x, double y) {
		this._setPositionX(x);
		this._setPositionY(y);
	}
	
	public double _getPositionX() {
		return positionX;
	}
	
	public double _getPositionY() {
		return positionY;
	}
	
	public String _getPosition() {
		return "(" + this._getPositionX() + ";" + this._getPositionY() + ")";
	}
	
	public String _getGeschwindigkeit() {
		if (fraesenStatus == true) {
			return (_getSchnittGeschwindigkeit() + " ");
		}
		else {
			return (_getFahrGeschwindigkeit() + " ");
		}
	}
	
	public void run() {
		//TODO: Insert Thread Actions here
	}
}
//Joshua
