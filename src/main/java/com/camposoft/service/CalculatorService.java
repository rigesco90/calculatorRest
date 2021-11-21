package com.camposoft.service;

import com.camposoft.dto.CalculatorDTO;

public interface CalculatorService {

	public CalculatorDTO operation(String operation);

	public void sendOperand(CalculatorDTO calculatorDTO);
}
