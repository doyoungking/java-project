package mgui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.PlayerDAO;
import dto.PlayerDTO;

public class JoinGui extends JFrame implements ActionListener {
	JButton jb = new JButton("ȸ������");
	private PlayerDTO ut = new PlayerDTO();
	private PlayerDAO ua = new PlayerDAO();
	LoginGui lo = new LoginGui();
	JPanel[] m = new JPanel[6];
	JLabel title = new JLabel("ȸ������");
	JLabel id = new JLabel("ID");
	JLabel pw = new JLabel("pw");
	JLabel userName = new JLabel("�̸�");
	JLabel phoneNumber = new JLabel("��ȭ��ȣ");
	JTextField idt = new JTextField(10);
	JTextField pwt = new JTextField(10);
	JTextField unt = new JTextField(10);
	JTextField pnt = new JTextField(10);

	

	public JoinGui() {
		this.setBounds(100, 100, 300, 300);
		this.setLayout(new GridLayout(6, 1));
		for (int i = 0; i < m.length; i++) {
			m[i] = new JPanel();
			this.add(m[i]);
		}
		m[0].add(title);
		m[1].add(id);
		m[1].add(idt);
		m[2].add(pw);
		m[2].add(pwt);
		m[3].add(userName);
		m[3].add(unt);
		m[4].add(phoneNumber);
		m[4].add(pnt);
		m[5].add(jb);
		jb.addActionListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String getid = idt.getText();
		String getpw = pwt.getText();
		String getuserName = unt.getText();
		String pnum = pnt.getText();
		if (e.getSource().equals(jb)) {//ȸ������ ��ư
			ut.setId(getid);
			ut.setPw(getpw);
			ut.setUserName(getuserName);
			ut.setPhonenumber(pnum);
			if (ua.insert(ut)) {
				JOptionPane.showMessageDialog(null, "ȸ������ �Ϸ�", "ȸ������", JOptionPane.PLAIN_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "�ߺ��Ⱦ��̵� �Դϴ�", "���̵��ߺ�", JOptionPane.WARNING_MESSAGE);
			}
			this.setVisible(false);
			lo.actionPerformed(e);//�α���ȭ������ 
		}
	}

}
