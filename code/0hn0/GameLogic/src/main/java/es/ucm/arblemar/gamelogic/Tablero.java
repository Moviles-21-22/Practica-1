package es.ucm.arblemar.gamelogic;


import com.sun.tools.javac.util.Pair;
import java.util.Random;

public class Tablero {

    int _size = 4;
    private int adyacentes = 0;
    // Numero de celdas grises cambiadas de color
    int contador = 0;
    private Celda[][] casillas;
    boolean [] locks;
    boolean rendirse = false;

    public Tablero(int size){
        _size = 3;
        casillas = new Celda[_size][_size];
        for(int i = 0 ; i < _size ; i++){
            for(int j = 0 ; j < _size ; j++){
                casillas[i][j] = new CeldaGris(0);
            }
        }
        Init();
        RenderizaConsola();
    }

    private void RenderizaConsola() {
        System.out.println("Rojos: R   Azules: v   Grises: X");

        for(int x = 0 ; x < _size ; x++){
            for(int y = 0 ; y < _size ; y++){
                if(casillas[x][y]._tipoCelda == TipoCelda.GRIS){
                    System.out.print("X ");
                }
                else if(casillas[x][y]._tipoCelda == TipoCelda.ROJO){
                    System.out.print("R ");
                }
                else if(casillas[x][y]._tipoCelda == TipoCelda.AZUL){
                    System.out.print(((CeldaAzul)casillas[x][y]).getValue() + " ");
                }
            }
            System.out.println();
        }
    }

    // Inicializa el tablero y asegura que tenga solución
    private void Init(){
        Random r = new Random();

        //  Número de azules a poner
        int circulosAzules = r.nextInt(_size - 1) + 1;

        //  Bucle para poner los azules
        boolean azulesPuesto = false;
        //  Contador de cuantos azules se han puesto
        int contAzul = 0;
        while (!azulesPuesto){
            int indX = r.nextInt(_size - 1) + 1;
            int indY = r.nextInt(_size - 1) + 1;
            if(!casillas[indX][indY].IsLock()){
                int valor = r.nextInt(_size - 1) + 1;
                if(Valid(indX,indY,valor)){
                    //System.out.println("Pos " + indX + " " +indY);
                    casillas[indX][indY] = new CeldaAzul(valor);
                    casillas[indX][indY]._lock = true;
                    contAzul++;
                }
            }
            azulesPuesto = contAzul >= circulosAzules;
        }

        //  Número de rojos a poner
        int circulosRojos = r.nextInt(_size - 1) + 1;
        //  Bucle para poner los azules
        boolean rojosPuesto = false;
        //  Contador de cuantos azules se han puesto
        int contRojos = 0;
        while (!rojosPuesto){
            int indX = r.nextInt(_size - 1) + 1;
            int indY = r.nextInt(_size - 1) + 1;
            if(!casillas[indX][indY].IsLock()){
                int valor = r.nextInt(_size - 1) + 1;
                if(Valid(indX,indY,valor)){
                    //System.out.println("Pos " + indX + " " +indY);
                    casillas[indX][indY] = new CeldaRoja(valor);
                    casillas[indX][indY]._lock = true;
                    contRojos++;
                }
            }
            rojosPuesto = contRojos >= circulosRojos;

        }
    }

    private boolean revisaAdyacentes(Vector2D coors, Vector2D dir, int valor){
        int x = (int)coors._x;
        int y = (int)coors._y;
        coors._y += dir._y;
        coors._x += dir._x;
        boolean correcto = true;
        while (correcto){
            //  Me he salido por arriba entonces cambio de dirección
            if(coors._y < 0){
                coors._y = y + 1;
                dir._y = 1;
            }
            //  Me he salido por la izquierda entonces cambio de dirección
            else if(coors._x < 0){
                coors._x = x + 1;
                dir._x = 1;
            }
            //  Me he salido por abajo
            else if(dir._y == 1 && coors._y >= _size){
                return adyacentes > valor;
            }
            //  Me he salido por la derecha
            else if(dir._x == 1 && coors._x >= _size){
                return adyacentes > valor;
            }
            //  Hay casilla adyacente
            else if(casillas[(int)coors._x][(int)coors._y].IsLock()){
                adyacentes++;
                coors._y += dir._y;
                coors._x += dir._x;
            }
            //  Cambio de dirección en el eje y
            else if(dir._y == -1){
                dir._y = 1;
                coors._y += dir._y + 1;
            }
            else if(dir._x == -1){
                dir._x = 1;
                coors._x += dir._x + 1;
            }
            //  No hay más posibilidades de adyacencia o no hay más adyacentes de las permitidas
            else if(dir._y == 1 || dir._x == 1|| adyacentes > valor){
                return false;
            }
        }
       //Pair<String,Integer> v = new Pair<>("correcto",ady);
        return adyacentes < valor;
    }

    //  Recorrer el rango del valor en el eje x e y, mientras la suma de los adyacentes
    //  no sea mayor al valor, es correcto.
    private boolean Valid(int x, int y, int valor){
        adyacentes = 0;
        Vector2D coors = new Vector2D(x,y);
        Vector2D dir = new Vector2D(0,-1);

        if(!revisaAdyacentes(coors,dir,valor)){
            dir._x = -1;
            dir._y = 0;
            coors._x = x;
            coors._y = y;
            revisaAdyacentes(coors,dir,valor);

        }

        return adyacentes > valor;
    }

}