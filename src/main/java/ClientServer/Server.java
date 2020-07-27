package ClientServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.function.Consumer;


public class Server {

    public static List<Fireball> fireballs = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket server = new ServerSocket(8189);
        System.out.println("Сервер запущен!");
        Socket socket = server.accept();
        System.out.println("Клиент подключился."+"\n");

        InputStream is = socket.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);

        System.out.println("Что это тут у нас?"+"\n");
        List<Fireball> receivedFireballs = (List<Fireball>) ois.readObject();  //Кастуем фаерболы!

        for (Fireball o: receivedFireballs){
            System.out.println(o.getText());
        }

        System.out.println("\n"+"Испепеляемся");

        try {
            ois.close();
            server.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
