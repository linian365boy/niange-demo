package com.demo.nian.reactor;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public class Acceptor implements Runnable {
	
	private MyReactor myReactor;
	
	public Acceptor(MyReactor myReactor) {
		this.myReactor = myReactor;
	}

	@Override
	public void run() {
		try{
			SocketChannel socketChannel = myReactor.serverSocketChannel.accept();
			if(socketChannel!=null){
				System.out.println("come on ...");
				//调用Handler处理channel
				new SocketReadHandler(myReactor.selector, socketChannel);;
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
}