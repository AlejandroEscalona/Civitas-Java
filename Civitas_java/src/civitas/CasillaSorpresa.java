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
public class CasillaSorpresa extends Casilla {
    
    private MazoSorpresas mazo;
    
     CasillaSorpresa (MazoSorpresas mazo, String nombre){ 
        super(nombre);
        this.mazo = mazo;   
    }
     
          @Override
    public String toString(){
        String devolver="La casilla es de tipo sorpresa, y la última sorpresa fue: "+mazo.getUltimaSorpresa();
        return devolver;
    }

        @Override
      public void recibeJugador(int iactual, ArrayList<Jugador> todos){
     
        Sorpresa sorpresas;
        
        if(jugadorCorrecto(iactual, todos) ){
            sorpresas = mazo.siguiente();
            super.informe(iactual, todos);
            sorpresas.aplicarAJugador(iactual, todos);
        }
    }
    
}
