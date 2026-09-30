package com.aisha;

public class LegacySmartMirror {

    private final String modelName;
    private final DisplayType displayType;
    private final String cameraResolution;
    private final OperatingMode operatingMode;

    private final boolean lidarEnabled;
    private final boolean arLayerEnabled;
    private final boolean voiceAssistantEnabled;
    private final boolean motionControlEnabled;
    private final boolean weatherIntegrationEnabled;
    private final boolean digitalWardrobeEnabled;
    private final boolean accessibilityModeEnabled;

    private final int speakerVolume;
    private final int brightness;

    public LegacySmartMirror(
            String modelName,
            DisplayType displayType,
            String cameraResolution,
            OperatingMode operatingMode,
            boolean lidarEnabled,
            boolean arLayerEnabled,
            boolean voiceAssistantEnabled,
            boolean motionControlEnabled,
            boolean weatherIntegrationEnabled,
            boolean digitalWardrobeEnabled,
            boolean accessibilityModeEnabled,
            int speakerVolume,
            int brightness
    ) {
        this.modelName = modelName;
        this.displayType = displayType;
        this.cameraResolution = cameraResolution;
        this.operatingMode = operatingMode;
        this.lidarEnabled = lidarEnabled;
        this.arLayerEnabled = arLayerEnabled;
        this.voiceAssistantEnabled = voiceAssistantEnabled;
        this.motionControlEnabled = motionControlEnabled;
        this.weatherIntegrationEnabled = weatherIntegrationEnabled;
        this.digitalWardrobeEnabled = digitalWardrobeEnabled;
        this.accessibilityModeEnabled = accessibilityModeEnabled;
        this.speakerVolume = speakerVolume;
        this.brightness = brightness;
    }

    @Override
    public String toString() {
        return "LegacySmartMirror{" +
                "modelName='" + modelName + '\'' +
                ", displayType=" + displayType +
                ", cameraResolution='" + cameraResolution + '\'' +
                ", operatingMode=" + operatingMode +
                ", lidarEnabled=" + lidarEnabled +
                ", arLayerEnabled=" + arLayerEnabled +
                ", voiceAssistantEnabled=" + voiceAssistantEnabled +
                ", motionControlEnabled=" + motionControlEnabled +
                ", weatherIntegrationEnabled=" + weatherIntegrationEnabled +
                ", digitalWardrobeEnabled=" + digitalWardrobeEnabled +
                ", accessibilityModeEnabled=" + accessibilityModeEnabled +
                ", speakerVolume=" + speakerVolume +
                ", brightness=" + brightness +
                '}';
    }
}