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
	private final Label chaldeanLabel;
	private final TextBox chaldean;
	private final Label pythagoreanLabel;
	private final TextBox pythagorean;
	private final Label iaLabel;
	private final TextBox ia;
	private final Label naeqLabel;
	private final TextBox naeq;
	
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
		chaldeanLabel = new Label();
		chaldean.addValueChangeHandler(new ValueChangeHandler<String>() {
	        @Override
	        public void onValueChange(ValueChangeEvent<String> event) {
	        	chaldeanLabel.setText(chaldean.getText());
	        }
	    });

		pythagorean = new TextBox();
		pythagoreanLabel = new Label();
		pythagorean.addValueChangeHandler(new ValueChangeHandler<String>() {
	        @Override
	        public void onValueChange(ValueChangeEvent<String> event) {
	        	pythagoreanLabel.setText(pythagorean.getText());
	        }
	    });
		
		ia = new TextBox();
		iaLabel = new Label();
		ia.addValueChangeHandler(new ValueChangeHandler<String>() {
	        @Override
	        public void onValueChange(ValueChangeEvent<String> event) {
	        	iaLabel.setText(ia.getText());
	        }
	    });
		
		naeq = new TextBox();
		naeqLabel = new Label();
		naeq.addValueChangeHandler(new ValueChangeHandler<String>() {
	        @Override
	        public void onValueChange(ValueChangeEvent<String> event) {
	        	naeqLabel.setText(naeq.getText());
	        }
	    });
		
		initDetailsTable();
		contentDetailsPanel.add(detailsTable);

		HorizontalPanel menuPanel = new HorizontalPanel();
		saveButton = new Button("Save");
		cancelButton = new Button("Cancel");
		menuPanel.add(saveButton);
		menuPanel.add(cancelButton);
		contentDetailsPanel.add(menuPanel);
		contentDetailsDecorator.add(contentDetailsPanel);

		doChange();
		latinString.selectAll();
		latinString.setFocus(true);
	}

	private void doChange() {
		//force upper case
		String newTerm = latinString.getText().toUpperCase();
		latinString.setText(newTerm);
		
		//create new term and update view
		Term term = new Term(newTerm);
		chaldean.setText(term.getChaldean().toString());
		chaldeanLabel.setText(term.getChaldean().toString());
		pythagorean.setText(term.getPythagorean().toString());		
		pythagoreanLabel.setText(term.getPythagorean().toString());		
		ia.setText(term.getIa().toString());
		iaLabel.setText(term.getIa().toString());
		naeq.setText(term.getNaeq().toString());
		naeqLabel.setText(term.getNaeq().toString());
	}
	
	private void initDetailsTable() {
		detailsTable.setWidget(0, 0, new Label("Term"));
		detailsTable.setWidget(0, 1, latinString);
		detailsTable.setWidget(1, 0, new Label("Chaldean"));
		detailsTable.setWidget(1, 1, chaldeanLabel);
		detailsTable.getCellFormatter().setAlignment(1, 1,
				HasHorizontalAlignment.ALIGN_RIGHT,
				HasVerticalAlignment.ALIGN_MIDDLE);		
		detailsTable.setWidget(2, 0, new Label("Pythagorean"));
		detailsTable.setWidget(2, 1, pythagoreanLabel);
		detailsTable.getCellFormatter().setAlignment(2, 1,
				HasHorizontalAlignment.ALIGN_RIGHT,
				HasVerticalAlignment.ALIGN_MIDDLE);		
		detailsTable.setWidget(3, 0, new Label("A=1, B=2 .. Z=26"));
		detailsTable.setWidget(3, 1, iaLabel);
		detailsTable.getCellFormatter().setAlignment(3, 1,
				HasHorizontalAlignment.ALIGN_RIGHT,
				HasVerticalAlignment.ALIGN_MIDDLE);		
		detailsTable.setWidget(4, 0, new Label("NAEQ"));
		detailsTable.setWidget(4, 1, naeqLabel);
		detailsTable.getCellFormatter().setAlignment(4, 1,
				HasHorizontalAlignment.ALIGN_RIGHT,
				HasVerticalAlignment.ALIGN_MIDDLE);		

		latinString.setFocus(true);
		latinString.selectAll();
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
	
	@Override
	public HasValue<String> getIa() {
		return ia;
	}
	
	@Override
	public HasValue<String> getNaeq() {
		return naeq;
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
