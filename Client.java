import java.io.DataOutputStream;
import java.net.Socket;

class client extends Thread{
    @Override
    public void run(){
        try{
            Socket socket = new Socket("localhost", 5000);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("Hello Server");
            dos.flush();
            dos.close();
            socket.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}