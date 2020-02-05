package civitas;
import java.util.ArrayList;

abstract public class Sorpresa {
    private String texto;                                       //DUDA: Son los atributos en java privados por defecto o tienen visibilidad de paquete?
                            //Se puede poner un atributo al ser abstract
    Sorpresa(String texto){
        this.texto=texto;
    }
    
    /*
    //Constructor para crear una sorpresa que envía a la cárcel
    Sorpresa(TipoSorpresa tipo, Tablero tablero){          
        init();
        this.tablero=tablero;
        this.tipo=tipo;
        //Inicializados por mi:
        texto="Carta sorpresa que te envia a la carcel :( ";
    }
    
    //Constructor para crear una sorpresa que envía al jugador a otra casilla
    Sorpresa(TipoSorpresa tipo, Tablero tablero, int valor, String texto){
        init();
        this.tipo=tipo;
        this.tablero=tablero;
        this.valor=valor;
        this.texto=texto;
    }
    
    //Constructor que crea el resto de sorpresas
    Sorpresa(TipoSorpresa tipo, int valor, String texto){
        init();
        this.tipo=tipo;
        this.valor=valor;
        this.texto=texto;
    }
    
    //Constructor que crea la sorpresa que permite evitar la cárcel    
    Sorpresa(TipoSorpresa tipo, MazoSorpresas mazo){
        init();
        this.tipo=tipo;
        this.mazo=mazo;
        //Inicializados por mi:
        texto="Con esta carta sorpresa puedes evitar ir a la carcel";
    }
    */
    //Redirecciona a la función aplicarAJugador_XXXX correspondiente,
    //según el tipo de sorpresa a la que se aplique

    
    //Traslada al jugador a la casilla indicada en el atributo valor del tablero indicado
    //en el atributo tablero
    
    
    //Modifica el saldo de un jugador en la cantidad indicada en el atributo valor
    
    //Modifica el saldo del jugador con el valor de la sorpresa multiplicado por el numero de
    //casas y hoteles del jugador
    
    //Los demas jugadores pagan al jugador pasado por argumento el valor de la sorpresa
    
    //Si nadie más tiene la carta para escapar de la cárcel se le entrega al jugador del argumento
    
    //Informa en el diario de que se aplica una sorpresa a un jugador
    protected void informe(int actual, ArrayList<Jugador> todos){
        Diario.getInstance().ocurreEvento("Aplicando sorpresa a "+todos.get(actual).getNombre());
    }
    
    
    //Devuelve si el índice del jugador es correcto
    public boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos){       //Duda: pongo si el jugador es null o no?
        return actual>=0&&actual<todos.size();
    }
    
    //Si la carta sorpresa es de tipo SALIRCARCEL la inhabilita
    
    //Devuelve el nombre de la sorpresa
    @Override
    public String toString(){       
        return texto;
    }
    
    abstract void aplicarAJugador(int actual, ArrayList<Jugador> todos);
    
    //Si la carta sorpresa es de tipo SALIRCARCEL la habilita
}