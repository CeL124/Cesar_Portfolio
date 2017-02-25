package application;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class BorderSlideBar extends VBox {

	private double expandedSize;
	private Pos flapbarLocation;
	Animation showPanel, hidePanel;

	/**
	 * 
	 * @param expandedSize
	 * @param onButton
	 * @param location
	 * @param nodes
	 */
	public BorderSlideBar(double expandedSize, final ToggleButton onButton, Pos location, Node... nodes) {

		setExpandedSize(expandedSize);
		setVisible(true);

		// Set location
		if (location == null) {
			flapbarLocation = Pos.TOP_CENTER; // Set default location
		}
		flapbarLocation = location;

		initPosition();

		// Add nodes in the vbox
		getChildren().addAll(nodes);


		onButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent actionEvent) {

				if (!onButton.isSelected()) {
					// Create an animation to hide the panel.
					hidePanel = new Transition() {
						{
							setCycleDuration(Duration.millis(250));
						}

						@Override
						protected void interpolate(double frac) {
							final double size = getExpandedSize() * (1.0 - frac);
							translateByPos(size);
						}
					};

					hidePanel.onFinishedProperty().set(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent actionEvent) {
							setVisible(false);
						}
					});
					onButton.setText("Show Globals");
					hidePanel.play();
				}
				if (onButton.isSelected()) {

					// Create an animation to show the panel.
					showPanel = new Transition() {
						{
							setCycleDuration(Duration.millis(250));
						}

						@Override
						protected void interpolate(double frac) {
							final double size = getExpandedSize() * frac;
							translateByPos(size);
						}
					};

					showPanel.onFinishedProperty().set(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent actionEvent) {
						}
					});
					onButton.setText("Hide Globals");
					setVisible(true);
					showPanel.play();
				}

			}
		});
	}

	/**
	 * Initialize position orientation.
	 */
	private void initPosition() {
		
		switch (flapbarLocation) {
		case BASELINE_RIGHT:
			setPrefWidth(0);
			setMinWidth(0);
			break;
		default:
			break;

		}
	}

	/**
	 * Translate the VBox according to location Pos.
	 *
	 * @param size
	 */
	private void translateByPos(double size) {
		
		
		switch (flapbarLocation) {
		case BASELINE_RIGHT:
			setPrefWidth(size);
			break;
		default:
			break;

		}
	}

	/**
	 * @return the expandedSize
	 */
	public double getExpandedSize() {
		return expandedSize;
		
	}

	/**
	 * @param expandedSize
	 * the expandedSize to set
	 */
	public void setExpandedSize(double expandedSize) {
		this.expandedSize = expandedSize;
	}
}
