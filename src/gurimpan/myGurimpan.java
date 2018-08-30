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

// jmenuBar --> 클래스 객체를 만들어서 그것을
// 제이 메뉴바를 add해서 jframe에 붙이기 
// file-> jmenu --> jmenubar에 붙이기
// file--[save,load,exit] jmenuitem의 3 객체 --> jmunu에 붙이기

class myFrame extends JFrame implements Serializable {

	private JPanel leftPanel;
	private JPanel rightPanel;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private Color btnColor;

	Font font = new Font("고딕", Font.BOLD, 50);
	Font font2 = new Font("고딕", Font.BOLD, 200);

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

	// Generator
	myFrame() {

		JMenuBar jmb = new JMenuBar();
		JMenu jm = new JMenu("file");  //파일 메뉴
		JMenu cl = new JMenu("color"); //색깔 메뉴 
		jmb.add(jm)/* .setFont(font2) */;
		jmb.add(cl);
		JMenuItem jmiSave = new JMenuItem("Save");  //파일 메뉴에 속하는 리스트들
		JMenuItem jmiLoad = new JMenuItem("Load");
		JMenuItem jmiExit = new JMenuItem("Exit");

		JMenuItem jmiRed = new JMenuItem("red");  //색깔 메뉴에 속하는 리스트들 
		JMenuItem jmiBlue = new JMenuItem("blue");
		JMenuItem jmiGreen = new JMenuItem("green");

		
		jm.add(jmiSave)/* .setFont(font2) */;
		jm.addSeparator(); // 버튼끼리 구분 짓는 선
		jm.add(jmiLoad)/* .setFont(font2) */;
		jm.addSeparator();
		jm.add(jmiExit)/* .setFont(font2) */;

		cl.add(jmiRed)/* .setFont(font2) */;
		cl.addSeparator(); // 버튼끼리 구분 짓는 선
		cl.add(jmiBlue)/* .setFont(font2) */;
		cl.addSeparator();
		cl.add(jmiGreen)/* .setFont(font2) */;

				 
		
		// When the Save button is pressed
		jmiSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String fileName = JOptionPane.showInputDialog("저장할 파일명을 입력하시오 ");

					// 객체를 파일로 직렬화 하기 . 파일 저장될 위치 인수로 받아서 객체 생성
					// FileOutputStream : 문자를 바이트 파일로 변환 하는 역할
					FileOutputStream fow = new FileOutputStream(
							"C:\\javaFile\\newJava\\gurimpan\\" + fileName + ".txt");
					// 작렬화 하기 위해....
					ObjectOutputStream out = new ObjectOutputStream(fow);

					// 직렬화 만드는 함수
					out.writeObject(shapeList);
					out.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		// Loading Saved Files
		jmiLoad.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					String fileName = JOptionPane.showInputDialog("불러올 파일명을 입력하시오 ");

					// FileInputStream : 파일에서 바이트를 읽어들여 문자로 변환
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
		
		// When the red button is pressed
		jmiRed.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e){
				// TODO Auto-generated method stub
				
				
			}
			
		});
		

		this.getContentPane().setLayout(new GridLayout(0, 2));
		leftPanel = new JPanel();
		rightPanel = new RightPanelC();

		this.add(leftPanel); // 컴포넌트 추가
		this.add(rightPanel);

		leftPanel.setLayout(new GridLayout(3, 0));

		btn1 = new JButton(" 선 그리기 ");
		btn2 = new JButton(" 사각형 그리기 ");
		btn3 = new JButton(" 원 그리기 ");

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

	// a basic framework
	public void basic() {

		this.setSize(1100, 1100);
		this.setTitle("DH's 그림판");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	// Create Leiner
	public void createListner() {

		// 버튼 1번 눌렀을 경우
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

		// 버튼 2번 눌렀을 경우
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

		// 버튼 3번 눌렀을 경우
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

	// Inner class
	class RightPanelC extends JPanel {

         
		RightPanelC() {

			this.addMouseListener(new MouseAdapter() {

				// 프레스드,릴리즈(마지막위치)
				public void mousePressed(MouseEvent e) {
					if (buttonCliked == btn1) {
                        startPx=e.getX();
                        startPy=e.getY();
					}
				}

				public void mouseReleased(MouseEvent e) {
					if (buttonCliked == btn1) {

						endPx = e.getX(); // 드래그 한 부분을 종료점
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
	
		// Import stored information .... ! Vector ~
		public void paintComponent(Graphics g) {
			super.paintComponent(g); // 부모 페인트 호출

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

	// ============== 내렸다가 올려도 그 상태 그대로....!=================

	// 1. (rightPanel) 도형그릴때마다 저장 하기.
	// -----> 1) 그림 그려질 때마다 그 도형에대한 정보를 저장... 객체로 ! 클래스 정의를 할것
	// (공통점 : 첫 시작점이 존재한다. 이 도형의 아이디 ) ------ 다형성을 이용해서 ... !
	// 서로다른 도형들을 객체에 정의 한다.

	// 세 다른 것중에서 공통점이 존재한다.... 여기서 상속이 필요하다 !
	// -----> 2) 미리 정의된 객체를 가지고 배열에 저장한다 .

	// 2. 저장된 정보들을 가져와서 새롭게 그려주기.---> 라이트 판넬에서 그려주라

}

public class myGurimpan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new myFrame();
	}

}
