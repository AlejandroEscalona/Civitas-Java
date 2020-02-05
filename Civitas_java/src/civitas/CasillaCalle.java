package civitas;

import java.util.ArrayList;

public class CasillaCalle extends Casilla{
   
    private TituloPropiedad tituloPropiedad;
    
    
    //(CasillaCalle(casilla)).getTituloPropiedad;
    // con el @Override   //Super.informe // Super.RecibeJugador   //Super.jugadorCorrecto
   // public void recibeJugador(Jugador jugador);
    
    CasillaCalle (TituloPropiedad titulopropiedad){ 
        super(titulopropiedad.getNombre() );
        this.tituloPropiedad=titulopropiedad;
      
    }
    
    
    TituloPropiedad getTituloPropiedad(){
        return tituloPropiedad;
    }
    
    
    @Override
    public String toString(){
        String devolver="La casilla de tipo calle es "+getTituloPropiedad().toString();
        return devolver;
    }
    

    @Override
      public void recibeJugador(int iactual, ArrayList<Jugador> todos){
        
        if(jugadorCorrecto(iactual, todos)){
            
            super.informe(iactual, todos);
            Jugador nuevo = todos.get(iactual);
           
        if(!tituloPropiedad.tienePropietario()){
            nuevo.puedeComprarCasilla();
        } 
        else{
        tituloPropiedad.tramitarAlquiler(nuevo);    
        }
    }
    }
    
    
    
    
}