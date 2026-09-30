# Assignment 1 — Builder Pattern

## 1. Problem Description

MIRR-AI is a smart interactive mirror designed to help users choose outfits and manage their daily routine more efficiently.

The system can include several hardware and software components such as:
- RGB camera
- LiDAR / depth sensors
- 4K OLED display
- AR clothing try-on
- voice assistant
- motion control
- weather integration
- digital wardrobe
- accessibility features

Because MIRR-AI can be configured in many different ways, creating the object with one large constructor makes the code difficult to read and maintain.

The Builder Pattern is used to construct SmartMirror objects step by step and make the configuration more understandable.

## 2. Individual Variant

Domain: Smart Mirror System / MIRR-AI

Constraint: AR Try-On can only be enabled when LiDAR/depth sensing is enabled.

Required Preset: PREMIUM

## 3. Initial Constructor-Based Solution

Before using Builder, SmartMirror can be created using a large constructor.

Example:

    LegacySmartMirror mirror = new LegacySmartMirror(
            "MIRR-AI Pro",
            DisplayType.OLED_4K,
            "4K",
            OperatingMode.PREMIUM,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            80,
            90
    );

This approach works, but it causes several design problems.

## 4. Problems With Initial Design

### Problem 1 — Unclear Boolean Parameters

The constructor contains many boolean values.

For example:

    true, true, false, true

It is difficult to understand what each value means without checking the constructor declaration.

### Problem 2 — Parameter Order

The constructor has many parameters, so it is easy to place a value in the wrong position.

For example, speaker volume and brightness can be accidentally swapped.

### Problem 3 — Difficult Extension

If a new feature is added to MIRR-AI, the constructor must be changed.

All existing constructor calls may also need modification.

### Problem 4 — Difficult Validation

Some properties depend on other properties.

For example, AR Layer requires LiDAR.

With a large constructor, this validation becomes harder to organize and maintain.

## 5. Builder Solution

The final implementation uses the Builder Pattern.

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
            .withAccessibilityMode()
            .speakerVolume(80)
            .brightness(90)
            .build();

This version is easier to read because every optional feature has a meaningful method name.

## 6. Builder Pattern Participants

### Product

Class: SmartMirror

Responsibility:
Represents the final configured smart mirror.

The Product is immutable.

### Builder

Class: SmartMirror.Builder

Responsibility:
Constructs SmartMirror step by step.

The Builder also validates the configuration before creating the Product.

### Client

Class: Main

Responsibility:
Creates and uses SmartMirror objects.

### Director

Class: SmartMirrorDirector

Responsibility:
Creates reusable preset configurations such as BASIC, SMART and PREMIUM.

## 7. Required and Optional Properties

| Type | Property |
|---|---|
| Required | modelName |
| Required | displayType |
| Required | cameraResolution |
| Required | operatingMode |
| Optional | lidarEnabled |
| Optional | arLayerEnabled |
| Optional | voiceAssistantEnabled |
| Optional | motionControlEnabled |
| Optional | weatherIntegrationEnabled |
| Optional | digitalWardrobeEnabled |
| Optional | accessibilityModeEnabled |
| Optional | speakerVolume |
| Optional | brightness |

The project uses several data types:
- String
- boolean
- int
- enum

The project also uses SensorConfig as a value object.

## 8. Default Values

The Builder provides default values for optional properties.

Default feature values:

- LiDAR: false
- AR Layer: false
- Voice Assistant: false
- Motion Control: false
- Weather Integration: false
- Digital Wardrobe: false
- Accessibility Mode: false

Default speaker volume:

    50

Default brightness:

    70

These defaults allow simple SmartMirror configurations without setting every optional property manually.

## 9. Validation Rules

The Builder does not allow invalid SmartMirror objects to be created.

### Single-Field Validation

#### Rule 1

Model name cannot be null, empty or blank.

Error message:

    Model name cannot be blank.

#### Rule 2

Speaker volume must be between 0 and 100.

Error message:

    Speaker volume must be between 0 and 100.

#### Rule 3

Brightness must be between 0 and 100.

Error message:

    Brightness must be between 0 and 100.

### Cross-Field Validation

#### Rule 1 — AR and LiDAR

If AR Layer is enabled, LiDAR must also be enabled.

Invalid example:

    .withARLayer()
    .build();

Error:

    AR Layer requires LiDAR/depth sensing.

#### Rule 2 — Accessibility

If accessibility mode is enabled, at least one hands-free control option must be enabled.

The required options are:
- Voice Assistant
  or
- Motion Control

Error:

    Accessibility mode requires voice assistant or motion control.

## 10. Preset Configurations

The project contains three reusable configurations.

### BASIC

The BASIC configuration contains:
- OLED HD display
- 1080p camera
- weather integration
- speaker volume 40
- brightness 60

It does not use AR or LiDAR.

### SMART

The SMART configuration contains:
- 4K OLED display
- 4K camera
- voice assistant
- motion control
- weather integration
- digital wardrobe
- speaker volume 60
- brightness 75

### PREMIUM

The PREMIUM configuration contains:
- 4K OLED display
- 4K camera
- LiDAR
- AR Layer
- voice assistant
- motion control
- weather integration
- digital wardrobe
- accessibility mode
- speaker volume 80
- brightness 90

The PREMIUM configuration satisfies the AR/LiDAR compatibility rule.

## 11. UML Diagram

The UML diagram source is stored in:

    docs/builder-uml.puml

The UML contains:
- SmartMirror
- SmartMirror.Builder
- SmartMirrorDirector
- Main
- SensorConfig
- DisplayType
- OperatingMode

## 12. Builder Traceability Table

| Builder Role | Project Class | Responsibility |
|---|---|---|
| Product | SmartMirror | Represents the final immutable SmartMirror configuration |
| Builder | SmartMirror.Builder | Constructs and validates SmartMirror step by step |
| Client | Main | Creates and uses SmartMirror objects |
| Director | SmartMirrorDirector | Creates reusable preset configurations |

## 13. Clean Code Before -> After

### Example 1 — Validation

BEFORE:

A possible implementation could contain one large validation method with all validation rules.

Example:

    private void validate() {
        if (...) { ... }
        if (...) { ... }
        if (...) { ... }
        if (...) { ... }
        if (...) { ... }
    }

Problem:
The method would have several responsibilities and would become difficult to read.

AFTER:

    validateModelName();
    validateSpeakerVolume();
    validateBrightness();
    validateARCompatibility();
    validateAccessibilityCompatibility();

Clean Code Principle:
Small Functions and Single Responsibility.

Why it is better:
Each method checks one specific rule, so the code is easier to understand and modify.

### Example 2 — Domain-Oriented Method Names

BEFORE:

    setLidar(true);

Problem:
The method exposes a low-level boolean configuration.

AFTER:

    withLidar();

Clean Code Principle:
Descriptive Naming.

Why it is better:
The method clearly describes what feature is being added to the mirror.

### Example 3 — Preset Duplication

BEFORE:

The same Builder chains could be written repeatedly in Main.

Problem:
The same construction logic would be duplicated.

AFTER:

    director.createBasicMirror();
    director.createSmartMirror();
    director.createPremiumMirror();

Clean Code Principle:
DRY — Don't Repeat Yourself.

Why it is better:
Preset configuration is stored in one place and can be reused by the Client.

## 14. Design Decision

Decision:
Validation is mainly placed inside SmartMirror.Builder.

Alternative:
Validation could be placed inside the SmartMirror constructor.

Reasoning:
The Builder controls the construction process.

It can check the configuration before creating the final Product.

This keeps construction-related validation in one place and prevents invalid SmartMirror objects from being created.

The Product remains immutable and simple.

## 15. Automated Testing

The project uses JUnit 5.

A total of 10 automated tests are implemented.

### Valid Construction Tests

1. createBasicMirrorSuccessfully()
2. createSmartMirrorSuccessfully()
3. createPremiumMirrorSuccessfully()

### Invalid Construction Tests

4. rejectBlankModelName()
5. rejectInvalidBrightness()
6. rejectInvalidSpeakerVolume()

### Boundary Tests

7. brightnessZeroIsValid()
8. brightnessHundredIsValid()

### Individual Constraint Test

9. arLayerWithoutLidarFails()

This test verifies that AR cannot be enabled without LiDAR.

### Builder Reuse Test

10. builderReuseDoesNotModifyPreviouslyBuiltProduct()

This test verifies that changing the Builder after building one object does not modify the previously created SmartMirror.

## 16. Builder Reuse and Product Independence

SmartMirror is immutable.

Its fields are private and final.

When build() is called, a new SmartMirror object is created.

Example:

    SmartMirror.Builder builder = new SmartMirror.Builder(
            "Reusable Builder Mirror",
            DisplayType.OLED_4K,
            "4K",
            OperatingMode.SMART
    );

    SmartMirror firstMirror = builder.build();

    builder.withLidar();

    SmartMirror secondMirror = builder.build();

The first object remains unchanged.

This demonstrates Product independence.

## 17. Sample Program Output

Example output:

    === MANUAL BUILDER ===
    SmartMirror{modelName='MIRR-AI Custom', displayType=OLED_4K, ...}

    === BASIC PRESET ===
    SmartMirror{modelName='MIRR-AI Basic', displayType=OLED_HD, ...}

    === SMART PRESET ===
    SmartMirror{modelName='MIRR-AI Smart', displayType=OLED_4K, ...}

    === PREMIUM PRESET ===
    SmartMirror{modelName='MIRR-AI Pro', displayType=OLED_4K, ...}

    Premium MIRR-AI created successfully 🍌

    === INVALID CONFIGURATION ===
    Error: AR Layer requires LiDAR/depth sensing.

## 18. Git Development History

Suggested meaningful Git commits:

1. initial MIRR-AI domain model
2. add constructor-based SmartMirror implementation
3. refactor SmartMirror using Builder pattern
4. add validation rules and MIRR-AI constraints
5. add SmartMirror preset configurations
6. add JUnit tests and Clean Code refactoring

These commits demonstrate the development process instead of only showing the final implementation.

## 19. Conclusion

The Builder Pattern is suitable for MIRR-AI because SmartMirror contains many required and optional configuration parameters.

Builder improves readability, supports method chaining, provides default values and centralizes validation.

SmartMirrorDirector provides reusable preset configurations.

The final Product is immutable and safe to use.

The project also demonstrates Clean Code principles, automated testing, UML modeling and Builder reuse.

## 20. GitHub Repository

GitHub Repository:

<ADD GITHUB LINK HERE>