package com.aisha;

public final class SensorConfig {

    private final String cameraResolution;
    private final boolean lidarEnabled;
    private final boolean motionControlEnabled;

    public SensorConfig(String cameraResolution,
                        boolean lidarEnabled,
                        boolean motionControlEnabled) {
        this.cameraResolution = cameraResolution;
        this.lidarEnabled = lidarEnabled;
        this.motionControlEnabled = motionControlEnabled;
    }

    public String getCameraResolution() {
        return cameraResolution;
    }

    public boolean isLidarEnabled() {
        return lidarEnabled;
    }

    public boolean isMotionControlEnabled() {
        return motionControlEnabled;
    }

    @Override
    public String toString() {
        return "SensorConfig{" +
                "cameraResolution='" + cameraResolution + '\'' +
                ", lidarEnabled=" + lidarEnabled +
                ", motionControlEnabled=" + motionControlEnabled +
                '}';
    }
}