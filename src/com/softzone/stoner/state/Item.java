package com.softzone.stoner.state;

public class Item {

	private String name, type, supplier, location, pic, description;
	private int itemId, amount;
	private double prize;

	public Item() {
	}

	public Item(String name, String type, String supplier, String location,
			String pic, int amount, double prize) {
		this.name = name;
		this.type = type;
		this.supplier = supplier;
		this.location = location;
		this.pic = pic;
		this.amount = amount;
		this.prize = prize;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getSupplier() {
		return supplier;
	}

	public String getLocation() {
		return location;
	}

	public String getPic() {
		return pic;
	}

	public String getDescription() {
		return description;
	}

	public int getAmount() {
		return amount;
	}

	public double getPrize() {
		return prize;
	}

	public int getItemId() {
		return itemId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setPrize(double prize) {
		this.prize = prize;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

}
