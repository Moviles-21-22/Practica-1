public class CeldaAzul extends Celda{
    
    CeldaAzul(){
        super(Color.azul);
        lock = true;
    }

    private void Init(){
        
    }

    public bool Click(){
        // se hace grande y pequeño para indicar que no se puede
        // enviar un aviso al tablero

        return lock;
    }
}