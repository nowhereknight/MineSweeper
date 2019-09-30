package mineswepper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class ConsultarEstadisticasVentana extends javax.swing.JFrame {

    public static final int width = 800;
    public static final int height = 400;
    private ArrayList<Jugador> jugadores;
    JButton regresar;

    public ConsultarEstadisticasVentana() {
        initComponents();
        setResizable(false);
        setSize(width, height);
        jugadores = new ArrayList<Jugador>();
        try{
            BufferedReader in = new BufferedReader(new FileReader("usuarios.txt"));
            String s;
            while((s = in.readLine()) != null){
                String[] linea = s.split("::");
                System.out.println(linea[0]+" "+linea[1]+" "+linea[2]+" "+linea[3]+" "+linea[4]);
                jugadores.add(new Jugador(linea[0],linea[1],Integer.parseInt(linea[2]),
                            Integer.parseInt(linea[3]),Integer.parseInt(linea[4])));            
            }
        }catch(HeadlessException | IOException e){}
        Collections.sort(jugadores, new Comparator<Jugador>() {
        @Override
        public int compare(Jugador lhs, Jugador rhs) {
            // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
            return lhs.getScore() > rhs.getScore() ? -1 : (lhs.getScore() < rhs.getScore()) ? 1 : 0;
        }
         });
        
        setStatistics();
            
    }

    public void regresarButtonActionPerformed(java.awt.event.ActionEvent evt){
        dispose();
        new VentanaPrincipal().setVisible(true);
    }
    
    public void setStatistics(){
        String[] columnNames = {"#","Usuario",
                        "Puntaje",
                        "Partidas Ganadas",
                        "Partidas Perdidas"};
        Object[][] topPlayers=new Object[10][6];
        for(int i=0; i<10;i++){
            topPlayers[i][0]=new Integer(i+1);
            topPlayers[i][1]=jugadores.get(i).getUsername();
            topPlayers[i][2]=(Integer) jugadores.get(i).getScore();
            topPlayers[i][3]=(Integer) jugadores.get(i).getRoundsWon();
            topPlayers[i][4]=(Integer) jugadores.get(i).getRoundsLost();
        }
        
        JTable table = new JTable(topPlayers, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        regresar= new JButton("Regresar");
        regresar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarButtonActionPerformed(evt);
            }
        });
        
        
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        jPanel2.add(scrollPane, BorderLayout.NORTH);
        jPanel2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), BorderFactory.createLoweredBevelBorder()));
        jPanel2.setPreferredSize(new Dimension(300, 300));
        jPanel2.setLayout(new GridLayout(0, 1));
        
        
        
        jPanel1.removeAll();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(jPanel2, BorderLayout.NORTH);
        jPanel1.add(regresar, BorderLayout.CENTER);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("        ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel2)))
                .addContainerGap(234, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(337, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
