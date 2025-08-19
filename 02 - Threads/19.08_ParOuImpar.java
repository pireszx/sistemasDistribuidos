import java.util.ArrayList;
import java.util.List;

class ListaCompartilhada {
    private final List<Integer> numeros = new ArrayList<>();

    public synchronized void adicionarNumero(int umNumero) {
        numeros.add(umNumero);
        System.out.println(Thread.currentThread().getName() + " adicionou: " + umNumero);
    }

    public synchronized List<Integer> retornarNumeros() {
        return new ArrayList<>(numeros);
    }
}

class ThreadDeTrabalho extends Thread {
    private final ListaCompartilhada listaCompartilhada;
    private final int inicio;
    private final int fim;
    private final boolean par;

    public ThreadDeTrabalho(ListaCompartilhada lista, int inicio, int fim, boolean par) {
        this.listaCompartilhada = lista;
        this.inicio = inicio;
        this.fim = fim;
        this.par = par;
    }

    @Override
    public void run() {
        for (int i = inicio; i <= fim; i++) {
            // verifica se o número é par ou ímpar com propriedade dele ser par
            if ((i % 2 == 0 && par) || (i % 2 != 0 && !par)) {
                listaCompartilhada.adicionarNumero(i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ignored) {}
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ListaCompartilhada listaCompartilhada = new ListaCompartilhada();

        Thread t1 = new ThreadDeTrabalho(listaCompartilhada, 100, 200, true);
        t1.setName("thread par");

        Thread t2 = new ThreadDeTrabalho(listaCompartilhada, 1, 99, false);
        t2.setName("thread impar");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Lista final: " + listaCompartilhada.retornarNumeros());
    }
}
