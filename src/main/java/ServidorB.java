import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorB extends Thread {
    private static ArrayList<BufferedWriter> clientes;
    private static ServerSocket server;
    private String nome;
    private Socket con;
    private InputStream in;
    private InputStreamReader inr;
    private BufferedReader bfr;



    public ServidorB(Socket con){
        this.con = con;
        try {
            in  = con.getInputStream();
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
        double total= 0;
        char operacao;
        char opr = 0;

        //Cria um socket na porta 9999
        ServerSocket servidor2 = new ServerSocket(9999);
        System.out.println("Porta 9999 aberta!");


        System.out.print("Aguardando conexão do cliente...");
        Socket cliente = servidor2.accept();

        System.out.println("Nova conexao com o cliente " + cliente.getInetAddress().getHostAddress());


        ObjectOutputStream resultado = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream dados = new ObjectInputStream(cliente.getInputStream());


            num1 = dados.readInt();
            num2 = dados.readInt();

            operacao = (char) dados.readInt();
        if (operacao == 5) {

            opr = '%';
            total = (Double.parseDouble(String.valueOf(num1)) * Double.parseDouble(String.valueOf(num2)) / 100);


        }
        if (operacao == 6) {

            opr = 'r';
            total = Math.sqrt(Double.parseDouble(String.valueOf(num1)));
        }
        if (operacao == 7) {


            opr = '^';
            total = (int) Math.pow(num1,num2);
        }


        resultado.writeDouble(total);
        resultado.writeChar(opr);
        resultado.flush();
        servidor2.close();
    }
}