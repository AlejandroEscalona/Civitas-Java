 
package civitas;

public class TituloPropiedad {
    private float alquilerBase;
    private static float factorInteresesHipoteca=1.1f;
    private float factorRevalorizacion;
    private float hipotecaBase;
    private boolean hipotecado;
    private String nombre;
    private int numCasas;
    private int numHoteles;
    private float precioCompra;
    private float precioEdificar;
    
    //Atributos de referencia
    private Jugador propietario;
    
    //Constructor
    TituloPropiedad(String nom, float ab, float fr, float hb, float pc, float pe){
        nombre=nom;
        alquilerBase=ab;
        factorRevalorizacion=fr;
        hipotecaBase=hb;
        precioCompra=pc;
        precioEdificar=pe;
        propietario=null;
        numCasas=0;
        numHoteles=0;
        hipotecado=false;
    }
    
    //Cambia el atributo propietario por el del parámetro
    void actualizaPropietarioPorConversion(Jugador jugador){
        propietario=jugador;
    }
    
    public boolean estaHipotecado(){
        return hipotecado;
    }
    
    //Cancela la hipoteca si el jugador pasado por parametro es el dueño. Paga el importe de cancelación
    boolean cancelarHipoteca(Jugador jugador){
        boolean operacion=false;
        if(hipotecado&&jugador==propietario){
            operacion=true;
            propietario.paga(getImporteCancelarHipoteca());
            hipotecado = false;
        }
        return operacion;
    }
    
    //Cantidad de casas y hoteles de una propiedad
    int cantidadCasasHoteles(){
        return numCasas+numHoteles;
    }
    
    //Devuelve true si se realiza la operacion. Asigna la propiedad a un jugador y le hace pagar el precio
    boolean comprar(Jugador jugador){
        boolean operacion=false;
        if(propietario==null){
            propietario=jugador;
            propietario.paga(precioCompra);
            operacion=true;
        }
        return operacion;
    }
    
    //Devuelve true si se realiza la operacion. Si el jugador del parámetro 
    //es el dueño crea una casa en la propiedad y le hace pagar por ella 
    boolean construirCasa(Jugador jugador){
        boolean operacion=false;
        if(jugador==propietario){
            propietario.paga(precioEdificar);
            numCasas++;
            operacion=true;
        }
        return operacion;
    }
    
    //Lo mismo que construirCasa pero con un hotel. Para poder realizarse, tienen
    //que existir cuatro casas en la propiedad, que se eliminaran
    boolean construirHotel(Jugador jugador){            
        boolean operacion=false;
        if(jugador==propietario&&numCasas>=4){
            operacion=true;
            numCasas-=4;
            propietario.paga(precioEdificar);
            numHoteles+=1;
        }
        return operacion;
    }
    
    //Devuelve true si se realiza la operacion. El jugador pasado tiene que ser el dueño.
    boolean derruirCasas(int n, Jugador jugador){
        boolean operacion=false;
        if(jugador==propietario&&numCasas>=n){
            operacion=true;
            numCasas-=n;
            
        }
        return operacion;
    }
    
    //Devuelve true si el propietario coincide con el jugador del argumento
    private boolean esEsteElPropietario(Jugador jugador){
        return propietario==jugador;
    }
    
    //Devuelve si la propiedad está hipotecada
    public boolean getHipotecado(){
        return hipotecado;
    }
    
    //Devuelve el precio por cancelar la hipoteca
    float getImporteCancelarHipoteca(){
        return (float) ((hipotecaBase*(1+(numCasas*0.5)+(numHoteles*2.5)))*factorInteresesHipoteca);
    }
    
    private float getImporteHipoteca(){             //DUDA: Hay que devolver la hipoteca base o el precio total
         return (float) (hipotecaBase*(1+(numCasas*0.5)+(numHoteles*2.5)));
    }
    
    //Devuelve el nombre de la propiedad
    public String getNombre(){
        return nombre;
    }
    
    //Devuelve el numero de casas construidas
    public int getNumCasas(){
        return numCasas;
    }
    
    //Devuelve el numero de hoteles construidos
    public int getNumHoteles(){
        return numHoteles;
    }
    
    //Devuelve el precio del alquiler a pagar
    private float getPrecioAlquiler(){
        if(hipotecado||propietarioEncarcelado())
            return 0;
        else
            return (float) (alquilerBase*(1+(numCasas*0.5)+(numHoteles*2.5)));
    }
    
    float getPrecioCompra(){
        return precioCompra;
    }
    
    float getPrecioEdificar(){
        return precioEdificar;
    }
    
    private float getPrecioVenta(){
        return (float) precioCompra+(numCasas+5*numHoteles)*precioEdificar*factorRevalorizacion;
    }
    
    Jugador getPropietario(){
        return propietario;
    }
    
    //Devuelve true si se ha completado la operacion. Hipoteca la propiedad si el jugador
    //pasado es el dueño. Si lo es, el jugador recibe el importe de la hipoteca
    boolean hipotecar(Jugador jugador){
        boolean operacion=false;
        if(!hipotecado&&jugador==propietario){
            operacion=true;
            propietario.recibe((float) (hipotecaBase*(1+(numCasas*0.5)+(numHoteles*2.5))));
            hipotecado=true;
        }
        return operacion;
    }
    
    //Devuelve true si existe propietario y está encarcelado
    private boolean propietarioEncarcelado(){
        if(propietario==null)
            return false;
        else
            return propietario.isEncarcelado();
    }
    
    //Devuelve true si tiene propietario
    boolean tienePropietario(){
        return propietario!=null;
    }
    
    //Devuelve un string con la descripción de la propiedad
    @Override
    public String toString(){
        return "CASILLA:"+nombre+
                "\n  -alquilerBase: "+alquilerBase+ " -factorInteresesHipoteca:"+factorInteresesHipoteca+
                "\n  -factorRevalorizacion:"+factorRevalorizacion+"  -hipotecaBase:"+hipotecaBase+
                "\n  -hipotecado:"+hipotecado+"  -numCasas:"+numCasas+"  -numHoteles:"+numHoteles+
                "\n  -precioCompra:"+precioCompra+"  -precioEdificar:"+precioEdificar+
                "\n -propietario:"+propietario;
    }
    
    
    
    
    //Si procede, el jugador paga el alquiler al propietario 
    void tramitarAlquiler(Jugador jugador){
        if(propietario!=null&&jugador!=propietario){
            float cantidad=(float) (alquilerBase*(1+(numCasas*0.5)+(numHoteles*2.5)));
            jugador.pagaAlquiler(cantidad);
            propietario.recibe(cantidad);
           
        }
    }
    
    
    boolean vender(Jugador jugador){
        boolean operacion=false;     
        if(propietario==jugador&&!hipotecado){
            propietario.recibe(getPrecioVenta());
            propietario=null;
            numCasas=0;
            numHoteles=0;
            operacion=true;
        }
        return operacion;
    }
}