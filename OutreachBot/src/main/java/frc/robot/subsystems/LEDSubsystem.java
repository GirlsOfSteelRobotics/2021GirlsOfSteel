package frc.robot.subsystems;


import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDSubsystem extends SubsystemBase {
    private final AddressableLED m_led;
    private static final int NUMBER_LED = 60;
    private final AddressableLEDBuffer m_ledBuffer;
    private int m_rainbowFirstPixelHue;

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
//    private void redLED() {
//        for (var i = 0; i < m_ledBuffer.getLength() / 2; i++) {
//            // Sets the specified LED to the RGB values for red
//            m_ledBuffer.setRGB(i, 255, 0, 0);
//        }
//
//        m_led.setData(m_ledBuffer);
//    }
//    private void redLED() {
//        generalLED(0, NUMBER_LED / 2, 255, 0, 0);
//    }
//    private void blueLED() {
//        for (var i = (NUMBER_LED / 2); i < m_ledBuffer.getLength(); i++) {
//            // Sets the specified LED to the RGB values for red
//            m_ledBuffer.setRGB(i, 0, 0, 255);
//        }
//
//        m_led.setData(m_ledBuffer);
//    }
//    private void blueLED() {
//        generalLED(NUMBER_LED / 2, NUMBER_LED, 0, 0, 255);
//
//    }
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
    public void GoSLED() {
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
        for (var i = 0; i < NUMBER_LED; i+=2){
            m_ledBuffer.setRGB(i, 255, 0, 0);
        }
        for (var i = 1; i <NUMBER_LED; i+=2){
            m_ledBuffer.setRGB(i,255,255,255);
        }
    }
    @Override
    public void periodic(){
        gosColorTest();
        m_led.setData(m_ledBuffer);
    }
    public void generalLED(int startLED, int endLED, int rColor, int gColor, int bColor) {
        for (var i = startLED; i < endLED; i++) {
            m_ledBuffer.setRGB(i, rColor, gColor, bColor);
        }

    }
}

