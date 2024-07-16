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

public class Storage extends JFrame implements ActionListener {
	JPanel[] m = new JPanel[4];
	JLabel gname = new JLabel("��ǰ��");
	JLabel gea = new JLabel("�߰� ����");
	JTextField gnamet = new JTextField(10);
	JTextField geat = new JTextField(10);
	JButton button = new JButton("��ǰ�԰�");
	JButton menu = new JButton("�޴�");
	MarketGui mk = null;
	JLabel wel = new JLabel("��ǰ�԰� ȭ�� �Դϴ�");

	Storage(MarketGui a) {
		mk = a;
		this.setBounds(100, 100, 500, 400);
		this.setLayout(new GridLayout(4, 1));
		for (int i = 0; i < m.length; i++) {
			m[i] = new JPanel();
			this.add(m[i]);
		}
		m[0].add(button);
		m[1].add(gname);
		m[1].add(gnamet);
		m[2].add(gea);
		m[2].add(geat);
		m[0].add(menu);
		m[3].add(wel);

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
			String getname = gnamet.getText();
			int k = Integer.parseInt(geat.getText());
			mt.setName(getname);
			mt.setEa(k);
			ma.update(mt);
			wel.setText("�԰�Ϸ�");
			JOptionPane.showMessageDialog(null, "�԰�Ǿ����ϴ�", "��ǰ�԰�", JOptionPane.PLAIN_MESSAGE);
		}

		if (e.getSource().equals(menu)) {
			gnamet.setText("");
			geat.setText("");
			this.setVisible(false);

			mk.marketbox();

		}

	}
}
