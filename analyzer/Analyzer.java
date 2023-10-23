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
        int tam=10;
        String[] complexity = {"1","log(n)","n","n*log(n)","n^2","n^3","2^n"};
        long[] times =new long[tam];
        Chronometer chrono = new Chronometer();
        chrono.pause();
        long n = 10000;
        long n2;
        for(int i = 1 ; i<=tam;i++){
            n2=n*(i*i);
            algorithm.init(n2);
            chrono.resume();
            algorithm.run();
            long time = chrono.getElapsedTime();
            times[i-1]=time;
            chrono.pause();
            //System.out.println(i);
        }
        //System.out.println();
        //show_times(times);
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