package com.mingslife.web.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/message")
public class MessageWebSocket {
	private Session session;
	
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		System.out.println("创建连接");
	}
	
	@OnClose
	public void onClose() {
		System.out.println("关闭连接");
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println(message);
		
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000L);
					MessageWebSocket.this.session.getBasicRemote().sendText("Hi!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("连接错误");
	}
}
