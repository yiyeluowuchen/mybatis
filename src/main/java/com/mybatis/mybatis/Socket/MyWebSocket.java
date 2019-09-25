package com.mybatis.mybatis.Socket;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Configuration
@ServerEndpoint(value = "/ws/myWebSocket")
public class MyWebSocket {

    private Session session;

    private static int onlineCount = 0;

    private static CopyOnWriteArraySet<MyWebSocket> copyOnWriteArraySet = new CopyOnWriteArraySet<MyWebSocket>();


    @OnOpen
    public void onOpen(Session sessiion){
        this.session = session;
        copyOnWriteArraySet.add(this);
        addOnlineCount();
        System.out.println("有新连接加入!当前在线人数为"+MyWebSocket.getOnlineCount());
    }


    @OnClose
    public void OnClose(){
    copyOnWriteArraySet.remove(this);
    subOnlineCount();
    System.out.println("有一个连接关闭，当前在线人数为"+getOnlineCount());
    }


    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        //群发消息
        for(MyWebSocket item: copyOnWriteArraySet){
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }


    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }


    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }


    public void sendMessage(String sessionId, String message) throws IOException {
        Session session = null;
        MyWebSocket tempWebSocket = null;
        for (MyWebSocket webSocket : copyOnWriteArraySet) {
            if (webSocket.session.getId().equals(sessionId)) {
                tempWebSocket = webSocket;
                session = webSocket.session;
                break;
            }
        }
        if (session != null) {
            tempWebSocket.session.getBasicRemote().sendText(message);
        } else {
           System.out.println("基础学习一下！");
        }
    }

    public static synchronized void  addOnlineCount(){

        MyWebSocket.onlineCount++;
    }

    public static synchronized int getOnlineCount(){
        return onlineCount;
    }

    public static synchronized void subOnlineCount(){

        onlineCount--;
    }



//    @Bean
//    public ServerEndpointExporter serverEndpointExporter() {
//        //这个对象说一下，貌似只有服务器是tomcat的时候才需要配置,具体我没有研究
//        return new ServerEndpointExporter();
//    }



}