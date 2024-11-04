package com.example.meepmeepwork;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.roadrunner.Constraints;
import com.noahbres.meepmeep.roadrunner.DriveTrainType;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MeepMeepBracken {

    public static void main(String[] args) {
        // Declare a MeepMeep instance
        // With a field size of 800 pixels
        MeepMeep meepMeep = new MeepMeep(600);
        Pose2d startPose = new Pose2d(-9, -62, Math.toRadians(-90));
        ColorSchemeBlueDark colorSchemeBlueDark = new ColorSchemeBlueDark();
        Constraints myBotConstraints = new Constraints(42.5, 62.890756998077265, 3.358942, Math.toRadians(180), 15.8);
        RoadRunnerBotEntity myBot = new RoadRunnerBotEntity(meepMeep,
                myBotConstraints,
                16,
                18,
                startPose,
                colorSchemeBlueDark,
                1,
                DriveTrainType.MECANUM,
                true);
        // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
        // Option: Set theme. Default = ColorSchemeRedDark()
        myBot.followTrajectorySequence(myBot.getDrive()
                .trajectorySequenceBuilder(startPose)
                .back(25)
                .back(2)
                .forward(4)
                .turn(Math.toRadians(-90) )
                .waitSeconds(0.2)
                .splineToLinearHeading((startPose).plus(new Pose2d(-29, 36, Math.toRadians(-90))), Math.toRadians(90))
                .strafeLeft(10)
                .turn(Math.toRadians(45))
                .forward(15)
                .back(15)
                .turn(Math.toRadians(-45))
                .strafeRight(27)
                .back(10)

                .build());
        // Set field image
        Image img = null;
        try { img = ImageIO.read(new File("/Users/Austin/Downloads/intoTheDeepImage.png")); }
        catch(IOException e) {}
        meepMeep.setBackground(img)
                .setDarkMode(true)
                // Background opacity from 0-1
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
