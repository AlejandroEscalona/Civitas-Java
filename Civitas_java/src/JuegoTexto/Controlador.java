package JuegoTexto;

import civitas.CivitasJuego;
import civitas.Diario;
import civitas.OperacionesJuego;
import civitas.SalidasCarcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import civitas.Casilla;
import civitas.GestionesInmobiliarias;
import civitas.Jugador;
import civitas.Respuestas;
import civitas.TituloPropiedad;
import civitas.OperacionInmobiliaria;


public class Controlador {
    private CivitasJuego juego;
    private VistaTextual vista;
    
    Controlador(CivitasJuego juego, VistaTextual vista){
        this.juego=juego;
        this.vista=vista;
    }
    
    void juega(){
        vista.setCivitasJuego(juego);
        while(!juego.finalDelJuego()){
            vista.actualizarVista();
            vista.pausa();
            OperacionesJuego operacion=juego.siguientePaso();
            vista.mostrarSiguienteOperacion(operacion);
            if(operacion!=OperacionesJuego.PASAR_TURNO){
                vista.mostrarEventos();
            }
            if(!juego.finalDelJuego()){
                if(null!=operacion)switch (operacion) {
                    case COMPRAR:
                        if(vista.comprar()==Respuestas.SI){
                            juego.comprar();
                        }
                        juego.siguientePasoCompletado(OperacionesJuego.COMPRAR);               
                        break;
                    case GESTIONAR:
                        vista.gestionar();
                        OperacionInmobiliaria nuevo=new OperacionInmobiliaria(GestionesInmobiliarias.values()[vista.getGestion()],vista.getPropiedad());
                        switch(nuevo.getGestion()){
                            case VENDER:
                                juego.vender(vista.getPropiedad());
                            break;
                            case HIPOTECAR:
                                juego.hipotecar(vista.getPropiedad());
                            break;
                            case CANCELAR_HIPOTECA:
                                juego.cancelarHipoteca(vista.getPropiedad());
                            break;
                            case CONSTRUIR_CASA:
                                juego.construirCasa(vista.getPropiedad());
                            break;
                            case CONSTRUIR_HOTEL:
                                juego.construirHotel(vista.getPropiedad());
                            break;
                            case TERMINAR:
                                juego.siguientePasoCompletado(operacion);
                            break;
                        }
                        break;
                    case SALIR_CARCEL:
                        if(vista.salirCarcel()==SalidasCarcel.PAGANDO){
                            juego.salirCarcelPagando();
                        } else{
                            juego.salirCarcelTirando();
                        }
                        juego.siguientePasoCompletado(OperacionesJuego.SALIR_CARCEL);
                        break;
                }
                
                //MOSTRAR RANKING
                if(juego.finalDelJuego()){
                    System.out.println("El juego ha finalizado debido a que un jugador ha caido en bancarrota. Ranking: ");
                    for(int i=0; i < juego.ranking().size(); i++)
                    System.out.println( juego.ranking().get(i) );
                }
            }
        }
    }
}