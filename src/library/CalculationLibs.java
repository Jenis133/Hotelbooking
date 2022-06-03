package library;

public class CalculationLibs {
	double roomPrice, acPrice, tvPrice, miniBarService, totalPrice;

	public CalculationLibs() {
		super();
		this.roomPrice = 0;
		this.acPrice = 0;
		this.tvPrice = 0;
		this.miniBarService = 0;
		this.totalPrice = totalPrice;
	}

	public CalculationLibs(double roomPrice, double acPrice, double tvPrice, double miniBarService, double totalPrice) {
		super();
		this.roomPrice = roomPrice;
		this.acPrice = acPrice;
		this.tvPrice = tvPrice;
		this.miniBarService = miniBarService;
		this.totalPrice = totalPrice;
	}

	public double getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}

	public double getAcPrice() {
		return acPrice;
	}

	public void setAcPrice(double acPrice) {
		this.acPrice = acPrice;
	}

	public double getTvPrice() {
		return tvPrice;
	}

	public void setTvPrice(double tvPrice) {
		this.tvPrice = tvPrice;
	}

	public double getMiniBarService() {
		return miniBarService;
	}

	public void setMiniBarService(double miniBarService) {
		this.miniBarService = miniBarService;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Calculation [roomPrice=" + roomPrice + ", acPrice=" + acPrice + ", tvPrice=" + tvPrice
				+ ", miniBarService=" + miniBarService + ", totalPrice=" + totalPrice + "]";
	}

}
