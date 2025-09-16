package exemplo2_gerarEmail;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorTCPGUI extends JFrame {
    private JTextArea txtLog;      // área de log
    private ArrayList<Pessoa> lista; // lista de pessoas

    public ServidorTCPGUI() {
        setTitle("Servidor TCP - Gerador de E-mails");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        txtLog = new JTextArea(12, 40);
        txtLog.setEditable(false);
        txtLog.setFont(new Font("Consolas", Font.PLAIN, 14));
        add(new JScrollPane(txtLog), BorderLayout.CENTER);

        lista = new ArrayList<>();

        // inicia o servidor em outra thread para não travar a interface
        new Thread(this::iniciarServidor).start();
    }

    private void iniciarServidor() {
        try {
            int portaServidor = 50000;
            ServerSocket servidor = new ServerSocket(portaServidor);
            log("✅ Servidor ouvindo na porta " + portaServidor);

            while (true) {
                Socket cliente = servidor.accept();
                new Thread(() -> tratarCliente(cliente)).start();
            }
        } catch (Exception e) {
            log("❌ Erro no servidor: " + e.getMessage());
        }
    }

    private void tratarCliente(Socket cliente) {
    try {
        String enderecoIP = cliente.getInetAddress().getHostAddress();
        log("🔗 Cliente conectado: " + enderecoIP);

        ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
        String nomePessoa = (String) entrada.readObject();

        // 👉 mostra a mensagem recebida do cliente
        log("📨 Mensagem recebida: " + nomePessoa);

        // gera o email
        String[] vetorNome = nomePessoa.split(" ");
        String email = vetorNome[0] + "." + vetorNome[vetorNome.length - 1] + "@ufn.edu.br";

        Pessoa p = new Pessoa(nomePessoa.toUpperCase(), email.toLowerCase());

        boolean encontrado = lista.contains(p);
        if (!encontrado) {
            lista.add(p);
        }

        ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
        saida.flush();
        saida.writeObject(encontrado ? null : p);

        saida.close();
        entrada.close();
        cliente.close();

        atualizarLista();
    } catch (Exception e) {
        log("⚠️ Erro com cliente: " + e.getMessage());
    }
}

    private void atualizarLista() {
        log("\n📌 Lista atual de clientes:");
        for (Pessoa p : lista) {
            log(" - " + p.toString());
        }
    }

    private void log(String msg) {
        SwingUtilities.invokeLater(() -> txtLog.append(msg + "\n"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ServidorTCPGUI().setVisible(true));
    }
}
