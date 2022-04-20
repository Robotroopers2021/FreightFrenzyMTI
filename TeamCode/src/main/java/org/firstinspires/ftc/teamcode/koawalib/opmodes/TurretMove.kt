package org.firstinspires.ftc.teamcode.koawalib.opmodes

import com.asiankoala.koawalib.command.CommandOpMode
import com.asiankoala.koawalib.command.commands.GoToPointCommand
import com.asiankoala.koawalib.command.commands.InfiniteCommand
import com.asiankoala.koawalib.command.commands.InstantCommand
import com.asiankoala.koawalib.command.commands.MecanumDriveCommand
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.util.Logger
import com.asiankoala.koawalib.util.LoggerConfig
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.koawalib.Koawa
import org.firstinspires.ftc.teamcode.koawalib.ScuffedKoawa
import org.firstinspires.ftc.teamcode.koawalib.commands.*
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Arm
import org.firstinspires.ftc.teamcode.koawalib.subsystem.DuckSpinnerCommand
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Turret

@TeleOp
class TurretMove : CommandOpMode() {
    private lateinit var koawa : ScuffedKoawa
    override fun mInit() {
        Logger.config = LoggerConfig(isLogging = true, isPrinting = false, isLoggingTelemetry = false, isDebugging = false, maxErrorCount = 1)
        koawa = ScuffedKoawa()


        gunner.dpadUp.onPress(InstantCommand({koawa.turret.setPower(0.25)}, koawa.turret))
        gunner.dpadDown.onPress(InstantCommand({koawa.turret.setPower(-0.25)}, koawa.turret))
        gunner.a.onPress(InstantCommand({koawa.turret.setPower(0.0)}, koawa.turret))


//        koawa.turret.setPIDTarget(0.0)
//        koawa.arm.setPIDTarget(30.0)
    }


    override fun mStart() {
    }

    override fun mLoop() {
    }
}
