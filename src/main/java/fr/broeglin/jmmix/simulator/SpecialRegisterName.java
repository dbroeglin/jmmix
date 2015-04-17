package fr.broeglin.jmmix.simulator;

public enum SpecialRegisterName {

	rA("Arithmetic status register"),
	rB("Bootstrap register"),
	rC("Continuation register"),
	rD("Dividend register"),
	rE("Epsilon register"),
	rF("Failure location register"),
	rG("Global threshold register"),
	rH("Himult register"),
	rI("Interval counter"),
	rJ("Return-jump register"),
	rK("Interrupt mask register"),
	rL("Local threshold register"),
	rM("Multiplex mask register"),
	rN("Serial number"),
	rO("Register stack offset"),
	rP("Prediction register"),
	rQ("Interrupt request register"),
	rR("Remainder register"),
	rS("Register stack pointer"),
	rT("Trap address register"),
	rU("Usage counter"),
	rV("Virtual translation register"),
	rW("Where-interrupted register"),
	rX("Execution register"),
	rY("Y operand"),
	rZ("Z operand"),
	rBB("Bootstrap register"),
	rTT("Dynamic trap address register"),
	rWW("Where-interrupted register"),
	rXX("Execution register"),
	rYY("Y operand"),
	rZZ("Z operand");

	String description;

	private SpecialRegisterName(String description) {
		this.description = description;
	}
}
