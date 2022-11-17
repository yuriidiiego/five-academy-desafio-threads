package br.com.five.desafio.threads.model;

import static java.lang.Thread.sleep;

import br.com.five.desafio.threads.controller.BombaPosto;

public class Fabrica implements Runnable {

  private BombaPosto bombaPosto;

  public Fabrica(BombaPosto bombaPosto) {
    this.bombaPosto = bombaPosto;
  }

  @Override
  public void run() {
    try {
      sleep(30000);
      bombaPosto.reEstoque(50);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
