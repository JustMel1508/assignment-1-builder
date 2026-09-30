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

The project applies Clean Code principles such as small functions, descriptive naming, minimizing function arguments, DRY, single responsibility and clear error handling.

### Example 1 — Long Constructor vs Builder API

BEFORE:

The initial implementation uses the constructor from LegacySmartMirror:

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

What was wrong?

The constructor contains many arguments. The boolean values are especially difficult to understand because the Client cannot immediately see which feature each true or false value represents.

Clean Code principles:

- Minimize Function Arguments
- Descriptive Naming

AFTER:

The final implementation uses Builder:

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

Why is it better?

Only the required values are passed to the Builder constructor. Optional configuration is expressed using descriptive methods such as withLidar() and withARLayer(). The Client code is easier to read and less likely to contain parameter-order mistakes.

### Example 2 — No Validation vs Small Validation Functions

BEFORE:

LegacySmartMirror accepts the constructor values directly:

    this.modelName = modelName;
    this.lidarEnabled = lidarEnabled;
    this.arLayerEnabled = arLayerEnabled;
    this.speakerVolume = speakerVolume;
    this.brightness = brightness;

There is no check that the values create a valid configuration.

For example, the old implementation could accept AR without LiDAR or brightness greater than 100.

What was wrong?

Invalid objects could be created because construction and validation were not controlled.

Clean Code principles:

- Clear Error Handling
- Small Functions
- Single Responsibility

AFTER:

SmartMirror.Builder calls:

    private void validate() {
        validateModelName();
        validateSpeakerVolume();
        validateBrightness();
        validateARCompatibility();
        validateAccessibilityCompatibility();
    }

Each rule is implemented in its own method.

Example:

    private void validateARCompatibility() {
        if (arLayerEnabled && !lidarEnabled) {
            throw new IllegalArgumentException(
                    "AR Layer requires LiDAR/depth sensing."
            );
        }
    }

Why is it better?

Each function has one clear responsibility. Validation rules are easy to find, understand and modify. Invalid SmartMirror objects are rejected before the Product is created.

### Example 3 — Manual Configuration vs Reusable Director Presets

BEFORE:

A SmartMirror can be configured manually in the Client:

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

If standard configurations were written manually every time, the same Builder sequences would be repeated in the Client code.

What was wrong?

Reusable configuration knowledge would be duplicated across the application.

Clean Code principle:

- DRY — Don't Repeat Yourself

AFTER:

Standard configurations are stored in SmartMirrorDirector:

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

The Client can simply use:

    SmartMirror premiumMirror = director.createPremiumMirror();

Why is it better?

The PREMIUM construction sequence is defined in one place. The Client does not need to know every construction step, and the same preset can be reused without duplicating code.

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

    MANUAL BUILDER 
    SmartMirror{modelName='MIRR-AI Custom', displayType=OLED_4K, ...}

    BASIC PRESET 
    SmartMirror{modelName='MIRR-AI Basic', displayType=OLED_HD, ...}

    SMART PRESET 
    SmartMirror{modelName='MIRR-AI Smart', displayType=OLED_4K, ...}

    PREMIUM PRESET 
    SmartMirror{modelName='MIRR-AI Pro', displayType=OLED_4K, ...}

    Premium MIRR-AI created successfully 🍌

    INVALID CONFIGURATION 
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

<https://github.com/JustMel1508/assignment-1-builder>