package es.ucm.arblemar.engine;

/**
 * Interfaz de la fuenta de letra común a Android y Java
 * Contiene los métodos necesarios para gestionar el tipo de fuente, tamaño,
 * posición y contenido del texto que se va a mostrar
 */
public interface Font {
    public boolean init();

    public void setSize(float newSize);
}