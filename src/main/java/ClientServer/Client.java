package ClientServer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.net.Socket;


public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8189);
        System.out.println("Подключаемся");

        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);

        List<Fireball> fireballs = new ArrayList<>();
        fireballs.add(new Fireball("Try to dodge! WrrrrrshhhhsWrrrrshshshh"));
        fireballs.add(new Fireball("With one like this we have killed all Dinos"));
        fireballs.add(new Fireball("No more mana"));
        oos.writeObject(fireballs);
        System.out.println("Кастанули");
        try {
            oos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Отдыхаем");
    }
}
