package gurimpan;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyDrawing extends JFrame {
	 
	 JPanel p1,p2;
	 JButton btR, btG, btB, btOpen;
	 Canvas can; // �θ�Ÿ��
	 PaintToolFrame pt;
	 
	 public MyDrawing(){
	  super("::MyDrawing::");
	  pt=new PaintToolFrame();
	  p1=new JPanel(); add(p1, "North");
	  p2=new JPanel(){ // �����ֱ�
	  public Insets getInsets(){
	  return new Insets(40,10,10,10);
	   }
	  }; add(p2, "Center");
	  p2.setBackground(Color.lightGray);
	  
	  btR=new JButton(new ImageIcon("./images/red.png")); p1.add(btR);
	  btG=new JButton(new ImageIcon("./images/green.png")); p1.add(btG);
	  btB=new JButton(new ImageIcon("./images/blue.png")); p1.add(btB);
	  btOpen=new JButton("Paint Tool"); p1.add(btOpen);
	  
	  can=new MyCanvas(); // ��ȭ�� ������ �ϴ� ������Ʈ MyCanvas�� can�� ��� �޴� �ڽ�->���� ���ڱ� ����
	  can.setSize(300, 300); // ��ȭ�� ũ��
	  can.setBackground(Color.white); // ��ȭ�� ���� �ֱ�
	  p2.add(can);
	  
	  //������ ���� -------------------
	  MyHandler my=new MyHandler();
	  can.addMouseMotionListener(my); // ĵ���� ��ü�� ���콺��Ǹ����ʸ� �����Ѵ�.
	  btR.addActionListener(my);
	  btG.addActionListener(my);
	  btB.addActionListener(my);
	  btOpen.addActionListener(my);
	    
	  //pt��ư(PaintToolFrame Ŭ������)���� �����ʸ� ��������
	  pt.btPlus.addActionListener(my);
	  pt.btMinus.addActionListener(my);
	  pt.btClear.addActionListener(my);
	  pt.btAllClear.addActionListener(my);
	  pt.btColor.addActionListener(my);
	  pt.btClose.addActionListener(my);
	    
	  //�̹����� ���ο��� ���ϰ� �����ڿ��� �Ѵ�
	  setSize(500,500);
	  setVisible(true);
	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  
	 }
	 
	 /*�̺�Ʈ�ҽ�: ĵ����
	  * �̺�Ʈ: MouseEvent
	  * �̺�Ʈ �ڵ鷯: MouseMotionListener�� ����
	  * */
	 
	 class MyHandler implements MouseMotionListener, ActionListener{
	  
	  public void mouseDragged(MouseEvent e){
	   setTitle("Drag");
	   //���콺�� �巡���� ������ x��ǥ,y��ǥ�� ���ͼ� can�� x,y ��ǥ���� �����Ѵ�.
	   int xx=e.getX(); int yy=e.getY();
	   setTitle("xx="+xx+", yy"+yy);
	   ((MyCanvas)can).x=xx; ((MyCanvas)can).y=yy;
	   
	   //paint()�� JVM�� ȣ�����ִ� �޼ҵ����� ����x, repaint�� �Ἥ ��������
	   can.repaint();
	   
	  }
	  
	  public void mouseMoved(MouseEvent e){
	  }
	  
	  public void actionPerformed(ActionEvent e){
	   Object o=e.getSource();
	   MyCanvas can2 = (MyCanvas)can;
	   
	   if(o==btR){
	    can2.cr=Color.red;
	   }else if(o==btG){
	    can2.cr=Color.GREEN;
	   }else if(o==btB){
	    can2.cr=Color.blue;
	   }else if(o==btOpen){
	    // ���ο� 'paintToolJframe' �����ؼ� â ����
	    //PaintToolFrame pt = new PaintToolFrame(); ���⼭ �ڵ� ������ �Ź� ������ ���� ��
	    //pt.setSize(400, 400);
	    pt.pack(); // ũ�⸦ �����ؼ� ������
	    pt.setLocation(getWidth(),0); //x�ุŭ ���������� â �̵�
	    pt.setVisible(true);
	   }else if(o==pt.btPlus){
	    can2.w +=10; can2.h+=10;
	   }else if(o==pt.btMinus){
	    if(can2.w>3){ // ��ư�� ��� ������ �ƿ� �ȳ���. �ּ����� ũ�� ����
	     can2.w -= 10; can2.h -= 10; 
	    }
	   }else if(o==pt.btClear)
	    // �巡���� ������ �κ� �����
	    can2.cr=can.getBackground();
	   }else if(o==pt.btAllClear){
	    // ĵ������ ��� �����
	    // Graphics Ŭ������ clearRect(x,y,w,h)
	    Graphics g=can2.getGraphics();
	    g.clearRect(0, 0, can.getWidth(), can.getHeight()); 
	   }else if(o==pt.btColor){
	    // (Swing�� ����) JColorChooser�� ����� ������ �������� �׷�������
	    Color selCr = JColorChooser.showDialog(null, "������", Color.blue); // null=��ũ�� �߾ӿ� ȭ�� ����
	    can2.cr=selCr; 
	   }else if(o==pt.btClose){
	    // pt�� ����������
	    //pt.setVisible(false);-> ���� ������ �ʴ°��ϻ���
	    pt.dispose(); // �ý��� �ڿ��� �ݳ�����
	   }
	  }
	 }
	 
	 
	 public static void main(String[] args) {
	  new MyDrawing(); // ������ �ҷ�����
	 }
	}

	public class MyCanvas extends Canvas {
	 
	 //ó���� ��� �� �������� �ϱ� ���ؼ� x,y -�� ����
	 int x=-50; int y=-50; w=7; h=7;
	 Color cr=Color.black;
	 
	 public void paint(Graphics g){
	  g.setColor(cr);
	  g.fillOval(x, y, w, h); // x, y ������ 70,70 ũ���� �� �׸���
	 }
	 
	 @Override
	 public void update(Graphics g){
	  paint(g);
	 }
	 
	}


	public class PaintToolFrame extends JFrame{

	 JButton btPlus, btMinus, btClear, btAllClear, btClose, btColor;
	 JPanel p;
	 
	 public PaintToolFrame(){
	  super("::PaintToolFrame::");
	  Container c=getContentPane();
	  
	  p=new JPanel();
	  c.add(p, "Center");
	  p.add(btPlus=new JButton("ũ��"));
	  p.add(btMinus=new JButton("�۰�"));
	  p.add(btClear=new JButton("�����"));
	  p.add(btAllClear=new JButton("��������"));
	  p.add(btColor=new JButton("����"));
	  p.add(btClose=new JButton("�ݱ�"));
	  
	  
	 }
	 
	}


