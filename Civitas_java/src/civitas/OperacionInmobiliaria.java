/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

/**
 *
 * @author alex
 */
public class OperacionInmobiliaria {
    private int numPropiedad;
    private GestionesInmobiliarias gestion;
    
    
   public OperacionInmobiliaria(GestionesInmobiliarias gest,int ip){
        
        numPropiedad=ip;
        gestion = gest;
    }
    
    public int getNumPropiedad(){
        
        return numPropiedad;
    }
    
    public GestionesInmobiliarias getGestion(){
        
        return gestion;
    }
    
}
