package fr.broeglin.jmmix.simulator;

public enum SpecialRegisterName {

	/* Order matters !!! */
	
	rB("Bootstrap register"), // 0
	rD("Dividend register"),
	rE("Epsilon register"),
	rH("Himult register"),
	rJ("Return-jump register"),
	rM("Multiplex mask register"),
	rR("Remainder register"),
	rBB("Bootstrap register"),
	rC("Continuation register"), // cycle counter ?
	rN("Serial number"),
	
	rO("Register stack offset"), // 10
	rS("Register stack pointer"),
	rI("Interval counter"),
	rT("Trap address register"),
	rTT("Dynamic trap address register"),
	rK("Interrupt mask register"),
	rQ("Interrupt request register"),
	rU("Usage counter"),
	rV("Virtual translation register"),
	rG("Global threshold register"),

	rL("Local threshold register"), // 20
	rA("Arithmetic status register"),
	rF("Failure location register"),
	rP("Prediction register"),
	rW("Where-interrupted register"),
	rX("Execution register"),
	rY("Y operand"),
	rZ("Z operand"),
	rWW("Where-interrupted register"),
	rXX("Execution register"),
	
	rYY("Y operand"), // 30
	rZZ("Z operand");


	String description;

	
	
	private SpecialRegisterName(String description) {
		this.description = description;
	}
}
