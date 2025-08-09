package client;

import java.io.*;
import java.net.*;
import java.util.Set;

public class ClientHandler implements Runnable {
    private Socket socket;
    private Set<PrintWriter> clientWriters;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket, Set<PrintWriter> clientWriters) {
        this.socket = socket;
        this.clientWriters = clientWriters;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Add this client's writer to the set
            clientWriters.add(out);

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Received: " + message);

                // Broadcast to all clients
                for (PrintWriter writer : clientWriters) {
                    writer.println(message);
                }
            }
        } catch (IOException e) {
            System.out.println("Client disconnected.");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            clientWriters.remove(out);
        }
    }
}
