package com.camposoft.serviceImplement;

import org.springframework.stereotype.Service;

import com.camposoft.dto.CalculatorDTO;
import com.camposoft.service.CalculatorService;

@Service
public class CalculatorServiceImplement implements CalculatorService {

	private CalculatorDTO calculator = new CalculatorDTO();

	@Override
	public CalculatorDTO operation(String operation) {
		calculator.setOperation(operation);
		switch (calculator.getOperation()) {
		case "Sum":
			calculator.setResult(sum());
			return calculator;

		case "Multiply":
			calculator.setResult(multiply());
			return calculator;

		case "Substraction":
			calculator.setResult(substraction());
			return calculator;

		case "Division":
			calculator.setResult(division());
			return calculator;
		default:
			break;
		}
		return null;
	}

	@Override
	public void sendOperand(CalculatorDTO calculatorDTO) {
		calculator.addOperand(calculatorDTO.getOperand());
	}

	private Integer sum() {
		return calculator.getOperands().stream().reduce(0, Integer::sum);
	}

	private Integer multiply() {
		return calculator.getOperands().stream().mapToInt(x -> x).reduce(1, Math::multiplyExact);
	}

	private Integer division() {
		Integer result = calculator.getOperands().get(0);
		for (Integer i = 1; i < calculator.getOperands().size(); i++) {
			result = result / calculator.getOperands().get(i);
		}
		return result;
	}

	private Integer substraction() {
		Integer result = calculator.getOperands().get(0);
		for (Integer i = 1; i < calculator.getOperands().size(); i++) {
			result -= calculator.getOperands().get(i);
		}
		return result;
	}
}
