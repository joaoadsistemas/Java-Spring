package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        // ADICIONANDO ELEMENTO A UMA LISTA
        List<Integer> lista = new ArrayList<>();

        adicionarElemento(lista, n -> n + 1);
        lista.forEach(System.out::println);

        System.out.println("////////////////////////////////////////////");

        // Filtrar por pares
        lista.stream().filter(n -> n % 2 == 0).forEach(System.out::println);

        System.out.println("////////////////////////////////////////////");


        // Concatenação
        StringConcatenetor concatenator = (n1, n2) -> n1 + n2;
        String res = concatenator.concatena("HELLO ", "WORLD");
        System.out.println(res);

        System.out.println("////////////////////////////////////////////");

        // Calcular media
        List<Double> numeros = new ArrayList<>();
        numeros.add(33.2);
        numeros.add(6.5);
        numeros.add(19.2);
        numeros.add(9.2);
        numeros.add(1.4);

        double media = calcularMedia(numeros, n -> n);

    }


    // adicionar elemento ao aray lista
    private static void adicionarElemento(List<Integer> lista, IntFunction funcao) {

        for (int i = 0; i < 10; i++) {

            int elemento = funcao.apply(i);
            lista.add(elemento);
        }

    }

    // adicionar elemento ao aray lista
    interface IntFunction {
        int apply(int valor);
    }


    // Calcular media
    private static double calcularMedia(List<Double> numeros, DoubleFunction n) {
    }

    interface DoubleFunction {
        double apply(double n);
    }

}