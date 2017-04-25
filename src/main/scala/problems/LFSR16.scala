// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.util.Cat

// Problem:
//
// Implement a 16-bit Fibonacci Linear-feedback shift register
// with polynomial x^16 + x^14 + x^13 + x^11 + 1
// State change is allowed only when 'inc' is asserted
//
class LFSR16 extends Module {
  val io = IO(new Bundle {
    val inc = Input(Bool())
    val out = Output(UInt(16.W))
  })

  // Implement below ----------
  val x = Reg(init=1.U(16.W))
  when (io.inc) {
    val x_nxt = Cat(x(5)^x(3)^x(2)^x(0), x(15,1))
    x := x_nxt
  }
  io.out := x

  // Implement above ----------
}
