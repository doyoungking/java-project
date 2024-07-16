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
	JLabel gname = new JLabel("상품명");
	JLabel gea = new JLabel("수량");
	JLabel cucash = new JLabel("");
	JLabel wel = new JLabel("장바구니 화면 입니다");
	JTextField gnamet = new JTextField(10);
	JTextField geat = new JTextField(10);
	JButton button = new JButton("장바구니 담기");
	JButton menu = new JButton("메뉴");
	String uid = null;//현재 id 저장변수
	String scash = null;// 보유금액 저장변수
	public ProductPur(String id) {//생성자
		uid = id;
		PlayerDAO pad = new PlayerDAO();
		scash = pad.showcash(uid);
		cucash.setText(uid + "님의 보유금액" + scash + "원");// 해당아이디 보유금액
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
				wel.setText("장바구니 담기 완료");
				JOptionPane.showMessageDialog(null, "완료 되었습니다", "장바구니 담기", JOptionPane.PLAIN_MESSAGE);
			} else if (Integer.parseInt(scash) == 0) {
				JOptionPane.showMessageDialog(null, "현재 보유금액이 부족합니다 충전부탁드립니다", "보유금액부족", JOptionPane.WARNING_MESSAGE);
			}
		}
		if (e.getSource().equals(menu)) {
			clear();
			this.setVisible(false);
			new PurchaseGui(uid);
		}
	}
	
}
