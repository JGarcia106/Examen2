/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen2;

/**
 *
 * @author Laboratorio
 */
public class Habitacion {
    //Aquí se establecen las variables privadas.
    private String numero;
    private String tipo;
    private int precio;
    private String estado;
    //Se ingresan los "constructores".
    public Habitacion(String numero, String tipo, int precio, String estado) {
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
        this.estado = estado;
    }
    //Y por acá los setters y getters que nos ayudan en la ventana "main" a encapsular todos los datos y brindarlos. Estos son los mas importantes ya que sin esto el codigo no tiene funcionamiento.
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
