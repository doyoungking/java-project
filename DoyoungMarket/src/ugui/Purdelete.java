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
	JButton list = new JButton("장바구니보기");
	JLabel gdel = new JLabel("취소할 상품명");
	JLabel eadel = new JLabel("취소할 수량");
	JLabel wel = new JLabel("취소 화면 입니다");// 취소되었습니다 set text 바꾸고
	JTextField dnamet = new JTextField(10);
	JTextField deat = new JTextField(10);
	JButton button = new JButton("장바구니취소");
	JButton menu = new JButton("메뉴");
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
			wel.setText("취소 완료");
			JOptionPane.showMessageDialog(null, "취소 되었습니다", "취소", JOptionPane.PLAIN_MESSAGE);
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
