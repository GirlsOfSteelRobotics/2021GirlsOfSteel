package frc.robot.subsystems;


import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDSubsystem extends SubsystemBase {
    private final AddressableLED m_led;
    private static final int NUMBER_LED = 60;
    private final AddressableLEDBuffer m_ledBuffer;

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
    private void redLED() {
        for (var i = 0; i < m_ledBuffer.getLength() / 2; i++) {
            // Sets the specified LED to the RGB values for red
            m_ledBuffer.setRGB(i, 255, 0, 0);
        }

        m_led.setData(m_ledBuffer);
    }
    private void blueLED() {
        for (var i = (NUMBER_LED / 2); i < m_ledBuffer.getLength(); i++) {
            // Sets the specified LED to the RGB values for red
            m_ledBuffer.setRGB(i, 0, 0, 255);
        }

        m_led.setData(m_ledBuffer);
    }
    public void GoSLED() {
        redLED();
        blueLED();
    }
}

