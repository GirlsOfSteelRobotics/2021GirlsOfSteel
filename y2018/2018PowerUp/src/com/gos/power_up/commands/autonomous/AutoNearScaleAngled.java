package com.gos.power_up.commands.autonomous;

import com.gos.power_up.GameData;
import com.gos.power_up.commands.CollectPosition;
import com.gos.power_up.commands.CollectorStop;
import com.gos.power_up.commands.DriveByMotionMagic;
import com.gos.power_up.commands.LiftHold;
import com.gos.power_up.commands.LiftToScale;
import com.gos.power_up.commands.ReleaseFast;
import com.gos.power_up.commands.TimeDelay;
import com.gos.power_up.commands.WristHold;
import com.gos.power_up.commands.WristToShoot;
import com.gos.power_up.subsystems.Chassis;
import com.gos.power_up.subsystems.Collector;
import com.gos.power_up.subsystems.Lift;
import com.gos.power_up.subsystems.Wrist;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoNearScaleAngled extends CommandGroup {


    //distance constants
    private static final double DISTANCE_FORWARD_1 = 235.0;
    private static final double DISTANCE_TURN_1 = 25;
    private static final double BACK_UP = -30.0;


    public AutoNearScaleAngled(Chassis chassis, Lift lift, Wrist wrist, Collector collector, GameData.FieldSide robotPosition) {
        System.out.println("AutoFarScale starting");

        //moves robot forward
        addParallel(new DriveByMotionMagic(chassis, DISTANCE_FORWARD_1, 0));
        addSequential(new TimeDelay(2.0));

        //gets lift & wrist into position
        addSequential(new WristToShoot(wrist));
        addSequential(new LiftToScale(lift));
        addParallel(new WristHold(wrist));
        addParallel(new LiftHold(lift));
        addSequential(new TimeDelay(3.0));

        //turn
        if (robotPosition == GameData.FieldSide.left) {
            addSequential(new DriveByMotionMagic(chassis, DISTANCE_TURN_1, -30));
        } else {
            addSequential(new DriveByMotionMagic(chassis, DISTANCE_TURN_1, 30));
        }

        //release cube and back up
        addParallel(new ReleaseFast(collector, 0.5));
        addSequential(new TimeDelay(1.0));
        addSequential(new DriveByMotionMagic(chassis, BACK_UP, 0));

        //puts lift down and stops collector
        addSequential(new CollectPosition(lift, wrist));
        addSequential(new CollectorStop(collector));
        addParallel(new WristHold(wrist));
        addParallel(new LiftHold(lift));
    }
}
