package com.aisha;

public class SmartMirrorDirector {

    public SmartMirror createBasicMirror() {
        return new SmartMirror.Builder(
                "MIRR-AI Basic",
                DisplayType.OLED_HD,
                "1080p",
                OperatingMode.BASIC
        )
                .withWeatherIntegration()
                .speakerVolume(40)
                .brightness(60)
                .build();
    }

    public SmartMirror createSmartMirror() {
        return new SmartMirror.Builder(
                "MIRR-AI Smart",
                DisplayType.OLED_4K,
                "4K",
                OperatingMode.SMART
        )
                .withVoiceAssistant()
                .withMotionControl()
                .withWeatherIntegration()
                .withDigitalWardrobe()
                .speakerVolume(60)
                .brightness(75)
                .build();
    }

    public SmartMirror createPremiumMirror() {
        return new SmartMirror.Builder(
                "MIRR-AI Pro",
                DisplayType.OLED_4K,
                "4K",
                OperatingMode.PREMIUM
        )
                .withLidar()
                .withARLayer()
                .withVoiceAssistant()
                .withMotionControl()
                .withWeatherIntegration()
                .withDigitalWardrobe()
                .withAccessibilityMode()
                .speakerVolume(80)
                .brightness(90)
                .build();
    }
}