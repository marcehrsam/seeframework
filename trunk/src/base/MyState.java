package base;

import java.awt.Color;

public class MyState{

	private String statusText = "---";
	private Color statusColor = Color.BLACK;
	private int priorität = 0;
	
	
	public MyState(String text, Color farbe, int priorität){
		this.statusText = text;
		this.statusColor = farbe;
		this.priorität = priorität;
	}
	
	public String getStatusText(){
		return statusText;
	}
	
	public void setStatusText(String text){
		this.statusText = text;
	}

	public Color getStatusColor() {
		return statusColor;
	}

	public void setStatusColor(Color statusColor) {
		this.statusColor = statusColor;
	}

	public int getPriorität() {
		return priorität;
	}

	public void setPriorität(int priorität) {
		this.priorität = priorität;
	}
	
}
