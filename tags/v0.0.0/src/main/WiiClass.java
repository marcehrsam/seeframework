package main;
import java.io.IOException;

import wiiremotej.WiiRemote;
import wiiremotej.WiiRemoteExtension;
import wiiremotej.WiiRemoteJ;
import wiiremotej.event.WRAccelerationEvent;
import wiiremotej.event.WRButtonEvent;
import wiiremotej.event.WRCombinedEvent;
import wiiremotej.event.WRExtensionEvent;
import wiiremotej.event.WRIREvent;
import wiiremotej.event.WRStatusEvent;
import wiiremotej.event.WiiRemoteListener;

public class WiiClass implements WiiRemoteListener{
	
	public WiiClass(){
		try {
			WiiRemote myWii = WiiRemoteJ.findRemote();
			myWii.setAccelerometerEnabled(true);
			myWii.addWiiRemoteListener(this);
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void IRInputReceived(WRIREvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void accelerationInputReceived(WRAccelerationEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void buttonInputReceived(WRButtonEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void combinedInputReceived(WRCombinedEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void disconnected() {
		// TODO Auto-generated method stub
		
	}

	public void extensionConnected(WiiRemoteExtension arg0) {
		// TODO Auto-generated method stub
		
	}

	public void extensionDisconnected(WiiRemoteExtension arg0) {
		// TODO Auto-generated method stub
		
	}

	public void extensionInputReceived(WRExtensionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void extensionPartiallyInserted() {
		// TODO Auto-generated method stub
		
	}

	public void extensionUnknown() {
		// TODO Auto-generated method stub
		
	}

	public void statusReported(WRStatusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}