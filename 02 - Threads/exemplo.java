/*
    popularLista(lista1, 10000)
    popularLista(lista2, 3000)

    ordenarBolha(lista2)
    ordenarPente(lista1)
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void popularLista(List<Integer> lista, int tamanho) {
        Random rand = new Random();
        for (int i = 0; i < tamanho; i++) {
            lista.add(rand.nextInt(10000));
        }
    }

    public static void ordenarBolha(List<Integer> lista) {
        int n = lista.size();
        boolean troca;
        do {
            troca = false;
            for (int i = 0; i < n - 1; i++) {
                if (lista.get(i) > lista.get(i + 1)) {
                    int temp = lista.get(i);
                    lista.set(i, lista.get(i + 1));
                    lista.set(i + 1, temp);
                    troca = true;
                }
            }
            n--;
        } while (troca);
        System.out.println("bolha ordenada");
    }

    public static void ordenarPente(List<Integer> lista) {
        int tamanho = lista.size();
        int gap = tamanho;
        boolean troca = true;

        while (gap > 1 || troca) {
            gap = (int) (gap / 1.3);
            if (gap < 1) gap = 1;

            troca = false;
            for (int i = 0; i + gap < tamanho; i++) {
                if (lista.get(i) > lista.get(i + gap)) {
                    int temp = lista.get(i);
                    lista.set(i, lista.get(i + gap));
                    lista.set(i + gap, temp);
                    troca = true;
                }
            }
        }
        System.out.println("pente ordenado");
    }

    public static void main(String[] args) {
        List<Integer> lista1 = new ArrayList<>();
        List<Integer> lista2 = new ArrayList<>();

        popularLista(lista1, 10000);
        popularLista(lista2, 3000);

        Thread threadPente = new Thread(() -> ordenarPente(lista1));
        Thread threadBolha = new Thread(() -> ordenarBolha(lista2));

        threadPente.start();
        threadBolha.start();

        try {
            threadPente.join();
            threadBolha.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("threads finalizadas!");
    }
}
