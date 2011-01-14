package arithmea.client.view;

import java.util.ArrayList;
import java.util.List;

import arithmea.client.presenter.TermsPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class TermsView extends Composite implements TermsPresenter.Display {
  private final Button addButton;
  private final Button deleteButton;
  private FlexTable termsTable;
  private final FlexTable contentTable;
  
  public TermsView() {
    DecoratorPanel contentTableDecorator = new DecoratorPanel();
    initWidget(contentTableDecorator);
    contentTableDecorator.setWidth("100%");
    contentTableDecorator.setWidth("18em");

    contentTable = new FlexTable();
    contentTable.setWidth("100%");
    contentTable.getCellFormatter().addStyleName(0, 0, "terms-ListContainer");
    contentTable.getCellFormatter().setWidth(0, 0, "100%");
    contentTable.getFlexCellFormatter().setVerticalAlignment(0, 0, DockPanel.ALIGN_TOP);
    
    // Create the menu
    //
    HorizontalPanel hPanel = new HorizontalPanel();
    hPanel.setBorderWidth(0);
    hPanel.setSpacing(0);
    hPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
    addButton = new Button("Add");
    hPanel.add(addButton);
    deleteButton = new Button("Delete");
    hPanel.add(deleteButton);
    contentTable.getCellFormatter().addStyleName(0, 0, "terms-ListMenu");
    contentTable.setWidget(0, 0, hPanel);
    
    // Create the terms list
    //
    termsTable = new FlexTable();
    termsTable.setCellSpacing(0);
    termsTable.setCellPadding(0);
    termsTable.setWidth("100%");
    termsTable.addStyleName("terms-ListContents");
    termsTable.getColumnFormatter().setWidth(0, "15px");
    contentTable.setWidget(1, 0, termsTable);
    
    contentTableDecorator.add(contentTable);
  }
  
  public HasClickHandlers getAddButton() {
    return addButton;
  }
  
  public HasClickHandlers getDeleteButton() {
    return deleteButton;
  }
  
  public HasClickHandlers getList() {
    return termsTable;
  }

  public void setData(List<String> data) {
	  termsTable.removeAllRows();
    
    for (int i = 0; i < data.size(); ++i) {
    	termsTable.setWidget(i, 0, new CheckBox());
      termsTable.setText(i, 1, data.get(i));
    }
  }
  
  public int getClickedRow(ClickEvent event) {
    int selectedRow = -1;
    HTMLTable.Cell cell = termsTable.getCellForEvent(event);
    
    if (cell != null) {
      // Suppress clicks if the user is actually selecting the 
      //  check box
      //
      if (cell.getCellIndex() > 0) {
        selectedRow = cell.getRowIndex();
      }
    }
    
    return selectedRow;
  }
  
  public List<Integer> getSelectedRows() {
    List<Integer> selectedRows = new ArrayList<Integer>();
    
    for (int i = 0; i < termsTable.getRowCount(); ++i) {
      CheckBox checkBox = (CheckBox)termsTable.getWidget(i, 0);
      if (checkBox.getValue()) {
        selectedRows.add(i);
      }
    }
    
    return selectedRows;
  }
  
  public Widget asWidget() {
    return this;
  }
}
