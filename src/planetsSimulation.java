import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.transform.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.*;
import javafx.scene.layout.BackgroundImage.*;


public class planetsSimulation extends Application {

    private Parent createContent() throws Exception {

        Coordinates coordinates = new Coordinates();

        Sphere earth = new Sphere(2);
        earth.setTranslateZ(7);
        earth.setTranslateX(26);
        Image earthImage = new Image("file:../img/earth.jpg");
        PhongMaterial earthPhong = new PhongMaterial();
        earthPhong.setDiffuseMap(earthImage);
        earth.setMaterial(earthPhong);
        RotateTransition rt4 = new RotateTransition();
        rt4.setNode(earth);
        rt4.setDuration(Duration.millis(3000));
        rt4.setAxis(Rotate.Y_AXIS);
        rt4.setByAngle(360);
        rt4.setCycleCount(Animation.INDEFINITE);
        rt4.setInterpolator(Interpolator.LINEAR);
        rt4.play();

        Sphere moon = new Sphere(1);
        moon.getTransforms().add(new Rotate(-15, 0, 20, -20, Rotate.Z_AXIS));
        Image moonImage = new Image("file:../img/moon.jpg");
        PhongMaterial moonPhong = new PhongMaterial();
        moonPhong.setDiffuseMap(moonImage);
        moon.setMaterial(moonPhong);
        RotateTransition rt5 = new RotateTransition();
        rt5.setNode(moon);
        rt5.setDuration(Duration.millis(30000));
        moon.setTranslateZ(7);
        moon.setTranslateX(24.5);
        rt5.setAxis(Rotate.Y_AXIS);
        rt5.setByAngle(360);
        rt5.setCycleCount(Animation.INDEFINITE);
        rt5.setInterpolator(Interpolator.LINEAR);
        rt5.play();

        Sphere mars = new Sphere(2);
        double[] marsxy = coordinates.getMarsCoordinates(30);
        mars.setTranslateZ(marsxy[0]);
        mars.setTranslateX(marsxy[1]);
        Image marsImage = new Image("file:../img/mars.jpg");
        PhongMaterial marsPhong = new PhongMaterial();
        marsPhong.setDiffuseMap(marsImage);
        mars.setMaterial(marsPhong);
        RotateTransition rtmars = new RotateTransition();
        rtmars.setNode(mars);
        rtmars.setDuration(Duration.millis(3120));
        rtmars.setAxis(Rotate.Y_AXIS);
        rtmars.setByAngle(360);
        rtmars.setCycleCount(Animation.INDEFINITE);
        rtmars.setInterpolator(Interpolator.LINEAR);
        rtmars.play();


        Sphere mercury = new Sphere(1.3);
        double[] mercurysxy = coordinates.getMercuryCoordinates(9.5);
        mercury.setTranslateZ(mercurysxy[0]);
        mercury.setTranslateX(mercurysxy[1]);
        Image mercuryImage = new Image("file:../img/mercury.jpg");
        PhongMaterial mercuryPhong = new PhongMaterial();
        mercuryPhong.setDiffuseMap(mercuryImage);
        mercury.setMaterial(mercuryPhong);
        RotateTransition rtMercury = new RotateTransition();
        rtMercury.setNode(mercury);
        rtMercury.setDuration(Duration.seconds(2908));
        rtMercury.setAxis(Rotate.Y_AXIS);
        rtMercury.setByAngle(360);
        rtMercury.setCycleCount(Animation.INDEFINITE);
        rtMercury.setInterpolator(Interpolator.LINEAR);
        rtMercury.play();

        Sphere venus = new Sphere(2);
        double[] venusxy = coordinates.getVenusCoordinates(14.30);
        venus.setTranslateZ(venusxy[0]);
        venus.setTranslateX(venusxy[1]);
        Image venusImage = new Image("file:../img/venus.jpg");
        PhongMaterial venusPhong = new PhongMaterial();
        venusPhong.setDiffuseMap(venusImage);
        venus.setMaterial(venusPhong);
        RotateTransition rtvenus = new RotateTransition();
        rtvenus.setNode(venus);
        rtvenus.setDuration(Duration.seconds(5800));
        rtvenus.setAxis(Rotate.Y_AXIS);
        rtvenus.setByAngle(360);
        rtvenus.setCycleCount(Animation.INDEFINITE);
        rtvenus.setInterpolator(Interpolator.LINEAR);
        rtvenus.play();




        Sphere sun = new Sphere(5);
        sun.setMaterial(new PhongMaterial(Color.RED));
        Image sunImage = new Image("file:../img/sun.jpg");
        PhongMaterial sunPhong = new PhongMaterial();
        sunPhong.setDiffuseMap(sunImage);
        sun.setMaterial(sunPhong);

        Translate pivot = new Translate();
        Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);

        // Create and position camera
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.getTransforms().addAll (
                pivot,
                yRotate,
                new Rotate(-10, Rotate.X_AXIS),
                new Translate(0, 0, -120)
        );
        camera.setFarClip(300);

        // animate the camera position.
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(0), 
                        new KeyValue(yRotate.angleProperty(), 0)
                ),
                new KeyFrame(
                        Duration.seconds(15), 
                        new KeyValue(yRotate.angleProperty(), 360)
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Build the Scene Graph
        Group root = new Group();       
        root.getChildren().add(camera);
        root.getChildren().add(sun);
        root.getChildren().add(mercury);
        root.getChildren().add(venus);
        root.getChildren().add(earth);
        root.getChildren().add(moon);
        root.getChildren().add(mars);



        // set the pivot for the camera position animation base upon mouse clicks on objects
        root.getChildren().stream()
                .filter(node -> !(node instanceof Camera))
                .forEach(node ->
                        node.setOnMouseClicked(event -> {
                            pivot.setX(node.getTranslateX());
                            pivot.setY(node.getTranslateY());
                            pivot.setZ(node.getTranslateZ());
                        })
                );

        // Use a SubScene
        SubScene subScene = new SubScene(
                root,
                1500,900,
                true,
                SceneAntialiasing.BALANCED
        );

        subScene.setCamera(camera);
        Group group = new Group();
        group.getChildren().add(subScene);

        return group;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        Scene scene = new Scene(createContent());
        scene.setFill(new ImagePattern(new Image("file:../img/galaxy.jpg"), 0, 0, 1, 1, true));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}