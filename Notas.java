 /**
 * @version 1.2/2020
 */
public class Notas {
    double[] listaNotas;

    public Notas() {
        listaNotas = new double[5];
    }

    public double calcularPromedio() {
        double suma = 0;
        for (int i = 0; i < listaNotas.length; i++) {
            suma = suma + listaNotas[i];
        }

        return (suma / listaNotas.length);
    }

    public double calcularDesviacion() {
        double promedio = calcularPromedio();
        double sumaCuadrados = 0;
        for (int i = 0; i < listaNotas.length; i++) {
            sumaCuadrados += Math.pow(listaNotas[i] - promedio, 2);
        }
        return Math.sqrt(sumaCuadrados / listaNotas.length);
    }

    public double notaMayor() {
        double mayor = listaNotas[0];
        for (int i = 1; i < listaNotas.length; i++) {
            if (listaNotas[i] > mayor) {
                mayor = listaNotas[i];
            }
        }
        return mayor;
    }

    public double notaMenor() {
        double menor = listaNotas[0];
        for (int i = 1; i < listaNotas.length; i++) {
            if (listaNotas[i] < menor) {
                menor = listaNotas[i];
            }
        }
        return menor;
    }
}
