package mgui;

import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.PlayerDAO;
import ugui.PurchaseGui;

public class LoginGui extends JFrame implements ActionListener {
	JPanel[] m = new JPanel[5];
	JLabel title = new JLabel("로그인");
	JLabel wel = new JLabel("환영합니다");
	JLabel id = new JLabel("ID");
	JLabel pw = new JLabel("PW");
	JTextField idt = new JTextField(10);
	TextField pwt = new TextField(10);
	JButton login = new JButton("로그인");
	JButton join = new JButton("회원가입");
	JButton manage = new JButton("관리자");
	private PlayerDAO ua = new PlayerDAO();
//	private PlayerDTO ut = new PlayerDTO();
//	MarketGui k = new MarketGui();

	public LoginGui() {

		this.setBounds(100, 100, 300, 200);
		this.setLayout(new GridLayout(5, 1));
		for (int i = 0; i < m.length; i++) {
			m[i] = new JPanel();
			this.add(m[i]);
		}
		m[0].add(title);
		m[1].add(id);
		m[1].add(idt);
		m[2].add(pw);
		m[2].add(pwt);
		m[3].add(wel);
		m[4].add(manage);
		m[4].add(login);
		m[4].add(join);
		// 컴포넌트 중에 save 컴포넌트는 인터페이스의 기능을 사용할 컴포넌트 이다
		login.addActionListener(this);
		join.addActionListener(this);
		manage.addActionListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		pwt.setEchoChar('*');
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String getid = idt.getText();
		String getpw = pwt.getText();
		if (e.getSource().equals(join)) {// 회원가입 버튼
			this.setVisible(false);
			new JoinGui();

		} else if (e.getSource().equals(login)) {// 로그인버튼
			wel.setText(getid + "/" + getpw + "님 로그인");
			if (ua.chkUser(getid, getpw) != null) {
				this.setVisible(false);
				new PurchaseGui(getid);

			} else {
				System.out.println("실패");
			}

		} else if (e.getSource().equals(manage)) {// 관리자 버튼
			this.setVisible(false);
			new Manage();
		}

	}
}
