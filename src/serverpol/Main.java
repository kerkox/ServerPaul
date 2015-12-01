package serverpol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        

        
        if(args.length == 0){
                System.out.println("Se debe especificar el puerto");
                return;
            }
        
        ServerSocket serverSocket=null;
        boolean isExecuted = true;
        try {
            int port = Integer.parseInt(args[0]);
            serverSocket = new ServerSocket(port);
            System.out.println("1. Servidor iniciado");
                        
            //Aceptar la conexion del cliente
            while(isExecuted){
                
            Socket client = serverSocket.accept();
            
            System.out.println("inetadress: "+client.getRemoteSocketAddress().toString());
            new ServerThread(client).start();
            System.out.println("2. Conexion aceptada");
            
            }
        } catch (IOException ex) {
            System.out.println("No fue posible crear el servidor "
            + ex.getMessage());
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException ex) {
//                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
