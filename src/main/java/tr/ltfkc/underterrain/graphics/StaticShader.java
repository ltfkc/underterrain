package tr.ltfkc.underterrain.graphics;

import org.joml.Matrix4f;

public class StaticShader extends ShaderProgram{

    private static final String VERTEX_SRC = """
            #version 400 core
                        
            in vec3 position;
            in vec2 textureCoordinates;
                        
            out vec3 colour;
            out vec2 pass_textureCoordinates;
                        
            uniform mat4 transformationMatrix;
            uniform mat4 projectionMatrix;
            uniform mat4 viewMatrix;
                        
            void main(void) {
            	gl_Position = projectionMatrix * viewMatrix * transformationMatrix * vec4(position, 1.0);
            	pass_textureCoordinates = textureCoordinates;
            	colour = vec3(position.x + 0.5, 0.0, position.y + 0.5);
            }
            """;
    private static final String FRAGMENT_SRC = """
            #version 400 core
                
            in vec3 colour;
            in vec2 pass_textureCoordinates;
                
            out vec4 out_Color;
                
            uniform sampler2D modelTexture;
                
            void main(void) {
            	out_Color = texture(modelTexture, pass_textureCoordinates);
            }
            """;

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;

    public StaticShader() {
        super(VERTEX_SRC, FRAGMENT_SRC);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoordinates");
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
    }

    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadViewMatrix(Matrix4f matrix){
        super.loadMatrix(location_viewMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f projection){
        super.loadMatrix(location_projectionMatrix, projection);
    }
}