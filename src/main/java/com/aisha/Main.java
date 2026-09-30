package com.aisha;

public class Main {

    public static void main(String[] args) {

        SmartMirrorDirector director = new SmartMirrorDirector();

        System.out.println("MANUAL BUILDER ");

        SmartMirror manualMirror = new SmartMirror.Builder(
                "MIRR-AI Custom",
                DisplayType.OLED_4K,
                "4K",
                OperatingMode.SMART
        )
                .withVoiceAssistant()
                .withMotionControl()
                .withWeatherIntegration()
                .speakerVolume(65)
                .brightness(80)
                .build();

        System.out.println(manualMirror);

        System.out.println("\n BASIC PRESET ");

        SmartMirror basicMirror = director.createBasicMirror();
        System.out.println(basicMirror);

        System.out.println("\n SMART PRESET ");

        SmartMirror smartMirror = director.createSmartMirror();
        System.out.println(smartMirror);

        System.out.println("\n PREMIUM PRESET ");

        SmartMirror premiumMirror = director.createPremiumMirror();
        System.out.println(premiumMirror);

        System.out.println("Premium MIRR-AI created successfully 🍌");

        System.out.println("\n INVALID CONFIGURATION ");

        try {
            SmartMirror invalidMirror = new SmartMirror.Builder(
                    "Broken MIRR-AI",
                    DisplayType.OLED_4K,
                    "4K",
                    OperatingMode.PREMIUM
            )
                    .withARLayer()
                    .build();

            System.out.println(invalidMirror);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}