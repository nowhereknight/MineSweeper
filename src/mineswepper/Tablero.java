package mineswepper;

import java.awt.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;

public final class Tablero extends javax.swing.JFrame implements ActionListener, ContainerListener{
    int n;
    //Panel de tablero
    JPanel panel = new JPanel();
    JPanel botones = new JPanel();
    //Panel de botones
    JPanel opciones = new JPanel();
    //Multiarreglo que almacena los botones de las casillas. Parte gráfica de los botones
    JButton[][] cartas;
    //Arreglo de iconos
    ImageIcon[] iconos = new ImageIcon[12];
    MouseHandler mh;
    //Multiarreglo que almacena la parte lógica de las casillas
    Casilla[][] casillas;
    JButton regresar= new JButton("Regresar");
    JButton pausar = new JButton("Pausar");
    JButton reaundar = new JButton("Reanudar");
    int numMines;
    JTextField tf_time;
    Stopwatch sw;
    boolean starttime=false, isPaused=false;
    long time;
    int score;
    Jugador jugador;
    
    int casillasMarcadas=0;
    
    public Tablero(Jugador jugador) {
        this.n=8;
        this.jugador=jugador;
        initComponents();
        Initialize();
         
        setSize(400, 400);
        }
    
    public Tablero(int n, Jugador jugador) {
        this.n=n;
        this.jugador=jugador;
        
        initComponents();
        Initialize();
        setSize(n*45, n*40);
    }
    
    public void Initialize(){
        sw=new Stopwatch();
        this.tf_time = new JTextField("0000");
        this.cartas = new JButton[n][n];
        numMines=20;
        this.casillas = new Casilla[n][n];
        tf_time.setEditable(false);
        setPanel();
        setResizable(false);
        
        pausar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PauseButtonActionPerformed(evt);
            }
        });
        
        reaundar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReaundarButtonActionPerformed(evt);
            }
        });
        regresar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnButtonActionPerformed(evt);
            }
        });
    }
    
    private void PauseButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        sw.stop();
        isPaused=true;
        panel.setVisible(false);
    } 
    
    private void ReaundarButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        if(isPaused){
            sw.Start(sw.a);
            isPaused=false;
            panel.setVisible(true);
        }  
    } 
    
    private void ReturnButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        dispose();
        new ElegirJuegoVentana(jugador).setVisible(true); 
    } 
      
    public void setPanel(){
        //Establece las casillas y el tablero y el panel de opciones
        getContentPane().removeAll();
        panel.removeAll();
        botones.removeAll();
        opciones.removeAll();
        
        botones.add(regresar, BorderLayout.SOUTH);
        botones.add(pausar, BorderLayout.NORTH);
        botones.add(reaundar, BorderLayout.CENTER);
        botones.setPreferredSize(new Dimension(80, 300));
           
        opciones.setLayout(new BorderLayout());
        opciones.add(tf_time, BorderLayout.NORTH);
        opciones.add(botones, BorderLayout.SOUTH);
        
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), BorderFactory.createLoweredBevelBorder()));
        panel.setPreferredSize(new Dimension(300, 300));
        panel.setLayout(new GridLayout(0, n));
        panel.addContainerListener(this);
        mh = new MouseHandler();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                casillas[i][j] = new Casilla(i,j);
                cartas[i][j] = new JButton("");
                cartas[i][j].setActionCommand(String.format("%d:%d",i,j));
                //cartas[i][j].addActionListener(this);
                cartas[i][j].addMouseListener(mh);
                panel.add(cartas[i][j]);
             }
        }
        
        panel.revalidate();
        panel.repaint();
        reset();
        setMines();
        setValues();
        printTablero();
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().addContainerListener(this);
        getContentPane().revalidate();
        getContentPane().repaint();
        panel.revalidate();
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(opciones, BorderLayout.EAST);
        setVisible(true);
    }
    
    public void reset(){
         for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                casillas[i][j].setValue(0);
                casillas[i][j].setMine(false);
                cartas[i][j].setEnabled(true);
                cartas[i][j].setBackground(null);
             }
        }
    }
    
    public void setMines(){
        while (numMines>0){
            Random rand = new Random();
            int row=rand.nextInt(n);
            int col= rand.nextInt(n);
            
            if(!casillas[row][col].hasMine()){
                casillas[row][col].setMine(true);
                numMines-=1;
            }
        }
    }
   public void setValues(){
            for(int i = 0; i<n; i++){
                for(int j = 0; j<n; j++){
                     if(!casillas[i][j].hasMine()){
                         if(j>0 && casillas[i][j-1].hasMine()) casillas[i][j].incrementValue();
                         if(j< n-1 && casillas[i][j+1].hasMine()) casillas[i][j].incrementValue();
                         if(i>0 && casillas[i-1][j].hasMine()) casillas[i][j].incrementValue();
                         if(i< n-1 && casillas[i+1][j].hasMine()) casillas[i][j].incrementValue();
                         if(i>0 && j>0 && casillas[i-1][j-1].hasMine()) casillas[i][j].incrementValue();
                         if(i<n-1 && j<n-1 && casillas[i+1][j+1].hasMine()) casillas[i][j].incrementValue();
                         if(i>0 && j< n-1 && casillas[i-1][j+1].hasMine()) casillas[i][j].incrementValue();
                         if(i<n-1 && j>0 && casillas[i+1][j-1].hasMine()) casillas[i][j].incrementValue();
                     }
                }
            }
        }
    
    public void printTablero(){
        String tablero="";
        for(int i=0; i<n;i++){
            for(int j=0; j<n;j++){
                if(casillas[i][j].hasMine()){
                    tablero+=" * ";
                }else{
                    tablero+=" "+Integer.toString(casillas[i][j].getValue())+" ";
                }
            }
            tablero+="\n";
        }
        System.out.println(tablero);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) { }

    @Override
    public void componentAdded(ContainerEvent e) {}

    @Override
    public void componentRemoved(ContainerEvent e) {}
    
    public class MouseHandler implements MouseListener {

    @Override
    public void mousePressed(MouseEvent e) {
      if (starttime == false) {
                sw.Start();
                starttime = true;
            }
      //Obtener el botón que causó el evento y castearlo
      JButton boton = (JButton) e.getSource();
      //Obtener coordenadas del botón causante del evento
      String nombre=boton.getActionCommand();
      //Obtener las coordenadas i, j del botón
      String[] coordenadas = nombre.split(":");
      int i=Integer.parseInt(coordenadas[0]);
      int j=Integer.parseInt(coordenadas[1]);
      
      if (SwingUtilities.isLeftMouseButton(e)){
          if(casillas[i][j].hasMine()){
              cartas[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mine.gif")));
              System.out.println("Perdiste");
              stopGame();
              JOptionPane.showMessageDialog(Tablero.this,
                "Has hallado una mina",
                "Perdiste",
                JOptionPane.ERROR_MESSAGE);
              jugador.setRoundsLost(jugador.getRoundsLost()+1);
              updateStatistics(0,0,1);
              if(casillasMarcadas!=0)
                  sw.stop();
            }else{
                Random rand = new Random();
                int exp=rand.nextInt(n);
                
                pressCasilla(i,j,exp);
            }
      } else {
          if(casillas[i][j].hasFlag()){
              casillasMarcadas-=1;
              cartas[i][j].setBackground(null);
              cartas[i][j].setForeground(null);
              cartas[i][j].setText("");
              cartas[i][j].setIcon(null);
              casillas[i][j].unsetFlag();
          }else{
              casillasMarcadas+=1;
              cartas[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/flag.gif")));
              casillas[i][j].setFlag();
          }
           
           //cartas[i][j].setEnabled(false);
      }
      
      if(casillasMarcadas >= n*n){
                JOptionPane.showMessageDialog(Tablero.this,
                "Felicidades :) "+jugador.getUsername(),
                "Ganaste",
                JOptionPane.ERROR_MESSAGE);
                jugador.setRoundsWon(jugador.getRoundsWon()+1);
                if(n<16){
                    score=100;
                    
                }else if(n<25){
                    jugador.setScore(jugador.getScore()+200);
                }else{
                    jugador.setScore(jugador.getScore()+300);
                }
            jugador.setScore(jugador.getScore()+score);
            updateStatistics(score,1,0);
        }
        
    }
    
    public void stopGame(){
         for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(casillas[i][j].hasMine())
                    cartas[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mine.gif")));
                cartas[i][j].setEnabled(false);
            }
        }
    }
    
    public int pressCasilla(int i, int j,int exp){
        if(casillas[i][j].isPressed())
            return -1;
        casillasMarcadas+=1;
        casillas[i][j].press();
        cartas[i][j].setEnabled(false);
        if(casillas[i][j].getValue() != 0) {
                  cartas[i][j].setText(Integer.toString(casillas[i][j].getValue()));
              }
        cartas[i][j].setBackground(Color.GRAY);
        cartas[i][j].setForeground(Color.GRAY);

        Random rand = new Random();
        int yes;
        while(exp>1){
            yes=rand.nextInt(2);
            if( j>0 && yes==1 && !casillas[i][j-1].hasMine()) {
                exp-=1;
                exp=pressCasilla(i,j-1,exp);
            }
            yes=rand.nextInt(2);
            if(j<n-1 && yes==1 && !casillas[i][j+1].hasMine()){
                exp-=1;
                exp=pressCasilla(i,j+1,exp);
            }
            yes=rand.nextInt(2);
            if(i>0 && yes==1 && !casillas[i-1][j].hasMine()) {
                exp-=1;
                exp=pressCasilla(i-1,j,exp);
            }
            yes=rand.nextInt(2);
            if(i< n-1 && yes==1 && !casillas[i+1][j].hasMine()) {
                exp-=1;
                exp=pressCasilla(i+1,j,exp); 
            }
            yes=rand.nextInt(2);
            if(i>0 && j>0 && yes==1 && !casillas[i-1][j-1].hasMine()){
                exp-=1;
                exp=pressCasilla(i-1,j-1,exp);
            }
            yes=rand.nextInt(2);
            if(i<n-1 && j<n-1 && yes==1 && !casillas[i+1][j+1].hasMine()) {
                exp-=1;
                exp=pressCasilla(i+1,j+1,exp);
            }
            yes=rand.nextInt(2);
            if(i>0 && j<n-1 && yes==1 && !casillas[i-1][j+1].hasMine()){
                exp-=1;
                exp=pressCasilla(i-1,j+1,exp); 
            } 
            yes=rand.nextInt(2);
            if(i<n-1 && j>0 && yes==1 && !casillas[i+1][j-1].hasMine()) {
                exp-=1;
                exp=pressCasilla(i+1,j-1,exp); 
            }
        }
        return exp;
    }
    
    public void updateStatistics(int score, int won, int lost){
        File fileToBeModified = new File("usuarios.txt");
        String oldString=jugador.getUsername()+"::"+jugador.getPassword()+"::"+(jugador.getScore()-score)+"::"
                +(jugador.getRoundsWon()-won)+"::"+(jugador.getRoundsLost()-lost)+System.lineSeparator();
        String newString=jugador.getUsername()+"::"+jugador.getPassword()+"::"+jugador.getScore()+"::"+
                jugador.getRoundsWon()+"::"+jugador.getRoundsLost()+System.lineSeparator();
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
         
        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            String line = reader.readLine();
             
            while (line != null) 
            {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            String newContent = oldContent.replaceAll(oldString, newString);
             
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
        }
        catch (IOException e){ e.printStackTrace();}
        finally
        {
            try
            {
                reader.close();
                writer.close();
            } 
            catch (IOException e) 
            {e.printStackTrace();            }
        }
    }
    
    public void mouseReleased(MouseEvent e) {    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}
    
    }

    public class Stopwatch extends JFrame implements Runnable {

        long startTime;
        Thread updater;
        boolean isRunning = false;
        long a = 0;
        Runnable displayUpdater = new Runnable() {

            public void run() {
                displayElapsedTime(a);
                a++;
            }
        };

        public void stop() {
            long elapsed = a;
            isRunning = false;
            try {
                updater.join();
            } catch (InterruptedException ie) {  }
            displayElapsedTime(elapsed);
            //a = 0;
        }

        private void displayElapsedTime(long elapsedTime) {
            if (elapsedTime >= 0 && elapsedTime < 9) {
                tf_time.setText("00" + elapsedTime);
            } else if (elapsedTime > 9 && elapsedTime < 99) {
                tf_time.setText("0" + elapsedTime);
            } else if (elapsedTime > 99 && elapsedTime < 999) {
                tf_time.setText("" + elapsedTime);
            }
        }

        public void run() {
            try {
                while (isRunning) {
                    SwingUtilities.invokeAndWait(displayUpdater);
                    Thread.sleep(1000);
                }
            } catch (java.lang.reflect.InvocationTargetException ite) {
                ite.printStackTrace(System.err);
            } catch (InterruptedException ie) {
            }
        }

        public void Start() {
            startTime = System.currentTimeMillis();
            isRunning = true;
            updater = new Thread(this);
            updater.start();
        }
        
        public void Start(long a) {
            startTime = a;
            isRunning = true;
            updater = new Thread(this);
            updater.start();
            
        }
    }
}
