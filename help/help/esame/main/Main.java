package help.esame.main;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {	
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PowerOutages.fxml"));
			BorderPane root = (BorderPane) loader.load();
			PowerOutagesController controller = loader.getController();

			Model model = new Model();
			controller.setModel(model);

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}}
