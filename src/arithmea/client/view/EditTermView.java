package arithmea.client.view;

import arithmea.client.ExtendedTextBox;
import arithmea.client.presenter.EditTermPresenter;
import arithmea.shared.Term;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EditTermView extends Composite implements
		EditTermPresenter.Display {
	private final ExtendedTextBox latinString;
	private final TextBox chaldean;
	private final TextBox pythagorean;
	private final FlexTable detailsTable;
	private final Button saveButton;
	private final Button cancelButton;

	public EditTermView() {
		DecoratorPanel contentDetailsDecorator = new DecoratorPanel();
		contentDetailsDecorator.setWidth("18em");
		initWidget(contentDetailsDecorator);

		VerticalPanel contentDetailsPanel = new VerticalPanel();
		contentDetailsPanel.setWidth("100%");

		detailsTable = new FlexTable();
		detailsTable.setCellSpacing(0);
		detailsTable.setWidth("100%");
		detailsTable.addStyleName("terms-ListContainer");
		detailsTable.getColumnFormatter().addStyleName(1, "add-terms-input");
		latinString = new ExtendedTextBox();
		latinString.addValueChangeHandler(new ValueChangeHandler<String>() {
	        @Override
	        public void onValueChange(ValueChangeEvent<String> event) {
	        	doChange();
	        }
	    });
		latinString.addKeyUpHandler(new KeyUpHandler() {
	        @Override
	        public void onKeyUp(KeyUpEvent event) {
	        	doChange();
	        }
	    });

		chaldean = new TextBox();
		chaldean.setReadOnly(true);
		pythagorean = new TextBox();
		pythagorean.setReadOnly(true);

		initDetailsTable();
		contentDetailsPanel.add(detailsTable);

		HorizontalPanel menuPanel = new HorizontalPanel();
		saveButton = new Button("Save");
		cancelButton = new Button("Cancel");
		menuPanel.add(saveButton);
		menuPanel.add(cancelButton);
		contentDetailsPanel.add(menuPanel);
		contentDetailsDecorator.add(contentDetailsPanel);
	}

	private void doChange() {
		Term term = new Term(latinString.getText());
		chaldean.setText(term.getChaldean().toString());
		pythagorean.setText(term.getPythagorean().toString());
	}
	
	private void initDetailsTable() {
		detailsTable.setWidget(0, 0, new Label("Term"));
		detailsTable.setWidget(0, 1, latinString);
		detailsTable.setWidget(1, 0, new Label("Chaldean"));
		detailsTable.setWidget(1, 1, chaldean);
		detailsTable.setWidget(2, 0, new Label("Pythagorean"));
		detailsTable.setWidget(2, 1, pythagorean);
		latinString.setFocus(true);
	}

	public HasValue<String> getLatinString() {
		return latinString;
	}
	
	@Override
	public HasValue<String> getChaldean() {
		return chaldean;
	}

	@Override
	public HasValue<String> getPythagorean() {
		return pythagorean;
	}
	
	public HasClickHandlers getSaveButton() {
		return saveButton;
	}

	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}

	public Widget asWidget() {
		return this;
	}


}
