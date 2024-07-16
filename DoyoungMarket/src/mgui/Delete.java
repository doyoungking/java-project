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

import dao.MarketDAO;
import dto.MarketDTO;

public class Delete extends JFrame implements ActionListener {
	JPanel[] m = new JPanel[3];
	JLabel gdel = new JLabel("������ ��ǰ��");
	JLabel wel = new JLabel("��ǰ���� ȭ�� �Դϴ�");
	JTextField dnamet = new JTextField(10);
	JButton button = new JButton("��ǰ����");
	JButton menu = new JButton("�޴�");
	MarketGui mk = null;

	public Delete(MarketGui a) {
		mk = a;
		this.setBounds(100, 100, 500, 400);
		this.setLayout(new GridLayout(3, 1));
		for (int i = 0; i < m.length; i++) {
			m[i] = new JPanel();
			this.add(m[i]);
		}
		m[0].add(button);
		m[0].add(menu);
		m[1].add(gdel);
		m[1].add(dnamet);
		m[2].add(wel);
		button.addActionListener(this);
		menu.addActionListener(this);
//		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MarketDAO ma = new MarketDAO();
		MarketDTO mt = new MarketDTO();
		if (e.getSource().equals(button)) {
			String dname = dnamet.getText();
			mt.setName(dname);
			ma.delete(mt);
			wel.setText("�����Ϸ�");
			JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�", "��ǰ����", JOptionPane.PLAIN_MESSAGE);
		}
		if (e.getSource().equals(menu)) {
			dnamet.setText("");
			this.setVisible(false);
			mk.marketbox();
		}
	}

}
