# Simple Virtual Machine (SVM) Specification

This document provides a comprehensive specification of the Simple Virtual Machine (SVM), including architecture, memory organization, instruction set, channel communication, and handling signals.
## Architecture

The SVM architecture consists of:
1. **Registers** : 16 general-purpose 8-bit registers (R0 to R15).
2. **Memory** : 256 bytes of memory, with each byte being addressable.
3. **Program Counter (PC)** : An 8-bit register that points to the current instruction being executed.
4. **Call Stack** : A stack used for handling function calls and returns.
5. **Channels** : A mechanism for communication with external modules.
## Memory Organization

Memory is organized into a 256-byte address space, with each byte being addressable. The memory is typically divided into the following sections:
1. **Program Code** : The executable code of the program.
2. **Data** : Constants and variables used by the program.
3. **Stack** : The call stack for managing function calls and local variables.
## Instruction Set

The SVM features an extended instruction set, including arithmetic, memory access, control flow, and channel communication operations. Each instruction is described below, along with its opcode, encoding, and expected behavior.
### LOAD

**Operation** : Load a value from memory into a register

**Opcode** : 0x01

**Encoding** : `0x01 Rn MM`
- Rn: Register number (0-15)
- MM: Memory address (0x00-0xFF)

**Description** : The LOAD instruction reads an 8-bit value from the specified memory address and stores it in the specified register.
### STORE

**Operation** : Store a value from a register to memory

**Opcode** : 0x02

**Encoding** : `0x02 Rn MM`
- Rn: Register number (0-15)
- MM: Memory address (0x00-0xFF)

**Description** : The STORE instruction writes the 8-bit value from the specified register to the specified memory address.
### ADD

**Operation** : Add two registers and store the result in another register

**Opcode** : 0x03

**Encoding** : `0x03 Rd Rs Rt`
- Rd: Destination register number (0-15)
- Rs: Source register 1 number (0-15)
- Rt: Source register 2 number (0-15)

**Description** : The ADD instruction adds the values in the source registers (Rs and Rt) and stores the result in the destination register (Rd).
### SUB

**Operation** : Subtract two registers and store the result in another register

**Opcode** : 0x04

**Encoding** : `0x04 Rd Rs Rt`
- Rd: Destination register number (0-15)
- Rs: Source register 1 number (0-15)
- Rt: Source register 2 number (0-15)

**Description** : The SUB instruction subtracts the value in the second source register (Rt) from the value in the first source register (Rs) and stores the result in the destination register (Rd).
### MUL

**Operation** : Multiply two registers and store the result in another register

**Opcode** : 0x05

**Encoding** : `0x05 Rd Rs Rt`
- Rd: Destination register number (0-15)
- Rs: Source register 1 number (0-15)
- Rt: Source register 2 number (0-15)

**Description** : The MUL instruction multiplies the values in the source registers (Rs and Rt) and stores the result in the destination register (Rd).
### DIV

**Operation** : Divide two registers and store the result in another register

**Opcode** : 0x


- 06

**Encoding** : `0x06 Rd Rs Rt`
- Rd: Destination register number (0-15)
- Rs: Source register 1 number (0-15)
- Rt: Source register 2 number (0-15)

**Description** : The DIV instruction divides the value in the first source register (Rs) by the value in the second source register (Rt) and stores the result in the destination register (Rd). If the divisor (Rt) is zero, the result is undefined, and an error should be handled accordingly.
### JMP

**Operation** : Jump to a specific memory location

**Opcode** : 0x07

**Encoding** : `0x07 MM`
- MM: Memory address (0x00-0xFF)

**Description** : The JMP instruction sets the program counter (PC) to the specified memory address, causing execution to continue from that location.
### JZ

**Operation** : Jump if the value in a specified register is zero

**Opcode** : 0x08

**Encoding** : `0x08 Rn MM`
- Rn: Register number (0-15)
- MM: Memory address (0x00-0xFF)

**Description** : The JZ instruction checks the value in the specified register (Rn). If the value is zero, it sets the program counter (PC) to the specified memory address, causing execution to continue from that location.
### JNZ

**Operation** : Jump if the value in a specified register is not zero

**Opcode** : 0x09

**Encoding** : `0x09 Rn MM`
- Rn: Register number (0-15)
- MM: Memory address (0x00-0xFF)

**Description** : The JNZ instruction checks the value in the specified register (Rn). If the value is not zero, it sets the program counter (PC) to the specified memory address, causing execution to continue from that location.
### PUSH

**Operation** : Push a value onto the stack

**Opcode** : 0x0A

**Encoding** : `0x0A Rn`
- Rn: Register number (0-15)

**Description** : The PUSH instruction pushes the value in the specified register (Rn) onto the call stack.
### POP

**Operation** : Pop a value from the stack

**Opcode** : 0x0B

**Encoding** : `0x0B Rn`
- Rn: Register number (0-15)

**Description** : The POP instruction pops the top value from the call stack and stores it in the specified register (Rn).
### CALL

**Operation** : Call a function at a specific memory address

**Opcode** : 0x0C

**Encoding** : `0x0C MM`
- MM: Memory address (0x00-0xFF)

**Description** : The CALL instruction pushes the current program counter (PC) value onto the call stack and sets the PC to the specified memory address, causing execution to continue from that location (the function entry point).
### RET

**Operation** : Return from a function

**Opcode** : 0x0D

**Encoding** : `0x0D`

**Description** : The RET instruction pops the top value from the call stack (the return address) and sets the program counter (PC) to that value, causing execution to continue from the calling function.
### MOV

**Operation** : Move a value between registers or from/to the stack

**Opcode** : 0x0E

**Encoding** : `0x0E Rd Rs`
- Rd: Destination register number (0-15)
- Rs: Source register number (0-15)

**Description** : The MOV instruction copies the value from the source register (Rs


- ) to the destination register (Rd).
### SEND

**Operation** : Send a value to an external module via a channel

**Opcode** : 0x0F

**Encoding** : `0x0F Rn CC`
- Rn: Register number (0-15)
- CC: Channel number (0x00-0xFF)

**Description** : The SEND instruction sends the value in the specified register (Rn) to an external module via the specified channel (CC). The behavior of the SEND instruction is dependent on the implementation of the external module and the communication protocol.
### RECV

**Operation** : Receive a value from an external module via a channel

**Opcode** : 0x10

**Encoding** : `0x10 Rn CC`
- Rn: Register number (0-15)
- CC: Channel number (0x00-0xFF)

**Description** : The RECV instruction receives a value from an external module via the specified channel (CC) and stores it in the specified register (Rn). The behavior of the RECV instruction is dependent on the implementation of the external module and the communication protocol.
### HALT

**Operation** : Stop the execution of the virtual machine

**Opcode** : 0x11

**Encoding** : `0x11`

**Description** : The HALT instruction stops the execution of the virtual machine. The current state of the registers and memory is preserved.
### WAIT_SIGNAL

**Operation** : Wait for a signal on a specific channel and execute a subroutine when the signal is received

**Opcode** : 0x12

**Encoding** : `0x12 CC MM`
- CC: Channel number (0x00-0xFF)
- MM: Memory address (0x00-0xFF)

**Description** : The WAIT_SIGNAL instruction waits for a signal on the specified channel (CC). When a signal is received, the current program counter (PC) value is pushed onto the call stack, and the PC is set to the specified memory address (MM) to start executing the subroutine. The subroutine should end with a RET instruction to return to the instruction after WAIT_SIGNAL.
## Example Program

Here's an example of a simple assembly program that demonstrates the use of the WAIT_SIGNAL instruction to listen for a signal on channel 0x01 and call a subroutine when the signal is received:

```asm

; Subroutine: signal_handler
; Description: Executes when a signal is received on channel 0x01
signal_handler:
  ; Perform desired operations when the signal is received
  ; ...
  RET              ; Return from the subroutine

; Main program
main:
  ; Set up and initialize the program
  ; ...

  ; Wait for a signal on channel 0x01
  WAIT_SIGNAL 0x01, signal_handler

  ; Continue with the main program
  ; ...

  HALT             ; Stop execution
```



In this example, the main program sets up and initializes the system as required, then waits for a signal on channel 0x01 using the WAIT_SIGNAL instruction. When a signal is received, the program counter jumps to the `signal_handler` subroutine, performs the desired operations, and returns to the instruction after WAIT_SIGNAL using the RET instruction. Afterward, the main program continues execution and eventually reaches the HALT instruction to stop execution.


- ## Usage Considerations

When working with the Simple Virtual Machine (SVM), consider the following guidelines and best practices to ensure efficient and maintainable code:
1. **Memory management** : Be mindful of memory limitations, as the SVM has only 256 bytes of memory. Make efficient use of memory by reusing variables, minimizing the use of global variables, and limiting the call stack depth.
2. **Function calls** : When using CALL and RET instructions, ensure that the call stack is balanced to avoid stack overflows or underflows. Always return from a function using the RET instruction.
3. **Error handling** : Implement error handling and recovery mechanisms for instructions that may produce undefined results or encounter runtime errors, such as the DIV instruction (division by zero).
4. **Concurrency** : When using channels to communicate with external modules, consider implementing synchronization mechanisms to prevent data races or other concurrency-related issues.
5. **Code organization** : Organize your assembly code into well-defined functions, labels, and data sections to improve readability and maintainability.
6. **Commenting** : Provide meaningful comments throughout your assembly code to explain the purpose and functionality of various sections, instructions, and variables.
## Additional Features

To further extend the SVM's functionality, consider implementing the following features:
1. **Interrupt handling** : Implement an interrupt handling mechanism that allows the SVM to respond to external events or signals, such as hardware interrupts or software-generated exceptions.
2. **Virtual memory** : Implement a virtual memory system that allows the SVM to address a larger memory space, with support for paging and memory protection.
3. **Floating-point arithmetic** : Add support for floating-point arithmetic operations, such as addition, subtraction, multiplication, and division, as well as comparison and conversion instructions.
4. **Input/output (I/O) operations** : Implement I/O instructions for reading and writing to peripheral devices, such as keyboards, displays, and storage devices.
5. **Multitasking** : Introduce a multitasking mechanism that allows the SVM to execute multiple programs concurrently, with support for process isolation, scheduling, and inter-process communication.

By following the guidelines and incorporating additional features, you can create a more robust and versatile virtual machine capable of handling a wide variety of tasks and applications.

