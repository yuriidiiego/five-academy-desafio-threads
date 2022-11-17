package br.com.five.desafio.threads.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import java.awt.Font;

import br.com.five.desafio.threads.controller.BombaPosto;

public class Veiculos {

  private int x;
  private int y;
  private Color color;
  private int combustivel;
  private int id;
  public Font font = new Font("Impact", Font.BOLD, 15);

  public Veiculos(int x, int y, int combustivel, Color color) {
    this.x = x;
    this.y = y;
    this.combustivel = combustivel;
    this.color = color;
  }

  public void abastecer(int combustivel) {
    this.combustivel = combustivel;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int combustivel() {
    return combustivel;
  }

  public Color getColor() {
    return color;
  }

  public void mover() {
    if (combustivel >= 0) {
      Random rand = new Random();
      int sortx = rand.nextInt(30) - 10;
      x += sortx;
      y += rand.nextInt(30) - 10;
      if (x > 800 || y > 600) {
        x = 0;
        y = 0;
      }
      combustivel -= sortx;
    }
  }

  public void draw(Graphics g) throws IOException {    
    g.setFont(font);
    g.setColor(Color.BLUE);
    g.drawString("Carro : " + getId(), x, y + 0);
    BufferedImage img = ImageIO.read(
      new File("src/br/com/five/desafio/threads/view/img/veiculo.png"));   
      g.drawImage(img, x, y, null);


         
      if(combustivel < 0) {
        g.setColor(Color.RED);
        g.drawString("Sem Combustível: " + combustivel, x, y + 20);
        g.drawString("X" , x, y +45 );
        
      }else {
        
        g.setColor(Color.GREEN.darker());
        g.drawString("Combustível: " + combustivel, x, y + 20); 
      }
      
    }
    

  public int getId() {
    return this.id;
  }
}
