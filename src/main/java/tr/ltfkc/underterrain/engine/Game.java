package tr.ltfkc.underterrain.engine;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Game {

    private GameListener gameListener;

    private long glfwWindow;
    private double delta;
    private int fps;
    private int fpsCap;
    private boolean isRunning = false;

    private boolean[] pressedKeys = new boolean[350];
    private boolean[] pressedMouseButtons = new boolean[8];
    private double mouseScrollX, mouseScrollY;
    private double mouseX, mouseY, lastMouseX, lastMouseY;

    public Game(int width, int height, String title, int fpsCap) {
        this.fpsCap = fpsCap;
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW.");
        }

        glfwDefaultWindowHints();

        glfwWindow = glfwCreateWindow(width, height, title, NULL, NULL);
        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window.");
        }

        glfwSetKeyCallback(glfwWindow, this::keyCallback);
        glfwSetMouseButtonCallback(glfwWindow, this::mouseButtonCallback);
        glfwSetCursorPosCallback(glfwWindow, this::mousePosCallback);
        glfwSetScrollCallback(glfwWindow, this::mouseScrollCallback);

        glfwMakeContextCurrent(glfwWindow);
        glfwSwapInterval(0);
        glfwShowWindow(glfwWindow);
    }

    public void run() {
        if(isRunning)
            throw new IllegalStateException("Cannot run the game already running");
        if (gameListener == null)
            throw new IllegalStateException("Cannot run the game without GameListener.");

        isRunning = true;
        GL.createCapabilities();
        gameListener.create(this);
        double last = glfwGetTime();
        int frames = 0;
        double fpsTimer = 0;
        while (!glfwWindowShouldClose(glfwWindow)) {
            double time = glfwGetTime();
            delta += time - last;
            last = time;
            if (delta > 1d / fpsCap) {
                frames++;
                fpsTimer += delta;
                if(fpsTimer >= 1) {
                    fps = frames;
                    frames = 0;
                    fpsTimer = 0;
                }
                glfwPollEvents();
                gameListener.render(this);
                glfwSwapBuffers(glfwWindow);
                mouseScrollX = 0;
                mouseScrollY = 0;
                lastMouseX = mouseX;
                lastMouseY = mouseY;
                delta = 0;
            }
        }
        terminate();
    }

    private void terminate() {
        gameListener.dispose(this);
        glfwDestroyWindow(glfwWindow);
        glfwTerminate();
    }

    // Utilities

    public void setGameListener(GameListener gameListener) {
        this.gameListener = gameListener;
    }

    public GameListener getGameListener() {
        return gameListener;
    }

    public void changeGameListener(GameListener gameListener) {
        this.gameListener.dispose(this);
        this.gameListener = gameListener;
        this.gameListener.create(this);
    }

    public void exit(int status) {
        terminate();
        System.exit(status);
    }

    public float getDelta() {
        return (float) delta;
    }

    public int getFPS() {
        return fps;
    }

    public void setFPSCap(int fpsCap) {
        this.fpsCap = fpsCap;
    }

    public int getFPSCap() {
        return fpsCap;
    }

    public void setWindowSize(int width, int height) {
        glfwSetWindowSize(glfwWindow, width, height);
    }

    // KEYBOARD INPUT

    public boolean isKeyPressed(int key) {
        return pressedKeys[key];
    }

    private void keyCallback(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_PRESS) {
            pressedKeys[key] = true;
        } else if (action == GLFW_RELEASE) {
            pressedKeys[key] = false;
        }
    }

    // MOUSE INPUT

    public boolean isMouseButtonPressed(int button) {
        return pressedMouseButtons[button];
    }

    public double getMouseX() {
        return mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public float getMouseDX() {
        return (float) (mouseX - lastMouseX);
    }

    public float getMouseDY() {
        return (float) (mouseY - lastMouseY);
    }

    public float getMouseScrollX() {
        return (float) mouseScrollX;
    }

    public float getMouseScrollY() {
        return (float) mouseScrollY;
    }

    private void mousePosCallback(long window, double mouseX, double mouseY) {
        lastMouseX = this.mouseX;
        lastMouseY = this.mouseY;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    private void mouseButtonCallback(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            pressedMouseButtons[button] = true;
        } else if (action == GLFW_RELEASE) {
            pressedMouseButtons[button] = false;
        }
    }

    private void mouseScrollCallback(long window, double xOffset, double yOffset) {
        mouseScrollX = xOffset;
        mouseScrollY = yOffset;
    }
}
