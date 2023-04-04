package com.github.himnabil.dop.savm;

@FunctionalInterface
public interface InstructionParser {
    Instruction parse(String line);
}
