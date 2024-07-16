package mgui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Manage extends JFrame implements ActionListener {
	JPanel[] m = new JPanel[4];
	JLabel title = new JLabel("관리자");
	JLabel pw = new JLabel("관리자");
	JTextField pwt = new JTextField(10);
	JButton login = new JButton("로그인");// 이버튼 누르면 market 으로 넘어가게
	JButton menu = new JButton("초기화면");// 로그인 객체
	MarketGui k = new MarketGui();

	public Manage() {
		this.setBounds(100, 100, 300, 200);
		this.setLayout(new GridLayout(4, 1));
		for (int i = 0; i < m.length; i++) {
			m[i] = new JPanel();
			this.add(m[i]);
		}
		m[0].add(title);
		m[1].add(pw);
		m[1].add(pwt);
		m[2].add(login);
		m[3].add(menu);
		login.addActionListener(this);
		menu.addActionListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String getpw = pwt.getText();
		if (e.getSource().equals(login)) {
			if (getpw.equals("1111")) {
				this.setVisible(false);
				k.marketbox();
			}
		} else if (e.getSource().equals(menu)) {
			new LoginGui();
		}

	}
}