package ugui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.UpurchaseDAO;
import dto.UpurchaseDTO;

public class Purdelete extends JFrame implements ActionListener {
	JPanel[] m = new JPanel[4];
	JButton list = new JButton("��ٱ��Ϻ���");
	JLabel gdel = new JLabel("����� ��ǰ��");
	JLabel eadel = new JLabel("����� ����");
	JLabel wel = new JLabel("��� ȭ�� �Դϴ�");// ��ҵǾ����ϴ� set text �ٲٰ�
	JTextField dnamet = new JTextField(10);
	JTextField deat = new JTextField(10);
	JButton button = new JButton("��ٱ������");
	JButton menu = new JButton("�޴�");
	String uid = null;

	public Purdelete(String id) {
		uid = id;
		this.setBounds(100, 100, 500, 400);
		this.setLayout(new GridLayout(4, 1));
		for (int i = 0; i < m.length; i++) {
			m[i] = new JPanel();
			this.add(m[i]);
		}
		m[0].add(list);
		m[0].add(button);
		m[0].add(menu);
		m[1].add(gdel);
		m[1].add(dnamet);
		m[2].add(eadel);
		m[2].add(deat);
		m[3].add(wel);
		button.addActionListener(this);
		menu.addActionListener(this);
		list.addActionListener(this);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		UpurchaseDAO pa = new UpurchaseDAO();
		UpurchaseDTO pt = new UpurchaseDTO();
		if (e.getSource().equals(button)) {
			String getname = dnamet.getText();
			int getea = Integer.parseInt(deat.getText());
			pt.setName(getname);
			pt.setEa(getea);
			pa.delete(pt);
			wel.setText("��� �Ϸ�");
			JOptionPane.showMessageDialog(null, "��� �Ǿ����ϴ�", "���", JOptionPane.PLAIN_MESSAGE);
		}
		if (e.getSource().equals(list)) {
			new PurList(uid);
		}
		if (e.getSource().equals(menu)) {
			this.setVisible(false);
			new PurchaseGui(uid);
		}

	}

}
