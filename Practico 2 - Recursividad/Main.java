import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Ejercicio 1: contar dígitos
        System.out.println("Ej1: " + Recursivos.contarDigitos(12345));

        // Ejercicio 2: invertir cadena
        System.out.println("Ej2: " + Recursivos.invertirCadena("recursivo"));

        // Ejercicio 3: suma y promedio de arreglo
        int[] arr = {2, 4, 6, 8};
        System.out.println("Ej3 suma: " + Recursivos.sumarArreglo(arr, 0));


        // Ejercicio 4: MCD
        System.out.println("Ej4 MCD: " + Recursivos.mcd(48, 18));

        // Ejercicio 5: conversión a binario
        System.out.println("Ej5 binario de 13: " + Recursivos.aBinario(13));

        // Ejercicio 6: palíndromo
        System.out.println("Ej6 'neuquen': " + Recursivos.esPalindromo("neuquen"));
        System.out.println("Ej6 'informatica': " + Recursivos.esPalindromo("informatica"));

        // Ejercicio 7: Fibonacci con memoización
        int n = 10;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        System.out.println("Ej7 Fibonacci de " + n + ": " + Recursivos.FibonacciMemo(n, memo));

        // Ejercicio 8: buscar en arreglo
        int buscar = 7;
        int[] arrBuscar = {3, 5, 7, 9};
        System.out.println("Ej8 buscar " + buscar + ": " + Recursivos.buscarArreglo(arrBuscar, 0, buscar));
    }
}
