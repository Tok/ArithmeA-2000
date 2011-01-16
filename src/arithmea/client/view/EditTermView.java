package arithmea.client.view;

import java.util.HashMap;
import java.util.Map;

import arithmea.client.ExtendedTextBox;
import arithmea.client.HebrewTreeWidget;
import arithmea.client.presenter.EditTermPresenter;
import arithmea.shared.GematriaMethod;
import arithmea.shared.HebrewMethod;
import arithmea.shared.LatinMethod;
import arithmea.shared.SephirothData;
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
	private final HebrewTreeWidget tree = new HebrewTreeWidget(SephirothData.WIDTH, SephirothData.HEIGHT);

	private final ExtendedTextBox inputTextBox;
	private final Label latinString;
	private final Label hebrewString;
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
		
		inputTextBox = new ExtendedTextBox();
		inputTextBox.setWidth("100%");
		inputTextBox.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				doChange();
			}
		});
		inputTextBox.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				doChange();
			}
		});
		
		latinString = new Label();
		latinString.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		latinString.setStyleName("latin-label");
		
		hebrewString = new Label();
		hebrewString.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		hebrewString.setStyleName("hebrew-label");
		
		for (LatinMethod gm : LatinMethod.values()) {
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
		
		for (HebrewMethod method : HebrewMethod.values()) {
			final TextBox textBox = new TextBox();
			final Label label = new Label();
			textBox.addValueChangeHandler(new ValueChangeHandler<String>() {
				@Override
				public void onValueChange(ValueChangeEvent<String> event) {
					label.setText(textBox.getText());
				}
			});
			textBoxes.put(method, textBox);
			labels.put(method, label);
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

		contentDetailsPanel.add(tree);
		contentDetailsDecorator.add(contentDetailsPanel);
	}

	private void doChange() {
		// force upper case
		final String newTerm = inputTextBox.getText().toUpperCase();
		final Term term = new Term(newTerm);
		latinString.setText(term.getLatinString());
		hebrewString.setText(term.getHebrewString());
		
		// create new term and update view
		for (LatinMethod gm : LatinMethod.values()) {
			String value = term.get(gm).toString();
			textBoxes.get(gm).setText(value);
			labels.get(gm).setText(value);
		}
		for (HebrewMethod method : HebrewMethod.values()) {
			String value = term.get(method).toString();
			textBoxes.get(method).setText(value);
			labels.get(method).setText(value);
		}
	}

	private void initDetailsTable() {
		detailsTable.setWidget(0, 0, new Label("Please Enter Word:"));
		detailsTable.getCellFormatter().addStyleName(0, 1, "menu-table");
		
		detailsTable.setWidget(0, 1, inputTextBox);
		detailsTable.setWidget(1, 1, latinString);
		detailsTable.getCellFormatter().addStyleName(1, 1, "text-table");
		int row = 2;
		for (LatinMethod gm : LatinMethod.values()) {
			addRow(detailsTable, row, gm.name(), labels.get(gm));
			row++;
		}
		
		detailsTable.setWidget(row, 1, hebrewString);
		detailsTable.getCellFormatter().addStyleName(row, 1, "text-table");
		row++;
		for (HebrewMethod method : HebrewMethod.values()) {
			addRow(detailsTable, row, method.name(), labels.get(method));
			row++;
		}
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

	public HasValue<String> getInputText() {
		return inputTextBox;
	}
	
	public TextBox getInputTextBox() {
		return inputTextBox;
	}
	
	public HasValue<String> getHebrewString() {
		//TODO remove textbox
		TextBox textBox = new TextBox();
		textBox.setValue(hebrewString.getText());
		return textBox;
	}
	
	public HasValue<String> getLatinString() {
		//TODO remove textbox
		TextBox textBox = new TextBox();
		textBox.setValue(latinString.getText());
		return textBox;
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

	@Override
	public HebrewTreeWidget getTree() {
		return tree;
	}

}
