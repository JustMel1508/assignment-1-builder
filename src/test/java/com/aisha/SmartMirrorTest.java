package com.aisha;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartMirrorTest {

    @Test
    void createBasicMirrorSuccessfully() {
        SmartMirrorDirector director = new SmartMirrorDirector();

        SmartMirror mirror = director.createBasicMirror();

        assertEquals("MIRR-AI Basic", mirror.getModelName());
        assertEquals(OperatingMode.BASIC, mirror.getOperatingMode());
        assertTrue(mirror.isWeatherIntegrationEnabled());
        assertFalse(mirror.isArLayerEnabled());
    }

    @Test
    void createSmartMirrorSuccessfully() {
        SmartMirrorDirector director = new SmartMirrorDirector();

        SmartMirror mirror = director.createSmartMirror();

        assertEquals("MIRR-AI Smart", mirror.getModelName());
        assertEquals(OperatingMode.SMART, mirror.getOperatingMode());
        assertTrue(mirror.isVoiceAssistantEnabled());
        assertTrue(mirror.isDigitalWardrobeEnabled());
    }

    @Test
    void createPremiumMirrorSuccessfully() {
        SmartMirrorDirector director = new SmartMirrorDirector();

        SmartMirror mirror = director.createPremiumMirror();

        assertEquals("MIRR-AI Pro", mirror.getModelName());
        assertEquals(OperatingMode.PREMIUM, mirror.getOperatingMode());
        assertTrue(mirror.getSensorConfig().isLidarEnabled());
        assertTrue(mirror.isArLayerEnabled());
        assertTrue(mirror.isAccessibilityModeEnabled());
    }

    @Test
    void rejectBlankModelName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new SmartMirror.Builder(
                        " ",
                        DisplayType.OLED_HD,
                        "1080p",
                        OperatingMode.BASIC
                ).build()
        );
    }

    @Test
    void rejectInvalidBrightness() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new SmartMirror.Builder(
                        "Test Mirror",
                        DisplayType.OLED_HD,
                        "1080p",
                        OperatingMode.BASIC
                )
                        .brightness(101)
                        .build()
        );
    }

    @Test
    void rejectInvalidSpeakerVolume() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new SmartMirror.Builder(
                        "Test Mirror",
                        DisplayType.OLED_HD,
                        "1080p",
                        OperatingMode.BASIC
                )
                        .speakerVolume(-1)
                        .build()
        );
    }

    @Test
    void brightnessZeroIsValid() {
        SmartMirror mirror = new SmartMirror.Builder(
                "Test Mirror",
                DisplayType.OLED_HD,
                "1080p",
                OperatingMode.BASIC
        )
                .brightness(0)
                .build();

        assertEquals(0, mirror.getBrightness());
    }

    @Test
    void brightnessHundredIsValid() {
        SmartMirror mirror = new SmartMirror.Builder(
                "Test Mirror",
                DisplayType.OLED_HD,
                "1080p",
                OperatingMode.BASIC
        )
                .brightness(100)
                .build();

        assertEquals(100, mirror.getBrightness());
    }

    @Test
    void arLayerWithoutLidarFails() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new SmartMirror.Builder(
                        "AR Test Mirror",
                        DisplayType.OLED_4K,
                        "4K",
                        OperatingMode.PREMIUM
                )
                        .withARLayer()
                        .build()
        );
    }

    @Test
    void builderReuseDoesNotModifyPreviouslyBuiltProduct() {
        SmartMirror.Builder builder = new SmartMirror.Builder(
                "Reusable Builder Mirror",
                DisplayType.OLED_4K,
                "4K",
                OperatingMode.SMART
        );

        SmartMirror firstMirror = builder.build();

        builder.withLidar();

        SmartMirror secondMirror = builder.build();

        assertFalse(firstMirror.getSensorConfig().isLidarEnabled());
        assertTrue(secondMirror.getSensorConfig().isLidarEnabled());
        assertNotSame(firstMirror, secondMirror);
    }
}