package com.camposoft.serviceImplement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.camposoft.dto.CalculatorDTO;

public class CalculadoraServiceTest {

	CalculatorServiceImplement calculatorService;
	CalculatorDTO calculatorDTO;

	@BeforeEach
	void setUp() {
		calculatorDTO = mock(CalculatorDTO.class);
		calculatorService = new CalculatorServiceImplement();
		calculatorService.calculatorDTO = calculatorDTO;
	}

	@Test
	void sendOperandTest() {
		CalculatorDTO calculatorDTO = new CalculatorDTO();
		calculatorDTO.setOperand(4);
		calculatorDTO = calculatorService.sendOperand(calculatorDTO);
		calculatorDTO.setOperand(3);
		calculatorDTO = calculatorService.sendOperand(calculatorDTO);

		assertNotNull(calculatorDTO);
		assertEquals(calculatorDTO.getOperands().size(), 2);
		assertEquals(calculatorDTO.getOperands().get(0), 4);
		assertEquals(calculatorDTO.getOperands().get(1), 3);
	}

	@Test
	void sumTest() {
		List<Integer> operands = Arrays.asList(10, 20, 30, 40);
		when(calculatorDTO.getOperands()).thenReturn(operands);
		Integer result = calculatorService.sum();

		assertEquals(result, 100);
	}
	
	@Test
	void substractionTest() {
		List<Integer> operands = Arrays.asList(40, 10, 8);
		when(calculatorDTO.getOperands()).thenReturn(operands);
		Integer result = calculatorService.substraction();

		assertEquals(result, 22);
	}
	
	@Test
	void multiplyTest() {
		List<Integer> operands = Arrays.asList(10, 2, 3, 4);
		when(calculatorDTO.getOperands()).thenReturn(operands);
		Integer result = calculatorService.multiply();

		assertEquals(result, 240);
	}

	@Test
	void divisionTest() {
		List<Integer> operands = Arrays.asList(40, 2);
		when(calculatorDTO.getOperands()).thenReturn(operands);
		Integer result = calculatorService.division();

		assertEquals(result, 20);
	}
}
