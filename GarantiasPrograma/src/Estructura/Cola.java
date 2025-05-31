package Estructura;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class Cola<T> {
    private LinkedList<T> elementos;

    public Cola() {
        elementos = new LinkedList<>();
    }

    public void encolar(T item) {
        elementos.addLast(item);
    }

    public T desencolar() {
        return elementos.pollFirst();
    }

    public T peek() {
        return elementos.peekFirst();
    }

    public boolean estaVacia() {
        return elementos.isEmpty();
    }

    public List<T> obtenerTodos() {
        return new ArrayList<>(elementos);
    }

}