package ram

import SymbolicTrajectoryEvaluation._
import chisel3._
import chiseltest._
import org.scalatest.freespec.AnyFreeSpec
import scala.collection.mutable.ArrayBuffer
import chisel3._;import chisel3.util._;

class ramSpecArrayIndex extends AnyFreeSpec with ChiselScalatestTester {
  "ram lunwen pass" in {
    val width = 2;
    val size = 8;
    test(new RAM(size, width)) { dut =>
      var a = VAR("read_addr_variable", log2Ceil(size))
      var b = VAR("b", log2Ceil(size))
      var d = VAR("data", width)
      var ant: trajFormula  = at_cycle(is_bv(dut.io.readAdr, a) && is_array(dut.mem, d, b, width - 1, 0),0)
      var cons:trajFormula = at_cycle(Imply(EQUAL(a, b), is_bv(dut.io.out, d)),0)
      val assert = new Assert(ant, cons, () => new RAM(size, width), SMT)
      assert.STE
    }
  }
}
