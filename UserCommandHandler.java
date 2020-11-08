/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usercommandhandler;

/**
 *
 * @author ferens
 */
public class UserCommandHandler {
    stdio.StandardIO myUI;
    server.Server myServer;

    public UserCommandHandler(stdio.StandardIO myUI, server.Server myServer) {
        this.myUI = myUI;
        this.myServer = myServer;
    }

    public void execute(String theCommand) {
        byte msg = ' ';
        
        switch (Integer.parseInt(theCommand)) {
            case 1: //QUIT
                myServer.stopServer();
                myUI.display("Quitting program by User command.");
                System.exit(-1);
                break;
            case 2: //LISTEN
                myUI.display("Server is now listening, ...");
                myServer.listen();
                break;
            case 3: //SET PORT
                break;
            case 4: //GET PORT
                break;
            case 5: //GET CLIENT IP ADDRESS
                //clientIPAddress = myServer.getClientIPAddress();
                //myUI.display(clientIPAddress.toString());
                break;
            case 6: //START SERVER SOCKET
                myServer.startServer();
                myUI.display("Server Socket has been created.");
                break;
            default:
                break;
        }
    }
}
