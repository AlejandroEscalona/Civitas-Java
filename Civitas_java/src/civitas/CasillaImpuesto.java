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
public class CasillaImpuesto extends Casilla {
    
    private int importe;
    
    
    
     CasillaImpuesto (int cantidad,String nombre){ 
        super(nombre);
        this.importe = cantidad;
    }
     
     
       @Override
    public String toString(){
        String devolver="La casilla es de tipo impuesto y tiene un importe de: "+importe;
        return devolver;
    }
    
    @Override
     public void recibeJugador(int iactual, ArrayList<Jugador> todos){
        if(iactual>=0&&iactual<todos.size()&&todos.get(iactual)!=null){
            super.informe(iactual,todos);
            todos.get(iactual).pagaImpuesto(importe);
        }
    }
    
    
}
