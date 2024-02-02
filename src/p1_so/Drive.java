/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_so;
import javax.swing.JLabel;

/**
 *
 * @author gabriellavetencourt
 */
public class Drive {
    private int guion;
    private int escenario;
    private int animacion;
    private int doblaje;
    private int plotwist;
    private int diasRestantes;
    private int deadline;
    private int capitulo;
    private int capituloPlotTwist;
    private JLabel[] labels;
    private int empresa;
    private double salario;
    private int diasTranscurridos;

    public Drive(int empresa) {
        this.guion = 0;
        this.escenario = 0;
        this.animacion = 0;
        this.doblaje = 0;
        this.plotwist = 0;
        this.diasRestantes = 5; //pilas no sabemos pq hay un 5 ahi
        this.deadline = 0;
        this.capitulo = 0;
        this.capituloPlotTwist = 0;
        this.empresa = empresa;
        this.salario = 0;
        this.diasTranscurridos = 0;
    }

    public int getGuion() {
        return guion;
    }

    public void setGuion(int guion) {
        this.guion = guion;
    }

    public int getEscenario() {
        return escenario;
    }

    public void setEscenario(int escenario) {
        this.escenario = escenario;
    }

    public int getAnimacion() {
        return animacion;
    }

    public void setAnimacion(int animacion) {
        this.animacion = animacion;
    }

    public int getDoblaje() {
        return doblaje;
    }

    public void setDoblaje(int doblaje) {
        this.doblaje = doblaje;
    }

    public int getPlotwist() {
        return plotwist;
    }

    public void setPlotwist(int plotwist) {
        this.plotwist = plotwist;
    }

    public int getDiasRestantes() {
        return diasRestantes;
    }

    public void setDiasRestantes(int diasRestantes) {
        this.diasRestantes = diasRestantes;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(int capitulo) {
        this.capitulo = capitulo;
    }

    public int getCapituloPlotTwist() {
        return capituloPlotTwist;
    }

    public void setCapituloPlotTwist(int capituloPlotTwist) {
        this.capituloPlotTwist = capituloPlotTwist;
    }

    public JLabel[] getLabels() {
        return labels;
    }

    public void setLabels(JLabel[] labels) {
        this.labels = labels;
    }

    public int getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getDiasTranscurridos() {
        return diasTranscurridos;
    }

    public void setDiasTranscurridos(int diasTranscurridos) {
        this.diasTranscurridos = diasTranscurridos;
    }
    
    
  
    
}
