package com.demo.nian.reactor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName: MyReactor  
 * @Description: Reactor模式模拟 
 * 传统线程池做法：来一个客人(请求)去一个服务员(线程) 
 * 反应器模式做法：当客人点菜的时候，服务员就可以去招呼其他客人了，等客人点好了菜，直接招呼一声“服务员” 
 * @date: 2017年2月16日 上午10:36:53 
 * 
 * @author tanfan 
 * @version  
 * @since JDK 1.7
 */
public class MyReactor implements Runnable {
	
	final Selector selector;
	final ServerSocketChannel serverSocketChannel;
	
	public MyReactor(int port) throws IOException{
		selector = Selector.open();
		serverSocketChannel = ServerSocketChannel.open();
		InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), port);
		serverSocketChannel.socket().bind(inetSocketAddress);
		//配置非阻塞
		serverSocketChannel.configureBlocking(false);
		//向selector注册改channel
		SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		//利用attache绑定Acceptor，如果有事情，触发Accepor
		selectionKey.attach(new Acceptor(this));
		System.out.println("在"+InetAddress.getLocalHost()+"连接"+port+"成功！");
	}
	
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				selector.select();
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> it = selectionKeys.iterator();
				while(it.hasNext()){
					SelectionKey selectionKey = it.next();
					dispatch(selectionKey);
					it.remove();
					selectionKeys.clear();
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private void dispatch(SelectionKey selectionKey) {
		Runnable r = (Runnable)selectionKey.attachment();
		System.out.println("r.class name="+r.getClass().getName());
		if(r!=null){
			r.run();
		}
	}
	
	
	public static void main(String[] args) {
		try{
			MyReactor reactor = new MyReactor(8888);
			reactor.run();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
