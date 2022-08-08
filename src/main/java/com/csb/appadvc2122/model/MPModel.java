package com.csb.appadvc2122.model;

import javax.persistence.*;

@Entity
@Table(name = "meal_plan")
public class MPModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer day_number;

    @Column(nullable = false)
    private Integer meal_number;

    @Column(nullable = false)
    private String food;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDay_number() {
		return day_number;
	}

	public void setDay_number(Integer day_number) {
		this.day_number = day_number;
	}

	public Integer getMeal_number() {
		return meal_number;
	}

	public void setMeal_number(Integer meal_number) {
		this.meal_number = meal_number;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}
}
