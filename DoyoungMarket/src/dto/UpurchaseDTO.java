package dto;

public class UpurchaseDTO {
	private String id;// �������̵�
	private String name;// ��ǰ��
	private int ea;// ��ǰ ����
	private int p_price;// ���� ����

	public int getP_price() {
		return p_price;
	}

	public void setP_price(int p_price) {
		this.p_price = p_price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEa() {
		return ea;
	}

	public void setEa(int ea) {
		this.ea = ea;
	}

}
