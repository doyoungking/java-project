package ugui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dto.UpurchaseDTO;
import mgui.AllList;
import mgui.LoginGui;

public class PurchaseGui extends JFrame implements ActionListener {
	JPanel[] m = new JPanel[5];
	JLabel title = new JLabel("Market ���Ű��� ȯ���մϴ�");
	JButton b1 = new JButton("��ٱ��ϴ��");
	JButton b2 = new JButton("��ٱ��� ���");
	JButton b3 = new JButton("��ٱ��� ���� "); 
	JButton b4 = new JButton("������������ ");
	JButton b5 = new JButton("�ʱ�ȭ��");
	UpurchaseDTO nowuser;
	
	public PurchaseGui(String uid) {
		UpurchaseDTO a = new UpurchaseDTO();
		title.setText(uid+"�� Market ���Ű��� ȯ���մϴ�");
		a.setId(uid);
		nowuser = a;
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
		m[3].add(b5);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public void pguibox() {
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(b1)) {//��ٱ��ϴ��
			new ProductPur(nowuser.getId());
			new AllList();
			this.setVisible(false);
		}
		if (e.getSource().equals(b2)) {//��ٱ������
			this.setVisible(false);
			new Purdelete(nowuser.getId());
		}
		if (e.getSource().equals(b3)) {//��ٱ��Ϻ��� �� ����
			this.setVisible(false);
			new PurList(nowuser.getId());
		}
		if (e.getSource().equals(b4)) {//������������ �� ����
			this.setVisible(false);
			new UserAllList(nowuser.getId());
		}
		if (e.getSource().equals(b5)) {//�ʱ�ȭ��
			this.setVisible(false);
			new LoginGui();
		}
	}
}
