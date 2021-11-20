package com.camposoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camposoft.service.CalculatorService;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

	@Autowired
	private CalculatorService calculatorService;

	@PostMapping("/add")
	public <T> Integer add(@RequestBody List<Integer> nums) {
		return calculatorService.add(nums);
	}

	@PostMapping("/sendOperand")
	public <T> String sendOperand(@RequestBody String operand) {
		return operand;

	}

}
