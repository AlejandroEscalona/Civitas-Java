package civitas;
import java.util.ArrayList;


public class TestP2 {
    public static void main(String[] args) {
        Jugador uno=new Jugador("Alex");
        //System.out.println(uno);
        uno.recibe(500);
        //System.out.println(uno);
        
        ArrayList <String> jugadores=new ArrayList<>();
        jugadores.add("Alex");
        jugadores.add("Pedro");
        jugadores.add("Manu");
        
        
        CivitasJuego partida = new CivitasJuego(jugadores);
        
        
        System.out.println(jugadores.size());
        Jugador jugador1 = partida.getJugadorActual();
        System.out.println(jugador1);
        
       // partida.pasarTurno();
        
        jugador1 = partida.getJugadorActual();
        System.out.println(jugador1);
        
       // partida.pasarTurno();
        
        jugador1 = partida.getJugadorActual();
        System.out.println(jugador1);
        
       // partida.pasarTurno();
        
        jugador1 = partida.getJugadorActual();
        System.out.println(jugador1);
        
       // partida.pasarTurno();
        
        jugador1 = partida.getJugadorActual();
        System.out.println(jugador1.toString());
        
        System.out.println(partida.infoJugadorTexto());
        
        System.out.println(partida.getCasillaActual());
        
        partida.getJugadorActual().moverACasilla(19);   
        System.out.println(partida.getCasillaActual());
    }
}
