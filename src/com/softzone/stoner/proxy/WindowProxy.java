package com.softzone.stoner.proxy;

import java.awt.Window;

import com.softzone.stoner.state.*;


public class WindowProxy {
	
	private State searchWindow;
	private LoadingWindowNew loadingWindow;
	private Thread retrievalThread;
	
	//to avoid deadlock
	private boolean retrieving = false;
	
	String authority,userName;
	
	public WindowProxy(String authority,String userName) {
		this.authority = authority;
		loadingWindow = new LoadingWindowNew();
		this.userName = userName;
		start();
		System.out.println("********* "+userName);
	}

	private void start() {
		if (searchWindow!=null) {
			//do nothing
		} else {

			System.out.println("loading searching window ...");
			loadingWindow.setVisible(true);
			
			if (!retrieving) {
				retrieving = true;
				retrievalThread = new Thread(new Runnable() {
					public void run() {
						try {
							if(authority.equals("admin")){

								searchWindow = new AdminState();
								searchWindow.init();
								searchWindow.setUserName(userName);
								searchWindow.buildSearchWindow();		
								searchWindow.setVisible(true);
							
							}
							else{
								searchWindow = new StandardUserState();
								searchWindow.setUserName(userName);
								searchWindow.init();
								searchWindow.buildSearchWindow();
							}
							loadingWindow.setVisible(false);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				retrievalThread.start();
			}
		}
	}

}
