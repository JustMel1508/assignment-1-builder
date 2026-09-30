package com.aisha;

public final class SmartMirror {

    private final String modelName;
    private final DisplayType displayType;
    private final OperatingMode operatingMode;
    private final SensorConfig sensorConfig;

    private final boolean arLayerEnabled;
    private final boolean voiceAssistantEnabled;
    private final boolean weatherIntegrationEnabled;
    private final boolean digitalWardrobeEnabled;
    private final boolean accessibilityModeEnabled;

    private final int speakerVolume;
    private final int brightness;

    private SmartMirror(Builder builder) {
        this.modelName = builder.modelName;
        this.displayType = builder.displayType;
        this.operatingMode = builder.operatingMode;

        this.sensorConfig = new SensorConfig(
                builder.cameraResolution,
                builder.lidarEnabled,
                builder.motionControlEnabled
        );

        this.arLayerEnabled = builder.arLayerEnabled;
        this.voiceAssistantEnabled = builder.voiceAssistantEnabled;
        this.weatherIntegrationEnabled = builder.weatherIntegrationEnabled;
        this.digitalWardrobeEnabled = builder.digitalWardrobeEnabled;
        this.accessibilityModeEnabled = builder.accessibilityModeEnabled;

        this.speakerVolume = builder.speakerVolume;
        this.brightness = builder.brightness;
    }

    public String getModelName() {
        return modelName;
    }

    public DisplayType getDisplayType() {
        return displayType;
    }

    public OperatingMode getOperatingMode() {
        return operatingMode;
    }

    public SensorConfig getSensorConfig() {
        return sensorConfig;
    }

    public boolean isArLayerEnabled() {
        return arLayerEnabled;
    }

    public boolean isVoiceAssistantEnabled() {
        return voiceAssistantEnabled;
    }

    public boolean isWeatherIntegrationEnabled() {
        return weatherIntegrationEnabled;
    }

    public boolean isDigitalWardrobeEnabled() {
        return digitalWardrobeEnabled;
    }

    public boolean isAccessibilityModeEnabled() {
        return accessibilityModeEnabled;
    }

    public int getSpeakerVolume() {
        return speakerVolume;
    }

    public int getBrightness() {
        return brightness;
    }

    @Override
    public String toString() {
        return "SmartMirror{" +
                "modelName='" + modelName + '\'' +
                ", displayType=" + displayType +
                ", operatingMode=" + operatingMode +
                ", sensorConfig=" + sensorConfig +
                ", arLayerEnabled=" + arLayerEnabled +
                ", voiceAssistantEnabled=" + voiceAssistantEnabled +
                ", weatherIntegrationEnabled=" + weatherIntegrationEnabled +
                ", digitalWardrobeEnabled=" + digitalWardrobeEnabled +
                ", accessibilityModeEnabled=" + accessibilityModeEnabled +
                ", speakerVolume=" + speakerVolume +
                ", brightness=" + brightness +
                '}';
    }

    public static class Builder {

        private final String modelName;
        private final DisplayType displayType;
        private final String cameraResolution;
        private final OperatingMode operatingMode;

        private boolean lidarEnabled = false;
        private boolean arLayerEnabled = false;
        private boolean voiceAssistantEnabled = false;
        private boolean motionControlEnabled = false;
        private boolean weatherIntegrationEnabled = false;
        private boolean digitalWardrobeEnabled = false;
        private boolean accessibilityModeEnabled = false;

        private int speakerVolume = 50;
        private int brightness = 70;

        public Builder(String modelName,
                       DisplayType displayType,
                       String cameraResolution,
                       OperatingMode operatingMode) {

            this.modelName = modelName;
            this.displayType = displayType;
            this.cameraResolution = cameraResolution;
            this.operatingMode = operatingMode;
        }

        public Builder withLidar() {
            this.lidarEnabled = true;
            return this;
        }

        public Builder withARLayer() {
            this.arLayerEnabled = true;
            return this;
        }

        public Builder withVoiceAssistant() {
            this.voiceAssistantEnabled = true;
            return this;
        }

        public Builder withMotionControl() {
            this.motionControlEnabled = true;
            return this;
        }

        public Builder withWeatherIntegration() {
            this.weatherIntegrationEnabled = true;
            return this;
        }

        public Builder withDigitalWardrobe() {
            this.digitalWardrobeEnabled = true;
            return this;
        }

        public Builder withAccessibilityMode() {
            this.accessibilityModeEnabled = true;
            return this;
        }

        public Builder speakerVolume(int speakerVolume) {
            this.speakerVolume = speakerVolume;
            return this;
        }

        public Builder brightness(int brightness) {
            this.brightness = brightness;
            return this;
        }

        public SmartMirror build() {
            validate();
            return new SmartMirror(this);
        }

        private void validate() {
            validateModelName();
            validateSpeakerVolume();
            validateBrightness();
            validateARCompatibility();
            validateAccessibilityCompatibility();
        }

        private void validateModelName() {
            if (modelName == null || modelName.isBlank()) {
                throw new IllegalArgumentException(
                        "Model name cannot be blank."
                );
            }
        }

        private void validateSpeakerVolume() {
            if (speakerVolume < 0 || speakerVolume > 100) {
                throw new IllegalArgumentException(
                        "Speaker volume must be between 0 and 100."
                );
            }
        }

        private void validateBrightness() {
            if (brightness < 0 || brightness > 100) {
                throw new IllegalArgumentException(
                        "Brightness must be between 0 and 100."
                );
            }
        }

        private void validateARCompatibility() {
            if (arLayerEnabled && !lidarEnabled) {
                throw new IllegalArgumentException(
                        "AR Layer requires LiDAR/depth sensing."
                );
            }
        }

        private void validateAccessibilityCompatibility() {
            if ((accessibilityModeEnabled
                    || operatingMode == OperatingMode.ACCESSIBILITY)
                    && !voiceAssistantEnabled
                    && !motionControlEnabled) {

                throw new IllegalArgumentException(
                        "Accessibility mode requires voice assistant or motion control."
                );
            }
        }
    }
}