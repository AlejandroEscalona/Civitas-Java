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
public class SorpresaPorJugador extends Sorpresa{
    private int valor;
    SorpresaPorJugador(int valor, String texto){
        super(texto);
        this.valor=valor;
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        if(actual>=0&&actual<todos.size()&&todos.get(actual)!=null){
            informe(actual,todos);                                                              //DUDA: Que constructor utilizo aquí para la sorpresa?
            Sorpresa uno=new SorpresaPagarCobrar(valor*-1,"Decrementa saldo");
            Sorpresa dos=new SorpresaPagarCobrar(valor*todos.size(),"Incrementa saldo");
            for(int i=0;i<todos.size();i++){
                if(actual!=i){
                    uno.aplicarAJugador(i, todos);
                } else {
                    dos.aplicarAJugador(i, todos);
                }
            }
        }
    }
}
