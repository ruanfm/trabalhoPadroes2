/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suporte;

/**
 *
 * @author willt
 */
public abstract class Personagens{
    public abstract Hero criarHero();
    public abstract Shot criarShot(int x, int y, GameGridModel model);
    public abstract Enemy criarEnemy(int y, int x,GameGridModel model);
}
