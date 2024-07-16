package mgui;

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

import dao.MarketDAO;
import dto.MarketDTO;

public class Productselgrid extends JFrame implements ActionListener {
	DefaultTableModel model2 = new DefaultTableModel();
	JTable table2 = new JTable(model2);
	JScrollPane sc2 = new JScrollPane(table2);
	JButton en = new JButton("상품등록");
	MarketDAO ma = new MarketDAO();
	
	JButton menu = new JButton("메뉴");
	JButton clear = new JButton("지우기");
	JPanel[] m = new JPanel[5];
	JLabel l = new JLabel("상품등록화면");
	JLabel gname = new JLabel("상품명");
	JLabel gd = new JLabel("상품정보");
	JLabel gea = new JLabel("수량");
	JLabel gprice = new JLabel("가격");
	JLabel wel = new JLabel("상품등록 화면 입니다");
	JTextField gnamet = new JTextField(10);
	JTextField gdt = new JTextField(10);
	JTextField geat = new JTextField(10);
	JTextField gpricet = new JTextField(10);

	MarketGui mk = null;
	


	public Productselgrid(MarketGui a) {
		mk = a;
//		this.setVisible(true);
		this.setBounds(100, 100, 1000, 800);

		this.setLayout(null);
		for (int i = 0; i < m.length; i++) {
			m[i] = new JPanel();
			this.add(m[i]);
		}
		m[0].setBounds(0, 0, 1000, 50);
		m[0].add(l);
		m[1].setBounds(0, 50, 1000, 50);
		m[1].add(gname);
		m[1].add(gnamet);
		m[1].add(gd);
		m[1].add(gdt);
		m[1].add(gea);
		m[1].add(geat);
		m[1].add(gname);
		m[1].add(gnamet);
		m[1].add(gd);
		m[1].add(gdt);
		m[1].add(gea);
		m[1].add(geat);
		m[1].add(gprice);
		m[1].add(gpricet);
		m[2].add(en);
		m[2].add(menu);
		m[2].add(clear);
		m[2].setBounds(0, 100, 1000, 50);
		m[3].setBounds(0, 150, 1000, 50);
		m[3].add(wel);
		m[4].setBounds(0, 200, 1000, 800);
		en.addActionListener(this);
		menu.addActionListener(this);
		clear.addActionListener(this);
		talbeColumn();

	}

	public void talbeColumn() {
		model2.addColumn("상품명");
		model2.addColumn("상품정보");
		model2.addColumn("수량");
		model2.addColumn("가격");
	}

	public void tableRow() {
		ArrayList<MarketDTO> g2 = ma.selectAll();
		for (MarketDTO temp2 : g2) {
			String getname2 = temp2.getName();
			String getdetail2 = temp2.getDetail();
			int getea2 = temp2.getEa();
			int getprice2 = temp2.getPrice();
			model2.addRow(new Object[] { getname2, getdetail2, getea2, getprice2 });
		}
		m[4].add(sc2);
	}

	public void clear() {
		gnamet.setText("");
		gdt.setText("");
		geat.setText("");
		gpricet.setText("");
		model2.setNumRows(0);
//		model2.removeTableModelListener(table2);
		wel.setText("상품등록 화면 입니다");
		m[4].removeAll();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MarketDTO mt = new MarketDTO();
		if (e.getSource().equals(en)) {
			String getname = gnamet.getText();
			String getdetail = gdt.getText();
			int k = Integer.parseInt(geat.getText());
			int p = Integer.parseInt(gpricet.getText());
			mt.setName(getname);
			mt.setDetail(getdetail);
			mt.setEa(k);
			mt.setPrice(p);
			ma.insert(mt);
			wel.setText("등록완료");
			tableRow();
		}

		if (e.getSource().equals(menu)) {
			this.setVisible(false);
			mk.marketbox();
		}

		if (e.getSource().equals(clear)) {
			clear();
		}

	}

}
