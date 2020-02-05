
package civitas;
import java.util.ArrayList;

public class Casilla {
    public String nombre;
    
    
    //Construye una casilla de tipo DESCANSO
    Casilla(String nombre){
        this.nombre=nombre;                    
    }
    
    public String getNombre(){
        return nombre;
    }

    
    //Informa en el diario
    public void informe(int iactual, ArrayList<Jugador> todos){
        Diario.getInstance().ocurreEvento("El jugador actual "+todos.get(iactual).getNombre()+" esta en una nueva casilla:\n "+toString());
    }
   
    ////Devuelve si el índice del jugador es correcto
    public boolean jugadorCorrecto(int iactual, ArrayList<Jugador> todos){
        return iactual>=0&&iactual<todos.size();
    }
   
  
   void recibeJugador(int iactual, ArrayList<Jugador> todos){
        
   informe(iactual,todos); 
   
   }    
                    
                
    @Override
    public String toString(){
        String devolver="ERROR";
  
        devolver = "tipo casilla descanso: "+nombre;
                
        return devolver;
    }      
        
        
    
    
   
}


