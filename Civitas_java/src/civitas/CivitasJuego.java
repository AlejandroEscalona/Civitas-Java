/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
import java.util.ArrayList;
import java.util.Collections;
import GUI.*;
/**
 *
 * @author alex
 */
public class CivitasJuego {
    
    private int indiceJugadorActual;
    private ArrayList<Jugador>jugadores;
    private Tablero tablero;
    private MazoSorpresas mazo;
    private EstadosJuego estado;
    private GestorEstados gestorEstados;
    
    public CivitasJuego(ArrayList<String> nombres){
        
    jugadores=new ArrayList<>();
        for(int i=0; i<nombres.size(); i++){
            Jugador nuevo = new Jugador( nombres.get(i) );
            jugadores.add(nuevo);
        }
        
        mazo = new MazoSorpresas(true);
        gestorEstados = new GestorEstados();
        inicializarTablero(mazo);
        inicializarMazo(tablero);
        
            
        
        estado = EstadosJuego.INICIO_TURNO;
        indiceJugadorActual = Dado.getInstance().quienEmpieza(nombres.size()) ;
        
    }
    
    void actualizarInfo(){
        System.out.println("Las propiedades del jugador son: ");
        System.out.print( jugadores.get(indiceJugadorActual).getPropiedades() );
        System.out.println("El jugador actual está en la casilla: ");
        System.out.print( jugadores.get(indiceJugadorActual).getNumCasillaActual() );
        System.out.println("¿El jugador actual está en bancarrota? ");
        System.out.print( jugadores.get(indiceJugadorActual).enBancarrota() );
        System.out.println( ranking() );
    }
    
    private void inicializarTablero(MazoSorpresas mazo){
        // 12 tipo calle, 1 juez, 3 sorpresa,1 impuesto, 1 parking, 1 carcel,
                                               //nombre, alqui base,fac, hipoteca base,precio compra, precio edificar
        tablero=new Tablero(6);                                       
        
        TituloPropiedad titulo1 = new TituloPropiedad("Cartagena",400,1.2f,1800,2000,600);
        CasillaCalle casilla1=new CasillaCalle(titulo1);
        tablero.añadeCasilla(casilla1);
        TituloPropiedad titulo2 = new TituloPropiedad("Ogijares",500,1.3f,2200,2500,700);
        CasillaCalle casilla2=new CasillaCalle(titulo2);
        tablero.añadeCasilla(casilla2);
        TituloPropiedad titulo3 = new TituloPropiedad("Pekín",850,1.4f,2500,3000,1200);
        CasillaCalle casilla3=new CasillaCalle(titulo3);
        tablero.añadeCasilla(casilla3);
        TituloPropiedad titulo4 = new TituloPropiedad("Melbourne",900,1.2f,2900,3200,1300);
        CasillaCalle casilla4=new CasillaCalle(titulo4);
        tablero.añadeCasilla(casilla4);
        TituloPropiedad titulo5 = new TituloPropiedad("Barrio Quintana",200,1.2f,900,1300,350);
        CasillaCalle casilla5=new CasillaCalle(titulo5);
        tablero.añadeCasilla(casilla5);
        
        CasillaImpuesto impuesto = new CasillaImpuesto(1000,"impuesto");
        tablero.añadeCasilla(impuesto);
        
        CasillaSorpresa sorpresa1 = new CasillaSorpresa(mazo, "sorpresa1");
        tablero.añadeCasilla(sorpresa1);
        
        TituloPropiedad titulo6 = new TituloPropiedad("Paris",600,1.2f,2000,2500,900);
        CasillaCalle casilla6=new CasillaCalle(titulo6);
        tablero.añadeCasilla(casilla6);
        TituloPropiedad titulo7 = new TituloPropiedad("Oslo",700,1.2f,1600,1950,850);
        CasillaCalle casilla7=new CasillaCalle(titulo7);
        tablero.añadeCasilla(casilla7);
        TituloPropiedad titulo8 = new TituloPropiedad("Toulouse",400,1.2f,1200,1500,700);
        CasillaCalle casilla8=new CasillaCalle(titulo8);
        tablero.añadeCasilla(casilla8);
        TituloPropiedad titulo9 = new TituloPropiedad("Dubai",1500,1.3f,3000,3700,1500);
        CasillaCalle casilla9=new CasillaCalle(titulo9);
        tablero.añadeCasilla(casilla9);
        
         CasillaSorpresa sorpresa2 = new CasillaSorpresa(mazo, "sorpresa2");
        tablero.añadeCasilla(sorpresa2);
        
        CasillaSorpresa sorpresa3 = new CasillaSorpresa(mazo, "sorpresa3");
        tablero.añadeCasilla(sorpresa3);
        
        TituloPropiedad titulo10 = new TituloPropiedad("California",700,1.1f,1800,2500,1000);
        CasillaCalle casilla10=new CasillaCalle(titulo10);
        tablero.añadeCasilla(casilla10);
        TituloPropiedad titulo11 = new TituloPropiedad("Casablanca",500,1.3f,1200,1600,800);
        CasillaCalle casilla11=new CasillaCalle(titulo11);
        tablero.añadeCasilla(casilla11);
        TituloPropiedad titulo12 = new TituloPropiedad("Wellington",1200,1.4f,2000,2300,1000);
        CasillaCalle casilla12=new CasillaCalle(titulo12);
        tablero.añadeCasilla(casilla12);
        
        Casilla parking = new Casilla("parking");
        tablero.añadeCasilla(parking);
        
         tablero.añadeJuez(); 
        
        
    }
    
    private void inicializarMazo(Tablero tablero){
        
        Sorpresa Especulador = new SorpresaEspeculador(1500,"El jugador pasará a especulador");
        mazo.alMazo(Especulador);
        
        Sorpresa ircarcel = new SorpresaIrCarcel(tablero,"Te vas a la carcel :(");
        mazo.alMazo(ircarcel);
        
        Sorpresa irCasilla1 = new SorpresaIrCasilla(tablero,8,"Se mueve a las casilla 8");
        mazo.alMazo(irCasilla1);
        
        Sorpresa irCasilla2 = new SorpresaIrCasilla(tablero,17,"Se mueve a la casilla 17");
        mazo.alMazo(irCasilla2);
        
        Sorpresa irCasilla3 = new SorpresaIrCasilla(tablero,tablero.getCarcel(),"Se mueve a las casilla de la carcel! :( ");
        mazo.alMazo(irCasilla3);
        
        Sorpresa salirCarcel = new SorpresaSalirCarcel(mazo,"Obtienes el salvoconducto :D");
        mazo.alMazo(salirCarcel);
        
        Sorpresa pagar = new SorpresaPagarCobrar(-1200,"Al jugador le toca pagar 1200!");
        mazo.alMazo(pagar);
        
        Sorpresa cobrar = new SorpresaPagarCobrar(1500,"El jugador cobra 1500");
        mazo.alMazo(cobrar);
        
        Sorpresa porCasaHotel1 = new SorpresaPorCasaHotel(-250, "El jugador pagará 250 por cada casa u hotel");
        mazo.alMazo(porCasaHotel1);
        
        Sorpresa porCasaHotel2 = new SorpresaPorCasaHotel(250, "El jugador cobrará 250 por cada casa u hotel");
        mazo.alMazo(porCasaHotel2);
        
        Sorpresa porJugador1 = new SorpresaPorJugador(300,"El jugador cobrará 300 de cada jugador :D");
        mazo.alMazo(porJugador1);
       
        Sorpresa porJugador2 = new SorpresaPorJugador(-300,"El jugador pagará 300 de cada jugador :D");
        mazo.alMazo(porJugador2);
        
      
        
        
    
    }   
    
    private void contabilizarPasoPorSalida(Jugador jugadorActual){
        
        if (tablero.getPorSalida() > 0  ){
            jugadorActual.pasaPorSalida();
        }
    }
    
    private void avanzaJugador(){
        
        Jugador jugadorActual = jugadores.get(indiceJugadorActual);
        int posicionActual =  jugadorActual.getNumCasillaActual();
        int tirada = Dado.getInstance().tirar();
        int posicionNueva = tablero.nuevaPosicion(posicionActual, tirada);
        Casilla casilla =  tablero.getCasilla(posicionNueva);
        this.contabilizarPasoPorSalida(jugadorActual);
        jugadorActual.moverACasilla(posicionNueva);
        casilla.recibeJugador(indiceJugadorActual, jugadores);
        this.contabilizarPasoPorSalida(jugadorActual);
        
        
        
    }
    
    
    private void pasarTurno(){
        
       indiceJugadorActual= (indiceJugadorActual+1)% jugadores.size();
       System.out.println("Es el turno de: "+jugadores.get(indiceJugadorActual));
    }
    
    
    public void siguientePasoCompletado(OperacionesJuego operacion){
        
        Jugador jugadorActual = jugadores.get(indiceJugadorActual);
       estado = gestorEstados.siguienteEstado(jugadorActual, estado, operacion);
        
    }
    
    public OperacionesJuego siguientePaso(){
        
        Jugador jugadorActual = jugadores.get(indiceJugadorActual);//1
        contabilizarPasoPorSalida(jugadorActual); 
        OperacionesJuego operacion = gestorEstados.operacionesPermitidas(jugadorActual, estado); //2
        if(operacion == OperacionesJuego.PASAR_TURNO){
            pasarTurno();//3
            siguientePasoCompletado(operacion);//4
        }
        if(operacion == OperacionesJuego.AVANZAR){
            avanzaJugador(); //5
            siguientePasoCompletado(operacion); //6
            
        }
        contabilizarPasoPorSalida(jugadorActual); 
        return operacion;
        
    }
    
    public boolean construirCasa(int ip){
        
        return jugadores.get(indiceJugadorActual).construirCasa(ip);
    }
    
    public boolean construirHotel(int ip){
        
       return jugadores.get(indiceJugadorActual).construirHotel(ip);
    }
    
    public boolean vender(int ip){
        
       return jugadores.get(indiceJugadorActual).vender(ip);
    }
    
    public boolean hipotecar(int ip){
        
        return jugadores.get(indiceJugadorActual).hipotecar(ip);
    }
    
     public boolean cancelarHipoteca(int ip){
        
        return jugadores.get(indiceJugadorActual).cancelarHipoteca(ip);
    }
     
    public  boolean salirCarcelPagando(){
        
        return jugadores.get(indiceJugadorActual).salirCarcelPagando();
    }
      
     public boolean salirCarcelTirando(){
          
          return jugadores.get(indiceJugadorActual).salirCarcelTirando();
      }
      
     public boolean finalDelJuego(){
          
          boolean vuelta=false;
          for(int i=0; i < jugadores.size(); i++){
           if ( jugadores.get(i).getSaldo() <= 0)
                vuelta =true;
          }
          return vuelta;
      }
      
      public ArrayList<Jugador>ranking(){
      Collections.sort(jugadores);
      return jugadores;
   
      }
      
      public boolean comprar(){
        
          Jugador jugadorActual = jugadores.get(indiceJugadorActual);//1
          int numCasillaActual = jugadorActual.getNumCasillaActual(); //2
          Casilla casilla = tablero.getCasilla(numCasillaActual); //3
          TituloPropiedad titulo = ((CasillaCalle)casilla).getTituloPropiedad(); // 4
         boolean res = jugadorActual.comprar(titulo);//5
          
          return res;
          
      }
      
      public Casilla getCasillaActual(){
          
         return  tablero.getCasilla(jugadores.get(indiceJugadorActual).getNumCasillaActual() );
      }
      
      public Jugador getJugadorActual(){
          return jugadores.get(indiceJugadorActual);
      }
    
      public String infoJugadorTexto(){
      
          return getJugadorActual().toString();
      }
}