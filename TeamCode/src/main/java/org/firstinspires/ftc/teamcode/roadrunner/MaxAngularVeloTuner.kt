
package robotuprising.ftc2021.auto.drive.opmode

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.acmerobotics.roadrunner.geometry.Pose2d
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.util.ElapsedTime
import robotuprising.ftc2021.auto.drive.SampleMecanumDrive
import java.util.*

/**
 * This routine is designed to calculate the maximum angular velocity your bot can achieve under load.
 *
 *
 * Upon pressing start, your bot will turn at max power for RUNTIME seconds.
 *
 *
 * Further fine tuning of MAX_ANG_VEL may be desired.
 */
@Autonomous(group = "drive")
@Disabled
class MaxAngularVeloTuner : LinearOpMode() {
    private lateinit var timer: ElapsedTime
    private var maxAngVelocity = 0.0
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        val drive = SampleMecanumDrive(hardwareMap)
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER)
        telemetry = MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry())
        telemetry.addLine("Your bot will turn at full speed for " + RUNTIME + " seconds.")
        telemetry.addLine("Please ensure you have enough space cleared.")
        telemetry.addLine("")
        telemetry.addLine("Press start when ready.")
        telemetry.update()
        waitForStart()
        telemetry.clearAll()
        telemetry.update()
        drive.setDrivePower(Pose2d(0.0, 0.0, 1.0))
        timer = ElapsedTime()
        while (!isStopRequested && timer.seconds() < RUNTIME) {
            drive.updatePoseEstimate()
            val poseVelo: Pose2d = Objects.requireNonNull(drive.poseVelocity, "poseVelocity() must not be null. Ensure that the getWheelVelocities() method has been overridden in your localizer.")!!
            maxAngVelocity = Math.max(poseVelo.heading, maxAngVelocity)
        }
        drive.setDrivePower(Pose2d())
        telemetry.addData("Max Angular Velocity (rad)", maxAngVelocity)
        telemetry.addData("Max Angular Velocity (deg)", Math.toDegrees(maxAngVelocity))
        telemetry.update()
        while (!isStopRequested()) idle()
    }

    companion object {
        var RUNTIME = 4.0
    }
}