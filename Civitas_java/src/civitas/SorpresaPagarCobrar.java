/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author pedrobedmar
 */
public class SorpresaPagarCobrar extends Sorpresa{
    private int valor;
    SorpresaPagarCobrar(int valor, String texto){
        super(texto);
        this.valor=valor;
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        if(actual>=0&&actual<todos.size()&&todos.get(actual)!=null){
            informe(actual,todos);
            todos.get(actual).modificarSaldo(valor);
        }
    }
}
