package org.firstinspires.ftc.teamcode.perseus.commands

import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import org.firstinspires.ftc.teamcode.perseus.subsystem.*

class TurretSequenceCommand(turret : Turret, arm : Arm, slides : Slides, intake : Intake, clocking : Clocking, turretAngle : Double, armAngle : Double) : SequentialGroup(
    InstantCmd({arm.motor.setPIDTarget(armAngle)})
        .alongWith(InstantCmd({turret.motor.setPIDTarget(turretAngle)}))
        .alongWith(SlidesCommands.SlidesHomeCommand(slides))
        .alongWith(ClockingCommands.ClockingLift(clocking))
        .alongWith(IntakeCommands.IntakeOff(intake))
        .alongWith(InstantCmd(intake::stopReading)),
        WaitCmd(0.5),
        IntakeCommands.IntakeSlow(intake)
) {
    init {
        addRequirements(turret, arm, intake, slides, clocking)
    }
}