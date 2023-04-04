package com.github.himnabil.dop.savm;

public sealed interface Instruction {

    record Halt() implements Instruction {}
}
