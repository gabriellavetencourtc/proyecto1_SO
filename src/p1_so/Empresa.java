/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_so;

import java.util.concurrent.Semaphore;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author gabriellavetencourt
 */
public class Empresa {

    private String empresa;

    private String nombreEmpresa;
    private Lista guionistas;
    private Lista disenadores;
    private Lista animadores;
    private Lista actores;
    private Lista guionistasPT;
    private Lista ensambladores;
    private ProjectManager pm;
    private Director dir;
    private RevisionPM revision;
    private Semaphore mutex;
    private Drive drive;
    private double ingresos;
    private double salario;
    private double utilidad;
    private JLabel[] labels;
    private int cantTrabajadores;

    public Empresa(int cantTrabajadores, String empresa) {
        this.nombreEmpresa = empresa;
        this.guionistas = new Lista();
        this.disenadores = new Lista();
        this.animadores = new Lista();
        this.actores = new Lista();
        this.guionistasPT = new Lista();
        this.ensambladores = new Lista();
        this.mutex = new Semaphore(1);
        this.drive = new Drive(empresa);
        this.ingresos = 0;
        this.salario = 0;
        this.utilidad = 0;
        this.cantTrabajadores = cantTrabajadores;
    }
    


    public void AggTrabajador(Trabajadores t) {
        if ((this.guionistas.getSize()
                + this.disenadores.getSize()
                + this.animadores.getSize()
                + this.actores.getSize()
                + this.guionistasPT.getSize()
                + this.ensambladores.getSize()) < this.cantTrabajadores) {

            Nodo tNodo = new Nodo(t);

            if (t.getTipo() == 0) { //guion
                this.guionistas.insertarAlFinal(tNodo);
                this.labels[1].setText(Integer.toString(this.guionistas.getSize()));
              
            }

            if (t.getTipo() == 1) { //disenadores
                this.disenadores.insertarAlFinal(tNodo);
                this.labels[2].setText(Integer.toString(this.disenadores.getSize()));
            }

            if (t.getTipo() == 2) { //animadores
                this.animadores.insertarAlFinal(tNodo);
                this.labels[3].setText(Integer.toString(this.animadores.getSize()));

            }

            if (t.getTipo() == 3) { //actores
                this.actores.insertarAlFinal(tNodo);
                this.labels[4].setText(Integer.toString(this.actores.getSize()));

            }

            if (t.getTipo() == 4) { //guionistasPT
                this.guionistasPT.insertarAlFinal(tNodo);
                this.labels[5].setText(Integer.toString(this.guionistasPT.getSize()));

            }
            int TrabajadoresDisponibles = cantTrabajadores - (this.guionistas.getSize() + this.disenadores.getSize()
                    + this.animadores.getSize() + this.actores.getSize() + this.guionistasPT.getSize()
                    + this.ensambladores.getSize());

            this.labels[0].setText(Integer.toString(TrabajadoresDisponibles));
            
            t.start();

        } else {
            limiteTrabajadores();
        }
    }

    public void eliminarTrabajador(int tipo) {
        Nodo aux;
        Trabajadores auxTrabajadores;

        if (tipo == 0) { //guionista
            aux = this.guionistas.getpLast();
            
            this.guionistas.eliminarAlFinal();
            this.labels[1].setText(Integer.toString(this.guionistas.getSize()));
            
            auxTrabajadores = Trabajadores.class.cast(aux.getData());
            auxTrabajadores.setActivo(false);
            
        }
        if (tipo == 1) { //disenadores
            aux = this.disenadores.getpLast();
            
            this.disenadores.eliminarAlFinal();
            this.labels[2].setText(Integer.toString(this.disenadores.getSize()));
            
            auxTrabajadores = Trabajadores.class.cast(aux.getData());
            auxTrabajadores.setActivo(false);
        }

        if (tipo == 2) { //animadores
            aux = this.animadores.getpLast();

            this.animadores.eliminarAlFinal();
            this.labels[3].setText(Integer.toString(this.animadores.getSize()));
            
            auxTrabajadores = Trabajadores.class.cast(aux.getData());
            auxTrabajadores.setActivo(false);
                     
        }

        if (tipo == 3) { //actores
            aux = this.actores.getpLast();
            
            this.actores.eliminarAlFinal();
            this.labels[4].setText(Integer.toString(this.actores.getSize()));
            
            auxTrabajadores = Trabajadores.class.cast(aux.getData());
            auxTrabajadores.setActivo(false);
            
        }

        if (tipo == 4) { //guionistasPT
            aux = this.guionistasPT.getpLast();
            
            this.guionistasPT.eliminarAlFinal();
            this.labels[5].setText(Integer.toString(this.guionistasPT.getSize()));
            
            auxTrabajadores = Trabajadores.class.cast(aux.getData());
            auxTrabajadores.setActivo(false);
           
        }

        int TrabajadoresDisponibles = cantTrabajadores - (this.guionistas.getSize() + this.disenadores.getSize()
                    + this.animadores.getSize() + this.actores.getSize() + this.guionistasPT.getSize()
                    + this.ensambladores.getSize());

        this.labels[0].setText(Integer.toString(TrabajadoresDisponibles+1));
       
    }

//    public boolean disminuirTrabajadores() {
//        int totalTrabajadores = guionistas.getSize() + disenadores.getSize() + animadores.getSize() + actores.getSize() + guionistasPT.getSize() + ensambladores.getSize();
//        return totalTrabajadores < cantTrabajadores;
//    }

    public void AggEnsamblador(Ensamblador ensamblador) {
        if ((this.guionistas.getSize() + this.disenadores.getSize() + this.animadores.getSize()
                + this.actores.getSize() + this.guionistasPT.getSize()
                + this.ensambladores.getSize()) < this.cantTrabajadores) {
            Nodo eNodo = new Nodo(ensamblador);
            this.ensambladores.insertarAlFinal(eNodo);
            this.labels[6].setText(Integer.toString(this.ensambladores.getSize()));
            
            int TrabajadoresDisponibles = cantTrabajadores - (this.guionistas.getSize() + this.disenadores.getSize() + this.animadores.getSize() + this.actores.getSize() + this.guionistasPT.getSize() + this.ensambladores.getSize());
            this.labels[0].setText(Integer.toString(TrabajadoresDisponibles));
            
            ensamblador.start();
        }
    }

    public void borrarEnsamblador() {
        if (this.ensambladores.getSize() > 1) {
            Nodo aux;
            Ensamblador auxEnsamblador;

            aux = this.ensambladores.getpLast();
            this.ensambladores.eliminarAlFinal();
            this.labels[6].setText(Integer.toString(this.ensambladores.getSize()));
            
            auxEnsamblador = Ensamblador.class.cast(aux.getData());
            auxEnsamblador.setActivo(false);
            
            int TrabajadoresDisponibles = cantTrabajadores - (this.guionistas.getSize() + this.disenadores.getSize()
                    + this.animadores.getSize() + this.actores.getSize() + this.guionistasPT.getSize()
                    + this.ensambladores.getSize());

            this.labels[0].setText(Integer.toString(TrabajadoresDisponibles));
           
        }

    }

    public void setUtilidad(double utilidad) {
        this.utilidad = utilidad;
        this.labels[9].setText(Double.toString(this.utilidad));
    }

    public void sumSalario(double salario) {
        this.salario += salario;
        this.labels[10].setText(Double.toString(this.salario));
    }

    public void sumIngreso(double ingreso) {
        this.ingresos += ingreso;
        this.labels[8].setText(Double.toString(this.ingresos));
    }

    public void limiteTrabajadores() {
        JOptionPane.showMessageDialog(null, "Limite máximo de trabajadores");
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Lista getGuionistas() {
        return guionistas;
    }

    public void setGuionistas(Lista guionistas) {
        this.guionistas = guionistas;
    }

    public Lista getDisenadores() {
        return disenadores;
    }

    public void setDisenadores(Lista disenadores) {
        this.disenadores = disenadores;
    }

    public Lista getAnimadores() {
        return animadores;
    }

    public void setAnimadores(Lista animadores) {
        this.animadores = animadores;
    }

    public Lista getActores() {
        return actores;
    }

    public void setActores(Lista actores) {
        this.actores = actores;
    }

    public Lista getGuionistasPT() {
        return guionistasPT;
    }

    public void setGuionistasPT(Lista guionistasPT) {
        this.guionistasPT = guionistasPT;
    }

    public Lista getEnsambladores() {
        return ensambladores;
    }

    public void setEnsambladores(Lista ensambladores) {
        this.ensambladores = ensambladores;
    }

    public ProjectManager getPm() {
        return pm;
    }

    public void setPm(ProjectManager pm) {
        this.pm = pm;
    }

    public Director getDir() {
        return dir;
    }

    public void setDir(Director dir) {
        this.dir = dir;
    }

    public RevisionPM getRevision() {
        return revision;
    }

    public void setRevision(RevisionPM revision) {
        this.revision = revision;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public Semaphore getMutex() {
        return mutex;
    }

    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public JLabel[] getLabels() {
        return labels;
    }

    public void setLabels(JLabel[] labels) {
        this.labels = labels;
    }

    public int getCantTrabajadores() {
        return cantTrabajadores;
    }

    public void setCantTrabajadores(int cantTrabajadores) {
        this.cantTrabajadores = cantTrabajadores;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public double getUtilidad() {
        return utilidad;
    }

}
