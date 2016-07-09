package edf.medor.bmserv;

import java.io.IOException;

import com.sun.grizzly.ControllerStateListener;

public class ServerStateListener implements ControllerStateListener {
	@Override
	public void onException(Throwable arg0) {
//		System.out.println("Server exception !");
	}

	@Override
	public void onReady() {
//		System.out.println("Server ready !");
	}

	@Override
	public void onStarted() {
		System.out.println("Server Started !");
	}

	@Override
	public void onStopped() {
		System.out.println("==> Server stopped !\n");
		try {
			Writer.serialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
