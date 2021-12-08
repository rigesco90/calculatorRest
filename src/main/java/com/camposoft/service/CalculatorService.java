package com.camposoft.service;

import com.camposoft.dto.CalculatorDTO;

public interface CalculatorService {

	public CalculatorDTO operation(String operation);

	public CalculatorDTO sendOperand(CalculatorDTO calculatorDTO);
}
