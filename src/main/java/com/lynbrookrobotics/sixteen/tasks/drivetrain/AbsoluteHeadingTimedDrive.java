package com.lynbrookrobotics.sixteen.tasks.drivetrain;

import com.lynbrookrobotics.potassium.tasks.FiniteTask;
import com.lynbrookrobotics.sixteen.components.drivetrain.DriveOnHeadingController;
import com.lynbrookrobotics.sixteen.components.drivetrain.Drivetrain;
import com.lynbrookrobotics.sixteen.config.RobotHardware;

import java.util.function.Function;
import java.util.function.Supplier;

public class AbsoluteHeadingTimedDrive extends FiniteTask {
    RobotHardware hardware;
    Drivetrain drivetrain;

    double absoluteHeading;
    Function<Double, Double> forward;
    DriveOnHeadingController controller;
    long duration;
    long endTime;

    public AbsoluteHeadingTimedDrive(long duration, Function<Double, Double> forward, double absoluteHeading, RobotHardware hardware, Drivetrain drivetrain) {
        this.duration = duration;
        this.absoluteHeading = absoluteHeading;
        this.forward = forward;
        this.hardware = hardware;
        this.drivetrain = drivetrain;
    }

    @Override
    protected void startTask() {
        Supplier<Double> progressForward =
                () -> forward.apply((endTime - System.currentTimeMillis()) / (double) duration);

        controller = new DriveOnHeadingController(absoluteHeading, progressForward, hardware);
        drivetrain.setController(controller);
        endTime = System.currentTimeMillis() + duration;
    }

    @Override
    protected void update() {
        if (System.currentTimeMillis() >= endTime) {
            finished();
        }
    }

    @Override
    protected void endTask() {
        logger.debug("DONEDONEDONE");
        drivetrain.resetToDefault();
    }
}