import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Distribuidor extends Thread {
    private List<List<Integer>> listas;
    private int n, m;

    public Distribuidor(List<List<Integer>> listas, int n, int m) {
        this.listas = listas;
        this.n = n;
        this.m = m;
    }

    @Override
    public void run() {
        System.out.println("Distribuindo " + n + " números em " + m + " listas");
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int numero = 1000 + random.nextInt(100001 - 1000);
            int indiceLista = i % m;
            listas.get(indiceLista).add(numero);
        }
    }
}

class CalculaSoma extends Thread {
    private List<Integer> lista;
    private long soma;

    public CalculaSoma(List<Integer> lista) {
        this.lista = lista;
    }

    @Override
    public void run() {
        soma = 0;
        for (int num : lista) {
            soma += num;
        }
    }

    public long getSoma() {
        return soma;
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // n = numero // m = lista
        int n = 10000;
        int m = 5;     

        List<List<Integer>> listas = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            listas.add(new ArrayList<>());
        }

        Distribuidor distribuidor = new Distribuidor(listas, n, m);
      
        distribuidor.start();
        distribuidor.join();

        CalculaSoma[] threadsSoma = new CalculaSoma[m];
      
        for (int i = 0; i < m; i++) {
            threadsSoma[i] = new CalculaSoma(listas.get(i));
            threadsSoma[i].start();
        }

        long somaTotal = 0;
        
        for (int i = 0; i < m; i++) {
            threadsSoma[i].join();
            somaTotal += threadsSoma[i].getSoma();
        }
        
        double media = (double) somaTotal / n;
        
        System.out.println("Soma total: " + somaTotal);
        System.out.println("Media: " + media);
    }
}
