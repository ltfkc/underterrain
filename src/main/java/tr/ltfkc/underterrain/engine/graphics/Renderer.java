package tr.ltfkc.underterrain.engine.graphics;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Renderer {

    private StaticShader shader;
    private Map<TexturedModel, List<Renderable>> renderables = new HashMap<>();

    public Renderer() {
        this.shader = new StaticShader();
    }

    public void processRenderable(Renderable renderable){
        TexturedModel texturedModel = renderable.getTexturedModel();
        List<Renderable> batch = renderables.get(texturedModel);
        if (batch != null) {
            batch.add(renderable);
        } else {
            List<Renderable> newBatch = new ArrayList<>();
            newBatch.add(renderable);
            renderables.put(texturedModel, newBatch);
        }
    }

    public void render(Camera camera) {
        shader.start();
        shader.loadViewMatrix(camera.getViewMatrix());
        shader.loadProjectionMatrix(camera.getProjectionMatrix());
        for (TexturedModel model : renderables.keySet()) {
            prepareTexturedModel(model);
            List<Renderable> batch = renderables.get(model);
            for (Renderable renderable : batch) {
                prepareInstance(renderable);
                GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
            }
            unbindTexturedModel();
        }
        shader.stop();
    }

    public void dispose() {
        shader.dispose();
    }

    private void prepareTexturedModel(TexturedModel model) {
        RawModel rawModel = model.getRawModel();
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getTextureID());
    }

    private void unbindTexturedModel() {
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    private void prepareInstance(Renderable renderable) {
        Matrix4f transformationMatrix = createTransformationMatrix(renderable.getPosition(),
                new Vector2f(renderable.getScale()).mul(renderable.getTexturedModel().getTexture().getDimensions()), renderable.getAngle());
        shader.loadTransformationMatrix(transformationMatrix);
    }

    private Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale, float angle) {
        Matrix4f matrix = new Matrix4f();
        matrix.identity();
        matrix.translate(translation.x, translation.y, 0);
        matrix.rotate(angle, new Vector3f(0, 0, 1));
        matrix.scale(scale.x, scale.y, 1);
        return matrix;
    }
}