package java_ilkl;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TextEditor extends JFrame implements ActionListener,MouseListener{
	ImageIcon renk_ikonu;
	JTextArea textArea;
	JScrollPane scrollpane;
	JSpinner fontspinner;
	JButton fontrenk;
	JColorChooser chooser;
	JComboBox fontbox;
	
	JLabel label;
	JLabel label2;
	
	
	
	JMenuBar menubar;
	JMenu open;
	JMenu save;
	JMenu exit;
	
	
	ImageIcon gunduzmod_ikonu;
	ImageIcon gecemod_ikonu;
	ImageIcon open_ikon;
	ImageIcon save_ikon;
	ImageIcon exit_ikon;
	
	TextEditor(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Basit Metin Editörü");
		this.setSize(new Dimension(500,500));
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		
		//----------- Gece ve Gündüz Modu Ayarlamaları-------------//
		
		gecemod_ikonu=new ImageIcon("gecemodu.png");
		Image gecemod_ikonu_resize_ikonu=gecemod_ikonu.getImage().getScaledInstance(144, 64,Image.SCALE_SMOOTH);
		gecemod_ikonu=new ImageIcon(gecemod_ikonu_resize_ikonu);
		label2=new JLabel();
		label2.setIcon(gecemod_ikonu);
		label2.addMouseListener(this);
		label2.setVisible(false);
		
		
		gunduzmod_ikonu=new ImageIcon("gündüz.png");
		Image gunduzmod_resize_ikonu=gunduzmod_ikonu.getImage().getScaledInstance(144, 64,Image.SCALE_SMOOTH);
		gunduzmod_ikonu=new ImageIcon(gunduzmod_resize_ikonu);
		label=new JLabel();
		label.setIcon(gunduzmod_ikonu);
		label.addMouseListener(this);
		
		//------------------------//
		
		//------	Menü İşlemleri İçin	------//
		
		open_ikon=new ImageIcon("open.png");
		Image open_resize=open_ikon.getImage().getScaledInstance(60, 60, DO_NOTHING_ON_CLOSE);
		open_ikon=new ImageIcon(open_resize);
		
		
		save_ikon=new ImageIcon("save.png");
		Image save_resize=save_ikon.getImage().getScaledInstance(60, 60, DO_NOTHING_ON_CLOSE);
		save_ikon=new ImageIcon(save_resize);
		
		
		
		exit_ikon=new ImageIcon("exit.png");
		Image exit_resize=exit_ikon.getImage().getScaledInstance(60, 60, DO_NOTHING_ON_CLOSE);
		exit_ikon=new ImageIcon(exit_resize);
		
		
		
		menubar=new JMenuBar();
		
		open=new JMenu();
		open.setIcon(open_ikon);
		
		save=new JMenu();
		save.setIcon(save_ikon);
		
		exit=new JMenu();
		exit.setIcon(exit_ikon);
		
		menubar.add(open);
		menubar.add(save);
		menubar.add(exit);
		
		
		open.addMouseListener(this);
		save.addMouseListener(this);
		exit.addMouseListener(this);
		
		
		//------	Menu İşlemleri	------//
		
		
		
		
		
		renk_ikonu=new ImageIcon("ozel buton.png");
		
		
		// Text Field Özellikleri
		textArea=new JTextArea();
		
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Arial",Font.PLAIN,25));
		textArea.setText("Bu uygulamada yalnızca .txt dosyaları ile işlem yapabilirsiniz. "
				+ "Kaydettiğiniz dosyaları uygulama üzerinden tekrar açtığınızda aynı haliyle yüklenecektir."
				+"\n\n\ngithub.com/mahmut-akarsu");
		
		
		
		
		// Scrollpane(metni yazdıkça çubuğun kayması için) Ayarlar
		scrollpane=new JScrollPane(textArea);
		scrollpane.setPreferredSize(new Dimension(1350,550));
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		JPanel panel=new JPanel();
		panel.add(scrollpane);
		
		
		// Button that font's color 
		fontrenk=new JButton();
		fontrenk.setPreferredSize(new Dimension(102,25));
		fontrenk.setFocusable(false);
		fontrenk.setIcon(renk_ikonu);
		fontrenk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==fontrenk) {
					chooser=new JColorChooser();
					Color color=chooser.showDialog(null, "Renk ayari",Color.black);
					textArea.setForeground(color);
					
				}
				
				if(e.getSource()==fontbox) {
					textArea.setFont(new Font((String) fontbox.getSelectedItem(),Font.PLAIN,textArea.getFont().getSize()));
				}
				
			} 
			
		});
		
		
		fontspinner=new JSpinner();
		fontspinner.setPreferredSize(new Dimension(50,25));
		fontspinner.setValue(30);
		fontspinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				textArea.setFont(new Font(textArea.getFont().getFamily(),Font.PLAIN,(int) fontspinner.getValue()));
			}
			
		});
		
		
		String[] fontlar=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		fontbox=new JComboBox(fontlar);
		fontbox.addActionListener(this);
		fontbox.setSelectedItem("Arial");		
		fontbox.setPreferredSize(new Dimension(160,24));
		
		
		this.add(label);
		this.add(label2);
		this.setJMenuBar(menubar);
		this.add(fontbox);
		
		this.add(fontrenk);
		
		this.add(fontspinner);
		
		this.add(panel);
		
		
		this.setVisible(true);
		
		
	}
	
	
	//Fontboxtan seçilen fontu textArea'ya işletme.
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==fontbox) {
			textArea.setFont(new Font((String) fontbox.getSelectedItem(),Font.PLAIN,textArea.getFont().getSize()));
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
			if(e.getSource()==label) {
				textArea.setBackground(Color.black);
				textArea.setForeground(Color.gray);
				label.setVisible(false);
				label2.setVisible(true);			
				}
			if(e.getSource()==label2) {
				textArea.setBackground(Color.white);
				textArea.setForeground(Color.black);
				label2.setVisible(false);
				label.setVisible(true);
			}
			
			if(e.getSource()==open) {
				JFileChooser filechooser=new JFileChooser();	
				//FileNameExtensionFilter filter=new FileNameExtensionFilter(".txt"," ");
				//filechooser.setFileFilter(filter);
				int response1=filechooser.showOpenDialog(null);
				if(response1==JFileChooser.APPROVE_OPTION) {
					File file=new File(filechooser.getSelectedFile().getAbsolutePath());
					Scanner fileIn=null;
					try {
						fileIn=new Scanner(file);
						if(file.isFile()) {
							while(fileIn.hasNextLine()) {
								String line=fileIn.nextLine()+"\n";
								textArea.append(line);
							}
						}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					finally {
						fileIn.close();
					}
				}
			}
			
			
			
			if(e.getSource()==save) {
				JFileChooser filechooser=new JFileChooser();
				int response=filechooser.showSaveDialog(null);
				if(response==JFileChooser.APPROVE_OPTION) {
					File file;
					PrintWriter fileOut = null;
					file=new File(filechooser.getSelectedFile().getAbsolutePath());
					try {
						fileOut=new PrintWriter(file);
						fileOut.println(textArea.getText());
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					finally {
						JOptionPane.showMessageDialog(filechooser, "Dosya Başarıyla Kaydedildi");
						
					}
					
				}
				
			
			}
			
			if(e.getSource()==exit) {
				System.exit(0);
			}
			
			
			
		}
		
	


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
