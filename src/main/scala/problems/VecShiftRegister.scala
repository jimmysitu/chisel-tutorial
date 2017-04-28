// See LICENSE.txt for license details.
package problems

import chisel3._

// Problem:
//
// Implement a loadable shift register with four 4-bit stages using Vec
// Shift occurs if 'shift' is asserted
// Load  occurs if 'load'  is asserted
// Whole state should be replaced with 'ins' when loaded
//
class VecShiftRegister extends Module {
  val io = IO(new Bundle {
    val ins   = Input(Vec(4, UInt(4.W)))
    val load  = Input(Bool())
    val shift = Input(Bool())
    val out   = Output(UInt(4.W))
  })
  // Implement below ----------
  val data = Reg(Vec(4, UInt(4.W)))

  when(io.load){
      data(0) := io.ins(0)
      data(1) := io.ins(1)
      data(2) := io.ins(2)
      data(3) := io.ins(3)
  }.elsewhen(io.shift){
      data(0) := io.ins(0)
      data(1) := data(0)
      data(2) := data(1)
      data(3) := data(2)
  }

  io.out := data(3)

  // Implement above ----------
}
