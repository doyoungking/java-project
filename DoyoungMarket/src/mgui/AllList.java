package mgui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.MarketDAO;
import dto.MarketDTO;

public class AllList extends JFrame implements ActionListener {

	JButton menu = new JButton("메뉴");
	MarketGui mk;

	public AllList() {

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("상품명");
		model.addColumn("상품정보");
		model.addColumn("수량");
		model.addColumn("단가");
		
		MarketDAO ma = new MarketDAO();
		ArrayList<MarketDTO> g = ma.selectAll();
		for (MarketDTO temp : g) {
			String getname = temp.getName();
			String getdetail = temp.getDetail();
			int getea = temp.getEa();
			int getprice = temp.getPrice();
			model.addRow(new Object[] { getname, getdetail, getea, getprice });
		}

		JTable table = new JTable(model);
		JScrollPane sc = new JScrollPane(table);
		this.add(sc);
		this.setBounds(600, 100, 500, 400);
		menu.addActionListener(this);
		this.setVisible(true);
		this.setResizable(false);
	}

	public void listbox() {
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(menu)) {
			mk.marketbox();
		}

	}

}
