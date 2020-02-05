/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author alex
 */
public class SorpresaEspeculador extends Sorpresa{
    
    private int fianza;
    
    SorpresaEspeculador( int fianza, String texto){
        super(texto);
        this.fianza = fianza;
    }
    
     @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        if(actual>=0&&actual<todos.size()&&todos.get(actual)!=null){
            informe(actual,todos);
            JugadorEspeculador jugador = new JugadorEspeculador( todos.get(actual) , fianza);
            todos.remove(actual);
            todos.add(jugador);
        }
    }
    
}
