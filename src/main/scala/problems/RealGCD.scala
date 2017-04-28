// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.util.{Valid, DeqIO}

// Problem:
// Implement a GCD circuit (the greatest common divisor of two numbers).
// Input numbers are bundled as 'RealGCDInput' and communicated using the Decoupled handshake protocol
//
class RealGCDInput extends Bundle {
  val a = UInt(16.W)
  val b = UInt(16.W)
}

class RealGCD extends Module {
  val io  = IO(new Bundle {
    val in  = DeqIO(new RealGCDInput())
    val out = Output(Valid(UInt(16.W)))
  })

  // Implement below ----------
  val x = Reg(UInt())
  val y = Reg(UInt())
  val bsy = Reg(init=false.B)
 
  io.in.ready := !bsy    // when = true, means data a and b is ready captured

  when(io.in.valid){
      x := io.in.bits.a
      y := io.in.bits.b
      bsy := true.B
  }

  when(bsy){  
      when(x > y){ 
          x := x -% y 
      }.otherwise{
          y := y -% x
      }
  }

  io.out.bits := x
  io.out.valid := (y === 0.U)
  when(io.out.valid){
      bsy := false.B
  }
  // Implement above ----------

}
