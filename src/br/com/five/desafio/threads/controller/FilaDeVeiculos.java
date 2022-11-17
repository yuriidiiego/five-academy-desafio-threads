package br.com.five.desafio.threads.controller;

import static java.lang.Thread.sleep;

import br.com.five.desafio.threads.model.Veiculos;

public class FilaDeVeiculos implements Runnable {

  private Veiculos veiculo;
  private BombaPosto bombaPosto;

  public FilaDeVeiculos(Veiculos veiculo, BombaPosto bombaPosto) {
    this.veiculo = veiculo;
    this.bombaPosto = bombaPosto;
  }

  @Override
  public void run() {
    while (true) {
      try {
        if (verificaCombustivel()) {
          bombaPosto.ativarBomba(veiculo);
        }
        veiculo.mover();
        sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private boolean verificaCombustivel() {
    return veiculo.combustivel() <= 0;
  }
}
