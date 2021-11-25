package com.camposoft.enums;

import java.util.stream.Stream;

public enum OperationEnum {
	SUM, MULTIPLY, SUBSTRACTION, DIVISION;

	public static Stream<OperationEnum> stream() {
		return Stream.of(OperationEnum.values());
	}
}
