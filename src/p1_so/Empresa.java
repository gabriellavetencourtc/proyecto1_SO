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
    private Lista guionistas;
    private Lista disenadores;
    private Lista animadores;
    private Lista actores;
    private Lista guionistasPT;
    private Lista ensambladores;
    //private ProjectManager pm;
   // private Director dir;
    //private DirectorWatch dirWatch;    
    private Semaphore mutex;
    private Drive drive;   
    private double ingresos;
    private double salario;
    private double utilidad;   
    private int duracionDia;
    private int DuracionHora;
    private int DuracionMinuto;
    
    private JLabel[] labels;
    
    private int cantTrabajadores;

    public Empresa(int cantTrabajadores, String empresa){
        this.empresa = empresa;
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
        this.duracionDia = duracionDia;
        this.DuracionHora = DuracionHora;
        this.DuracionMinuto = DuracionMinuto;
        this.labels = labels;
        this.cantTrabajadores = cantTrabajadores;
    }
    
    //PILAS FALTAN GETTERS Y SETTERS DEL PM Y DIR Y DIRWATCH


    public void AggTrabajador(GameDeveloper dev){
        if((this.guionistas.getlSize() + this.disenadores.getlSize() + this.animadores.getlSize() + this.actores.getlSize() + this.guionistasPT.getlSize() + this.ensambladores.getlSize()) < this.cantTrabajadores){
            Node devNode = new Node(dev);
            switch (dev.getGameComponent()){

                case "Narrative":
                    this.guionistas.insertEnd(devNode);
                    this.labels[1].setText(Integer.toString(this.guionistas.getlSize()));
                    this.labels[8].setText(Integer.toString(this.guionistas.getlSize()));
                    break;

                case "LevelDesign":
                    this.disenadores.insertEnd(devNode);
                    this.labels[2].setText(Integer.toString(this.disenadores.getlSize()));
                    this.labels[9].setText(Integer.toString(this.disenadores.getlSize()));
                    break;

                case "SpriteArt":
                    this.animadores.insertEnd(devNode);
                    this.labels[3].setText(Integer.toString(this.animadores.getlSize()));
                    this.labels[10].setText(Integer.toString(this.animadores.getlSize()));
                    break;

                case "GameLogic":
                    this.actores.insertEnd(devNode);
                    this.labels[4].setText(Integer.toString(this.actores.getlSize()));
                    this.labels[11].setText(Integer.toString(this.actores.getlSize()));
                    break;

                case "DLC":
                    this.guionistasPT.insertEnd(devNode);
                    this.labels[5].setText(Integer.toString(this.guionistasPT.getlSize()));
                    this.labels[12].setText(Integer.toString(this.guionistasPT.getlSize()));
                    break;
            }        

            this.labels[0].setText(Integer.toString(this.guionistas.getlSize() + this.disenadores.getlSize() + this.animadores.getlSize() + this.actores.getlSize() + this.guionistasPT.getlSize() + this.ensambladores.getlSize()));
            this.labels[7].setText(Integer.toString(this.guionistas.getlSize() + this.disenadores.getlSize() + this.animadores.getlSize() + this.actores.getlSize() + this.guionistasPT.getlSize() + this.ensambladores.getlSize()));
            dev.start();
            
        }else{
            devLimitNotice();
        }
    }

    
    public void addIntegrator(Integrator integ){
        if((this.guionistas.getlSize() + this.disenadores.getlSize() + this.animadores.getlSize() + this.actores.getlSize() + this.guionistasPT.getlSize() + this.ensambladores.getlSize()) < this.cantTrabajadores){
            Node integNode = new Node(integ);
            this.ensambladores.insertEnd(integNode);
            this.labels[6].setText(Integer.toString(this.ensambladores.getlSize()));
            this.labels[13].setText(Integer.toString(this.ensambladores.getlSize()));
            
            this.labels[0].setText(Integer.toString(this.guionistas.getlSize() + this.disenadores.getlSize() + this.animadores.getlSize() + this.actores.getlSize() + this.guionistasPT.getlSize() + this.ensambladores.getlSize()));
            this.labels[7].setText(Integer.toString(this.guionistas.getlSize() + this.disenadores.getlSize() + this.animadores.getlSize() + this.actores.getlSize() + this.guionistasPT.getlSize() + this.ensambladores.getlSize()));
            integ.start();
        }
    }

    public void removeIntegrator(){
        if (this.ensambladores.getlSize() > 1) {
            Node tempNode;
            Integrator tempInte;

            tempNode = this.ensambladores.getlLast();
            tempInte = Integrator.class.cast(tempNode.getData());
            tempInte.setIsActive(false);
            this.ensambladores.delLast();
            this.labels[6].setText(Integer.toString(this.ensambladores.getlSize()));
            this.labels[13].setText(Integer.toString(this.ensambladores.getlSize()));
            this.labels[0].setText(Integer.toString(this.guionistas.getlSize() + this.disenadores.getlSize() + this.animadores.getlSize() + this.actores.getlSize() + this.guionistasPT.getlSize() + this.ensambladores.getlSize()));
            this.labels[7].setText(Integer.toString(this.guionistas.getlSize() + this.disenadores.getlSize() + this.animadores.getlSize() + this.actores.getlSize() + this.guionistasPT.getlSize() + this.ensambladores.getlSize()));
        
        }
       
    }
    
    
    public boolean canDecreaseMaxDevs() {
        int totalDevs = guionistas.getlSize() + disenadores.getlSize() + animadores.getlSize() + actores.getlSize() + guionistasPT.getlSize() + ensambladores.getlSize();
        return totalDevs < cantTrabajadores;
    }
    
 /*
    public void activateDevs(){
        Node tempNode = this.guionistas.getlFirst();
        for(int i = 0; i < this.guionistas.getlSize(); i++){
            GameDeveloper tempDev = GameDeveloper.class.cast(tempNode.getData()); 
            tempDev.start();
            tempNode = tempNode.getpNext();
        }
        
        tempNode = this.disenadores.getlFirst();
        for(int i = 0; i < this.disenadores.getlSize(); i++){
            GameDeveloper tempDev = GameDeveloper.class.cast(tempNode.getData()); 
            tempDev.start();
            tempNode = tempNode.getpNext();
        }
        
        tempNode = this.animadores.getlFirst();
        for(int i = 0; i < this.animadores.getlSize(); i++){
            GameDeveloper tempDev = GameDeveloper.class.cast(tempNode.getData()); 
            tempDev.start();
            tempNode = tempNode.getpNext();
        }
        
        tempNode = this.actores.getlFirst();
        for(int i = 0; i < this.actores.getlSize(); i++){
            GameDeveloper tempDev = GameDeveloper.class.cast(tempNode.getData()); 
            tempDev.start();
            tempNode = tempNode.getpNext();
        }
        
        tempNode = this.guionistasPT.getlFirst();
        for(int i = 0; i < this.guionistasPT.getlSize(); i++){
            GameDeveloper tempDev = GameDeveloper.class.cast(tempNode.getData()); 
            tempDev.start();
            tempNode = tempNode.getpNext();
        }
    
    }
*/
    
    public void removeDev(String devType){
    
        Node tempNode;
        GameDeveloper tempDev;
        
        switch(devType){
        
            
            
            case "Narrative":
                
                tempNode = this.guionistas.getlLast();
                tempDev = GameDeveloper.class.cast(tempNode.getData());
                tempDev.setIsActive(false);
                this.guionistas.delLast();
                this.labels[1].setText(Integer.toString(this.guionistas.getlSize()));
                this.labels[8].setText(Integer.toString(this.guionistas.getlSize()));
                
                break;
                
            case "LevelDesign":
                
                tempNode = this.disenadores.getlLast();
                tempDev = GameDeveloper.class.cast(tempNode.getData());
                tempDev.setIsActive(false);
                this.disenadores.delLast();
                this.labels[2].setText(Integer.toString(this.disenadores.getlSize()));
                this.labels[9].setText(Integer.toString(this.disenadores.getlSize()));
                
                break;
                
            case "SpriteArt":
                
                tempNode = this.animadores.getlLast();
                tempDev = GameDeveloper.class.cast(tempNode.getData());
                tempDev.setIsActive(false);
                this.animadores.delLast();
                this.labels[3].setText(Integer.toString(this.animadores.getlSize()));
                this.labels[10].setText(Integer.toString(this.animadores.getlSize()));
                
                break;
                
            case "GameLogic":
                
                tempNode = this.actores.getlLast();
                tempDev = GameDeveloper.class.cast(tempNode.getData());
                tempDev.setIsActive(false);
                this.actores.delLast();
                this.labels[4].setText(Integer.toString(this.actores.getlSize()));
                this.labels[11].setText(Integer.toString(this.actores.getlSize()));
                
                break;
                
            case "DLC":
                
                tempNode = this.guionistasPT.getlLast();
                tempDev = GameDeveloper.class.cast(tempNode.getData());
                tempDev.setIsActive(false);
                this.guionistasPT.delLast();
                this.labels[5].setText(Integer.toString(this.guionistasPT.getlSize()));
                this.labels[12].setText(Integer.toString(this.guionistasPT.getlSize()));
                
                break;
            
        }
        
        this.labels[0].setText(Integer.toString(this.guionistas.getlSize() + this.disenadores.getlSize() + this.animadores.getlSize() + this.actores.getlSize() + this.guionistasPT.getlSize() + this.ensambladores.getlSize()));
        this.labels[7].setText(Integer.toString(this.guionistas.getlSize() + this.disenadores.getlSize() + this.animadores.getlSize() + this.actores.getlSize() + this.guionistasPT.getlSize() + this.ensambladores.getlSize()));
        
    }

    public JLabel[] getLabels() {
        return labels;
    }

    public void setLabels(JLabel[] labels) {
        this.labels = labels;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getUtilities() {
        return utilidad;
    }

    public void setUtilities(double utilities) {
        this.utilidad = utilities;
        this.labels[15].setText(Double.toString(this.utilidad));
    }
    
    public void addSalary(double salary){
        this.salario += salary;
        this.labels[16].setText(Double.toString(this.salario));
    }
    
    public void addIncome(double income){
        this.ingresos += income;
        this.labels[14].setText(Double.toString(this.ingresos));
    }
 
    public void devLimitNotice(){
        JOptionPane.showMessageDialog(null, "Limite de desarrolladores alcanzado, incremente el maximo para poder añadir más");
    }
}
