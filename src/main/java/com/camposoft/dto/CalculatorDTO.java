package com.camposoft.dto;

import java.util.ArrayList;
import java.util.List;

public class CalculatorDTO {

	private String operation;
	private Integer operand;
	private List<Integer> operands;
	private Integer result;

	public String getOperation() {
		return operation;
	}

	public Integer getOperand() {
		return operand;
	}

	public List<Integer> getOperands() {
		return operands;
	}

	public Integer getResult() {
		return result;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public void setOperand(Integer operand) {
		this.operand = operand;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public void addOperand(Integer operand) {
		if (this.operands == null) {
			this.operands = new ArrayList<>();
			this.operands.add(operand);
		} else {
			this.operands.add(operand);
		}
	}

}
