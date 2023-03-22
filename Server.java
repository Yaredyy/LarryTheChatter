import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;

class Server extends Thread{
    @Override
    public void run(){
        try{
            ServerSocket server = new ServerSocket(5000);
            Socket socket = server.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String str = (String)dis.readUTF();
            System.out.println("Message= "+str);
            server.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}