package com.github.himnabil.dop.savm;

import java.util.List;

public class ComputeUnit {

        private final List<Instruction> instructions;
        private int pc = 0;

        private long[] registers = new long[][16];




        public ComputeUnit(List<Instruction> instructions) {
            this.instructions = instructions;
        }

        public void run() {

                 Instruction.Halt) {
                    break;
                }
            }
        }
}
