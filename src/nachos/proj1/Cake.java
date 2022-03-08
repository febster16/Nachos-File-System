package nachos.proj1;

public class Cake implements Runnable{
	
	private String name;
	private String filling;
	private String topping;
	
	public Cake(String name, String filling, String topping) {
		super();
		this.name = name;
		this.filling = filling;
		this.topping = topping;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilling() {
		return filling;
	}

	public void setFilling(String filling) {
		this.filling = filling;
	}

	public String getTopping() {
		return topping;
	}

	public void setTopping(String topping) {
		this.topping = topping;
	}
	
	@Override
	public void run() {
		System.out.println("Cake name: " + name);
		System.out.println("Cake Filling: " + filling);
		System.out.println("Cake Topping: " + topping);
		System.out.println();
	}

}
