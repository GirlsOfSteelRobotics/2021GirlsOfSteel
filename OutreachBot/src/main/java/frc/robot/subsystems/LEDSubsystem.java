package frc.robot.subsystems;


import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDSubsystem extends SubsystemBase {
    private final AddressableLED m_led;
    private static final int NUMBER_LED = 60;
    private final AddressableLEDBuffer m_ledBuffer;
    private int m_rainbowFirstPixelHue;
    private int m_pixelCounter;
    private int m_loopCounter;
    private double m_fadeCounter;
    private double m_direction = 0.02;

    public LEDSubsystem() {
        m_led = new AddressableLED(9);

        // Reuse buffer
        // Default to a length of 60, start empty output
        // Length is expensive to set, so only set it once, then just update data
        m_ledBuffer = new AddressableLEDBuffer(NUMBER_LED);
        m_led.setLength(m_ledBuffer.getLength());

        // Set the data
        m_led.setData(m_ledBuffer);
        m_led.start();
    }

    public void rainbow() {
        // For every pixel
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
            // Calculate the hue - hue is easier for rainbows because the color
            // shape is a circle so only one value needs to precess
            final var hue = (m_rainbowFirstPixelHue + (i * 180 / m_ledBuffer.getLength())) % 180;
            // Set the value
            m_ledBuffer.setHSV(i, hue, 255, 128);
        }
        // Increase by to make the rainbow "move"
        m_rainbowFirstPixelHue += 3;
        // Check bounds
        m_rainbowFirstPixelHue %= 180;
    }

    public void gosLED() {
        generalLED(0, NUMBER_LED / 2, 255, 0, 0);
        generalLED(NUMBER_LED / 2, NUMBER_LED, 0, 0, 255);
    }

    //1st 4 blue, next 6 red, next 10 green, next 1 yellow, the rest purple
    public void generalLEDTest() {
        generalLED(0, 4, 0, 0, 255);
        generalLED(4, 10, 255, 0, 0);
        generalLED(10, 20, 0, 255, 0);
        generalLED(20, 21, 255, 255, 0);
        generalLED(21, NUMBER_LED, 153, 51, 255);
    }

    public void gosColorTest() {
        for (var i = 0; i < NUMBER_LED; i += 2) {
            m_ledBuffer.setRGB(i, 255, 0, 0);
        }
        for (var i = 1; i < NUMBER_LED; i += 2) {
            m_ledBuffer.setRGB(i, 255, 255, 255);
        }
    }

    public void singlePixel() {
        generalLED(m_pixelCounter, m_pixelCounter + 1, 255, 0, 0);
        if (m_loopCounter % 5 == 0) {
            m_pixelCounter++;
        }

        if (m_pixelCounter >= NUMBER_LED) {
            m_pixelCounter = 0;
        }
    }

    public void clear() {
        generalLED(0, NUMBER_LED, 0, 0, 0);
    }

    @SuppressWarnings("PMD")
    public void fade() {
        generalLED(0, NUMBER_LED, 0, (int) (255 * m_fadeCounter), 0);
        m_fadeCounter += m_direction;
        if (m_fadeCounter >= 1) {
            m_direction *= -1.0;
        }
        if (m_fadeCounter <= 0) {
            m_direction *= -1.0;
        }

    }

    public void limelightCheck(boolean seeLimelight) {
        if (seeLimelight) {
            generalLED(0, 5, 0, 255, 0);
        }
    }
    private static final double MAX_LIMELIGHT_DEGREE = 10.0;
    private static final int NUM_LIMELIGHT_LED = 20;
    private static final int MIDDLE_LED = NUM_LIMELIGHT_LED / 2;

    public void shooterError(double limelightError) {
        double ledProportion = Math.abs(limelightError / MAX_LIMELIGHT_DEGREE);
        int ledOn = (int) (ledProportion * MIDDLE_LED);
        if (ledOn > MIDDLE_LED) {
            ledOn = MIDDLE_LED;
        }
        int differenceInLED = MIDDLE_LED;
        if (limelightError < 0) {
            differenceInLED = MIDDLE_LED - ledOn;
        }
        if (limelightError > 0) {
            differenceInLED = MIDDLE_LED + ledOn;
        }

        if (differenceInLED < MIDDLE_LED) {
            generalLED(differenceInLED, MIDDLE_LED, 255, 0, 0);
        }
        if (differenceInLED > MIDDLE_LED) {
            generalLED(MIDDLE_LED, differenceInLED, 255, 0, 0);
        }

        System.out.println(ledProportion + "," + ledOn + "," + MIDDLE_LED + "," + differenceInLED);

    }

    @Override
    public void periodic() {
        m_loopCounter++;
        clear();
        shooterError(15);
        m_led.setData(m_ledBuffer);
    }

    public void generalLED(int startLED, int endLED, int redColor, int greenColor, int blueColor) {
        for (var i = startLED; i < endLED; i++) {
            m_ledBuffer.setRGB(i, redColor, greenColor, blueColor);
        }

    }
}


