package exercicios_28.pkg10;

import java.rmi.Naming;

public class Client {
    public static void main(String args[]) {
        try {
            ITrataNomes obj = (ITrataNomes) Naming.lookup("rmi://localhost/TrataNomes"); 
            
            String nomeCompleto = "Pedro Pires"; 

            String primeiroNome = obj.pegarNome(nomeCompleto);
            System.out.println("Nome: " + primeiroNome);
            
            String sobrenome = obj.pegarSobrenome(nomeCompleto);
            System.out.println("Sobrenome: " + sobrenome);
            
            String email = obj.gerarEmail(nomeCompleto);
            System.out.println("Email: " + email);

        } catch (Exception e) {
            System.out.println("Erro no cliente: " + e);
        }
    }
}