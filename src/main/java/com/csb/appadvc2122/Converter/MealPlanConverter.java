package com.csb.appadvc2122.Converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.csb.appadvc2122.model.MPModel;

@Component
public class MealPlanConverter implements Converter<String, MPModel>{

	@Override
	public MPModel convert(String id) {
		// TODO Auto-generated method stub
		System.out.print("helo!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return null;
	}

}
