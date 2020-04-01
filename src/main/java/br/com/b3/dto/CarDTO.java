package br.com.b3.dto;

import java.io.Serializable;

import br.com.b3.entity.Car;

public class CarDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer year;
	private String licensePlate;
	private String model;
	private String color;
	
	public CarDTO() {}

	public CarDTO(Car car) {
		this.year = car.getYear();
		this.licensePlate = car.getLicensePlate();
		this.model = car.getModel();
		this.color = car.getColor();
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
