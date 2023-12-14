package com.example.eftv.item1;

/*
 * Effective-java 아이템1 : 생성자 대신 정적 팩터리 메서드를 고려하라
 * 장점1. 이름을 가질 수 있다.
 * 장점2. 호출될 때마다 인스턴스를 새로 생성하지는 않아도 된다.
 * 장점3. 반환타입의 하위 타입 객체를 반환할 수 있는 능력이 있다.
 * 장점4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.
 * 장점5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
 * */
public class Car {
	private String brandName;
	
	private String modelName;
	
	private String size;
	
	private Car() {
	}

	public Car(String brandName, String modelName) {
		this.brandName = brandName;
		this.modelName = modelName;
	}
	
// 시그니처가 같은 생성자 여러개를 만들 수 없음
//	public Car(String brandName, String size) {
//		this.brandName = brandName;
//		this.size = size;
//	}
	
	public static Car getSonata() {
		return new Car("현대", "소나타");
	}
	
	public static Car getMediumSize(String brandName) {
		Car car = new Car();
		car.brandName = brandName;
		car.size = "medium";
		return car;
	}
	
	private static final Car carInstance = new Car();
	
	public static Car getCar() {
		return carInstance;
	}
	
	public static void main(String[] args) {
		//생성자를 이용한 객체 생성
		//매개변수 만으로는 반환 될 객체의 특성을 알기 힘들다.
		Car car = new Car("현대", "소나타");
		
		//장점1. 이름을 가질 수 있다.
		//정적 팩터리 메서드를 활용해 반환될 객체의 특성을 메서드 명을 통해 알기 쉽다.
		Car sonata = Car.getSonata();
		
		//장점1. 이름을 가질 수 있다.
		//"현대"브랜드의 중형사이즈의 자동차를 만들고 싶다.
		//하지만  시그니처가 brandName, modelName인 생성자가 이미 존재하기 때문에 같은 시그니처인 brandName, size로 생성자를 만들지 못한다.
		//정적 팩터리 메서드를 활용해 내가 반환하고 싶은 "현대"브랜드의 중형사이즈의 자동차를 만들었다.
		Car mediumSizeCar = Car.getMediumSize("현대");
		
		//장점2. 호출될 때마다 인스턴스를 새로 생성하지 않아도 된다.
		//생성자를 private로 선언해 외부에서 생성할 수 없도록 통제하고, 불변하는 인스턴스를 만든다.
		//정적 팩터리 메서드를 활용해 인스턴스가 단 하나임을 보장할 수 있다.
		Car car1 = Car.getCar();
	}
}
