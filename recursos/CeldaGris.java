public class CeldaGris extends Celda{
    
    CeldaGris(){
        super(Color.gris);
    }

    private Init(){
        
    }

    // En caso de que devuelva false, el tablero comprueba que se haya completado el puzzle
    public bool Click(){
        color++;
        if(color >= Color.MAX){
            color = Color.gris;
            // Aquí llamaríamos a algo de Graphics para avisar del cambio de color
        }

        return false;
    }
}