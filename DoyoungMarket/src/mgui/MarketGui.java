package mgui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.PlayerDAO;
import dto.PlayerDTO;

public class MarketGui extends JFrame implements ActionListener {
	Storage s = new Storage(this);
	Delete d = new Delete(this);
	Productselgrid g = new Productselgrid(this);
	JPanel[] m = new JPanel[5];
	JLabel title = new JLabel("Market 오신것을 환영합니다");
	JButton b1 = new JButton("상품등록");// ok 연동
	JButton b2 = new JButton("상품입고");
	JButton b3 = new JButton("상품삭제");
	JButton b4 = new JButton("상품전체보기");
	JButton b6 = new JButton("초기화면");


	public MarketGui() {
		this.setBounds(100, 100, 500, 400);
		this.setLayout(new GridLayout(5, 4));
		for (int i = 0; i < m.length; i++) {
			m[i] = new JPanel();
			this.add(m[i]);
		}
		m[0].add(title);
		m[1].add(b1);
		m[1].add(b2);
		m[1].add(b3);
		m[1].add(b4);
		m[3].add(b6);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);

		b6.addActionListener(this);
//		this.setResizable(false);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}

	public void marketbox() {
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) {
			this.setVisible(false);
			g.setVisible(true);
		}
		if (e.getSource().equals(b2)) {
			this.setVisible(false);
			s.setVisible(true);
		}
		if (e.getSource().equals(b3)) {
			this.setVisible(false);
			d.setVisible(true);
		}
		if (e.getSource().equals(b4)) {
			new AllList();
		}
		if (e.getSource().equals(b6)) {
			this.setVisible(false);
			new LoginGui();
		}
	}

}
