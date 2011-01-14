package arithmea.client.presenter;

import java.util.ArrayList;
import java.util.List;

import arithmea.client.ArithmeaServiceAsync;
import arithmea.client.event.AddTermEvent;
import arithmea.client.event.EditTermEvent;
import arithmea.shared.TermDetails;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class TermsPresenter implements Presenter {  
  private List<TermDetails> termDetails;
	  
  public interface Display {
    HasClickHandlers getAddButton();
    HasClickHandlers getDeleteButton();
    HasClickHandlers getList();
    void setData(List<String> data);
    int getClickedRow(ClickEvent event);
    List<Integer> getSelectedRows();
    Widget asWidget();
  }
  
  private final ArithmeaServiceAsync rpcService;
  private final HandlerManager eventBus;
  private final Display display;
  
  public TermsPresenter(ArithmeaServiceAsync rpcService, HandlerManager eventBus, Display view) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.display = view;
  }
  
  public void bind() {
    display.getAddButton().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) {
        eventBus.fireEvent(new AddTermEvent());
      }
    });

    display.getDeleteButton().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) {
        deleteSelectedTerms();
      }
    });
    
    display.getList().addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        int selectedRow = display.getClickedRow(event);
        
        if (selectedRow >= 0) {
          String id = termDetails.get(selectedRow).getId();
          eventBus.fireEvent(new EditTermEvent(id));
        }
      }
    });
  }
  
  public void go(final HasWidgets container) {
    bind();
    container.clear();
    container.add(display.asWidget());
    fetchTermDetails();
  }

  public void sortTermDetails() {
    
    // Yes, we could use a more optimized method of sorting, but the 
    //  point is to create a test case that helps illustrate the higher
    //  level concepts used when creating MVP-based applications. 
    //
    for (int i = 0; i < termDetails.size(); ++i) {
      for (int j = 0; j < termDetails.size() - 1; ++j) {
        if (termDetails.get(j).getDisplayName().compareToIgnoreCase(termDetails.get(j + 1).getDisplayName()) >= 0) {
          TermDetails tmp = termDetails.get(j);
          termDetails.set(j, termDetails.get(j + 1));
          termDetails.set(j + 1, tmp);
        }
      }
    }
  }

  public void setTermDetails(List<TermDetails> termDetails) {
    this.termDetails = termDetails;
  }
  
  public TermDetails getTermDetail(int index) {
    return termDetails.get(index);
  }
  
  private void fetchTermDetails() {
    rpcService.getTermDetails(new AsyncCallback<ArrayList<TermDetails>>() {
      public void onSuccess(ArrayList<TermDetails> result) {
          termDetails = result;
          sortTermDetails();
          List<String> data = new ArrayList<String>();

          for (int i = 0; i < result.size(); ++i) {
            data.add(termDetails.get(i).getDisplayName());
          }
          
          display.setData(data);
      }
      
      public void onFailure(Throwable caught) {
        Window.alert("Error fetching term details");
      }
    });
  }

  private void deleteSelectedTerms() {
    List<Integer> selectedRows = display.getSelectedRows();
    ArrayList<String> ids = new ArrayList<String>();
    
    for (int i = 0; i < selectedRows.size(); ++i) {
      ids.add(termDetails.get(selectedRows.get(i)).getId());
    }
    
    rpcService.deleteTerms(ids, new AsyncCallback<ArrayList<TermDetails>>() {
      public void onSuccess(ArrayList<TermDetails> result) {
        termDetails = result;
        sortTermDetails();
        List<String> data = new ArrayList<String>();

        for (int i = 0; i < result.size(); ++i) {
          data.add(termDetails.get(i).getDisplayName());
        }
        
        display.setData(data);
        
      }
      
      public void onFailure(Throwable caught) {
        Window.alert("Error deleting selected terms");
      }
    });
  }
}
