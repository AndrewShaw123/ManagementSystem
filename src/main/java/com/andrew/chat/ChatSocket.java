package com.andrew.chat;

import com.andrew.domain.NormalMessage;
import com.andrew.domain.SystemMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ChatSocket Class
 *
 * @author andrew
 * @date 2019/8/1
 */
@ServerEndpoint("/chatSocket")
public class ChatSocket {

    public String username;
    public static Map<String,Session> sessions=new HashMap<>();
    public static List<String> names=new ArrayList<>();

    @OnOpen
    public void open(Session session){
        String param=session.getQueryString();

        username=param.split("=")[1];

        username= URLDecoder.decode(username);


        sessions.put(username,session);
        names.add(username);
        broadcasrUserList(names);
    }

    @OnClose
    public void close(Session session){
        sessions.remove(username);
        names.remove(this.username);
        broadcasrUserList(names);
    }

    @OnMessage
    public void message(String message){

        NormalMessage normalMessage= null;
        try {
            ObjectMapper objectMapper=new ObjectMapper();
            normalMessage = objectMapper.readValue(message,NormalMessage.class);
            String to=normalMessage.getTo();
            String from=normalMessage.getFrom();
            for(String username:sessions.keySet()){
                if(username.equals(to)||username.equals(from)){
                    Session session=sessions.get(username);
                    session.getBasicRemote().sendText(objectMapper.writeValueAsString(normalMessage));
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void broadcast(Map<String,Session> sessions,String data){

        for (String username:sessions.keySet()) {
            Session session=sessions.get(username);
            try {
                session.getBasicRemote().sendText(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void broadcasrUserList(List<String> names){
        SystemMessage systemMessage=new SystemMessage(names);
        String json= null;
        try {
            ObjectMapper objectMappe=new ObjectMapper();
            json = objectMappe.writeValueAsString(systemMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        broadcast(sessions,json);
    }

}
