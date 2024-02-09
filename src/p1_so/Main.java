/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package p1_so;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author gabriellavetencourt
 */
public class Main extends javax.swing.JFrame {

    Empresa Nickelodeon = new Empresa(17, "Nickelodeon"); //19 con PM y Dir
    Nickelodeon nick = new Nickelodeon();
    
    Empresa Cartoon = new Empresa(20, "Cartoon Network"); //20 con PM y Dir
    Cartoon cartoon = new Cartoon();
    
    Empresa StarChannel = new Empresa(16, "Star Channel"); //16 con PM y Dir
    StarChannel starchannel = new StarChannel();
    
    int duracionDia = 3500;
    
    

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
     

        //Se pasan los labels correspondientes a cada compañia y sus drives para manipular la interfaz        
        JLabel[] NickDriveLabels = {nick.getDriveGuiones(), nick.getDriveEscensarios(), nick.getDriveAnimaciones(), nick.getDriveDoblajes(), nick.getDrivePlot(), nick.getDeadline(), nick.getCapitulos(), nick.getCapitulosPT(), nick.getDiastrans()};
        JLabel[] NickLabels = {TrabajadoresNick, GuionistasNick, DisenadoresNick, AnimadorNick, ActoresNick, GuioPlotNick, EnsambladoresNick, nick.getIngresosNick(), nick.getUtilidadNick(), nick.getEgresosNick()};

        JLabel[] CartoonDriveLabels = {cartoon.getDriveGuiones(), cartoon.getDriveEscensarios(), cartoon.getDriveAnimaciones(), cartoon.getDriveDoblajes(), cartoon.getDrivePlot(), cartoon.getDeadline(), cartoon.getCapitulos(), cartoon.getCapitulosPT(), cartoon.getDiastrans()};
        JLabel[] CartoonLabels = {TrabajadoresCartoon, GuionistasCartoon, DiseñadoresCartoon, AnimadorCartoon, ActoresCartoon, GuioPlotCartoon, EnsambladoresCartoon, cartoon.getIngresosCartoon(), cartoon.getUtilidadCartoon(), cartoon.getEgresosCartoon()};
             
        JLabel[] StarChannelDriveLabels = {starchannel.getDriveGuiones3(), starchannel.getDriveEscensarios3(), starchannel.getDriveAnimaciones3(), starchannel.getDriveDoblajes3(), starchannel.getDrivePlot3(), starchannel.getDeadline3(), starchannel.getCapitulos3(), starchannel.getCapitulosPT3(), starchannel.getDiastrans3()};
        JLabel[] StarChannelLabels = {TrabajadoresStar, GuionistasStar, DiseñadoresStar, AnimadorStar, ActoresStar, GuioPlotStar, EnsambladoresStar, starchannel.getIngresosST(), starchannel.getUtilidadST(), starchannel.getEgresosST()};
//      
     


        deadline.setText("1");
        this.Nickelodeon.getDrive().setLabels(NickDriveLabels);
        this.Nickelodeon.setLabels(NickLabels);
        
        this.Cartoon.getDrive().setLabels(CartoonDriveLabels);
        this.Cartoon.setLabels(CartoonLabels);
        
        this.StarChannel.getDrive().setLabels(StarChannelDriveLabels);
        this.StarChannel.setLabels(StarChannelLabels);
           inicioParaCap();
       

        this.DuracionDiaTF.setText(Integer.toString(duracionDia));


  
        TrabajadoresNick.setText(Integer.toString(this.Nickelodeon.getCantTrabajadores()));
        TrabajadoresCartoon.setText(Integer.toString(this.Cartoon.getCantTrabajadores()));
        TrabajadoresStar.setText(Integer.toString(this.StarChannel.getCantTrabajadores()));

        //Conversion de hora y minutos   
        int duracionHora = duracionDia / 24;
        int duracionMin = duracionHora / 60;
        if (duracionHora == 0) {
            duracionHora = 1;
        }
        if (duracionMin == 0) {
            duracionMin = 1;
        }

   
        ProjectManager nickPM = new ProjectManager(this.duracionDia, duracionHora, duracionMin, this.Nickelodeon.getDrive(), this.Nickelodeon.getMutex(), nick.getPMestado());
        Director Nickdir = new Director(this.Nickelodeon.getDrive(), this.Nickelodeon.getMutex(), nickPM, duracionMin, nick.getPMfaltas());
        RevisionPM NickRevPM = new RevisionPM(this.duracionDia, duracionHora, duracionMin, Nickdir, nick.getDirEstado(), this.Nickelodeon);

        ProjectManager CartoonPM = new ProjectManager(this.duracionDia, duracionHora, duracionMin, this.Cartoon.getDrive(), this.Cartoon.getMutex(), cartoon.getPMestado());
        Director Cartoondir = new Director(this.Cartoon.getDrive(), this.Cartoon.getMutex(), CartoonPM, duracionMin, cartoon.getPMfaltas());
        RevisionPM CartoonRevPM = new RevisionPM(this.duracionDia, duracionHora, duracionMin, Cartoondir, cartoon.getDirEstado(), this.Cartoon);
        
        ProjectManager StarPM = new ProjectManager(this.duracionDia, duracionHora, duracionMin, this.StarChannel.getDrive(), this.StarChannel.getMutex(), starchannel.getPMestado3());
        Director Stardir = new Director(this.StarChannel.getDrive(), this.StarChannel.getMutex(), StarPM, duracionMin, starchannel.getPMfaltas3());
        RevisionPM StarRevPM = new RevisionPM(this.duracionDia, duracionHora, duracionMin, Stardir, starchannel.getDirEstado3(), this.StarChannel);

  
        
        nickPM.start();       
        Nickdir.start();
        NickRevPM.start();
        
        CartoonPM.start();       
        Cartoondir.start();
        CartoonRevPM.start();
        
        StarPM.start();       
        Stardir.start();
        StarRevPM.start();

    }
    
    public void inicioParaCap(){
        int[] partesCapitulo = {0, 1, 2, 3, 4};
        for(int i = 0; i < partesCapitulo.length; i++){
            Trabajadores t1 = new Trabajadores(partesCapitulo[i], "Nickelodeon", this.duracionDia, this.Nickelodeon.getDrive(), this.Nickelodeon.getMutex());
            this.Nickelodeon.AggTrabajador(t1);
            this.Nickelodeon.getDrive().setDiasRestantes(5);
            this.Nickelodeon.getDrive().setDeadline(5);
            this.nick.getDeadline().setText(Integer.toString(Nickelodeon.getDrive().getDeadline()));
        }
        Ensamblador ens1 = new Ensamblador(this.Nickelodeon.getDrive(), this.Nickelodeon.getMutex(), this.duracionDia);
        this.Nickelodeon.AggEnsamblador(ens1);

        for(int j = 0; j < partesCapitulo.length; j++){
            Trabajadores t2 = new Trabajadores(partesCapitulo[j], "Cartoon", this.duracionDia, this.Cartoon.getDrive(), this.Cartoon.getMutex());
            this.Cartoon.AggTrabajador(t2);
        }
        Ensamblador ens2 = new Ensamblador(this.Cartoon.getDrive(), this.Cartoon.getMutex(), this.duracionDia);
        this.Cartoon.AggEnsamblador(ens2);
        
        for(int h = 0; h < partesCapitulo.length; h++){
            Trabajadores t3 = new Trabajadores(partesCapitulo[h], "Star Channel", this.duracionDia, this.StarChannel.getDrive(), this.StarChannel.getMutex());
            this.StarChannel.AggTrabajador(t3);
        }
        Ensamblador ens3 = new Ensamblador(this.StarChannel.getDrive(), this.StarChannel.getMutex(), this.duracionDia);
        this.StarChannel.AggEnsamblador(ens3);
        
    }
    

    public void MaxErrorTrab() {
        JOptionPane.showMessageDialog(null, "Máximo de trabajadores alcanzado, disminuya la cantidad!");
    }

    public void minErrorTrab() {
        JOptionPane.showMessageDialog(null, "No es posible disminuir la cantidad de trabajadores.");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TrabajadoresNick = new javax.swing.JLabel();
        GuionistasNick = new javax.swing.JLabel();
        DisenadoresNick = new javax.swing.JLabel();
        AnimadorNick = new javax.swing.JLabel();
        ActoresNick = new javax.swing.JLabel();
        GuioPlotNick = new javax.swing.JLabel();
        EnsambladoresNick = new javax.swing.JLabel();
        MenosGuionistasNick = new javax.swing.JButton();
        MenosDisenadorNick = new javax.swing.JButton();
        MenosAnimadorNick = new javax.swing.JButton();
        MenosActoresNick = new javax.swing.JButton();
        MenosGuioPlotNick = new javax.swing.JButton();
        MenosEnsambladoresNick = new javax.swing.JButton();
        MasGuionistaNick = new javax.swing.JButton();
        MasAnimadorNick = new javax.swing.JButton();
        MasDisenadorNick = new javax.swing.JButton();
        MasActorNick = new javax.swing.JButton();
        MasGuioPlotNick = new javax.swing.JButton();
        MasEnsambladoresNick = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        DuracionDiaTF = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        salir = new javax.swing.JButton();
        masDeadline = new javax.swing.JButton();
        deadline = new javax.swing.JLabel();
        menosDeadline = new javax.swing.JButton();
        Guardar = new javax.swing.JButton();
        mostrarNick = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        TrabajadoresCartoon = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        GuionistasCartoon = new javax.swing.JLabel();
        DiseñadoresCartoon = new javax.swing.JLabel();
        AnimadorCartoon = new javax.swing.JLabel();
        ActoresCartoon = new javax.swing.JLabel();
        GuioPlotCartoon = new javax.swing.JLabel();
        EnsambladoresCartoon = new javax.swing.JLabel();
        MenosGuiCartoon = new javax.swing.JButton();
        MasGuioCartoon = new javax.swing.JButton();
        MenosDiseñadorCartoon = new javax.swing.JButton();
        MasDiseñadorCartoon = new javax.swing.JButton();
        MenosAnimadorCartoon = new javax.swing.JButton();
        MasAnimadorCartoon = new javax.swing.JButton();
        MenosActCartoon = new javax.swing.JButton();
        MasActCartoon = new javax.swing.JButton();
        MenosPlotCartoon = new javax.swing.JButton();
        MasPlotCartoon = new javax.swing.JButton();
        MenosEnsambCartoon = new javax.swing.JButton();
        MasEnsambCartoon = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        TrabajadoresStar = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        GuionistasStar = new javax.swing.JLabel();
        DiseñadoresStar = new javax.swing.JLabel();
        AnimadorStar = new javax.swing.JLabel();
        ActoresStar = new javax.swing.JLabel();
        GuioPlotStar = new javax.swing.JLabel();
        EnsambladoresStar = new javax.swing.JLabel();
        MenosGuioStar = new javax.swing.JButton();
        MasGuioStar = new javax.swing.JButton();
        MenosDiseñadorStar = new javax.swing.JButton();
        MasDiseñadorStar = new javax.swing.JButton();
        MenosAnimadorStar = new javax.swing.JButton();
        MasAnimadorStar = new javax.swing.JButton();
        MenosActoresStar = new javax.swing.JButton();
        MasActoresStar = new javax.swing.JButton();
        MenosPlotStar = new javax.swing.JButton();
        MasPlotStar = new javax.swing.JButton();
        MenosEnsambStar = new javax.swing.JButton();
        MasEnsambStar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nickelodeon");

        jLabel2.setText("Trabajadores disponibles:");

        jLabel3.setText("Guionistas");

        jLabel4.setText("Diseñador de escenarios");

        jLabel5.setText("Animador de personajes");

        jLabel6.setText("Actores de doblaje");

        jLabel7.setText("Guionista de Plotwist");

        jLabel8.setText("Ensambladores");

        TrabajadoresNick.setText("1");

        GuionistasNick.setText("0");

        DisenadoresNick.setText("0");

        AnimadorNick.setText("0");

        ActoresNick.setText("0");

        GuioPlotNick.setText("0");

        EnsambladoresNick.setText("0");

        MenosGuionistasNick.setText("-");
        MenosGuionistasNick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenosGuionistasNickActionPerformed(evt);
            }
        });

        MenosDisenadorNick.setText("-");
        MenosDisenadorNick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenosDisenadorNickActionPerformed(evt);
            }
        });

        MenosAnimadorNick.setText("-");
        MenosAnimadorNick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenosAnimadorNickActionPerformed(evt);
            }
        });

        MenosActoresNick.setText("-");
        MenosActoresNick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenosActoresNickActionPerformed(evt);
            }
        });

        MenosGuioPlotNick.setText("-");
        MenosGuioPlotNick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenosGuioPlotNickActionPerformed(evt);
            }
        });

        MenosEnsambladoresNick.setText("-");
        MenosEnsambladoresNick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenosEnsambladoresNickActionPerformed(evt);
            }
        });

        MasGuionistaNick.setText("+");
        MasGuionistaNick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasGuionistaNickActionPerformed(evt);
            }
        });

        MasAnimadorNick.setText("+");
        MasAnimadorNick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasAnimadorNickActionPerformed(evt);
            }
        });

        MasDisenadorNick.setText("+");
        MasDisenadorNick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasDisenadorNickActionPerformed(evt);
            }
        });

        MasActorNick.setText("+");
        MasActorNick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasActorNickActionPerformed(evt);
            }
        });

        MasGuioPlotNick.setText("+");
        MasGuioPlotNick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasGuioPlotNickActionPerformed(evt);
            }
        });

        MasEnsambladoresNick.setText("+");
        MasEnsambladoresNick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasEnsambladoresNickActionPerformed(evt);
            }
        });

        jLabel9.setText("TIEMPO");

        jLabel10.setText("Duracion de un día:");

        jLabel11.setText("segundos");

        jLabel12.setText("Deadline de entrega:");

        jLabel13.setText("días");

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        masDeadline.setText("+");
        masDeadline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masDeadlineActionPerformed(evt);
            }
        });

        deadline.setText("0");

        menosDeadline.setText("-");
        menosDeadline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menosDeadlineActionPerformed(evt);
            }
        });

        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });

        mostrarNick.setText("Mostrar");
        mostrarNick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarNickActionPerformed(evt);
            }
        });

        jLabel14.setText("Cartoon Network");

        jLabel15.setText("Trabajadores disponibles:");

        TrabajadoresCartoon.setText("1");

        jLabel16.setText("Guionistas");

        jLabel17.setText("Diseñador de escenarios");

        jLabel18.setText("Animador de personajes");

        jLabel19.setText("Actores de doblaje");

        jLabel20.setText("Guionistas de Plotwist");

        jLabel21.setText("Ensambladores");

        GuionistasCartoon.setText("0");

        DiseñadoresCartoon.setText("0");

        AnimadorCartoon.setText("0");

        ActoresCartoon.setText("0");

        GuioPlotCartoon.setText("0");

        EnsambladoresCartoon.setText("0");

        MenosGuiCartoon.setText("-");
        MenosGuiCartoon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenosGuiCartoonActionPerformed(evt);
            }
        });

        MasGuioCartoon.setText("+");
        MasGuioCartoon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasGuioCartoonActionPerformed(evt);
            }
        });

        MenosDiseñadorCartoon.setText("-");
        MenosDiseñadorCartoon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenosDiseñadorCartoonActionPerformed(evt);
            }
        });

        MasDiseñadorCartoon.setText("+");
        MasDiseñadorCartoon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasDiseñadorCartoonActionPerformed(evt);
            }
        });

        MenosAnimadorCartoon.setText("-");
        MenosAnimadorCartoon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenosAnimadorCartoonActionPerformed(evt);
            }
        });

        MasAnimadorCartoon.setText("+");
        MasAnimadorCartoon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasAnimadorCartoonActionPerformed(evt);
            }
        });

        MenosActCartoon.setText("-");
        MenosActCartoon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenosActCartoonActionPerformed(evt);
            }
        });

        MasActCartoon.setText("+");
        MasActCartoon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasActCartoonActionPerformed(evt);
            }
        });

        MenosPlotCartoon.setText("-");
        MenosPlotCartoon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenosPlotCartoonActionPerformed(evt);
            }
        });

        MasPlotCartoon.setText("+");
        MasPlotCartoon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasPlotCartoonActionPerformed(evt);
            }
        });

        MenosEnsambCartoon.setText("-");
        MenosEnsambCartoon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenosEnsambCartoonActionPerformed(evt);
            }
        });

        MasEnsambCartoon.setText("+");
        MasEnsambCartoon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasEnsambCartoonActionPerformed(evt);
            }
        });

        jLabel22.setText("Star Channel");

        jLabel23.setText("Trabajadores disponibles: ");

        TrabajadoresStar.setText("0");

        jLabel24.setText("Guionistas");

        jLabel25.setText("Diseñador de escenarios");

        jLabel26.setText("Animador de personajes");

        jLabel27.setText("Actores de doblaje");

        jLabel28.setText("Guionistas de Plotwist");

        jLabel29.setText("Ensambladores");

        GuionistasStar.setText("0");

        DiseñadoresStar.setText("0");

        AnimadorStar.setText("0");

        ActoresStar.setText("0");

        GuioPlotStar.setText("0");

        EnsambladoresStar.setText("0");

        MenosGuioStar.setText("-");
        MenosGuioStar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenosGuioStarActionPerformed(evt);
            }
        });

        MasGuioStar.setText("+");
        MasGuioStar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasGuioStarActionPerformed(evt);
            }
        });

        MenosDiseñadorStar.setText("-");
        MenosDiseñadorStar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenosDiseñadorStarActionPerformed(evt);
            }
        });

        MasDiseñadorStar.setText("+");
        MasDiseñadorStar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasDiseñadorStarActionPerformed(evt);
            }
        });

        MenosAnimadorStar.setText("-");
        MenosAnimadorStar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenosAnimadorStarActionPerformed(evt);
            }
        });

        MasAnimadorStar.setText("+");
        MasAnimadorStar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasAnimadorStarActionPerformed(evt);
            }
        });

        MenosActoresStar.setText("-");
        MenosActoresStar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenosActoresStarActionPerformed(evt);
            }
        });

        MasActoresStar.setText("+");
        MasActoresStar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasActoresStarActionPerformed(evt);
            }
        });

        MenosPlotStar.setText("-");
        MenosPlotStar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenosPlotStarActionPerformed(evt);
            }
        });

        MasPlotStar.setText("+");
        MasPlotStar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasPlotStarActionPerformed(evt);
            }
        });

        MenosEnsambStar.setText("-");
        MenosEnsambStar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenosEnsambStarActionPerformed(evt);
            }
        });

        MasEnsambStar.setText("+");
        MasEnsambStar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasEnsambStarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 318, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(DuracionDiaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(menosDeadline, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(masDeadline, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(deadline, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mostrarNick, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Guardar, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)))
                .addGap(15, 15, 15))
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TrabajadoresNick)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(GuionistasNick)
                        .addGap(18, 18, 18)
                        .addComponent(MenosGuionistasNick)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MasGuionistaNick))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(DisenadoresNick)
                        .addGap(18, 18, 18)
                        .addComponent(MenosDisenadorNick)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MasDisenadorNick))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AnimadorNick)
                        .addGap(18, 18, 18)
                        .addComponent(MenosAnimadorNick)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MasAnimadorNick))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ActoresNick)
                        .addGap(18, 18, 18)
                        .addComponent(MenosActoresNick)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MasActorNick))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(GuioPlotNick)
                        .addGap(18, 18, 18)
                        .addComponent(MenosGuioPlotNick)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MasGuioPlotNick))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(EnsambladoresNick)
                        .addGap(18, 18, 18)
                        .addComponent(MenosEnsambladoresNick)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MasEnsambladoresNick)))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel9)
                        .addComponent(jLabel18)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TrabajadoresCartoon)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(GuionistasCartoon)
                        .addGap(18, 18, 18)
                        .addComponent(MenosGuiCartoon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MasGuioCartoon))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(DiseñadoresCartoon)
                        .addGap(18, 18, 18)
                        .addComponent(MenosDiseñadorCartoon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MasDiseñadorCartoon))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AnimadorCartoon)
                        .addGap(18, 18, 18)
                        .addComponent(MenosAnimadorCartoon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MasAnimadorCartoon))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ActoresCartoon)
                        .addGap(18, 18, 18)
                        .addComponent(MenosActCartoon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MasActCartoon))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(GuioPlotCartoon)
                        .addGap(18, 18, 18)
                        .addComponent(MenosPlotCartoon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MasPlotCartoon))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(EnsambladoresCartoon)
                        .addGap(18, 18, 18)
                        .addComponent(MenosEnsambCartoon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MasEnsambCartoon)))
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ActoresStar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MenosActoresStar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MasActoresStar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(GuionistasStar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MenosGuioStar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MasGuioStar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AnimadorStar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MenosAnimadorStar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MasAnimadorStar))
                    .addComponent(TrabajadoresStar)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(DiseñadoresStar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MenosDiseñadorStar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MasDiseñadorStar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(GuioPlotStar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MenosPlotStar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MasPlotStar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(EnsambladoresStar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MenosEnsambStar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MasEnsambStar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jLabel1)
                .addGap(235, 235, 235)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(176, 176, 176))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel14)
                    .addComponent(jLabel22))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TrabajadoresNick)
                    .addComponent(jLabel15)
                    .addComponent(TrabajadoresCartoon)
                    .addComponent(jLabel23)
                    .addComponent(TrabajadoresStar))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(GuionistasNick)
                    .addComponent(MenosGuionistasNick)
                    .addComponent(MasGuionistaNick)
                    .addComponent(jLabel16)
                    .addComponent(GuionistasCartoon)
                    .addComponent(MenosGuiCartoon)
                    .addComponent(MasGuioCartoon)
                    .addComponent(jLabel24)
                    .addComponent(GuionistasStar)
                    .addComponent(MenosGuioStar)
                    .addComponent(MasGuioStar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(DisenadoresNick)
                    .addComponent(MenosDisenadorNick)
                    .addComponent(MasDisenadorNick)
                    .addComponent(jLabel17)
                    .addComponent(DiseñadoresCartoon)
                    .addComponent(MenosDiseñadorCartoon)
                    .addComponent(MasDiseñadorCartoon)
                    .addComponent(jLabel25)
                    .addComponent(DiseñadoresStar)
                    .addComponent(MenosDiseñadorStar)
                    .addComponent(MasDiseñadorStar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(AnimadorNick)
                    .addComponent(MenosAnimadorNick)
                    .addComponent(MasAnimadorNick)
                    .addComponent(jLabel18)
                    .addComponent(AnimadorCartoon)
                    .addComponent(MenosAnimadorCartoon)
                    .addComponent(MasAnimadorCartoon)
                    .addComponent(jLabel26)
                    .addComponent(AnimadorStar)
                    .addComponent(MenosAnimadorStar)
                    .addComponent(MasAnimadorStar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(ActoresNick)
                    .addComponent(MenosActoresNick)
                    .addComponent(MasActorNick)
                    .addComponent(jLabel19)
                    .addComponent(ActoresCartoon)
                    .addComponent(MenosActCartoon)
                    .addComponent(MasActCartoon)
                    .addComponent(jLabel27)
                    .addComponent(ActoresStar)
                    .addComponent(MenosActoresStar)
                    .addComponent(MasActoresStar))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(GuioPlotNick)
                            .addComponent(jLabel7)
                            .addComponent(jLabel20)
                            .addComponent(GuioPlotCartoon))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(EnsambladoresNick)
                            .addComponent(MenosEnsambladoresNick)
                            .addComponent(MasEnsambladoresNick)
                            .addComponent(jLabel21)
                            .addComponent(EnsambladoresCartoon)
                            .addComponent(MenosEnsambCartoon)
                            .addComponent(MasEnsambCartoon))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(MasPlotCartoon)
                                .addComponent(MenosPlotCartoon)
                                .addComponent(jLabel28)
                                .addComponent(GuioPlotStar)
                                .addComponent(MenosPlotStar)
                                .addComponent(MasPlotStar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(MasGuioPlotNick)
                                .addComponent(MenosGuioPlotNick)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(MenosEnsambStar)
                            .addComponent(MasEnsambStar)
                            .addComponent(EnsambladoresStar))
                        .addGap(68, 68, 68)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(DuracionDiaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(deadline)
                            .addComponent(jLabel13)
                            .addComponent(menosDeadline)
                            .addComponent(masDeadline, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(mostrarNick)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Guardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenosGuionistasNickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenosGuionistasNickActionPerformed
        // TODO add your handling code here:
        if (this.Nickelodeon.getGuionistas().getSize() > 1) {
            this.Nickelodeon.eliminarTrabajador(0);
        } else {
            minErrorTrab();
        }
    }//GEN-LAST:event_MenosGuionistasNickActionPerformed

    private void MenosDisenadorNickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenosDisenadorNickActionPerformed
        // TODO add your handling code here:
        if (this.Nickelodeon.getDisenadores().getSize() > 1) {
            this.Nickelodeon.eliminarTrabajador(1);
        } else {
            minErrorTrab();
        }
    }//GEN-LAST:event_MenosDisenadorNickActionPerformed

    private void MenosAnimadorNickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenosAnimadorNickActionPerformed
        // TODO add your handling code here:
        if (this.Nickelodeon.getAnimadores().getSize() > 1) {
            this.Nickelodeon.eliminarTrabajador(2);
        } else {
            minErrorTrab();
        }
    }//GEN-LAST:event_MenosAnimadorNickActionPerformed

    private void MenosActoresNickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenosActoresNickActionPerformed
        // TODO add your handling code here:
        if (this.Nickelodeon.getActores().getSize() > 1) {
            this.Nickelodeon.eliminarTrabajador(3);
        } else {
            minErrorTrab();
        }
    }//GEN-LAST:event_MenosActoresNickActionPerformed

    private void MenosGuioPlotNickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenosGuioPlotNickActionPerformed
        // TODO add your handling code here:
        if (this.Nickelodeon.getGuionistasPT().getSize() > 1) {
            this.Nickelodeon.eliminarTrabajador(4);
        } else {
            minErrorTrab();
        }
    }//GEN-LAST:event_MenosGuioPlotNickActionPerformed

    private void MenosEnsambladoresNickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenosEnsambladoresNickActionPerformed
        // TODO add your handling code here:
        if (this.Nickelodeon.getEnsambladores().getSize() > 1) {
            this.Nickelodeon.borrarEnsamblador();
        } else {
            minErrorTrab();
        }
    }//GEN-LAST:event_MenosEnsambladoresNickActionPerformed

    private void MasGuionistaNickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasGuionistaNickActionPerformed
        // TODO add your handling code here:
        Trabajadores nickt1 = new Trabajadores(0, "Nickelodeon", duracionDia, Nickelodeon.getDrive(), Nickelodeon.getMutex());
        Nickelodeon.AggTrabajador(nickt1);
    }//GEN-LAST:event_MasGuionistaNickActionPerformed

    private void MasAnimadorNickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasAnimadorNickActionPerformed
        // TODO add your handling code here:
        Trabajadores nickt1 = new Trabajadores(2, "Nickelodeon", duracionDia, Nickelodeon.getDrive(), Nickelodeon.getMutex());
        Nickelodeon.AggTrabajador(nickt1);

    }//GEN-LAST:event_MasAnimadorNickActionPerformed

    private void MasDisenadorNickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasDisenadorNickActionPerformed
        // TODO add your handling code here:
        Trabajadores nickt1 = new Trabajadores(1, "Nickelodeon", duracionDia, Nickelodeon.getDrive(), Nickelodeon.getMutex());
        Nickelodeon.AggTrabajador(nickt1);
    }//GEN-LAST:event_MasDisenadorNickActionPerformed

    private void MasActorNickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasActorNickActionPerformed
        // TODO add your handling code here:
        Trabajadores nickt1 = new Trabajadores(3, "Nickelodeon", duracionDia, Nickelodeon.getDrive(), Nickelodeon.getMutex());
        Nickelodeon.AggTrabajador(nickt1);
    }//GEN-LAST:event_MasActorNickActionPerformed

    private void MasGuioPlotNickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasGuioPlotNickActionPerformed
        // TODO add your handling code here:
        Trabajadores nickt1 = new Trabajadores(4, "Nickelodeon", duracionDia, Nickelodeon.getDrive(), Nickelodeon.getMutex());
        Nickelodeon.AggTrabajador(nickt1);
    }//GEN-LAST:event_MasGuioPlotNickActionPerformed

    private void MasEnsambladoresNickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasEnsambladoresNickActionPerformed
        // TODO add your handling code here:
        Ensamblador nicke1 = new Ensamblador(Nickelodeon.getDrive(), Nickelodeon.getMutex(), duracionDia);
        Nickelodeon.AggEnsamblador(nicke1);
    }//GEN-LAST:event_MasEnsambladoresNickActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

    private void menosDeadlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menosDeadlineActionPerformed
        // TODO add your handling code here:
        int deadlineActual = Nickelodeon.getDrive().getDeadline();
        if (deadlineActual > 1) {
            deadlineActual--;
            Nickelodeon.getDrive().setDeadline(deadlineActual);

            this.deadline.setText(Integer.toString(deadlineActual));

        }
    }//GEN-LAST:event_menosDeadlineActionPerformed

    private void masDeadlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masDeadlineActionPerformed
        // TODO add your handling code here:
        int deadlineActual = Nickelodeon.getDrive().getDeadline();
        deadlineActual++;
        Nickelodeon.getDrive().setDeadline(deadlineActual);


        this.deadline.setText(Integer.toString(deadlineActual));
    }//GEN-LAST:event_masDeadlineActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
 

    }//GEN-LAST:event_GuardarActionPerformed

    private void mostrarNickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarNickActionPerformed
        // TODO add your handling code here:        
 
           nick.setVisible(true); 
       
            cartoon.setVisible(true); 
      
            starchannel.setVisible(true); 
       
        
    }//GEN-LAST:event_mostrarNickActionPerformed

    private void MenosGuiCartoonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenosGuiCartoonActionPerformed
        // TODO add your handling code here:
         if (this.Cartoon.getGuionistas().getSize() > 1) {
            this.Cartoon.eliminarTrabajador(0);
        } else {
            minErrorTrab();
        }
    }//GEN-LAST:event_MenosGuiCartoonActionPerformed

    private void MasPlotStarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasPlotStarActionPerformed
        // TODO add your handling code here:
         Trabajadores start1 = new Trabajadores(4, "StarChannel", duracionDia, StarChannel.getDrive(), StarChannel.getMutex());
        StarChannel.AggTrabajador(start1);
    }//GEN-LAST:event_MasPlotStarActionPerformed

    private void MasGuioCartoonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasGuioCartoonActionPerformed
        // TODO add your handling code here:
        Trabajadores cartoont1 = new Trabajadores(0, "Cartoon", duracionDia, Cartoon.getDrive(), Cartoon.getMutex());
        Cartoon.AggTrabajador(cartoont1);
    }//GEN-LAST:event_MasGuioCartoonActionPerformed

    private void MenosDiseñadorCartoonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenosDiseñadorCartoonActionPerformed
        // TODO add your handling code here:
            if (this.Cartoon.getDisenadores().getSize() > 1) {
            this.Cartoon.eliminarTrabajador(1);
        } else {
            minErrorTrab();
        }
    }//GEN-LAST:event_MenosDiseñadorCartoonActionPerformed

    private void MasDiseñadorCartoonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasDiseñadorCartoonActionPerformed
        // TODO add your handling code here:
        Trabajadores cartoont1 = new Trabajadores(1, "Cartoon", duracionDia, Cartoon.getDrive(), Cartoon.getMutex());
        Cartoon.AggTrabajador(cartoont1);
    }//GEN-LAST:event_MasDiseñadorCartoonActionPerformed

    private void MenosAnimadorCartoonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenosAnimadorCartoonActionPerformed
        // TODO add your handling code here:
        if (this.Cartoon.getAnimadores().getSize() > 1) {
            this.Cartoon.eliminarTrabajador(2);
        } else {
            minErrorTrab();
        }
    }//GEN-LAST:event_MenosAnimadorCartoonActionPerformed

    private void MasAnimadorCartoonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasAnimadorCartoonActionPerformed
        // TODO add your handling code here:
        Trabajadores cartoont1 = new Trabajadores(2, "Cartoon", duracionDia, Cartoon.getDrive(), Cartoon.getMutex());
        Cartoon.AggTrabajador(cartoont1);
    }//GEN-LAST:event_MasAnimadorCartoonActionPerformed

    private void MenosActCartoonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenosActCartoonActionPerformed
        // TODO add your handling code here:
           if (this.Cartoon.getActores().getSize() > 1) {
            this.Cartoon.eliminarTrabajador(3);
        } else {
            minErrorTrab();
        }
    }//GEN-LAST:event_MenosActCartoonActionPerformed

    private void MasActCartoonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasActCartoonActionPerformed
        // TODO add your handling code here:
        Trabajadores cartoont1 = new Trabajadores(3, "Cartoon", duracionDia, Cartoon.getDrive(), Cartoon.getMutex());
        Cartoon.AggTrabajador(cartoont1);
    }//GEN-LAST:event_MasActCartoonActionPerformed

    private void MenosPlotCartoonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenosPlotCartoonActionPerformed
        // TODO add your handling code here:
        if (this.Cartoon.getGuionistasPT().getSize() > 1) {
            this.Cartoon.eliminarTrabajador(4);
        } else {
            minErrorTrab();
        }
    }//GEN-LAST:event_MenosPlotCartoonActionPerformed

    private void MasPlotCartoonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasPlotCartoonActionPerformed
        // TODO add your handling code here:
        
        Trabajadores cartoont1 = new Trabajadores(4, "Cartoon", duracionDia, Cartoon.getDrive(), Cartoon.getMutex());
        Cartoon.AggTrabajador(cartoont1);
    }//GEN-LAST:event_MasPlotCartoonActionPerformed

    private void MenosEnsambCartoonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenosEnsambCartoonActionPerformed
        // TODO add your handling code here:
        if (this.Cartoon.getEnsambladores().getSize() > 1) {
            this.Cartoon.borrarEnsamblador();
        } else {
            minErrorTrab();
        }
    }//GEN-LAST:event_MenosEnsambCartoonActionPerformed

    private void MasEnsambCartoonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasEnsambCartoonActionPerformed
        // TODO add your handling code here:
        Ensamblador cartoone1 = new Ensamblador(Cartoon.getDrive(), Cartoon.getMutex(), duracionDia);
        Cartoon.AggEnsamblador(cartoone1);
    }//GEN-LAST:event_MasEnsambCartoonActionPerformed

    private void MenosGuioStarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenosGuioStarActionPerformed
        // TODO add your handling code here:
       if (this.StarChannel.getGuionistas().getSize() > 1) {
            this.StarChannel.eliminarTrabajador(0);
        } else {
            minErrorTrab();
        }
    }//GEN-LAST:event_MenosGuioStarActionPerformed

    private void MasGuioStarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasGuioStarActionPerformed
        // TODO add your handling code here:
        Trabajadores start1 = new Trabajadores(0, "StarChannel", duracionDia, StarChannel.getDrive(), StarChannel.getMutex());
        StarChannel.AggTrabajador(start1);
    }//GEN-LAST:event_MasGuioStarActionPerformed

    private void MenosDiseñadorStarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenosDiseñadorStarActionPerformed
        // TODO add your handling code here:
            if (this.StarChannel.getDisenadores().getSize() > 1) {
            this.StarChannel.eliminarTrabajador(1);
        } else {
            minErrorTrab();
        }
    }//GEN-LAST:event_MenosDiseñadorStarActionPerformed

    private void MasDiseñadorStarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasDiseñadorStarActionPerformed
        // TODO add your handling code here:
        Trabajadores start1 = new Trabajadores(1, "StarChannel", duracionDia, StarChannel.getDrive(), StarChannel.getMutex());
        StarChannel.AggTrabajador(start1);
    }//GEN-LAST:event_MasDiseñadorStarActionPerformed

    private void MenosAnimadorStarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenosAnimadorStarActionPerformed
        // TODO add your handling code here:
            if (this.StarChannel.getAnimadores().getSize() > 1) {
            this.StarChannel.eliminarTrabajador(2);
        } else {
            minErrorTrab();
        }
    }//GEN-LAST:event_MenosAnimadorStarActionPerformed

    private void MasAnimadorStarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasAnimadorStarActionPerformed
        // TODO add your handling code here:
        Trabajadores start1 = new Trabajadores(2, "StarChannel", duracionDia, StarChannel.getDrive(), StarChannel.getMutex());
        StarChannel.AggTrabajador(start1);
    }//GEN-LAST:event_MasAnimadorStarActionPerformed

    private void MenosActoresStarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenosActoresStarActionPerformed
        // TODO add your handling code here:
           if (this.StarChannel.getActores().getSize() > 1) {
            this.StarChannel.eliminarTrabajador(3);
        } else {
            minErrorTrab();
        }
    }//GEN-LAST:event_MenosActoresStarActionPerformed

    private void MasActoresStarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasActoresStarActionPerformed
        // TODO add your handling code here:
        Trabajadores start1 = new Trabajadores(3, "StarChannel", duracionDia, StarChannel.getDrive(), StarChannel.getMutex());
        StarChannel.AggTrabajador(start1);
    }//GEN-LAST:event_MasActoresStarActionPerformed

    private void MenosPlotStarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenosPlotStarActionPerformed
        // TODO add your handling code here:
          if (this.StarChannel.getGuionistasPT().getSize() > 1) {
            this.StarChannel.eliminarTrabajador(4);
        } else {
            minErrorTrab();
        }
    }//GEN-LAST:event_MenosPlotStarActionPerformed

    private void MenosEnsambStarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenosEnsambStarActionPerformed
        // TODO add your handling code here:
        if (this.StarChannel.getEnsambladores().getSize() > 1) {
            this.StarChannel.borrarEnsamblador();
        } else {
            minErrorTrab();
        }
    }//GEN-LAST:event_MenosEnsambStarActionPerformed

    private void MasEnsambStarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasEnsambStarActionPerformed
        // TODO add your handling code here:
        Ensamblador start1 = new Ensamblador(StarChannel.getDrive(), StarChannel.getMutex(), duracionDia);
        StarChannel.AggEnsamblador(start1);
    }//GEN-LAST:event_MasEnsambStarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ActoresCartoon;
    private javax.swing.JLabel ActoresNick;
    private javax.swing.JLabel ActoresStar;
    private javax.swing.JLabel AnimadorCartoon;
    private javax.swing.JLabel AnimadorNick;
    private javax.swing.JLabel AnimadorStar;
    private javax.swing.JLabel DisenadoresNick;
    private javax.swing.JLabel DiseñadoresCartoon;
    private javax.swing.JLabel DiseñadoresStar;
    private javax.swing.JTextField DuracionDiaTF;
    private javax.swing.JLabel EnsambladoresCartoon;
    private javax.swing.JLabel EnsambladoresNick;
    private javax.swing.JLabel EnsambladoresStar;
    private javax.swing.JButton Guardar;
    private javax.swing.JLabel GuioPlotCartoon;
    private javax.swing.JLabel GuioPlotNick;
    private javax.swing.JLabel GuioPlotStar;
    private javax.swing.JLabel GuionistasCartoon;
    private javax.swing.JLabel GuionistasNick;
    private javax.swing.JLabel GuionistasStar;
    private javax.swing.JButton MasActCartoon;
    private javax.swing.JButton MasActorNick;
    private javax.swing.JButton MasActoresStar;
    private javax.swing.JButton MasAnimadorCartoon;
    private javax.swing.JButton MasAnimadorNick;
    private javax.swing.JButton MasAnimadorStar;
    private javax.swing.JButton MasDisenadorNick;
    private javax.swing.JButton MasDiseñadorCartoon;
    private javax.swing.JButton MasDiseñadorStar;
    private javax.swing.JButton MasEnsambCartoon;
    private javax.swing.JButton MasEnsambStar;
    private javax.swing.JButton MasEnsambladoresNick;
    private javax.swing.JButton MasGuioCartoon;
    private javax.swing.JButton MasGuioPlotNick;
    private javax.swing.JButton MasGuioStar;
    private javax.swing.JButton MasGuionistaNick;
    private javax.swing.JButton MasPlotCartoon;
    private javax.swing.JButton MasPlotStar;
    private javax.swing.JButton MenosActCartoon;
    private javax.swing.JButton MenosActoresNick;
    private javax.swing.JButton MenosActoresStar;
    private javax.swing.JButton MenosAnimadorCartoon;
    private javax.swing.JButton MenosAnimadorNick;
    private javax.swing.JButton MenosAnimadorStar;
    private javax.swing.JButton MenosDisenadorNick;
    private javax.swing.JButton MenosDiseñadorCartoon;
    private javax.swing.JButton MenosDiseñadorStar;
    private javax.swing.JButton MenosEnsambCartoon;
    private javax.swing.JButton MenosEnsambStar;
    private javax.swing.JButton MenosEnsambladoresNick;
    private javax.swing.JButton MenosGuiCartoon;
    private javax.swing.JButton MenosGuioPlotNick;
    private javax.swing.JButton MenosGuioStar;
    private javax.swing.JButton MenosGuionistasNick;
    private javax.swing.JButton MenosPlotCartoon;
    private javax.swing.JButton MenosPlotStar;
    private javax.swing.JLabel TrabajadoresCartoon;
    private javax.swing.JLabel TrabajadoresNick;
    private javax.swing.JLabel TrabajadoresStar;
    private javax.swing.JLabel deadline;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton masDeadline;
    private javax.swing.JButton menosDeadline;
    private javax.swing.JButton mostrarNick;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
