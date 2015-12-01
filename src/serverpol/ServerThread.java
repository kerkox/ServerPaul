package serverpol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThread extends Thread{
    
    private Socket client;
    
    
    public ServerThread(Socket socket){
        this.client = socket;
    }
    
    @Override
    public void run(){
        BufferedReader input = null;
        PrintStream output = null;
        try {
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            output = new PrintStream(client.getOutputStream());
            try{
            String str = input.readLine();
            String[] numbers = str.split(" ");
            int result = Integer.parseInt(numbers[0])
                    + Integer.parseInt(numbers[1]);
            output.println("Hola tu resultado es: "+result);
            }catch(IOException ex){
                output.println("Se genero el siguiente error: "+
                        ex.getLocalizedMessage());
            }
        } catch (IOException ex) {
            
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
    }
    
}
