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
public class PersonagensFactory extends Personagens{

    @Override
    public Hero criarHero() {
       return new Hero();
    }

    @Override
    public Shot criarShot(int x, int y, GameGridModel model) {
      return new Shot(x, y, model);
    }

    @Override
    public Enemy criarEnemy(int y, int x, GameGridModel model) {
     return new Enemy(y, x, model);
    }
}
