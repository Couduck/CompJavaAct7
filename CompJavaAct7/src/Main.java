import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws java.io.IOException
    {
        int[] valoresEntradas = ingresarInformacion();
        int[] arregloPrimos = obtenerPrimos(valoresEntradas[0]);
        int[] arregloFibonacci = obtenerNumerosFibonacci(valoresEntradas[1]);
        imprimirResultados(arregloPrimos, arregloFibonacci);
    }

    public static int[] ingresarInformacion() throws java.io.IOException
    {
        Scanner intro = new Scanner(System.in);      //Scanner que permite la entrada de datos al programa
        int numPrimos = 0, numFibonacci = 0;
        boolean primoValido = false, fibonacciValida = false;     //Verifican si el numero de primos y el numero de términos fibonacci son correctos

        do  //Este ciclo se repetirá hasta que el numero de valores primos a imprimir sea un valor aceptable
        {
            try
            {
                System.out.print("-----\nIngrese el numero de primos que desea obtener: ");
                numPrimos = intro.nextInt();

                if(numPrimos < 1)   //No se aceptan numeros menores a 1
                {
                    System.out.print("-----\nEl numero ingresado solamente puede ser 1 o mayor\n\nPresione ENTER para continuar . . .");
                    System.in.read();
                    System.out.println();
                }

                primoValido = true;
            }

            catch(InputMismatchException a)     //Si lo ingresado no puede convertirse a int, se genera un InputMismatchException y se pide que se ingresen nuevamente los valores
            {
                System.out.print("-----\nEl valor ingresado no puede ser transformado a un numero entero, favor de intentar de nuevo\n\nPresione ENTER para continuar . . .");
                System.in.read();
                System.out.println();
                intro.next();
            }
        }
        while(!primoValido);

        do  //Este ciclo se repetirá hasta que el valores primos a imprimir sea un valor aceptable
        {
            try
            {
                System.out.print("-----\nIngrese el numero de factores Fibonacci que desea obtener: ");
                numFibonacci = intro.nextInt();

                if(numPrimos < 1)   //No se aceptan numeros menores a 1
                {
                    System.out.print("-----\nEl numero ingresado solamente puede ser 1 o mayor\n\nPresione ENTER para continuar . . .");
                    System.in.read();
                    System.out.println();
                }

                fibonacciValida = true;
            }

            catch(InputMismatchException a)     //Si lo ingresado no puede convertirse a int, se genera un InputMismatchException y se pide que se ingresen nuevamente los valores
            {
                System.out.print("-----\nEl valor ingresado no puede ser transformado a un numero entero, favor de intentar de nuevo\n\nPresione ENTER para continuar . . .");
                System.in.read();
                System.out.println();
                intro.next();
            }
        }
        while(!fibonacciValida);

        return new int[]{numPrimos, numFibonacci};
    }

    public static int[] obtenerPrimos(int totalPrimos)      //En este método se obtienen los numeros primos que van desde el 2 hasta el "n" numero primo
    {
        int[] primosRegresar = new int[totalPrimos];    //Se crea el arreglo de los números primos a regresar
        int primosObtenidos = 0, numActual = 2;         //Se inicializa el total de números primos obtenidos actualmente ademas del numero actual que se analizará si es primo o no (siempre comienza en 2 ya que ningun numero anterior es a él es primo)

        while(primosObtenidos < totalPrimos)    //Mientras el numero de primos obtenidos no sea igual a los numeros primos solicitados, el ciclo se repetirá
        {
            if(esPrimo(numActual))      //Se evalua si el numero actual es primo
            {
                //Si es primo: se añade al arreglo y se actualiza la cantidad de primos obtenidos
                primosRegresar[primosObtenidos] = numActual;
                primosObtenidos++;
            }

            //Se actualiza el numero actual sin importar que
            numActual++;
        }

        return primosRegresar;  //Se regresa el arreglo completo
    }

    public static int[] obtenerNumerosFibonacci(int totalFibonacci)     //En este método se obtienen los términos Fibonacci que van desde el primero hasta el "n" término
    {
        int[] fibonacciRegresar = new int[totalFibonacci];  //Se crea el arreglo a devolvercon los terminos Fibonacci
        int numerosObtenidos = 0;   //Total de terminos obtenidos
        int fibonacci1 = 0, fibonacci2 = 1, fibonacciAux;   //Almacena el primer valor fibonacci de la iteración, el segundo valor y uno auxiliar en donde guardar los valores para no perderlos

        while(numerosObtenidos < totalFibonacci)    //Hasta que no se encuentren todos los terminos Fibonacci deseados
        {
            /*
                1° Fibonacci 2 se añade al arreglo a devolver
                2° La suma de Fibonacci 1 y Fibonacci 2 (Es decir, el siguiente valor que Fibonacci 2 debe tomar) se guarda en Fibonacci auxiliar
                3° Fibonacci 1 adquiere el valor de Fibonacci 2
                4° Fibonacci 2 adquiere el valor de Fibonacci auxiliar
                5° Se actualiza el numero de términos de Fibonacci encontrados
             */
            fibonacciRegresar[numerosObtenidos] = fibonacci2;
            fibonacciAux = fibonacci1 + fibonacci2;
            fibonacci1 = fibonacci2;
            fibonacci2 = fibonacciAux;
            numerosObtenidos++;
        }

        return fibonacciRegresar;   //Se regresa el arreglo final
    }

    public static void imprimirResultados(int[] arregloPrimos, int[] arregloFibonacci)  //Metodo en el que se imprimen los arreglos creados
    {
        System.out.println("-----\nNumeros Primos hasta el " + arregloPrimos.length + "° termino\n" + imprimirArreglo(arregloPrimos));
        System.out.println("-----\nSucesion Fibonacci hasta el " + arregloFibonacci.length + "° termino\n" + imprimirArreglo(arregloFibonacci));
    }

    public static boolean esPrimo(int numero)   //Metodo que comprueba si el numero proporcionado es primo
    {
        boolean primo = true;   //Variable que comprueba que un numero sea primo, se asume que lo es inicialmente

        switch (numero)     //Se comprueba el valor del numero
        {
            case 2:
            case 3:
            case 5:
            case 7:
                //Si es igual a cualquiera de los 4 de arriba, no hay necesidaad de hacer el proceso ya que es un primo automátocamente
            break;

            default:
                //Se declara el arreglo con el cual comprobar si el numero actual es uno primo
                int[] primosPrincipales = {2,3,5,7};

                //Si el residuo del numero a evaluar entre cualquiera de los 4 anteriores es iguala 0, significa que tiene otro divisor y por lo tanto, no es primo
                for(byte i = 0; i < 4; i++)
                {
                    if(numero % primosPrincipales[i] == 0)
                    {
                        primo = false;
                        break;
                    }
                }

            break;
        }

        //Se regresa si es primo o no
        return primo;
    }

    public static String imprimirArreglo(int[] arreglo)     //Transforma el arreglo que recibe a una cadena de texto que puede leerse asi: Arreglo original: {1,2,3} --> 1, 2, 3.
    {
        StringBuilder arregloAString = new StringBuilder();

        for (byte i = 0; i < arreglo.length; i++)
        {
            if(i == arreglo.length-1)
            {
                arregloAString.append((arreglo[i])).append(".");
            }

            else
            {
                arregloAString.append((arreglo[i])).append(", ");
            }
        }

        return arregloAString.toString();
    }
}