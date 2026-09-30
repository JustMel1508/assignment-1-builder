# MIRR-AI Builder Pattern

## Project Overview

MIRR-AI is a smart interactive mirror that helps users choose outfits.

The system can include:
- RGB camera
- LiDAR / depth sensors
- 4K OLED display
- AR try-on layer
- voice assistant
- motion control
- weather integration
- digital wardrobe
- accessibility features

This project demonstrates the Builder Design Pattern in Java.

## Why Builder Pattern?

MIRR-AI has many required and optional configuration parameters.

Using one large constructor makes the code difficult to read and maintain.

The Builder Pattern allows the object to be created step by step using readable method chaining.

Example:

    SmartMirror mirror = new SmartMirror.Builder(
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
            .brightness(90)
            .speakerVolume(80)
            .build();

## Product

The main Product is SmartMirror.

The final SmartMirror object is immutable.

Its fields are private and final, and it does not contain setters.

## Required Properties

The Builder requires:

- modelName
- displayType
- cameraResolution
- operatingMode

## Optional Properties

Optional features include:

- LiDAR
- AR Layer
- Voice Assistant
- Motion Control
- Weather Integration
- Digital Wardrobe
- Accessibility Mode
- Speaker Volume
- Brightness

## Builder Participants

| Builder Role | Project Class | Responsibility |
|---|---|---|
| Product | SmartMirror | Represents the final configured smart mirror |
| Builder | SmartMirror.Builder | Builds and validates SmartMirror step by step |
| Client | Main | Creates and uses SmartMirror objects |
| Director | SmartMirrorDirector | Creates reusable preset configurations |

## Validation Rules

### Single-field validation

1. Model name cannot be blank.
2. Speaker volume must be between 0 and 100.
3. Brightness must be between 0 and 100.

### Cross-field validation

1. AR Layer requires LiDAR/depth sensing.
2. Accessibility mode requires voice assistant or motion control.

## Preset Configurations

### BASIC

A simple smart mirror with:
- camera
- display
- weather integration

### SMART

Includes:
- voice assistant
- motion control
- digital wardrobe
- weather integration

### PREMIUM

Includes:
- LiDAR
- AR Layer
- voice assistant
- motion control
- weather integration
- digital wardrobe
- accessibility mode

## UML

The UML diagram source is located at:

docs/builder-uml.puml

## Testing

The project uses JUnit 5.

The automated tests include:

- 3 valid construction tests
- 3 invalid construction tests
- 2 boundary tests
- 1 AR/LiDAR constraint test
- 1 Builder reuse/Product independence test

## How to Run

Open the project in IntelliJ IDEA.

Run:

Main.java

The application demonstrates:

- manual Builder creation
- BASIC preset
- SMART preset
- PREMIUM preset
- invalid configuration handling

## How to Run Tests

In IntelliJ IDEA, open:

SmartMirrorTest.java

and run all tests.

You can also use Maven:

    mvn test

## Git Development Plan

Suggested meaningful commits:

1. initial MIRR-AI domain model
2. add constructor-based SmartMirror implementation
3. refactor SmartMirror using Builder pattern
4. add validation rules and MIRR-AI constraints
5. add SmartMirror preset configurations
6. add JUnit tests and Clean Code refactoring

## Individual Variant

Domain: Smart Mirror System / MIRR-AI

Constraint: AR Try-On can only be enabled when LiDAR/depth sensing is enabled.

Required Preset: PREMIUM