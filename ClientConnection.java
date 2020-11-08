/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clientconnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ferens
 */
public class ClientConnection implements Runnable {

    InputStream input;
    OutputStream output;
    Socket clientSocket = null;
    stdio.StandardIO myUI;
    clientcommandhandler.ClientCommandHandler myClientCommandHandler;
    server.Server myServer;
    boolean stopThisThread = false;

    public ClientConnection(Socket clientSocket, clientcommandhandler.ClientCommandHandler myClientCommandHandler, server.Server myServer) {
        this.clientSocket = clientSocket;
        this.myClientCommandHandler = myClientCommandHandler;
        this.myServer = myServer;
        try {
            input = clientSocket.getInputStream();
            output = clientSocket.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Cannot create IO streams; exiting program.");
            System.exit(1);
        }
    }
    
    @Override
    public void run() {
        byte clientByteMessage = 0x00;
        while (stopThisThread == false) {
            try {
                clientByteMessage = (byte) input.read();
                myServer.handleMessageFromClient(this, clientByteMessage);
            } catch (IOException e) {
                myServer.connectionException("Unexpected disconnection from client: "+clientSocket.getRemoteSocketAddress());
                unexpectedDisconnectionHandler();
                stopThisThread = true;
            }
        }
    }  

    public void sendMessageToClient(byte byteMessage) {
        try {
            output.write(byteMessage);
            output.flush();
        } catch (IOException e) {
            System.err.println("cannot send to socket; exiting program.");
            System.exit(1);
        } finally {
        }
    }

    public void unexpectedDisconnectionHandler(){
        try {
            clientSocket.close();
            clientSocket = null;
            input = null;
            output = null;
        } catch (IOException e) {
            System.err.println("cannot close client socket; exiting program.");
            System.exit(1);
        }
    }

    public void disconnectTheClient(){
        try {
            stopThisThread = true;
            clientSocket.close();
            clientSocket = null;
            input = null;
            output = null;
        } catch (IOException e) {
            System.err.println("cannot close client socket; exiting program.");
            System.exit(1);
        } finally {
        }
    }

    public Socket getClientSocket(){
        return clientSocket;
    }
    
    public void quitClient() {
        myServer.clientQuit(this, clientSocket);
        disconnectTheClient();
    }
    
    public void disconnectClient() {
        myServer.clientDisconnected(this, clientSocket);
        disconnectTheClient();
    }
}
