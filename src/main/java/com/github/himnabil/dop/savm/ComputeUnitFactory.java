package com.github.himnabil.dop.savm;

import java.util.List;

@FunctionalInterface
public interface ComputeUnitFactory {
    ComputeUnit create(List<Instruction> instructions);
}
