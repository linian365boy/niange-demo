package com.demo.nian.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class SocketReadHandler implements Runnable {

	private SocketChannel socketChannel;
	
	public SocketReadHandler(Selector selector, SocketChannel socketChannel) throws IOException {
		this.socketChannel = socketChannel;
		//配置非阻塞
		socketChannel.configureBlocking(false);
		SelectionKey selectionKey = socketChannel.register(selector, 0);
		//将SelectionKey绑定为本Handler 下一步有事件触发时，将调用本类的run方法。 
		selectionKey.attach(this);
		//同时将SelectionKey标记为可读，以便读取。    
		selectionKey.interestOps(SelectionKey.OP_READ);
		selector.wakeup();
	}

	@Override
	public void run() {
		ByteBuffer inputBuffer = ByteBuffer.allocate(1024);
		inputBuffer.clear();
		String receiveText; 
		int count=0;
		try{
			count = socketChannel.read(inputBuffer);
			if (count > 0) {  
                receiveText = new String( inputBuffer.array(),0,count);  
                System.out.println("服务器端接受客户端数据--:"+receiveText);  
            } 
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}