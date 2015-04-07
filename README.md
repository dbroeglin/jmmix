# Java MMIX Interpreter

## Disclaimer

This interpreter was written as an exercise to better understand the MMIX processor
and its instruction set. Use at your own peril! ;-)

## Useful commands

To inspect the content of MMO files:

    hexdump -e '1/1 "%03_ad |"' -e '4/1 " %02X" " | "' -e '4/1 "%_p" "\n"' <mmo file>
