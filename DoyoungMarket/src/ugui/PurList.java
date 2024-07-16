package ugui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.PlayerDAO;
import dao.UpurchaseDAO;
import dto.PlayerDTO;
import dto.UpurchaseDTO;

public class PurList extends JFrame implements ActionListener {
	JPanel p = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	JPanel p6 = new JPanel();
	JLabel basket = new JLabel("장바구니");
	JLabel cucash = new JLabel("");
	JLabel pay = new JLabel("");
	JLabel wel = new JLabel("");
	JButton pur = new JButton("결제하기");
	JButton menu = new JButton("메뉴");
	PurchaseGui pg;
	String uid = null;//현재 id 저장변수
	String scash = null;// 보유금액 저장변수
	int totalpay;// 총 결제 금액 저장변수
	UpurchaseDAO upa = new UpurchaseDAO();

	public PurList(String id) {
		uid = id;
		PlayerDAO pa = new PlayerDAO();
		scash = pa.showcash(uid);// 해당 id 보유금액
		System.out.println("보유금액" + scash);//
		totalpay = upa.showprice(uid);// 해당 id 결제 금액
		pay.setText("총 결제금액 " + totalpay + "원");
		cucash.setText(uid + "님의 보유금액" + scash + "원");
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("구매한 상품명");
		model.addColumn("구매한 수량");
		model.addColumn("결제금액");

		ArrayList<UpurchaseDTO> g = upa.select();
		for (UpurchaseDTO temp : g) {
			if (uid.equals(temp.getId())) {
				String getid = temp.getId();
				String getname = temp.getName();
				int getea = temp.getEa();
				int getprice = temp.getP_price();
				model.addRow(new Object[] { getid, getname, getea, getprice });
			}
		}
		JTable t = new JTable(model);
		JScrollPane sc = new JScrollPane(t);
		sc.setPreferredSize(new Dimension(300, 100));
		this.setBounds(100, 100, 600, 500);
		this.setLayout(null);
		p.add(basket);
		p.setBounds(0, 0, 600, 50);
		this.add(p);
		p2.add(sc);
		p2.setBounds(0, 50, 600, 110);
		this.add(p2);

		p3.setBounds(0, 170, 600, 30);
		p3.add(pay);
		this.add(p3);

		p4.setBounds(0, 210, 600, 70);
		p4.add(cucash);
		p4.add(pur);
		this.add(p4);

		p5.add(wel);
		p5.setBounds(0, 285, 600, 30);
		this.add(p5);

		p6.add(menu);
		p6.setBounds(0, 320, 600, 50);
		this.add(p6);
		menu.addActionListener(this);
		pur.addActionListener(this);
		this.setVisible(true);
		this.setResizable(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(pur)) {
			if (Integer.parseInt(scash) - totalpay >= 0) {
				UpurchaseDTO upt = new UpurchaseDTO();
				upt.setId(uid);
				upa.purchase(upt, totalpay);
				upa.del(upt);
				wel.setText("구매 완료");
			} else if (Integer.parseInt(scash) - totalpay < 0) {
				JOptionPane.showMessageDialog(null, "현재 보유금액이 부족합니다 충전부탁드립니다", "보유금액부족", JOptionPane.WARNING_MESSAGE);
			}
		}

		if (e.getSource().equals(menu)) {
			this.setVisible(false);
			new PurchaseGui(uid);

		}

	}

}
