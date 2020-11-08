package servertest;

/**
 *
 * @author ferens
 */
public class ServerTest {

    public static void main(String[] args) {

        stdio.StandardIO myUI = new stdio.StandardIO();
        clientcommandhandler.ClientCommandHandler myClientCommand = new clientcommandhandler.ClientCommandHandler(myUI);
        server.Server myServer = new server.Server(5555, 1, myClientCommand, myUI);
        usercommandhandler.UserCommandHandler myCommand = new usercommandhandler.UserCommandHandler(myUI, myServer);
        myUI.setCommand(myCommand);
        Thread myUIthread = new Thread(myUI);
        myUIthread.start();     
        myUI.display("1:\tQuit\n2:\tlisten\n3:\tSet Port\n4:\tGet Port\n5:\tget Client IP Address\n6:\tStart Server Socket\n");
    }
}
