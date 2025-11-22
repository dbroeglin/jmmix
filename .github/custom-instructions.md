# GitHub Copilot Custom Instructions for JMMIX

## Project Overview

This is a Java implementation of an MMIX processor simulator/interpreter. MMIX is a 64-bit RISC architecture designed by Donald Knuth as a successor to his educational MIX computer. This project is an educational exercise to understand the MMIX processor and instruction set.

## Technology Stack

- **Language**: Java 8
- **Build Tool**: Maven
- **Testing**: JUnit 4.13.1, EasyMock 3.3.1
- **Dependencies**: Google Guava (test scope)
- **Main Class**: `fr.broeglin.jmmix.simulator.Main`

## Architecture

The simulator consists of:
- **Memory subsystem**: Memory management for the MMIX processor (mostly complete)
- **Processor**: CPU simulation (work in progress)
- **Instructions**: Various instruction implementations organized by category:
  - Arithmetic instructions
  - Float instructions
  - Branch instructions
  - Conditional instructions
  - Store instructions
  - Load instructions

## Coding Standards

1. **Java Version**: Target Java 8 (source and target compatibility)
2. **Package Structure**: Use `fr.broeglin.jmmix.simulator` as the base package
3. **Naming Conventions**: Follow standard Java naming conventions
4. **Error Handling**: Use `InvalidInstruction` exception for invalid operations
5. **Testing**: Write JUnit tests for new functionality

## Known Limitations

- Register stack (rL) is not yet implemented
- Arithmetic register (rA) is not fully implemented
- The simulator is in "tracing bullet" state - basic functionality works, but many features are incomplete

## MMIX-Specific Context

- MMIX is a 64-bit RISC processor with 256 general-purpose registers
- The simulator loads and executes MMO (MMIX Object) files
- External tools required for testing: `mmixal` (assembler) and `mmix` (reference simulator)
- MMIX documentation: http://mmix.cs.hm.edu/doc/index.html
- MMIX opcodes reference: http://mmix.cs.hm.edu/doc/opcodes.html

## Development Workflow

1. **Building**: `mvn clean package`
2. **Testing**: `mvn test` (requires `bash`, `mmix`, and `mmixal` in PATH)
3. **Running**: `java -jar jmmix.jar <mmo file>`
4. **Debugging**: Use logging properties from `src/test/resources/logging.properties`

## Code Generation Guidelines

When generating code for this project:

1. **Instruction Implementation**: New instructions should follow the existing pattern in the instructions package
2. **Register Operations**: Work with 64-bit values and respect MMIX register conventions
3. **Memory Access**: Use the Memory class for all memory operations
4. **Testing**: Include unit tests that compare behavior with the reference MMIX simulator when possible
5. **Documentation**: Reference MMIX documentation and Knuth's original C implementation for correctness
6. **Attribution**: Good ideas come from Knuth's implementation; bugs are ours to fix

## Special Considerations

- This is an educational project inspired by Knuth's C implementation
- Correctness is important - when in doubt, consult MMIX documentation
- The codebase is work-in-progress; incomplete features should be clearly marked
- Performance is secondary to clarity and correctness
