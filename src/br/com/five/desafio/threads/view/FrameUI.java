package br.com.five.desafio.threads.view;

import static java.awt.EventQueue.invokeLater;
import static javax.swing.BorderFactory.createBevelBorder;
import static javax.swing.border.BevelBorder.RAISED;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameUI extends JFrame {

  private JPanel jPanel2;

  public FrameUI() throws InterruptedException {
    initComponents();
  }

  private void initComponents() throws InterruptedException {
    jPanel2 = new Panel2();
    jPanel2.setBackground(new Color(255, 255, 255));
    jPanel2.setBorder(createBevelBorder(RAISED));
    this.setContentPane(jPanel2);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
  }

  public static void main(String[] args) {
    invokeLater(
      () -> {
        try {
          new FrameUI().setVisible(true);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    );
  }
}
