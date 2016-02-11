package com.lynbrookrobotics.sixteen.config;

import edu.wpi.first.wpilibj.Jaguar;

/**
 * Aggregation of all intake hardware.
 */
public class IntakeHardware {
  Jaguar rightJaguar;
  Jaguar leftJaguar;

  public IntakeHardware(Jaguar rightJaguar, Jaguar leftJaguar) {
    this.rightJaguar = rightJaguar;
    this.leftJaguar = leftJaguar;
  }

  /**
   * Constructs an IntakeHardware given a configuration object
   * @param configuration the configuration to load ports from
   */
  public IntakeHardware(VariableConfiguration configuration) {
    this(
        new Jaguar(configuration.intakePorts().rightPort()),
        new Jaguar(configuration.intakePorts().leftPort())
    );
  }

  public Jaguar rightJaguar() {
    return rightJaguar;
  }

  public Jaguar leftJaguar() {
    return leftJaguar;
  }
}
