/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_so;

/**
 *
 * @author gabriellavetencourt
 */
public class Lista<T> {

    private Nodo<T> pFirst;
    private Nodo<T> pLast;
    private int size;

    public Lista() {
        this.pFirst = null;
        this.pLast = null;
        this.size = 0;
    }

    public void insertarAlInicio(T data) {
        Nodo<T> nuevoNodo = new Nodo<>(data);
        if (this.size == 0) {
            this.pFirst = nuevoNodo;
            this.pLast = nuevoNodo;
        } else {
            nuevoNodo.setPnext(this.pFirst);
            this.pFirst = nuevoNodo;
        }
        this.size++;
    }

    public void insertarAlFinal(T data) {
        Nodo<T> nuevoNodo = new Nodo<>(data);
        if (this.size == 0) {
            this.pFirst = nuevoNodo;
            this.pLast = nuevoNodo;
        } else {
            this.pLast.setPnext(nuevoNodo);
            this.pLast = nuevoNodo;
        }
        this.size++;
    }


    public void eliminarAlInicio() {
        if (this.size == 0) {
            return;
        }
        if (this.size == 1) {
            this.pFirst = null;
            this.pLast = null;
        } else {
            this.pFirst = this.pFirst.getPnext();
        }
        this.size--;
    }

    public void eliminarAlFinal() {
        if (this.size == 0) {
            return;
        }
        if (this.size == 1) {
            this.pFirst = null;
            this.pLast = null;
        } else {
            Nodo<T> actual = this.pFirst;
            Nodo<T> anterior = null;
            while (actual.getPnext() != null) {
                anterior = actual;
                actual = actual.getPnext();
            }
            anterior.setPnext(null);
            this.pLast = anterior;
        }
        this.size--;
    }

    public void imprimirLista() {
        Nodo<T> actual = this.pFirst;

        while (actual != null) {
            System.out.print(actual.getData() + " ");
            actual = actual.getPnext();
        }

        System.out.println();
    }


  
    public boolean esVacia() {
        return pFirst == null;
    }
 

    public Nodo<T> getpFirst() {
        return pFirst;
    }

    public void setpFirst(Nodo<T> pFirst) {
        this.pFirst = pFirst;
    }

    public Nodo<T> getpLast() {
        return pLast;
    }

    public void setpLast(Nodo<T> pLast) {
        this.pLast = pLast;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    
}
