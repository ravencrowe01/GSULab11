import java.io.Serializable;

public class Person implements Comparable<Person>, Serializable {
	private int age;
	private String name;
	private String address;
	private int zip;
	private double salary;
	
	public Person(int age, String name, String address, int zip, double salary) {
		super();
		this.age = age;
		this.name = name;
		this.address = address;
		this.zip = zip;
		this.salary = salary;
	}
	
	public Person () { }
	

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString () {
		return String.format("%s %s %s %s %.2f%n", age, name, address, zip, salary);
	}

	@Override
	public int compareTo(Person o) {
		return getSalary() > o.getSalary() ? 1 : getSalary() == o.getSalary() ? 0 : -1;
	}
}
