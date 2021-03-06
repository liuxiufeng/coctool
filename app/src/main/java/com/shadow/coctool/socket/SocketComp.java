package com.shadow.coctool.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import javax.inject.Inject;

/**
 * Created by lxf on 2017/7/21.
 */

public class SocketComp {
    private final String QUIT_MESSAGE = "bye";

    private Socket socket;

    private Thread readThread;

    private List<ReadObserver> readList;

    private BufferedReader buf;

    private OutputStream out;

    private ErrorObserver errorObserver;


    @Inject
    public SocketComp() {
        readList = new Vector<>();
    }

    public void start(String ip, int port) {
        readThread = new Thread(() -> {
            try {
                socket = new Socket();
                socket.connect(new InetSocketAddress(ip, port), 1000);
                socket.setTcpNoDelay(true);

                buf = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                out = socket.getOutputStream();

                while (!Thread.interrupted() && socket.isConnected()) {
                    String string = buf.readLine();
                    if (QUIT_MESSAGE.equals(string)) {
                        break;
                    }
                    if (string != null && !"".equals(string)) {
                        synchronized (readList) {
                            for (ReadObserver ob : readList) {
                                ob.execute(string);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (errorObserver != null) {
                    errorObserver.execute("房间没有创建");
                }
            } finally {
                try {
                    buf.close();
                    out.close();
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        readThread.start();
    }

    public void send(String message) {
        new Thread(() -> {
            try {
                if (out == null) {
                    Thread.sleep(1000);
                }
                if (!socket.isConnected()) {
                    return;
                }
                String newLine = message + "\r\n";
                out.write(newLine.getBytes());
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void stop() {
        send(QUIT_MESSAGE);
    }

    public void addReadObserver(ReadObserver observer) {
        readList.add(observer);
    }

    public ErrorObserver getErrorObserver() {
        return errorObserver;
    }

    public void setErrorObserver(ErrorObserver errorObserver) {
        this.errorObserver = errorObserver;
    }

    public interface ReadObserver {
        public void execute(String json);
    }

    public interface ErrorObserver {
        public void execute(String message);
    }
}
