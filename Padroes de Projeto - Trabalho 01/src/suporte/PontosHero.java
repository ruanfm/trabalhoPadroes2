/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suporte;

import javax.swing.JOptionPane;

/**
 *
 * @author willt
 */
public class PontosHero implements ObservadorShot {
    int pontosAtual = 0;
    @Override
    public void atualizar(Shot shot) {
        pontosAtual = shot.getPontos();
        pontosAtual++;
        JOptionPane.showMessageDialog(null, "Pontuação: "+pontosAtual);
    }
}
