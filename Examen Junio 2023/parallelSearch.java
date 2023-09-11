/**
 * Examen Junio 2023 - Ejercicio 2
 * @author Manuel Pérez Ruiz
 * DNI 44065118Q
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class parallelSearch {
    private static final int LIST_SIZE = 1000000;
    private static final int THREADS = 4;

    private static List<Integer> generateRandomList() {
        List<Integer> list = new ArrayList<>(LIST_SIZE);

        Random random = new Random();
        for (int i = 0; i < LIST_SIZE; i++) {
            list.add(random.nextInt(1000));
        }

        return list;
    }

    private static int searchNumber(List<Integer> list, int number) {
        int count = 0;
        for (int i : list) {
            if (i == number) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int numberToSearch;
        if (args.length > 0) {
            numberToSearch = Integer.parseInt(args[0]);
        } else {
            numberToSearch = 10; // Número a buscar por defecto
        }

        // Generación de la lista en paralelo
        ExecutorService generationExecutor = Executors.newFixedThreadPool(THREADS);
        List<Future<List<Integer>>> generationFutures = new ArrayList<>();

        for (int i = 0; i < THREADS; i++) {
            Future<List<Integer>> future = generationExecutor.submit(parallelSearch::generateRandomList);
            generationFutures.add(future);
        }

        List<Integer> generatedList = new ArrayList<>(LIST_SIZE);

        try {
            for (Future<List<Integer>> future : generationFutures) {
                generatedList.addAll(future.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        generationExecutor.shutdown();

        // Búsqueda paralela
        ExecutorService searchExecutor = Executors.newFixedThreadPool(THREADS);
        List<Future<Integer>> searchFutures = new ArrayList<>();

        int sublistSize = LIST_SIZE / THREADS;

        for (int i = 0; i < THREADS; i++) {
            int startIndex = i * sublistSize;
            int endIndex = (i + 1) * sublistSize;

            if (i == THREADS - 1) {
                endIndex = LIST_SIZE; // Asegurar que la última sublista incluya todos los elementos restantes
            }

            List<Integer> sublist = generatedList.subList(startIndex, endIndex);

            Future<Integer> future = searchExecutor.submit(() -> searchNumber(sublist, numberToSearch));
            searchFutures.add(future);
        }

        int totalOccurrences = 0;

        try {
            for (Future<Integer> future : searchFutures) {
                int occurrences = future.get();
                totalOccurrences += occurrences;
                System.out.println("Paralelo: Encontradas " + occurrences + " coincidencias del número " + numberToSearch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        searchExecutor.shutdown();

        // Búsqueda secuencial
        int sequentialOccurrences = searchNumber(generatedList, numberToSearch);
        System.out.println("Secuencial: Encontradas " + sequentialOccurrences + " coincidencias del número " + numberToSearch);

        // Total de coincidencias encontradas en paralelo
        System.out.println("Totales de coincidencias encontradas en paralelo: " + totalOccurrences);
    }
}
