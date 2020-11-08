/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clientcommandhandler;

import clientconnection.ClientConnection;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ferens
 */
public class ClientCommandHandler {

    ui.UserInterface myUI;

    public ClientCommandHandler(stdio.StandardIO myUI) {
        this.myUI = myUI;
    }

    public void execute(clientconnection.ClientConnection myClientConnection, String theCommand) {
        byte msg;
        Random rand = new Random();
        
        if (theCommand.charAt(0) == 'd') {
            myUI.udpate("Client has disconnected:\n\tRemote Socket Address = "+myClientConnection.getClientSocket().getRemoteSocketAddress() + "\n\tLocal Socket Address = " +myClientConnection.getClientSocket().getLocalSocketAddress());
            myClientConnection.disconnectClient();
        } else if (theCommand.charAt(0) == 'q') {
            myUI.udpate("Client has quit:\n\tRemote Socket Address = "+myClientConnection.getClientSocket().getRemoteSocketAddress() + "\n\tLocal Socket Address = " +myClientConnection.getClientSocket().getLocalSocketAddress());
            myClientConnection.quitClient();
        } 
        else if (theCommand.contains("L1On")) {
            myClientConnection.sendMessageToClient((byte)'L');
            myClientConnection.sendMessageToClient((byte)'1');
            myClientConnection.sendMessageToClient((byte)'O');
            myClientConnection.sendMessageToClient((byte)'n');
            myClientConnection.sendMessageToClient((byte)0xFF);
            myUI.udpate("Client has requested LED 1 On\n");
        } 
        else if (theCommand.contains("L2On")) {
            myClientConnection.sendMessageToClient((byte)'L');
            myClientConnection.sendMessageToClient((byte)'2');
            myClientConnection.sendMessageToClient((byte)'O');
            myClientConnection.sendMessageToClient((byte)'n');
            myClientConnection.sendMessageToClient((byte)0xFF);
            myUI.udpate("Client has requested LED 2 On\n");
        } 
        else if (theCommand.contains("L3On")) {
            myClientConnection.sendMessageToClient((byte)'L');
            myClientConnection.sendMessageToClient((byte)'3');
            myClientConnection.sendMessageToClient((byte)'O');
            myClientConnection.sendMessageToClient((byte)'n');
            myClientConnection.sendMessageToClient((byte)0xFF);
            myUI.udpate("Client has requested LED 3 On\n");
        } 
        else if (theCommand.contains("L4On")) {
            myClientConnection.sendMessageToClient((byte)'L');
            myClientConnection.sendMessageToClient((byte)'4');
            myClientConnection.sendMessageToClient((byte)'O');
            myClientConnection.sendMessageToClient((byte)'n');
            myClientConnection.sendMessageToClient((byte)0xFF);
            myUI.udpate("Client has requested LED 4 On\n");
        } 
        else if (theCommand.contains("L1Off")) {
            myClientConnection.sendMessageToClient((byte)'L');
            myClientConnection.sendMessageToClient((byte)'1');
            myClientConnection.sendMessageToClient((byte)'O');
            myClientConnection.sendMessageToClient((byte)'f');
            myClientConnection.sendMessageToClient((byte)'f');
            myClientConnection.sendMessageToClient((byte)0xFF);
            myUI.udpate("Client has requested LED 1 Off\n");
        } 
        else if (theCommand.contains("L2Off")) {
            myClientConnection.sendMessageToClient((byte)'L');
            myClientConnection.sendMessageToClient((byte)'2');
            myClientConnection.sendMessageToClient((byte)'O');
            myClientConnection.sendMessageToClient((byte)'f');
            myClientConnection.sendMessageToClient((byte)'f');
            myClientConnection.sendMessageToClient((byte)0xFF);
            myUI.udpate("Client has requested LED 2 Off\n");
        } 
        else if (theCommand.contains("L3Off")) {
            myClientConnection.sendMessageToClient((byte)'L');
            myClientConnection.sendMessageToClient((byte)'3');
            myClientConnection.sendMessageToClient((byte)'O');
            myClientConnection.sendMessageToClient((byte)'f');
            myClientConnection.sendMessageToClient((byte)'f');
            myClientConnection.sendMessageToClient((byte)0xFF);
            myUI.udpate("Client has requested LED 3 Off\n");
        } 
        else if (theCommand.contains("L4Off")) {
            myClientConnection.sendMessageToClient((byte)'L');
            myClientConnection.sendMessageToClient((byte)'4');
            myClientConnection.sendMessageToClient((byte)'O');
            myClientConnection.sendMessageToClient((byte)'f');
            myClientConnection.sendMessageToClient((byte)'f');
            myClientConnection.sendMessageToClient((byte)0xFF);
            myUI.udpate("Client has requested LED 4 Off\n");
        } 
        else if (theCommand.contains("gpb1")) {
            int myRandomNumber = rand.nextInt(2);
            myClientConnection.sendMessageToClient((byte)'P');
            myClientConnection.sendMessageToClient((byte)'B');
            myClientConnection.sendMessageToClient((byte)'1');
            if(myRandomNumber==0){
                myClientConnection.sendMessageToClient((byte)'O');
                myClientConnection.sendMessageToClient((byte)'f');
                myClientConnection.sendMessageToClient((byte)'f');
            }
            else {
                myClientConnection.sendMessageToClient((byte)'O');
                myClientConnection.sendMessageToClient((byte)'n');
            }
            myClientConnection.sendMessageToClient((byte)0xFF);
            myUI.udpate("Client has requested PB1 status\n");
        } 
        else if (theCommand.contains("gpb2")) {
            int myRandomNumber = rand.nextInt(2);
            myClientConnection.sendMessageToClient((byte)'P');
            myClientConnection.sendMessageToClient((byte)'B');
            myClientConnection.sendMessageToClient((byte)'2');
            if(myRandomNumber==0){
                myClientConnection.sendMessageToClient((byte)'O');
                myClientConnection.sendMessageToClient((byte)'f');
                myClientConnection.sendMessageToClient((byte)'f');
            }
            else {
                myClientConnection.sendMessageToClient((byte)'O');
                myClientConnection.sendMessageToClient((byte)'n');
            }
            myClientConnection.sendMessageToClient((byte)0xFF);
            myUI.udpate("Client has requested PB2 status\n");
        } 
        else if (theCommand.charAt(0) == 't') {
            Calendar cal = Calendar.getInstance();
            cal.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            myClientConnection.sendMessageToClient((byte)'T');
            myClientConnection.sendMessageToClient((byte)'h');
            myClientConnection.sendMessageToClient((byte)'e');
            myClientConnection.sendMessageToClient((byte)' ');
            myClientConnection.sendMessageToClient((byte)'t');
            myClientConnection.sendMessageToClient((byte)'i');
            myClientConnection.sendMessageToClient((byte)'m');
            myClientConnection.sendMessageToClient((byte)'e');
            myClientConnection.sendMessageToClient((byte)' ');
            myClientConnection.sendMessageToClient((byte)'i');
            myClientConnection.sendMessageToClient((byte)'s');
            myClientConnection.sendMessageToClient((byte)':');
            myClientConnection.sendMessageToClient((byte)' ');
            for (int i = 0; i < sdf.format(cal.getTime()).length(); i++) {
                msg = (byte) sdf.format(cal.getTime()).charAt(i);
                myClientConnection.sendMessageToClient(msg);
            }
            myClientConnection.sendMessageToClient((byte)0xFF);
            myUI.udpate("Client has requested time: "+sdf.format(cal.getTime()));
        }
    }
}
