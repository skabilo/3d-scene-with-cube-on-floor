package javafxplhr2;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class CubePlhrEjetastikh extends Application {

    private static final int CUBE_SIZE = 100;
    private static final int FLOOR_SIZE = 500;

    @Override
    public void start(Stage stage) {
        // Create the cube
        Box cube = new Box(CUBE_SIZE, CUBE_SIZE, CUBE_SIZE);
        cube.setTranslateX(150);
        cube.setTranslateY(+370);
        cube.setTranslateZ(50);
        PhongMaterial cubeMaterial = new PhongMaterial();
        cubeMaterial.setDiffuseColor(Color.AQUA);
        cube.setMaterial(cubeMaterial);

        // Create the floor
    Box floor = new Box(FLOOR_SIZE, 15, FLOOR_SIZE);
    floor.setTranslateX(200);
    floor.setTranslateY(420);
    floor.setTranslateZ(100);
    PhongMaterial floorMaterial = new PhongMaterial();
    floorMaterial.setDiffuseColor(Color.BLUE);
    floor.setMaterial(floorMaterial);

        // Add a light source
        PointLight light = new PointLight(Color.rgb(255, 255, 255));
        light.setTranslateX(FLOOR_SIZE / 2);
        light.setTranslateY(FLOOR_SIZE / 2);
        light.setTranslateZ(-100);

        // Add the cube, floor, and light to the scene
        Group root = new Group(floor, cube, light);
        Scene scene = new Scene(root, FLOOR_SIZE, FLOOR_SIZE);

        // Add a camera to the scene
    PerspectiveCamera camera = new PerspectiveCamera(false);
    camera.setTranslateX(CUBE_SIZE / 4);
    camera.setTranslateY(CUBE_SIZE / 4);
    camera.setTranslateZ(-400);
    scene.setCamera(camera);
    
    scene.setOnMouseDragged(event -> {
  double deltaX = event.getSceneX() - scene.getWidth() / 2;
  double deltaY = event.getSceneY() - scene.getHeight() / 2;
  camera.getTransforms().add(new Rotate(-deltaY / 100, Rotate.Y_AXIS));
  
});


    // Add event handlers for key presses
    scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
      switch (event.getCode()) {
        case A:
          cube.setRotate(cube.getRotate() + 10);
          break;
        case D:
          cube.setRotate(cube.getRotate() - 10);
          break;
        case LEFT:
          camera.getTransforms().add(new Rotate(-3, Rotate.Y_AXIS));
          break;
        case RIGHT:
          camera.getTransforms().add(new Rotate(3, Rotate.Y_AXIS));
          break;
      }
    });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
