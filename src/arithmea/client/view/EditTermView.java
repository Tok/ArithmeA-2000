package arithmea.client.view;

import java.util.HashMap;
import java.util.Map;

import arithmea.client.ExtendedTextBox;
import arithmea.client.presenter.EditTermPresenter;
import arithmea.shared.GematriaMethod;
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
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EditTermView extends Composite implements
		EditTermPresenter.Display {
	private final ExtendedTextBox latinString;
	private final Map<GematriaMethod, Label> labels = new HashMap<GematriaMethod, Label>();
	private final Map<GematriaMethod, TextBox> textBoxes = new HashMap<GematriaMethod, TextBox>();
	private final FlexTable detailsTable;
	private final Button saveButton;
	private final Button cancelButton;

	public EditTermView() {
		final DecoratorPanel contentDetailsDecorator = new DecoratorPanel();
		contentDetailsDecorator.setWidth("600px");
		initWidget(contentDetailsDecorator);

		final VerticalPanel contentDetailsPanel = new VerticalPanel();
		contentDetailsPanel.setWidth("100%");

		detailsTable = new FlexTable();
		detailsTable.setWidth("100%");
		latinString = new ExtendedTextBox();
		latinString.setWidth("100%");
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

		for (GematriaMethod gm : GematriaMethod.values()) {
			final TextBox textBox = new TextBox();
			final Label label = new Label();
			textBox.addValueChangeHandler(new ValueChangeHandler<String>() {
				@Override
				public void onValueChange(ValueChangeEvent<String> event) {
					label.setText(textBox.getText());
				}
			});
			textBoxes.put(gm, textBox);
			labels.put(gm, label);
		}


		
		initDetailsTable();
		contentDetailsPanel.add(detailsTable);

		final FlexTable menuTable = new FlexTable();
		menuTable.setWidth("100%");
		final HorizontalPanel hPanel = new HorizontalPanel();
		saveButton = new Button("Save");
		hPanel.add(saveButton);
		cancelButton = new Button("Cancel");
		hPanel.add(cancelButton);
		menuTable.getCellFormatter().addStyleName(0, 0, "menu-table");
		menuTable.setWidget(0, 0, hPanel);
		contentDetailsPanel.add(menuTable);
		
		contentDetailsDecorator.add(contentDetailsPanel);

		doChange();
		latinString.selectAll();
		latinString.setFocus(true);
	}

	private void doChange() {
		// force upper case
		final String newTerm = latinString.getText().toUpperCase();
		latinString.setText(newTerm);

		// create new term and update view
		final Term term = new Term(newTerm);
		for (GematriaMethod gm : GematriaMethod.values()) {
			String value = term.get(gm).toString();
			textBoxes.get(gm).setText(value);
			labels.get(gm).setText(value);
		}
	}

	private void initDetailsTable() {
		detailsTable.setWidget(0, 0, new Label("Term"));
		detailsTable.setWidget(0, 1, latinString);
		int row = 1;
		for (GematriaMethod gm : GematriaMethod.values()) {
			addRow(detailsTable, row, gm.name(), labels.get(gm));
			row++;
		}
//		latinString.setFocus(true);
//		latinString.selectAll();
	}

	private void addRow(final FlexTable table, final int row,
			final String description, final Label label) {
		table.setWidget(row, 0, new Label(description));
		table.getCellFormatter().addStyleName(row, 0, "border-cell");
		table.setWidget(row, 1, label);
		table.getCellFormatter().addStyleName(row, 1, "border-cell");
		table.getCellFormatter().setAlignment(row, 1,
				HasHorizontalAlignment.ALIGN_RIGHT,
				HasVerticalAlignment.ALIGN_MIDDLE);
	}

	public HasValue<String> getLatinString() {
		return latinString;
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

	@Override
	public HasValue<String> get(GematriaMethod gm) {
		return textBoxes.get(gm);
	}

}
