package controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class ChatServer {
    private static List<Session> list = new CopyOnWriteArrayList<>();

    private void print(String msg) {
        System.out.printf("[%tT] %s\n", Calendar.getInstance(), msg);
    }

    @OnOpen
    public void handleOpen(Session session) {
        print("Incoming");
        list.add(session);
    }

    @OnMessage
    public void handleMessage(String msg, Session session) {
        int index = msg.indexOf("#", 2);
        String no = msg.substring(0, 1);
        String user = msg.substring(2, index);
        String txt = msg.substring(index + 1);

        if (no.equals("1")) {
            for (Session s : list) {
                if (s != session) {
                    try {
                        s.getBasicRemote().sendText("1#" + user + "#");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (no.equals("2")) {
            for (Session s : list) {
                try {
                    s.getBasicRemote().sendText("2#" + user + "#" + txt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (no.equals("3")) {
            for (Session s : list) {
                if (s != session) {
                    try {
                        s.getBasicRemote().sendText("3#" + user + "#");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            list.remove(session);
        }
    }

    @OnClose
    public void handleClose(Session session) {
        list.remove(session);
        print("Exit");
    }

    @OnError
    public void handleError(Throwable t, Session session) {
        
        print("Error: " + t.getMessage());
        t.printStackTrace(); // 스택 추적을 콘솔에 출력합니다.
    }

}
