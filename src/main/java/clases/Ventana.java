/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.swing.JFrame;


public class Ventana extends JFrame {

    private final int ANCHO = 800, ALTO = 500;
    private TableroJuego lamina;
    private Hilo hilo;

    public  Ventana() {

        setTitle("Pong");
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(null); //ubicando la ventana en el centro de la pantalla
        setResizable(false);
        lamina = new TableroJuego();
        add(lamina);
        addKeyListener(new EventoTeclado());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hilo = new Hilo(lamina);
        hilo.start();

    }
}
