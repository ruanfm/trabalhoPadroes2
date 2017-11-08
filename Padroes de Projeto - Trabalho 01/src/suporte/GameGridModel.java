package suporte;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author wagnnersousa & adilsonv77
 */
@SuppressWarnings("serial")
public class GameGridModel extends AbstractTableModel {
     
       //Inicio do Padrão de Projeto Singleton.
       private static GameGridModel instance;
       
       public synchronized static GameGridModel getInstance() {//Ponto do sistema responsável pelo fornecimento da instancia.
        if (instance == null) {
            instance = new GameGridModel();
        }
        return instance;
    }
    //Fim do padrão de projeto Singleton.
        
	private GameItem[][] objetos = new GameItem[10][10];
	private GameItem[][] objetosNovoEstado = new GameItem[10][10];
	
	private Hero hero;
	private int heroX;

	public GameItem[][] getObjetosNovoEstado() {
		return objetosNovoEstado;
	}
	
	public GameGridModel() {
		this.hero = new Hero();
		this.heroX = 5;
		this.objetos[9][heroX] = hero;
	}
	
	@Override
	public int getColumnCount() {
		return 10;
	}

	@Override
	public int getRowCount() {
		return 10;
	}

	@Override
	public Object getValueAt(int lin, int col) {
		GameItem obj = objetos[lin][col];
		return (obj == null ? null : obj.getImagem());
	}

	public void addObjeto(GameItem obj, int lin, int col) {
		if (objetos[lin][col] == null)
			objetos[lin][col] = obj;

	}

	public void atualizar() throws Exception {

		for (int lin = 0; lin < 10; lin++) {
			for (int col = 0; col < 10; col++) {
				objetosNovoEstado[lin][col] = objetos[lin][col];
			}
		}

		for (int lin = 0; lin < 10; lin++) {
			for (int col = 0; col < 10; col++) {
				if (objetos[lin][col] != null) {
					objetos[lin][col].atualizar();
				}
			}
		}

		for (int lin = 0; lin < 10; lin++) {
			for (int col = 0; col < 10; col++) {
				objetos[lin][col] = objetosNovoEstado[lin][col];
			}
		}
	}

	public void heroGoLeft() {
		if (heroX > 0) {
			moveHero(0);
		}
	}

	public void heroGoRight() {
		if (heroX < 9) {
			moveHero(1);
		}
	}

	private void moveHero(int direcao) {
		objetos[9][heroX] = null;

		switch (direcao) {
			case 0: heroX--; break;
			case 1: heroX++; break;
		}

		objetos[9][heroX] = hero;
	}

	public void heroShot() {
		/* Existe um erro que o tiro come�a uma linha acima... isso � um problema que pode ser resolvido 
		 * usando Padr�es de Projeto ;)
		 */
                
		Shot tiro = new Shot(heroX, 8, this);
		objetos[8][heroX] = tiro;

	}
}
