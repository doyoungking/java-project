package ugui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.PlayerDAO;
import dao.UpurchaseDAO;
import dto.PlayerDTO;
import dto.UpurchaseDTO;
import mgui.AllList;

public class ProductPur extends JFrame implements ActionListener {
	JPanel[] m = new JPanel[5];
	JLabel gname = new JLabel("��ǰ��");
	JLabel gea = new JLabel("����");
	JLabel cucash = new JLabel("");
	JLabel wel = new JLabel("��ٱ��� ȭ�� �Դϴ�");
	JTextField gnamet = new JTextField(10);
	JTextField geat = new JTextField(10);
	JButton button = new JButton("��ٱ��� ���");
	JButton menu = new JButton("�޴�");
	String uid = null;//���� id ���庯��
	String scash = null;// �����ݾ� ���庯��
	public ProductPur(String id) {//������
		uid = id;
		PlayerDAO pad = new PlayerDAO();
		scash = pad.showcash(uid);
		cucash.setText(uid + "���� �����ݾ�" + scash + "��");// �ش���̵� �����ݾ�
		this.setBounds(100, 100, 500, 400);
		this.setLayout(new GridLayout(5, 1));
		for (int i = 0; i < m.length; i++) {
			m[i] = new JPanel();
			this.add(m[i]);
		}
		m[0].add(cucash);
		m[1].add(button);
		m[1].add(menu);
		m[2].add(gname);
		m[2].add(gnamet);
		m[3].add(gea);
		m[3].add(geat);
		m[4].add(wel);
		button.addActionListener(this);
		menu.addActionListener(this);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public void clear() {
		gnamet.setText("");
		geat.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		UpurchaseDAO pa = new UpurchaseDAO();
		UpurchaseDTO pt = new UpurchaseDTO();
		if (e.getSource().equals(button)) {
			if (Integer.parseInt(scash) > 0) {
				String getname = gnamet.getText();
				int getea = Integer.parseInt(geat.getText());
				pt.setName(getname);
				pt.setEa(getea);
				pt.setId(uid);
				pa.insert(pt);
				pa.eaupdate(pt);
				wel.setText("��ٱ��� ��� �Ϸ�");
				JOptionPane.showMessageDialog(null, "�Ϸ� �Ǿ����ϴ�", "��ٱ��� ���", JOptionPane.PLAIN_MESSAGE);
			} else if (Integer.parseInt(scash) == 0) {
				JOptionPane.showMessageDialog(null, "���� �����ݾ��� �����մϴ� ������Ź�帳�ϴ�", "�����ݾ׺���", JOptionPane.WARNING_MESSAGE);
			}
		}
		if (e.getSource().equals(menu)) {
			clear();
			this.setVisible(false);
			new PurchaseGui(uid);
		}
	}
	
}
