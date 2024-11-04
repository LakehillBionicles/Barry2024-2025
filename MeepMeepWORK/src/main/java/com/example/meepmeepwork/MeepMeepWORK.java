package com.example.meepmeepwork;

import static java.lang.Math.PI;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.Constraints;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.DriveTrainType;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MeepMeepWORK {
    public static void main(String[] args) {
        // Declare a MeepMeep instance
        // With a field size of 800 pixels
        MeepMeep meepMeep = new MeepMeep(600);
        Pose2d startPose = new Pose2d(10, -62, Math.toRadians(-90));
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
                        .back(24)
                        .strafeRight(3)
                        .back(6.5)
                        .forward(16)
                        .splineToLinearHeading((startPose).plus(new Pose2d(23, 23, Math.toRadians(0))), Math.toRadians(0))
                        .splineToLinearHeading((startPose).plus(new Pose2d(23, 35, Math.toRadians(0))), Math.toRadians(90))
                        .back(1)
                        .splineToLinearHeading((startPose).plus(new Pose2d(26, 40, Math.toRadians(0))), Math.toRadians(90))
                        .splineToLinearHeading((startPose).plus(new Pose2d(34, 48, Math.toRadians(0))), Math.toRadians(-90))
                        .turn(Math.toRadians(90))
                        .strafeRight(5)
                        .splineToLinearHeading((startPose).plus(new Pose2d(34, 15, Math.toRadians(90))), Math.toRadians(90))
                        .back(5)
                        .strafeLeft(43)
                        .splineToLinearHeading((startPose).plus(new Pose2d(44, 48, Math.toRadians(90))), Math.toRadians(-90))
                        .strafeRight(5)
                        .splineToLinearHeading((startPose).plus(new Pose2d(44, 15, Math.toRadians(90))), Math.toRadians(90))
                        .back(3)
                        .strafeLeft(43)
                        .splineToLinearHeading((startPose).plus(new Pose2d(38, 50, Math.toRadians(90))), Math.toRadians(-90))
                        .strafeRight(40)
                        .forward(5)
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