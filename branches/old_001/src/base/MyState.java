package base;

import java.awt.Color;

public class MyState{

	private String statusText = "---";
	private Color statusColor = Color.BLACK;
	private int priorit�t = 0;
	
	
	public MyState(String text, Color farbe, int priorit�t){
		this.statusText = text;
		this.statusColor = farbe;
		this.priorit�t = priorit�t;
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

	public int getPriorit�t() {
		return priorit�t;
	}

	public void setPriorit�t(int priorit�t) {
		this.priorit�t = priorit�t;
	}
	
}
