package arithmea.client.view;

import arithmea.client.presenter.InfoPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * View for the info page.
 */
public class InfoView extends Composite implements InfoPresenter.Display {
    private final Button cancelButton;
    private final FlexTable contentTable;

    /**
     * Default constructor.
     */
    public InfoView() {
        final DecoratorPanel contentTableDecorator = new DecoratorPanel();
        initWidget(contentTableDecorator);
        contentTableDecorator.setWidth("100%");
        contentTableDecorator.setWidth("800px");

        contentTable = new FlexTable();
        contentTable.setWidth("100%");

        final HorizontalPanel menuPanel = new HorizontalPanel();
        cancelButton = new Button("Cancel");
        menuPanel.add(cancelButton);

        contentTable.getCellFormatter().addStyleName(0, 0, "menu-table");
        contentTable.setWidget(0, 0, menuPanel);

        FlexTable infoTable = new FlexTable();
        int row = 0;
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));
        infoTable.setText(row++, 0, "Chaldean:");
        infoTable.setText(row++, 0, "Shows the value of the word in chaldean numerology. The result is not reduced in order to preserve the full information.");
        final String chaldeanWikiUrl = "http://en.wikipedia.org/wiki/Arithmancy#The_Chaldean_method";
        infoTable.setWidget(row++, 0, new Anchor(chaldeanWikiUrl, chaldeanWikiUrl));
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));

        infoTable.setText(row++, 0, "Pythagorean:");
        infoTable.setText(row++, 0, "Shows the value of the word in pythagorean (or agrippan) numerology. The result is not reduced in order to preserve the full information.");
        final String pythWikiUrl = "http://en.wikipedia.org/wiki/Arithmancy#The_Agrippan_method";
        infoTable.setWidget(row++, 0, new Anchor(pythWikiUrl, pythWikiUrl));
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));

        infoTable.setText(row++, 0, "IA:");
        infoTable.setText(row++, 0, "1=A, 2=B, 3=C .. 26=Z. Also known as Simple English Gematria.");
        final String segWikiUrl = "http://wmjas.wikidot.com/simple-english-gematria";
        infoTable.setWidget(row++, 0, new Anchor(segWikiUrl, segWikiUrl));
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));

        infoTable.setText(row++, 0, "NAEQ:");
        infoTable.setText(row++, 0, "New Aeon English Qabalah");
        final String naeqWikiUrl = "http://en.wikipedia.org/wiki/English_Qabalah#ALW_Cipher";
        infoTable.setWidget(row++, 0, new Anchor(naeqWikiUrl, naeqWikiUrl));
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));

        infoTable.setText(row++, 0, "TQ:");
        infoTable.setText(row++, 0, "Trigrammaton Qabalah");
        final String tqWikiUrl = "http://en.wikipedia.org/wiki/English_Qabalah#Trigrammaton_Qabalah_.28TQ.29";
        infoTable.setWidget(row++, 0, new Anchor(tqWikiUrl, tqWikiUrl));
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));

        infoTable.setText(row++, 0, "German:");
        infoTable.setText(row++, 0, "A cipher specific to the german language, that was discovered by Heinz Borchardt.");
        final String gerUrl = "http://www.rolf-keppler.de/schluessel.htm";
        infoTable.setWidget(row++, 0, new Anchor(gerUrl, gerUrl));
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));

        infoTable.setText(row++, 0, "EQ:");
        infoTable.setText(row++, 0, "English Qabalah method that uses the same sequence as NAEQ, but without reducing the Values (thanks to Alien696).");
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));
        infoTable.setWidget(row++, 0, new HTML("&nbsp;"));

        infoTable.setText(row++, 0, "Project page and source-code for ArithmeA 2000:");
        final String projectUrl = "http://tok.github.com/ArithmeA-2000/";
        infoTable.setWidget(row++, 0, new Anchor(projectUrl, projectUrl));

        contentTable.setWidget(1, 0, infoTable);
        contentTable.getCellFormatter().setAlignment(1, 0, HasHorizontalAlignment.ALIGN_LEFT, HasVerticalAlignment.ALIGN_TOP);
        contentTable.getCellFormatter().setHeight(1, 0, "635px");

        contentTableDecorator.add(contentTable);
    }

    @Override
    public final HasClickHandlers getCancelButton() {
        return cancelButton;
    }

    @Override
    public final Widget asWidget() {
        return this;
    }

}
