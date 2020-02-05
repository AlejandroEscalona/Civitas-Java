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

class VistaTextual {
  
  CivitasJuego juegoModel; 
  int iGestion=-1;
  int iPropiedad=-1;
  private static String separador = "=====================";
  
  private Scanner in;
  
  VistaTextual () {
    in = new Scanner (System.in);
  }
  
  void mostrarEstado(String estado) {
    System.out.println (estado);
  }
              
  void pausa() {
    System.out.print("Pulsa una tecla: ");
    in.nextLine();
  }

  int leeEntero (int max, String msg1, String msg2) {
    Boolean ok;
    String cadena;
    int numero = -1;
    do {
      System.out.print (msg1);
      cadena = in.nextLine();
      try {  
        numero = Integer.parseInt(cadena);
        ok = true;
      } catch (NumberFormatException e) { // No se ha introducido un entero
        System.out.println (msg2);
        ok = false;  
      }
      if (ok && (numero < 0 || numero >= max)) {
        System.out.println (msg2);
        ok = false;
      }
    } while (!ok);

    return numero;
  }

  int menu (String titulo, ArrayList<String> lista) {
    String tab = "  ";
    int opcion;
    System.out.println (titulo);
    for (int i = 0; i < lista.size(); i++) {
      System.out.println (tab+i+"-"+lista.get(i));
    }

    opcion = leeEntero(lista.size(),
                          "\n"+tab+"Elige una opción: ",
                          tab+"Valor erróneo");
    return opcion;
  }

  SalidasCarcel salirCarcel() {
    int opcion = menu ("Elige la forma para intentar salir de la carcel",
      new ArrayList<> (Arrays.asList("Pagando","Tirando el dado")));
    return (SalidasCarcel.values()[opcion]);
  }

  Respuestas comprar() {
  
    Scanner sc = new Scanner(System.in);
       
  System.out.println("¿Desea comprar la calle a la que ha llegado?    Si - No");
 
   String s = sc.nextLine();
  
   boolean si= ("si".equals(s) || "Si".equals(s) || "SI".equals(s));
   
   if(si){
       return Respuestas.SI;
   }
   else{
       return Respuestas.NO;
   }
      
  }

  void gestionar () {
      
  String titulo= "Numero de gestión elegida?";
  ArrayList<String> lista;
  lista = new ArrayList<>();
  lista.add("VENDER");
  lista.add("HIPOTECAR");
  lista.add("CANCELAR_HIPOTECA");
  lista.add("CONSTRUIR_CASA");
  lista.add("CONSTRUIR_HOTEL");
  lista.add("TERMINAR");
  
  int seleccion = menu(titulo,lista);
  switch (seleccion){
      case 0: iGestion = 0;
      break;
      case 1: iGestion = 1;
      break;
      case 2: iGestion = 2;
      break;
      case 3: iGestion = 3;
      break;
      case 4: iGestion = 4;
      break;
      case 5: iGestion = 5;
      break;
  }
  
  if(iGestion != 5){
    //Scanner zz = new Scanner(System.in);
    //System.out.println("Ahora introduce el indice de la propiedad a gestionar: ");
    //int a = zz.nextInt();
    //iPropiedad = a;
    ArrayList<TituloPropiedad>  propiedades_titulo;
      ArrayList<String>  propiedades_nombre = new ArrayList<>();
    propiedades_titulo = juegoModel.getJugadorActual().getPropiedades();
    
    for(int i=0; i < propiedades_titulo.size(); i++ )
    {
        propiedades_nombre.add(propiedades_titulo.get(i).getNombre() );
    }
    
    int opcion = menu ("Sobre que propiedad?",propiedades_nombre);
    iPropiedad=opcion;
  }
  
  }
  
  public int getGestion(){
  
      return iGestion;
  }
  
  public int getPropiedad(){
  
      return iPropiedad;
  }
    

  void mostrarSiguienteOperacion(OperacionesJuego operacion) {
                 
      System.out.println( operacion );    
      
              
      
  }


  void mostrarEventos() {
  
      while (Diario.getInstance().eventosPendientes() ){
          
        System.out.println(Diario.getInstance().leerEvento());
      }
  }
  
  public void setCivitasJuego(CivitasJuego civitas){ 
        juegoModel=civitas;
        this.actualizarVista();

    }
  
  void actualizarVista(){
  
      juegoModel.getJugadorActual().toString();
      System.out.println(juegoModel.getCasillaActual());
             
      
  } 
}