package analyzer;
import java.lang.Math;
import java.util.Arrays;

public class Analyzer implements Runnable {
    Algorithm algorithm;
    long maxExecutionTime;
    String complexity = null;
    
    public Analyzer(Algorithm algorithm, long maxExecutionTime) {
        this.algorithm = algorithm;
        this.maxExecutionTime = maxExecutionTime;
    }

    public String getComplexity() {
        return complexity;
    }

    @Override
    public void run() {
        complexity = findComplexityOf(algorithm, maxExecutionTime);
    }
/*
    static long calculate_time_average(){
        long result=0;
        int mean=3;
        for(int i = 0; i<mean;i++){//mean of 3 results

            //result+=l[i];
        }
        return result/mean;
    }
*/
    public static double calcularDesviacionTipica(long[] array) {
        if (array.length < 2) {
            throw new IllegalArgumentException("El array debe tener al menos 2 elementos para calcular la desviación estándar.");
        }

        // Calcular la media
        double media = Arrays.stream(array).average().orElse(0);

        // Calcular la suma de los cuadrados de las diferencias
        double sumaCuadradosDiferencias = Arrays.stream(array)
                .mapToDouble(value -> (value - media) * (value - media))
                .sum();

        // Dividir la suma de los cuadrados por la cantidad de elementos y tomar la raíz cuadrada
        double desviacionTipica = Math.sqrt(sumaCuadradosDiferencias / (array.length - 1));

        return desviacionTipica;
    }
     public static double calcularDesviacionEstandar(long[] array) {
        if (array.length < 2) {
            throw new IllegalArgumentException("El array debe tener al menos 2 elementos para calcular la desviación estándar.");
        }

        // Calcular la media
        double media = Arrays.stream(array).average().orElse(0);

        // Calcular la suma de los cuadrados de las diferencias
        double sumaCuadradosDiferencias = Arrays.stream(array)
                .mapToDouble(value -> (value - media) * (value - media))
                .sum();

        // Dividir la suma de los cuadrados por la cantidad de elementos y tomar la raíz cuadrada
        double desviacionEstandar = Math.sqrt(sumaCuadradosDiferencias / array.length);

        return desviacionEstandar;
    }

    static void show_times(long[] t){
        for(int i = 1 ; i<=t.length;i++){
            System.out.print(Math.pow(2,i)+" * 10^4 = "+ (double)(t[i-1])/1000 +"\t" );
           // System.out.print(i+"*10^4 = "+ t[i-1] +"\t");
        }
        System.out.println("Desviacion tipica: "+calcularDesviacionTipica(t)/1000 + ", Desviacion estandar: "+calcularDesviacionEstandar(t)/1000);

    }
    static String findComplexityOf(Algorithm algorithm, long maxExecutionTime) {
        // Modify the content of this method in order to find the complexity of the given algorithm.
        // You can delete any of the following instructions if you don't need them. You can also
        // add new instructions or new methods, but you cannot modify the signature of this method
        // nor the existing methods.
        //numero catalan tam=5,n=300
        //CONSTANT--
        //"Constant","EuclideanDistance","LinearSearchConstant","MatrixMultiplicationConstant"
        //Constant
        //EuclideanDistance
        //LinearSearchConstant
        //MatrixMultiplicationConstant
        //log--
        // tam=12, n =100000000, es el que tenga mayo desviacion tipica
        // N --
        //"LinearSearch","MinMax" tam =10 n = 10000
        //"FibonacciIterative","Linear" tam = 10, n = 50
        //nlog n  -- 
        //"HeapSort" tam = 7 , n = 12000;
        //"Linearithmic" tam = 7, n = 31;
        //"MergeSort" tam=7 , n=30000;
        //"Skyline" tam = 7, n= 100;
        //N^2 --
        //CatalanNumber tam =5, n=400
        //"BubleSort" tam=5, n=170;
        //"Quadratic" tam=4 , n=5
        //"SelectionSort" tam =6 , n=90
        // N^3 --
        //"Cubic" tam = 4, n = 2;
        //"Floyd" tam = 4, n=37;
        //"MatrixMultiplication"  tam=4, n=120
        //"Warshall" tam =4, n=30;
        //2^N --
        //BipartiteGraphBruteForce
        //"BruteForceKnapsack" tam =2 , n =5;
        //"Exponential" tam = 2 , n = 3
        //"FibonacciRecursive" tam = 2, n=11
        
        int tam=10;
        //String[] complexities = {"1","log(n)","n","n*log(n)","n^2","n^3","2^n"};
        long[] times =new long[tam];
        Chronometer chrono = new Chronometer();
        chrono.pause();
        long n =100000;
        long n2;
        for(int i = 1 ; i<=tam;i++){
            n2=n*(i+i);
            algorithm.init(n2);
            chrono.resume();
            algorithm.run();
            long time = chrono.getElapsedTime();
            times[i-1]=time;
            chrono.pause();
            //System.out.println(i);
        }
        //System.out.println();
        show_times(times);
        //ya tenemos los tiempos, ahora calcular los resultados teoricos.

        long[] tlog= new long[tam];
        long[] tn = new long[tam];
        long[] tnlogn = new long[tam];
        long[] tn2 = new long[tam];
        long[] tn3 = new long[tam];
        long[] t2n = new long[tam];
        for(int i = 1; i<=tam;i++){
            //System.out.println(i);
            tlog[i-1]=times[i-1]/(long)(Math.log((i+4)+1));
            tn[i-1]=times[i-1]/ (long)(i+1);
            tnlogn[i-1]=times[i-1]/ (long)((Math.pow(2,i))*Math.log(Math.pow(2,i)+1)) ;
            tn2[i-1]=times[i-1]/(long)(Math.pow(Math.pow(2,i),2));
            tn3[i-1]=times[i-1]/(long)(Math.pow(Math.pow(2,i),3));
            t2n[i-1]=times[i-1]/ (long)(Math.pow(2,Math.pow(2,i)));
           // System.out.println();
        }
        //System.out.println(times);
        System.out.println("log");
        show_times(tlog);
                        System.out.println("-------------------------");

        System.out.println("n");
        show_times(tn);
                        System.out.println("-------------------------");

        System.out.println("n*log");
        show_times(tnlogn);
                        System.out.println("-------------------------");

        System.out.println("n^2");
        show_times(tn2);
                        System.out.println("-------------------------");

        System.out.println("n^3");
        show_times(tn3);
                        System.out.println("-------------------------");

        System.out.println("2^n");
        show_times(t2n);
                        System.out.println("-------------------------");

                System.out.println("-------------------------");
        System.out.println("");

        String complejidad="1";
        if((calcularDesviacionTipica(times)/1000) <0.1){
            complejidad="1";
        }else if((double)(tn[tam-1])/1000 <0.0011) {
            complejidad = "log(n)";
        }else if((double)(tnlogn[tam-1])/1000 <0.001){
            complejidad="n";
        }else if((double)(tn2[tam-1])/1000<0.001){
            complejidad="n*log(n)";
        }else if((double)(tn3[tam-1])/1000<0.001){
            complejidad="n^2";
        }else if((double)(t2n[tam-1])/1000<0.001){
            complejidad="n^3";
        }else if((double)(t2n[tam-1])/1000>0.001){
            complejidad="2^n";
        }
        
        return complejidad;
    }
    static String findComplexityOf1(Algorithm algorithm, long maxExecutionTime) {
        // Modify the content of this method in order to find the complexity of the given algorithm.
        // You can delete any of the following instructions if you don't need them. You can also
        // add new instructions or new methods, but you cannot modify the signature of this method
        // nor the existing methods.
        //numero catalan tam=5,n=300
        //CONSTANT--
        //CUBIC N^3 --
        int tam=10;
        String[] complexity = {"1","log(n)","n","n*log(n)","n^2","n^3","2^n"};
        long[] times =new long[tam];
        Chronometer chrono = new Chronometer();
        chrono.pause();
        long n = 5;
        long n2;
        for(int i = 1 ; i<=tam;i++){
            n2=n*(i+i);
            algorithm.init(n2);
            chrono.resume();
            algorithm.run();
            long time = chrono.getElapsedTime();
            times[i-1]=time;
            chrono.pause();
            //System.out.println(i);
        }
        //System.out.println();
        show_times(times);
        //ya tenemos los tiempos, ahora calcular los resultados teoricos.

        long[] tlog= new long[tam];
        long[] tn = new long[tam];
        long[] tnlogn = new long[tam];
        long[] tn2 = new long[tam];
        long[] tn3 = new long[tam];
        long[] t2n = new long[tam];
        for(int i = 1; i<=tam;i++){
            //System.out.println(i);
            tlog[i-1]=times[i-1]/(long)(Math.log(Math.pow(2,((i+4)))));
            tn[i-1]=times[i-1]/ (long)(Math.pow(2,i));
            tnlogn[i-1]=times[i-1]/ (long)((Math.pow(2,i))*Math.log(Math.pow(2,i)+1)) ;
            tn2[i-1]=times[i-1]/(long)(Math.pow(Math.pow(2,i),2));
            tn3[i-1]=times[i-1]/(long)(Math.pow(Math.pow(2,i),3));
            t2n[i-1]=times[i-1]/ (long)(Math.pow(2,Math.pow(2,i)));
           // System.out.println();
        }
        //System.out.println(times);
        System.out.println("log");
        show_times(tlog);
        System.out.println("n");
        show_times(tn);
        System.out.println("n*log");
        show_times(tnlogn);
        System.out.println("n^2");
        show_times(tn2);
        System.out.println("n^3");
        show_times(tn3);
        System.out.println("2^n");
        show_times(t2n);

        /*if(time > 0.1) {
            complexity = "log(n)";
        }*/
        return "n";
    }
}