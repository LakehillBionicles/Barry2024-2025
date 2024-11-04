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
        Pose2d startPose = new Pose2d(-20, -60, Math.toRadians(-90));
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
                                .lineToLinearHeading(new Pose2d(0, -34, Math.toRadians(-90)))//depositing specimen on bar//


                                .forward(5)//pulling away from bar//
                                .turn(Math.toRadians(-90))//turning to specimens//
                                .forward(40)//driving to specimens//
                                .lineToLinearHeading(new Pose2d(-44,-34,Math.toRadians(120)))//picking up first specimen//
                        //where the arm code will go?//
                                .lineToLinearHeading(new Pose2d(-58,-54, Math.toRadians(-125)))//depositing first specimen//
                        //more arm code//
                                .lineToLinearHeading(new Pose2d(-50,-34,Math.toRadians(120)))//picking up second specimen//
                        //more arm code//
                                .lineToLinearHeading(new Pose2d(-58, -54,Math.toRadians(-125)))//scoring second specimen//
                        //more arm code//
                                .lineToLinearHeading(new Pose2d(-34,0,Math.toRadians(180)))//low level ascent//
                                .back(10)//low level ascent completed//
                        //the previous is the code for scoring two specimens on the left side of the field, and then achieving a low level ascent//
                        //the following will be the code for pushing two specimens into the team area, and then low level ascending//










                        .build());
        // Set field image5
        Image img = null;

        try { img = ImageIO.read(new File("C:\\Users\\raduu\\Downloads\\field.png")); }
        catch(IOException e) {}
        meepMeep.setBackground(img)
                .setDarkMode(true)
                // Background opacity from 0-1
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }

}