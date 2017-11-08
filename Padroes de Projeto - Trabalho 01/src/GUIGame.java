import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import suporte.PersonagensFactory;

import suporte.Enemy;
import suporte.GameGridModel;
import suporte.GameGridRenderer;
import suporte.Hero;
import suporte.PontosHero;
import suporte.Shot;


@SuppressWarnings("serial")
public class GUIGame extends JFrame {

	public GUIGame() {
		setTitle("Trabalho 1 55PPR");
		setSize(510, 530);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initComponents();
	}
	
	private int lastKey;
	
	private void initComponents() {
                //Sem o Padrão de Projeto: Singleton.
		//final GameGridModel model = new GameGridModel();
                
                //Com o Padrão de Projeto: Singleton.
                final GameGridModel model = GameGridModel.getInstance();
                
                PontosHero pontos = new PontosHero();
                Shot shot = new Shot(0,0, model);


		final JTable espaco = new JTable(model);
		for (int x=0;x<espaco.getColumnModel().getColumnCount();x++) {
			espaco.getColumnModel().getColumn(x).setWidth(50);
			espaco.getColumnModel().getColumn(x).setMinWidth(50);
			espaco.getColumnModel().getColumn(x).setMaxWidth(50);
		}
		espaco.setRowHeight(50);
		espaco.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		espaco.setDefaultRenderer(Object.class, new GameGridRenderer());
		
		getContentPane().add(espaco);
		
		espaco.addKeyListener(new KeyAdapter(){
			
			@Override
			public void keyReleased(KeyEvent e) {
				lastKey = e.getKeyCode();
			}
			
		});
		
		final Random sorteio = new Random();
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
                                        
					int rodada = 0;
					while (true) {
						// lerInputs
						switch (lastKey) {
							case 32: model.heroShot(); break;
						    case 37: model.heroGoLeft(); break;
						    case 39: model.heroGoRight(); break;
						}
						lastKey = 0;

						// mudar o estado dos objetos
						model.atualizar();
						
						if (rodada % 5 == 0) {
							int col = sorteio.nextInt(10);
                                                        //Sem o Padrão de Projeto: Abstract Factory.
							//model.addObjeto(new GameItem(0, col, model), 0, col);
                                                        //Com o Padrão de Projeto: Abstract Factory.
                                                        model.addObjeto(new PersonagensFactory().criarEnemy(0, col, model), 0, col);
							rodada = 0;
						}
						
						// renderizar
						espaco.repaint();
                                                //shot.setPontos(cont);
 						Thread.sleep(500);
					}
				} catch (Exception e) {
					espaco.repaint();
                                        pontos.atualizar(shot);
					JOptionPane.showMessageDialog(null, e.getMessage());
					if (e.getMessage().equals("Fim de Jogo"))                                               
						System.exit(0);
				}
			}
		};
		t.start();
		
	}

	public static void main(String[] args) {

		GUIGame a = new GUIGame();
		a.setVisible(true);
	}

}
