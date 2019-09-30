package mineswepper;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class MouseHandler implements MouseListener {

    @Override
    public void mousePressed(MouseEvent e) {
      JButton boton = (JButton) e.getSource();
      String nombre=boton.getActionCommand();
      if (SwingUtilities.isLeftMouseButton(e)){
          System.out.println("Click derecho");
      } else {
          System.out.println("Click izquierdo");
      }
      //System.out.println(nombre);
    }
    @Override
    public void mouseReleased(MouseEvent e) {    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}
}

   