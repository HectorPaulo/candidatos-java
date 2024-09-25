import java.util.InputMismatchException;
import java.util.Scanner;

class Candidato {
    private String nombre;
    private String rfc;
    private int edad;
    private double estatura;
    private double peso;
    private double imc;
    private double promedioBachillerato;

    public Candidato(String nombre, String rfc, int edad, double estatura, double peso, double promedioBachillerato) {
        this.nombre = nombre;
        this.rfc = rfc;
        this.edad = edad;
        this.estatura = estatura;
        this.peso = peso;
        this.promedioBachillerato = promedioBachillerato;
        this.imc = calcularIMC();
    }

    private double calcularIMC() {
        if (estatura == 0) {
            throw new ArithmeticException("Estatura no puede ser cero.");
        }
        return peso / (estatura * estatura);
    }

    public boolean esValido() {
        return validarRFC(rfc) && edad > 18 && estatura >= 1.65 && imc > 18.5 && imc < 25;
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("RFC: " + rfc);
        System.out.println("Edad: " + edad);
        System.out.println("Estatura: " + estatura);
        System.out.println("Peso: " + peso);
        System.out.println("IMC: " + imc);
        System.out.println("Promedio Bachillerato: " + promedioBachillerato);
        System.out.println("Candidato válido: " + (esValido() ? "Sí" : "No"));
    }

    private boolean validarRFC(String rfc) {
        return rfc.length() == 13;
    }
}

public class Inscripcion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Ingrese el nombre del candidato:");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese el RFC del candidato (13 caracteres):");
            String rfc = scanner.nextLine();

            System.out.println("Ingrese la edad del candidato:");
            int edad = scanner.nextInt();
            if (edad <= 18) {
                System.out.println("La edad debe ser mayor de 18 años.");
                return;
            }

            System.out.println("Ingrese la estatura del candidato (en centímetros):");
            double estatura = scanner.nextDouble();
            if (estatura < 165) {
                System.out.println("La estatura debe ser mayor o igual a 1.65 metros.");
                return;
            }

            System.out.println("Ingrese el peso del candidato (en kilogramos):");
            double peso = scanner.nextDouble();

            System.out.println("Ingrese el promedio de bachillerato del candidato:");
            double promedioBachillerato = scanner.nextDouble();

            if (!validarRFC(rfc)) {
                System.out.println("El RFC debe tener exactamente 13 caracteres.");
                return;
            }

            Candidato candidato = new Candidato(nombre, rfc, edad, estatura, peso, promedioBachillerato);
            candidato.mostrarInformacion();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, ingrese el tipo de dato correcto.");
        } catch (ArithmeticException e) {
            System.out.println("Error al calcular IMC: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static boolean validarRFC(String rfc) {
        return rfc.length() == 13;
    }
}
