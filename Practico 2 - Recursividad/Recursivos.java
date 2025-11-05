public class Recursivos {

    public static int contarDigitos(int n){
        if (n<10) {
            return 1;
        } else {
            return 1+contarDigitos(n/10);
        }
    }

    public static String invertirCadena(String s){
        if (s.length()<=1) {
            return s;
        } else {
            return s.charAt(s.length()-1)+invertirCadena(s.substring(0, s.length()-1));
        }
    }

    public static int sumarArreglo(int []arr, int i){
        if (i==arr.length) {
            return 0;
        } else {
            return arr[i]+sumarArreglo(arr, i+1);
        }
    }

    public static int mcd(int a, int b){
        if (b==0) {
            return a;
        } else {
            return mcd(b, a%b);
        }
    }

    public static String aBinario(int n){
        if (n==0) {
            return "0";
        }
        if (n==1) {
            return "1";
        }
        return aBinario(n/2) + (n%2);
    }

    public static boolean esPalindromo(String s){
        if (s.length()<=1) {
            return true;
        }
        if (s.charAt(0)!=s.charAt(s.length()-1)) {
            return false;
        }
        return esPalindromo(s.substring(1, s.length()-1));
    }

    public static int Fibonacci(int n){
        if (n==0) return 0;
        if (n==1) return 1;
        return Fibonacci(n-1) + Fibonacci(n-2);
    }

    public static int FibonacciMemo(int n, int []memo){
        if (n==0) return 0;
        if (n==1) return 1;
        if (memo[n] != -1) return memo[n];
        memo[n] = FibonacciMemo(n-1, memo) + FibonacciMemo(n-2, memo);
        return memo[n];
    }

    public static boolean buscarArreglo(int []arr, int i, int valor){
        if (i==arr.length) {
            return false;
        }
        if (arr[i]==valor) {
            return true;
        }
        return buscarArreglo(arr, i+1, valor);
    }
}
