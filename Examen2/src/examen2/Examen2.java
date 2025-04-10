/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package examen2;

import javax.swing.JOptionPane;

/**
 *
 * @author Laboratorio
 */
public class Examen2 {
    //Se establece la longitud del arreglo.
    static Habitacion[][] hotel = new Habitacion[5][5]; // 5 pisos, 5 habitaciones por piso.

    public static void main(String[] args) {
        primeraCargaHotel();
        menu();
    }

    public static void primeraCargaHotel() {
        //Se emplea un "for" para ambos (piso y habitación) y dependiendo del piso se asigna un valor y un estado.
        int numero = 101;
        for (int piso = 0; piso < hotel.length; piso++) {
            for (int hab = 0; hab < hotel[piso].length; hab++) {
                String tipo = ((hab % 2) == 0) ? "Simple" : "Doble";
                int precio = 40;
                if (piso == 0) {
                    precio = 30;
                }
                if (piso == 1 && hab == 0) {
                    precio = 50;
                }
                hotel[piso][hab] = new Habitacion(String.valueOf(numero), tipo, precio, "Libre");
                numero++;
            }
            numero += 95;
        }

        //Estas son habitaciones predeterminadas para darle saber al usario cuales estan sucias/ocupadas y darles un porcentaje. En el menú si le da al número 4 se brinda esta información.
        hotel[0][4].setEstado("Sucia");
        hotel[1][0].setEstado("Ocupada");
        hotel[2][0].setEstado("Sucia");
    }

    public static void menu() {
        //En esta parte se le brinda el menú al usuario, dependiendo de su respuestas en números del 1 al 5 se le manda al respectivo menú.
        boolean salir = false;
        while (!salir) {
            String seleccion = JOptionPane.showInputDialog(
                    "Sistema de Gestión Hotelera\n"
                    + "1. Ver estado de habitaciones\n"
                    + "2. Modificar habitación\n"
                    + "3. Reservar habitación\n"
                    + "4. Ver resumen de ocupación\n"
                    + "5. Salir\nSeleccione una opción:");

            if (seleccion == null) {
                return;
            }
            //Aquí se hace el "if" para ejecutar la respuesta del usuario, unicámente funcional si digita un número del 1-5.
            if (seleccion.equals("1")) {
                mostrarCuartos();
            } else if (seleccion.equals("2")) {
                editarCuartos();
            } else if (seleccion.equals("3")) {
                reservarCuarto();                 //El "equals" en este caso es para redirigir al usuario al menú que escoja dependiendo del numero ingresado.
            } else if (seleccion.equals("4")) {
                sintesisHotel();
            } else if (seleccion.equals("5")) {
                salir = true;
            } else {
                JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }
    }

    public static void mostrarCuartos() {
        //Aquí se desglosa por completo todas las habitaciónes, se da a conocer la matriz y se muestra en pantalla # de cuartos, estado y precio.
        String aviso = "Estado actual de hotel:\n";
        for (int piso = hotel.length - 1; piso >= 0; piso--) {
            aviso += "Piso " + (piso + 1) + "\n";
            for (int hab = 0; hab < hotel[piso].length; hab++) {
                Habitacion h = hotel[piso][hab];
                aviso += h.getNumero() + " | " + h.getEstado() + " | " + h.getTipo() + " | $" + h.getPrecio() + "\n";
            }
            aviso += "\n";
        }
        JOptionPane.showMessageDialog(null, aviso);
    }

    public static void editarCuartos() {
        //En este segmento se le brinda al usuario una pestaña para editar la habitación que desee y luego modificándose en el desglose de habitaciónes (En el menú seleccionando el número 1).
        String digito = JOptionPane.showInputDialog("Ingrese el número de la habitación que desea modificar: ");
        Habitacion h = buscarCuarto(digito);
        if (h != null) {
            //Se le pregunta al usuario el estado, precio e tipo.
            String estadoNuevo = JOptionPane.showInputDialog("¿Que estado nuevo desea? (Libre/Ocupada/Sucia): ", h.getEstado());
            String tipoNuevo = JOptionPane.showInputDialog("¿Que tipo nuevo desea? (Simple/Doble): ", h.getTipo());
            int precioNuevo = Integer.parseInt(JOptionPane.showInputDialog("Nuevo precio: ", h.getPrecio()));
            //Se guardan y se encapsulan los datos.
            h.setEstado(estadoNuevo);
            h.setTipo(tipoNuevo);
            h.setPrecio(precioNuevo);
            //Con esos "JOptionPane.showMessageDialog" se deja saber si la modificación fue hecha con éxito o errónea.
            JOptionPane.showMessageDialog(null, "La habitación fue modificada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "La habitación no se encontró.");
        }
    }

    public static void reservarCuarto() {
        //Este es el espacio de para reservar el cuarto. Se le pregunta al usuario el numero de habitación.
        String digito = JOptionPane.showInputDialog("Ingrese el número de habitación que quiere reservar: ");
        Habitacion h = buscarCuarto(digito);
        if (h != null) {
            //Aquí se deja saber si la reserva fue exitosa o no, dependiendo de el digíto ingresado en el apartado anterior.
            //El "equalsIgnoreCase" es utilizado por si el usuario no brinda nada y solo presiona la tecla enter.
            if (h.getEstado().equalsIgnoreCase("Libre")) {
                h.setEstado("Ocupada");
                JOptionPane.showMessageDialog(null, "Reserva realizada con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "Está habitación no se encuentra disponible");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró está habitación");
        }
    }

    public static void sintesisHotel() {
        //Este es el resumen o la síntesis del hotel donde se ejecutan las sumatorias a los valores en 0 dependiendo de las respuestas del usuario.
        int libres = 0, ocupadas = 0, sucias = 0, profit = 0;
        int total = hotel.length * hotel[0].length;
        //Aquí se realizan las sumatorias
        for (int piso = 0; piso < hotel.length; piso++) {
            for (int hab = 0; hab < hotel[piso].length; hab++) {
                String estado = hotel[piso][hab].getEstado();
                //El "equals" en este caso es utilizado para brindar una sumatoria
                if (estado.equals("Libre")) {
                    libres++;
                //El "equals" en este caso es utilizado para brindar una sumatoria
                } else if (estado.equals("Ocupada")) {
                    ocupadas++;
                    profit += hotel[piso][hab].getPrecio();
                //El "equals" en este caso es utilizado para brindar una sumatoria
                } else if (estado.equals("Sucia")) {
                    sucias++;
                }
            }
        }
        //En este espacio se resume la ocupación dando porcentajes dependiendo de los numeros que brinde el usuario. Ya que depende de cada digíto dar un resultado distinto.
        String aviso = "Resumen de ocupación:\n"
                + "Habitaciones libres: " + libres + " (" + (libres * 100 / total) + "%)\n"
                + "Habitaciones ocupadas: " + ocupadas + " (" + (ocupadas * 100 / total) + "%)\n"
                + "Habitaciones sucias: " + sucias + " (" + (sucias * 100 / total) + "%)\n"
                + "Ganancia actual: $" + profit;

        JOptionPane.showMessageDialog(null, aviso);
    }

    public static Habitacion buscarCuarto(String digito) {
        //Aquí simplemnte se genera un for para ambos (piso y habitación) nuevamente para buscar el cuarto ingresado y saber sus especificaciones.
        for (int piso = 0; piso < hotel.length; piso++) {
            for (int hab = 0; hab < hotel[piso].length; hab++) {
                if (hotel[piso][hab].getNumero().equals(digito)) {
                    return hotel[piso][hab];
                }
            }
        }
        return null;
    }
}
