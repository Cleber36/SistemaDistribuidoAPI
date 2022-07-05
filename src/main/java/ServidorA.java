import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ServidorA<total> extends Thread {
    private static ArrayList<BufferedWriter> clientes;
    private static ServerSocket server;
    private String nome;
    private Socket con;
    private InputStream in;
    private InputStreamReader inr;
    private BufferedReader bfr;


    public ServidorA(Socket con) {
        this.con = con;
        try {
            in = con.getInputStream();
            inr = new InputStreamReader(in);
            bfr = new BufferedReader(inr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void run() {

    }

    public static void main(String[] args) throws IOException {
        int num1, num2;
        double total = 0;
        int operacao = 0;
        char opr = 0;

        //Cria um socket na porta 9998
        ServerSocket servidor = new ServerSocket(9998);
        System.out.println("Porta 9999 aberta!");

        System.out.print("Aguardando conexão do cliente...");
        Socket cliente = servidor.accept();

        System.out.println("Nova conexao com o cliente " + cliente.getInetAddress().getHostAddress());


        ObjectOutputStream resultado = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream dados = new ObjectInputStream(cliente.getInputStream());


        num1 = dados.readInt();
        num2 = dados.readInt();

        operacao = (char) dados.readInt();
        if (operacao == 1) {

            opr = '+';
            total = (num1 + num2);


        }
        if (operacao == 2) {

            opr = '-';
            total = (num1 - num2);
        }
        if (operacao == 3) {


            opr = 'x';
            total = (num1 * num2);
        }
        if (operacao == 4) {

            opr = '/';
            total = (num1 / num2);
        }


        resultado.writeDouble(total);
        resultado.writeChar(opr);
        resultado.flush();
        servidor.close();
    }

}





    
    
    
    
    
    
   

