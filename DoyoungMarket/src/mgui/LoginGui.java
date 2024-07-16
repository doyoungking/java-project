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
	JLabel title = new JLabel("�α���");
	JLabel wel = new JLabel("ȯ���մϴ�");
	JLabel id = new JLabel("ID");
	JLabel pw = new JLabel("PW");
	JTextField idt = new JTextField(10);
	TextField pwt = new TextField(10);
	JButton login = new JButton("�α���");
	JButton join = new JButton("ȸ������");
	JButton manage = new JButton("������");
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
		// ������Ʈ �߿� save ������Ʈ�� �������̽��� ����� ����� ������Ʈ �̴�
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
		if (e.getSource().equals(join)) {// ȸ������ ��ư
			this.setVisible(false);
			new JoinGui();

		} else if (e.getSource().equals(login)) {// �α��ι�ư
			wel.setText(getid + "/" + getpw + "�� �α���");
			if (ua.chkUser(getid, getpw) != null) {
				this.setVisible(false);
				new PurchaseGui(getid);

			} else {
				System.out.println("����");
			}

		} else if (e.getSource().equals(manage)) {// ������ ��ư
			this.setVisible(false);
			new Manage();
		}

	}
}
