package Notas;

/**
 * Esta clase denominada Notas define un array de notas numéricas de tipo double.
 * @version 1.2/2020
 */
public class Notas {
    double[] listaNotas; /* Atributo que identifica un array de notas de tipo double */

    /**
     * Constructor de la clase Notas, instancia un array con 5 notas de tipo double
     */
    public Notas() {
        listaNotas = new double[5]; // Crea un array de 5 notas
    }

    /**
     * Método que calcula el promedio de notas
     * @return El promedio de notas calculado
     */
    double calcularPromedio() {
        double suma = 0;
        for (int i = 1; i < listaNotas.length; i++) { // Se recorre el array
            suma = suma + listaNotas[i]; // Suma las notas del array
        }
        /* Obtiene el promedio como la división de la suma de notas sobre el total de notas */
        return (suma / listaNotas.length);
    }

    /**
     * Método que calcula la desviación estándar de las notas
     * @return La desviación estándar calculada
     */
    double calcularDesviacion() {
        double promedio = calcularPromedio();
        double sumaCuadrados = 0;
        for (int i = 0; i < listaNotas.length; i++) {
            sumaCuadrados += Math.pow(listaNotas[i] - promedio, 2);
        }
        return Math.sqrt(sumaCuadrados / listaNotas.length);
    }

    /**
     * Método que retorna la nota mayor del array
     * @return La nota mayor
     */
    double notaMayor() {
        double mayor = listaNotas[0];
        for (int i = 1; i < listaNotas.length; i++) {
            if (listaNotas[i] > mayor) {
                mayor = listaNotas[i];
            }
        }
        return mayor;
    }

    /**
     * Método que retorna la nota menor del array
     * @return La nota menor
     */
    double notaMenor() {
        double menor = listaNotas[0];
        for (int i = 1; i < listaNotas.length; i++) {
            if (listaNotas[i] < menor) {
                menor = listaNotas[i];
            }
        }
        return menor;
    }
}
