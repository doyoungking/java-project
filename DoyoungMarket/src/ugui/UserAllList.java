package ugui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.PlayerDAO;
import dto.PlayerDTO;

public class UserAllList extends JFrame implements ActionListener {
	JPanel p = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	JTextField ch = new JTextField(10);
	JLabel l = new JLabel("개인정보");
	JButton charge = new JButton("충전");
	JLabel wel = new JLabel("");
	JButton menu = new JButton("메뉴");
	String uid = null;
	PlayerDAO pa = new PlayerDAO();

	public UserAllList(String id) {
		uid = id;
		DefaultTableModel model2 = new DefaultTableModel();
		model2.addColumn("id");
		model2.addColumn("이름");
		model2.addColumn("전화번호");
		model2.addColumn("보유금액");
		ArrayList<PlayerDTO> g = pa.selectall();
		for (PlayerDTO temp : g) {
			if (uid.equals(temp.getId())) {
				String getid = temp.getId();
				String getusername = temp.getUserName();
				String getnumber = temp.getPhonenumber();
				int cash = temp.getCash();
				model2.addRow(new Object[] { getid, getusername, getnumber, cash });
			}
		}
		JTable table2 = new JTable(model2);
		JScrollPane sc2 = new JScrollPane(table2);
		
		
		sc2.setPreferredSize(new Dimension(300, 100));
		this.setBounds(100, 100, 600, 500);
		this.setLayout(null);
		p.add(l);
		p.setBounds(0, 0, 600, 50);

		this.add(p);
		p2.add(sc2);
		p2.setBounds(0, 50, 600, 150);

		this.add(p2);
		p3.setBounds(0, 210, 600, 70);
		p3.add(ch);
		p3.add(charge);

		this.add(p3);
		p4.add(wel);
		p4.setBounds(0, 280, 600, 50);

		this.add(p4);
		p5.add(menu);
		p5.setBounds(0, 330, 600, 50);

		this.add(p5);

		menu.addActionListener(this);
		charge.addActionListener(this);
		this.setVisible(true);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		PlayerDTO pt = new PlayerDTO();
		if (e.getSource().equals(charge)) {
			wel.setText("충전되었습니다");
			int money = Integer.parseInt(ch.getText());
			pt.setCash(money);
			pt.setId(uid);
			pa.update(pt);
		}

		if (e.getSource().equals(menu)) {
			this.setVisible(false);
			new PurchaseGui(uid);
		}

	}

}
