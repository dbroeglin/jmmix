# Java MMIX Interpreter

## Disclaimer

This interpreter was written as an exercise to better understand the MMIX processor
and its instruction set. Use at your own peril! ;-)

Currently the simulator is in a kind of "tracing bullet" state where it is able
to load and execute very simple programs. I will continue working on it as time
allows. The memory sub-system seems complete, the processor is a work in progress.

## Running

To run the simulator:

	java -jar jmmix.jar <mmo file>

or you can simply execute the unit tests (`mmixal` needs to be in the PATH for the
tests to run).



## Useful commands

To inspect the content of MMO files:

    hexdump -e '1/1 "%03_ad |"' -e '4/1 " %02X" " | "' -e '4/1 "%_p" "\n"' <mmo file>
