package com.shadow.coctool.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import javax.inject.Inject;

/**
 * Created by lxf on 2017/7/21.
 */

public class SocketComp {

    private Socket socket;

    private Thread readThread;

    private List<ReadObserver> readList;

    private BufferedReader buf;

    private OutputStream out;


    @Inject
    public SocketComp() {
        readList = new Vector<>();
    }

    public void start(String ip, int port) {
        readThread = new Thread(() -> {
            try {
                socket = new Socket(ip, port);
                socket.setTcpNoDelay(true);
                buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = socket.getOutputStream();

                while (!Thread.interrupted() && socket.isConnected()) {
                    String string = buf.readLine();
                    if (string != null && !"".equals(string)) {
                        synchronized (readList) {
                            for (ReadObserver ob : readList) {
                                ob.execute(string);
                            }
                        }
                    }
                }
                buf.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
            }
        });

        readThread.start();
    }

    public void send(String message) {
        new Thread(() -> {
            if (out != null) {
                try {
                    String newLine = message + "\r\n";
                    out.write(newLine.getBytes());
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void stop() {
        try {
            if (socket != null) {
                buf.close();
                out.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addReadObserver(ReadObserver observer) {
        readList.add(observer);
    }

    public interface ReadObserver {
        public void execute(String json);
    }

    public interface ErrorObserver {
        public void execute(String message);
    }
}
