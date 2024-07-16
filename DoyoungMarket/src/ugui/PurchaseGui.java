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
	JLabel title = new JLabel("Market 오신것을 환영합니다");
	JButton b1 = new JButton("장바구니담기");
	JButton b2 = new JButton("장바구니 취소");
	JButton b3 = new JButton("장바구니 보기 "); 
	JButton b4 = new JButton("개인정보보기 ");
	JButton b5 = new JButton("초기화면");
	UpurchaseDTO nowuser;
	
	public PurchaseGui(String uid) {
		UpurchaseDTO a = new UpurchaseDTO();
		title.setText(uid+"님 Market 오신것을 환영합니다");
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
		if (e.getSource().equals(b1)) {//장바구니담기
			new ProductPur(nowuser.getId());
			new AllList();
			this.setVisible(false);
		}
		if (e.getSource().equals(b2)) {//장바구니취소
			this.setVisible(false);
			new Purdelete(nowuser.getId());
		}
		if (e.getSource().equals(b3)) {//장바구니보기 및 결제
			this.setVisible(false);
			new PurList(nowuser.getId());
		}
		if (e.getSource().equals(b4)) {//개인정보보기 및 충전
			this.setVisible(false);
			new UserAllList(nowuser.getId());
		}
		if (e.getSource().equals(b5)) {//초기화면
			this.setVisible(false);
			new LoginGui();
		}
	}
}
