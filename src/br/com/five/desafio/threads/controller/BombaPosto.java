package br.com.five.desafio.threads.controller;

import static java.lang.System.out;
import static java.lang.Thread.sleep;

import br.com.five.desafio.threads.exception.SemCombustivelException;
import br.com.five.desafio.threads.model.Veiculos;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BombaPosto {

  private int combustivelBomba;
  private BlockingQueue<Veiculos> fila;

  public BombaPosto() {
    combustivelBomba = 50;
    fila = new ArrayBlockingQueue<>(10);
  }

  public synchronized void ativarBomba(Veiculos veiculo) {
    fila.offer(veiculo);
    try {
      if (combustivelBomba() > 0) {
        Veiculos veiculoAtual = bombaOcupada();
        abasteceVeiculoAtual(veiculoAtual);
        sleep(5000);
        out.println("Veiculo " + veiculoAtual.getId() + " abastecido");
        consomeCombustivel(10);
      } else {
        out.println("Sem combustível no posto");
        this.wait();
      }
    } catch (InterruptedException  e ) {
      throw new SemCombustivelException(); 
  }
  }

  private void abasteceVeiculoAtual(Veiculos veiculoAtual) {
    out.println("Abastecendo veiculo " + veiculoAtual.getId());
    veiculoAtual.abastecer(10);
  }

  private Veiculos bombaOcupada() throws InterruptedException {
    return fila.take();
  }

  public int combustivelBomba() {
    return combustivelBomba;
  }

  private void consomeCombustivel(int combustivel) {
    combustivelBomba = combustivelBomba - combustivel;
  }

  public synchronized void reEstoque(int reestoqueCombustivel) {
    combustivelBomba = combustivelBomba + reestoqueCombustivel;
    notifyAll();
    }
  }