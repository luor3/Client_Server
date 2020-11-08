package abstractserver;

import java.io.*;
import java.net.*;
import server.Server;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractServer implements Runnable{

    InputStream input;
    OutputStream output;
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    protected clientcommandhandler.ClientCommandHandler myClientCommandHandler;
    int portNumber = 5555, backlog = 500;
    boolean doListen = false;

    public AbstractServer(int portNumber, int backlog, clientcommandhandler.ClientCommandHandler myClientCommand) {
        this.portNumber = portNumber;
        this.backlog = backlog;
        this.myClientCommandHandler = myClientCommand;
    }

    public void startServer() {
        if (serverSocket != null) {
            stopServer();
        } else {
            try {
                serverSocket = new ServerSocket(portNumber, backlog);
            } catch (IOException e) {
                System.err.println("Cannot create ServerSocket, exiting program.");
                System.exit(1);
            } finally {
                serverStarted();
            }
        }
    }
    
    public void stopServer() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.err.println("Cannot close ServerSocket, exiting program.");
                System.exit(1);
            } finally {
                serverStopped();
            }

        }
    }

    protected synchronized void setDoListen(boolean doListen){
        this.doListen = doListen;
    }
    
    public void listen() {
        try {
            setDoListen(true);
            serverSocket.setSoTimeout(500);
            Thread myListenerThread = new Thread(this);
            myListenerThread.start();     
        } catch (SocketException ex) {
            //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run(){
        while (true) {
            if (doListen == true) {
                try {
                    clientSocket = serverSocket.accept();
                    clientconnection.ClientConnection theCC = new clientconnection.ClientConnection(clientSocket, myClientCommandHandler, (Server) this);
                    Thread theCCthread = new Thread(theCC);
                    theCCthread.start();
                    clientConnected(theCC, clientSocket);
                } catch (IOException e) {
                    //check doListen.
                } 
            } else {
                try {
                    Thread.sleep(500);  //Performance is not an issue here, since user input controls the doListen field; the worst case scenario is that the code won't respond to a user's start listening request until a maximum of 1/2 s.
                } catch (InterruptedException ie) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ie);
                    System.out.println("interrupted exception on listener thread!");
                }
            }
        }
    }

    public void setPort(int portNumber) {
        this.portNumber = portNumber;
    }

    public int getPort() {
        return this.portNumber;
    }
    
    public abstract void handleMessageFromClient(clientconnection.ClientConnection theCC, byte theCommand);

    public void connectionException(String Message){}
    public void clientConnected(clientconnection.ClientConnection theCC, Socket clientSocket){}
    public void clientDisconnected(clientconnection.ClientConnection theCC, Socket clientSocket){}
    public void clientQuit(clientconnection.ClientConnection theCC, Socket clientSocket){}
    public void clientException(){}
    public void serverStarted(){}
    public void serverStopped(){}
    public void serverClosed(){}
    
}