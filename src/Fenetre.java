
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;


import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Fenetre extends JFrame  implements ActionListener  {

	private static final long serialVersionUID = 6194625107521090216L;
	private JPanel monConteneur;
	private JLabel pret;
	private JLabel label_titre;
	private JLabel regle;
	private JLabel regle2;
	private JLabel regle3;
	private JButton btnValider;
	private JButton btnRemiseA0;
	private JButton acces;
	private Color c;
	
	
	
	public Fenetre(){
		super("Juste Prix");
		this.setSize(400,400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.game();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	
		
        
	}


	public void game(){
		monConteneur = new JPanel();
		monConteneur.setLayout(new GridLayout(0, 1));
		
		this.label_titre = new JLabel("Voici le jeu du Juste Prix !!");
        this.label_titre.setHorizontalAlignment(JLabel.CENTER);
		this.label_titre.setFont(new Font(label_titre.getFont().getName(), label_titre.getFont().getStyle(), 20));
		monConteneur.add(label_titre, BorderLayout.PAGE_START);
		
		this.regle = new JLabel("Les règles sont simples : ");
		this.regle2 = new JLabel("Tu dois trouver le juste prix compris entre 0 et 100");
		this.regle3 = new JLabel("et ce en max 10 essais ! ");
		this.regle.setHorizontalAlignment(JLabel.CENTER);
		this.regle2.setHorizontalAlignment(JLabel.CENTER);
		this.regle3.setHorizontalAlignment(JLabel.CENTER);
		this.regle.setFont(new Font(regle.getFont().getName(), regle.getFont().getStyle(), 15));
		this.regle2.setFont(new Font(regle2.getFont().getName(), regle2.getFont().getStyle(), 15));
		this.regle3.setFont(new Font(regle3.getFont().getName(), regle3.getFont().getStyle(), 15));
		monConteneur.add(regle);
		monConteneur.add(regle2);
		monConteneur.add(regle3);
		
		this.pret = new JLabel("Est ce que tu es readyyyy ??? ");
		this.pret.setFont(new Font(pret.getFont().getName(), pret.getFont().getStyle(), 15));
		this.pret.setHorizontalAlignment(JLabel.CENTER);
		monConteneur.add(pret);
		
		this.btnValider = new JButton("Prêt !");
		this.btnValider.addActionListener(this);
		monConteneur.add(btnValider);
		
		this.acces = new JButton("B&W");
		this.acces.addActionListener(this);
		monConteneur.add(acces, BorderLayout.LINE_END);
		
		this.btnRemiseA0 = new JButton("R.A.Z");
		this.btnRemiseA0.addActionListener(this);
		monConteneur.add(btnRemiseA0, BorderLayout.LINE_END);
		
		this.getContentPane().add(monConteneur);
        monConteneur.setBackground(Color.pink);
        monConteneur.setForeground(Color.white);
        
    
  }
	//fonction 1 setButtonsColor(Color c)
		public void setButtonsColor(Color newC) {
			this.c = newC; 
			JButton[] tab = {this.btnValider, this.acces, this.btnRemiseA0};
			for (int i=0; i<tab.length; i++) {
				tab[i].setBackground(this.c);	
			}
		}
	//fonction 2 setMonConteneurFontColor(Color  c)
		public void setMonConteneurColor(Color c) {
			this.monConteneur.setBackground(c);
		}
	//fonction 3 setMonConteneurFontColor(Color  c)
		public void setMonConteneurPoliceColor(Color newC) {
			this.c= newC; 
			JLabel[] tab = {this.label_titre, this.pret, this.regle, this.regle2, this.regle3};
			for (int i=0; i<tab.length; i++) {
				tab[i].setForeground(this.c);	
			}
			JButton[] tab2 = {this.btnValider, this.acces, this.btnRemiseA0};
			for (int i=0; i<tab2.length; i++) {
				tab2[i].setForeground(this.c);	
			}
		}
		
	//fonction 4 setFrameTitle(String title)
		public void setFrameTitle(String title) {
			this.setTitle(title);	
		}
	//fonction pour transformer les virgules en tirets et pouvoir ensuite les utiliser dans les fichiers init
		public Color lineToColor(String line) throws NumberFormatException {
			Color c = Color.pink; 
			String[] rgb = line.split("-");
			if (rgb.length == 3) {
			int red = Integer.parseInt(rgb[0]);
			int green = Integer.parseInt(rgb[1]);
			int blue = Integer.parseInt(rgb[2]);
			if (red >= 0 && red < 256 && green >=0 && green < 256 && blue >=0 && blue < 256) {
			 c = new Color(red, green, blue);
			}
			 }
			 return c;
			}
	//fonction pour ajouter les fichiesr init et init2	
		public void AjouterFichier(String file,String... tab) throws IOException {
			FileWriter fichier = new FileWriter(file+".txt");
			BufferedWriter bw = new BufferedWriter(fichier);
			PrintWriter pw = new PrintWriter(bw);
			for(int i=0; i<tab.length;i++) {
				pw.println(tab[i]);
			}
			pw.close();
			
		}
		public void createInitFile(String file) {
			try{
				
				String ligne1 = "0-0-0"; 
				String ligne2 = "0-0-0";
				String ligne3 = "255-255-255";
				String ligne4 = "Juste Prix Black & White"; 
				AjouterFichier(file, ligne1, ligne2, ligne3, ligne4); 
				
				}
				catch (IOException e){
				e.printStackTrace();
				}
		}
	//fonction 5 loadInitFile(String file)
		
		public void loadInitFile(String file) {
			try {
				FileReader fr = new FileReader(file);
				//creation d'un tableau qui va contenir les couleurs changées 
				Color [] tabColor = new Color[3]; 
				BufferedReader br = new BufferedReader(fr);
				String ligne ;
				//boucle while pour prendre les données du fichier, les transformer en couleur
				int cpt = 0; 
				while ((ligne = br.readLine()) != null) {
					if(cpt < 3) {
						tabColor[cpt] = lineToColor(ligne); 
					}else {
						this.setFrameTitle(ligne);
						 
					}
					cpt++; 	
				}
				br.close();
				//Pour chaque case du tableau on affecte un code couleur par fonction 
				this.setButtonsColor(tabColor[0]);
				this.monConteneur.setBackground(tabColor[1]);
				this.setMonConteneurPoliceColor(tabColor[2]);
				} catch (IOException e) {
				e.printStackTrace();
				}
		}
	//fonction 6 ecriture
		private void ecriture (String l1, String l2, String l3,String l4, String file) {
			try{
				FileWriter fichier = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fichier);
				PrintWriter pw = new PrintWriter(bw);
				pw.println(l1); 
				pw.println(l2);
				pw.println(l3); 
				pw.println(l4);
				pw.close();
				
				}
				catch (IOException e){
				e.printStackTrace();
				}
		}
	@Override
	
	public void actionPerformed(ActionEvent arg0) { 
		if (arg0.getSource() == this.acces){
			this.createInitFile("init");
			this.loadInitFile("init.txt");
		}
		if (arg0.getSource() == this.btnRemiseA0){
			this.ecriture("255-255-255","255-192-203","0-0-0","Juste Prix","init2.txt");
			this.loadInitFile("init2.txt"); 
		}
		
		if (arg0.getSource() == this.btnValider){
			 final int essai = 10;
		        JOptionPane.showMessageDialog(null, "Tu pars avec 2000 eu mais plus tu tardes et moins tu gagnes alors fais vite ! ");	
		        //fonction random pour le nombre aléatoire 
		        final Random r = new Random();
		        final int numAlea = r.nextInt(100) + 1;
		        //c est le compteur du nombre d'essai
		        int c = 0;
		        //on part avec 2000 euros et plus le nombre d'essai monte plus la somme baisse donc faut vite deviner hihi
		        final int prix = 2100;
		        String nbJoueur;
		        int nbJoueurInt = -1;

		        while (nbJoueurInt != numAlea && c < essai) {
		        	nbJoueur = JOptionPane.showInputDialog("Entre un nombre entre 1 et 100");
		        	
		        	//je convertis le String en Int pour avoir le même type dans les boucles
		            nbJoueurInt = Integer.parseInt(nbJoueur);
		            c++;

		            if (nbJoueurInt == numAlea) {
		            	 JOptionPane.showMessageDialog(null,
		 		                "Bravissimoooo !" + "\nLe Juste Prix était  " + numAlea + "\nTu l'as trouvé en " + c + " essais");
		 		        JOptionPane.showMessageDialog(null, "Tu as gagné la somme de : " + (prix - c * 100) + " euros ;)");
		 		        return;
		               
		            } 
		            else if (nbJoueurInt > numAlea) {
		                JOptionPane.showMessageDialog(null,  "essai " + c + " , le nombre  est trop grand, il doit être plus petit");
		            
		            } 
		            else if (nbJoueurInt < numAlea) {
		                JOptionPane.showMessageDialog(null, "essai "+ c + " , le nombre est trop petit, il doit être plus grand");
		            }
		        }
                JOptionPane.showMessageDialog(null, "GAME OVEEERR (Boouuh !)","oups", JOptionPane.ERROR_MESSAGE);

		    }
			
		
			}
	
			}  
			
			

		
		
		

		

	
	

