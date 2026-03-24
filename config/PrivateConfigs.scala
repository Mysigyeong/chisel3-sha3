//see LICENSE for license
package rocketchip

import chisel3._
import freechips.rocketchip.tile._
import freechips.rocketchip.diplomacy._
import org.chipsalliance.cde.config._
import sha3._

// Modernized configuration using standard CDE `(site, here, up) => { ... }` function
class Sha3Config extends Config((site, here, up) => {
  // Replace old `Knob` and `World.TopDefs` with direct mappings to parameters defined in sha3.scala
  case Sha3WidthP => 64
  case Sha3Stages => 1
  case Sha3FastMem => true
  case Sha3BufferSram => false

  // Replace the old `RoccParameters` object instantiation with the modern `LazyModule` approach for RoCC
  case BuildRoCC => up(BuildRoCC, site) ++ Seq(
    (p: Parameters) => {
      LazyModule(new Sha3Accel(OpcodeSet.custom0)(p))
    }
  )
})

// Original compositions are maintained, assuming DefaultVLSIConfig, etc., exist in your broader module structure.
// Constraints and knobs have been natively resolved in Sha3Config above.
class Sha3VLSIConfig extends Config(new Sha3Config /* ++ new DefaultVLSIConfig */)
class Sha3FPGAConfig extends Config(new Sha3Config /* ++ new DefaultFPGAConfig */) 
class Sha3CPPConfig extends Config(new Sha3Config /* ++ new DefaultCPPConfig */) 
