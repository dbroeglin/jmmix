# Java MMIX Interpreter

## Disclaimer

This interpreter was written as an exercise to better understand the MMIX processor
and its instruction set. Use at your own peril! ;-)

Currently the simulator is in a kind of "tracing bullet" state where it is able
to load and execute very simple programs. I will continue working on it as time
allows. The memory sub-system seems complete, the processor is a work in progress.

Although this is not exactly a Java port of Knuth's MMIX simulator, I liberally
read his C code and based this work on it. Any good idea in this code is to be
attributed to the original implementation, whereas all bugs and mistakes are 
solely mine.

## References

* MMIX documentation: http://mmix.cs.hm.edu/doc/index.html
* MMIX opcodes: http://mmix.cs.hm.edu/doc/opcodes.html
* Knuth's MMIX simulator documentation: http://mmix.cs.hm.edu/doc/mmix-sim.pdf

## Running

To run the simulator:

	java -jar jmmix.jar <mmo file>

or you can simply execute the unit tests (`mmixal` needs to be in the PATH for the
tests to run).

Running the simulator with logging:

	java -Djava.util.logging.config.file=src/test/resources/logging.properties -jar jmmix.jar <mmo file>

## Testing

To run the tests:

	mvn test

The tests must be run under a system that has ``bash``, ``mmix``and ``mmixal`` in
the PATH.

## Useful commands

To inspect the content of MMO files:

    hexdump -e '1/1 "%03_ad |"' -e '4/1 " %02X" " | "' -e '4/1 "%_p" "\n"' <mmo file>
