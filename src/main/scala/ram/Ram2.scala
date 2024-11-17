package ram
import chisel3._;import chisel3.util._;

class RAM(size:Int,width:Int) extends Module {
    val io = IO(new Bundle {
        val readAdr = Input(UInt(log2Ceil(size).W))
        val dataIn = Input(UInt(width.W))
        val out = Output(UInt(width.W))
    })
    val mem = Mem(size, UInt(width.W))
    mem.write(io.readAdr, io.dataIn)
    io.out := mem.read(io.readAdr)
}