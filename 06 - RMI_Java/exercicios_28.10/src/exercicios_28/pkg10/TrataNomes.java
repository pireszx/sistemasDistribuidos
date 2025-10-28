package exercicios_28.pkg10;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class TrataNomes extends UnicastRemoteObject implements ITrataNomes {

    public TrataNomes() throws RemoteException {
        super();
    }

    @Override
    public String gerarEmail(String nome) throws RemoteException {
        String[] partes = nome.trim().split("\\s+");
        String primeiroNome = partes[0];
        String ultimoNome = partes[partes.length - 1];
        
        return primeiroNome.toLowerCase() + "." + ultimoNome.toLowerCase() + "@ufn.edu.br";
    }


    @Override
    public String pegarSobrenome(String nome) throws RemoteException {
        String[] partes = nome.trim().split("\\s+");
        return partes[partes.length - 1];
    }

    @Override
    public String pegarNome(String nome) throws RemoteException {
        String[] partes = nome.trim().split("\\s+");
        return partes[0];
    }
}


