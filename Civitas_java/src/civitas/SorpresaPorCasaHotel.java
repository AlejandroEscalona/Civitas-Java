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
public class SorpresaPorCasaHotel extends Sorpresa{
    private int valor;
    SorpresaPorCasaHotel(int valor, String texto){
        super(texto);
        this.valor=valor;
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        if(actual>=0&&actual<todos.size()&&todos.get(actual)!=null){
            informe(actual,todos);
            todos.get(actual).modificarSaldo(valor*(todos.get(actual).cantidadCasasHoteles()));                        //DUDA SIN ACABAR: Con que metodo obtengo las casas y hoteles de un jugador? Vale lo mismo una casa que un hotel para lo que tiene que pagar?
        }
    }
}
