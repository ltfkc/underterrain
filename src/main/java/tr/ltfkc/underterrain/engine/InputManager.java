package tr.ltfkc.underterrain.engine;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class InputManager {

    private static boolean[] pressedKeys = new boolean[350];
    private static boolean[] pressedMouseButtons = new boolean[8];
    private static double mouseScrollX, mouseScrollY;
    private static double mouseX, mouseY, lastMouseX, lastMouseY;

    static void update() {
        mouseScrollX = 0;
        mouseScrollY = 0;
        lastMouseX = mouseX;
        lastMouseY = mouseY;
    }

    // KEYBOARD INPUT

    public static boolean isKeyPressed(int key) {
        return pressedKeys[key];
    }

    static void keyCallback(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_PRESS) {
            pressedKeys[key] = true;
        } else if (action == GLFW_RELEASE) {
            pressedKeys[key] = false;
        }
    }

    // MOUSE INPUT

    public static  boolean isMouseButtonPressed(int button) {
        return pressedMouseButtons[button];
    }

    public static double getMouseX() {
        return mouseX;
    }

    public static double getMouseY() {
        return mouseY;
    }

    public static float getMouseDX() {
        return (float) (mouseX - lastMouseX);
    }

    public static float getMouseDY() {
        return (float) (mouseY - lastMouseY);
    }

    public static float getMouseScrollX() {
        return (float) mouseScrollX;
    }

    public static float getMouseScrollY() {
        return (float) mouseScrollY;
    }

    static void mousePosCallback(long window, double x, double y) {
        lastMouseX = mouseX;
        lastMouseY = mouseY;
        mouseX = x;
        mouseY = y;
    }

    static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            pressedMouseButtons[button] = true;
        } else if (action == GLFW_RELEASE) {
            pressedMouseButtons[button] = false;
        }
    }

    static void mouseScrollCallback(long window, double xOffset, double yOffset) {
        mouseScrollX = xOffset;
        mouseScrollY = yOffset;
    }
}
