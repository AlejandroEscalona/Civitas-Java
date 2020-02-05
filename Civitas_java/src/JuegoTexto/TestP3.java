/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
package JuegoTexto;
import java.util.ArrayList;
import civitas.CivitasJuego;
import civitas.Diario;
import civitas.OperacionesJuego;
import civitas.SalidasCarcel;
import java.util.Arrays;
import java.util.Scanner;
import civitas.Casilla;
import civitas.GestionesInmobiliarias;
import civitas.Jugador;
import civitas.Respuestas;
import civitas.TituloPropiedad;
//import civitas.Dado;




public class TestP3 {
    
    
    public static void main(String[] args) {
    
    VistaTextual vistatextual = new VistaTextual();
    
    ArrayList <String> jugadores = new ArrayList<>();
    
    
    Scanner input = new Scanner(System.in);
    
    System.out.println("Quieres utilizar tus propios nombres de jugador?   Si - No");
    String s = input.nextLine();
    if("si".equals(s) || "Si".equals(s) || "SI".equals(s)){
        System.out.println("Introduce el número de jugadores que van a jugar: ");
        String numero=input.nextLine();
        int numJugadores = Integer.parseInt(numero);
        for(int i=0;i<numJugadores;i++){
            System.out.println("Introduce el nombre del jugador "+i+":");
            String nombre=input.nextLine();
            jugadores.add(nombre);
        }
    } else {
        jugadores.add("Alex");
        jugadores.add("Pedro");
        jugadores.add("Laurita");
        jugadores.add("Paquita");
    }
    
    
    CivitasJuego partida = new CivitasJuego(jugadores);
  
    //Dado.getInstance().setDebug(true);
    
    Controlador controlador = new Controlador(partida,vistatextual);
    controlador.juega();
    
    } //fin del main
}// fin de class
