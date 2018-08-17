package gurimpan;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
// jmenuBar --> Ŭ���� ��ü�� ���� �װ���
// ���� �޴��ٸ� add�ؼ� jframe�� ���̱� 
// file-> jmenu --> jmenubar�� ���̱�
// file--[save,load,exit] jmenuitem�� 3 ��ü --> jmunu�� ���̱�

class myFrame extends JFrame implements Serializable {

	private JPanel leftPanel;
	private JPanel rightPanel;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private Color btnColor;

	Font font = new Font("���", Font.BOLD, 50);
	Font font2 = new Font("���", Font.BOLD, 200);

	private JButton buttonCliked;
	Box<Shape> shapeList = new Box<>();

	Vector<Shape> n1 = new Vector<Shape>();

	int startPx ;
	int startPy ;
	int endPx ;
	int endPy ;
	
	class Box<shape> extends Vector implements Serializable{}
	
	
	class Shape implements Serializable{
		int shapeId;
		int getX;
		int getY;

		Shape(int argId, int argX, int argY) {

			shapeId = argId;
			getX = argX;
			getY = argY;

		}
	}

	class Rectangle extends Shape {
		int width = 100;
		int hight = 100;

		Rectangle(int argX, int argY) {
			super(2, argX, argY);
		}
	}

	class Circle extends Shape {
		int width = 100;
		int hight = 100;

		Circle(int argX, int argY) {
			super(3, argX, argY);
		}
	}

	class Line extends Shape {
 
		int argX2;
		int argY2;
		
		Line(int argX, int argY, int argX2, int argY2) {
			super(1, argX, argY);
			this.argX2=argX2;
			this.argY2=argY2;
			
		}
	}

	// ������
	myFrame() {

		JMenuBar jmb = new JMenuBar();
		JMenu jm = new JMenu("file");  //���� �޴�
		JMenu cl = new JMenu("color"); //���� �޴� 
		jmb.add(jm)/* .setFont(font2) */;
		jmb.add(cl);
		JMenuItem jmiSave = new JMenuItem("Save");  //���� �޴��� ���ϴ� ����Ʈ��
		JMenuItem jmiLoad = new JMenuItem("Load");
		JMenuItem jmiExit = new JMenuItem("Exit");

		JMenuItem jmiRed = new JMenuItem("red");  //���� �޴��� ���ϴ� ����Ʈ�� 
		JMenuItem jmiBlue = new JMenuItem("blue");
		JMenuItem jmiGreen = new JMenuItem("green");

		
		jm.add(jmiSave)/* .setFont(font2) */;
		jm.addSeparator(); // ��ư���� ���� ���� ��
		jm.add(jmiLoad)/* .setFont(font2) */;
		jm.addSeparator();
		jm.add(jmiExit)/* .setFont(font2) */;

		cl.add(jmiRed)/* .setFont(font2) */;
		cl.addSeparator(); // ��ư���� ���� ���� ��
		cl.add(jmiBlue)/* .setFont(font2) */;
		cl.addSeparator();
		cl.add(jmiGreen)/* .setFont(font2) */;

				 
		
		// �����ϱ� ��ư ������ ��
		jmiSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String fileName = JOptionPane.showInputDialog("������ ���ϸ��� �Է��Ͻÿ� ");

					// ��ü�� ���Ϸ� ����ȭ �ϱ� . ���� ����� ��ġ �μ��� �޾Ƽ� ��ü ����
					// FileOutputStream : ���ڸ� ����Ʈ ���Ϸ� ��ȯ �ϴ� ����
					FileOutputStream fow = new FileOutputStream(
							"C:\\javaFile\\newJava\\gurimpan\\" + fileName + ".txt");
					// �۷�ȭ �ϱ� ����....
					ObjectOutputStream out = new ObjectOutputStream(fow);

					// ����ȭ ����� �Լ�
					out.writeObject(shapeList);
					out.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// ����� ���� �ε��ϱ�
		jmiLoad.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					String fileName = JOptionPane.showInputDialog("�ҷ��� ���ϸ��� �Է��Ͻÿ� ");

					// FileInputStream : ���Ͽ��� ����Ʈ�� �о�鿩 ���ڷ� ��ȯ
					FileInputStream fiw = new FileInputStream("C:\\javaFile\\newJava\\gurimpan\\" + fileName + ".txt");
					ObjectInputStream in = new ObjectInputStream(fiw);

					shapeList = (Box<Shape>) in.readObject();
					repaint();

					in.close();
					

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//������ ��ư�� ������ ��� 
		jmiRed.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e){
				// TODO Auto-generated method stub
				
				
			}
			
		});
		

		this.getContentPane().setLayout(new GridLayout(0, 2));
		leftPanel = new JPanel();
		rightPanel = new RightPanelC();

		this.add(leftPanel); // ������Ʈ �߰�
		this.add(rightPanel);

		leftPanel.setLayout(new GridLayout(3, 0));

		btn1 = new JButton(" �� �׸��� ");
		btn2 = new JButton(" �簢�� �׸��� ");
		btn3 = new JButton(" �� �׸��� ");

		btnColor = btn1.getBackground();
		btnColor = btn2.getBackground();
		btnColor = btn3.getBackground();

		leftPanel.add(btn1).setFont(font);
		leftPanel.add(btn2).setFont(font);
		leftPanel.add(btn3).setFont(font);

		basic();
		createListner();
		setJMenuBar(jmb);

		//
	}

	// �⺻ Ʋ
	public void basic() {

		this.setSize(1100, 1100);
		this.setTitle("DH's �׸���");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	// ������ ����
	public void createListner() {

		// ��ư 1�� ������ ���
		this.btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() != buttonCliked) {
					btn1.setBackground(Color.pink);
					if (buttonCliked != null) {
						buttonCliked.setBackground(btnColor);
					}

					buttonCliked = (JButton) e.getSource();
				}
			}
		});

		// ��ư 2�� ������ ���
		this.btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() != buttonCliked) {
					btn2.setBackground(Color.pink);
					if (buttonCliked != null) {
						buttonCliked.setBackground(btnColor);
					}
					buttonCliked = (JButton) e.getSource();
				}
			}

		});

		// ��ư 3�� ������ ���
		this.btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() != buttonCliked) {
					btn3.setBackground(Color.pink);
					if (buttonCliked != null) {
						buttonCliked.setBackground(btnColor);
					}

					buttonCliked = (JButton) e.getSource();
				}
			}
		});
	}

	// �̳� Ŭ����
	class RightPanelC extends JPanel {

         
		RightPanelC() {

			this.addMouseListener(new MouseAdapter() {

				// ��������,������(��������ġ)
				public void mousePressed(MouseEvent e) {
					if (buttonCliked == btn1) {
                        startPx=e.getX();
                        startPy=e.getY();
					}
				}

				public void mouseReleased(MouseEvent e) {
					if (buttonCliked == btn1) {

						endPx = e.getX(); // �巡�� �� �κ��� ������
						endPy = e.getY();
						
						Graphics g = rightPanel.getGraphics();
						g.drawLine(startPx,startPy,endPx,endPy);
						Line li = new Line(startPx,startPy,endPx,endPy);
						shapeList.add((Shape) li);
				
					}
				}

				public void mouseClicked(MouseEvent e) {

					if (buttonCliked == btn2) {
						Graphics g = rightPanel.getGraphics();
						g.drawRect(e.getX() - 50, e.getY() - 50, 100, 100);
						Rectangle re = new Rectangle(e.getX(), e.getY());
						shapeList.add((Shape) re);
					}

					if (buttonCliked == btn3) {
						Graphics g = rightPanel.getGraphics();
						g.drawOval(e.getX() - 50, e.getY() - 50, 100, 100);
						Circle ci = new Circle(e.getX(), e.getY());
						shapeList.add((Shape) ci);
					}
				}

			});

		}
	
		// ����� �������� �������� .... ! Vector ~
		public void paintComponent(Graphics g) {
			super.paintComponent(g); // �θ� ����Ʈ ȣ��

			for (int i = 0; i < shapeList.size(); i++) {

				Shape s = (Shape) shapeList.get(i);
				if (s.shapeId == 1) {
					Line r = (Line) s;
					g.drawLine(r.getX, r.getY, r.argX2, r.argY2);
				}
							
				if (s.shapeId == 2) {
					Rectangle r = (Rectangle) s;
					g.drawRect(r.getX, r.getY, r.width, r.hight);
				}

				if (s.shapeId == 3) {
					Circle r = (Circle) s;
					g.drawOval(s.getX, s.getY, r.width, r.hight);
				}
			}

		}
	}

	// ============== ���ȴٰ� �÷��� �� ���� �״��....!=================

	// 1. (rightPanel) �����׸������� ���� �ϱ�.
	// -----> 1) �׸� �׷��� ������ �� ���������� ������ ����... ��ü�� ! Ŭ���� ���Ǹ� �Ұ�
	// (������ : ù �������� �����Ѵ�. �� ������ ���̵� ) ------ �������� �̿��ؼ� ... !
	// ���δٸ� �������� ��ü�� ���� �Ѵ�.

	// �� �ٸ� ���߿��� �������� �����Ѵ�.... ���⼭ ����� �ʿ��ϴ� !
	// -----> 2) �̸� ���ǵ� ��ü�� ������ �迭�� �����Ѵ� .

	// 2. ����� �������� �����ͼ� ���Ӱ� �׷��ֱ�.---> ����Ʈ �ǳڿ��� �׷��ֶ�

}

public class myGurimpan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new myFrame();
	}

}
