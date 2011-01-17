package arithmea.client.view;

import java.util.HashMap;
import java.util.Map;

import arithmea.client.presenter.EditTermPresenter;
import arithmea.client.widgets.ExtendedTextBox;
import arithmea.client.widgets.tree.HebrewTreeWidget;
import arithmea.client.widgets.tree.LatinTreeWidget;
import arithmea.shared.data.Term;
import arithmea.shared.gematria.GematriaMethod;
import arithmea.shared.gematria.HebrewMethod;
import arithmea.shared.gematria.LatinMethod;
import arithmea.shared.qabalah.SephirothData;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
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
	private final HorizontalPanel treePanel = new HorizontalPanel();
	private final HebrewTreeWidget hebrewTree = new HebrewTreeWidget(SephirothData.WIDTH, SephirothData.HEIGHT);
	private final LatinTreeWidget latinTree = new LatinTreeWidget(SephirothData.WIDTH, SephirothData.HEIGHT);

	private final ExtendedTextBox inputTextBox;
	private final Label latinString;
	private final Label hebrewString;
	private final Map<GematriaMethod, Anchor> anchors = new HashMap<GematriaMethod, Anchor>();
	private final Map<GematriaMethod, TextBox> textBoxes = new HashMap<GematriaMethod, TextBox>();
	private final FlexTable detailsTable;
	private final Button saveButton;
	private final Button cancelButton;

	public EditTermView() {
		final String word = com.google.gwt.user.client.Window.Location.getParameter("word");
		
		final DecoratorPanel contentDetailsDecorator = new DecoratorPanel();
		contentDetailsDecorator.setWidth("800px");
		initWidget(contentDetailsDecorator);

		final VerticalPanel contentDetailsPanel = new VerticalPanel();
//		contentDetailsPanel.setWidth("100%");
		
		final FlexTable menuTable = new FlexTable();
		menuTable.setWidth("790");
		final HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setWidth("100%");

		final FlowPanel buttonFlow = new FlowPanel();
		cancelButton = new Button("Cancel Input");
		buttonFlow.add(cancelButton);
		saveButton = new Button("Save Word");
		buttonFlow.add(saveButton);
		
		hPanel.add(buttonFlow);
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
		hPanel.add(inputTextBox);
		
		menuTable.getCellFormatter().addStyleName(0, 0, "menu-table");
		menuTable.setWidget(0, 0, hPanel);		
		contentDetailsPanel.add(menuTable);

		detailsTable = new FlexTable();
		detailsTable.setWidth("100%");

		latinString = new Label();
		latinString.setWidth("400px");
		latinString.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		latinString.setStyleName("latin-label");
		
		hebrewString = new Label();
		hebrewString.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		hebrewString.setStyleName("hebrew-label");
		
		for (final LatinMethod method : LatinMethod.values()) {
			final TextBox textBox = new TextBox();
			final Anchor anchor = new Anchor();
			textBox.addValueChangeHandler(new ValueChangeHandler<String>() {
				@Override
				public void onValueChange(ValueChangeEvent<String> event) {
					doChange();
				}
			});
			textBoxes.put(method, textBox);
			anchors.put(method, anchor);
		}
		
		for (final HebrewMethod method : HebrewMethod.values()) {
			final TextBox textBox = new TextBox();
			final Anchor anchor = new Anchor();
			textBox.addValueChangeHandler(new ValueChangeHandler<String>() {
				@Override
				public void onValueChange(ValueChangeEvent<String> event) {
					doChange();
				}
			});
			textBoxes.put(method, textBox);
			anchors.put(method, anchor);
		}
		
		initDetailsTable();
		contentDetailsPanel.add(detailsTable);

		treePanel.add(hebrewTree);
		treePanel.add(latinTree);
		contentDetailsPanel.add(treePanel);
		
		if (word != null && !word.equals("")) {
			inputTextBox.setText(word);
			doChange();
		}
		
		contentDetailsDecorator.add(contentDetailsPanel);
	}
	
	private void doChange() {
		try {
			final Term term = new Term(inputTextBox.getText());
			latinString.setText(term.getLatinString());
			hebrewString.setText(term.getHebrewString());
		
			// create new term and update view
			for (LatinMethod method : LatinMethod.values()) {
				String value = term.get(method).toString();
				textBoxes.get(method).setText(value);
				anchors.get(method).setText(value);
				anchors.get(method).setHref("?method="+method.name()+"&number="+value+"#show");
			}
			for (HebrewMethod method : HebrewMethod.values()) {
				String value = term.get(method).toString();
				textBoxes.get(method).setText(value);
				anchors.get(method).setText(value);
				anchors.get(method).setHref("?method="+method.name()+"&number="+value+"#show");
			}

			// update tree widgets
			hebrewTree.setWord(term.getHebrewString());
			latinTree.setWord(term.getLatinString());

		} catch (IllegalArgumentException iae) {
			latinString.setText("");
			hebrewString.setText("");
			hebrewTree.setWord("");
			latinTree.setWord("");
		}
		
	}

	private void initDetailsTable() {
		detailsTable.setWidget(0, 1, latinString);
		detailsTable.getCellFormatter().addStyleName(0, 1, "text-table");
		int row = 1;
		for (LatinMethod gm : LatinMethod.values()) {
			addRow(detailsTable, row, gm.name(), anchors.get(gm));
			row++;
		}
		
		detailsTable.setWidget(row, 1, hebrewString);
		detailsTable.getCellFormatter().addStyleName(row, 1, "text-table");
		row++;
		for (HebrewMethod method : HebrewMethod.values()) {
			addRow(detailsTable, row, method.name(), anchors.get(method));
			row++;
		}
	}

	private void addRow(final FlexTable table, final int row,
			final String description, final Anchor anchor) {
		table.setWidget(row, 0, new Label(description));
		table.getCellFormatter().addStyleName(row, 0, "border-cell");
		table.setWidget(row, 1, anchor);
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
	public HebrewTreeWidget getHebrewTree() {
		return hebrewTree;
	}
	
	@Override
	public LatinTreeWidget getLatinTree() {
		return latinTree;
	}

}
