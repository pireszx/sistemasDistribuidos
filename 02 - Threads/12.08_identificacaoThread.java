package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class PopulaLista extends Thread {
    private List<Integer> lista;
    Integer tamanho;

    public PopulaLista(List<Integer> lista, Integer tamanho, String nome) {
        super(nome);
        this.lista = lista;
        this.tamanho = tamanho;
    }

    @Override
    public void run() {
        System.out.println("Iniciando: " + Thread.currentThread().getName());
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            lista.add(random.nextInt(tamanho));
        }
        System.out.println("Finalizando: " + Thread.currentThread().getName());
    }
}

class OrdenaBolha extends Thread {
    private List<Integer> lista;

    public OrdenaBolha(List<Integer> lista, String nome) {
        super(nome);
        this.lista = lista;
    }

    public void run() {
        System.out.println("Iniciando: " + Thread.currentThread().getName());
        boolean houveTroca;
        int tmp;
        do {
            houveTroca = false;
            for (int i = 0; i < lista.size() - 1; i++) {
                if (lista.get(i) > lista.get(i + 1)) {
                    houveTroca = true;
                    tmp = lista.get(i);
                    lista.set(i, lista.get(i + 1));
                    lista.set(i + 1, tmp);
                }
            }
        } while (houveTroca);
        System.out.println("Finalizando: " + Thread.currentThread().getName());
    }
}

class OrdenaPente extends Thread {
    private List<Integer> lista;

    public OrdenaPente(List<Integer> lista, String nome) {
        super(nome);
        this.lista = lista;
    }

    public void run() {
        System.out.println("Iniciando: " + Thread.currentThread().getName());
        boolean houveTroca;
        int tmp;
        int distancia = lista.size();
        do {
            distancia = (int) (distancia / 1.3);
            if (distancia < 1) distancia = 1;
            houveTroca = false;
            for (int i = 0; i < lista.size() - distancia; i++) {
                if (lista.get(i) > lista.get(i + distancia)) {
                    houveTroca = true;
                    tmp = lista.get(i);
                    lista.set(i, lista.get(i + distancia));
                    lista.set(i + distancia, tmp);
                }
            }
        } while (distancia > 1 || houveTroca);
        System.out.println("Finalizando: " + Thread.currentThread().getName());
    }
}

public class Java {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> lista1 = new ArrayList<>();
        List<Integer> lista2 = new ArrayList<>();
        List<Integer> lista3 = new ArrayList<>();

        PopulaLista threadPopula1 = new PopulaLista(lista1, 10000, "Populador Lista 1");
        PopulaLista threadPopula2 = new PopulaLista(lista2, 1000, "Populador Lista 2");
        PopulaLista threadPopula3 = new PopulaLista(lista3, 5000, "Populador Lista 3");

        threadPopula1.start();
        threadPopula2.start();
        threadPopula3.start();

        threadPopula1.join();
        threadPopula2.join();
        threadPopula3.join();

        OrdenaPente threadOrdena1 = new OrdenaPente(lista1, "Ordenador Pente Lista 1");
        OrdenaBolha threadOrdena2 = new OrdenaBolha(lista2, "Ordenador Bolha Lista 2");
        OrdenaBolha threadOrdena3 = new OrdenaBolha(lista3, "Ordenador Bolha Lista 3");

        threadOrdena1.start();
        threadOrdena2.start();
        threadOrdena3.start();
    }
}
