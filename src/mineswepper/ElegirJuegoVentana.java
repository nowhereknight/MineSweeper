package mineswepper;

public class ElegirJuegoVentana extends javax.swing.JFrame {

    public final int width = 400;
    public final int height = 400;
    public Jugador jugador;
    
    public ElegirJuegoVentana(Jugador jugador) {
        initComponents();
        setResizable(false);
        setSize(width, height);
        this.jugador=jugador;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        OchoButton = new javax.swing.JButton();
        DiesiseisButton = new javax.swing.JButton();
        TwentyFiveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Elija el nivel:");

        OchoButton.setText("8x8");
        OchoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OchoButtonActionPerformed(evt);
            }
        });

        DiesiseisButton.setText("16x16");
        DiesiseisButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiesiseisButtonActionPerformed(evt);
            }
        });

        TwentyFiveButton.setText("25x25");
        TwentyFiveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TwentyFiveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(166, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(DiesiseisButton)
                        .addComponent(OchoButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1))
                    .addComponent(TwentyFiveButton))
                .addGap(160, 160, 160))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addGap(54, 54, 54)
                .addComponent(OchoButton)
                .addGap(18, 18, 18)
                .addComponent(DiesiseisButton)
                .addGap(29, 29, 29)
                .addComponent(TwentyFiveButton)
                .addContainerGap(166, Short.MAX_VALUE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OchoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OchoButtonActionPerformed
        dispose();
        new Tablero(jugador).setVisible(true);
    }//GEN-LAST:event_OchoButtonActionPerformed

    private void DiesiseisButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiesiseisButtonActionPerformed
        dispose();
        new Tablero(16, jugador).setVisible(true);
    }//GEN-LAST:event_DiesiseisButtonActionPerformed

    private void TwentyFiveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TwentyFiveButtonActionPerformed
        dispose();
        new Tablero(25,jugador);
    }//GEN-LAST:event_TwentyFiveButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DiesiseisButton;
    private javax.swing.JButton OchoButton;
    private javax.swing.JButton TwentyFiveButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
