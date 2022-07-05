import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static spark.Spark.*;

public class Api {

    public static void main(String [] args) {
        port(8080);

        get("/", (request, response) ->{
            return "Olá, Mundo!";
        });

        get("/somar/:num1/:num2", (request, response) -> {
            response.status(200);
            int num1 = Integer.parseInt(request.params("num1"));
            int num2 = Integer.parseInt(request.params("num2"));

            return "SOMA: " + Client(1, 1, num1, num2);
        });

        get("/substrair/:num1/:num2", (request, response) ->  {
            response.status(200);
            int num1 = Integer.parseInt(request.params("num1"));
            int num2 = Integer.parseInt(request.params("num2"));

            return "SUB: " + Client(1, 2, num1, num2);
        });

        get("/dividir/:num1/:num2", (request, response) -> {
            response.status(200);
            int num1 = Integer.parseInt(request.params("num1"));
            int num2 = Integer.parseInt(request.params("num2"));

            return "DIV: " + Client(1, 4, num1, num2);
        });

        get("/multiplicacao/:num1/:num2", (request, response) -> {
            response.status(200);
            int num1 = Integer.parseInt(request.params("num1"));
            int num2 = Integer.parseInt(request.params("num2"));

            return "MUL: " + Client(1, 3, num1, num2);
        });

        get("/potencia/:num1/:num2", (request, response) -> {
            response.status(200);
            int num1 = Integer.parseInt(request.params("num1"));
            int num2 = Integer.parseInt(request.params("num2"));

            return "POT: " + Client(1, 7, num1, num2);
        });

        get("/raiz/:num", (request, response) -> {
            response.status(200);
            int num = Integer.parseInt(request.params("num"));

            return "RAIZ: " + Client(1, 6, num, 1);
        });

        get("/porcentagem/:num1/:num2", (request, response) -> {

            response.status(200);
            int num1 = Integer.parseInt(request.params("num1"));
            int num2 = Integer.parseInt(request.params("num2"));

            return "PORC: " + Client(1, 5, num1, num2);
        });
    }

    public static String Client(int serverOP, int operacao, int num1, int num2) {
        Socket client;
        Socket cliente = null;
        try {
            // opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite o servidor de sua preferencia"));
            if (serverOP == 1) {
                cliente = new Socket("127.0.1.1", 9998);
            }if (serverOP == 2) {
                cliente = new Socket("127.0.1.1", 9999);
            }else{
                System.out.printf("Você digitou uma opção inválida.");
            }


            char opr;
            System.out.println("Conectado");

            ObjectInputStream resultado = new ObjectInputStream(cliente.getInputStream());
            ObjectOutputStream dados = new ObjectOutputStream(cliente.getOutputStream());

            dados.writeInt(operacao);
            dados.writeInt(num1);
            dados.writeInt(num2);
            dados.flush();

            double total = resultado.readDouble();
            opr = resultado.readChar();
            System.out.println("Total de " + num1 + opr + num2 + " = " + total);

            return String.valueOf(total);
        }catch(NumberFormatException e) {
            System.err.println("Dever informar o servidor corretamente!");
            e.printStackTrace();

            return "ERRO: Valores Invalido!";
        } catch ( Exception e) {
            e.printStackTrace();

            return "ERRO: " + e.getMessage();
        }
    }
}
