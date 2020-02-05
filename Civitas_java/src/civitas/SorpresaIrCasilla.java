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
public class SorpresaIrCasilla extends Sorpresa{
    private Tablero tablero;
    private int valor;
    SorpresaIrCasilla(Tablero tablero, int valor ,String texto){
        super(texto);
        this.tablero=tablero;
        this.valor=valor;
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){                 
        if(actual>=0&&actual<todos.size()&&todos.get(actual)!=null){
            informe(actual,todos);
            int casillaactual=todos.get(actual).getNumCasillaActual();
            int tirada=tablero.calcularTirada(casillaactual,valor);
            int nposicion=tablero.nuevaPosicion(casillaactual,tirada);
            todos.get(actual).moverACasilla(nposicion);
            tablero.getCasilla(nposicion).recibeJugador(actual, todos);
        }
    }
    
    
}
