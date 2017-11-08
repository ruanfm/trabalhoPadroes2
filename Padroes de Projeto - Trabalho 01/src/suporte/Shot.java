package suporte;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

/**
*
* @author wagnnersousa & adilsonv77
*/

public class Shot extends GameItem {

	private int turno = 1;
	private int x;
	private int y;
	private GameGridModel model;
        
        //Com o Padrão de Projeto: Observer.
    public List<ObservadorShot> observadores = new ArrayList<>();
    
    private int pontos;

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
    
    public void anexar(ObservadorShot obs) {
        observadores.add(obs);
    }

    public void desanexar(ObservadorShot obs) {
        observadores.remove(obs);
    }

    public void notificar() {
        for (ObservadorShot obs : observadores) {
            obs.atualizar(this);
        }
    }
    //Fim do Padrão de Projeto: Observer.

	public Shot(int x, int y, GameGridModel model) {
		this.x = x;
		this.y = y;
		this.model = model;
	}

	@Override
	public ImageIcon getImagem() {

		return new ImageIcon("suporte.Tiro" + turno + ".png");
	}

	public void atualizar() {
                notificar();
		model.getObjetosNovoEstado()[this.y][this.x] = null;
		
		y--;
		if (y >= 0) {
			
			model.getObjetosNovoEstado()[this.y][this.x] = this;
			turno = (turno % 4) + 1;
		}
	}
}
