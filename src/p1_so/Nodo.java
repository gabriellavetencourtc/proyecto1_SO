/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_so;

/**
 *
 * @author gabriellavetencourt
 */
public class Nodo<T> {
    private T data;
    private Nodo pnext;

    public Nodo(T data) {
        this.data = data;
        this.pnext = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Nodo getPnext() {
        return pnext;
    }

    public void setPnext(Nodo pnext) {
        this.pnext = pnext;
    }
    
    
    
}
