package br.com.five.desafio.threads.view;

import br.com.five.desafio.threads.controller.BombaPosto;
import br.com.five.desafio.threads.controller.FilaDeVeiculos;
import br.com.five.desafio.threads.model.Fabrica;
import br.com.five.desafio.threads.model.Veiculos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JPanel;

public class Panel2 extends JPanel {

  private BlockingQueue<Veiculos> veiculos;
  private ExecutorService threadPool;

  Panel2() throws InterruptedException {
    setPreferredSize(new Dimension(850, 650));
    this.threadPool = Executors.newCachedThreadPool();
    criarVeiculos();
    criarFila();
  }

  private Color defineColor() {
    Random rand = new Random();
    float r = rand.nextFloat();
    float g = rand.nextFloat();
    float b = rand.nextFloat();
    return new Color(r, g, b);
  }

  private void criarVeiculos() throws InterruptedException {
    Random rand = new Random();
    veiculos = new ArrayBlockingQueue<Veiculos>(10);
    for (int i = 0; i < 10; i++) {
      Veiculos v = new Veiculos(
        rand.nextInt(800),
        rand.nextInt(600),
        rand.nextInt(50),
        defineColor()
      );
      v.mover();
      v.setId(i);
      veiculos.put(v);
    }
  }

  private void criarFila() throws InterruptedException {
    this.threadPool.execute(
        new Runnable() {
          public void run() {
            while (true) {
              Panel2.this.repaint();
              try {
                Thread.sleep(1000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            }
          }
        }
      );
    BombaPosto bombaPosto = new BombaPosto();
    this.threadPool.execute(new Fabrica(bombaPosto));
    this.veiculos.forEach(
        v -> this.threadPool.execute(new FilaDeVeiculos(v, bombaPosto))
      );
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (Veiculos v : veiculos) {
      try {
        v.draw(g);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
