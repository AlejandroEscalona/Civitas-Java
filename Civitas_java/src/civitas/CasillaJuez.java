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
public class CasillaJuez extends Casilla{
    
    private static int carcel;
    
    CasillaJuez(int numCasillaCarcel, String nombre){
        super(nombre);
        carcel = numCasillaCarcel;
    }
    @Override
      public void recibeJugador(int iactual, ArrayList<Jugador> todos){
        if(iactual>=0&&iactual<todos.size()&&todos.get(iactual)!=null){
            super.informe(iactual, todos);
            todos.get(iactual).encarcelar(carcel);
            System.out.println("El jugador ha caido en la casilla del juez, y es enviado a la carcel");
        }
    }
      
    @Override
    public String toString(){
        String devolver="La casilla es de tipo Juez, y la casilla de la carcel es: "+carcel;
        return devolver;
    }
    
}
