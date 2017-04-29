// See LICENSE.txt for license details.
package problems

import chisel3._

// Problem:
//
// Implement a parametrized simple shift register.
// 'n' is the number of elements in the shift register.
// 'w' is the width of one element.

class VecShiftRegisterParam(val n: Int, val w: Int) extends Module {
  val io = IO(new Bundle {
    val in  = Input(UInt(w.W))
    val out = Output(UInt(w.W))
  })

  // Implement below ----------
  val iv = Vec.fill(n){0.U(w.W)}
  val data = RegInit(Vec(n, UInt(w.W)), iv)
  for (i <- n-1 to 1 by -1)
      data(i) := data(i-1)
  data(0) := io.in
  io.out := data(n-1)
}
// Implement above ----------
