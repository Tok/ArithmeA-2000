package arithmea.client.presenter;

import arithmea.client.ArithmeaServiceAsync;
import arithmea.client.event.EditTermCancelledEvent;
import arithmea.client.event.TermUpdatedEvent;
import arithmea.shared.Term;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class EditTermPresenter implements Presenter{  
  public interface Display {
    HasClickHandlers getSaveButton();
    HasClickHandlers getCancelButton();
    HasValue<String> getLatinString();
    HasValue<String> getChaldean();
    HasValue<String> getPythagorean();
    Widget asWidget();
  }
  
  private Term term;
  private final ArithmeaServiceAsync rpcService;
  private final HandlerManager eventBus;
  private final Display display;
  
  public EditTermPresenter(ArithmeaServiceAsync rpcService, HandlerManager eventBus, Display display) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.term = new Term();
    this.display = display;
    bind();
  }
  
  public EditTermPresenter(ArithmeaServiceAsync rpcService, HandlerManager eventBus, Display display, String id) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.display = display;
    bind();
    
    rpcService.getTerm(id, new AsyncCallback<Term>() {
      public void onSuccess(Term result) {
        term = result;
        EditTermPresenter.this.display.getLatinString().setValue(term.getLatinString());
        EditTermPresenter.this.display.getChaldean().setValue(term.getChaldean().toString());
        EditTermPresenter.this.display.getPythagorean().setValue(term.getPythagorean().toString());
      }
      
      public void onFailure(Throwable caught) {
        Window.alert("Error retrieving term");
      }
    });
    
  }
  
  public void bind() {
    this.display.getSaveButton().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) {
        doSave();
      }
    });

    this.display.getCancelButton().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) {
        eventBus.fireEvent(new EditTermCancelledEvent());
      }
    });
  }

  public void go(final HasWidgets container) {
    container.clear();
    container.add(display.asWidget());
  }

  private void doSave() {
    term.setLatinString(display.getLatinString().getValue());
    term.setChaldean(Integer.decode(display.getChaldean().getValue()));
    term.setPythagorean(Integer.decode(display.getPythagorean().getValue()));
    
    rpcService.updateTerm(term, new AsyncCallback<Term>() {
        public void onSuccess(Term result) {
          eventBus.fireEvent(new TermUpdatedEvent(result));
        }
        public void onFailure(Throwable caught) {
          Window.alert("Error updating Term");
        }
    });
  }
  
}
