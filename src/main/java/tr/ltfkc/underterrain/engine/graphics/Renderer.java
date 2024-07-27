package tr.ltfkc.underterrain.engine.graphics;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.joml.Matrix4f;

import java.util.HashMap;
import java.util.Map;

public class Renderer {

    private StaticShader shader = new StaticShader();
    private Map<TexturedModel, HashMap<Renderable, Integer>> renderables = new HashMap<>();

    private int priorityCount = 0;

    public Renderer() {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0f);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    public void prepare() {
        prepare(Color.BLACK);
    }

    public void prepare(Color color) {
        prepare(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public void prepare(float r, float g, float b, float a) {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(r, g, b, a);
    }

    public void put(Renderable renderable){
        TexturedModel texturedModel = renderable.getTexturedModel();
        HashMap<Renderable, Integer> batch = renderables.get(texturedModel);
        if (batch != null) {
            batch.put(renderable, priorityCount);
        } else {
            HashMap<Renderable, Integer> newBatch = new HashMap<>();
            newBatch.put(renderable, priorityCount);
            renderables.put(texturedModel, newBatch);
        }
        priorityCount++;
    }

    public void put(TexturedModel model, float x, float y, float width, float height, float angle) { // 80
        Renderable renderable = new Renderable(model, x + (width / 2f), y + (height / 2f), width, height, angle);
        put(renderable);
    }

    public void put(TexturedModel model, float x, float y, float angle) {
        put(model, x, y, model.getTexture().getWidth(), model.getTexture().getHeight(), angle);
    }

    public void put(TexturedModel model, float x, float y) {
        put(model, x, y, 0);
    }

    public void render(Camera camera) {
        shader.start();
        shader.loadProjectionMatrix(camera.getProjectionMatrix());
        shader.loadViewMatrix(camera.getViewMatrix());
        for (TexturedModel model : renderables.keySet()) {
            prepareTexturedModel(model);
            HashMap<Renderable, Integer> batch = renderables.get(model);
            for (Renderable renderable : batch.keySet()) {
                prepareInstance(renderable, batch.get(renderable));
                GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
            }
            unbindTexturedModel();
        }
        shader.stop();
        renderables.clear();
        priorityCount = 0;
    }

    public void dispose() {
        shader.dispose();
    }

    private void prepareTexturedModel(TexturedModel model) {
        RawModel rawModel = model.getRawModel();
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getTextureID());
    }

    private void unbindTexturedModel() {
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }

    private void prepareInstance(Renderable renderable, int priority) {
        Matrix4f transformationMatrix = createTransformationMatrix(renderable.getPosition(),
                renderable.getDimensions(),
                renderable.getAngle(), priority * (1f / priorityCount));
        shader.loadTransformationMatrix(transformationMatrix);
    }

    private Matrix4f createTransformationMatrix(Vector2f translation, Vector2f dimensions, float angle, float zBuffer) {
        Matrix4f matrix = new Matrix4f();
        matrix.identity();
        matrix.translate(translation.x, translation.y, zBuffer);
        matrix.rotate(angle, new Vector3f(0, 0, 1));
        matrix.scale(dimensions.x, dimensions.y, 0);
        return matrix;
    }
}